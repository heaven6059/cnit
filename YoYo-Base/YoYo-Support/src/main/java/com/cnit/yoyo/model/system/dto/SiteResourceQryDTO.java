package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 用户新增使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class SiteResourceQryDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private String orderColumn;//账号ID
	
	private long companyId;//父菜单ID

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}





}
