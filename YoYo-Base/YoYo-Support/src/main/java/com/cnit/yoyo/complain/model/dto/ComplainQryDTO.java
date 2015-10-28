package com.cnit.yoyo.complain.model.dto;

/**   
 * @Package com.cnit.yoyo.complain.model 
 * @Description: 投诉
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-29 下午2:58:31 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

public class ComplainQryDTO extends BaseQryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5776355092737718428L;

	private String orderId;
	private String complainId;
	private String reason;
	private String status;
	private String startDate;
	private String endDate;
	private Long memberId;
	private Long storeId;
	private String storeName;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getComplainId() {
		return complainId;
	}

	public void setComplainId(String complainId) {
		this.complainId = complainId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}