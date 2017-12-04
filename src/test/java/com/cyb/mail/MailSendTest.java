package com.cyb.mail;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.cyb.base.SpringJunitBase;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月4日
 */

public class MailSendTest  extends SpringJunitBase{

    @Autowired
    private JavaMailSenderImpl mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
    	SimpleMailMessage message = new SimpleMailMessage();
       
        message.setFrom("383065059@qq.com");
        message.setTo("1048417686@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        
        mailSender.setHost("smtp.qq.com");//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(465);//默认端口，标准的SMTP端口
        //mailSender.setUsername("1048417686@qq.com");//用户名
        //mailSender.setPassword("euxusivehnjvbehi");//密码
        
        
        mailSender.send(message);
        
        Thread.sleep(100*1000);
    }

}
