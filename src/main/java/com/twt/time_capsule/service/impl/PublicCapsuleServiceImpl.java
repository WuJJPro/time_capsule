package com.twt.time_capsule.service.impl;

import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.entity.PublicCapsule;
import com.twt.time_capsule.service.PublicCapsuleService;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.stereotype.Service;

@Service
public class PublicCapsuleServiceImpl implements PublicCapsuleService {
    @Override
    public APIResponse addPublicCapsule(PublicCapsule capsule) {
        return null;
    }

    @Override
    public APIResponse alterPublicCapsule(PublicCapsule capsule) {
        return null;
    }

    @Override
    public APIResponse deletePublicCapsule(String key) {
        return null;
    }

    @Override
    public APIResponse lovePublicCapsule(String key,int action) {
        return null;
    }

    @Override
    public APIResponse reportPublicCapsule(String key,String reason) {
        return null;
    }

    @Override
    public APIResponse getPublicCapsule(String key) {
        return null;
    }
}
