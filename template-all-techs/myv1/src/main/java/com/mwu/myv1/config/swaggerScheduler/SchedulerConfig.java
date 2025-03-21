package com.mwu.myv1.config.swaggerScheduler;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnProperty(prefix = "com.mwu.myv1.scheduling", name="enabled", havingValue="true", matchIfMissing = true)
public class SchedulerConfig {
}

//
//@Configuration
//public class SchedulerConfig {
//    @Bean
//    public TaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(5);
//        scheduler.setThreadNamePrefix("MyTaskScheduler-");
//        scheduler.setWaitForTasksToCompleteOnShutdown(true);
//        return scheduler;
//    }
//}