package com.mwu.myv1.controller;

import com.mwu.myv1.config.auth.CustomUserDetails;
import com.mwu.myv1.config.auth.JwtTokenProvider;
import com.mwu.myv1.config.auth.service.UserService;
import com.mwu.myv1.constant.AppConstants;
import com.mwu.myv1.utils.EncryptedPasswordUtils;
import com.mwu.myv1.utils.request.LoginRequest;
import com.mwu.myv1.utils.response.BaseResponse;
import com.mwu.myv1.utils.response.LoginResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api")
@Tag(name = "Login Controller")
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    private final UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        CustomUserDetails userDetails = userService.getUserDetailByUsername(request.getUsername());
        if (Objects.isNull(userDetails) ||  !EncryptedPasswordUtils.getPasswordEncoder()
                .matches(request.getPassword(), userDetails.getEncryptedPassword())) {
            return LoginResponse.builder().success(false).message("invalid username/password").build();

        }
        String jwt = tokenProvider.generateJwtToken(userDetails.getUserId());
        return LoginResponse.builder()
                .success(true)
                .accessToken(jwt)
                .tokenType(AppConstants.TOKEN_TYPE)
                .build();
    }
    @GetMapping("/test-valid-jwt")
    public BaseResponse testValidJwt() {
        return BaseResponse.builder().success(true).message("You are using a valid JWT").build();
    }
}
