package com.cnit.yoyo.membercar.model;

import java.io.Serializable;

public class MemberCarDTO extends MemberCar implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4736375393513626194L;
	private String carName;
	private String carLogo;
	private String carDeptName;

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public String getCarLogo() {
		return carLogo;
	}

	public void setCarLogo(String carLogo) {
		this.carLogo = carLogo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCarDeptName() {
		return carDeptName;
	}

	public void setCarDeptName(String carDeptName) {
		this.carDeptName = carDeptName;
	}

	
}
