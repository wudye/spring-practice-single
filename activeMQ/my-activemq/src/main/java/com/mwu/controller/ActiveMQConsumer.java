package com.mwu.controller;

import com.mwu.dto.Store;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.message.Message;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j

public class ActiveMQConsumer {

    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void consume(Store message) {
        log.info("Received message: {}", message);
    }
}
