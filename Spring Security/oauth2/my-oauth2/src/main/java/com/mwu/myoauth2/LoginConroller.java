package com.mwu.myoauth2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginConroller {

    @GetMapping("/login")
    public String login(HttpServletRequest request, Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "Invalid username and password");
        }
        return "pages/login";
    }

    @GetMapping("/")
    public String home() {
        return "pages/home";
    }

}
