package com.twt.time_capsule.service;

import com.twt.time_capsule.utils.APIResponse;

public interface AuthService {
    APIResponse login(String token);
}
