package com.mwu.myv1.controller;

import com.mwu.myv1.utils.response.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends BaseController {
    @GetMapping
    public BaseResponse getAdmin() {
        return BaseResponse.builder().message("Hello Admin!").success(true) .build();
    }
}
