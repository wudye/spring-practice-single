package com.mwu.mysheduler.shedul1;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class task3 {

    // To trigger the scheduler to run every two seconds
    @Scheduled(fixedRate = 2000)
    private void reportCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Task3: " + simpleDateFormat.format(new Date()));
    }
    // To trigger the scheduler every one minute
    // between 19:00 PM to 19:59 PM
     @Scheduled(cron="0 * 19 * * ?")
    public void scheduleTaskUsingCronExpression() {

         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            System.out.println("Task4: " + simpleDateFormat.format(new Date()));
    }

    // To trigger the scheduler every 3 seconds with
    // an initial delay of 5 seconds.
    @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    public void scheduleTaskWithInitialDelay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        System.out.println("Task5: " + simpleDateFormat.format(new Date()));
    }
}
