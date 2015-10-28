package com.cnit.yoyo.model.site.dto;

import java.io.Serializable;

public class PicInfo implements Serializable {

	private static final long serialVersionUID = -180921214930051670L;

	private String picUrl;
	private String destUrl;
	private String title;
	private String desc;
	private String orderNo;
	private String bgColor;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getDestUrl() {
		return destUrl;
	}
	public void setDestUrl(String destUrl) {
		this.destUrl = destUrl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getBgColor() {
		return bgColor;
	}
	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}
}
