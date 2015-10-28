package com.cnit.yoyo.weixin.vo;

public class CheckLoginVo {

	/**
	 * 当前登录状态 true登录 false未登录
	 */
	private Boolean loginState;
	/**
	 * 登录成功后要跳转到的页面url(不包含项目名称，如/mainController/queryMyWallet)
	 */
	private String requestUrl;
	
	public Boolean getLoginState() {
		return loginState;
	}
	public void setLoginState(Boolean loginState) {
		this.loginState = loginState;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	
}
