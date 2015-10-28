package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;

/**
 * @Description: 订单提示DTO
 * @author 王鹏
 * @date 2015年7月29日 下午2:37:24
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class OrderTipsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2069417175812174493L;
	private Integer unPay;
	private Integer confirm;
	private Integer unComments;
	private Integer wishCount;
	private Integer unconfirm;
	private Integer finish;
	private Integer selling;
	private Integer putaway;
	private Integer refunds;
	private Integer aftersales;
	private Integer complain;
	private Integer report;
	private Integer coupons;

	public Integer getUnPay() {
		return unPay;
	}

	public void setUnPay(Integer unPay) {
		this.unPay = unPay;
	}

	public Integer getConfirm() {
		return confirm;
	}

	public void setConfirm(Integer confirm) {
		this.confirm = confirm;
	}

	public Integer getUnComments() {
		return unComments;
	}

	public void setUnComments(Integer unComments) {
		this.unComments = unComments;
	}

	public Integer getWishCount() {
		return wishCount;
	}

	public void setWishCount(Integer wishCount) {
		this.wishCount = wishCount;
	}

	public Integer getUnconfirm() {
		return unconfirm;
	}

	public void setUnconfirm(Integer unconfirm) {
		this.unconfirm = unconfirm;
	}

	public Integer getFinish() {
		return finish;
	}

	public void setFinish(Integer finish) {
		this.finish = finish;
	}

	public Integer getSelling() {
		return selling;
	}

	public void setSelling(Integer selling) {
		this.selling = selling;
	}

	public Integer getPutaway() {
		return putaway;
	}

	public void setPutaway(Integer putaway) {
		this.putaway = putaway;
	}

	public Integer getRefunds() {
		return refunds;
	}

	public void setRefunds(Integer refunds) {
		this.refunds = refunds;
	}

	public Integer getAftersales() {
		return aftersales;
	}

	public void setAftersales(Integer aftersales) {
		this.aftersales = aftersales;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getComplain() {
		return complain;
	}

	public void setComplain(Integer complain) {
		this.complain = complain;
	}

	public Integer getReport() {
		return report;
	}

	public void setReport(Integer report) {
		this.report = report;
	}

	public Integer getCoupons() {
		return coupons;
	}

	public void setCoupons(Integer coupons) {
		this.coupons = coupons;
	}

}
