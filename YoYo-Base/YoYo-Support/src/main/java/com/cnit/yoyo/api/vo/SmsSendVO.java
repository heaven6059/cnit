package com.cnit.yoyo.api.vo;  

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;
  
public class SmsSendVO extends Parameter implements Serializable{
	@NotBlank(message="{mobile不能为空}")
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
