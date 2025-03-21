package com.mwu.mysheduler.shedul1;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Task2 {

    private int count = 0;

    @Scheduled(cron="*/5 * * * * ?")
    private void reportCurrentTime() {
        System.out.println("Task2: " + count++);
    }
}
