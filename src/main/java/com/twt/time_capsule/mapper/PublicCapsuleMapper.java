package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.PublicCapsule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PublicCapsuleMapper extends BaseMapper<PublicCapsule> {
    PublicCapsule getCapsule(@Param("uid") String uid,@Param("poolId") String poolId);
}
