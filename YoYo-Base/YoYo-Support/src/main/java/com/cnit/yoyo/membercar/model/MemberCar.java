package com.cnit.yoyo.membercar.model;

import java.io.Serializable;
import java.util.Date;

public class MemberCar implements Serializable {
    private Integer id;

    private Integer memberId;

    private String carNickName;

    private Integer isDefault;

    private Long carBrandId;

    private Long carDeptId;

    private Long carId;

    private Integer carYear;

    private Integer carProductionMonth;

    private Integer useYear;

    private Integer useMonth;
    
    private Long useMeter;

    private Date lastmodified;
    
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getLastmodified() {
        return lastmodified;
    }

    public void setLastmodified(Date lastmodified) {
        this.lastmodified = lastmodified;
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