package com.cnit.yoyo.model.sales.activity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ScoreBuyApply implements Serializable {

	private Integer id;
	private Long gid;
	private Integer aid;
	private Long companyId;
	private Long storeId;
	private Integer memberId;
	private Integer catId;
	private BigDecimal price;
	private BigDecimal lastPrice;
	private Integer score;
	private Integer ismemlv;
	private Integer remainnums;
	private Integer nums;
	private Integer personlimit;
	private Integer status;
	private String remark;
	private String imageCodeid;
	private Date lastMidifity;
	private String isdel;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getGid() {
		return gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getIsmemlv() {
		return ismemlv;
	}

	public void setIsmemlv(Integer ismemlv) {
		this.ismemlv = ismemlv;
	}

	public Integer getRemainnums() {
		return remainnums;
	}

	public void setRemainnums(Integer remainnums) {
		this.remainnums = remainnums;
	}

	public Integer getNums() {
		return nums;
	}

	public void setNums(Integer nums) {
		this.nums = nums;
	}

	public Integer getPersonlimit() {
		return personlimit;
	}

	public void setPersonlimit(Integer personlimit) {
		this.personlimit = personlimit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getImageCodeid() {
		return imageCodeid;
	}

	public void setImageCodeid(String imageCodeid) {
		this.imageCodeid = imageCodeid;
	}

	public Date getLastMidifity() {
		return lastMidifity;
	}

	public void setLastMidifity(Date lastMidifity) {
		this.lastMidifity = lastMidifity;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", gid=").append(gid);
		sb.append(", aid=").append(aid);
		sb.append(", companyId=").append(companyId);
		sb.append(", storeId=").append(storeId);
		sb.append(", memberId=").append(memberId);
		sb.append(", catId=").append(catId);
		sb.append(", price=").append(price);
		sb.append(", lastPrice=").append(lastPrice);
		sb.append(", score=").append(score);
		sb.append(", ismemlv=").append(ismemlv);
		sb.append(", remainnums=").append(remainnums);
		sb.append(", nums=").append(nums);
		sb.append(", personlimit=").append(personlimit);
		sb.append(", status=").append(status);
		sb.append(", remark=").append(remark);
		sb.append(", imageCodeid=").append(imageCodeid);
		sb.append(", lastMidifity=").append(lastMidifity);
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
		ScoreBuyApply other = (ScoreBuyApply) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
				&& (this.getGid() == null ? other.getGid() == null : this.getGid().equals(other.getGid()))
				&& (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
				&& (this.getCompanyId() == null ? other.getCompanyId() == null
						: this.getCompanyId().equals(other.getCompanyId()))
				&& (this.getStoreId() == null ? other.getStoreId() == null
						: this.getStoreId().equals(other.getStoreId()))
				&& (this.getMemberId() == null ? other.getMemberId() == null
						: this.getMemberId().equals(other.getMemberId()))
				&& (this.getCatId() == null ? other.getCatId() == null : this.getCatId().equals(other.getCatId()))
				&& (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
				&& (this.getLastPrice() == null ? other.getLastPrice() == null
						: this.getLastPrice().equals(other.getLastPrice()))
				&& (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
				&& (this.getIsmemlv() == null ? other.getIsmemlv() == null
						: this.getIsmemlv().equals(other.getIsmemlv()))
				&& (this.getRemainnums() == null ? other.getRemainnums() == null
						: this.getRemainnums().equals(other.getRemainnums()))
				&& (this.getNums() == null ? other.getNums() == null : this.getNums().equals(other.getNums()))
				&& (this.getPersonlimit() == null ? other.getPersonlimit() == null
						: this.getPersonlimit().equals(other.getPersonlimit()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
				&& (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
				&& (this.getImageCodeid() == null ? other.getImageCodeid() == null
						: this.getImageCodeid().equals(other.getImageCodeid()))
				&& (this.getLastMidifity() == null ? other.getLastMidifity() == null
						: this.getLastMidifity().equals(other.getLastMidifity()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getGid() == null) ? 0 : getGid().hashCode());
		result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
		result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
		result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
		result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
		result = prime * result + ((getCatId() == null) ? 0 : getCatId().hashCode());
		result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
		result = prime * result + ((getLastPrice() == null) ? 0 : getLastPrice().hashCode());
		result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
		result = prime * result + ((getIsmemlv() == null) ? 0 : getIsmemlv().hashCode());
		result = prime * result + ((getRemainnums() == null) ? 0 : getRemainnums().hashCode());
		result = prime * result + ((getNums() == null) ? 0 : getNums().hashCode());
		result = prime * result + ((getPersonlimit() == null) ? 0 : getPersonlimit().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getImageCodeid() == null) ? 0 : getImageCodeid().hashCode());
		result = prime * result + ((getLastMidifity() == null) ? 0 : getLastMidifity().hashCode());
		return result;
	}

	public String getIsdel() {
		return isdel;
	}

	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
}