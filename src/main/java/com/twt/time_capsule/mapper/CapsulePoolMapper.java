package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.CapsulePool;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CapsulePoolMapper extends BaseMapper<CapsulePool> {
    List<CapsulePool> getPools(int state);
}
