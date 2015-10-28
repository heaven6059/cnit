package com.cnit.yoyo.model.activity;

import java.io.Serializable;

public class CouponsPicShip implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_coupons_pic_ship.id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_coupons_pic_ship.cpns_id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    private Integer cpnsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_coupons_pic_ship.picture_id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    private Long pictureId;

    private String picturePath;
    
    
    
    public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_coupons_pic_ship
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_coupons_pic_ship.id
     *
     * @return the value of t_coupons_pic_ship.id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_coupons_pic_ship.id
     *
     * @param id the value for t_coupons_pic_ship.id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_coupons_pic_ship.cpns_id
     *
     * @return the value of t_coupons_pic_ship.cpns_id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    public Integer getCpnsId() {
        return cpnsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_coupons_pic_ship.cpns_id
     *
     * @param cpnsId the value for t_coupons_pic_ship.cpns_id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    public void setCpnsId(Integer cpnsId) {
        this.cpnsId = cpnsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_coupons_pic_ship.picture_id
     *
     * @return the value of t_coupons_pic_ship.picture_id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    public Long getPictureId() {
        return pictureId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_coupons_pic_ship.picture_id
     *
     * @param pictureId the value for t_coupons_pic_ship.picture_id
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupons_pic_ship
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cpnsId=").append(cpnsId);
        sb.append(", pictureId=").append(pictureId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupons_pic_ship
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
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
        CouponsPicShip other = (CouponsPicShip) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCpnsId() == null ? other.getCpnsId() == null : this.getCpnsId().equals(other.getCpnsId()))
            && (this.getPictureId() == null ? other.getPictureId() == null : this.getPictureId().equals(other.getPictureId()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_coupons_pic_ship
     *
     * @mbggenerated Tue Jul 21 09:24:12 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCpnsId() == null) ? 0 : getCpnsId().hashCode());
        result = prime * result + ((getPictureId() == null) ? 0 : getPictureId().hashCode());
        return result;
    }
}