package com.twt.time_capsule.utils;

import com.twt.time_capsule.entity.Notice;
import com.twt.time_capsule.entity.PrivateCapsule;
import com.twt.time_capsule.entity.User;
import com.twt.time_capsule.mapper.NoticeMapper;
import com.twt.time_capsule.mapper.PrivateCapsuleMapper;
import com.twt.time_capsule.mapper.UserMapper;
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
    private final int NOTICE_STATE_UNREAD = 1;
    private final int NOTICE_STATE_READ = 0;
    @Autowired
    PrivateCapsuleMapper capsuleMapper;
    @Autowired
    NoticeMapper noticeMapper;
    @Autowired
    ThreadUtil threadUtil;
    @Autowired
    UserMapper userMapper;
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
                notice.setRead(NOTICE_STATE_UNREAD);
                noticeMapper.insert(notice);
            } catch (Exception e) {
                e.printStackTrace();
                capsule.setSuccess(STATE_OPENED_FAILED);
                capsuleMapper.updateById(capsule);
            }
        }
    }


    private void sendMail(PrivateCapsule capsule) throws Exception{
        //获取邮箱
        User user = userMapper.getUserByUid(capsule.getUid());
        String email = user.getEmail();
        //获取时间差
        Date createDate = capsule.getCreatedAt();
        Date openData = capsule.getOpenTime();
        int days = differentDaysByMillisecond(openData,createDate);
        String theme = "叮~你有一个时间胶囊待打开哦~";
        String template = days+"天前，你留下了这个胶囊。里面装着的或许是当初定下的一个小目标，或许是在某个特殊日子留下的一段话，或许是一句想说却没敢说出口的话……当某个时刻成了回忆，那它便有了专属的意义。现在就去打开这段回忆吧！";
        threadUtil.sendSimpleMail(email,theme, template);
    }


    private int differentDaysByMillisecond(Date date1, Date date2) {
        return Math.abs((int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24)));
    }
}
