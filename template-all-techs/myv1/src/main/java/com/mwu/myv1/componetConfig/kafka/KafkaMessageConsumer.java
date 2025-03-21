package com.mwu.myv1.componetConfig.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.mwu.myv1.componetConfig.activeMq.DemoMessage;
import com.mwu.myv1.constant.KafkaConstants;
import com.mwu.myv1.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class KafkaMessageConsumer {

    @KafkaListener(
            id = "messge-consumer",
            concurrency = KafkaConstants.MESSAGE_CONCURRENCY,
            topics = KafkaConstants.MESSAGE_TOPIC
    )
    public  void consume(List<String> message) {
        for (String msg : message) {
            try {
                DemoMessage demoMessage = JsonUtils.map(msg, new TypeReference<>() {});
                log.info("Received message {}", JsonUtils.toJson(demoMessage));
            } catch (Exception e) {
                log.error("> Cannot deserialize msg: {}, Exception: ", msg, e);
            }
        }
    }
}
