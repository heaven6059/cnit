package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesReason;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRequire;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.model.order.OrderItems;
import com.cnit.yoyo.model.trade.AftersalesReturnLogWithBLOBs;

public class RefundsProductDTO implements Serializable {
	private static final long serialVersionUID = 8800220588978007286L;
	private Long returnId;
	private Long orderId;
	private Integer safeguardRequire;
	private Integer safeguardType;
	private Integer isSafeguard;
	private String sellerReason;
	private String interevenImage;
	private String interevenComment;
	private Date addTime;
	private String content;
	private String memberImagePath;
	private String memberName;
	private String sellerComment;
	private String memberPhone;
	private BigDecimal amount;
	private Integer applyNums;
	private Integer status;
	private String refundAddress;
	private Long goodsId;
	private Integer appeal;
	private Integer appealReturnId;
	private List<OrderItems> orderItems;
	private List<AftersalesReturnLogWithBLOBs> logs;

	private String tradeNo;

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
		if (null == this.getStatus())
			return "";
		try {
			return AfterSalesStatus.getAfterSalesStausText(this.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @description <b>获取退货原因文本</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-6-1
	 * @param @return
	 * @return String
	 */
	public String getAfterSalesReasonText() {
		if (null == this.getSafeguardType())
			return "";
		return AfterSalesReason.getAfterSalesReason(this.getSafeguardType());
	}

	/**
	 * @description <b>售后服务要求文本</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月27日
	 * @return String
	 */
	public String getSafeguardRequireText() {
		if (null == this.getSafeguardRequire())
			return "";
		return AfterSalesRequire.getAfterSalesRequire(this.getSafeguardRequire());
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getSafeguardRequire() {
		return safeguardRequire;
	}

	public void setSafeguardRequire(Integer safeguardRequire) {
		this.safeguardRequire = safeguardRequire;
	}

	public Integer getSafeguardType() {
		return safeguardType;
	}

	public void setSafeguardType(Integer safeguardType) {
		this.safeguardType = safeguardType;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMemberImagePath() {
		return memberImagePath;
	}

	public void setMemberImagePath(String memberImagePath) {
		this.memberImagePath = memberImagePath;
	}

	public Long getReturnId() {
		return returnId;
	}

	public void setReturnId(Long returnId) {
		this.returnId = returnId;
	}

	public Integer getIsSafeguard() {
		return isSafeguard;
	}

	public void setIsSafeguard(Integer isSafeguard) {
		this.isSafeguard = isSafeguard;
	}

	public String getSellerReason() {
		return sellerReason;
	}

	public void setSellerReason(String sellerReason) {
		this.sellerReason = sellerReason;
	}

	public String getInterevenImage() {
		return interevenImage;
	}

	public void setInterevenImage(String interevenImage) {
		this.interevenImage = interevenImage;
	}

	public String getRefundAddress() {
		return refundAddress;
	}

	public void setRefundAddress(String refundAddress) {
		this.refundAddress = refundAddress;
	}

	public String getSellerComment() {
		return sellerComment;
	}

	public void setSellerComment(String sellerComment) {
		this.sellerComment = sellerComment;
	}

	public List<AftersalesReturnLogWithBLOBs> getLogs() {
		return logs;
	}

	public void setLogs(List<AftersalesReturnLogWithBLOBs> logs) {
		this.logs = logs;
	}

	public String getInterevenComment() {
		return interevenComment;
	}

	public void setInterevenComment(String interevenComment) {
		this.interevenComment = interevenComment;
	}

	public Integer getApplyNums() {
		return applyNums;
	}

	public void setApplyNums(Integer applyNums) {
		this.applyNums = applyNums;
	}

	public Integer getAppeal() {
		return appeal;
	}

	public void setAppeal(Integer appeal) {
		this.appeal = appeal;
	}

	public Integer getAppealReturnId() {
		return appealReturnId;
	}

	public void setAppealReturnId(Integer appealReturnId) {
		this.appealReturnId = appealReturnId;
	}

}
