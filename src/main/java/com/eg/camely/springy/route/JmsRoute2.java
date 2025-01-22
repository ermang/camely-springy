//package com.eg.camely.springy.route;
//
//import com.eg.camely.springy.processor.ExceptionProcessor;
//import com.eg.camely.springy.processor.SampleProcessor;
//import org.apache.camel.builder.RouteBuilder;
//import org.springframework.stereotype.Component;
//
//@Component
//public class JmsRoute2 extends RouteBuilder {
//
//    private final ExceptionProcessor exceptionProcessor;
//    private final SampleProcessor sampleProcessor;
//
//    public JmsRoute2(ExceptionProcessor exceptionProcessor, SampleProcessor sampleProcessor) {
//        this.exceptionProcessor = exceptionProcessor;
//        this.sampleProcessor = sampleProcessor;
//    }
//
//    @Override
//    public void configure() throws Exception {
//
//        onException(Exception.class)
//                .process(exceptionProcessor);
//
//
//        from("jms:queue:my-queue")
//                .process(sampleProcessor);
//    }
//}
