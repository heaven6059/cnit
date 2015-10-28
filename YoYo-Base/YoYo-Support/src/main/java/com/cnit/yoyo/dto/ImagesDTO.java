package com.cnit.yoyo.dto;

import java.io.Serializable;

/**
 * 上传图片返回实体
 * 
 * @author wanghb
 * @date 2015年5月12日
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class ImagesDTO implements Serializable{

	private static final long serialVersionUID = -1343628161295367181L;
	
	private String fileName;// 文件名称
	private String suffix;//文件后缀

	
	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
