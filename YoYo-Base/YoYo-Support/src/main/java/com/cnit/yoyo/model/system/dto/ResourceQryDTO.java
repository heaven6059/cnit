package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 用户新增使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class ResourceQryDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer accountId;//账号ID
	
	private Integer parentId;//父菜单ID


	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}



}
