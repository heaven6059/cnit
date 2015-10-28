package com.cnit.yoyo.model.goods.dto;

import com.cnit.yoyo.model.goods.GoodsWithBLOBs;

public class GoodsBrandDTO extends GoodsWithBLOBs {

	
	private static final long serialVersionUID = -7346070906808591832L;
	private String brandName;//品牌名称

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	
}
