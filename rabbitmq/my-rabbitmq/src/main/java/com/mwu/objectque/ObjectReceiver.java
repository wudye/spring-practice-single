package com.mwu.objectque;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mwu.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "object")
public class ObjectReceiver {

//    @RabbitHandler
//    private void receive(User user) {
//        System.out.println("Receiver: " + user);
//    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void receiveMessage(byte[] message) {
        try {
            User user = objectMapper.readValue(message, User.class);
            System.out.println("Received: " + user.toString());
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert message", e);
        }
    }
}
