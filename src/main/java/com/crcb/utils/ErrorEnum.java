package com.crcb.utils;

/**
 * @Classname ErrorEnum
 * @Description 返回结果值枚举类
 * @Date 2020/3/6 16:23
 */
public enum ErrorEnum {
    SUCCESS(200, "ok"),
    account_password_err(10012,"用户名或密码错误!"),
    account_password_null(10013,"用户名或密码为空!"),
    SERVER_ERROR(500, "服务器发生错误"),
    ;
    private String message;

    private int code;

    ErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
