package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 角色新增使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class ShopRoleAddDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private String roleId;//角色ID

	private String roleName;//角色名称
	
	private String description;//角色描述
	
	private String resourceIds;//权限ID

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResourceIds() {
		return resourceIds;
	}

	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}



}
