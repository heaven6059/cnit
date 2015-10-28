package com.cnit.yoyo.model.painting.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.cnit.yoyo.dto.BaseQryDTO;

public class PaintingOrderDTO extends BaseQryDTO implements Serializable {

	private static final long serialVersionUID = 5442482586644619104L;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date startDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date endDate;
	private BigDecimal minPayed;
	private BigDecimal maxPayed;
	private String storeName;
	private Integer paymentId;
	private String name;
	private Integer pageNum;
	private Integer pageSize;
	//会员ID
	private Integer memberId;
	private Integer storeId;
	//订单时间范围
	@DateTimeFormat(pattern="yyyy-MM-dd")  
	private Date createtime;
	//支付状态
	private String payStatus;
	//订单状态
	private String status;
	//是否已删除
	private String disabled;
	
	private boolean beforeOneYear;
	
	private String id;
	
	//分页时的订单ID列表
	private List<String> ids;
	
	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public boolean isBeforeOneYear() {
		return beforeOneYear;
	}

	public void setBeforeOneYear(boolean beforeOneYear) {
		this.beforeOneYear = beforeOneYear;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getMinPayed() {
		return minPayed;
	}

	public void setMinPayed(BigDecimal minPayed) {
		this.minPayed = minPayed;
	}

	public BigDecimal getMaxPayed() {
		return maxPayed;
	}

	public void setMaxPayed(BigDecimal maxPayed) {
		this.maxPayed = maxPayed;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
