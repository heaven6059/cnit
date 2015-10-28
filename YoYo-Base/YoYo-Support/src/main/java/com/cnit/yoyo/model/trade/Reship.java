package com.cnit.yoyo.model.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.cnit.yoyo.model.order.Order;

public class Reship implements Serializable {

	private Order order;
	private ReshipItems reItem;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.reship_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private Long reshipId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.order_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String orderId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.reship_bn
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String reshipBn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.money
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private BigDecimal money;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.delivery
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String delivery;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.logi_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String logiId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.logi_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String logiName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.logi_no
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String logiNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.ship_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String shipName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.ship_area
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String shipArea;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.ship_zip
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String shipZip;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.ship_tel
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String shipTel;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.ship_mobile
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String shipMobile;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.ship_email
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String shipEmail;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.t_begin
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private Date tBegin;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.t_send
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private Date tSend;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.t_confirm
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private Date tConfirm;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.op_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private String opName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.status
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private Integer status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_reship.disabled
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private Integer disabled;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_reship
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ReshipItems getReItem() {
		return reItem;
	}

	public void setReItem(ReshipItems reItem) {
		this.reItem = reItem;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.reship_id
	 * 
	 * @return the value of t_reship.reship_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public Long getReshipId() {
		return reshipId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.reship_id
	 * 
	 * @param reshipId
	 *            the value for t_reship.reship_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setReshipId(Long reshipId) {
		this.reshipId = reshipId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.order_id
	 * 
	 * @return the value of t_reship.order_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.order_id
	 * 
	 * @param orderId
	 *            the value for t_reship.order_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.reship_bn
	 * 
	 * @return the value of t_reship.reship_bn
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getReshipBn() {
		return reshipBn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.reship_bn
	 * 
	 * @param reshipBn
	 *            the value for t_reship.reship_bn
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setReshipBn(String reshipBn) {
		this.reshipBn = reshipBn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.money
	 * 
	 * @return the value of t_reship.money
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.money
	 * 
	 * @param money
	 *            the value for t_reship.money
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.delivery
	 * 
	 * @return the value of t_reship.delivery
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getDelivery() {
		return delivery;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.delivery
	 * 
	 * @param delivery
	 *            the value for t_reship.delivery
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.logi_id
	 * 
	 * @return the value of t_reship.logi_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getLogiId() {
		return logiId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.logi_id
	 * 
	 * @param logiId
	 *            the value for t_reship.logi_id
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setLogiId(String logiId) {
		this.logiId = logiId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.logi_name
	 * 
	 * @return the value of t_reship.logi_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getLogiName() {
		return logiName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.logi_name
	 * 
	 * @param logiName
	 *            the value for t_reship.logi_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setLogiName(String logiName) {
		this.logiName = logiName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.logi_no
	 * 
	 * @return the value of t_reship.logi_no
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getLogiNo() {
		return logiNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.logi_no
	 * 
	 * @param logiNo
	 *            the value for t_reship.logi_no
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setLogiNo(String logiNo) {
		this.logiNo = logiNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.ship_name
	 * 
	 * @return the value of t_reship.ship_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getShipName() {
		return shipName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.ship_name
	 * 
	 * @param shipName
	 *            the value for t_reship.ship_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.ship_area
	 * 
	 * @return the value of t_reship.ship_area
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getShipArea() {
		return shipArea;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.ship_area
	 * 
	 * @param shipArea
	 *            the value for t_reship.ship_area
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setShipArea(String shipArea) {
		this.shipArea = shipArea;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.ship_zip
	 * 
	 * @return the value of t_reship.ship_zip
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getShipZip() {
		return shipZip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.ship_zip
	 * 
	 * @param shipZip
	 *            the value for t_reship.ship_zip
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setShipZip(String shipZip) {
		this.shipZip = shipZip;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.ship_tel
	 * 
	 * @return the value of t_reship.ship_tel
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getShipTel() {
		return shipTel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.ship_tel
	 * 
	 * @param shipTel
	 *            the value for t_reship.ship_tel
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setShipTel(String shipTel) {
		this.shipTel = shipTel;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.ship_mobile
	 * 
	 * @return the value of t_reship.ship_mobile
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getShipMobile() {
		return shipMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.ship_mobile
	 * 
	 * @param shipMobile
	 *            the value for t_reship.ship_mobile
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setShipMobile(String shipMobile) {
		this.shipMobile = shipMobile;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.ship_email
	 * 
	 * @return the value of t_reship.ship_email
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getShipEmail() {
		return shipEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.ship_email
	 * 
	 * @param shipEmail
	 *            the value for t_reship.ship_email
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setShipEmail(String shipEmail) {
		this.shipEmail = shipEmail;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.t_begin
	 * 
	 * @return the value of t_reship.t_begin
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public Date gettBegin() {
		return tBegin;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.t_begin
	 * 
	 * @param tBegin
	 *            the value for t_reship.t_begin
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void settBegin(Date tBegin) {
		this.tBegin = tBegin;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.t_send
	 * 
	 * @return the value of t_reship.t_send
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public Date gettSend() {
		return tSend;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.t_send
	 * 
	 * @param tSend
	 *            the value for t_reship.t_send
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void settSend(Date tSend) {
		this.tSend = tSend;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.t_confirm
	 * 
	 * @return the value of t_reship.t_confirm
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public Date gettConfirm() {
		return tConfirm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.t_confirm
	 * 
	 * @param tConfirm
	 *            the value for t_reship.t_confirm
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void settConfirm(Date tConfirm) {
		this.tConfirm = tConfirm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.op_name
	 * 
	 * @return the value of t_reship.op_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public String getOpName() {
		return opName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.op_name
	 * 
	 * @param opName
	 *            the value for t_reship.op_name
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setOpName(String opName) {
		this.opName = opName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.status
	 * 
	 * @return the value of t_reship.status
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.status
	 * 
	 * @param status
	 *            the value for t_reship.status
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_reship.disabled
	 * 
	 * @return the value of t_reship.disabled
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public Integer getDisabled() {
		return disabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_reship.disabled
	 * 
	 * @param disabled
	 *            the value for t_reship.disabled
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	public void setDisabled(Integer disabled) {
		this.disabled = disabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_reship
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", reshipId=").append(reshipId);
		sb.append(", orderId=").append(orderId);
		sb.append(", reshipBn=").append(reshipBn);
		sb.append(", money=").append(money);
		sb.append(", delivery=").append(delivery);
		sb.append(", logiId=").append(logiId);
		sb.append(", logiName=").append(logiName);
		sb.append(", logiNo=").append(logiNo);
		sb.append(", shipName=").append(shipName);
		sb.append(", shipArea=").append(shipArea);
		sb.append(", shipZip=").append(shipZip);
		sb.append(", shipTel=").append(shipTel);
		sb.append(", shipMobile=").append(shipMobile);
		sb.append(", shipEmail=").append(shipEmail);
		sb.append(", tBegin=").append(tBegin);
		sb.append(", tSend=").append(tSend);
		sb.append(", tConfirm=").append(tConfirm);
		sb.append(", opName=").append(opName);
		sb.append(", status=").append(status);
		sb.append(", disabled=").append(disabled);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_reship
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
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
		Reship other = (Reship) that;
		return (this.getReshipId() == null ? other.getReshipId() == null : this
				.getReshipId().equals(other.getReshipId()))
				&& (this.getOrderId() == null ? other.getOrderId() == null
						: this.getOrderId().equals(other.getOrderId()))
				&& (this.getReshipBn() == null ? other.getReshipBn() == null
						: this.getReshipBn().equals(other.getReshipBn()))
				&& (this.getMoney() == null ? other.getMoney() == null : this
						.getMoney().equals(other.getMoney()))
				&& (this.getDelivery() == null ? other.getDelivery() == null
						: this.getDelivery().equals(other.getDelivery()))
				&& (this.getLogiId() == null ? other.getLogiId() == null : this
						.getLogiId().equals(other.getLogiId()))
				&& (this.getLogiName() == null ? other.getLogiName() == null
						: this.getLogiName().equals(other.getLogiName()))
				&& (this.getLogiNo() == null ? other.getLogiNo() == null : this
						.getLogiNo().equals(other.getLogiNo()))
				&& (this.getShipName() == null ? other.getShipName() == null
						: this.getShipName().equals(other.getShipName()))
				&& (this.getShipArea() == null ? other.getShipArea() == null
						: this.getShipArea().equals(other.getShipArea()))
				&& (this.getShipZip() == null ? other.getShipZip() == null
						: this.getShipZip().equals(other.getShipZip()))
				&& (this.getShipTel() == null ? other.getShipTel() == null
						: this.getShipTel().equals(other.getShipTel()))
				&& (this.getShipMobile() == null ? other.getShipMobile() == null
						: this.getShipMobile().equals(other.getShipMobile()))
				&& (this.getShipEmail() == null ? other.getShipEmail() == null
						: this.getShipEmail().equals(other.getShipEmail()))
				&& (this.gettBegin() == null ? other.gettBegin() == null : this
						.gettBegin().equals(other.gettBegin()))
				&& (this.gettSend() == null ? other.gettSend() == null : this
						.gettSend().equals(other.gettSend()))
				&& (this.gettConfirm() == null ? other.gettConfirm() == null
						: this.gettConfirm().equals(other.gettConfirm()))
				&& (this.getOpName() == null ? other.getOpName() == null : this
						.getOpName().equals(other.getOpName()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getDisabled() == null ? other.getDisabled() == null
						: this.getDisabled().equals(other.getDisabled()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_reship
	 * 
	 * @mbggenerated Thu Apr 16 14:01:33 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getReshipId() == null) ? 0 : getReshipId().hashCode());
		result = prime * result
				+ ((getOrderId() == null) ? 0 : getOrderId().hashCode());
		result = prime * result
				+ ((getReshipBn() == null) ? 0 : getReshipBn().hashCode());
		result = prime * result
				+ ((getMoney() == null) ? 0 : getMoney().hashCode());
		result = prime * result
				+ ((getDelivery() == null) ? 0 : getDelivery().hashCode());
		result = prime * result
				+ ((getLogiId() == null) ? 0 : getLogiId().hashCode());
		result = prime * result
				+ ((getLogiName() == null) ? 0 : getLogiName().hashCode());
		result = prime * result
				+ ((getLogiNo() == null) ? 0 : getLogiNo().hashCode());
		result = prime * result
				+ ((getShipName() == null) ? 0 : getShipName().hashCode());
		result = prime * result
				+ ((getShipArea() == null) ? 0 : getShipArea().hashCode());
		result = prime * result
				+ ((getShipZip() == null) ? 0 : getShipZip().hashCode());
		result = prime * result
				+ ((getShipTel() == null) ? 0 : getShipTel().hashCode());
		result = prime * result
				+ ((getShipMobile() == null) ? 0 : getShipMobile().hashCode());
		result = prime * result
				+ ((getShipEmail() == null) ? 0 : getShipEmail().hashCode());
		result = prime * result
				+ ((gettBegin() == null) ? 0 : gettBegin().hashCode());
		result = prime * result
				+ ((gettSend() == null) ? 0 : gettSend().hashCode());
		result = prime * result
				+ ((gettConfirm() == null) ? 0 : gettConfirm().hashCode());
		result = prime * result
				+ ((getOpName() == null) ? 0 : getOpName().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getDisabled() == null) ? 0 : getDisabled().hashCode());
		return result;
	}
}