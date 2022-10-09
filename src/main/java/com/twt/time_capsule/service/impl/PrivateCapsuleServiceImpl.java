package com.twt.time_capsule.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.mapper.PrivateCapsuleMapper;
import com.twt.time_capsule.service.PrivateCapsuleService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrivateCapsuleServiceImpl implements PrivateCapsuleService {
    @Autowired
    PrivateCapsuleMapper capsuleMapper;
    @Override
    public APIResponse addPrivateCapsule(PrivateCapsule capsule) {
        String uid = StpUtil.getLoginIdAsString();
        capsule.setUid(uid);
        capsule.setSuccess(0);
        capsuleMapper.insert(capsule);
        return APIResponse.success();
    }

    @Override
    public APIResponse getPrivateCapsule(String type) {
        String uid = StpUtil.getLoginIdAsString();
        if(type.equals("close")) {
            List<PrivateCapsule> capsules = capsuleMapper.getClosedCapsules(uid);
            return APIResponse.success(capsules);
        }
        else if(type.equals("open")){
            List<PrivateCapsule> capsules = capsuleMapper.getOpenedCapsules(uid);
            return APIResponse.success(capsules);
        }
        else if (type.equals("all")){
            List<PrivateCapsule> capsules = capsuleMapper.getAllCapsules(uid);
            return APIResponse.success(capsules);
        }
        return APIResponse.error(ErrorCode.PARAM_ERROR);
    }
}
