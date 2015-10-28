package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;
import java.util.Date;

import com.cnit.yoyo.model.goods.GoodsAppointment;

public class GoodsAppointmentDTO extends GoodsAppointment implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5550661076042398046L;
	private Date appointmentDate;//预约时间
	private Integer appointmentNum;//已预约数量
	private Boolean enabled;

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Integer getAppointmentNum() {
		return appointmentNum;
	}

	public void setAppointmentNum(Integer appointmentNum) {
		this.appointmentNum = appointmentNum;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
