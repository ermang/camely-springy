package com.eg.camely.springy.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class JmsRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("jms:queue:in-queue")
                .log("lolo")
        .to("jms:queue:out-queue");

    }
}
