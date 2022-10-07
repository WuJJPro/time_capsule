package com.twt.time_capsule.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.lianjiatech.retrofit.spring.boot.annotation.Intercept;
import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitClient;
import okhttp3.MultipartBody;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

@Component
//路径前缀，必须以 / 结尾
@RetrofitClient(baseUrl = "https://api.twt.edu.cn/api/")
public interface HttpUtil {

    /**
     * 通过微北洋的接口获取个人信息
     * @param token
     * @return
     */
    @Headers({"domain:weipeiyang.twt.edu.cn",
            "ticket:YmFuYW5hLjM3YjU5MDA2M2Q1OTM3MTY0MDVhMmM1YTM4MmIxMTMwYjI4YmY4YTc=",
            "Content-type:application/x-www-form-urlencoded"})
    @GET("user/single")
    JSONObject getUser(@Header("token") String token);





}


