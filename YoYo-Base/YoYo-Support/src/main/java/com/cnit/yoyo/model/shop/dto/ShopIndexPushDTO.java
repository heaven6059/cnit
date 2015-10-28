package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopIndexPushDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8698991940079360958L;
	private String title;// 标题
	private String subHead;// 副标题
	private String href;// 链接
	private String img;// 图片
	private String price;// 价格

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubHead() {
		return subHead;
	}

	public void setSubHead(String subHead) {
		this.subHead = subHead;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
