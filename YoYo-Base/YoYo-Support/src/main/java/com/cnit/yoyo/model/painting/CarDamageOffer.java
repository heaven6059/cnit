package com.cnit.yoyo.model.painting;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.goods.Company;

public class CarDamageOffer implements Serializable {
	
	private Company company;
	
    private List<CarDamageOfferDetail> offerDetailList;

    public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public List<CarDamageOfferDetail> getOfferDetailList() {
		return offerDetailList;
	}

	public void setOfferDetailList(List<CarDamageOfferDetail> offerDetailList) {
		this.offerDetailList = offerDetailList;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car_damage_offer.id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car_damage_offer.company_id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private Integer companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car_damage_offer.car_damage_id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private Integer carDamageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car_damage_offer.disabled
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private Boolean disabled;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car_damage_offer.totalPrice
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private BigDecimal totalprice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_car_damage_offer.createtime
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private Date createtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car_damage_offer.id
     *
     * @return the value of t_car_damage_offer.id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_car_damage_offer.id
     *
     * @param id the value for t_car_damage_offer.id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car_damage_offer.company_id
     *
     * @return the value of t_car_damage_offer.company_id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_car_damage_offer.company_id
     *
     * @param companyId the value for t_car_damage_offer.company_id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car_damage_offer.car_damage_id
     *
     * @return the value of t_car_damage_offer.car_damage_id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public Integer getCarDamageId() {
        return carDamageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_car_damage_offer.car_damage_id
     *
     * @param carDamageId the value for t_car_damage_offer.car_damage_id
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public void setCarDamageId(Integer carDamageId) {
        this.carDamageId = carDamageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car_damage_offer.disabled
     *
     * @return the value of t_car_damage_offer.disabled
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_car_damage_offer.disabled
     *
     * @param disabled the value for t_car_damage_offer.disabled
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car_damage_offer.totalPrice
     *
     * @return the value of t_car_damage_offer.totalPrice
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public BigDecimal getTotalprice() {
        return totalprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_car_damage_offer.totalPrice
     *
     * @param totalprice the value for t_car_damage_offer.totalPrice
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public void setTotalprice(BigDecimal totalprice) {
        this.totalprice = totalprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_car_damage_offer.createtime
     *
     * @return the value of t_car_damage_offer.createtime
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_car_damage_offer.createtime
     *
     * @param createtime the value for t_car_damage_offer.createtime
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", companyId=").append(companyId);
        sb.append(", carDamageId=").append(carDamageId);
        sb.append(", disabled=").append(disabled);
        sb.append(", totalprice=").append(totalprice);
        sb.append(", createtime=").append(createtime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
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
        CarDamageOffer other = (CarDamageOffer) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCarDamageId() == null ? other.getCarDamageId() == null : this.getCarDamageId().equals(other.getCarDamageId()))
            && (this.getDisabled() == null ? other.getDisabled() == null : this.getDisabled().equals(other.getDisabled()))
            && (this.getTotalprice() == null ? other.getTotalprice() == null : this.getTotalprice().equals(other.getTotalprice()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_car_damage_offer
     *
     * @mbggenerated Sat May 16 16:02:57 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCarDamageId() == null) ? 0 : getCarDamageId().hashCode());
        result = prime * result + ((getDisabled() == null) ? 0 : getDisabled().hashCode());
        result = prime * result + ((getTotalprice() == null) ? 0 : getTotalprice().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        return result;
    }
}