package com.mwu.myv1.componetConfig.activeMq;

import com.mwu.myv1.exception.ActiveMQPublishException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ActiveMQProducer {

    private final JmsTemplate jmsTemplate;

    @Retryable(
            value = ActiveMQPublishException.class,
            maxAttemptsExpression = "${retry.maxAttempts}",
            backoff = @Backoff(delayExpression = "${retry.backoffDelay}")
    )
    public void send(String queueName, DemoMessage message) throws ActiveMQPublishException {
        try {
            jmsTemplate.send(queueName, session -> session.createObjectMessage(message));
            log.info("> ActiveMQProducer sent message to queue: {}", queueName);
        } catch (Exception e) {
            log.error("> ERROR: Something went wrong when sending message to queue: ", e);
            throw new ActiveMQPublishException("Something went wrong when sending message to queue");
        }
    }



}
