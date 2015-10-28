package com.cnit.yoyo.model.member;

import java.io.Serializable;
/**
 * 店员角色
 *@description <一句话功能简述>
 *@detail <功能详细描述>
 *@version 1.0.0
 */
public class Roles implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_storeroles.roles_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private Long rolesId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_storeroles.roles_name
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private String rolesName;
    
    private String description;
    
    private String disabled;

    public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_storeroles.company_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private Long companyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_storeroles.store_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private Long storeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_storeroles.regtime
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private Long regtime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_business_storeroles.workground
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private String workground;

    private Long lastModifyTime;
    
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_storeroles.roles_id
     *
     * @return the value of t_business_storeroles.roles_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public Long getRolesId() {
        return rolesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_storeroles.roles_id
     *
     * @param rolesId the value for t_business_storeroles.roles_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public void setRolesId(Long rolesId) {
        this.rolesId = rolesId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_storeroles.roles_name
     *
     * @return the value of t_business_storeroles.roles_name
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public String getRolesName() {
        return rolesName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_storeroles.roles_name
     *
     * @param rolesName the value for t_business_storeroles.roles_name
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public void setRolesName(String rolesName) {
        this.rolesName = rolesName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_storeroles.company_id
     *
     * @return the value of t_business_storeroles.company_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public Long getCompanyId() {
        return companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_storeroles.company_id
     *
     * @param companyId the value for t_business_storeroles.company_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_storeroles.store_id
     *
     * @return the value of t_business_storeroles.store_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public Long getStoreId() {
        return storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_storeroles.store_id
     *
     * @param storeId the value for t_business_storeroles.store_id
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_storeroles.regtime
     *
     * @return the value of t_business_storeroles.regtime
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public Long getRegtime() {
        return regtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_storeroles.regtime
     *
     * @param regtime the value for t_business_storeroles.regtime
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public void setRegtime(Long regtime) {
        this.regtime = regtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_business_storeroles.workground
     *
     * @return the value of t_business_storeroles.workground
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public String getWorkground() {
        return workground;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_business_storeroles.workground
     *
     * @param workground the value for t_business_storeroles.workground
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    public void setWorkground(String workground) {
        this.workground = workground;
    }

    public Long getLastModifyTime() {
		return lastModifyTime;
	}

	public void setLastModifyTime(Long lastModifyTime) {
		this.lastModifyTime = lastModifyTime;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rolesId=").append(rolesId);
        sb.append(", rolesName=").append(rolesName);
        sb.append(", companyId=").append(companyId);
        sb.append(", storeId=").append(storeId);
        sb.append(", regtime=").append(regtime);
        sb.append(", workground=").append(workground);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
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
        Roles other = (Roles) that;
        return (this.getRolesId() == null ? other.getRolesId() == null : this.getRolesId().equals(other.getRolesId()))
            && (this.getRolesName() == null ? other.getRolesName() == null : this.getRolesName().equals(other.getRolesName()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getStoreId() == null ? other.getStoreId() == null : this.getStoreId().equals(other.getStoreId()))
            && (this.getRegtime() == null ? other.getRegtime() == null : this.getRegtime().equals(other.getRegtime()))
            && (this.getWorkground() == null ? other.getWorkground() == null : this.getWorkground().equals(other.getWorkground()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRolesId() == null) ? 0 : getRolesId().hashCode());
        result = prime * result + ((getRolesName() == null) ? 0 : getRolesName().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getStoreId() == null) ? 0 : getStoreId().hashCode());
        result = prime * result + ((getRegtime() == null) ? 0 : getRegtime().hashCode());
        result = prime * result + ((getWorkground() == null) ? 0 : getWorkground().hashCode());
        return result;
    }

    public String getDisabled() {
        return disabled;
    }

    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
    
    
}