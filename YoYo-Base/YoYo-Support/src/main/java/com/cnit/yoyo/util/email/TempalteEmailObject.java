package com.cnit.yoyo.util.email;  

import java.io.Serializable;
import java.util.Map;
  
public class TempalteEmailObject implements Serializable {
	//接收方
	private String toEmail;
	//邮件主题
	private String subject;
	//邮件模板名称
	private String templateName;
	//需要替换的占位符
	private Map<String,Object> root;
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public Map<String, Object> getRoot() {
		return root;
	}
	public void setRoot(Map<String, Object> root) {
		this.root = root;
	}
	
}
