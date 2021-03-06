package com.cnit.yoyo.model.spider;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class AutohomeCarInfo implements Serializable {
	private List<AutohomeCarAttr> autohomeCarAttrs;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.NAME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.PID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String pid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.IMG_PATH
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String imgPath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.AUTOHOME_URL
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String autohomeUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.CREATE_TIME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.UPDATE_TIME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.SCOPE_ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private Integer scopeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.MADE_ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String madeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.YEAR
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String year;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_autohome_car_info.LEVEL
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private String level;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_autohome_car_info
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.ID
     *
     * @return the value of t_autohome_car_info.ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.ID
     *
     * @param id the value for t_autohome_car_info.ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.NAME
     *
     * @return the value of t_autohome_car_info.NAME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.NAME
     *
     * @param name the value for t_autohome_car_info.NAME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.PID
     *
     * @return the value of t_autohome_car_info.PID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getPid() {
        return pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.PID
     *
     * @param pid the value for t_autohome_car_info.PID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.IMG_PATH
     *
     * @return the value of t_autohome_car_info.IMG_PATH
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.IMG_PATH
     *
     * @param imgPath the value for t_autohome_car_info.IMG_PATH
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.AUTOHOME_URL
     *
     * @return the value of t_autohome_car_info.AUTOHOME_URL
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getAutohomeUrl() {
        return autohomeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.AUTOHOME_URL
     *
     * @param autohomeUrl the value for t_autohome_car_info.AUTOHOME_URL
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setAutohomeUrl(String autohomeUrl) {
        this.autohomeUrl = autohomeUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.CREATE_TIME
     *
     * @return the value of t_autohome_car_info.CREATE_TIME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.CREATE_TIME
     *
     * @param createTime the value for t_autohome_car_info.CREATE_TIME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.UPDATE_TIME
     *
     * @return the value of t_autohome_car_info.UPDATE_TIME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.UPDATE_TIME
     *
     * @param updateTime the value for t_autohome_car_info.UPDATE_TIME
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.SCOPE_ID
     *
     * @return the value of t_autohome_car_info.SCOPE_ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public Integer getScopeId() {
        return scopeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.SCOPE_ID
     *
     * @param scopeId the value for t_autohome_car_info.SCOPE_ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setScopeId(Integer scopeId) {
        this.scopeId = scopeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.MADE_ID
     *
     * @return the value of t_autohome_car_info.MADE_ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getMadeId() {
        return madeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.MADE_ID
     *
     * @param madeId the value for t_autohome_car_info.MADE_ID
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setMadeId(String madeId) {
        this.madeId = madeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.YEAR
     *
     * @return the value of t_autohome_car_info.YEAR
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getYear() {
        return year;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.YEAR
     *
     * @param year the value for t_autohome_car_info.YEAR
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_autohome_car_info.LEVEL
     *
     * @return the value of t_autohome_car_info.LEVEL
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public String getLevel() {
        return level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_autohome_car_info.LEVEL
     *
     * @param level the value for t_autohome_car_info.LEVEL
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    public void setLevel(String level) {
        this.level = level;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_autohome_car_info
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", pid=").append(pid);
        sb.append(", imgPath=").append(imgPath);
        sb.append(", autohomeUrl=").append(autohomeUrl);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", scopeId=").append(scopeId);
        sb.append(", madeId=").append(madeId);
        sb.append(", year=").append(year);
        sb.append(", level=").append(level);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_autohome_car_info
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
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
        AutohomeCarInfo other = (AutohomeCarInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getImgPath() == null ? other.getImgPath() == null : this.getImgPath().equals(other.getImgPath()))
            && (this.getAutohomeUrl() == null ? other.getAutohomeUrl() == null : this.getAutohomeUrl().equals(other.getAutohomeUrl()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getScopeId() == null ? other.getScopeId() == null : this.getScopeId().equals(other.getScopeId()))
            && (this.getMadeId() == null ? other.getMadeId() == null : this.getMadeId().equals(other.getMadeId()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_autohome_car_info
     *
     * @mbggenerated Thu Aug 06 11:17:30 CST 2015
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getImgPath() == null) ? 0 : getImgPath().hashCode());
        result = prime * result + ((getAutohomeUrl() == null) ? 0 : getAutohomeUrl().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getScopeId() == null) ? 0 : getScopeId().hashCode());
        result = prime * result + ((getMadeId() == null) ? 0 : getMadeId().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        return result;
    }
    public List<AutohomeCarAttr> getAutohomeCarAttrs() {
		return autohomeCarAttrs;
	}
	public void setAutohomeCarAttrs(List<AutohomeCarAttr> autohomeCarAttrs) {
		this.autohomeCarAttrs = autohomeCarAttrs;
	}
}