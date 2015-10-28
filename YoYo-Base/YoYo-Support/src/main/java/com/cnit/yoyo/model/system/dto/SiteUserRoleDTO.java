package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 角色新增使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class SiteUserRoleDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private int userId;//账号ID 
	
	private long roleId;//角色ID



	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}



}
