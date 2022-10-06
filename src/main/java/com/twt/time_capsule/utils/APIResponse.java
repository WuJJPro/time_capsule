package com.twt.time_capsule.utils;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @ClassName: APIResponse
 * @Description:
 * @Author: 过河卒
 * @Date: 2022/1/14 19:59
 * @Version: 1.0
 */

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {
    private int error_code;
    private String message;
    private Object result;


    public APIResponse(String message){
        this.message = message;
    }


    public APIResponse(int code, String message){
        this.error_code = code;
        this.message = message;
    }

    public APIResponse(int code, Object result){
        this.error_code = code;
        this.result = result;
    }

    public APIResponse(String message, Object result){
        this.message = message;
        this.result = result;
    }
    public static APIResponse error(ErrorCode err){
        return APIResponse.error(err,null);
    }

    public static APIResponse error(ErrorCode errorCode,Object obj){
        return new APIResponse(errorCode.getCode(),errorCode.getMessage(),obj);
    }


    public static APIResponse success(){
        return APIResponse.success(0);
    }

    public static APIResponse success(Object result) {
        return new APIResponse(0, ErrorCode.OK.getMessage(), result);
    }
}
