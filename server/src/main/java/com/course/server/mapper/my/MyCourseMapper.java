package com.course.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/*
 * @ProjectName: course
 * @Package: com.course.server.mapper
 * @ClassName: MyCourseMapper
 * @Author: 游佳琪
 * @Description:
 * @Date: 2020-11-3 19:58
 * @Version: 1.0
 */
public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
}
