package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import com.cnit.yoyo.dto.BaseQryDTO;

public class ViolationQryDTO extends BaseQryDTO implements Serializable{

	private static final long serialVersionUID = -2873148349274881070L;
	private Integer catId;
	private Integer scoreValue;
	private Integer goodsDays;
	private Integer goodsdownDays;
	private Integer newsDays;
	private Integer newsDaysValue;
	private Integer storeDays;
	private Integer storedownDays;
	private Integer salesDays;
	private Double earnestMoney;
	private String earnestMoneySearchType;
	private Double earnestMoney1;
	private Double earnestMoney2;
	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public Integer getScoreValue() {
		return scoreValue;
	}
	public void setScoreValue(Integer scoreValue) {
		this.scoreValue = scoreValue;
	}
	public Integer getGoodsDays() {
		return goodsDays;
	}
	public void setGoodsDays(Integer goodsDays) {
		this.goodsDays = goodsDays;
	}
	public Integer getGoodsdownDays() {
		return goodsdownDays;
	}
	public void setGoodsdownDays(Integer goodsdownDays) {
		this.goodsdownDays = goodsdownDays;
	}
	public Integer getNewsDays() {
		return newsDays;
	}
	public void setNewsDays(Integer newsDays) {
		this.newsDays = newsDays;
	}
	public Integer getNewsDaysValue() {
		return newsDaysValue;
	}
	public void setNewsDaysValue(Integer newsDaysValue) {
		this.newsDaysValue = newsDaysValue;
	}
	public Integer getStoreDays() {
		return storeDays;
	}
	public void setStoreDays(Integer storeDays) {
		this.storeDays = storeDays;
	}
	public Integer getStoredownDays() {
		return storedownDays;
	}
	public void setStoredownDays(Integer storedownDays) {
		this.storedownDays = storedownDays;
	}
	public Integer getSalesDays() {
		return salesDays;
	}
	public void setSalesDays(Integer salesDays) {
		this.salesDays = salesDays;
	}
	public Double getEarnestMoney() {
		return earnestMoney;
	}
	public void setEarnestMoney(Double earnestMoney) {
		this.earnestMoney = earnestMoney;
	}
	public String getEarnestMoneySearchType() {
		return earnestMoneySearchType;
	}
	public void setEarnestMoneySearchType(String earnestMoneySearchType) {
		this.earnestMoneySearchType = earnestMoneySearchType;
	}
	public Double getEarnestMoney1() {
		return earnestMoney1;
	}
	public void setEarnestMoney1(Double earnestMoney1) {
		this.earnestMoney1 = earnestMoney1;
	}
	public Double getEarnestMoney2() {
		return earnestMoney2;
	}
	public void setEarnestMoney2(Double earnestMoney2) {
		this.earnestMoney2 = earnestMoney2;
	}
	
}
