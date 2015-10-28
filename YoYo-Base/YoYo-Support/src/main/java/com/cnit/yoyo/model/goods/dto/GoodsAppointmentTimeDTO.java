package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.util.List;

public class GoodsAppointmentTimeDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5007225538384745973L;
	private String appointmentTime;
	private List<GoodsAppointmentDTO> goodsAppointmentDTOs;

	public List<GoodsAppointmentDTO> getGoodsAppointmentDTOs() {
		return goodsAppointmentDTOs;
	}

	public void setGoodsAppointmentDTOs(
			List<GoodsAppointmentDTO> goodsAppointmentDTOs) {
		this.goodsAppointmentDTOs = goodsAppointmentDTOs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
}
