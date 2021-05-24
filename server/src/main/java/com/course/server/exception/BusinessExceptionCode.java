package com.course.server.exception;

public enum BusinessExceptionCode {
    /**
     *
     *  业务报错枚举变量
     */
    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    LOGIN_MEMBER_ERROR("手机号不存在或密码错误"),
    MOBILE_CODE_TOO_FREQUENT("短信请求过于频繁"),;

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
