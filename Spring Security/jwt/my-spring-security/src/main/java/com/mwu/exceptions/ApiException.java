package com.mwu.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException {
    private  String message;

    public ApiException(String message) {
        super(message);
        this.message = message;
    }
}
