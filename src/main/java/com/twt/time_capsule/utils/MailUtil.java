package com.twt.time_capsule.utils;




import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
public class MailUtil {

    @Resource
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送简单的邮箱
     *
     * @param to 收件人
     * @param theme 标题
     * @param content 正文内容
     * @param cc 抄送
     */
    public void sendSimpleMail(String to, String theme, String content, String... cc)throws Exception{
        // 创建邮件对象
        SimpleMailMessage message = new SimpleMailMessage();


        message.setFrom(String.valueOf(new InternetAddress(from, "零下几度", "UTF-8")));      // 发件人

        message.setTo(to);          // 收件人
        message.setSubject(theme);  // 标题
        message.setText(content);   // 内容

        if (ArrayUtils.isNotEmpty(cc)) {
            message.setCc(cc);
        }

        // 发送
        javaMailSender.send(message);
    }

    /**
     * 发送HTML邮件
     *
     * @param to      收件人地址
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param cc      抄送地址
     * @throws MessagingException 邮件发送异常
     */
    public void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        if (ArrayUtils.isNotEmpty(cc)) {
            helper.setCc(cc);
        }
        javaMailSender.send(message);
    }
}
