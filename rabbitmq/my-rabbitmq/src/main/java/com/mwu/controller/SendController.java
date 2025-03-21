package com.mwu.controller;

import com.mwu.hello.HelloSender;
import com.mwu.model.User;
import com.mwu.myqueue.MySender1;
import com.mwu.objectque.ObjectSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendController {

    @Autowired
    private HelloSender helloSender;

    @Autowired private ObjectSender objectSender;

    @Autowired
    private MySender1 mySender1;

    @GetMapping("/send")
    public String send() {
        helloSender.send();
        return "send success";
    }

    @PostMapping("/send/user")
    public String sendUser(@RequestBody User user) {
        objectSender.send(user);
        return "send success";

    }

    @GetMapping("/send/myqueue1")
    public String sendMyQueue1() {
        mySender1.send();
        return "send success";
    }

}
