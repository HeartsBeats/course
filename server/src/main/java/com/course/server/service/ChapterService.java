package com.course.server.service;


import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 查询全部数据
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapters);
        pageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto>  chapterDtoList = CopyUtils.copyList(chapters,ChapterDto.class);
        pageDto.setList(chapterDtoList);
    }
    /*
     *  保存数据
     * @Author: YJQ
     * @Date: 2020-10-27 09:12
     */
    public void save(ChapterDto chapterDto){
        Chapter chapter = new Chapter();
        chapterDto.setId(UuidUtil.getShortUuid());
        chapter = CopyUtils.copy(chapterDto,chapter.getClass());
        chapterMapper.insert(chapter);
    }

}
