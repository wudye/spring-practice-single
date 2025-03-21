package com.mwu.myv1.componetConfig.activeMq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ActiveMQListener {
    @JmsListener(destination = "demo-queue")
    public void receiveMessage(DemoMessage message) {
        log.info("Received message with id = {}", message.getId());
    }
}
