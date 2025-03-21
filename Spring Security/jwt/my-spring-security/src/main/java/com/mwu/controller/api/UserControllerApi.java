package com.mwu.controller.api;

import com.mwu.common.APIResponse;
import com.mwu.dto.request.LoginRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface UserControllerApi {
    ResponseEntity<APIResponse<String>> authentication(@RequestBody @Valid LoginRequestDto loginRequestDto);

}
