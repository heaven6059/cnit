package com.cnit.yoyo.api.vo;  

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;
  
public class CarTypeVO extends Parameter implements Serializable{
	@NotNull(message="{deptId不能为空}")
	private Integer deptId;

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
}
