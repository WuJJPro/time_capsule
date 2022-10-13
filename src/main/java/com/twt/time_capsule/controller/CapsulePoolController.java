package com.twt.time_capsule.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.service.CapsulePoolService;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SaCheckLogin
    @RequestMapping("api/pool")
public class CapsulePoolController {
    @Autowired
    CapsulePoolService capsulePoolService;

    /**
     * 新增一个胶囊池
     * @param jsonObject
     * @return
     */
    @SaCheckRole("admin")
    @PostMapping("add")
    public APIResponse addPool(@RequestBody JSONObject jsonObject){
        return capsulePoolService.addPool(jsonObject);
    }

    /**
     * 开启一个胶囊池
     * @param id
     * @return
     */
    @SaCheckRole("admin")
    @GetMapping("open")
    public APIResponse openPool(String id){
        return capsulePoolService.openPool(id);
    }

    /**
     * 关闭一个胶囊池
     * @param id
     * @return
     */
    @SaCheckRole("admin")
    @GetMapping("close")
    public APIResponse closePool(String id){
        return capsulePoolService.closePool(id);
    }

    /**
     * 删除一个胶囊池
     * @param id
     * @return
     */
    @SaCheckRole("admin")
    @GetMapping("delete")
    public APIResponse deletePool(String id){
        return capsulePoolService.deletePool(id);
    }

    /**
     * 获取所有的
     * @return
     */
    @GetMapping("list")
    public APIResponse getList(String type){
        return capsulePoolService.getList(type);
    }

    @GetMapping("one")
    public APIResponse getOne(String id){
        return capsulePoolService.getOne(id);
    }
}
