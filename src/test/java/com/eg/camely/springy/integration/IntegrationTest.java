package com.eg.camely.springy.integration;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaKraftBroker;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@CamelSpringBootTest
@SpringBootTest
public class IntegrationTest {

    private static EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private ProducerTemplate producerTemplate;

    @Autowired
    private ConsumerTemplate consumerTemplate;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeAll
    public static void setup() {
        embeddedKafkaBroker = new EmbeddedKafkaKraftBroker(1, 1, "in-topic", "out-topic");
        embeddedKafkaBroker.afterPropertiesSet();
        String brokerAddress = embeddedKafkaBroker.getBrokersAsString();

        System.out.println("Kafka Broker is running on: " + brokerAddress);  // This should print the dynamic port

        // Dynamically set the broker address for Camel configuration
        // Using System.setProperty will make the configuration available for Camel
        System.setProperty("camel.component.kafka.brokers", brokerAddress);
    }

    @Test
    public void kafka_test() {
        String expectedBody = """
                {"key": "value"}
                """;

        producerTemplate.sendBody("kafka:in-topic", expectedBody);

        String receivedBody = consumerTemplate.receiveBody("kafka:out-topic?seekTo=BEGINNING", 5000L, String.class);

        Assertions.assertEquals(expectedBody, receivedBody);
    }

    @Test
    public void jms_test() {
        String expectedBody = """
                {"key": "value"}
                """;

        producerTemplate.sendBody("jms:queue:in-queue", expectedBody);

        String receivedBody = consumerTemplate.receiveBody("jms:queue:out-queue", 5000L, String.class);

        Assertions.assertEquals(expectedBody, receivedBody);
    }
}
