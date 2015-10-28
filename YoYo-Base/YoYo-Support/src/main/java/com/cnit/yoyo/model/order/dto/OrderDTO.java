package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * 订单数据传输对象
* @author ssd
* @date 2015-4-13 下午5:39:54
 */
public class OrderDTO implements Serializable {
	
	private static final long serialVersionUID = -24729625091537558L;
	private Date lastModified;// 最后更新时间
	private String status;//active:活动订单;dead:已作废;finish:已完成;unconfirm:待确认;create:创建订单
	private String markText;//订单备注
	private long orderId;//订单号
	private String memeberName;
	private String shopName;//主店名称
	private String memeberPhone;
	private Date createtime;
	private Integer payObject;
	private String payStatus;//0：未支付；1：已支付；2：已付款至担保方；3：部分付款；4：部分退款；5：全额退款
	private String payment;//付款状态
	private String orderType;//订单类型
	private String source;//平台来源
	private String refundStatus;//退款状态 transient 
	private BigDecimal finalAmount;//订单货币总值
	
	public String getMarkText() {
		return markText;
	}
	public void setMarkText(String markText) {
		this.markText = markText;
	}
	public String getMemeberName() {
		return memeberName;
	}
	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getMemeberPhone() {
		return memeberPhone;
	}
	public void setMemeberPhone(String memeberPhone) {
		this.memeberPhone = memeberPhone;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public BigDecimal getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getPayObject() {
		return payObject;
	}
	public void setPayObject(Integer payObject) {
		this.payObject = payObject;
	}
	public String getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	
	
}
