package com.twt.time_capsule.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.mapper.PrivateCapsuleMapper;
import com.twt.time_capsule.service.PrivateCapsuleService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.QuadCurve2D;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrivateCapsuleServiceImpl implements PrivateCapsuleService {
    private final int STATE_TIME_NOT_ARRIVED = 0;
    private final int STATE_WAIT_OPEN = 1;
    private final int STATE_OPENED = 2;
    private final int STATE_OPENED_FAILED = 3;
    @Autowired
    PrivateCapsuleMapper capsuleMapper;
    @Override
    public APIResponse addPrivateCapsule(PrivateCapsule capsule) {
        String uid = StpUtil.getLoginIdAsString();
        capsule.setUid(uid);
        capsule.setSuccess(STATE_TIME_NOT_ARRIVED);
        capsuleMapper.insert(capsule);
        return APIResponse.success();
    }

    @Override
    public APIResponse getPrivateCapsule(String type) {
        String uid = StpUtil.getLoginIdAsString();
        List<PrivateCapsule> capsules;
        if(type.equals("close")) {
            capsules = capsuleMapper.getClosedCapsules(uid);

        }
        else if(type.equals("open")){
            capsules = capsuleMapper.getOpenedCapsules(uid);

        }
        else if (type.equals("all")){
            capsules = capsuleMapper.getAllCapsules(uid);

        }
        else if(type.equals("ready")){
            capsules = capsuleMapper.getReadyCapsules(uid);
        }
        else{
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }
        JSONArray jsonArray = new JSONArray();
        for(PrivateCapsule capsule:capsules){
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(capsule);
            jsonObject.remove("content");
            jsonObject.remove("record");
            jsonObject.remove("picture");
            jsonObject.remove("uid");
            jsonArray.add(jsonObject);
        }

        return APIResponse.success(jsonArray);
    }

    @Override
    public APIResponse openPrivateCapsule(String key) {
//        获取这个胶囊
        PrivateCapsule capsule = capsuleMapper.selectById(key);
//        判断存在
        if(capsule==null){
            return APIResponse.error(ErrorCode.PRIVATE_CAPSULE_UNEXIST);
        }
//        判断是否是本人的胶囊
        String uid = StpUtil.getLoginIdAsString();
        if(!capsule.getUid().equals(uid)){
            return APIResponse.error(ErrorCode.OTHER_USER_CAPSULE);
        }
        else if(capsule.getSuccess()==STATE_TIME_NOT_ARRIVED){
            //        判断是否可以打开
            return APIResponse.error(ErrorCode.TIME_NOT_ARRIVED);
        }
        else if(capsule.getSuccess()==STATE_OPENED){
            //        判断是否已经打开
            return APIResponse.error(ErrorCode.CAPSULE_OPENED);
        }
        capsule.setSuccess(STATE_OPENED);
        capsuleMapper.updateById(capsule);
        return getPrivateCapsuleContent(key);
    }

    @Override
    public APIResponse getPrivateCapsuleContent(String key) {
        PrivateCapsule capsule = capsuleMapper.selectById(key);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id",key);
        capsuleMapper.selectList(queryWrapper);
        if(capsule.getSuccess()==STATE_OPENED){
            JSONObject jsonObject = (JSONObject) JSONObject.toJSON(capsule);
            jsonObject.remove("uid");
            return APIResponse.success(jsonObject);
        }
        return APIResponse.error(ErrorCode.TIME_NOT_ARRIVED);
    }
}
