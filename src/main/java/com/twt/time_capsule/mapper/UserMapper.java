package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
