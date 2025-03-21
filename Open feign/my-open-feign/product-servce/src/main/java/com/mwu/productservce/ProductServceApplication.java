package com.mwu.productservce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ProductServceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServceApplication.class, args);
    }

}
