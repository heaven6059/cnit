package com.cnit.yoyo.model.other.consult.dto;

import java.io.Serializable;


/**
 * @Title: ConsultItems.java
 * @Package com.cnit.yoyo.model.other.consult
 * @Description: 咨询项设置
 * @Author 王鹏
 * @date 2015-4-28 上午9:26:29
 * @version V1.0
 */
public class ConsultItemsDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2311362088824573284L;
	private Integer[] itemId;
	private String[] title;

	public Integer[] getItemId() {
		return itemId;
	}

	public void setItemId(Integer[] itemId) {
		this.itemId = itemId;
	}

	public String[] getTitle() {
		return title;
	}

	public void setTitle(String[] title) {
		this.title = title;
	}

}