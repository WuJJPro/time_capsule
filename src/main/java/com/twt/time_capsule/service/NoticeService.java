package com.twt.time_capsule.service;

import com.twt.time_capsule.utils.APIResponse;

import java.util.List;

public interface NoticeService {
    APIResponse readNotice(List<String> ids);
}
