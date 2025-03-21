package com.mwu.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NotFoundException extends RuntimeException {


    private String message;

    public NotFoundException() {
        super();
        this.message = "Record not found";
    }

    public NotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}