package com.twt.time_capsule.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 使用阿里 fastjson 作为 JSON MessageConverter
     *
     * @param converters
     */
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
