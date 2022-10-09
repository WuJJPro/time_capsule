package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    User getUserByUid(String uid);
    User getUserByEmail(String email);
    User checkPassword(String email,String password);
}
