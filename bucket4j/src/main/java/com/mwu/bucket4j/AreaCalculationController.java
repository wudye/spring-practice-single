package com.mwu.bucket4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AreaCalculationController {

    @GetMapping("/data")
    public String getData() {
        return "Here is the protected data!";
    }
}
