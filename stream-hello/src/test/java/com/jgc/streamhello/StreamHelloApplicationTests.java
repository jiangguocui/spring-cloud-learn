package com.jgc.streamhello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
//@SpringBootTest
@EnableBinding(value = {StreamHelloApplicationTests.SinkSender.class})
public class StreamHelloApplicationTests {


    @Autowired
    private SinkSender sinkSender;

    @Test
    public void contextLoads() {

        sinkSender.ouput().send(MessageBuilder.withPayload("send a message").build());
    }

    @Service
    public interface SinkSender{
        String OUTPUT = "input";

        @Output(SinkSender.OUTPUT)
        MessageChannel ouput();
    }

}
