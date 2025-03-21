package com.example.mythymleaf.controller;

import com.example.mythymleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ExampleController {

    @RequestMapping("/string")
    public String string(ModelMap map) {
        map.addAttribute("userName", "Tom");
        return "string";
    }

    @RequestMapping("/if")
    public String ifUnles(ModelMap map) {
        map.addAttribute("flag", "no");
        return "if";
    }

    @RequestMapping("/list")
    public String list(ModelMap map) {
        map.addAttribute("users", getUserList());
        return "list";
    }

    @RequestMapping("/url")
    public String url(ModelMap map) {
        map.addAttribute("url", "https://www.baidu.com/");
        map.addAttribute("type", "baidu");
        map.addAttribute("img", "https://www.baidu.com/img/bd_logo1.png");
        return "url";

    }

    @RequestMapping("/eq")
    public String eq(ModelMap map) {
        map.addAttribute("name", "Tom");
        map.addAttribute("age", 21);
        map.addAttribute("flag", "yes");
        return "eq";
    }

    @RequestMapping("/switch")
    public String switchCase(ModelMap map) {
        map.addAttribute("sex", "woman");
        return "switch";
    }

    private Object getUserList() {
        List<User> list = new ArrayList<User>();
        User user1 = new User("Tom", 21, "123456");
        User user2 = new User("Mike", 22, "123456");
        User user3 = new User("Jane", 23, "123456");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        return list;
    }
}
