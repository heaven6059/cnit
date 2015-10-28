package com.cnit.yoyo.model.article.dto;

import java.util.Date;

import com.cnit.yoyo.model.article.ArticleBodyWithBLOBs;

public class ArticleDto extends ArticleBodyWithBLOBs{

	private static final long serialVersionUID = -1117195322027026094L;

	private Integer articleId;
	
	private String title;
	
	private String platform;
	
	private Integer type;
	
	private Integer nodeId;
	
	private String nodeName;
	
	private String author;
	
	private Date pubtime;

	private Date uptime;

	private Integer level;

	private Integer ifpub;

	private Integer pv;

	private Integer disabled;

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPubtime() {
		return pubtime;
	}

	public void setPubtime(Date pubtime) {
		this.pubtime = pubtime;
	}

	public Date getUptime() {
		return uptime;
	}

	public void setUptime(Date uptime) {
		this.uptime = uptime;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getIfpub() {
		return ifpub;
	}

	public void setIfpub(Integer ifpub) {
		this.ifpub = ifpub;
	}

	public Integer getPv() {
		return pv;
	}

	public void setPv(Integer pv) {
		this.pv = pv;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	
	
    
    
}
