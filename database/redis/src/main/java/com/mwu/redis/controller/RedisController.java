package com.mwu.redis.controller;

import com.mwu.redis.model.Student;
import com.mwu.redis.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/save")
    public Student save() {
        return studentService.save();
    }
}
