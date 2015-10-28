package com.cnit.yoyo.model.goods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Product
 * @Description: 货品
 * @author xiaox
 * @date 2015-4-14 下午2:42:29
 */

public class Product implements Serializable {
	private static final long serialVersionUID = -818915661317924435L;
	private Integer productId;
	private Integer goodsId;
	private String barcode;
	private String title;
	private String bn;
	private BigDecimal price;
	private BigDecimal cost;
	private BigDecimal mktprice;
	private String name;
	private BigDecimal weight;
	private String specInfo;
	private String specDesc;
	private String unit;
	private Integer store;
	private String storePlace;
	private Integer freez;
	private String goodsType;
	private Date lastModify;
	private Date uptime;
	private String disabled;
	private String marketable;
	private List<Picture> pictures;
	private String picturePath; // 默认图片

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getMktprice() {
		return mktprice;
	}

	public void setMktprice(BigDecimal mktprice) {
		this.mktprice = mktprice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getStore() {
		return store;
	}

	public void setStore(Integer store) {
		this.store = store;
	}

	public String getStorePlace() {
		return storePlace;
	}

	public void setStorePlace(String storePlace) {
		this.storePlace = storePlace;
	}

	public Integer getFreez() {
		return freez;
	}

	public void setFreez(Integer freez) {
		this.freez = freez;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMarketable() {
		return marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public String getSpecInfo() {
		return specInfo;
	}

	public void setSpecInfo(String specInfo) {
		this.specInfo = specInfo;
	}

	public String getSpecDesc() {
		return specDesc;
	}

	public void setSpecDesc(String specDesc) {
		this.specDesc = specDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}
}