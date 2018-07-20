package com.jgc.streamhello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class Sinkreceiver {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @StreamListener(Sink.INPUT)
    public void receive(Object payload){
        logger.info("Received" + payload);
    }

}
