package com.cnit.yoyo.model.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class StoreGrade implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.grade_id
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Long gradeId;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.goods_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Long goodsNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.coupons_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Long couponsNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.theme_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Long themeNum;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.grade_money
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private BigDecimal gradeMoney;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.grade_name
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private String gradeName;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.issue_money
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private BigDecimal issueMoney;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.issue_type
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private String issueType;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.experience
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Integer experience;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.default_lv
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private String defaultLv;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.certification
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private String certification;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.disabled
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private String disabled;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.last_modify
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Integer lastModify;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.d_order
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private Integer dOrder;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database column t_business_storegrade.remark
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private String remark;
	/**
	 * This field was generated by MyBatis Generator. This field corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.grade_id
	 * @return  the value of t_business_storegrade.grade_id
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Long getGradeId() {
		return gradeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.grade_id
	 * @param gradeId  the value for t_business_storegrade.grade_id
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.goods_num
	 * @return  the value of t_business_storegrade.goods_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Long getGoodsNum() {
		return goodsNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.goods_num
	 * @param goodsNum  the value for t_business_storegrade.goods_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.coupons_num
	 * @return  the value of t_business_storegrade.coupons_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Long getCouponsNum() {
		return couponsNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.coupons_num
	 * @param couponsNum  the value for t_business_storegrade.coupons_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setCouponsNum(Long couponsNum) {
		this.couponsNum = couponsNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.theme_num
	 * @return  the value of t_business_storegrade.theme_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Long getThemeNum() {
		return themeNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.theme_num
	 * @param themeNum  the value for t_business_storegrade.theme_num
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setThemeNum(Long themeNum) {
		this.themeNum = themeNum;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.grade_money
	 * @return  the value of t_business_storegrade.grade_money
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public BigDecimal getGradeMoney() {
		return gradeMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.grade_money
	 * @param gradeMoney  the value for t_business_storegrade.grade_money
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setGradeMoney(BigDecimal gradeMoney) {
		this.gradeMoney = gradeMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.grade_name
	 * @return  the value of t_business_storegrade.grade_name
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getGradeName() {
		return gradeName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.grade_name
	 * @param gradeName  the value for t_business_storegrade.grade_name
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.issue_money
	 * @return  the value of t_business_storegrade.issue_money
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public BigDecimal getIssueMoney() {
		return issueMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.issue_money
	 * @param issueMoney  the value for t_business_storegrade.issue_money
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setIssueMoney(BigDecimal issueMoney) {
		this.issueMoney = issueMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.issue_type
	 * @return  the value of t_business_storegrade.issue_type
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getIssueType() {
		return issueType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.issue_type
	 * @param issueType  the value for t_business_storegrade.issue_type
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setIssueType(String issueType) {
		this.issueType = issueType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.experience
	 * @return  the value of t_business_storegrade.experience
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Integer getExperience() {
		return experience;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.experience
	 * @param experience  the value for t_business_storegrade.experience
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.default_lv
	 * @return  the value of t_business_storegrade.default_lv
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getDefaultLv() {
		return defaultLv;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.default_lv
	 * @param defaultLv  the value for t_business_storegrade.default_lv
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setDefaultLv(String defaultLv) {
		this.defaultLv = defaultLv;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.certification
	 * @return  the value of t_business_storegrade.certification
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getCertification() {
		return certification;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.certification
	 * @param certification  the value for t_business_storegrade.certification
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setCertification(String certification) {
		this.certification = certification;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.disabled
	 * @return  the value of t_business_storegrade.disabled
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.disabled
	 * @param disabled  the value for t_business_storegrade.disabled
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.last_modify
	 * @return  the value of t_business_storegrade.last_modify
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Integer getLastModify() {
		return lastModify;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.last_modify
	 * @param lastModify  the value for t_business_storegrade.last_modify
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setLastModify(Integer lastModify) {
		this.lastModify = lastModify;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.d_order
	 * @return  the value of t_business_storegrade.d_order
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public Integer getdOrder() {
		return dOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.d_order
	 * @param dOrder  the value for t_business_storegrade.d_order
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setdOrder(Integer dOrder) {
		this.dOrder = dOrder;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the value of the database column t_business_storegrade.remark
	 * @return  the value of t_business_storegrade.remark
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the value of the database column t_business_storegrade.remark
	 * @param remark  the value for t_business_storegrade.remark
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", gradeId=").append(gradeId);
		sb.append(", goodsNum=").append(goodsNum);
		sb.append(", couponsNum=").append(couponsNum);
		sb.append(", themeNum=").append(themeNum);
		sb.append(", gradeMoney=").append(gradeMoney);
		sb.append(", gradeName=").append(gradeName);
		sb.append(", issueMoney=").append(issueMoney);
		sb.append(", issueType=").append(issueType);
		sb.append(", experience=").append(experience);
		sb.append(", defaultLv=").append(defaultLv);
		sb.append(", certification=").append(certification);
		sb.append(", disabled=").append(disabled);
		sb.append(", lastModify=").append(lastModify);
		sb.append(", dOrder=").append(dOrder);
		sb.append(", remark=").append(remark);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
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
		StoreGrade other = (StoreGrade) that;
		return (this.getGradeId() == null ? other.getGradeId() == null : this
				.getGradeId().equals(other.getGradeId()))
				&& (this.getGoodsNum() == null ? other.getGoodsNum() == null
						: this.getGoodsNum().equals(other.getGoodsNum()))
				&& (this.getCouponsNum() == null ? other.getCouponsNum() == null
						: this.getCouponsNum().equals(other.getCouponsNum()))
				&& (this.getThemeNum() == null ? other.getThemeNum() == null
						: this.getThemeNum().equals(other.getThemeNum()))
				&& (this.getGradeMoney() == null ? other.getGradeMoney() == null
						: this.getGradeMoney().equals(other.getGradeMoney()))
				&& (this.getGradeName() == null ? other.getGradeName() == null
						: this.getGradeName().equals(other.getGradeName()))
				&& (this.getIssueMoney() == null ? other.getIssueMoney() == null
						: this.getIssueMoney().equals(other.getIssueMoney()))
				&& (this.getIssueType() == null ? other.getIssueType() == null
						: this.getIssueType().equals(other.getIssueType()))
				&& (this.getExperience() == null ? other.getExperience() == null
						: this.getExperience().equals(other.getExperience()))
				&& (this.getDefaultLv() == null ? other.getDefaultLv() == null
						: this.getDefaultLv().equals(other.getDefaultLv()))
				&& (this.getCertification() == null ? other.getCertification() == null
						: this.getCertification().equals(
								other.getCertification()))
				&& (this.getDisabled() == null ? other.getDisabled() == null
						: this.getDisabled().equals(other.getDisabled()))
				&& (this.getLastModify() == null ? other.getLastModify() == null
						: this.getLastModify().equals(other.getLastModify()))
				&& (this.getdOrder() == null ? other.getdOrder() == null : this
						.getdOrder().equals(other.getdOrder()))
				&& (this.getRemark() == null ? other.getRemark() == null : this
						.getRemark().equals(other.getRemark()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table t_business_storegrade
	 * @mbggenerated  Tue Apr 28 16:21:26 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getGradeId() == null) ? 0 : getGradeId().hashCode());
		result = prime * result
				+ ((getGoodsNum() == null) ? 0 : getGoodsNum().hashCode());
		result = prime * result
				+ ((getCouponsNum() == null) ? 0 : getCouponsNum().hashCode());
		result = prime * result
				+ ((getThemeNum() == null) ? 0 : getThemeNum().hashCode());
		result = prime * result
				+ ((getGradeMoney() == null) ? 0 : getGradeMoney().hashCode());
		result = prime * result
				+ ((getGradeName() == null) ? 0 : getGradeName().hashCode());
		result = prime * result
				+ ((getIssueMoney() == null) ? 0 : getIssueMoney().hashCode());
		result = prime * result
				+ ((getIssueType() == null) ? 0 : getIssueType().hashCode());
		result = prime * result
				+ ((getExperience() == null) ? 0 : getExperience().hashCode());
		result = prime * result
				+ ((getDefaultLv() == null) ? 0 : getDefaultLv().hashCode());
		result = prime
				* result
				+ ((getCertification() == null) ? 0 : getCertification()
						.hashCode());
		result = prime * result
				+ ((getDisabled() == null) ? 0 : getDisabled().hashCode());
		result = prime * result
				+ ((getLastModify() == null) ? 0 : getLastModify().hashCode());
		result = prime * result
				+ ((getdOrder() == null) ? 0 : getdOrder().hashCode());
		result = prime * result
				+ ((getRemark() == null) ? 0 : getRemark().hashCode());
		return result;
	}
}