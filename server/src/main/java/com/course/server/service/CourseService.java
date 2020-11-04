package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.CourseContent;
import com.course.server.domain.CourseExample;
import com.course.server.dto.CourseContentDto;
import com.course.server.dto.CourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseContentMapper;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.my.MyCourseMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    private static final Logger LOG = LoggerFactory.getLogger(CourseService.class);

    @Resource
    private CourseMapper courseMapper;

    @Resource
    private MyCourseMapper  myCourseMapper;

    @Resource
    private CourseCategoryService courseCategoryService;

    @Resource
    private CourseContentMapper courseContentMapper;
        /**
        * 列表查询
        */
        public void list(PageDto pageDto) {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            CourseExample courseExample = new CourseExample();
                    courseExample.setOrderByClause("sort asc");
            List<Course> courseList = courseMapper.selectByExample(courseExample);
            PageInfo<Course> pageInfo = new PageInfo<>(courseList);
            pageDto.setTotal(pageInfo.getTotal());
            List
            <CourseDto> courseDtoList = CopyUtils.copyList(courseList, CourseDto.class);
                pageDto.setList(courseDtoList);
        }

        /**
        * 保存，id有值时更新，无值时新增
        */
        @Transactional
        public void save(CourseDto courseDto) {
            Course course = CopyUtils.copy(courseDto, Course.class);
            if (StringUtils.isEmpty(courseDto.getId())) {
            this.insert(course);
            } else {
            this.update(course);
            }
            courseCategoryService.saveBatch(courseDto.getId(),courseDto.getCategorys());
        }

        /**
        * 新增
        */
        private void insert(Course course) {
            Date now = new Date();
                    course.setCreatedAt(now);
                    course.setUpdatedAt(now);
            course.setId(UuidUtil.getShortUuid());
            courseMapper.insert(course);
        }

        /**
        * 更新
        */
        private void update(Course course) {
                    course.setUpdatedAt(new Date());
            courseMapper.updateByPrimaryKey(course);
        }

        /**
        * 删除
        */
        public void delete(String id) {
            courseMapper.deleteByPrimaryKey(id);
        }

    /*
     * 更新课程时长
     * @param courseId
     * @return
     */
    public void updateTime(String courseId) {
        LOG.info("更新课程时长：{}", courseId);
        myCourseMapper.updateTime(courseId);
    }

    /*
     *  查找课程内容
     * @param courseId
     * @return
     */
    public CourseContentDto findContent(String courseId) {
        CourseContent courseContent = courseContentMapper.selectByPrimaryKey(courseId);
        if (courseContent == null) {
            return null;
        }
        return CopyUtils.copy(courseContent, CourseContentDto.class);
    }

    /*
     *  保存课程内容
     * @param courseId
     * @return
     */
    public int saveContent(CourseContentDto contentDto) {
        CourseContent courseContent = CopyUtils.copy(contentDto, CourseContent.class);
        int i = courseContentMapper.updateByPrimaryKeyWithBLOBs(courseContent);
        if (i == 0) {
            //说明不是更新而是新增
            courseContentMapper.insert(courseContent);
        }
        return i;
    }
}