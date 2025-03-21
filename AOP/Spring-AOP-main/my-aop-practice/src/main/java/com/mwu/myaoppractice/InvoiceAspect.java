package com.mwu.myaoppractice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class InvoiceAspect {

    @Pointcut("execution(* com.mwu.myaoppractice.InvoiceBusinessService.saveInvoice(..))")
    public void p1() {
        System.out.println("Pointcut saveInvoice() is is pp1111111111");
    }
    @Pointcut("execution(* com.mwu.myaoppractice.InvoiceBusinessService.helloInvoice(..))")
    public void p2() {
    }

    @Pointcut("execution(* com.mwu.myaoppractice.InvoiceBusinessService.testMethodforAroundAdvice(..))")
    public void p3() {
    }

    @Before("p1()")
    public void beforeSaveInvoice() {
        System.out.println("Before saveInvoice()");
    }
    @After("p1()")
    public void afterSaveInvoice() {
        System.out.println("After saveInvoice()");
    }
    @AfterReturning("p1()")
    public void afterReturningSaveInvoice() {
        System.out.println("AfterReturning saveInvoice()");
    }
    @AfterThrowing("p1()")
    public void afterThrowingSaveInvoice() {
        System.out.println("AfterThrowing saveInvoice()");
    }

    @AfterReturning(value = "p2()", returning = "obj")
    public void afterReturningHelloInvoice(String obj) {
        System.out.println("AfterReturning helloInvoice() : " + obj);
    }

    @AfterThrowing(value = "p2()", throwing = "th")
    public void afterThrowingHelloInvoice(Throwable th) {
        System.out.println("AfterThrowing helloInvoice() : " + th);
    }

    @Around("p3()")
    public void aroundTestMethodforAroundAdvice(ProceedingJoinPoint p) throws Throwable {
        System.out.println("Around testMethodforAroundAdvice() : Before");
        p.proceed(); //   pj.proceed(); // this code will call business method
        System.out.println("Around testMethodforAroundAdvice() : After");
    }


}
