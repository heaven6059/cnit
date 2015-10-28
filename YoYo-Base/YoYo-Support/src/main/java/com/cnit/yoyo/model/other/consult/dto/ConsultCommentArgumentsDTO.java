package com.cnit.yoyo.model.other.consult.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.other.consult.ConsultCommentArguments;

/**
 * @Title: ConsultCommentArguments.java
 * @Package com.cnit.yoyo.model.other.consult
 * @Description: 咨询评论设置
 * @Author 王鹏
 * @date 2015-4-28 上午9:25:37
 * @version V1.0
 */
public class ConsultCommentArgumentsDTO extends ConsultCommentArguments
		implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -863912801097121309L;
	private String[] pointTitle;
	private String[][] pointDesc;
	private Integer[] enabled;

	public String[] getPointTitle() {
		return pointTitle;
	}

	public void setPointTitle(String[] pointTitle) {
		this.pointTitle = pointTitle;
	}

	public String[][] getPointDesc() {
		return pointDesc;
	}

	public void setPointDesc(String[][] pointDesc) {
		this.pointDesc = pointDesc;
	}

	public Integer[] getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer[] enabled) {
		this.enabled = enabled;
	}

}