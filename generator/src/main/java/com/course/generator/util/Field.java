package com.course.generator.util;
/**
 *
 * @Author: YJQ
 * @Date: 2020-11-11 11:53
 */

public class Field {
    /**
        字段名：course_id
     */
    private String name;
    /**
     * 字段名小驼峰：courseId
     */
    private String nameHump;
    /**
     *字段名大驼峰：CourseId
     */
    private String nameBigHump;
    /**
     *  中文名：课程
     */
    private String nameCn;
    /**
     * 字段类型：char(8)
     */
    private String type;
    /**
     * java类型：String
     */
    private String javaType;
    /**
     * 注释：课程|ID
     */
    private String comment;
    /**
     *  是否可为空
     */
    private Boolean nullAble;
    /**
     * 字符串长度
     */
    private Integer length;
    /**
     *  是否是枚举
     */
    private Boolean enums;
    /**
     *  枚举常量 COURSE_LEVEL
     */
    private String enumsConst;

    public Boolean getEnums() {
        return enums;
    }

    public void setEnums(Boolean enums) {
        this.enums = enums;
    }

    public String getEnumsConst() {
        return enumsConst;
    }

    public void setEnumsConst(String enumsConst) {
        this.enumsConst = enumsConst;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameHump() {
        return nameHump;
    }

    public void setNameHump(String nameHump) {
        this.nameHump = nameHump;
    }

    public String getNameBigHump() {
        return nameBigHump;
    }

    public void setNameBigHump(String nameBigHump) {
        this.nameBigHump = nameBigHump;
    }

    public String getNameCn() {
        return nameCn;
    }

    public void setNameCn(String nameCn) {
        this.nameCn = nameCn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public Boolean getNullAble() {
        return nullAble;
    }

    public void setNullAble(Boolean nullAble) {
        this.nullAble = nullAble;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Field{");
        sb.append("name='").append(name).append('\'');
        sb.append(", nameHump='").append(nameHump).append('\'');
        sb.append(", nameBigHump='").append(nameBigHump).append('\'');
        sb.append(", nameCn='").append(nameCn).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", javaType='").append(javaType).append('\'');
        sb.append(", comment='").append(comment).append('\'');
        sb.append(", nullAble=").append(nullAble);
        sb.append(", length=").append(length);
        sb.append(", enums=").append(enums);
        sb.append(", enumsConst='").append(enumsConst).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
