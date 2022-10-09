package com.twt.time_capsule.service;

import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.utils.APIResponse;


public interface PrivateCapsuleService {
    APIResponse addPrivateCapsule(PrivateCapsule capsule);
    APIResponse getPrivateCapsule(String type);
}
