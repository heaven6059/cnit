package com.cnit.yoyo.model.system;

import java.io.Serializable;
/**
 * 后台角色
 *@description <一句话功能简述>
 *@detail <功能详细描述>
 *@version 1.0.0
 */
public class RoleWithBLOBs extends Role implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_desktop_roles.WORKGROUND
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private String workground;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_desktop_roles.CAT
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private String cat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_desktop_roles.WORKGROUND
     *
     * @return the value of t_desktop_roles.WORKGROUND
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    public String getWorkground() {
        return workground;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_desktop_roles.WORKGROUND
     *
     * @param workground the value for t_desktop_roles.WORKGROUND
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    public void setWorkground(String workground) {
        this.workground = workground;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_desktop_roles.CAT
     *
     * @return the value of t_desktop_roles.CAT
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    public String getCat() {
        return cat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_desktop_roles.CAT
     *
     * @param cat the value for t_desktop_roles.CAT
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    public void setCat(String cat) {
        this.cat = cat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", workground=").append(workground);
        sb.append(", cat=").append(cat);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
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
        RoleWithBLOBs other = (RoleWithBLOBs) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRoleStatus() == null ? other.getRoleStatus() == null : this.getRoleStatus().equals(other.getRoleStatus()))
            && (this.getWorkground() == null ? other.getWorkground() == null : this.getWorkground().equals(other.getWorkground()))
            && (this.getCat() == null ? other.getCat() == null : this.getCat().equals(other.getCat()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRole() == null) ? 0 : getRole().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRoleStatus() == null) ? 0 : getRoleStatus().hashCode());
        result = prime * result + ((getWorkground() == null) ? 0 : getWorkground().hashCode());
        result = prime * result + ((getCat() == null) ? 0 : getCat().hashCode());
        return result;
    }
}