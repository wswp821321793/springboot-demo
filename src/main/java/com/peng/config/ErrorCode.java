package com.peng.config;

public enum ErrorCode {
    USER_NAME_NOT_EXIST(300001,"账号不存在"),
    USER_PASSWORD_ERROR(300002,"密码错误"),
    USER_NOT_LOGIN(300003,"用户未登录，请登录后再试"),
    USER_UNKNOWN_ERROR(300999,"未知错误");
    private int code;
    private String msg;
    ErrorCode(int code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
