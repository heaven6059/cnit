package com.cnit.yoyo.model.site.dto;

import java.io.Serializable;
import java.util.List;

public class AdConfig implements Serializable {

	private static final long serialVersionUID = -2323170409118977795L;
	
	private List<PicInfo> content;
	private String intervalTime;
	
	public List<PicInfo> getContent() {
		return content;
	}
	public void setContent(List<PicInfo> content) {
		this.content = content;
	}
	public String getIntervalTime() {
		return intervalTime;
	}
	public void setIntervalTime(String intervalTime) {
		this.intervalTime = intervalTime;
	}
}
