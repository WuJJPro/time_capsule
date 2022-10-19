package com.twt.time_capsule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.entity.CapsulePool;
import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.entity.PublicCapsule;
import com.twt.time_capsule.mapper.CapsulePoolMapper;
import com.twt.time_capsule.mapper.PublicCapsuleMapper;
import com.twt.time_capsule.service.CapsulePoolService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapsulePoolServiceImpl implements CapsulePoolService {
    private static final int POOL_STATE_OPEN = 1;
    private static final int POOL_STATE_CLOSE = 0;
    @Autowired
    CapsulePoolMapper capsulePoolMapper;
    @Autowired
    PublicCapsuleMapper capsuleMapper;
    @Override
    public APIResponse addPool(JSONObject jsonObject) {
        CapsulePool capsulePool = jsonObject.toJavaObject(jsonObject,CapsulePool.class);
        capsulePool.setState(0);
        capsulePoolMapper.insert(capsulePool);
        return APIResponse.success();
    }

    @Override
    public APIResponse openPool(String id) {
        CapsulePool capsulePool = capsulePoolMapper.selectById(id);
        if(capsulePool==null){
            return APIResponse.error(ErrorCode.POOL_UN_EXIST);
        }
        if(capsulePool.getState()==POOL_STATE_OPEN){
            return APIResponse.error(ErrorCode.POOL_STATE_ALREADY);
        }
        capsulePool.setState(POOL_STATE_OPEN);
        capsulePoolMapper.updateById(capsulePool);
        return APIResponse.success();
    }


    @Override
    public APIResponse closePool(String id) {
        CapsulePool capsulePool = capsulePoolMapper.selectById(id);
        if(capsulePool==null){
            return APIResponse.error(ErrorCode.POOL_UN_EXIST);
        }
        if(capsulePool.getState()==POOL_STATE_CLOSE){
            return APIResponse.error(ErrorCode.POOL_STATE_ALREADY);
        }
        capsulePool.setState(POOL_STATE_CLOSE);
        capsulePoolMapper.updateById(capsulePool);
        return APIResponse.success();
    }

    @Override
    public APIResponse deletePool(String id) {
        CapsulePool capsulePool = capsulePoolMapper.selectById(id);
        if(capsulePool==null){
            return APIResponse.error(ErrorCode.POOL_UN_EXIST);
        }
        List<PublicCapsule> capsules = capsuleMapper.getCapsuleByPool(id);
        if (capsules != null) {
            return APIResponse.error(ErrorCode.POOL_UNABLE_DELETE);
        }
        capsulePoolMapper.deleteById(id);
        return APIResponse.success();
    }

    @Override
    public APIResponse getList(String type) {
        List<CapsulePool> capsulePools;
        if(type.equals("all")){
            capsulePools = capsulePoolMapper.selectList(null);
        }
        else if(type.equals("open")){
            capsulePools = capsulePoolMapper.getPools(POOL_STATE_OPEN);
        }
        else if(type.equals("close")){
            capsulePools = capsulePoolMapper.getPools(POOL_STATE_CLOSE);
        }
        else{
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }

        return APIResponse.success(capsulePools);
    }

    @Override
    public APIResponse getOne(String id) {
        CapsulePool capsulePool = capsulePoolMapper.selectById(id);
        if(capsulePool==null){
            return APIResponse.error(ErrorCode.POOL_UN_EXIST);
        }
        return APIResponse.success(capsulePool);
    }
}
