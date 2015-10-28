package com.cnit.yoyo.weixin.tencent.pojo;

public class AccessToken {

	/**
	 * 获取到的凭证
	 */
	private String token;
	/**
	 * 凭证有效时间，单位：秒
	 */
	private Integer expiresIn;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}
}
