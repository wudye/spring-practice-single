package com.mwu.myv1.config.retry;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {

    @Bean
    public RetryTemplate retryTemplate() {
       RetryTemplate retryTemplate =  new RetryTemplate();
       retryTemplate.registerListener(new CustomRetryListener());
         return retryTemplate;
    }
}
