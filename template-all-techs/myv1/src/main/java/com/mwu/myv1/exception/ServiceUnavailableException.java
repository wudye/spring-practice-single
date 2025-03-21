package com.mwu.myv1.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException(String msg) {
        super(msg);
    }

    public ServiceUnavailableException() {
        super();
    }
}
