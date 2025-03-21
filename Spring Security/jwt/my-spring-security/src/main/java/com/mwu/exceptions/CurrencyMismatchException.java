package com.mwu.exceptions;

public class CurrencyMismatchException extends RuntimeException{
    private String message;

    public CurrencyMismatchException() {
        super();
        this.message =  "Currency mismatch";
    }

    public CurrencyMismatchException(String message) {
        super(message);
        this.message = message;
    }
}
