package com.twt.time_capsule.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.mapper.UserMapper;
import com.twt.time_capsule.service.AuthService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import com.twt.time_capsule.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    HttpUtil httpUtil;
    @Autowired
    UserMapper userMapper;
    @Override
    public APIResponse login(String token) {
        String uuid = getUid(token);
        if(uuid!=null){
            userMapper.getUserByUid(uuid);
        }
        else{
            return APIResponse.error(ErrorCode.TOKEN_LOGIN_FILDED);
        }
        return
    }


    /**
     * 调用微北洋方法返回学号或者空值
     * @param token
     * @return
     */
    private String getUid(String token){
        JSONObject jsonObject = httpUtil.getUser(token);
        if((int)jsonObject.get("error_code")==0){
//            成功获取个人信息
            JSONObject result = (JSONObject) jsonObject.get("result");
            String uuid = (String)result.get("uid");
            return uuid;
        }
        return null;
    }
}
