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
    TOKEN_LOGIN_FILDED(10001,"token无效"),

    PARAM_ERROR(10002,"参数错误"),
    EMAIL_ERROR(10003,"邮箱不合法"),
    PASSWORD_INCONSISTENT(10004,"两次密码不一致"),
    CODE_ERROR(10005,"验证码错误"),
    USER_NULL(10006,"该邮箱未绑定任何账号"),
    EMAIL_OR_PASSWORD_ERROR(10007,"邮箱或密码错误"),

    PRIVATE_CAPSULE_UNEXIST(20001,"key错误,该胶囊不存在"),
    OTHER_USER_CAPSULE(20002,"你没有权限操作别人的胶囊"),
    TIME_NOT_ARRIVED(20003,"时间未到，无法打开该胶囊"),
    CAPSULE_OPENED(20004,"该胶囊已经打开过了"),

    POOL_UN_EXIST(30001,"该胶囊池不存在"),
    POOL_STATE_ALREADY(30002,"该胶囊池已经处于该状态"),

    WORDS_MAX(40001,"字数超过200上限"),


    UNLOGIN(10008,"未登录");
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
