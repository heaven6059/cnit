package com.cnit.yoyo.model.article.dto;

import java.io.Serializable;

import com.cnit.yoyo.dto.BaseQryDTO;

public class ArticleQryDto extends BaseQryDTO implements Serializable{
	
	private static final long serialVersionUID = 4011846289190115756L;

	private Integer nodeId;//所属栏目
	
	private String title;//文章标题

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
