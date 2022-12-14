package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.EmailCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailCodeMapper extends BaseMapper<EmailCode> {
    EmailCode getCode(String email);
}
