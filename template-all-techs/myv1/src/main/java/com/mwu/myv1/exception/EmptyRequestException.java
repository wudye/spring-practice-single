package com.mwu.myv1.exception;
public class EmptyRequestException extends BaseException {

    public EmptyRequestException(String msg) {
        super(msg);
    }

    public EmptyRequestException() {
        super();
    }
}
