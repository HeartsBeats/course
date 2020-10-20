package com.course.system.controller;

import com.course.system.domain.Test;
import com.course.system.service.TextService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class SystemController {
    @Resource
    private TextService textService;
    @RequestMapping("/hello")
    public String helloWorld() {
        return "success";
    }
    @RequestMapping("/test")
    public List<Test> test() {
        return textService.list();
    }

}
