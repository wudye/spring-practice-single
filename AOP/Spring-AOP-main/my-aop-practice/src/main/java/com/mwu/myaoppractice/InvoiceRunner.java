package com.mwu.myaoppractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
// the CommandLineRunner interface, which is a Spring Boot interface used to execute code
// after the application context is loaded and before the Spring Boot application starts.
@Component
public class InvoiceRunner implements CommandLineRunner {

    @Autowired
    private InvoiceBusinessService service;


    @Override
    public void run(String... args) throws Exception {

        service.helloInvoice();
        service.testMethodforAroundAdvice();
        service.saveInvoice();

    }
}
