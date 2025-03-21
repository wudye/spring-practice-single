package com.mwu.mysheduler.shedul1;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Task1 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");


    @Scheduled(fixedRate = 5000)
    private void reportCurrentTime() {
        System.out.println("The time is now " + dateFormat.format(new Date()));
    }
}
