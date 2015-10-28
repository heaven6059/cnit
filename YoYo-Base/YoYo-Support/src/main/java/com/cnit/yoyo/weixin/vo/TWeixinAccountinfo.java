package com.cnit.yoyo.weixin.vo;

import java.util.Date;



public class TWeixinAccountinfo {

	/**
	 * 公众号名称
	 */
	private String fappname;
	/**
	 * 公众号应用ID
	 */
	private String fappid;
	/**
	 * 公众号应用密钥
	 */
	private String fappsecret;
	/**
	 * 公众号的全局唯一票据
	 */
	private String faccesstoken;
	/**
	 * 更新时间yyyy-MM-dd HH:mm:ss
	 */
	private String fupdatetime;
	
	public String getFappname() {
		return fappname;
	}
	public void setFappname(String fappname) {
		this.fappname = fappname;
	}
	public String getFappid() {
		return fappid;
	}
	public void setFappid(String fappid) {
		this.fappid = fappid;
	}
	public String getFappsecret() {
		return fappsecret;
	}
	public void setFappsecret(String fappsecret) {
		this.fappsecret = fappsecret;
	}
	public String getFaccesstoken() {
		return faccesstoken;
	}
	public void setFaccesstoken(String faccesstoken) {
		this.faccesstoken = faccesstoken;
	}
	public String getFupdatetime() {
		return fupdatetime;
	}
	public void setFupdatetime(String fupdatetime) {
		this.fupdatetime = fupdatetime;
	}
	
}
