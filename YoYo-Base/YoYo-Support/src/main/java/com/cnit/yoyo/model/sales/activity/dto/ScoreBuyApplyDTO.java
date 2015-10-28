package com.cnit.yoyo.model.sales.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.model.sales.activity.ScoreBuyApply;

public class ScoreBuyApplyDTO extends ScoreBuyApply implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3546890473125004056L;
	private String actName;
	private String memName;
	private String storeName;
	private String goodsName;
	private String catName;
	private String memberLvScore;
	private String actOpen;
	private Date startTime;
	private Date endTime;
	private Integer limitNum;
	
	
	public Integer getLimitNum() {
		return limitNum;
	}
	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getActOpen() {
		return actOpen;
	}
	public void setActOpen(String actOpen) {
		this.actOpen = actOpen;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getMemberLvScore() {
		return memberLvScore;
	}
	public void setMemberLvScore(String memberLvScore) {
		this.memberLvScore = memberLvScore;
	}


}