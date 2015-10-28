package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;
/**
 * 
 * @Description: 保养周期 关系表dto
 * @author xiaox
 * @date 2015-3-26 下午4:30:51
 */
public class CarMaintainShipDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
    private Integer maintainId;

    private Integer categoryId;
    
    private String categoryName;

	public Integer getMaintainId() {
		return maintainId;
	}

	public void setMaintainId(Integer maintainId) {
		this.maintainId = maintainId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	} 

  
   
}