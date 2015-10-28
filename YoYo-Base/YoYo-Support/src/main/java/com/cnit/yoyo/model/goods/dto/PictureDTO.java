package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @description 图片
 * @detail <功能详细描述>
 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
 * @version 1.0.0
 */
public class PictureDTO implements Serializable {
	private static final long serialVersionUID = 8392012755855723060L;
	private Long pictureId;
	private String picturePath;
	private Long productId;
	private Long goodsId;

	public Long getPictureId() {
		return pictureId;
	}

	public void setPictureId(Long pictureId) {
		this.pictureId = pictureId;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}