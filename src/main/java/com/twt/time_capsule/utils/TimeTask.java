package com.twt.time_capsule.utils;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component //定时任务在类上的注解
public class TimeTask {

    /**
     * 本任务每天进行一次,进行私有胶囊的到期发送
     * 三件事：1.发送邮件 2.更改发送状态 3.推送通知
     */
    @Scheduled(cron = "0 0 20 * * ?") //定时任务在方法上的注解
    public void sendCapsule(){
        System.out.println("发送邮件"+new Date());
    }

}
