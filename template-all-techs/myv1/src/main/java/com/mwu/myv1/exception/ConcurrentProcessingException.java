package com.mwu.myv1.exception;
public class ConcurrentProcessingException extends BaseException {

    public ConcurrentProcessingException() {}

    public ConcurrentProcessingException(String msg) {
        super(msg);
    }
}
