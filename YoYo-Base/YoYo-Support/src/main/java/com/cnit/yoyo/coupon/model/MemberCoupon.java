package com.cnit.yoyo.coupon.model;

import java.io.Serializable;
import java.util.Date;

public class MemberCoupon implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -152649430966973957L;

	private Long memcCode;

	private Integer memberId;

	private Long memcGenOrderid;

	private Integer memcSource;

	private Integer memcEnabled;

	private Integer memcUsedTimes;

	private Integer memcGenTime;

	private Integer disabled;

	private Integer cpnsId;

	private Integer memcIsvalid;
	private Integer status;
	private Date createTime;
	private Date payTime;
	

	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getMemcCode() {
		return memcCode;
	}

	public void setMemcCode(Long memcCode) {
		this.memcCode = memcCode;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Long getMemcGenOrderid() {
		return memcGenOrderid;
	}

	public void setMemcGenOrderid(Long memcGenOrderid) {
		this.memcGenOrderid = memcGenOrderid;
	}

	public Integer getMemcSource() {
		return memcSource;
	}

	public void setMemcSource(Integer memcSource) {
		this.memcSource = memcSource;
	}

	public Integer getMemcEnabled() {
		return memcEnabled;
	}

	public void setMemcEnabled(Integer memcEnabled) {
		this.memcEnabled = memcEnabled;
	}

	public Integer getMemcUsedTimes() {
		return memcUsedTimes;
	}

	public void setMemcUsedTimes(Integer memcUsedTimes) {
		this.memcUsedTimes = memcUsedTimes;
	}

	public Integer getMemcGenTime() {
		return memcGenTime;
	}

	public void setMemcGenTime(Integer memcGenTime) {
		this.memcGenTime = memcGenTime;
	}

	public Integer getDisabled() {
		return disabled;
	}

	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	public Integer getCpnsId() {
		return cpnsId;
	}

	public void setCpnsId(Integer cpnsId) {
		this.cpnsId = cpnsId;
	}

	public Integer getMemcIsvalid() {
		return memcIsvalid;
	}

	public void setMemcIsvalid(Integer memcIsvalid) {
		this.memcIsvalid = memcIsvalid;
	}

}