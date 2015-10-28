package com.cnit.yoyo.model.order;


import org.apache.commons.lang.StringUtils;

import com.cnit.yoyo.commons.page.Page;

public class PaymentLog extends Page implements java.io.Serializable {

	private static final long serialVersionUID = 2781918003077734490L;

	private Long orderId;// 订单号
	private String tradeNo;// 支付宝交易号
	private String paymentType;// 支付类型对应请求时的payment_type参数，原样返回
	private String exterface;// 接口名称
	private String tradeStatus;// 交易状态,TRADE_FINISHED（普通即时到账的交易成功状态）
	private String notifyId;// 商户可以用这个流水号询问支付宝该条通知的合法性
	private String notifyTime;
	private String sellerEmail;// 卖家支付宝账号
	private String buyerEmail;// 买家支付宝账号
	private String sellerId;// 卖家支付宝账户号
	private String buyerId;// 买家支付宝账户号
	private Double totalFee;// 交易金额
	private String isSuccess;// 表示该次操作是否成功
	private String signType;// 签名方式
	private String sign;// 签名
	private String createTime;
	private String returnid;

	private String out_trade_no;
	private String trade_no;
	private String payment_type;
	private String trade_status;
	private String notify_id;
	private String notify_time;
	private String seller_email;
	private String buyer_email;
	private String seller_id;
	private String buyer_id;
	private String total_fee;
	private String is_success;
	private String sign_type;
	private String type;
	private Long companyId;
	/** 集团ID */
	private String shopName;
	/** 卖家名称 */
	private Integer memberId;
	private String name;
	private String startDate;
	private String endDate;
	private String nums;//总商品数
	private String storeId;
	private String totalAmount;
	private String finalAmount;
	private String refundsFeel;
	
	
	public String getRefundsFeel() {
		return refundsFeel;
	}

