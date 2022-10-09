package com.twt.time_capsule.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.service.AuthService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * 通过微北洋的jwt登录
     * @param token
     * @return
     */
    @GetMapping("login/jwt")
    public APIResponse loginByJwt(String token){
        return authService.loginByJwt(token) ;
    }

    /**
     * 绑定邮箱，向邮箱中发送验证码
     * @param email
     * @return
     */
    @SaCheckLogin
    @GetMapping("/email/code")
    public APIResponse bindCode(String email){
        return authService.sendCode(email);
    }

    /**
     * 验证验证码，绑定邮箱，设置密码
     * @param email
     * @param password
     * @param passwordConfirm
     * @param code
     * @return
     */
    @SaCheckLogin
    @PostMapping("register")
    public APIResponse register(String email,String password,String passwordConfirm,String code){
        return authService.register(email,password,passwordConfirm,code);
    }

    /**
     * 通过账号密码登录
     * @param email
     * @param password
     * @return
     */
    @PostMapping("/login/email")
    public APIResponse LoginByEmail(String email,String password){
        return authService.loginByEmail(email,password);
    }

    /**
     * 修改密码
     * @param email
     * @param password
     * @param passwordConfirm
     * @param code
     * @return
     */
    @PostMapping("findpassword")
    public APIResponse findPassword(String email,String password,String passwordConfirm,String code){
        return authService.findPassword(email,password,passwordConfirm,code);
    }
}
