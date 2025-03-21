package com.mwu.service;

import com.mwu.common.APIResponse;
import com.mwu.dto.request.LoginRequestDto;
import com.mwu.dto.request.RegistrationRequestDto;
import com.mwu.dto.response.RegistrationResponseDto;
import com.mwu.model.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<APIResponse<String>> authenticateAndReturnToken(LoginRequestDto loginRequestDto);

    ResponseEntity<APIResponse<RegistrationResponseDto>> register(RegistrationRequestDto registrationRequestDto);

    Customer getCustomerById(Long accountId);
}
