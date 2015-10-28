package com.cnit.yoyo.api.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;
import com.cnit.yoyo.dto.BaseQryDTO;

public class MemberCarDelVO extends Parameter implements Serializable {

	private static final long serialVersionUID = 4942687936887693016L;
	@NotNull(message="{车型id不能为空}")
	private Integer id;
	private Integer carId;
	private Long memberId;
	private Integer carYear;
	@NotBlank(message="{sessionid不能为空}")
	private String sessionid;
	
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

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

}