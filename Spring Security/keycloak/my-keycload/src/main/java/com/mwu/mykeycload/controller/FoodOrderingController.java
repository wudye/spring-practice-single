package com.mwu.mykeycload.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodOrderingController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/menu")
    public String menu(@AuthenticationPrincipal OidcUser user , Model model) {
        if (user != null) {
            model.addAttribute("userName", user.getFullName());
        } else {
            return "redirect:/";
        }
        return "menu";
    }
}
