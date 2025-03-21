package com.mwu.mysheduler.contriller;

import com.mwu.mysheduler.servcie.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;
    @GetMapping("/test")
    public String test() {
        testService.test();
        return "Test form  controller";
    }

}
