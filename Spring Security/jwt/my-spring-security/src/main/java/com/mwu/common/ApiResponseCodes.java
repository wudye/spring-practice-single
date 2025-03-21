package com.mwu.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
public enum ApiResponseCodes {
    SUCCESS("00", "Success"),
    FAILED("01", "Operation Failed."),
    PENDING("02", "Operation Processing.");

    private final String code;
    private final String status;

    ApiResponseCodes(String code, String status) {
        this.code = code;
        this.status = status;
    }


}
