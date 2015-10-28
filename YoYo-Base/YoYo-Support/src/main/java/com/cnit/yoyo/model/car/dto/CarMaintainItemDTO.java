package com.cnit.yoyo.model.car.dto;

import java.io.Serializable;

public class CarMaintainItemDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7991551470597457582L;
	private Long id;
	private String text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
