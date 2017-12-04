package com.cyb.web.mail;
import javax.mail.MessagingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 *作者 : iechenyb<br>
 *类描述: 说点啥<br>
 *创建时间: 2017年12月4日
 */
@RequestMapping("mail")
@Controller
public class MailController {
	Log log = LogFactory.getLog(MailController.class);
	@Autowired
    private JavaMailSenderImpl mailSender;
	
	@ResponseBody
	@RequestMapping("send")
	public String send(){
		SimpleMailMessage message = new SimpleMailMessage();
	       
        message.setFrom("383065059@qq.com");
        message.setTo("1048417686@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");
        
        /*mailSender.setHost("smtp.qq.com");//指定用来发送Email的邮件服务器主机名
        mailSender.setPort(465);//默认端口，标准的SMTP端口
        mailSender.setUsername("1048417686@qq.com");//用户名
        mailSender.setPassword("euxusivehnjvbehi");//密码
*/        
        MessagingException x;
        mailSender.send(message);
		return "success!";
        
	}
}
