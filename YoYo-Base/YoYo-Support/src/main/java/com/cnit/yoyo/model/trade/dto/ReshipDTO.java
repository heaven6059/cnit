package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.trade.ReshipLog;
import com.cnit.yoyo.util.OrderUtils;

public class ReshipDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8734922546838309247L;
	private Long reshipId;// 退货Id
	private Long orderId;// 订单Id
	private Integer refundStatus;// 订单退货状态
	private String refundText;// 订单状态文本
	private Date reshipDate;// 退货时间
	private String refundReason;// 退款原因
	private String memo;// 退货备注
	private Integer status;// 处理状态
	private BigDecimal refundAmount;// 退款金额
	private List<ReshipLog> reshipLogs;// 退款日志表
	private List<ReshipItemsDTO> reshipItemsDTOs;

	public Long getReshipId() {
		return reshipId;
	}

	public void setReshipId(Long reshipId) {
		this.reshipId = reshipId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public void setRefundText(String refundText) {
		this.refundText = refundText;
	}

	public Date getReshipDate() {
		return reshipDate;
	}

	public void setReshipDate(Date reshipDate) {
		this.reshipDate = reshipDate;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public BigDecimal getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(BigDecimal refundAmount) {
		this.refundAmount = refundAmount;
	}

	public List<ReshipItemsDTO> getReshipItemsDTOs() {
		return reshipItemsDTOs;
	}

	public void setReshipItemsDTOs(List<ReshipItemsDTO> reshipItemsDTOs) {
		this.reshipItemsDTOs = reshipItemsDTOs;
	}

	public List<ReshipLog> getReshipLogs() {
		return reshipLogs;
	}

	public void setReshipLogs(List<ReshipLog> reshipLogs) {
		this.reshipLogs = reshipLogs;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @description 获取付款状态文文本
	 * @detail <方法详细描述>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-4-16
	 * @param
	 * @return
	 */
	public String getRefundText() {
		if(null==this.refundStatus)return "";
		try{
			return OrderUtils.getPropertyValue(this.refundStatus.toString());
		}catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

}
