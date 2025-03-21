package com.mwu.mysheduler.servcie;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestService {

    @Scheduled(cron = "*/1 * * * * ?")
    @Async
    public void test() {
        System.out.println( "Test from TestService");
    }
}
