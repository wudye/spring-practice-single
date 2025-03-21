package com.mwu.myv1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/api/locale")
@RequiredArgsConstructor
public class LocaleController {

    private final MessageSource messageSource;

    @GetMapping("/greet")
    public String greet(Locale locale) {
        return messageSource.getMessage("greeting", null, locale);
    }
}
