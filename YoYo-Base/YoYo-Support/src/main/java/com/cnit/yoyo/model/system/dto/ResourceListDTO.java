package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.system.Resource.ResourceType;

/**
 * 用户列表使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class ResourceListDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;//资源ID
	
	private String resourceName;//资源名称
	
	private String resourceType;//资源类型
	
	private String url;//路径
	
	private String disabled;//是否可用
	
	private String display;//是否显示
	
	private String permission;//权限
	
	private String target;//是否叶节点
	
	private Integer menuOrder;//级别
	
	private Integer parentId;//父菜单ID
	
	private String parentIds;//父菜单ID列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	

}
