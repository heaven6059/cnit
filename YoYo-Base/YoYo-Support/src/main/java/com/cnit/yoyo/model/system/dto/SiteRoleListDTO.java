package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

/**
 * 角色列表使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class SiteRoleListDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;
	
	private long rolesId;

	private String rolesName;//角色名称
	
	private String description;//角色描述
	
	private String companyName;//主店名称
	
	private String storeName;//分店名称
	
	private String resourceName;//资源名称
	
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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
	
	public long getRolesId() {
		return rolesId;
	}

	public void setRolesId(long rolesId) {
		this.rolesId = rolesId;
	}

	public String getRolesName() {
		return rolesName;
	}

	public void setRolesName(String rolesName) {
		this.rolesName = rolesName;
	}

}
