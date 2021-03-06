package com.cnit.yoyo.model.system;

import java.io.Serializable;

public class SiteRole implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_site_roles.ROLE_ID
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    private Integer roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_site_roles.ROLE_NAME
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_site_roles.ROLE
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    private String role;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_site_roles.DESCRIPTION
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_site_roles.ROLE_STATUS
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    private String roleStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_site_roles
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_site_roles.ROLE_ID
     *
     * @return the value of t_site_roles.ROLE_ID
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_site_roles.ROLE_ID
     *
     * @param roleId the value for t_site_roles.ROLE_ID
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_site_roles.ROLE_NAME
     *
     * @return the value of t_site_roles.ROLE_NAME
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_site_roles.ROLE_NAME
     *
     * @param roleName the value for t_site_roles.ROLE_NAME
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_site_roles.ROLE
     *
     * @return the value of t_site_roles.ROLE
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public String getRole() {
        return role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_site_roles.ROLE
     *
     * @param role the value for t_site_roles.ROLE
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_site_roles.DESCRIPTION
     *
     * @return the value of t_site_roles.DESCRIPTION
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_site_roles.DESCRIPTION
     *
     * @param description the value for t_site_roles.DESCRIPTION
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_site_roles.ROLE_STATUS
     *
     * @return the value of t_site_roles.ROLE_STATUS
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public String getRoleStatus() {
        return roleStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_site_roles.ROLE_STATUS
     *
     * @param roleStatus the value for t_site_roles.ROLE_STATUS
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    public void setRoleStatus(String roleStatus) {
        this.roleStatus = roleStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_site_roles
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", roleId=").append(roleId);
        sb.append(", roleName=").append(roleName);
        sb.append(", role=").append(role);
        sb.append(", description=").append(description);
        sb.append(", roleStatus=").append(roleStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_site_roles
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
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
        SiteRole other = (SiteRole) that;
        return (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getRole() == null ? other.getRole() == null : this.getRole().equals(other.getRole()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRoleStatus() == null ? other.getRoleStatus() == null : this.getRoleStatus().equals(other.getRoleStatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_site_roles
     *
     * @mbggenerated Thu Jun 04 13:18:32 CST 2015
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
        return result;
    }
}