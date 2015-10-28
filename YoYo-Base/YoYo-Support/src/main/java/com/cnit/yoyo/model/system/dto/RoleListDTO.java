package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 角色列表使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class RoleListDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private int roleId;

	private String roleName;//角色名称
	
	private String description;//角色描述
	
	private String resourceName;//资源名称


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

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
}
