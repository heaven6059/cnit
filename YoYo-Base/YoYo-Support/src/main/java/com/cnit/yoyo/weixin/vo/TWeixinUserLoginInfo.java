package com.cnit.yoyo.weixin.vo;

public class TWeixinUserLoginInfo {
	/**
	 * 手机号码
	 */
	private String fphonenumber;
	/**
	 * 用户openid
	 */
	private String fopenid;
	/**
	 * 用户最近一次登录状态
	 */
	private String flaststate;
	/**
	 * 更新时间yyyy-MM-dd HH:mm:ss
	 */
	private String fupdatetime;
	public String getFphonenumber() {
		return fphonenumber;
	}
	public void setFphonenumber(String fphonenumber) {
		this.fphonenumber = fphonenumber;
	}
	public String getFopenid() {
		return fopenid;
	}
	public void setFopenid(String fopenid) {
		this.fopenid = fopenid;
	}
	public String getFlaststate() {
		return flaststate;
	}
	public void setFlaststate(String flaststate) {
		this.flaststate = flaststate;
	}
	public String getFupdatetime() {
		return fupdatetime;
	}
	public void setFupdatetime(String string) {
		this.fupdatetime = string;
	}

}
