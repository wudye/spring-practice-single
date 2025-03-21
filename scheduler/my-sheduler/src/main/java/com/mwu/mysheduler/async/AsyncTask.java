package com.mwu.mysheduler.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public class AsyncTask {

    @Scheduled(cron = "*/1 * * * * ?")
    @Async
    public void task6() {
        System.out.println("Task6: " + Thread.currentThread().getName());
    }

    @Scheduled(fixedRate = 2000)
    @Async
    public void task7() {
        System.out.println("Task7: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            // Handle interrupted exception
            e.printStackTrace();
        }
    }
}
