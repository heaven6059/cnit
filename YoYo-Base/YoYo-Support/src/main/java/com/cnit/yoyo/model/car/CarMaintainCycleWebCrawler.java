package com.cnit.yoyo.model.car;

import java.io.Serializable;

public class CarMaintainCycleWebCrawler implements Serializable {

	private Integer crawlerId;
	private Integer carId;
	private Integer maintainTime;
	private String name;
	private Integer carTripRange;
	private Integer purchaseTime;
	private Integer firstMaintainTime;
	private Integer firstMaintainKm;
	private String officialMaintain;
	private String maintainDesc;
	private static final long serialVersionUID = 1L;

	public Integer getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(Integer crawlerId) {
		this.crawlerId = crawlerId;
	}

	public Integer getCarId() {
		return carId;
	}

	public void setCarId(Integer carId) {
		this.carId = carId;
	}

	public Integer getMaintainTime() {
		return maintainTime;
	}

	public void setMaintainTime(Integer maintainTime) {
		this.maintainTime = maintainTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCarTripRange() {
		return carTripRange;
	}

	public void setCarTripRange(Integer carTripRange) {
		this.carTripRange = carTripRange;
	}

	public Integer getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Integer purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public Integer getFirstMaintainTime() {
		return firstMaintainTime;
	}

	public void setFirstMaintainTime(Integer firstMaintainTime) {
		this.firstMaintainTime = firstMaintainTime;
	}

	public Integer getFirstMaintainKm() {
		return firstMaintainKm;
	}

	public void setFirstMaintainKm(Integer firstMaintainKm) {
		this.firstMaintainKm = firstMaintainKm;
	}

	public String getOfficialMaintain() {
		return officialMaintain;
	}

	public void setOfficialMaintain(String officialMaintain) {
		this.officialMaintain = officialMaintain;
	}

	public String getMaintainDesc() {
		return maintainDesc;
	}

	public void setMaintainDesc(String maintainDesc) {
		this.maintainDesc = maintainDesc;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", crawlerId=").append(crawlerId);
		sb.append(", carId=").append(carId);
		sb.append(", maintainTime=").append(maintainTime);
		sb.append(", name=").append(name);
		sb.append(", carTripRange=").append(carTripRange);
		sb.append(", purchaseTime=").append(purchaseTime);
		sb.append(", firstMaintainTime=").append(firstMaintainTime);
		sb.append(", firstMaintainKm=").append(firstMaintainKm);
		sb.append(", officialMaintain=").append(officialMaintain);
		sb.append(", maintainDesc=").append(maintainDesc);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

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
		CarMaintainCycleWebCrawler other = (CarMaintainCycleWebCrawler) that;
		return (this.getCrawlerId() == null ? other.getCrawlerId() == null
				: this.getCrawlerId().equals(other.getCrawlerId()))
				&& (this.getCarId() == null ? other.getCarId() == null : this
						.getCarId().equals(other.getCarId()))
				&& (this.getMaintainTime() == null ? other.getMaintainTime() == null
						: this.getMaintainTime()
								.equals(other.getMaintainTime()))
				&& (this.getName() == null ? other.getName() == null : this
						.getName().equals(other.getName()))
				&& (this.getCarTripRange() == null ? other.getCarTripRange() == null
						: this.getCarTripRange()
								.equals(other.getCarTripRange()))
				&& (this.getPurchaseTime() == null ? other.getPurchaseTime() == null
						: this.getPurchaseTime()
								.equals(other.getPurchaseTime()))
				&& (this.getFirstMaintainTime() == null ? other
						.getFirstMaintainTime() == null : this
						.getFirstMaintainTime().equals(
								other.getFirstMaintainTime()))
				&& (this.getFirstMaintainKm() == null ? other
						.getFirstMaintainKm() == null : this
						.getFirstMaintainKm()
						.equals(other.getFirstMaintainKm()))
				&& (this.getOfficialMaintain() == null ? other
						.getOfficialMaintain() == null : this
						.getOfficialMaintain().equals(
								other.getOfficialMaintain()))
				&& (this.getMaintainDesc() == null ? other.getMaintainDesc() == null
						: this.getMaintainDesc()
								.equals(other.getMaintainDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getCrawlerId() == null) ? 0 : getCrawlerId().hashCode());
		result = prime * result
				+ ((getCarId() == null) ? 0 : getCarId().hashCode());
		result = prime
				* result
				+ ((getMaintainTime() == null) ? 0 : getMaintainTime()
						.hashCode());
		result = prime * result
				+ ((getName() == null) ? 0 : getName().hashCode());
		result = prime
				* result
				+ ((getCarTripRange() == null) ? 0 : getCarTripRange()
						.hashCode());
		result = prime
				* result
				+ ((getPurchaseTime() == null) ? 0 : getPurchaseTime()
						.hashCode());
		result = prime
				* result
				+ ((getFirstMaintainTime() == null) ? 0
						: getFirstMaintainTime().hashCode());
		result = prime
				* result
				+ ((getFirstMaintainKm() == null) ? 0 : getFirstMaintainKm()
						.hashCode());
		result = prime
				* result
				+ ((getOfficialMaintain() == null) ? 0 : getOfficialMaintain()
						.hashCode());
		result = prime
				* result
				+ ((getMaintainDesc() == null) ? 0 : getMaintainDesc()
						.hashCode());
		return result;
	}
}