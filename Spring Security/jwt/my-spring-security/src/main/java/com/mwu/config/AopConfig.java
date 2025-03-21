package com.mwu.config;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// two with Apointcut and one without
@Aspect
@Component
// @Configuration
public class AopConfig {
    private static final Logger logger = LoggerFactory.getLogger(AopConfig.class);

    @Pointcut("execution(* com.mwu.utils.AccountUtil.generateRandomStringAccount(..))")
    public void accountUtilsPointcut() {
    }

    @Before("accountUtilsPointcut()")
    public void beforeAccountUtils() {
        System.out.println("Before AccountUtil.generateRandomStringAccount() method");
    }

    @AfterReturning(value = "accountUtilsPointcut()", returning = "result")
    public void afterAccountUtils(String result) {
        System.out.println("After AccountUtil.generateRandomStringAccount() method");
        System.out.println("Generated Account Number: " + result);
    }

    @Before("execution(* com.mwu.controller.AuthController(..))")
    public void beforeAuthController() {
        System.out.println("Before AuthController class");
        logger.info("Before AuthController class");
    }

    @After("execution(* com.mwu.controller.AuthController(..))")
    public void afterAuthController() {
        System.out.println("After AuthController class");
        logger.info("After AuthController class");
    }


}
