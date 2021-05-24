package com.course.server.service;

import com.course.server.domain.MemberCourse;
import com.course.server.domain.MemberCourseExample;
import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.MemberCourseMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
        import java.util.Date;

@Service
public class MemberCourseService {

    @Resource
    private MemberCourseMapper memberCourseMapper;

        /**
        * 列表查询
        */
        public void list(PageDto pageDto) {
            PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
            MemberCourseExample memberCourseExample = new MemberCourseExample();
            List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);
            PageInfo<MemberCourse> pageInfo = new PageInfo<>(memberCourseList);
            pageDto.setTotal(pageInfo.getTotal());
            List
            <MemberCourseDto> memberCourseDtoList = CopyUtils.copyList(memberCourseList, MemberCourseDto.class);
                pageDto.setList(memberCourseDtoList);
        }

        /**
        * 保存，id有值时更新，无值时新增
        */
        public void save(MemberCourseDto memberCourseDto) {
            MemberCourse memberCourse = CopyUtils.copy(memberCourseDto, MemberCourse.class);
            if (StringUtils.isEmpty(memberCourseDto.getId())) {
            this.insert(memberCourse);
            } else {
            this.update(memberCourse);
            }
        }

        /**
        * 新增
        */
        private void insert(MemberCourse memberCourse) {
                            Date now = new Date();
            memberCourse.setId(UuidUtil.getShortUuid());
            memberCourseMapper.insert(memberCourse);
        }

        /**
        * 更新
        */
        private void update(MemberCourse memberCourse) {
            memberCourseMapper.updateByPrimaryKey(memberCourse);
        }

        /**
        * 删除
        */
        public void delete(String id) {
            memberCourseMapper.deleteByPrimaryKey(id);
        }
}