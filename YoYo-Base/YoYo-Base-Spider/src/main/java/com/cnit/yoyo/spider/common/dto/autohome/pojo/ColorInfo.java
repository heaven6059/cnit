package com.cnit.yoyo.spider.common.dto.autohome.pojo;

import java.io.Serializable;

public class ColorInfo implements Serializable {
	private static final long serialVersionUID = 7746584011120435011L;

	private Long id;

	private String name;// 颜色名称
	private String val;// 颜色值
	private Integer picnum;
	private Integer clubPicnum;

	public ColorInfo() {
		super();
	}

	public ColorInfo(Long id, String name, String val, Integer picnum, Integer clubPicnum) {
		super();
		this.id = id;
		this.name = name;
		this.val = val;
		this.picnum = picnum;
		this.clubPicnum = clubPicnum;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	public Integer getClubPicnum() {
		return clubPicnum;
	}

	public void setClubPicnum(Integer clubPicnum) {
		this.clubPicnum = clubPicnum;
	}

}
