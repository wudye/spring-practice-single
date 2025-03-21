package com.mwu.jediscrud.controller;

import com.mwu.jediscrud.model.Student;
import com.mwu.jediscrud.service.StudentService;
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
