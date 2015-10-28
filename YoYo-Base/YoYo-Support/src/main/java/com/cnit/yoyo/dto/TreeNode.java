package com.cnit.yoyo.dto;

import java.io.Serializable;
import java.util.List;

public class TreeNode implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3426614522312905484L;
	private String id;
	private String name;
	private String pId;
	private List<TreeNode> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

}
