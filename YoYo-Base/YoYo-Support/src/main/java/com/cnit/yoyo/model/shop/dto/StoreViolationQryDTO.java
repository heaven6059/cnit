package com.cnit.yoyo.model.shop.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.dto.BaseQryDTO;

public class StoreViolationQryDTO extends BaseQryDTO implements Serializable {

	private static final long serialVersionUID = 3141451773463853480L;
	private Integer catId;// 违规类型
	private Integer score;// 扣分节点
	private Integer newsValue;// 降权值
	private Double earnestMoney;// 支付保证金
	private String earnestMoneySearchType;
	private Double earnestMoney1;
	private Double earnestMoney2;
	private String goodsdownStarttime;
	private String goodsdownEndtime;
	private String goodsStarttime;
	private String goodsEndtime;
	private String storeStarttime;
	private String storeEndtime;
	private String storeDownStartTime;
	private String storeDownEndTime;

	private List<Integer> processed;// 是否已处理

	private Integer status;// 状态

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getNewsValue() {
		return newsValue;
	}

	public void setNewsValue(Integer newsValue) {
		this.newsValue = newsValue;
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

	public List<Integer> getProcessed() {
		return processed;
	}

	public void setProcessed(List<Integer> processed) {
		this.processed = processed;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getGoodsdownStarttime() {
		return goodsdownStarttime;
	}

	public void setGoodsdownStarttime(String goodsdownStarttime) {
		this.goodsdownStarttime = goodsdownStarttime;
	}

	public String getGoodsdownEndtime() {
		return goodsdownEndtime;
	}

	public void setGoodsdownEndtime(String goodsdownEndtime) {
		this.goodsdownEndtime = goodsdownEndtime;
	}

	public String getGoodsStarttime() {
		return goodsStarttime;
	}

	public void setGoodsStarttime(String goodsStarttime) {
		this.goodsStarttime = goodsStarttime;
	}

	public String getGoodsEndtime() {
		return goodsEndtime;
	}

	public void setGoodsEndtime(String goodsEndtime) {
		this.goodsEndtime = goodsEndtime;
	}

	public String getStoreStarttime() {
		return storeStarttime;
	}

	public void setStoreStarttime(String storeStarttime) {
		this.storeStarttime = storeStarttime;
	}

	public String getStoreEndtime() {
		return storeEndtime;
	}

	public void setStoreEndtime(String storeEndtime) {
		this.storeEndtime = storeEndtime;
	}

	public String getStoreDownStartTime() {
		return storeDownStartTime;
	}

	public void setStoreDownStartTime(String storeDownStartTime) {
		this.storeDownStartTime = storeDownStartTime;
	}

	public String getStoreDownEndTime() {
		return storeDownEndTime;
	}

	public void setStoreDownEndTime(String storeDownEndTime) {
		this.storeDownEndTime = storeDownEndTime;
	}

}
