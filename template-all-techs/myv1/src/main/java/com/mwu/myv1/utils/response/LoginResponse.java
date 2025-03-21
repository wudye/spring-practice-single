package com.mwu.myv1.utils.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {
    private Boolean success;
    private String message;
    private String accessToken;
    private String tokenType;
}
