package com.mwu.myv1.componetConfig.kafka;

import com.mwu.myv1.componetConfig.activeMq.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service("KafkaPublisher")
@RequiredArgsConstructor
public class KafkaPublisher implements MessageQueuePublisher {

    private final KafkaTemplate kafkaTemplate;

    @Override
    public void publish(String topic, Message message) throws MessageQueueException {
        kafkaTemplate.send(topic, message);
    }
}
