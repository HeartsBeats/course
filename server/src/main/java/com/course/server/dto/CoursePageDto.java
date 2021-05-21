package com.course.server.dto;

/**
 * @ProjectName: course
 * @Package: com.course.server.dto
 * @ClassName: CoursePageDto
 * @Author: 游佳琪
 * @Description:
 * @Date: 2021-5-21 16:41
 * @Version: 1.0
 */
public class CoursePageDto extends PageDto {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoursePageDto{");
        sb.append("status='").append(status).append('\'');
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}

