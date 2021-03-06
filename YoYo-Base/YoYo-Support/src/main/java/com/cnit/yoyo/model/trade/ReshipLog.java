package com.cnit.yoyo.model.trade;

import java.io.Serializable;
import java.util.Date;

public class ReshipLog implements Serializable {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.reship_log_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private Integer reshipLogId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.reship_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private Long reshipId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.order_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private Long orderId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.op_name
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private String opName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.op_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private Integer opId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.op_user_type
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private Integer opUserType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.op_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private String opLog;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship_log.op_date
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */

	private Date opDate;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_reship_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.reship_log_id
	 * 
	 * @return the value of t_reship_log.reship_log_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public Integer getReshipLogId() {
		return reshipLogId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.reship_log_id
	 * 
	 * @param reshipLogId
	 *            the value for t_reship_log.reship_log_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setReshipLogId(Integer reshipLogId) {
		this.reshipLogId = reshipLogId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.reship_id
	 * 
	 * @return the value of t_reship_log.reship_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public Long getReshipId() {
		return reshipId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.reship_id
	 * 
	 * @param reshipId
	 *            the value for t_reship_log.reship_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setReshipId(Long reshipId) {
		this.reshipId = reshipId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.order_id
	 * 
	 * @return the value of t_reship_log.order_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.order_id
	 * 
	 * @param orderId
	 *            the value for t_reship_log.order_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.op_name
	 * 
	 * @return the value of t_reship_log.op_name
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public String getOpName() {
		return opName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.op_name
	 * 
	 * @param opName
	 *            the value for t_reship_log.op_name
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setOpName(String opName) {
		this.opName = opName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.op_id
	 * 
	 * @return the value of t_reship_log.op_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public Integer getOpId() {
		return opId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.op_id
	 * 
	 * @param opId
	 *            the value for t_reship_log.op_id
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setOpId(Integer opId) {
		this.opId = opId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.op_log
	 * 
	 * @return the value of t_reship_log.op_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public String getOpLog() {
		return opLog;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.op_log
	 * 
	 * @param opLog
	 *            the value for t_reship_log.op_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setOpLog(String opLog) {
		this.opLog = opLog;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship_log.op_date
	 * 
	 * @return the value of t_reship_log.op_date
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public Date getOpDate() {
		return opDate;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.op_date
	 * 
	 * @param opDate
	 *            the value for t_reship_log.op_date
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setOpDate(Date opDate) {
		this.opDate = opDate;
	}


	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.op_user_type
	 * 
	 * @param opDate
	 *            the value for t_reship_log.op_user_type
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public Integer getOpUserType() {
		return opUserType;
	}
	

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship_log.op_user_type
	 * 
	 * @param opDate
	 *            the value for t_reship_log.op_user_type
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	public void setOpUserType(Integer opUserType) {
		this.opUserType = opUserType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_reship_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", reshipLogId=").append(reshipLogId);
		sb.append(", reshipId=").append(reshipId);
		sb.append(", orderId=").append(orderId);
		sb.append(", opName=").append(opName);
		sb.append(", opId=").append(opId);
		sb.append(", opLog=").append(opLog);
		sb.append(", opDate=").append(opDate);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_reship_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		ReshipLog other = (ReshipLog) that;
		return (this.getReshipLogId() == null ? other.getReshipLogId() == null
				: this.getReshipLogId().equals(other.getReshipLogId()))
				&& (this.getReshipId() == null ? other.getReshipId() == null
						: this.getReshipId().equals(other.getReshipId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null
						: this.getOrderId().equals(other.getOrderId()))
				&& (this.getOpName() == null ? other.getOpName() == null : this
						.getOpName().equals(other.getOpName()))
				&& (this.getOpId() == null ? other.getOpId() == null : this
						.getOpId().equals(other.getOpId()))
				&& (this.getOpLog() == null ? other.getOpLog() == null : this
						.getOpLog().equals(other.getOpLog()))
				&& (this.getOpDate() == null ? other.getOpDate() == null : this
						.getOpDate().equals(other.getOpDate()))
				&& (this.getOpUserType() == null ? other.getOpUserType() == null : this
						.getOpUserType().equals(other.getOpUserType()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_reship_log
	 * 
	 * @mbggenerated Fri Apr 17 10:01:17 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((getReshipLogId() == null) ? 0 : getReshipLogId().hashCode());
		result = prime * result
				+ ((getReshipId() == null) ? 0 : getReshipId().hashCode());
		result = prime * result
				+ ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result
				+ ((getOpName() == null) ? 0 : getOpName().hashCode());
		result = prime * result
				+ ((getOpId() == null) ? 0 : getOpId().hashCode());
		result = prime * result
				+ ((getOpLog() == null) ? 0 : getOpLog().hashCode());
		result = prime * result
				+ ((getOpDate() == null) ? 0 : getOpDate().hashCode());
		result = prime * result
				+ ((getOpUserType() == null) ? 0 : getOpUserType().hashCode());
		return result;
	}
}