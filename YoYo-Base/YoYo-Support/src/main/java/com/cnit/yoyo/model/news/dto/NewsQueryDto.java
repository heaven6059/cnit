package com.cnit.yoyo.model.news.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cnit.yoyo.dto.BaseQryDTO;

public class NewsQueryDto extends BaseQryDTO implements Serializable{
	
	private static final long serialVersionUID = -6926338590254536759L;
	
	private Integer newsId;
	
	private String title;
	
	private Boolean type;
	
	private String author;
	
	private Date pubtime;
	
	private Date lastModify;
	
	private Boolean hasContent;
	
	private Boolean ifpub;
	
	private Boolean disabled;
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date startDate;
	
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date endDate;

	private List<Integer> ids;
	
	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getType() {
		return type;
	}

	public void setType(Boolean type) {
		this.type = type;
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

	public Date getLastModify() {
		return lastModify;
	}

	public void setLastModify(Date lastModify) {
		this.lastModify = lastModify;
	}

	public Boolean getHasContent() {
		return hasContent;
	}

	public void setHasContent(Boolean hasContent) {
		this.hasContent = hasContent;
	}

	public Boolean getIfpub() {
		return ifpub;
	}

	public void setIfpub(Boolean ifpub) {
		this.ifpub = ifpub;
	}

	public Boolean getDisabled() {
		return disabled;
	}

	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
