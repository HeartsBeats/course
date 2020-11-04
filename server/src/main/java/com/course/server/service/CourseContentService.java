package com.course.server.service;

import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseContentExample;
import com.course.server.dto.CourseContentDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseContentService {

    @Resource
    private CourseContentMapper courseContentMapper;

        /**
        * 列表查询
        */
        public void list(PageDto pageDto) {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            CourseContentExample courseContentExample = new CourseContentExample();
            List<CourseContent> courseContentList = courseContentMapper.selectByExample(courseContentExample);
            PageInfo<CourseContent> pageInfo = new PageInfo<>(courseContentList);
            pageDto.setTotal(pageInfo.getTotal());
            List
            <CourseContentDto> courseContentDtoList = CopyUtils.copyList(courseContentList, CourseContentDto.class);
                pageDto.setList(courseContentDtoList);
        }

        /**
        * 保存，id有值时更新，无值时新增
        */
        public void save(CourseContentDto courseContentDto) {
            CourseContent courseContent = CopyUtils.copy(courseContentDto, CourseContent.class);
            if (StringUtils.isEmpty(courseContentDto.getId())) {
            this.insert(courseContent);
            } else {
            this.update(courseContent);
            }
        }

        /**
        * 新增
        */
        private void insert(CourseContent courseContent) {
            courseContent.setId(UuidUtil.getShortUuid());
            courseContentMapper.insert(courseContent);
        }

        /**
        * 更新
        */
        private void update(CourseContent courseContent) {
            courseContentMapper.updateByPrimaryKey(courseContent);
        }

        /**
        * 删除
        */
        public void delete(String id) {
            courseContentMapper.deleteByPrimaryKey(id);
        }
}