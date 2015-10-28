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
package com.cnit.yoyo.util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.Resource;

import com.cnit.yoyo.domain.EmailObject;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class EmailSenderUtil {
    public static final Logger log = LogManager.getLogger(EmailSenderUtil.class);
    public static Properties properties = new Properties();
    public static Resource configLocations;

    public void setConfigLocations(Resource configLocations) {
        EmailSenderUtil.configLocations = configLocations;
        try {
            properties.load(configLocations.getInputStream());
            log.info(properties.toString());
        } catch (IOException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public static InternetAddress[] listToArray(List<String> list) throws AddressException {
        InternetAddress[] internetAddresss = new InternetAddress[list.size()];
        for (int i = 0; i < list.size(); i++) {
            internetAddresss[i] = new InternetAddress(list.get(i));
        }
        return internetAddresss;
    }

    public static void sendEmail(EmailObject emailObject) {
        // 用properties构建一个session
        Session session = Session.getDefaultInstance(properties);
        // 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
        session.setDebug(true);
        // 用session为参数定义消息对象
        MimeMessage message = new MimeMessage(session);
        try {
            // 加载发件人地址
            message.setFrom(new InternetAddress(properties.getProperty("mail.smtp.user")));
            // 加载收件人地址
            if (emailObject.getRecipients() != null && emailObject.getRecipients().size() > 0) {
                InternetAddress[] recipients = EmailSenderUtil.listToArray(emailObject.getRecipients());
                message.addRecipients(Message.RecipientType.TO, recipients);
            }
            // 加载抄送人地址
            if (emailObject.getCcs() != null && emailObject.getCcs().size() > 0) {
                InternetAddress[] recipients = EmailSenderUtil.listToArray(emailObject.getCcs());
                message.addRecipients(Message.RecipientType.CC, recipients);
            }
            // 加载秘密抄送人地址
            if (emailObject.getBlindCcs() != null && emailObject.getBlindCcs().size() > 0) {
                InternetAddress[] recipients = EmailSenderUtil.listToArray(emailObject.getBlindCcs());
                message.addRecipients(Message.RecipientType.BCC, recipients);
            }
            // 加载标题
            message.setSubject(emailObject.getSubject());
            // 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            Multipart multipart = new MimeMultipart();
            // 设置邮件的文本内容
            BodyPart contentPart = new MimeBodyPart();
            contentPart.setText(emailObject.getContent());
            multipart.addBodyPart(contentPart);
            // 添加附件
            if(emailObject.getAttachments()!=null && emailObject.getAttachments().size()>0){
                for (File file : emailObject.getAttachments()) {
                    MimeBodyPart messageBodyPart = new MimeBodyPart();
                    messageBodyPart.attachFile(file);
                    multipart.addBodyPart(messageBodyPart);
                }
            }
            // 将multipart对象放到message中
            message.setContent(multipart);
            // 保存邮件
            message.saveChanges();
            // 发送邮件
            Transport transport = session.getTransport("smtp");
            // 连接服务器的邮箱
            transport.connect(properties.getProperty("mail.smtp.host"), properties.getProperty("mail.smtp.user"),
                    properties.getProperty("mail.smtp.pwd"));
            // 把邮件发送出去
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
