package com.mwu.withswagger.aop;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopConfig {

    private static final Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Before("execution(* com.mwu.withswagger.service.EmployeeService.*(..))")
    public void logBefore() {
        log.info("AOP: Before method execution");
    }

    @AfterReturning(value = "execution(* com.mwu.withswagger.service.EmployeeService.*(..))", returning = "result")
    public void logAfterReturning(Object result) {
        log.info("AOP: After method execution. Returned value: " + result);
    }

    @AfterThrowing(value = "execution(* com.mwu.withswagger.service.EmployeeService.*(..))", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        log.error("AOP: Exception thrown: " + exception);
    }


}
