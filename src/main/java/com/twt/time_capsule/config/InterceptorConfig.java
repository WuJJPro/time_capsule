package com.twt.time_capsule.config;

import com.twt.time_capsule.interceptor.CorsInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private static final List<String> EXCLUDE_PATH = Arrays.asList("/", "css/**", "js/**", "img/**", "json/**", "fonts/**","/*.html");

    @Bean
    public CorsInterceptor setBean1(){
        return new CorsInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(setBean1()).addPathPatterns("/**");

    }


}
