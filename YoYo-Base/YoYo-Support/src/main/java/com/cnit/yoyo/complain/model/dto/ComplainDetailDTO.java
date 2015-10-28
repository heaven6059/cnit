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
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.complain.model.ComplainComments;
import com.cnit.yoyo.model.order.OrderItems;

public class ComplainDetailDTO extends Complain implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7219300635750643670L;
	private String companyName;
	private Long companyId;
	private BigDecimal finalAmount;
	private Date confirmTime;

	private List<OrderItems> items;

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public List<OrderItems> getItems() {
		return items;
	}

	public void setItems(List<OrderItems> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}