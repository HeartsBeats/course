package com.course.server.exception;

public enum BusinessExceptionCode {
    /**
     *
     *  业务报错枚举变量
     */
    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_ERROR("用户名或密码不存在"),;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
