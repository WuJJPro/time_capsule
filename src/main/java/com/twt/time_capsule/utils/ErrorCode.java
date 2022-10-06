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
    ERROR_LOGIN_FAILED(40001,"登录失败"),
    ERROR_NO_ACCOUNT(40002,"账号不存在"),
    ERROR_ACCOUNT_EXIST(40003,"账号已存在，注册失败"),
    ERROR_WRONG_PASSWORD(40004,"密码错误，请重试"),
    EMPTY_FILE(40007,"the file cannot be empty"),
    WRONG_FORMAT(40008,"Maybe wrong picture format;" +
            "Maybe filename is blank; "+
            "Maybe the file has no suffix"),
    File_Exist(40009,"The file is existed"),
    CreateDir_FAILED(40010,"Create the directory failed"),
    NO_SUFFIX(40011,"The file has no suffix"),
    LOGIN_FAILED(40012,"wrong name or password"),
    PERMISSION_DENIED(40013,"have no permission to access "),
    RESET_PASSWORD_FAILED(40014,"fail to reset the password"),
    ADD_FAILED(40015,"fail to add"),
    GET_FALIED(40016,"fail to get"),
    UPDATE_FAILED(40017,"fail to update"),
    DELETE_FAILED(40018,"fail to delete"),
    NO_TOP_ARTICLE(40019,"板块置顶文章不存在"),
    DOWNLOAD_FAILED(40020,"fail to download file");

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
