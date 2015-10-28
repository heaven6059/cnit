package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.system.Resource.ResourceType;

/**
 * 用户列表使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class SiteResourceTreeDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;

	private long id;//菜单ID
	
	private long pId;//父菜单ID
	
	private String name;//菜单名称
	
	private String checked;
	
	private String target;//是否叶节点
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getpId() {
		return pId;
	}

	public void setpId(long pId) {
		this.pId = pId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
