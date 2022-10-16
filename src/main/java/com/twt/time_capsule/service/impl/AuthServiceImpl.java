package com.twt.time_capsule.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSONObject;
import com.twt.time_capsule.entity.EmailCode;
import com.twt.time_capsule.entity.User;
import com.twt.time_capsule.mapper.EmailCodeMapper;
import com.twt.time_capsule.mapper.UserMapper;
import com.twt.time_capsule.service.AuthService;
import com.twt.time_capsule.utils.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    HttpUtil httpUtil;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ThreadUtil threadUtil;
    @Autowired
    EmailCodeMapper emailCodeMapper;
    @Override


    public APIResponse loginByJwt(String token) {
        Map res = getUid(token);
        if(res!=null){
            User user = userMapper.getUserByUid((String)res.get("uid"));
            if(user==null) {
                //                第一次登陆
                User newUser = new User();
                newUser.setUid((String) res.get("uid"));
                newUser.setEmail((String) res.get("email"));
                newUser.setManage(0);
                newUser.setEmailState(0);
                userMapper.insert(newUser);
            }
            StpUtil.login(res.get("uid"));
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

    @Override
    public APIResponse sendCode(String email) {

        // 参数校验
        if (StringUtils.isAnyBlank(email)) {
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱校验
            return APIResponse.error(ErrorCode.EMAIL_ERROR);
        }   // 不通过


        // 随机生成6位数字验证码
        String code = StringUtil.randomSixCode();
        // 正文内容
        String content = "【天外天】亲爱的用户：\n" +
                         "您此次的验证码为：\n\n" +
                         code +
                         "\n\n" +
                         "此验证码5分钟内有效，请立即进行下一步操作。 如非你本人操作，请忽略此邮件。\n" +
                         "感谢您的使用！";
        // 发送验证码
        try {
            threadUtil.sendSimpleMail(email, "您此次的验证码为：" + code, content);
        } catch (Exception e) {
            e.printStackTrace();
            return APIResponse.error(ErrorCode.CODE_SEND_ERROR);
        }
        // 丢入mysql，设置5分钟过期
        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        Date now = new Date();
        System.out.println(now);
        Date endTime= new Date(now.getTime() + 300000);
        System.out.println(endTime);
        emailCode.setEndTime(endTime);
        emailCodeMapper.insert(emailCode);
        return APIResponse.success();
    }

    @Override
    public APIResponse register(String email, String password, String passwordConfirm,String code) {
        if (StringUtils.isAnyBlank(email, password, passwordConfirm, code)) {
            // 非空
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return APIResponse.error(ErrorCode.EMAIL_ERROR);
        }else if (!password.equals(passwordConfirm)) {
            // 密码一致校验
            return APIResponse.error(ErrorCode.PASSWORD_INCONSISTENT);
        }else if (!StringUtil.checkPassword(password) || code.length() != 6) {
            // 密码格式和验证码长度校验
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }

        // 获取数据库中最新的一张验证码
        EmailCode emailCode = emailCodeMapper.getCode(email);
        // 验证是否过期，是否正确
        if(emailCode==null){
            return APIResponse.error(ErrorCode.CODE_ERROR);
        }
        if(!emailCode.getCode().equals(code)){
            return APIResponse.error(ErrorCode.CODE_ERROR);
        }
        String uid = StpUtil.getLoginIdAsString();
        // 注册用户
        User user = userMapper.getUserByUid(uid);
        // 获取加密盐
        String salt = StringUtil.randomEncryptedSalt();
        // 邮箱状态
        user.setEmail(email);
        user.setEmailState(1);
        // 密码加密（原明文密码 + 随机加密盐） md5加密
        user.setPassword(DigestUtils.md5Hex(password + salt));
        // 加密盐
        user.setSalt(salt);
        userMapper.updateById(user);
        // 插入数据
        return APIResponse.success();
    }

    @Override
    public APIResponse loginByEmail(String email, String password) {
        User user = userMapper.getUserByEmail(email);
        if(user==null){
            return APIResponse.error(ErrorCode.USER_NULL);
        }
        // 获取加密盐
        String salt = user.getSalt();
        user = userMapper.checkPassword(email,DigestUtils.md5Hex(password + salt));

        if(user==null){
            return APIResponse.error(ErrorCode.EMAIL_OR_PASSWORD_ERROR);
        }
        String uid = user.getUid();
        StpUtil.login(uid);
        String token = StpUtil.getTokenValue();
        List roleList = StpUtil.getRoleList();
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        map.put("role",roleList);
        return APIResponse.success(map);
    }

    @Override
    public APIResponse findPassword(String email, String password, String passwordConfirm, String code) {
        if (StringUtils.isAnyBlank(email, password, passwordConfirm, code)) {
            // 非空
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }else if (!StringUtil.checkEmail(email)) {
            // 邮箱格式校验
            return APIResponse.error(ErrorCode.EMAIL_ERROR);
        }else if (!password.equals(passwordConfirm)) {
            // 密码一致校验
            return APIResponse.error(ErrorCode.PASSWORD_INCONSISTENT);
        }else if (!StringUtil.checkPassword(password) || code.length() != 6) {
            // 密码格式和验证码长度校验
            return APIResponse.error(ErrorCode.PARAM_ERROR);
        }

        // 获取数据库中最新的一张验证码
        EmailCode emailCode = emailCodeMapper.getCode(email);
        // 验证是否过期，是否正确
        if(emailCode==null){
            return APIResponse.error(ErrorCode.CODE_ERROR);
        }
        if(!emailCode.getCode().equals(code)){
            return APIResponse.error(ErrorCode.CODE_ERROR);
        }
        // 注册用户
        User user = userMapper.getUserByEmail(email);
        // 获取加密盐
        String salt = StringUtil.randomEncryptedSalt();
        // 密码加密（原明文密码 + 随机加密盐） md5加密
        user.setPassword(DigestUtils.md5Hex(password + salt));
        // 加密盐
        user.setSalt(salt);
        userMapper.updateById(user);
        // 插入数据
        return APIResponse.success();
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
            String email = (String)result.get("email");
            JSONObject map  = new JSONObject();
            map.put("uid",uid);
            map.put("email",email);
            return map;
        }
        return null;
    }
}
