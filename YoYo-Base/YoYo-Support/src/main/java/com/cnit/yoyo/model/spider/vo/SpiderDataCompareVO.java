package com.cnit.yoyo.model.spider.vo;  

import java.util.Date;
import java.util.List;
  
public class SpiderDataCompareVO {
	private String id;
	private String name;
	private String pid;
	private String url;
	private Date createTime;
	private Integer scopeId;
	private String madeId;
	private String year;
	private String level;
	private List detailConfig;//车型明细
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getScopeId() {
		return scopeId;
	}
	public void setScopeId(Integer scopeId) {
		this.scopeId = scopeId;
	}
	public String getMadeId() {
		return madeId;
	}
	public void setMadeId(String madeId) {
		this.madeId = madeId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public List getDetailConfig() {
		return detailConfig;
	}
	public void setDetailConfig(List detailConfig) {
		this.detailConfig = detailConfig;
	}
	
}
