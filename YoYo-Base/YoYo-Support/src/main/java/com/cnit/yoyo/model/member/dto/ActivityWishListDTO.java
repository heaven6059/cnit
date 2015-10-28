package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.model.member.ActivityWishList;

public class ActivityWishListDTO extends ActivityWishList implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6468867269904235934L;

	private String activityName;
	private String activityUrl;
	private Integer activityStatus;
	private Date activityBegin;
	private Date activityEnd;

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityUrl() {
		return activityUrl;
	}

	public void setActivityUrl(String activityUrl) {
		this.activityUrl = activityUrl;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Date getActivityBegin() {
		return activityBegin;
	}

	public void setActivityBegin(Date activityBegin) {
		this.activityBegin = activityBegin;
	}

	public Date getActivityEnd() {
		return activityEnd;
	}

	public void setActivityEnd(Date activityEnd) {
		this.activityEnd = activityEnd;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}