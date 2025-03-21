package com.mwu.myv1.utils.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class BaseResponse {
    protected String message;
    protected Boolean success;
}
