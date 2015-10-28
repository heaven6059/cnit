/**
 * 文 件 名   :  EmailSenderUtil.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-9 上午10:04:02
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util.email;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.EmailObject;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.util.EmailSenderUtil;
import com.cnit.yoyo.util.email.TempalteEmailObject;

import freemarker.template.Template;

/**
 * email服务
 * 
 * @Author:yangyi
 * @Date:2015年7月4日
 * @Version:1.0.0
 */
public class EmailService {
	public static final Logger log = LogManager.getLogger(EmailService.class);
	public static Properties properties = new Properties();
	public static Resource configLocations;
	private JavaMailSender sender;    
	private FreeMarkerConfigurer freeMarkerConfigurer=null;//FreeMarker的技术类    
	
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {    
		this.freeMarkerConfigurer = freeMarkerConfigurer;    
	}    
	
	public void setSender(JavaMailSender sender) {
		this.sender = sender;    
	}    
	
	 public Resource getConfigLocation() {
	        return configLocations;
	    }

	public void setConfigLocations(Resource configLocations) {
		try {
			properties.load(configLocations.getInputStream());
			log.info(properties.toString());
		} catch (IOException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		}
	}

      
    /** 
     * 生成html模板字符串 
     * @param root 存储动态数据的map 
     * @return 
     */  
    private String getMailText(Map<String,Object> root,String templateName){  
         String htmlText="";    
            try {    
                //通过指定模板名获取FreeMarker模板实例    
                Template tpl=freeMarkerConfigurer.getConfiguration().getTemplate(templateName);    
                htmlText=FreeMarkerTemplateUtils.processTemplateIntoString(tpl,root);    
            } catch (Exception e) {    
                e.printStackTrace();    
            }    
            return htmlText;    
    }  
    
    /** 
     * 群发送邮件 
     * @param root 存储动态数据的map 
     * @param toEmail 邮件地址 
     * @param subject 邮件主题 
     * @return 
     */  
    public boolean sendGroupTemplateMail(TempalteEmailObject emailObject,List<String> recipients){
        try {  
            MimeMessage msg=sender.createMimeMessage();    
            MimeMessageHelper helper=new MimeMessageHelper(msg,false,"utf-8");//由于是html邮件，不是mulitpart类型    
            helper.setFrom(properties.getProperty("mail.smtp.user"));
            if(null == recipients) {
            	return false;
            }
            // 设置收件人们
            final int num = recipients.size();
            InternetAddress[] addresses = new InternetAddress[num];
            for (int i = 0; i < num; i++) {
            	String address = recipients.get(i);
            	if(!StringUtils.isEmpty(address)) {
            		 InternetAddress internet= new InternetAddress(address);
            		 addresses[i] = internet;
            	}
            }
            helper.setTo(addresses);  
            helper.setSubject(emailObject.getSubject());    
            String htmlText=getMailText(emailObject.getRoot(),emailObject.getTemplateName());//使用模板生成html邮件内容    
            if(!StringUtils.isBlank(htmlText)){
            	helper.setText(htmlText, true);    
            	sender.send(msg);
            	return true; 
            }else{
            	return false;
            }
        } catch (MailException e) {  
           // System.out.println("失败发送模板邮件");   
            e.printStackTrace();  
            return false;  
        } catch (MessagingException e) {  
        //  System.out.println("失败发送模板邮件");   
            e.printStackTrace();  
            return false;  
        }    
         
    }
      
    /** 
     * 发送邮件 
     * @param root 存储动态数据的map 
     * @param toEmail 邮件地址 
     * @param subject 邮件主题 
     * @return 
     */  
    public boolean sendTemplateMail(TempalteEmailObject emailObject){
        try {  
            MimeMessage msg=sender.createMimeMessage();    
            MimeMessageHelper helper=new MimeMessageHelper(msg,false,"utf-8");//由于是html邮件，不是mulitpart类型    
            helper.setFrom(properties.getProperty("mail.smtp.user"));
            helper.setTo(emailObject.getToEmail());    
            helper.setSubject(emailObject.getSubject());    
            String htmlText=getMailText(emailObject.getRoot(),emailObject.getTemplateName());//使用模板生成html邮件内容    
            if(!StringUtils.isBlank(htmlText)){
            	helper.setText(htmlText, true);    
            	sender.send(msg);
            	return true; 
            }else{
            	return false;
            }
        } catch (MailException e) {  
           // System.out.println("失败发送模板邮件");   
            e.printStackTrace();  
            return false;  
        } catch (MessagingException e) {  
        //  System.out.println("失败发送模板邮件");   
            e.printStackTrace();  
            return false;  
        }    
         
    }

	public Object sendEmail(Object reqData) throws Exception {
		HeadObject headObject = new HeadObject();
		headObject.setRetType(GlobalStatic.RET_TYPE_OTHER);
		TempalteEmailObject emailObject=(TempalteEmailObject) JSONObject.toBean(JSONObject.fromObject(reqData), TempalteEmailObject.class);
		boolean result=sendTemplateMail(emailObject);
		if(result){
			headObject.setRetCode(ErrorCode.SUCCESS);
			headObject.setRetMsg("发送成功");
		}
		return headObject;
	}
}
