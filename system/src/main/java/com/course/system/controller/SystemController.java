package com.course.system.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    @RequestMapping("/hello")
    public String helloWorld() {
        return "success";
    }
}
