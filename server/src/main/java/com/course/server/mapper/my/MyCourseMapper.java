package com.course.server.mapper.my;

import com.course.server.dto.SortDto;
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

    int updateSort(SortDto sortDto);

    int moveSortsBackward(SortDto sortDto);

    int moveSortsForward(SortDto sortDto);
}
