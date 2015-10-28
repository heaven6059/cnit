package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.cnit.yoyo.model.order.OrderItems;
/**
 * 订单明细数据传输对象
* @author ssd
* @date 2015-4-16 下午5:39:54
 */
public class OrderDetailDTO implements Serializable {
	
	private static final long serialVersionUID = -24729625091537558L;
	private long orderId;//订单ID
	private BigDecimal costItem;//订单数
	private BigDecimal finalAmount;//订单总额
	private BigDecimal costPayment;//订单金额
	private BigDecimal weight;//重量
	private String payment;//支付方式
	private BigDecimal scoreG;//
	private String loginName;//用户名
	private String memeberName;//用户姓名
	private String memeberPhone;//用户手机号码
	private String area;//地区
	private String email;//
	private String memo;//
	private String markText;//订单备注
	private String remark;//用户备注
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getCostItem() {
		return costItem;
	}
	public void setCostItem(BigDecimal costItem) {
		this.costItem = costItem;
	}
	public BigDecimal getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}
	public BigDecimal getCostPayment() {
		return costPayment;
	}
	public void setCostPayment(BigDecimal costPayment) {
		this.costPayment = costPayment;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	public BigDecimal getScoreG() {
		return scoreG;
	}
	public void setScoreG(BigDecimal scoreG) {
		this.scoreG = scoreG;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getMemeberName() {
		return memeberName;
	}
	public void setMemeberName(String memeberName) {
		this.memeberName = memeberName;
	}
	public String getMemeberPhone() {
		return memeberPhone;
	}
	public void setMemeberPhone(String memeberPhone) {
		this.memeberPhone = memeberPhone;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMarkText() {
		return markText;
	}
	public void setMarkText(String markText) {
		this.markText = markText;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
