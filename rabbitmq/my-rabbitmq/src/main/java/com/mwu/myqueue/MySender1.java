package com.mwu.myqueue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySender1 {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send() {
        String message = "MyQueue1";
        System.out.println("Sender1: " + message);
        this.amqpTemplate.convertAndSend("myQueue", message);
    }
}
