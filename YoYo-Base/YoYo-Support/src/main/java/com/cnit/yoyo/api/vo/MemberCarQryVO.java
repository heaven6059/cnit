package com.cnit.yoyo.api.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.cnit.yoyo.dto.BaseQryDTO;

public class MemberCarQryVO extends BaseQryDTO implements Serializable {
	private static final long serialVersionUID = -8305536764532172595L;
	@NotBlank(message="{平台标识不能为空}")
	private String fphoneostype;
	private Integer id;
	private Integer carId;
	@NotNull(message="{memberId不能为空}") 
	private Long memberId;
	private Integer carYear;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public String getFphoneostype() {
		return fphoneostype;
	}

	public void setFphoneostype(String fphoneostype) {
		this.fphoneostype = fphoneostype;
	}

}