package com.mwu.layout;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LayoutController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
    @RequestMapping("fragment")
    public String fragment() {
        return "fragment";
    }
}
