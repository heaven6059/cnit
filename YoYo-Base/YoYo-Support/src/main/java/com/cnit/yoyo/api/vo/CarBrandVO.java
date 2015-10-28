package com.cnit.yoyo.api.vo;  

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;
  
public class CarBrandVO extends Parameter implements Serializable{
	@NotBlank(message="{identifier不能为空}")
	private String identifier;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
}
