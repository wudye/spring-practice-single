package com.example.myfileupload.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MultipartException.class)
    public String handlerMultipartException(MultipartException e, RedirectAttributes redirectAttributes) {
        System.out.println(e.getMessage());
        redirectAttributes.addFlashAttribute("messsage", e.getMessage());
        return "redirect:/uploadStatus";
    }
}
