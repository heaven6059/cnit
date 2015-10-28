package com.cnit.yoyo.model.system.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.system.Resource.ResourceType;

/**
 * 用户列表使用的dto
* @author ssd
* @date 2015-4-22 下午3:38:20
 */
public class ResourceTreeDTO implements Serializable {

	/**
	* @author ssd
	* @date 2015-4-22 下午3:38:11 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;//菜单ID
	
	private Integer pId;//父菜单ID
	
	private String name;//菜单名称
	
	private String checked;
	
	private String target;//是否叶节点
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Integer getpId() {
		return pId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

}
