package com.twt.time_capsule.utils;

/**
 * @ClassName: ErrorCode
 * @Description:
 * @Author: 过河卒
 * @Date: 2022/1/14 20:06
 * @Version: 1.0
 */

public enum ErrorCode {
    /**
     *temp
     */
    OK(0,"succeed"),
    ERROR(1,"Failed"),
    TOKEN_LOGIN_FILDED(10001,"token无效");



    private final int code;
    private final String message;

    ErrorCode(int code, String message){
        this.code = code;
        this.message = message;
    }
    public int getCode(){return code;}
    public String getMessage() {
        return message;
    }
}
