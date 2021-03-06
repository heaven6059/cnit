package com.cnit.yoyo.dao.system;

import com.cnit.yoyo.model.system.SiteRoleResourceLink;

public interface SiteRoleResourceLinkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_storeroles_resource
     *
     * @mbggenerated Wed May 27 18:43:23 CST 2015
     */
    int deleteByPrimaryKey(Long rId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_storeroles_resource
     *
     * @mbggenerated Wed May 27 18:43:23 CST 2015
     */
    int insert(SiteRoleResourceLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_storeroles_resource
     *
     * @mbggenerated Wed May 27 18:43:23 CST 2015
     */
    int insertSelective(SiteRoleResourceLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_storeroles_resource
     *
     * @mbggenerated Wed May 27 18:43:23 CST 2015
     */
    SiteRoleResourceLink selectByPrimaryKey(Long rId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_storeroles_resource
     *
     * @mbggenerated Wed May 27 18:43:23 CST 2015
     */
    int updateByPrimaryKeySelective(SiteRoleResourceLink record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_storeroles_resource
     *
     * @mbggenerated Wed May 27 18:43:23 CST 2015
     */
    int updateByPrimaryKey(SiteRoleResourceLink record);

	int selectByResourceId(Long resourceId);
}