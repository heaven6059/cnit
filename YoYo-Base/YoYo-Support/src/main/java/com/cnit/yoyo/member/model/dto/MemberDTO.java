package com.cnit.yoyo.member.model.dto;

import java.io.Serializable;

public class MemberDTO implements Serializable {

	/***/
	private static final long serialVersionUID = 7231931078531682698L;
	private Integer memberId;// 会员Id
	private String mobile;// 手机
	private String email;// 邮箱
	private String name;//会员名
	private String nickName;//昵称

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("memberId=").append(memberId);
		sb.append(", mobile=").append(mobile);
		sb.append(", email=").append(email);
		return sb.toString();
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}