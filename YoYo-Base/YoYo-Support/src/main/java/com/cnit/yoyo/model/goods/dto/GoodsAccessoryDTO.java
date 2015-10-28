package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.cnit.yoyo.model.car.AccessoryParam;

public class GoodsAccessoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2920204798928385576L;
	private String goodsName;
	private String goodsId;
	private String goodsImg;
	private BigDecimal price;
	private Integer catalogId;
	private String catalogName;
	private Integer catId;
	private Integer orderNum;
	private List<AccessoryParamValueDTO> accessoryParamValues;

	public Integer getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(Integer catalogId) {
		this.catalogId = catalogId;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<AccessoryParamValueDTO> getAccessoryParamValues() {
		return accessoryParamValues;
	}

	public void setAccessoryParamValues(
			List<AccessoryParamValueDTO> accessoryParamValues) {
		this.accessoryParamValues = accessoryParamValues;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String goodsImg) {
		this.goodsImg = goodsImg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
