package com.cnit.yoyo.model.car.dto;

import java.util.List;


public class SpiderDataDTO{

	private static final long serialVersionUID = 1937988456084861550L;
	
	private String name;
	
	private String imgPath;
	
	private String url;
	
	private List<SpiderConfigDataDTO> detailConfig;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<SpiderConfigDataDTO> getDetailConfig() {
		return detailConfig;
	}

	public void setDetailConfig(List<SpiderConfigDataDTO> detailConfig) {
		this.detailConfig = detailConfig;
	}

	
}
