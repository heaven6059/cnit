package com.cnit.yoyo.model.sales.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

public class ScoreBuyActivityQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3546890473125004056L;
	private String applyStartTime;
	private String applyEndTime;
	private String startTime;
	private String endTime;
	private Integer actOpen;
	private Integer type;

	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getApplyStartTime() {
		return applyStartTime;
	}

	public void setApplyStartTime(String applyStartTime) {
		this.applyStartTime = applyStartTime;
	}

	public String getApplyEndTime() {
		return applyEndTime;
	}

	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getActOpen() {
		return actOpen;
	}

	public void setActOpen(Integer actOpen) {
		this.actOpen = actOpen;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}