package com.twt.time_capsule.utils;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.entity.User;
import com.twt.time_capsule.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义权限验证接口扩展
 */
@Component    // 保证此类被SpringBoot扫描，完成Sa-Token的自定义权限验证扩展
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    UserMapper userMapper;
    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本list仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {

        List<String> list = new ArrayList<String>();
        JSONObject map = JSON.parseObject((String) loginId);
        if(map.get("type").equals("token")){
            //        当通过token登陆时
            User user = userMapper.getUserByUid((String)map.get("uid"));
            if (user != null) {
//                已经登陆过
                if(user.getManage()==1){
                    list.add("admin");
                }
                else{
                    list.add("user");
                }
                return list;
            }
            else{
//                第一次登陆
                User newUser = new User();
                user.setAvatar((String) map.get("avatar"));
                user.setUid((String) map.get("uid"));
                user.setUserName((String) map.get("userName"));
                list.add("user");
                return list;
            }

        }
        else{
            // TODO: 2022/10/7 通过密码登录
            return list;
        }
    }

}
