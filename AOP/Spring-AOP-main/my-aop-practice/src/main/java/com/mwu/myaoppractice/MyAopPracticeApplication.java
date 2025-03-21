package com.mwu.myaoppractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class MyAopPracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyAopPracticeApplication.class, args);
    }

}
