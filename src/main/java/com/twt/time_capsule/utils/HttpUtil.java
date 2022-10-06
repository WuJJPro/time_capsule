package com.twt.time_capsule.utils;


import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.function.Consumer;

/**
 * @ClassName: HttpUtil
 * @Description:
 * @Author: 过河卒
 * @Date: 2022/1/16 10:30
 * @Version: 1.0
 */
@Data
public class HttpUtil {

    HttpURLConnection httpURLConnection;

    public HttpUtil() {

    }

    static String DOMAIN="weipeiyang.twt.edu.cn";
    static String CONTENT_TYPE = "application/x-www-form-urlencoded";
    static String TICKET = "YmFuYW5hLjM3YjU5MDA2M2Q1OTM3MTY0MDVhMmM1YTM4MmIxMTMwYjI4YmY4YTc=";
    static String CHECKOUT_TOKEN_URL = "https://api.twt.edu.cn/api/user/single";
    String LOGIN_URL = "https://api.twt.edu.cn/api/auth/common";




    public enum method{GET,POST,DELETE,PUT};

    /**
     * 创建请求，必要参数为请求方式和url
     * @param theMethod
     * @param httpUrl
     * @return
     * @throws IOException
     */
    public static HttpUtil createConnection(method theMethod,String httpUrl) throws IOException {
        HttpUtil httpUtil = new HttpUtil();
        URL url =new URL(httpUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpUtil.setHttpURLConnection(httpURLConnection);
        httpUtil.getHttpURLConnection().setRequestMethod(theMethod.toString());
        return httpUtil;
    }

    /**
     * 给请求中添加参数
     * @param params
     * @throws IOException
     */
    public HttpUtil setParams(Map<String,String> params) throws IOException {
        //设置需要输出
        httpURLConnection.setDoOutput(true);
        //判断是否有参数.
        if (params != null && params.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append("&").append(entry.getKey()).append("=").append(entry.getValue());
            }
            //sb.substring(1)去除最前面的&
            httpURLConnection.getOutputStream().write(sb.substring(1).toString().getBytes("utf-8"));
        }
        return this;
    }

    /**
     * 添加请求头
     * @param header
     */
    public HttpUtil setHeader(Map<String,String>header){
        /**
         * 用consumer给请求添加请求头
         */
        Consumer<Map.Entry<String,String>> action= (entry)->{
            String key = entry.getKey();
            String value = entry.getValue();
            httpURLConnection.setRequestProperty(key,value);
        };
        header.entrySet().forEach(action);
        return this;
    }

    public APIResponse execute() throws IOException {
        String responseContent = null;
        try {
            httpURLConnection.connect();
            responseContent = StreamUtils.copyToString(httpURLConnection.getInputStream(), StandardCharsets.UTF_8);
            JSONObject response = JSONObject.parseObject(responseContent);
            APIResponse apiResponse = new APIResponse((int) response.get("error_code"), response.get("message").toString(),
                    response.get("result"));
            return apiResponse;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(responseContent);
            return APIResponse.error(ErrorCode.LOGIN_FAILED, responseContent);
        }

    }


    public static String executeTest(String token ,Integer testCount) throws IOException, InterruptedException {
        Integer errorCount = 0;
        HttpHeaders headers = new HttpHeaders();
        headers.add("ticket",TICKET);
        headers.add("domain",DOMAIN);
        headers.add("Content-type",CONTENT_TYPE);
        headers.add("token",token);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> resEntity = null;
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        for(int i = 0;i<testCount;i++){
            Thread.sleep(1);
            try {

                resEntity = restTemplate.exchange(CHECKOUT_TOKEN_URL, HttpMethod.GET, requestEntity, String.class);


            } catch (Exception e) {
                System.out.println(resEntity);
                errorCount++;
                e.printStackTrace();
            }
        }
        return  "请求失败数量/总请求数量："+errorCount+"/"+testCount;
    }
}

