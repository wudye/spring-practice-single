package com.mwu.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegistrationRequestDto {

    @NotBlank(message = "first name should not be blank")
    private String firstName;

    @NotBlank(message = "last name should not be blank")

    private String lastName;
    @NotBlank(message = "password should not be blank")

    private String password;
    @NotBlank(message = "password should not be blank")
    private String username;
    @NotBlank(message = "password should not be blank")
    private String address;
    @NotBlank(message = "phoneNumber should not be blank")
    private String phoneNumber;
    @NotBlank(message = "email should not be blank")
    private String email;
    @NotBlank(message = "customerType should not be blank")
    @Pattern(
            regexp = "^[CI]$",
            message = "customerType must be either 'C' (Corporate) or 'I' (Individual)"
    )
    private String customerType;
}
