package com.twt.time_capsule.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.twt.time_capsule.entity.Notice;
import com.twt.time_capsule.mapper.NoticeMapper;
import com.twt.time_capsule.service.NoticeService;
import com.twt.time_capsule.utils.APIResponse;
import com.twt.time_capsule.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    private final int NOTICE_STATE_UNREAD = 1;
    private final int NOTICE_STATE_READ = 0;
    @Autowired
    NoticeMapper noticeMapper;
    @Override
    public APIResponse readNotice(String id) {
        Notice notice = noticeMapper.selectById(id);
        if(notice==null){
            return APIResponse.error(ErrorCode.NOTICE_ALREADY_READ);
        }
        notice.setRead(NOTICE_STATE_READ);
        noticeMapper.updateById(notice);
        return APIResponse.success();
    }

    @Override
    public APIResponse getNotice() {
        String uid = StpUtil.getLoginIdAsString();
        List<Notice> notices = noticeMapper.getNotices(uid);
        return APIResponse.success(notices);
    }
}
