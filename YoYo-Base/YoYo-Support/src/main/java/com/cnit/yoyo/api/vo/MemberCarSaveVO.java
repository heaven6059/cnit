package com.cnit.yoyo.api.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;

/**
 * 保存车型实体VO
 * 
 * @Author:yangyi
 * @Date:2015年7月11日
 * @Version:1.0.0
 */
public class MemberCarSaveVO extends Parameter implements Serializable {

	private Integer id;

	private Integer memberId;

	private String carNickName;
	
	private Integer isDefault;
	
	@NotNull(message = "{carBrandId不能为空}")
	private Long carBrandId;
	
	@NotNull(message = "{carDeptId不能为空}")
	private Long carDeptId;
	
	@NotNull(message = "{carId不能为空}")
	private Long carId;
	
	@NotNull(message = "{carYear不能为空}")
	private Integer carYear;

	private Integer carProductionMonth;

	private Integer useYear;

	private Integer useMonth;
	
	private Long useMeter;

	private Date lastmodified;
	@NotBlank(message = "{sessionid不能为空}")
	private String sessionid;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getCarNickName() {
		return carNickName;
	}

	public void setCarNickName(String carNickName) {
		this.carNickName = carNickName;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public Long getCarBrandId() {
		return carBrandId;
	}

	public void setCarBrandId(Long carBrandId) {
		this.carBrandId = carBrandId;
	}

	public Long getCarDeptId() {
		return carDeptId;
	}

	public void setCarDeptId(Long carDeptId) {
		this.carDeptId = carDeptId;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public Integer getCarYear() {
		return carYear;
	}

	public void setCarYear(Integer carYear) {
		this.carYear = carYear;
	}

	public Integer getCarProductionMonth() {
		return carProductionMonth;
	}

	public void setCarProductionMonth(Integer carProductionMonth) {
		this.carProductionMonth = carProductionMonth;
	}

	public Integer getUseYear() {
		return useYear;
	}

	public void setUseYear(Integer useYear) {
		this.useYear = useYear;
	}

	public Integer getUseMonth() {
		return useMonth;
	}

	public void setUseMonth(Integer useMonth) {
		this.useMonth = useMonth;
	}
	
	public Long getUseMeter() {
		return useMeter;
	}

	public void setUseMeter(Long useMeter) {
		this.useMeter = useMeter;
	}

	public Date getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(Date lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", memberId=").append(memberId);
		sb.append(", carNickName=").append(carNickName);
		sb.append(", isDefault=").append(isDefault);
		sb.append(", carBrandId=").append(carBrandId);
		sb.append(", carDeptId=").append(carDeptId);
		sb.append(", carId=").append(carId);
		sb.append(", carYear=").append(carYear);
		sb.append(", carProductionMonth=").append(carProductionMonth);
		sb.append(", useYear=").append(useYear);
		sb.append(", useMonth=").append(useMonth);
		sb.append(", useMeter=").append(useMeter);
		sb.append(", lastmodified=").append(lastmodified);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}