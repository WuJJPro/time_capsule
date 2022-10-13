package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.PrivateCapsule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrivateCapsuleMapper extends BaseMapper<PrivateCapsule> {
    List<PrivateCapsule> getOpenedCapsules(String uid);
    List<PrivateCapsule> getClosedCapsules(String uid);
    List<PrivateCapsule> getAllCapsules(String uid);
    List<PrivateCapsule> getReadyCapsules(String uid);
}
