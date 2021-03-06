package com.cnit.yoyo.model.cart;

import java.io.Serializable;

public class Cart implements Serializable {
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.cart_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer cartId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.member_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer memberId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.goods_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer goodsId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.product_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer productId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.company_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer companyId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.spec_value_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private String specValueId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.quantity
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer quantity;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_cart_objects.time
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private Integer time;

	private Integer storeId;

	private String appointment;
	private Integer appointmentIndex;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_cart_objects
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	public Cart() {
		super();
	}

	public Cart(Integer memberId, Integer goodsId, Integer productId,
			Integer companyId, String specValueId, Integer quantity,
			Integer time, String appointment) {
		super();
		this.memberId = memberId;
		this.goodsId = goodsId;
		this.productId = productId;
		this.companyId = companyId;
		this.specValueId = specValueId;
		this.quantity = quantity;
		this.time = time;
		this.appointment = appointment;
	}

	public Cart(Integer memberId, Integer goodsId, Integer productId,
			Integer companyId, Integer storeId, String specValueId,
			Integer quantity, Integer time, String appointment) {
		super();
		this.memberId = memberId;
		this.goodsId = goodsId;
		this.productId = productId;
		this.companyId = companyId;
		this.storeId = storeId;
		this.specValueId = specValueId;
		this.quantity = quantity;
		this.time = time;
		this.appointment = appointment;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.cart_id
	 * 
	 * @return the value of t_cart_objects.cart_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getCartId() {
		return cartId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.cart_id
	 * 
	 * @param cartId
	 *            the value for t_cart_objects.cart_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.member_id
	 * 
	 * @return the value of t_cart_objects.member_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getMemberId() {
		return memberId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.member_id
	 * 
	 * @param memberId
	 *            the value for t_cart_objects.member_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.goods_id
	 * 
	 * @return the value of t_cart_objects.goods_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getGoodsId() {
		return goodsId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.goods_id
	 * 
	 * @param goodsId
	 *            the value for t_cart_objects.goods_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.product_id
	 * 
	 * @return the value of t_cart_objects.product_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getProductId() {
		return productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.product_id
	 * 
	 * @param productId
	 *            the value for t_cart_objects.product_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.company_id
	 * 
	 * @return the value of t_cart_objects.company_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getCompanyId() {
		return companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.company_id
	 * 
	 * @param companyId
	 *            the value for t_cart_objects.company_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.spec_value_id
	 * 
	 * @return the value of t_cart_objects.spec_value_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public String getSpecValueId() {
		return specValueId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.spec_value_id
	 * 
	 * @param specValueId
	 *            the value for t_cart_objects.spec_value_id
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setSpecValueId(String specValueId) {
		this.specValueId = specValueId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.quantity
	 * 
	 * @return the value of t_cart_objects.quantity
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.quantity
	 * 
	 * @param quantity
	 *            the value for t_cart_objects.quantity
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_cart_objects.time
	 * 
	 * @return the value of t_cart_objects.time
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public Integer getTime() {
		return time;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_cart_objects.time
	 * 
	 * @param time
	 *            the value for t_cart_objects.time
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getAppointment() {
		return appointment;
	}

	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}

	public Integer getAppointmentIndex() {
		return appointmentIndex;
	}

	public void setAppointmentIndex(Integer appointmentIndex) {
		this.appointmentIndex = appointmentIndex;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_cart_objects
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", cartId=").append(cartId);
		sb.append(", memberId=").append(memberId);
		sb.append(", goodsId=").append(goodsId);
		sb.append(", productId=").append(productId);
		sb.append(", companyId=").append(companyId);
		sb.append(", specValueId=").append(specValueId);
		sb.append(", quantity=").append(quantity);
		sb.append(", time=").append(time);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_cart_objects
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
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
		Cart other = (Cart) that;
		return (this.getCartId() == null ? other.getCartId() == null : this
				.getCartId().equals(other.getCartId()))
				&& (this.getMemberId() == null ? other.getMemberId() == null
						: this.getMemberId().equals(other.getMemberId()))
				&& (this.getGoodsId() == null ? other.getGoodsId() == null
						: this.getGoodsId().equals(other.getGoodsId()))
				&& (this.getProductId() == null ? other.getProductId() == null
						: this.getProductId().equals(other.getProductId()))
				&& (this.getCompanyId() == null ? other.getCompanyId() == null
						: this.getCompanyId().equals(other.getCompanyId()))
				&& (this.getSpecValueId() == null ? other.getSpecValueId() == null
						: this.getSpecValueId().equals(other.getSpecValueId()))
				&& (this.getQuantity() == null ? other.getQuantity() == null
						: this.getQuantity().equals(other.getQuantity()))
				&& (this.getTime() == null ? other.getTime() == null : this
						.getTime().equals(other.getTime()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_cart_objects
	 * 
	 * @mbggenerated Wed Apr 15 11:14:22 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getCartId() == null) ? 0 : getCartId().hashCode());
		result = prime * result
				+ ((getMemberId() == null) ? 0 : getMemberId().hashCode());
		result = prime * result
				+ ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
		result = prime * result
				+ ((getProductId() == null) ? 0 : getProductId().hashCode());
		result = prime * result
				+ ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
		result = prime
				* result
				+ ((getSpecValueId() == null) ? 0 : getSpecValueId().hashCode());
		result = prime * result
				+ ((getQuantity() == null) ? 0 : getQuantity().hashCode());
		result = prime * result
				+ ((getTime() == null) ? 0 : getTime().hashCode());
		return result;
	}
}