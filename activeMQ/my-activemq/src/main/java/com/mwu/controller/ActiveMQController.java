package com.mwu.controller;

import com.mwu.CustomMessage;
import com.mwu.dto.Store;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class ActiveMQController {

//    private final ActiveMQProducer activeMQProducer;
//    @PostMapping
//    public String publish(@RequestParam String message) {
//        String messageId = UUID.randomUUID().toString();
//
//        log.info("Message ID: {}", messageId);
//        activeMQProducer.produceMessagesString(
//                messageId,
//                CustomMessage.builder()
//                        .id(messageId)
//                        .message(message)
//                        .build(),
//                10
//        );
//        return "Message sent successfully";
//
//    }


    private final JmsTemplate jmsTemplate;

    private final ActiveMQProducer storeMessageProducer;

    @Value("${activemq.destination}")
    private String destination;

    /**
     * API for publish message for ActiveMQ queue
     * @param store
     * @return String
     */
    @PostMapping("/publish")
    public String publishMessage(@RequestBody Store store){
        storeMessageProducer.sendTo(destination,store);
        return "Success";
    }
}
