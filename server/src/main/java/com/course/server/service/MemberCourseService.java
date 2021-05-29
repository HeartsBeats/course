package com.course.server.service;

import com.course.server.domain.Course;
import com.course.server.domain.MemberCourse;
import com.course.server.domain.MemberCourseExample;
import com.course.server.dto.MemberCourseDto;
import com.course.server.dto.PageDto;
import com.course.server.mapper.CourseMapper;
import com.course.server.mapper.MemberCourseMapper;
import com.course.server.util.CopyUtils;
import com.course.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class MemberCourseService {

    @Resource
    private MemberCourseMapper memberCourseMapper;

    @Resource
    private CourseMapper courseMapper;

    /**
     * 列表查询
     */
    public void list(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPage(), pageDto.getSize());
        MemberCourseExample memberCourseExample = new MemberCourseExample();
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(memberCourseExample);
        PageInfo<MemberCourse> pageInfo = new PageInfo<>(memberCourseList);
        pageDto.setTotal(pageInfo.getTotal());
        List<MemberCourseDto> memberCourseDtoList = CopyUtils.copyList(memberCourseList, MemberCourseDto.class);
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
        memberCourse.setAt(now);
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

    /**
     * 报名，先判断是否已报名
     *
     * @param memberCourseDto
     */
    public MemberCourseDto enroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourseDb = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        if (memberCourseDb == null) {
            MemberCourse memberCourse = CopyUtils.copy(memberCourseDto, MemberCourse.class);
            this.insert(memberCourse);
            Course course = courseMapper.selectByPrimaryKey(memberCourseDto.getCourseId());
            course.setEnroll(course.getEnroll()+1);
            course.setUpdatedAt(new Date());
            courseMapper.updateByPrimaryKey(course);
            // 将数据库信息全部返回，包括id, at
            return CopyUtils.copy(memberCourse, MemberCourseDto.class);
        } else {
            // 如果已经报名，则直接返回已报名的信息
            return CopyUtils.copy(memberCourseDb, MemberCourseDto.class);

        }
    }

    /**
     * 根据memberId和courseId查询记录
     */
    public MemberCourse select(String memberId, String courseId) {
        MemberCourseExample example = new MemberCourseExample();
        example.createCriteria()
                .andCourseIdEqualTo(courseId)
                .andMemberIdEqualTo(memberId);
        List<MemberCourse> memberCourseList = memberCourseMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(memberCourseList)) {
            return null;
        } else {
            return memberCourseList.get(0);
        }
    }

    /**
     * 获取报名信息
     */
    public MemberCourseDto getEnroll(MemberCourseDto memberCourseDto) {
        MemberCourse memberCourse = this.select(memberCourseDto.getMemberId(), memberCourseDto.getCourseId());
        return CopyUtils.copy(memberCourse, MemberCourseDto.class);
    }
}
