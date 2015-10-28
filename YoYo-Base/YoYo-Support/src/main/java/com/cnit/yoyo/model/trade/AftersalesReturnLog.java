package com.cnit.yoyo.model.trade;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.util.OrderUtils;

public class AftersalesReturnLog implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.log_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Long logId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.order_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Long orderId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.return_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Long returnId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.op_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Integer opId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.op_name
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private String opName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.alttime
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Date alttime;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.behavior
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Integer behavior;
	
	private String behaviorText;
	
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.result
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Integer result;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.role
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private Integer role;
	
	
	public String getRoleText(){		
		return AfterSalesRole.getAfterSalesRoleText(this.role);
	}
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_aftersales_return_log.image_file
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private String imageFile;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_aftersales_return_log
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.log_id
	 * @return  the value of t_aftersales_return_log.log_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Long getLogId() {
		return logId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.log_id
	 * @param logId  the value for t_aftersales_return_log.log_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setLogId(Long logId) {
		this.logId = logId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.order_id
	 * @return  the value of t_aftersales_return_log.order_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.order_id
	 * @param orderId  the value for t_aftersales_return_log.order_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.return_id
	 * @return  the value of t_aftersales_return_log.return_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Long getReturnId() {
		return returnId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.return_id
	 * @param returnId  the value for t_aftersales_return_log.return_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.op_id
	 * @return  the value of t_aftersales_return_log.op_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Integer getOpId() {
		return opId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.op_id
	 * @param opId  the value for t_aftersales_return_log.op_id
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setOpId(Integer opId) {
		this.opId = opId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.op_name
	 * @return  the value of t_aftersales_return_log.op_name
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public String getOpName() {
		return opName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.op_name
	 * @param opName  the value for t_aftersales_return_log.op_name
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setOpName(String opName) {
		this.opName = opName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.alttime
	 * @return  the value of t_aftersales_return_log.alttime
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Date getAlttime() {
		return alttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.alttime
	 * @param alttime  the value for t_aftersales_return_log.alttime
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setAlttime(Date alttime) {
		this.alttime = alttime;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.behavior
	 * @return  the value of t_aftersales_return_log.behavior
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Integer getBehavior() {
		return behavior;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.behavior
	 * @param behavior  the value for t_aftersales_return_log.behavior
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setBehavior(Integer behavior) {
		this.behavior = behavior;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.result
	 * @return  the value of t_aftersales_return_log.result
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Integer getResult() {
		return result;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.result
	 * @param result  the value for t_aftersales_return_log.result
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setResult(Integer result) {
		this.result = result;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.role
	 * @return  the value of t_aftersales_return_log.role
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public Integer getRole() {
		return role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.role
	 * @param role  the value for t_aftersales_return_log.role
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setRole(Integer role) {
		this.role = role;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_aftersales_return_log.image_file
	 * @return  the value of t_aftersales_return_log.image_file
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public String getImageFile() {
		return imageFile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_aftersales_return_log.image_file
	 * @param imageFile  the value for t_aftersales_return_log.image_file
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	
	
	

	public String getBehaviorText() {
		if(null==this.behavior){
			return "";
		}else{
			try{
				return OrderUtils.getPropertyValue(this.getBehavior().toString());
			}catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
	}

	public void setBehaviorText(String behaviorText) {
		this.behaviorText = behaviorText;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_aftersales_return_log
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", logId=").append(logId);
		sb.append(", orderId=").append(orderId);
		sb.append(", returnId=").append(returnId);
		sb.append(", opId=").append(opId);
		sb.append(", opName=").append(opName);
		sb.append(", alttime=").append(alttime);
		sb.append(", behavior=").append(behavior);
		sb.append(", result=").append(result);
		sb.append(", role=").append(role);
		sb.append(", imageFile=").append(imageFile);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_aftersales_return_log
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
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
		AftersalesReturnLog other = (AftersalesReturnLog) that;
		return (this.getLogId() == null ? other.getLogId() == null : this
				.getLogId().equals(other.getLogId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null
						: this.getOrderId().equals(other.getOrderId()))
				&& (this.getReturnId() == null ? other.getReturnId() == null
						: this.getReturnId().equals(other.getReturnId()))
				&& (this.getOpId() == null ? other.getOpId() == null : this
						.getOpId().equals(other.getOpId()))
				&& (this.getOpName() == null ? other.getOpName() == null : this
						.getOpName().equals(other.getOpName()))
				&& (this.getAlttime() == null ? other.getAlttime() == null
						: this.getAlttime().equals(other.getAlttime()))
				&& (this.getBehavior() == null ? other.getBehavior() == null
						: this.getBehavior().equals(other.getBehavior()))
				&& (this.getResult() == null ? other.getResult() == null : this
						.getResult().equals(other.getResult()))
				&& (this.getRole() == null ? other.getRole() == null : this
						.getRole().equals(other.getRole()))
				&& (this.getImageFile() == null ? other.getImageFile() == null
						: this.getImageFile().equals(other.getImageFile()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_aftersales_return_log
	 * @mbggenerated  Sat Apr 18 17:01:00 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getLogId() == null) ? 0 : getLogId().hashCode());
		result = prime * result
				+ ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result
				+ ((getReturnId() == null) ? 0 : getReturnId().hashCode());
		result = prime * result
				+ ((getOpId() == null) ? 0 : getOpId().hashCode());
		result = prime * result
				+ ((getOpName() == null) ? 0 : getOpName().hashCode());
		result = prime * result
				+ ((getAlttime() == null) ? 0 : getAlttime().hashCode());
		result = prime * result
				+ ((getBehavior() == null) ? 0 : getBehavior().hashCode());
		result = prime * result
				+ ((getResult() == null) ? 0 : getResult().hashCode());
		result = prime * result
				+ ((getRole() == null) ? 0 : getRole().hashCode());
		result = prime * result
				+ ((getImageFile() == null) ? 0 : getImageFile().hashCode());
		return result;
	}
}