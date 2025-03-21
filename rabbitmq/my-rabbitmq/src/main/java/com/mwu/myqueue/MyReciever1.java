package com.mwu.myqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "myQueue")
public class MyReciever1 {

    @RabbitHandler
    public void process(String myqueue) {
        System.out.println("Receiver1: " + myqueue);
    }
}
