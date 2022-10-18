package com.twt.time_capsule.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.twt.time_capsule.entity.Notice;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {
    List<Notice> getNotices(String uid);
}
