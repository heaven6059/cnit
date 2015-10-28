package com.cnit.yoyo.model.system;

import java.io.Serializable;

public class UrlFilter implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_url_filter.id
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    private Integer id;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_url_filter.name
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_url_filter.url
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_url_filter.roles
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    private String roles;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_url_filter.permissions
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    private String permissions;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_url_filter
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_url_filter.id
     *
     * @return the value of t_url_filter.id
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_url_filter.name
     *
     * @return the value of t_url_filter.name
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_url_filter.name
     *
     * @param name the value for t_url_filter.name
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_url_filter.url
     *
     * @return the value of t_url_filter.url
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_url_filter.url
     *
     * @param url the value for t_url_filter.url
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_url_filter.roles
     *
     * @return the value of t_url_filter.roles
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public String getRoles() {
        return roles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_url_filter.roles
     *
     * @param roles the value for t_url_filter.roles
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_url_filter.permissions
     *
     * @return the value of t_url_filter.permissions
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public String getPermissions() {
        return permissions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_url_filter.permissions
     *
     * @param permissions the value for t_url_filter.permissions
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_url_filter
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", url=").append(url);
        sb.append(", roles=").append(roles);
        sb.append(", permissions=").append(permissions);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_url_filter
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
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
        UrlFilter other = (UrlFilter) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getRoles() == null ? other.getRoles() == null : this.getRoles().equals(other.getRoles()))
            && (this.getPermissions() == null ? other.getPermissions() == null : this.getPermissions().equals(other.getPermissions()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_url_filter
     *
     * @mbggenerated Tue Mar 24 17:41:18 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getRoles() == null) ? 0 : getRoles().hashCode());
        result = prime * result + ((getPermissions() == null) ? 0 : getPermissions().hashCode());
        return result;
    }
}