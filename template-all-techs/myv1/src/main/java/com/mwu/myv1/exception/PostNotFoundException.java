package com.mwu.myv1.exception;
public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(String msg) {
        super(msg);
    }

    public PostNotFoundException() {
        super();
    }
}