package com.mwu.myv1.componetConfig.event;

import com.mwu.myv1.model.mongoDB.DemoLog;
import com.mwu.myv1.repository.mongoDB.DemoLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@Async("threadPoolTaskExecutor2")
@RequiredArgsConstructor
public class NewProductEventListener implements ApplicationListener<NewProductEvent> {

    private final DemoLogRepository demoLogRepository;

    @Override
    public void onApplicationEvent(NewProductEvent event) {
        log.info("NewProductEventListener resolved {}", event);
        demoLogRepository.insert(DemoLog.builder().requestId(event.getRequestId()).build());
    }
}
