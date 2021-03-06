package com.cnit.yoyo.model.shop;

import java.io.Serializable;

public class ShopBrandWithBLOBs extends ShopBrand implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_brand.brand_keywords
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    private String brandKeywords;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_brand.brand_desc
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    private String brandDesc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_brand.fail_reason
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    private String failReason;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_business_brand
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_brand.brand_keywords
     *
     * @return the value of t_business_brand.brand_keywords
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    public String getBrandKeywords() {
        return brandKeywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_brand.brand_keywords
     *
     * @param brandKeywords the value for t_business_brand.brand_keywords
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    public void setBrandKeywords(String brandKeywords) {
        this.brandKeywords = brandKeywords;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_brand.brand_desc
     *
     * @return the value of t_business_brand.brand_desc
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    public String getBrandDesc() {
        return brandDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_brand.brand_desc
     *
     * @param brandDesc the value for t_business_brand.brand_desc
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_brand.fail_reason
     *
     * @return the value of t_business_brand.fail_reason
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_brand.fail_reason
     *
     * @param failReason the value for t_business_brand.fail_reason
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_brand
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", brandKeywords=").append(brandKeywords);
        sb.append(", brandDesc=").append(brandDesc);
        sb.append(", failReason=").append(failReason);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_brand
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
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
        ShopBrandWithBLOBs other = (ShopBrandWithBLOBs) that;
        return (this.getCompanyBrandId() == null ? other.getCompanyBrandId() == null : this.getCompanyBrandId().equals(other.getCompanyBrandId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()))
            && (this.getBrandName() == null ? other.getBrandName() == null : this.getBrandName().equals(other.getBrandName()))
            && (this.getStoreCat() == null ? other.getStoreCat() == null : this.getStoreCat().equals(other.getStoreCat()))
            && (this.getBrandUrl() == null ? other.getBrandUrl() == null : this.getBrandUrl().equals(other.getBrandUrl()))
            && (this.getBrandLogo() == null ? other.getBrandLogo() == null : this.getBrandLogo().equals(other.getBrandLogo()))
            && (this.getBrandAptitude() == null ? other.getBrandAptitude() == null : this.getBrandAptitude().equals(other.getBrandAptitude()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getBrandKeywords() == null ? other.getBrandKeywords() == null : this.getBrandKeywords().equals(other.getBrandKeywords()))
            && (this.getBrandDesc() == null ? other.getBrandDesc() == null : this.getBrandDesc().equals(other.getBrandDesc()))
            && (this.getFailReason() == null ? other.getFailReason() == null : this.getFailReason().equals(other.getFailReason()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_brand
     *
     * @mbggenerated Tue Mar 24 10:13:14 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCompanyBrandId() == null) ? 0 : getCompanyBrandId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        result = prime * result + ((getBrandName() == null) ? 0 : getBrandName().hashCode());
        result = prime * result + ((getStoreCat() == null) ? 0 : getStoreCat().hashCode());
        result = prime * result + ((getBrandUrl() == null) ? 0 : getBrandUrl().hashCode());
        result = prime * result + ((getBrandLogo() == null) ? 0 : getBrandLogo().hashCode());
        result = prime * result + ((getBrandAptitude() == null) ? 0 : getBrandAptitude().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getBrandKeywords() == null) ? 0 : getBrandKeywords().hashCode());
        result = prime * result + ((getBrandDesc() == null) ? 0 : getBrandDesc().hashCode());
        result = prime * result + ((getFailReason() == null) ? 0 : getFailReason().hashCode());
        return result;
    }
}