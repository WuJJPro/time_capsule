package com.twt.time_capsule.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.service.PrivateCapsuleService;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SaCheckLogin
@RequestMapping("api/capsule/private")
public class PrivateCapsuleController {
    @Autowired
    PrivateCapsuleService privateCapsuleService;

    /**
     * 新建一个私有胶囊
     * @param jsonObject
     * @return
     */
    @PostMapping("/add")
    public APIResponse addPrivateCapsule(@RequestBody JSONObject jsonObject){
        PrivateCapsule capsule = jsonObject.toJavaObject(PrivateCapsule.class);
        return privateCapsuleService.addPrivateCapsule(capsule);
    }

    /**
     * 获取个人所有胶囊列表
     * @param type “open” "close" "all"
     * @return
     */
    @GetMapping("/list")
    public APIResponse getPrivateCapsule(String type){
        return privateCapsuleService.getPrivateCapsule(type);
    }
}
