/**
 * 文 件 名   :  GoodsQryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午4:05:25
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.goods.Picture;
import com.cnit.yoyo.util.HtmlRegexpUtil;

/**
 * 订单查询条件
 * 
 * @author ssd
 * @date 2015-4-13 下午1:52:06
 */
public class OrderQryDTO extends BaseQryDTO implements Serializable {
	private static final long serialVersionUID = 3972668715243253431L;
	private String statusQ;// 状态查询
	private String payStatusQ;// 支付状态查询
	private String shipStatusQ;//
	private String status;// 状态
	private String payStatus;// 支付状态
	private String orderTime;// 订单时间
	private Date dateCondition;// 时间
	private String goodsName;// 商品名称
	private String goodsBn;// 商品
	private String orderId;// 订单ID
	private Integer storeId;// 店铺ID
	private Integer companyId;
	private Long memberId;// 会员ID
	private Long itemId;// 订单详情Id
	private String payment;
	private String orderType;
	private String source;
	private String refundStatus;
	private List<Object> orderIds;

	public String getStatusQ() {
		return statusQ;
	}

	public void setStatusQ(String statusQ) {
		this.statusQ = statusQ;
	}

	public String getPayStatusQ() {
		return payStatusQ;
	}

	public void setPayStatusQ(String payStatusQ) {
		this.payStatusQ = payStatusQ;
	}

	public String getShipStatusQ() {
		return shipStatusQ;
	}

	public void setShipStatusQ(String shipStatusQ) {
		this.shipStatusQ = shipStatusQ;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Date getDateCondition() {
		return dateCondition;
	}

	public void setDateCondition(Date dateCondition) {
		this.dateCondition = dateCondition;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGoodsBn() {
		return goodsBn;
	}

	public void setGoodsBn(String goodsBn) {
		this.goodsBn = goodsBn;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}


	public List<Object> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<Object> orderIds) {
		this.orderIds = orderIds;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

}
