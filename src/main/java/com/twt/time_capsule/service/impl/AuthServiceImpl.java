package com.twt.time_capsule.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.entity.User;
import com.twt.time_capsule.mapper.UserMapper;
import com.twt.time_capsule.service.AuthService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import com.twt.time_capsule.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    HttpUtil httpUtil;
    @Autowired
    UserMapper userMapper;
    @Override
    public APIResponse login(String token) {
        Map res = getUid(token);
        if(res!=null){
            StpUtil.login(res);
            String saToken = StpUtil.getTokenValue();
            Map<String,Object> map = new HashMap<>();
            List roleList = StpUtil.getRoleList();
            map.put("token",saToken);
            map.put("role",roleList);
            return APIResponse.success(map);
        }
        else{
            return APIResponse.error(ErrorCode.TOKEN_LOGIN_FILDED);
        }
    }


    /**
     * 调用微北洋方法返回包含学号的map或者空值
     * @param token
     * @return
     */
    private JSONObject getUid(String token){
        JSONObject jsonObject = httpUtil.getUser(token);
        if((int)jsonObject.get("error_code")==0){
//            成功获取个人信息
            Map result = (Map)jsonObject.get("result");
            String uid = (String)result.get("userNumber");
            String avatar = (String)result.get("avatar");
            String userName = (String)result.get("nickname");
            JSONObject map  = new JSONObject();
            map.put("uid",uid);
            map.put("avatar",avatar);
            map.put("userName",userName);
            map.put("type","token");
            return map;
        }
        return null;
    }
}
