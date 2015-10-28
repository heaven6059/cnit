package com.cnit.yoyo.search.model;

import java.math.BigDecimal;

import com.cnit.yoyo.commons.page.Page;


/**
 * 搜索商品实体
 * 
 * @author wanghb
 * @date 2015-04-22
 * @version 1.0.0
 */
public class SearchGoods extends Page {

	private Integer goodsId;
	private Integer catId;
	private Integer brandId;
	private Integer promptId;
	private Integer companyId;
	private Integer storeId;
	private String fbn;
	private String dbn;
	private String name;
	private String marketable;
	private BigDecimal score;
	private BigDecimal cost;
	private BigDecimal price;
	private BigDecimal mktPrice;
	private BigDecimal store;
	private BigDecimal storeFreeze;
	private Integer notifyNum;
	private String downTime;
	private String upTime;
	private String lastModify;
	private Integer minBuy;
	private String unit;
	private BigDecimal weight;
	private String brief;
	private String goodsKind;
	private String goodsType;
	private String udfImg;
	private String imageDefaultId;
	private String thumbnailPic;
	private String smallPic;
	private String midPic;
	private String bigPic;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getPromptId() {
		return promptId;
	}

	public void setPromptId(Integer promptId) {
		this.promptId = promptId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getFbn() {
		return fbn;
	}

	public void setFbn(String fbn) {
		this.fbn = fbn;
	}

	public String getDbn() {
		return dbn;
	}

	public void setDbn(String dbn) {
		this.dbn = dbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarketable() {
		return marketable;
	}

	public void setMarketable(String marketable) {
		this.marketable = marketable;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getMktPrice() {
		return mktPrice;
	}

	public void setMktPrice(BigDecimal mktPrice) {
		this.mktPrice = mktPrice;
	}

	public BigDecimal getStore() {
		return store;
	}

	public void setStore(BigDecimal store) {
		this.store = store;
	}

	public BigDecimal getStoreFreeze() {
		return storeFreeze;
	}

	public void setStoreFreeze(BigDecimal storeFreeze) {
		this.storeFreeze = storeFreeze;
	}

	public Integer getNotifyNum() {
		return notifyNum;
	}

	public void setNotifyNum(Integer notifyNum) {
		this.notifyNum = notifyNum;
	}

	public String getDownTime() {
		return downTime;
	}

	public void setDownTime(String downTime) {
		this.downTime = downTime;
	}

	public String getUpTime() {
		return upTime;
	}

	public void setUpTime(String upTime) {
		this.upTime = upTime;
	}

	public String getLastModify() {
		return lastModify;
	}

	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}

	public Integer getMinBuy() {
		return minBuy;
	}

	public void setMinBuy(Integer minBuy) {
		this.minBuy = minBuy;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getGoodsKind() {
		return goodsKind;
	}

	public void setGoodsKind(String goodsKind) {
		this.goodsKind = goodsKind;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getUdfImg() {
		return udfImg;
	}

	public void setUdfImg(String udfImg) {
		this.udfImg = udfImg;
	}

	public String getImageDefaultId() {
		return imageDefaultId;
	}

	public void setImageDefaultId(String imageDefaultId) {
		this.imageDefaultId = imageDefaultId;
	}

	public String getThumbnailPic() {
		return thumbnailPic;
	}

	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getMidPic() {
		return midPic;
	}

	public void setMidPic(String midPic) {
		this.midPic = midPic;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

}