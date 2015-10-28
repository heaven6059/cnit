package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

/**
 * @Title: ProductViewHistory.java
 * @Package com.cnit.yoyo.model.member
 * @Description: 商品浏览历史表
 * @Author 王鹏
 * @date 2015-5-18 下午3:29:51
 * @version V1.0
 */
public class ProductViewHistoryQryDTO extends BaseQryDTO implements Serializable {

	private Integer id;
	private Long memberId;
	private Long productId;
	private Long companyId;
	private Long storeId;
	private Long viewDate;
	private Integer goodsId;
	private String productName;
	private String productBn;
	private String picturePath;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getViewDate() {
		return viewDate;
	}

	public void setViewDate(Long viewDate) {
		this.viewDate = viewDate;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductBn() {
		return productBn;
	}

	public void setProductBn(String productBn) {
		this.productBn = productBn;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}