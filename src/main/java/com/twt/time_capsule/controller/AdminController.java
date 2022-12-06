package com.twt.time_capsule.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@SaCheckLogin
@RestController
@RequestMapping("api/admin")
public class AdminController {
    @GetMapping("report")
    public APIResponse getReport(){

    }
}
