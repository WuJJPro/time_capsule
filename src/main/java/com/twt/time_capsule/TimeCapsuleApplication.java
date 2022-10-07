package com.twt.time_capsule;

import com.github.lianjiatech.retrofit.spring.boot.annotation.RetrofitScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RetrofitScan("com.example.retrofitdemo.retrofitinterface")
public class TimeCapsuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeCapsuleApplication.class, args);
    }

}
