package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.Love;
import com.twt.time_capsule.entity.PublicCapsule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoveMapper extends BaseMapper<Love> {
    Love getLove(@Param("uid") String uid, @Param("key") String key);
}