	public void setRefundsFeel(String refundsFeel) {
		this.refundsFeel = refundsFeel;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(String finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getNums() {
		return nums;
	}

	public void setNums(String nums) {
		this.nums = nums;
	}

	public String getStartDate() {
		if(StringUtils.isNotBlank(startDate)){
			return startDate+" 00:00:00";
		}
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if(StringUtils.isNotBlank(endDate)){
			return endDate+" 23:59:59";
		}
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOrderId() {
		if (StringUtils.isNotBlank(getOut_trade_no())) {
			return Long.parseLong(getOut_trade_no());
		}
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTradeNo() {
		if (StringUtils.isNotBlank(getTrade_no())) {
			return getTrade_no();
		}
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getPaymentType() {
		if (StringUtils.isNotBlank(getPayment_type())) {
			return getPayment_type();
		}
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getExterface() {
		return exterface;
	}

	public void setExterface(String exterface) {
		this.exterface = exterface;
	}

	public String getTradeStatus() {
		if (StringUtils.isNotBlank(getTrade_status())) {
			return getTrade_status();
		}
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getNotifyId() {
		if (StringUtils.isNotBlank(getNotify_id())) {
			return getNotify_id();
		}
		return notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getNotifyTime() {
		if (StringUtils.isNotBlank(getNotify_time())) {
			return getNotify_time();
		}
		return notifyTime;
	}

	public void setNotifyTime(String notifyTime) {
		this.notifyTime = notifyTime;
	}

	public String getSellerEmail() {
		if (StringUtils.isNotBlank(getSeller_email())) {
			return getSeller_email();
		}
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getBuyerEmail() {
		if (StringUtils.isNotBlank(getBuyer_email())) {
			return getBuyer_email();
		}
		return buyerEmail;
	}

	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}

	public String getSellerId() {
		if (StringUtils.isNotBlank(getSeller_id())) {
			return getSeller_id();
		}
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerId() {
		if (StringUtils.isNotBlank(getBuyer_id())) {
			return getBuyer_id();
		}
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public Double getTotalFee() {
		if (null != getTotal_fee()) {
			return Double.valueOf(getTotal_fee()).doubleValue();
		}
		return totalFee;
	}

	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}

	public String getIsSuccess() {
		if (StringUtils.isNotBlank(getIs_success())) {
			return getIs_success();
		}
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getSignType() {
		if (StringUtils.isNotBlank(getSign_type())) {
			return getSign_type();
		}
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getReturnid() {
		return returnid;
	}

	public void setReturnid(String returnid) {
		this.returnid = returnid;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", orderId=").append(orderId);
		sb.append(", tradeNo=").append(tradeNo);
		sb.append(", paymentType=").append(paymentType);
		sb.append(", exterface=").append(exterface);
		sb.append(", tradeStatus=").append(tradeStatus);
		sb.append(", notifyId=").append(notifyId);
		sb.append(", notifyTime=").append(notifyTime);
		sb.append(", sellerEmail=").append(sellerEmail);
		sb.append(", buyerEmail=").append(buyerEmail);
		sb.append(", sellerId=").append(sellerId);
		sb.append(", buyerId=").append(buyerId);
		sb.append(", totalFee=").append(totalFee);
		sb.append(", isSuccess=").append(isSuccess);
		sb.append(", signType=").append(signType);
		sb.append(", sign=").append(sign);
		sb.append(", startDate=").append(startDate);
		sb.append(", endDate=").append(endDate);
		sb.append(", returnid=").append(returnid);
		sb.append("]");
		return sb.toString();
	}

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
		PaymentLog other = (PaymentLog) that;
		return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId())) && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo())) && (this.getPaymentType() == null ? other.getPaymentType() == null : this.getPaymentType().equals(other.getPaymentType())) && (this.getExterface() == null ? other.getExterface() == null : this.getExterface().equals(other.getExterface())) && (this.getTradeStatus() == null ? other.getTradeStatus() == null : this.getTradeStatus().equals(other.getTradeStatus())) && (this.getNotifyId() == null ? other.getNotifyId() == null : this.getNotifyId().equals(other.getNotifyId()))
				&& (this.getNotifyTime() == null ? other.getNotifyTime() == null : this.getNotifyTime().equals(other.getNotifyTime())) && (this.getSellerEmail() == null ? other.getSellerEmail() == null : this.getSellerEmail().equals(other.getSellerEmail())) && (this.getBuyerEmail() == null ? other.getBuyerEmail() == null : this.getBuyerEmail().equals(other.getBuyerEmail())) && (this.getSellerId() == null ? other.getSellerId() == null : this.getSellerId().equals(other.getSellerId())) && (this.getBuyerId() == null ? other.getBuyerId() == null : this.getBuyerId().equals(other.getBuyerId())) && (this.getTotalFee() == null ? other.getTotalFee() == null : this.getTotalFee().equals(other.getTotalFee()))
				&& (this.getIsSuccess() == null ? other.getIsSuccess() == null : this.getIsSuccess().equals(other.getIsSuccess()))
				&& (this.getStartDate() == null ? other.getStartDate() == null : this.getStartDate().equals(other.getStartDate()))
				&& (this.getEndDate() == null ? other.getEndDate() == null : this.getEndDate().equals(other.getEndDate()))
				&& (this.getSignType() == null ? other.getSignType() == null : this.getSignType().equals(other.getSignType())) && (this.getSign() == null ? other.getSign() == null : this.getSign().equals(other.getSign())) && (this.getReturnid() == null ? other.getReturnid() == null : this.getReturnid().equals(other.getReturnid()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
		result = prime * result + ((getPaymentType() == null) ? 0 : getPaymentType().hashCode());
		result = prime * result + ((getExterface() == null) ? 0 : getExterface().hashCode());
		result = prime * result + ((getTradeStatus() == null) ? 0 : getTradeStatus().hashCode());
		result = prime * result + ((getNotifyId() == null) ? 0 : getNotifyId().hashCode());
		result = prime * result + ((getNotifyTime() == null) ? 0 : getNotifyTime().hashCode());
		result = prime * result + ((getSellerEmail() == null) ? 0 : getSellerEmail().hashCode());
		result = prime * result + ((getBuyerEmail() == null) ? 0 : getBuyerEmail().hashCode());
		result = prime * result + ((getSellerId() == null) ? 0 : getSellerId().hashCode());
		result = prime * result + ((getBuyerId() == null) ? 0 : getBuyerId().hashCode());
		result = prime * result + ((getTotalFee() == null) ? 0 : getTotalFee().hashCode());
		result = prime * result + ((getIsSuccess() == null) ? 0 : getIsSuccess().hashCode());
		result = prime * result + ((getSignType() == null) ? 0 : getSignType().hashCode());
		result = prime * result + ((getSign() == null) ? 0 : getSign().hashCode());
		result = prime * result + ((getReturnid() == null) ? 0 : getReturnid().hashCode());
		result = prime * result + ((getStartDate() == null) ? 0 : getStartDate().hashCode());
		result = prime * result + ((getEndDate() == null) ? 0 : getEndDate().hashCode());
		return result;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

	public String getNotify_id() {
		return notify_id;
	}

	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getBuyer_email() {
		return buyer_email;
	}

	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getIs_success() {
		return is_success;
	}

	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}