package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.util.OrderUtils;
import com.cnit.yoyo.util.StringUtil;

public class Order implements Serializable {

	private static final long serialVersionUID = 4682537677057655123L;
	/**
	 * 订单对应的订单详情数据集合
	 */
	private List<OrderItems> orderItems;
	private Member member;

	private String orderMemberName;// 订单会员名
	/**
	 * 订单对应的订单操作日志集合
	 */
	private List<OrderLog> orderLogs;
	/** 订单号 */
	private Long orderId;
	/** 集团ID */
	private Long companyId;
	/** 店铺ID */
	private Long storeId;
	/** 会员ID */
	private Integer memberId;
	/** 卖家名称 */
	private String shopName;
	/** 商品默认货币总值 */
	private BigDecimal totalAmount;
	/** 订单货币总值 */
	private BigDecimal finalAmount;
	private String shipStatus;
	/** 付款状态 */
	private String payStatus;// 0：未支付；1：已支付；2：已付款至担保方；3：部分付款；4：部分退款；5：全额退款;500:取消订单后的支付状态
	/** 是否需要发货 */
	private String isDelivery;
	/** 抵扣金额 */
	private BigDecimal discountValue;
	/** 下单时间 */
	private Date createtime;
	/** 最后更新时间 */
	private Date lastModified;
	/** 支付方式 */
	private String payment;
	/** 支付方式（key） */
	private Integer paymentId;
	/** 配送方式ID */
	private String shipping;
	/** 订单状态 */
	private String status;// create:创建订单;unconfirm:待确认;uninstall:未安装;install:安装中;finish:已完成;dead:已作废;
	/** 确认状态 */
	private String confirm;
	/** 配送区域 */
	private String shipArea;
	/** 收货人姓名 */
	private String shipName;
	/** 订单总重量 */
	private BigDecimal weight;
	/** 订单文字描述 */
	private String tostr;
	/** 订单子订单数量 */
	private Integer itemnum;
	/** 订单创建者ip */
	private String ip;
	/** 收货地址 */
	private String shipAddr;
	/** 收货人邮编 */
	private String shipZip;
	/** 收货电话 */
	private String shipTel;
	/** 收货人email */
	private String shipEmail;
	/** 配送时间 */
	private String shipTime;
	/** 收货人手机 */
	private String shipMobile;
	/** 订单商品总价格 */
	private BigDecimal costItem;
	/** 是否使用优惠卷 */
	private Integer isCoupons;
	/** 订单税率 */
	private BigDecimal costTax;
	/** 发票抬头 */
	private String taxCompany;
	/** 是否还有保价费 */
	private String isProtect;
	/** 保价费 */
	private BigDecimal costProtect;
	/** 支付费用 */
	private BigDecimal costPayment;
	/** 订单支付货币 */
	private String currency;
	/** 订单支付货币汇率 */
	private Long curRate;
	/** 订单使用积分 */
	private BigDecimal scoreU;
	/** 订单获得积分 */
	private BigDecimal scoreG;
	/** 订单减免 */
	private BigDecimal discount;
	/** 商品促销优惠 */
	private BigDecimal pmtGoods;
	/** 订单促销优惠 */
	private BigDecimal pmtOrder;
	/** 订单支付金额 */
	private BigDecimal payed;
	/** 订单附言 */
	private String memo;
	/** 是否失效 */
	private String disabled;
	/** 订单备注图标 */
	private String markType;
	/** 订单备注 */
	private String markText;
	/** 配送费用 */
	private BigDecimal costFreight;
	/** extend */
	private String extend;
	/** 订单来源 */
	private String orderRefer;
	/** 订单附属信息（系列化） */
	private String addon;
	/** 平台来源 */
	private String source;
	/** 确认时间 */
	private Date confirmTime;
	/** 评论次数 */
	private Integer commentsCount;
	/** 退款状态 transient */
	private String refundStatus;
	/** 退款状态字符 */
	private String refundText;
	/** 活动ID */
	private Integer actId;
	/** 订单类型 */
	private String orderType;
	/** 是否发起延长收货申请 */
	private String isExtend;
	/** 订单类型1 */
	private String orderKind;
	/** 主单号 */
	private Long parentOrderId;

	/** 订单是否显示 1：是 0:否 **/
	private Integer isDisplay;

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
		if (StringUtil.isEmpty(refundStatus))
			return "";
		try {
			refundText = OrderUtils.getPropertyValue(refundStatus);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return refundText;
	}

