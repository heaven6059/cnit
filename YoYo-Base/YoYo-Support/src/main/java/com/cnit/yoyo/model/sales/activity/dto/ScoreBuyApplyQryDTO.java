package com.cnit.yoyo.model.sales.activity.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.dto.BaseQryDTO;

public class ScoreBuyApplyQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3546890473125004056L;
	private String actName;
	private String memName;
	private String storeName;
	private Integer status;
	private Integer memberId;
	private Integer type;//类型：1、积分换购2、团购3、秒杀4、限时抢购5、捆绑活动
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}


}