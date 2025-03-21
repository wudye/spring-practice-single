package com.mwu.myaoppractice;

import org.springframework.stereotype.Service;

@Service
public class InvoiceBusinessService {

    public void saveInvoice() {
        try {
            // Your business logic here
            System.out.println("From saveInvoice()");
            // Simulate an exception for demonstration purposes
            throw new RuntimeException("Exception occurred");
        } catch (Exception e) {
            // Handle the exception
            System.err.println("Exception in saveInvoice: " + e.getMessage());
            // Optionally log the exception or perform other actions
            // Do not re-throw the exception to allow the application to continue running
        }
    }

    public String helloInvoice() {
        System.out.println("From helloInvoice()");
        return "FROM helloInvoice()";
    }

    public void testMethodforAroundAdvice() {
        System.out.println("Business Method is getting Executed !");
    }
}