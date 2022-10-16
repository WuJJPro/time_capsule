package com.twt.time_capsule.service;

import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.entity.PublicCapsule;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.stereotype.Service;

@Service
public interface PublicCapsuleService {
    APIResponse addPublicCapsule(PublicCapsule capsule);
    APIResponse alterPublicCapsule(PublicCapsule capsule);
    APIResponse deletePublicCapsule(String key);
    APIResponse lovePublicCapsule(String key,int action);
    APIResponse reportPublicCapsule(String key,String reason);
    APIResponse getPublicCapsule(String key);
    APIResponse getList(String poolId);
}