	/**
	 * toString()方法重写
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("orderId=").append(orderId);
		sb.append(", companyId=").append(companyId);
		sb.append(", storeId=").append(storeId);
		sb.append(", memberId=").append(memberId);
		sb.append(", totalAmount=").append(totalAmount);
		sb.append(", finalAmount=").append(finalAmount);
		sb.append(", payStatus=").append(payStatus);
		sb.append(", isDelivery=").append(isDelivery);
		sb.append(", discountValue=").append(discountValue);
		sb.append(", createtime=").append(createtime);
		sb.append(", lastModified=").append(lastModified);
		sb.append(", payment=").append(payment);
		sb.append(", shippingId=").append(paymentId);
		sb.append(", shipping=").append(shipping);
		sb.append(", status=").append(status);
		sb.append(", confirm=").append(confirm);
		sb.append(", shipArea=").append(shipArea);
		sb.append(", shipName=").append(shipName);
		sb.append(", weight=").append(weight);
		sb.append(", tostr=").append(tostr);
		sb.append(", itemnum=").append(itemnum);
		sb.append(", ip=").append(ip);
		sb.append(", shipAddr=").append(shipAddr);
		sb.append(", shipZip=").append(shipZip);
		sb.append(", shipTel=").append(shipTel);
		sb.append(", shipEmail=").append(shipEmail);
		sb.append(", shipTime=").append(shipTime);
		sb.append(", shipMobile=").append(shipMobile);
		sb.append(", costItem=").append(costItem);
		sb.append(", isCoupons=").append(isCoupons);
		sb.append(", costTax=").append(costTax);
		sb.append(", taxCompany=").append(taxCompany);
		sb.append(", isProtect=").append(isProtect);
		sb.append(", costProtect=").append(costProtect);
		sb.append(", costPayment=").append(costPayment);
		sb.append(", currency=").append(currency);
		sb.append(", curRate=").append(curRate);
		sb.append(", scoreU=").append(scoreU);
		sb.append(", scoreG=").append(scoreG);
		sb.append(", discount=").append(discount);
		sb.append(", pmtGoods=").append(pmtGoods);
		sb.append(", pmtOrder=").append(pmtOrder);
		sb.append(", payed=").append(payed);
		sb.append(", memo=").append(memo);
		sb.append(", disabled=").append(disabled);
		sb.append(", markType=").append(markType);
		sb.append(", markText=").append(markText);
		sb.append(", costFreight=").append(costFreight);
		sb.append(", extend=").append(extend);
		sb.append(", orderRefer=").append(orderRefer);
		sb.append(", addon=").append(addon);
		sb.append(", source=").append(source);
		sb.append(", confirmTime=").append(confirmTime);
		sb.append(", commentsCount=").append(commentsCount);
		sb.append(", refundStatus=").append(refundStatus);
		sb.append(", actId=").append(actId);
		sb.append(", orderType=").append(orderType);
		sb.append(", isExtend=").append(isExtend);
		sb.append(", orderKind=").append(orderKind);
		return sb.toString();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<OrderItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}

	public List<OrderLog> getOrderLogs() {
		return orderLogs;
	}

	public void setOrderLogs(List<OrderLog> orderLogs) {
		this.orderLogs = orderLogs;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getIsDelivery() {
		return isDelivery;
	}

	public void setIsDelivery(String isDelivery) {
		this.isDelivery = isDelivery;
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public String getShipping() {
		return shipping;
	}

	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public String getShipArea() {
		return shipArea;
	}

	public void setShipArea(String shipArea) {
		this.shipArea = shipArea;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getTostr() {
		return tostr;
	}

	public void setTostr(String tostr) {
		this.tostr = tostr;
	}

	public Integer getItemnum() {
		return itemnum;
	}

	public void setItemnum(Integer itemnum) {
		this.itemnum = itemnum;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getShipAddr() {
		return shipAddr;
	}

	public void setShipAddr(String shipAddr) {
		this.shipAddr = shipAddr;
	}

	public String getShipZip() {
		return shipZip;
	}

	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	public String getShipTel() {
		return shipTel;
	}

	public void setShipTel(String shipTel) {
		this.shipTel = shipTel;
	}

	public String getShipEmail() {
		return shipEmail;
	}

	public void setShipEmail(String shipEmail) {
		this.shipEmail = shipEmail;
	}

	public String getShipTime() {
		return shipTime;
	}

	public void setShipTime(String shipTime) {
		this.shipTime = shipTime;
	}

	public String getShipMobile() {
		return shipMobile;
	}

	public void setShipMobile(String shipMobile) {
		this.shipMobile = shipMobile;
	}

	public BigDecimal getCostItem() {
		return costItem;
	}

	public void setCostItem(BigDecimal costItem) {
		this.costItem = costItem;
	}

	public Integer getIsCoupons() {
		return isCoupons;
	}

	public void setIsCoupons(Integer isCoupons) {
		this.isCoupons = isCoupons;
	}

	public BigDecimal getCostTax() {
		return costTax;
	}

	public void setCostTax(BigDecimal costTax) {
		this.costTax = costTax;
	}

	public String getTaxCompany() {
		return taxCompany;
	}

	public void setTaxCompany(String taxCompany) {
		this.taxCompany = taxCompany;
	}

	public String getIsProtect() {
		return isProtect;
	}

	public void setIsProtect(String isProtect) {
		this.isProtect = isProtect;
	}

	public BigDecimal getCostProtect() {
		return costProtect;
	}

	public void setCostProtect(BigDecimal costProtect) {
		this.costProtect = costProtect;
	}

	public BigDecimal getCostPayment() {
		return costPayment;
	}

	public void setCostPayment(BigDecimal costPayment) {
		this.costPayment = costPayment;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Long getCurRate() {
		return curRate;
	}

	public void setCurRate(Long curRate) {
		this.curRate = curRate;
	}

	public BigDecimal getScoreU() {
		return scoreU;
	}

	public void setScoreU(BigDecimal scoreU) {
		this.scoreU = scoreU;
	}

	public BigDecimal getScoreG() {
		return scoreG;
	}

	public void setScoreG(BigDecimal scoreG) {
		this.scoreG = scoreG;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public BigDecimal getPmtGoods() {
		return pmtGoods;
	}

	public void setPmtGoods(BigDecimal pmtGoods) {
		this.pmtGoods = pmtGoods;
	}

	public BigDecimal getPmtOrder() {
		return pmtOrder;
	}

	public void setPmtOrder(BigDecimal pmtOrder) {
		this.pmtOrder = pmtOrder;
	}

	public BigDecimal getPayed() {
		return payed;
	}

	public void setPayed(BigDecimal payed) {
		this.payed = payed;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMarkType() {
		return markType;
	}

	public void setMarkType(String markType) {
		this.markType = markType;
	}

	public String getMarkText() {
		return markText;
	}

	public void setMarkText(String markText) {
		this.markText = markText;
	}

	public BigDecimal getCostFreight() {
		return costFreight;
	}

	public void setCostFreight(BigDecimal costFreight) {
		this.costFreight = costFreight;
	}

	public String getExtend() {
		return extend;
	}

	public void setExtend(String extend) {
		this.extend = extend;
	}

	public String getOrderRefer() {
		return orderRefer;
	}

	public void setOrderRefer(String orderRefer) {
		this.orderRefer = orderRefer;
	}

	public String getAddon() {
		return addon;
	}

	public void setAddon(String addon) {
		this.addon = addon;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getConfirmTime() {
		return confirmTime;
	}

	public void setConfirmTime(Date confirmTime) {
		this.confirmTime = confirmTime;
	}

	public Integer getCommentsCount() {
		return commentsCount;
	}

	public void setCommentsCount(Integer commentsCount) {
		this.commentsCount = commentsCount;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getIsExtend() {
		return isExtend;
	}

	public void setIsExtend(String isExtend) {
		this.isExtend = isExtend;
	}

	public String getOrderKind() {
		return orderKind;
	}

	public void setOrderKind(String orderKind) {
		this.orderKind = orderKind;
	}

	public void setRefundText(String refundText) {
		this.refundText = refundText;
	}

	public Long getParentOrderId() {
		return parentOrderId;
	}

	public void setParentOrderId(Long parentOrderId) {
		this.parentOrderId = parentOrderId;
	}

	public String getOrderMemberName() {
		return orderMemberName;
	}

	public void setOrderMemberName(String orderMemberName) {
		this.orderMemberName = orderMemberName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Integer getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getShipStatus() {
		return shipStatus;
	}

	public void setShipStatus(String shipStatus) {
		this.shipStatus = shipStatus;
	}

}