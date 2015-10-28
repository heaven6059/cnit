package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.goods.Product;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.OrderBills;
import com.cnit.yoyo.model.order.OrderRefunds;
import com.cnit.yoyo.model.trade.AftersalesReturnProductWithBLOBs;

public class OrderRefundsDTO extends OrderRefunds implements Serializable {

	/**
	 * 退款dto
	 */
	private static final long serialVersionUID = 793015490501407306L;
	private AftersalesReturnProductWithBLOBs returnProduct;
	private Order order;//订单
	private OrderBills orderBills;//订单金额
	private Product product;//商品

	public AftersalesReturnProductWithBLOBs getReturnProduct() {
		return returnProduct;
	}

	public void setReturnProduct(AftersalesReturnProductWithBLOBs returnProduct) {
		this.returnProduct = returnProduct;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public OrderBills getOrderBills() {
		return orderBills;
	}

	public void setOrderBills(OrderBills orderBills) {
		this.orderBills = orderBills;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
