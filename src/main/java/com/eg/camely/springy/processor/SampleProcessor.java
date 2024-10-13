package com.eg.camely.springy.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleProcessor implements Processor {

    private static final Logger logger = LoggerFactory.getLogger(SampleProcessor.class);


    @Override
    public void process(Exchange exchange) throws Exception {
        //logger.info();
        throw new RuntimeException("thrown");
    }
}
