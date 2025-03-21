package com.mwu.myv1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthCheckController extends BaseController {

    @GetMapping("/")
    public String ping() {
        return "pong";
    }
}
