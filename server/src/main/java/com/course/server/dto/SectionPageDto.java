package com.course.server.dto;

/*
 * @ProjectName: course
 * @Package: com.course.server.dto
 * @ClassName: SectionPageDto
 * @Author: 游佳琪
 * @Description:
 * @Date: 2020-11-2 22:11
 * @Version: 1.0
 */
public class SectionPageDto extends PageDto {

    private String courseId;

    private String chapterId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SectionPageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", chapterId='").append(chapterId).append('\'');
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
