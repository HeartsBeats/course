package com.course.server.service;


import com.course.server.domain.Chapter;
import com.course.server.mapper.ChapterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    public List<Chapter> list() {
        return chapterMapper.selectByExample(null);
    }

}
