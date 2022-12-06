package com.twt.time_capsule.service.impl;

import com.twt.time_capsule.mapper.ReportMapper;
import com.twt.time_capsule.service.ReportService;
import com.twt.time_capsule.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportMapper reportMapper;
    @Override
    public APIResponse getReport() {
        return null;
    }
}
