package com.cnit.yoyo.model.member;

import java.io.Serializable;

public class MemberWithBLOBs extends Member implements Serializable {

    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_members.INTEREST
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    private String interest;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_members.FAV_TAGS
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    private String favTags;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_members.ADDON
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    private String addon;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database column t_members.REMARK
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    private String remark;
    /**
     * This field was generated by MyBatis Generator. This field corresponds to the database table t_members
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_members.INTEREST
     * @return  the value of t_members.INTEREST
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public String getInterest() {
        return interest;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_members.INTEREST
     * @param interest  the value for t_members.INTEREST
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public void setInterest(String interest) {
        this.interest = interest;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_members.FAV_TAGS
     * @return  the value of t_members.FAV_TAGS
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public String getFavTags() {
        return favTags;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_members.FAV_TAGS
     * @param favTags  the value for t_members.FAV_TAGS
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public void setFavTags(String favTags) {
        this.favTags = favTags;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_members.ADDON
     * @return  the value of t_members.ADDON
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public String getAddon() {
        return addon;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_members.ADDON
     * @param addon  the value for t_members.ADDON
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public void setAddon(String addon) {
        this.addon = addon;
    }

    /**
     * This method was generated by MyBatis Generator. This method returns the value of the database column t_members.REMARK
     * @return  the value of t_members.REMARK
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator. This method sets the value of the database column t_members.REMARK
     * @param remark  the value for t_members.REMARK
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

	/**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_members
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", interest=").append(interest);
        sb.append(", favTags=").append(favTags);
        sb.append(", addon=").append(addon);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


	/**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_members
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
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
        MemberWithBLOBs other = (MemberWithBLOBs) that;
        return (this.getMemberId() == null ? other.getMemberId() == null : this.getMemberId().equals(other.getMemberId()))
                && (this.getMemberLvId() == null ? other.getMemberLvId() == null : this.getMemberLvId().equals(other.getMemberLvId()))
                && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
                && (this.getNickName() == null ? other.getNickName() == null : this.getNickName().equals(other.getNickName()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getIdcard() == null ? other.getIdcard() == null : this.getIdcard().equals(other.getIdcard()))
                && (this.getFirstName() == null ? other.getFirstName() == null : this.getFirstName().equals(other.getFirstName()))
                && (this.getLastName() == null ? other.getLastName() == null : this.getLastName().equals(other.getLastName()))
                && (this.getbYear() == null ? other.getbYear() == null : this.getbYear().equals(other.getbYear()))
                && (this.getbMonth() == null ? other.getbMonth() == null : this.getbMonth().equals(other.getbMonth()))
                && (this.getbDay() == null ? other.getbDay() == null : this.getbDay().equals(other.getbDay()))
                && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
                && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
                && (this.getTel() == null ? other.getTel() == null : this.getTel().equals(other.getTel()))
                && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
                && (this.getArea() == null ? other.getArea() == null : this.getArea().equals(other.getArea()))
                && (this.getAddr() == null ? other.getAddr() == null : this.getAddr().equals(other.getAddr()))
                && (this.getZip() == null ? other.getZip() == null : this.getZip().equals(other.getZip()))
                && (this.getWedlock() == null ? other.getWedlock() == null : this.getWedlock().equals(other.getWedlock()))
                && (this.getEducation() == null ? other.getEducation() == null : this.getEducation().equals(other.getEducation()))
                && (this.getVocation() == null ? other.getVocation() == null : this.getVocation().equals(other.getVocation()))
                && (this.getCur() == null ? other.getCur() == null : this.getCur().equals(other.getCur()))
                && (this.getLang() == null ? other.getLang() == null : this.getLang().equals(other.getLang()))
                && (this.getAdvance() == null ? other.getAdvance() == null : this.getAdvance().equals(other.getAdvance()))
                && (this.getAdvanceFreeze() == null ? other.getAdvanceFreeze() == null : this.getAdvanceFreeze().equals(other.getAdvanceFreeze()))
                && (this.getExperience() == null ? other.getExperience() == null : this.getExperience().equals(other.getExperience()))
                && (this.getPointSummation() == null ? other.getPointSummation() == null : this.getPointSummation().equals(other.getPointSummation()))
                && (this.getPointUseable() == null ? other.getPointUseable() == null : this.getPointUseable().equals(other.getPointUseable()))
                && (this.getPointUsed() == null ? other.getPointUsed() == null : this.getPointUsed().equals(other.getPointUsed()))
                && (this.getPointDisrate() == null ? other.getPointDisrate() == null : this.getPointDisrate().equals(other.getPointDisrate()))
                && (this.getSettledTime() == null ? other.getSettledTime() == null : this.getSettledTime().equals(other.getSettledTime()))
                && (this.getBizMoney() == null ? other.getBizMoney() == null : this.getBizMoney().equals(other.getBizMoney()))
                && (this.getReferId() == null ? other.getReferId() == null : this.getReferId().equals(other.getReferId()))
                && (this.getReferUrl() == null ? other.getReferUrl() == null : this.getReferUrl().equals(other.getReferUrl()))
                && (this.getInfoIntegrity() == null ? other.getInfoIntegrity() == null : this.getInfoIntegrity().equals(other.getInfoIntegrity()))
                && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
                && (this.getOrderNum() == null ? other.getOrderNum() == null : this.getOrderNum().equals(other.getOrderNum()))
                && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
                && (this.getFavTags() == null ? other.getFavTags() == null : this.getFavTags().equals(other.getFavTags()))
                && (this.getAddon() == null ? other.getAddon() == null : this.getAddon().equals(other.getAddon()))
                && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table t_members
     * @mbggenerated  Mon Feb 09 17:07:07 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMemberId() == null) ? 0 : getMemberId().hashCode());
        result = prime * result + ((getMemberLvId() == null) ? 0 : getMemberLvId().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getNickName() == null) ? 0 : getNickName().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIdcard() == null) ? 0 : getIdcard().hashCode());
        result = prime * result + ((getFirstName() == null) ? 0 : getFirstName().hashCode());
        result = prime * result + ((getLastName() == null) ? 0 : getLastName().hashCode());
        result = prime * result + ((getbYear() == null) ? 0 : getbYear().hashCode());
        result = prime * result + ((getbMonth() == null) ? 0 : getbMonth().hashCode());
        result = prime * result + ((getbDay() == null) ? 0 : getbDay().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getTel() == null) ? 0 : getTel().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getArea() == null) ? 0 : getArea().hashCode());
        result = prime * result + ((getAddr() == null) ? 0 : getAddr().hashCode());
        result = prime * result + ((getZip() == null) ? 0 : getZip().hashCode());
        result = prime * result + ((getWedlock() == null) ? 0 : getWedlock().hashCode());
        result = prime * result + ((getEducation() == null) ? 0 : getEducation().hashCode());
        result = prime * result + ((getVocation() == null) ? 0 : getVocation().hashCode());
        result = prime * result + ((getCur() == null) ? 0 : getCur().hashCode());
        result = prime * result + ((getLang() == null) ? 0 : getLang().hashCode());
        result = prime * result + ((getAdvance() == null) ? 0 : getAdvance().hashCode());
        result = prime * result + ((getAdvanceFreeze() == null) ? 0 : getAdvanceFreeze().hashCode());
        result = prime * result + ((getExperience() == null) ? 0 : getExperience().hashCode());
        result = prime * result + ((getPointSummation() == null) ? 0 : getPointSummation().hashCode());
        result = prime * result + ((getPointUseable() == null) ? 0 : getPointUseable().hashCode());
        result = prime * result + ((getPointUsed() == null) ? 0 : getPointUsed().hashCode());
        result = prime * result + ((getPointDisrate() == null) ? 0 : getPointDisrate().hashCode());
        result = prime * result + ((getSettledTime() == null) ? 0 : getSettledTime().hashCode());
        result = prime * result + ((getBizMoney() == null) ? 0 : getBizMoney().hashCode());
        result = prime * result + ((getReferId() == null) ? 0 : getReferId().hashCode());
        result = prime * result + ((getReferUrl() == null) ? 0 : getReferUrl().hashCode());
        result = prime * result + ((getInfoIntegrity() == null) ? 0 : getInfoIntegrity().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getOrderNum() == null) ? 0 : getOrderNum().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getFavTags() == null) ? 0 : getFavTags().hashCode());
        result = prime * result + ((getAddon() == null) ? 0 : getAddon().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }
}