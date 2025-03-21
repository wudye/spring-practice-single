package com.mwu.mysheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyShedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyShedulerApplication.class, args);
    }

}
