package com.cnit.yoyo.model.other.consult.dto;

import java.io.Serializable;

/**
 * @Title: GoodsPoint.java
 * @Package com.cnit.yoyo.model.other.consult
 * @Description: 商品评分选项设置
 * @Author 王鹏
 * @date 2015-4-28 上午9:24:37
 * @version V1.0
 */
public class GoodsPointDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8541978963697772042L;
	private Integer[] pointId;
	private String[] pointTitle;
	private Integer[] enabled;
	private String[] oneDesc;
	private String[] towDesc;
	private String[] threeDesc;
	private String[] fourDesc;
	private String[] fiveDesc;
	

	public Integer[] getPointId() {
		return pointId;
	}

	public void setPointId(Integer[] pointId) {
		this.pointId = pointId;
	}

	public String[] getPointTitle() {
		return pointTitle;
	}

	public void setPointTitle(String[] pointTitle) {
		this.pointTitle = pointTitle;
	}

	public Integer[] getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer[] enabled) {
		this.enabled = enabled;
	}

	public String[] getOneDesc() {
		return oneDesc;
	}

	public void setOneDesc(String[] oneDesc) {
		this.oneDesc = oneDesc;
	}

	public String[] getTowDesc() {
		return towDesc;
	}

	public void setTowDesc(String[] towDesc) {
		this.towDesc = towDesc;
	}

	public String[] getThreeDesc() {
		return threeDesc;
	}

	public void setThreeDesc(String[] threeDesc) {
		this.threeDesc = threeDesc;
	}

	public String[] getFourDesc() {
		return fourDesc;
	}

	public void setFourDesc(String[] fourDesc) {
		this.fourDesc = fourDesc;
	}

	public String[] getFiveDesc() {
		return fiveDesc;
	}

	public void setFiveDesc(String[] fiveDesc) {
		this.fiveDesc = fiveDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}