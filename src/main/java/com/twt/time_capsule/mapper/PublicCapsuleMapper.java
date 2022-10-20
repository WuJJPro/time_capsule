package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twt.time_capsule.entity.PublicCapsule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublicCapsuleMapper extends BaseMapper<PublicCapsule> {
    PublicCapsule getCapsule(@Param("uid") String uid,@Param("poolId") String poolId);
    List<PublicCapsule> getCapsuleByPool(String poolId);
    IPage<PublicCapsule> getCapsuleByPoolByHotAsc(Page<PublicCapsule> page, @Param("poolId") String poolId);
    IPage<PublicCapsule> getCapsuleByPoolByHotDesc(Page<PublicCapsule> page,@Param("poolId") String poolId);
    IPage<PublicCapsule> getCapsuleByPoolByTimeAsc(Page<PublicCapsule> page,@Param("poolId") String poolId);
    IPage<PublicCapsule> getCapsuleByPoolByTimeDesc(Page<PublicCapsule> page,@Param("poolId") String poolId);
}
