package com.course.server.dto;

/*
 * @ProjectName: course
 * @Package: com.course.server.dto
 * @ClassName: ChapterPageDto
 * @Author: 游佳琪
 * @Description:
 * @Date: 2020-11-2 20:57
 * @Version: 1.0
 */
public class ChapterPageDto extends PageDto{
    private  String  courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChapterPageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
