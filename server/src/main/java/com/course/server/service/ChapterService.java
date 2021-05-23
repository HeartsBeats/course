package com.course.server.service;


import com.course.server.domain.Chapter;
import com.course.server.domain.ChapterExample;
import com.course.server.dto.ChapterDto;
import com.course.server.dto.ChapterPageDto;
import com.course.server.mapper.ChapterMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ChapterService {
    @Resource
    private ChapterMapper chapterMapper;

    /**
     * 查询全部数据
     */
    public void list(ChapterPageDto chapterPageDto) {
        PageHelper.startPage(chapterPageDto.getPage(), chapterPageDto.getSize());
        ChapterExample chapterExample = new ChapterExample();
        ChapterExample.Criteria criteria = chapterExample.createCriteria();
        if (!StringUtils.isEmpty(chapterPageDto.getCourseId())) {
            criteria.andCourseIdEqualTo(chapterPageDto.getCourseId());
        }
        List<Chapter> chapters = chapterMapper.selectByExample(chapterExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapters);
        chapterPageDto.setTotal(pageInfo.getTotal());
        List<ChapterDto> chapterDtoList = CopyUtils.copyList(chapters, ChapterDto.class);
        chapterPageDto.setList(chapterDtoList);
    }

    /**
     * 保存数据
     *
     * @Author: YJQ
     * @Date: 2020-10-27 09:12
     */
    public void save(ChapterDto chapterDto) {
        Chapter chapter = CopyUtils.copy(chapterDto, Chapter.class);
        if (StringUtils.isEmpty(chapter.getId())) {
            this.insert(chapter);
        } else {
            this.update(chapter);
        }
    }

    /**
     * 更新大章数据
     */
    public void update(Chapter chapter) {
        chapterMapper.updateByPrimaryKey(chapter);
    }

    /**
     * 大章数据插入
     */
    public void insert(Chapter chapter) {
        chapter.setId(UuidUtil.getShortUuid());
        chapterMapper.insert(chapter);
    }

    /**
     * 删除大章数据
     */
    public void delete(String id) {
        chapterMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询某一课程下的所有章
     */
    public List<ChapterDto> listByCourse(String courseId) {
        ChapterExample example = new ChapterExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<Chapter> chapterList = chapterMapper.selectByExample(example);
        List<ChapterDto> chapterDtoList = CopyUtils.copyList(chapterList, ChapterDto.class);
        return chapterDtoList;
    }

}
