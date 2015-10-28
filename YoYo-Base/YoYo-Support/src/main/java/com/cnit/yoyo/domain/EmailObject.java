/**
 * 文 件 名   :  EmailObject.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-9 上午9:53:06
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.domain;

import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * @description 电子邮件对象
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class EmailObject implements Serializable {
    private static final long serialVersionUID = -935146815846905871L;
    /**
     * 发件方
     */
    private String sender;
    /**
     * 收件方
     */
    private List<String> recipients;
    /**
     * 抄送方
     */
    private List<String> ccs;
    /**
     * 秘密抄送方
     */
    private List<String> blindCcs;
    /**
     * 主题
     */
    private String subject;
    /**
     * 内容
     */
    private String content;

    /**
     * 附件
     */
    private List<File> attachments;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public List<String> getCcs() {
        return ccs;
    }

    public void setCcs(List<String> ccs) {
        this.ccs = ccs;
    }

    public List<String> getBlindCcs() {
        return blindCcs;
    }

    public void setBlindCcs(List<String> blindCcs) {
        this.blindCcs = blindCcs;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<File> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<File> attachments) {
        this.attachments = attachments;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
