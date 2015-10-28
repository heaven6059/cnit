package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 角色新增使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class SiteRoleAddDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private long roleId;//角色ID

	private String roleName;//角色名称
	
	private String description;//角色描述
	
	private long companyId;//主店ID
	
	private long storeId;//分店ID
	
	private String resourceIds;//权限ID

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
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

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public long getStoreId() {
		return storeId;
	}

	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}



}
