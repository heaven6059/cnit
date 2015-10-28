package com.cnit.yoyo.model.goods.dto;

import java.util.List;

import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.model.goods.ProductWithBLOBs;

public class ProductImgDTO extends ProductWithBLOBs {

	private List<Picture> picList;// 图片路径

	private Long commentCount;// 货品评论数量

	private Long sumPoint;// 货品评分总数

	private Double priceDouble;// 货品价格

	private Integer storeInt;// 货品库存

	private Integer storeId;// 分店id

	private Integer carId;

	// private String limitGoodsdown;//下架所有商品
	//
	// private String limitStore;//店铺屏蔽
	//
	// private String limitStoredown;//关闭店铺

	public List<Picture> getPicList() {
		return picList;
	}

	public void setPicList(List<Picture> picList) {
		this.picList = picList;
	}

	public Long getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Long getSumPoint() {
		return sumPoint;
	}

	public void setSumPoint(Long sumPoint) {
		this.sumPoint = sumPoint;
	}

	public Double getPriceDouble() {
		return priceDouble;
	}

	public void setPriceDouble(Double priceDouble) {
		this.priceDouble = priceDouble;
	}

	public Integer getStoreInt() {
		return storeInt;
	}

	public void setStoreInt(Integer storeInt) {
		this.storeInt = storeInt;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

}
