package com.course.business.controller;

import com.course.server.domain.Chapter;
import com.course.server.service.ChapterService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ChapterController {
    @Resource
    private ChapterService chapterService;
    @RequestMapping("/hello")
    public String helloWorld() {
        return "success";
    }
    @RequestMapping("/chapter")
    public List<Chapter> chapter() {
        return chapterService.list();
    }

}
