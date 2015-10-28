package com.cnit.yoyo.api.vo;  

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;
  
public class CarFactoryIncludeCarDeptVO extends Parameter implements Serializable{
	@NotNull(message="{brandId不能为空}")
	private Integer brandId;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

}
