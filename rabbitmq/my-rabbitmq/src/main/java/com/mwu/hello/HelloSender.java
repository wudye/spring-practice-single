package com.mwu.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String message = "Hello World";
        System.out.println("Sender: " + message);
        this.rabbitTemplate.convertAndSend("hello", message);

    }
}
