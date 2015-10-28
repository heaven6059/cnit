package com.cnit.yoyo.model.system;

import java.io.Serializable;

import com.cnit.yoyo.model.member.PamAccount;

public class RoleUserLink implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_user.ID
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private Integer id;
    
    private Integer accountId;
    
    private Integer roleId;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_user.USER_ID
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private PamAccount user;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_user.ROLE_ID
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private Role role;

    public PamAccount getUser() {
		return user;
	}

	public void setUser(PamAccount user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_role_user.USER_TYPE
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private String userType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_role_user
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_user.ID
     *
     * @return the value of t_role_user.ID
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_role_user.USER_TYPE
     *
     * @return the value of t_role_user.USER_TYPE
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    public String getUserType() {
        return userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_role_user.USER_TYPE
     *
     * @param userType the value for t_role_user.USER_TYPE
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_user
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(user.getAccountId());
        sb.append(", roleId=").append(role.getRoleId());
        sb.append(", userType=").append(userType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_user
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
        RoleUserLink other = (RoleUserLink) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (user.getAccountId() == null ? other.getUser().getAccountId() == null : user.getAccountId().equals(other.getUser().getAccountId()))
            && (role.getRoleId() == null ? other.getRole().getRoleId() == null : role.getRoleId().equals(other.getRole().getRoleId()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role_user
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((user.getAccountId() == null) ? 0 : user.getAccountId().hashCode());
        result = prime * result + ((role.getRoleId() == null) ? 0 : role.getRoleId().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        return result;
    }

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}