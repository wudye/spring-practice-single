package com.mwu.myv1.componetConfig.kafka;

import com.mwu.myv1.componetConfig.activeMq.Message;

public interface MessageQueuePublisher {

    void publish(String topic, Message message) throws MessageQueueException;
}
