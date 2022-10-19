package com.twt.time_capsule.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
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
     * 获取某一个胶囊池的胶囊
     * @param poolId
     * @return
     */
//    TODO: 2022/10/16 按时间，按热度 升序降序，分页数  鉴权 参加过活动 活动结束 超管
    @GetMapping("list")
    public APIResponse getList(String poolId,int pageNum,int pageSize,String order,String type){
        return publicCapsuleService.getList(poolId,pageNum,pageSize,order,type);
    }
}
