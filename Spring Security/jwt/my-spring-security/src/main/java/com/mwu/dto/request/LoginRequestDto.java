package com.mwu.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDto {
    @NotBlank(message = "username should not be blank")
    private String username;
    @NotBlank(message = "password should not be blank")
    private String password;
}
