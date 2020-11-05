package com.course.server.dto;

/**
 * @ProjectName: course
 * @Package: com.course.server.dto
 * @ClassName: SortDto
 * @Author: 游佳琪
 * @Description:
 * @Date: 2020-11-5 18:54
 * @Version: 1.0
 */

public class SortDto {
    /**
     * ID
     */
    private String id;

    /**
     * 当前排序
     */
    private int oldSort;

    /**
     * 新排序
     */
    private int newSort;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNewSort() {
        return newSort;
    }

    public void setNewSort(int newSort) {
        this.newSort = newSort;
    }

    public int getOldSort() {
        return oldSort;
    }

    public void setOldSort(int oldSort) {
        this.oldSort = oldSort;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SortDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", oldSort=").append(oldSort);
        sb.append(", newSort=").append(newSort);
        sb.append('}');
        return sb.toString();
    }
}