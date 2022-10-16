package com.twt.time_capsule.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadUtil {

    @Autowired
    private MailUtil mailUtil;

    /**
     * 发送邮箱
     * @param to 收件人
     * @param theme 主题
     * @param content 内容
     */
    @Async("taskExecutor")
    public void sendSimpleMail(String to, String theme, String content)throws Exception{
        mailUtil.sendSimpleMail(to, theme, content);
    }
}

