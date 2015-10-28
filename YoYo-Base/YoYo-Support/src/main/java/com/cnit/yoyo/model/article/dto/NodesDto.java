package com.cnit.yoyo.model.article.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.article.ArticleIndex;

public class NodesDto implements Serializable {
    private static final long serialVersionUID = 8187872106085771298L;
    private Integer nodeId;
    private Integer parentId;
    private Integer hasChildren;
    private String nodeName;
    private String nodePath;
    private Integer ordernum;
    private Integer disabled;
    private List<NodesDto> children;
    private List<ArticleIndex> articleIndexList;
    
    public List<ArticleIndex> getArticleIndexList() {
		return articleIndexList;
	}

	public void setArticleIndexList(List<ArticleIndex> articleIndexList) {
		this.articleIndexList = articleIndexList;
	}

	public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getDisabled() {
        return disabled;
    }

    public void setDisabled(Integer disabled) {
        this.disabled = disabled;
    }

    public List<NodesDto> getChildren() {
        return children;
    }

    public void setChildren(List<NodesDto> children) {
        this.children = children;
    }

    public Integer getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Integer hasChildren) {
        this.hasChildren = hasChildren;
    }

}
