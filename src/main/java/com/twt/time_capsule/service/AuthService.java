package com.twt.time_capsule.service;

import com.twt.time_capsule.utils.APIResponse;

public interface AuthService {
    APIResponse loginByJwt(String token);
    APIResponse sendCode(String email);
    APIResponse register(String email,String password,String passwordConfirm,String code);
    APIResponse loginByEmail(String email,String password);
    APIResponse findPassword(String email,String password,String passwordConfirm,String code);
}
