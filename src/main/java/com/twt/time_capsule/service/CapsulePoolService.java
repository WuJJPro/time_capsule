package com.twt.time_capsule.service;

import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.utils.APIResponse;

public interface CapsulePoolService {
    APIResponse addPool(JSONObject jsonObject);
    APIResponse openPool(String id);
    APIResponse closePool(String id);
    APIResponse deletePool(String id);
    APIResponse getList(String type);
    APIResponse getOne(String id);
}
