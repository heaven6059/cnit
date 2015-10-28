package com.cnit.yoyo.model.cart.dto;

import java.io.Serializable;

import com.cnit.yoyo.model.goods.Product;

public class CartProductDTO extends Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6357385495179910210L;
	private Integer quantity;
	private String appointment;
	private Integer appointmentIndex;

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
