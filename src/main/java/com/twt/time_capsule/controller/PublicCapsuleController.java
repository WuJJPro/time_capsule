package com.twt.time_capsule.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.entity.PublicCapsule;
import com.twt.time_capsule.service.PublicCapsuleService;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SaCheckLogin
@RequestMapping("api/capsule/public")
public class PublicCapsuleController {
    @Autowired
    PublicCapsuleService publicCapsuleService;

    /**
     * 新建一个公共胶囊
     * @param jsonObject
     * @return
     */
    @PostMapping("add")
    public APIResponse addPublicCapsule(@RequestBody JSONObject jsonObject){
        PublicCapsule publicCapsule = jsonObject.toJavaObject(PublicCapsule.class);
        return publicCapsuleService.addPublicCapsule(publicCapsule);
    }

    /**
     * 修改公共胶囊
     * @param jsonObject
     * @return
     */
    @PostMapping("alter")
    public APIResponse alterPublicCapsule(@RequestBody JSONObject jsonObject){
        PublicCapsule publicCapsule = jsonObject.toJavaObject(PublicCapsule.class);
        return publicCapsuleService.alterPublicCapsule(publicCapsule);
    }

    /**
     * 获取一个公共胶囊
     * @param key
     * @return
     */
    @GetMapping("get")
    public APIResponse getPublicCapsule(String key){
        return publicCapsuleService.getPublicCapsule(key);
    }

    /**
     * 删除公共胶囊
     * @param key
     * @return
     */
    @GetMapping("delete")
    public APIResponse deletePublicCapsule(String key){
        return publicCapsuleService.deletePublicCapsule(key);
    }

    /**
     * 点赞，取消点赞
     * @param key
     * @param action  1为点赞，0为取消点赞
     * @return
     */
    @GetMapping("like")
    public APIResponse lovePublicCapsule(String key,int action){
        return publicCapsuleService.lovePublicCapsule(key,action);
    }

    /**
     * 举报
     * @param key
     * @param reason
     * @return
     */
    @PostMapping("report")
    public APIResponse reportPublicCapsule(String key,String reason){
        return publicCapsuleService.reportPublicCapsule(key,reason);
    }

    /**
     * 获取胶囊池下的胶囊
     * @param poolId
     * @param pageNum
     * @param pageSize
     * @param order 1正序 0倒序
     * @param type hot热度 time时间
     * @return
     */
    @SaIgnore
    @GetMapping("list")
    public APIResponse getList(String poolId,int pageNum,int pageSize,String order,String type){
        return publicCapsuleService.getList(poolId,pageNum,pageSize,order,type);
    }
}
