package com.mwu.myv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchClientAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {ElasticsearchClientAutoConfiguration.class, ElasticsearchRepositoriesAutoConfiguration.class})
@EnableAsync
@EnableFeignClients
@EnableRetry
@EnableCaching
public class Myv1Application {

    public static void main(String[] args) {
        SpringApplication.run(Myv1Application.class, args);
    }

}
