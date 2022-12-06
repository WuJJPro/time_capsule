package com.twt.time_capsule.service;

import com.twt.time_capsule.utils.APIResponse;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    APIResponse getReport();
}
