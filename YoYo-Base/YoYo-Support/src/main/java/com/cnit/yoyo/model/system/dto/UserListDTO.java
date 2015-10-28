package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 用户列表使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class UserListDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer accountId;

	private String loginName;//用户账号
	
	private Integer roleId;
	
	private String roleName;//角色名称

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
