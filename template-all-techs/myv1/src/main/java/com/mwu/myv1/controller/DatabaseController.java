package com.mwu.myv1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mwu.myv1.utils.response.BaseResponse;


@RestController
@RequestMapping("/api/db")
public class DatabaseController {

    @GetMapping
    public BaseResponse getDBAdmin() {
        return BaseResponse.builder().message("This is the database admin site").success(true).build();
    }
}

