package com.mwu.exceptions;

public class ValidationException extends RuntimeException{
    private String message;

    public ValidationException() {
        super();
        this.message =  "Validation failed";
    }

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }
}
