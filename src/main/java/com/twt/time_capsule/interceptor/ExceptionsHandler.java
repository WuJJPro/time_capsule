package com.twt.time_capsule.interceptor;


import cn.dev33.satoken.exception.NotLoginException;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

/**异常处理类
 * @author Administrator
 */
@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = NotLoginException.class)
    APIResponse handlerException(Exception e, HttpServletRequest request) {
        return APIResponse.error(ErrorCode.UNLOGIN,e.getMessage());
    }
}