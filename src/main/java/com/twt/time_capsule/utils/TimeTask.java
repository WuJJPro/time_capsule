package com.twt.time_capsule.utils;

import com.twt.time_capsule.entity.Notice;
import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.mapper.NoticeMapper;
import com.twt.time_capsule.mapper.PrivateCapsuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component //定时任务在类上的注解
@EnableScheduling
public class TimeTask {

    private final int STATE_TIME_NOT_ARRIVED = 0;
    private final int STATE_WAIT_OPEN = 1;
    private final int STATE_OPENED = 2;
    private final int STATE_OPENED_FAILED = 3;
    private final String NOTICE_TYPE_OPEN = "open";
    @Autowired
    PrivateCapsuleMapper capsuleMapper;
    @Autowired
    NoticeMapper noticeMapper;
    /**
     * 本任务每天进行一次,进行私有胶囊的到期发送
     * 三件事：1.发送邮件 2.更改发送状态 3.推送通知
     */
    @Scheduled(cron = "0 47 22 * * ? ") //定时任务在方法上的注解
    public void sendCapsule(){
        //找到所有可以发送状态的胶囊
        List<PrivateCapsule> capsules = capsuleMapper.timeTask();
        System.out.println(capsules);
        for(PrivateCapsule capsule:capsules){
            try {
                //发送邮件
                sendMail(capsule);
                //更改发送状态
                capsule.setSuccess(STATE_WAIT_OPEN);
                capsuleMapper.updateById(capsule);
                //推送通知
                Notice notice = new Notice();
                notice.setUid(capsule.getUid());
                notice.setType(NOTICE_TYPE_OPEN);
                noticeMapper.insert(notice);
            } catch (Exception e) {
                e.printStackTrace();
                capsule.setSuccess(STATE_OPENED_FAILED);
                capsuleMapper.updateById(capsule);
            }
        }
    }


    private void sendMail(PrivateCapsule capsule) throws Exception{
        String template = "这里是发送模板模板";
        // TODO: 2022/10/16 发送，找产品要模板，或者是写在mysql里面
    }
}
