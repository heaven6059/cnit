package com.cnit.yoyo.model.other.consult;

import java.io.Serializable;

/**
 * @Title: ConsultCommentArguments.java
 * @Package com.cnit.yoyo.model.other.consult
 * @Description: 咨询评论设置
 * @Author 王鹏
 * @date 2015-4-28 上午9:25:37
 * @version V1.0
 */
public class ConsultCommentArguments implements Serializable {
	/**
	 * 
	 * t_consult_comment_arguments.id
	 */
	private Integer id;

	/**
	 * 
	 * t_consult_comment_arguments.open_discuss
	 * 
	 */
	private Integer openDiscuss;

	/**
	 * 
	 * t_consult_comment_arguments.send_permission
	 * 
	 */
	private Integer sendPermission;

	/**
	 * 
	 * t_consult_comment_arguments.default_notic
	 * 
	 */
	private String defaultNotic;

	/**
	 * 
	 * t_consult_comment_arguments.wait_check_notic
	 * 
	 */
	private String waitCheckNotic;

	/**
	 * 
	 * t_consult_comment_arguments.send_suceess_notic
	 * 
	 */
	private String sendSuceessNotic;

	/**
	 * 
	 * t_consult_comment_arguments.send_notic
	 * 
	 */
	private String sendNotic;

	/**
	 * 
	 * t_consult_comment_arguments.goods_discuss_notice
	 * 
	 */
	private String goodsDiscussNotice;

	/**
	 * 
	 * t_consult_comment_arguments.success_point
	 * 
	 */
	private Integer successPoint;

	/**
	 * t_consult_comment_arguments.open_point_status
	 */
	private Integer openPointStatus;

	/**
	 * t_consult_comment_arguments.args_type
	 */

	private String argsType;

	/**
	 * This field corresponds to the database table t_consult_comment_arguments
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the value of t_consult_comment_arguments.id
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the value for t_consult_comment_arguments.id
	 * 
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the value of t_consult_comment_arguments.initialize_num
	 * 
	 */
	public Integer getOpenDiscuss() {
		return openDiscuss;
	}

	/**
	 * @param initializeNum
	 *            the value for t_consult_comment_arguments.initialize_num
	 * 
	 */
	public void setOpenDiscuss(Integer openDiscuss) {
		this.openDiscuss = openDiscuss;
	}

	/**
	 * @return the value of t_consult_comment_arguments.send_permission
	 * 
	 */
	public Integer getSendPermission() {
		return sendPermission;
	}

	/**
	 * @param sendPermission
	 *            the value for t_consult_comment_arguments.send_permission
	 * 
	 */
	public void setSendPermission(Integer sendPermission) {
		this.sendPermission = sendPermission;
	}

	/**
	 * @return the value of t_consult_comment_arguments.default_notic
	 * 
	 */
	public String getDefaultNotic() {
		return defaultNotic;
	}

	/**
	 * @param defaultNotic
	 *            the value for t_consult_comment_arguments.default_notic
	 * 
	 */
	public void setDefaultNotic(String defaultNotic) {
		this.defaultNotic = defaultNotic;
	}

	/**
	 * @return the value of t_consult_comment_arguments.wait_check_notic
	 * 
	 */
	public String getWaitCheckNotic() {
		return waitCheckNotic;
	}

	/**
	 * @param waitCheckNotic
	 *            the value for t_consult_comment_arguments.wait_check_notic
	 * 
	 */
	public void setWaitCheckNotic(String waitCheckNotic) {
		this.waitCheckNotic = waitCheckNotic;
	}

	/**
	 * @return the value of t_consult_comment_arguments.send_suceess_notic
	 * 
	 */
	public String getSendSuceessNotic() {
		return sendSuceessNotic;
	}

	/**
	 * @param sendSuceessNotic
	 *            the value for t_consult_comment_arguments.send_suceess_notic
	 * 
	 */
	public void setSendSuceessNotic(String sendSuceessNotic) {
		this.sendSuceessNotic = sendSuceessNotic;
	}

	/**
	 * @return the value of t_consult_comment_arguments.send_notic
	 * 
	 */
	public String getSendNotic() {
		return sendNotic;
	}

	/**
	 * @param sendNotic
	 *            the value for t_consult_comment_arguments.send_notic
	 * 
	 */
	public void setSendNotic(String sendNotic) {
		this.sendNotic = sendNotic;
	}

	/**
	 * @return the value of t_consult_comment_arguments.goods_discuss_notice
	 * 
	 */
	public String getGoodsDiscussNotice() {
		return goodsDiscussNotice;
	}

	/**
	 * @param goodsDiscussNotice
	 *            the value for t_consult_comment_arguments.goods_discuss_notice
	 * 
	 */
	public void setGoodsDiscussNotice(String goodsDiscussNotice) {
		this.goodsDiscussNotice = goodsDiscussNotice;
	}

	/**
	 * @return the value of t_consult_comment_arguments.success_point
	 * 
	 */
	public Integer getSuccessPoint() {
		return successPoint;
	}

	/**
	 * @param successPoint
	 *            the value for t_consult_comment_arguments.success_point
	 * 
	 */
	public void setSuccessPoint(Integer successPoint) {
		this.successPoint = successPoint;
	}

	/**
	 * @return the value of t_consult_comment_arguments.open_point_status
	 * 
	 */
	public Integer getOpenPointStatus() {
		return openPointStatus;
	}

	/**
	 * @param openPointStatus
	 *            the value for t_consult_comment_arguments.open_point_status
	 * 
	 */
	public void setOpenPointStatus(Integer openPointStatus) {
		this.openPointStatus = openPointStatus;
	}

	public String getArgsType() {
		return argsType;
	}

	public void setArgsType(String argsType) {
		this.argsType = argsType;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", openDiscuss=").append(openDiscuss);
		sb.append(", sendPermission=").append(sendPermission);
		sb.append(", defaultNotic=").append(defaultNotic);
		sb.append(", waitCheckNotic=").append(waitCheckNotic);
		sb.append(", sendSuceessNotic=").append(sendSuceessNotic);
		sb.append(", sendNotic=").append(sendNotic);
		sb.append(", goodsDiscussNotice=").append(goodsDiscussNotice);
		sb.append(", successPoint=").append(successPoint);
		sb.append(", openPointStatus=").append(openPointStatus);
		sb.append(", argsType=").append(argsType);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}