package com.mwu.objectque;

import com.mwu.model.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ObjectSender {

    @Autowired private RabbitTemplate rabbitTemplate;

    public void send(User user) {
        System.out.println("Sender: " + user.toString());
        this.rabbitTemplate.convertAndSend("object", user);
    }
}
