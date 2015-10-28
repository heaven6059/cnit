package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.math.BigDecimal;

import com.cnit.yoyo.model.goods.Goods;
import com.cnit.yoyo.model.goods.Product;

public class OrderItems implements Serializable {
	/***/
	private static final long serialVersionUID = 7269782605228391348L;
	private Integer itemId;
	private Long orderId;
	private Integer productId;
	private Long goodsId;
	private String bn;
	private String name;
	private BigDecimal price;
	private BigDecimal gPric;
	private BigDecimal cost;
	private BigDecimal amount;
	private Integer score;
	private Integer weight;
	private Integer nums;
	private Integer sendnum;
	private String itemType;
	private String addon;
	private String picturePath;
	private String appointment;
	private Order order;
	private Product product;
	private Goods goods;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" itemId=").append(itemId);
		sb.append(", orderId=").append(orderId);
		sb.append(", productId=").append(productId);
		sb.append(", bn=").append(bn);
		sb.append(", name=").append(name);
		sb.append(", price=").append(price);
		sb.append(", gPric=").append(gPric);
		sb.append(", cost=").append(cost);
		sb.append(", amount=").append(amount);
		sb.append(", score=").append(score);
		sb.append(", weight=").append(weight);
		sb.append(", nums=").append(nums);
		sb.append(", sendnum=").append(sendnum);
		sb.append(", itemType=").append(itemType);
		sb.append(", addon=").append(addon);
		return sb.toString();
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getBn() {
		return bn;
	}

	public void setBn(String bn) {
		this.bn = bn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getgPric() {
		return gPric;
	}

	public void setgPric(BigDecimal gPric) {
		this.gPric = gPric;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Integer getSendnum() {
		return sendnum;
	}

	public void setSendnum(Integer sendnum) {
		this.sendnum = sendnum;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getAddon() {
		return addon;
	}

	public void setAddon(String addon) {
		this.addon = addon;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

}