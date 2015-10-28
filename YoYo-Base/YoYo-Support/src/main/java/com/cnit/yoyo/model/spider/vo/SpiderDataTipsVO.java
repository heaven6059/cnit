package com.cnit.yoyo.model.spider.vo;  

import java.util.ArrayList;
import java.util.List;
  
public class SpiderDataTipsVO {
	private String name;//名称
	private String url;//url
	private List detailConfig;//车型明细
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List getDetailConfig() {
		return detailConfig;
	}
	public void setDetailConfig(List detailConfig) {
		this.detailConfig = detailConfig;
	}
	
	public void addDetailConfigs(Object obj){
		if(detailConfig == null){
			detailConfig=new ArrayList<>();
		}
		detailConfig.add(obj);
	}
	
}
