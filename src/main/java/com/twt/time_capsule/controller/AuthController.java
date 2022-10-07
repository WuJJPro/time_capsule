package com.twt.time_capsule.controller;

import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.service.AuthService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @GetMapping("login")
    public APIResponse login(String token){
        return authService.login(token) ;
    }
}
