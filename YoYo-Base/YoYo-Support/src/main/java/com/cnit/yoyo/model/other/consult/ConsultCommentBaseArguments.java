package com.cnit.yoyo.model.other.consult;

import java.io.Serializable;

/**
 * @Title: ConsultCommentBaseArguments.java
 * @Package com.cnit.yoyo.model.other.consult
 * @Description: 咨询评论基础设置
 * @Author 王鹏
 * @date 2015-4-28 上午9:46:41
 * @version V1.0
 */
public class ConsultCommentBaseArguments implements Serializable {
	// t_consult_comment_base_arguments.id
	private Integer id;

	// t_consult_comment_base_arguments.initialize_num
	private Integer initializeNum;

	// t_consult_comment_base_arguments.display_lv
	private Integer displayLv;

	// t_consult_comment_base_arguments.switch_reply
	private Integer switchReply;

	// t_consult_comment_base_arguments.verifyCode
	private Integer verifycode;

	// t_consult_comment_base_arguments.display
	private Integer display;

	private static final long serialVersionUID = 1L;

	// t_consult_comment_base_arguments.id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getInitializeNum() {
		return initializeNum;
	}

	public void setInitializeNum(Integer initializeNum) {
		this.initializeNum = initializeNum;
	}

	public Integer getDisplayLv() {
		return displayLv;
	}

	public void setDisplayLv(Integer displayLv) {
		this.displayLv = displayLv;
	}

	public Integer getSwitchReply() {
		return switchReply;
	}

	public void setSwitchReply(Integer switchReply) {
		this.switchReply = switchReply;
	}

	public Integer getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(Integer verifycode) {
		this.verifycode = verifycode;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", initializeNum=").append(initializeNum);
		sb.append(", displayLv=").append(displayLv);
		sb.append(", switchReply=").append(switchReply);
		sb.append(", verifycode=").append(verifycode);
		sb.append(", display=").append(display);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}