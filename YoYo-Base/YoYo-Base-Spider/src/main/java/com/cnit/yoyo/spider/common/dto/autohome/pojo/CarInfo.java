package com.cnit.yoyo.spider.common.dto.autohome.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class CarInfo implements Serializable {
	private static final long serialVersionUID = 3679449955257897668L;

	private String id;
	private String name;
	private String pid;
	private String imgPath;
	private String autohomeUrl; // 汽车之家
	private Date createTime;
	private Date updateTime;
	private Integer scopeId;
	private String madeId;//国产1，进口2
	private String year;//年款
	private String level;//级别
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

	public String getImgPath() {
		if(StringUtils.isEmpty(imgPath)){
			imgPath = "";
		}
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getAutohomeUrl() {
		return autohomeUrl;
	}

	public void setAutohomeUrl(String autohomeUrl) {
		this.autohomeUrl = autohomeUrl;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

}