package com.cnit.yoyo.model.shop.dto;

import com.cnit.yoyo.model.shop.Violation;

public class ViolationDTO extends Violation{

	private String catName;//违规类型名称

	public String getCatName() {
		return catName;
	}

	public void setCatName(String catName) {
		this.catName = catName;
	}
	
}
