package com.cnit.yoyo.dao.system;

import java.util.List;

import com.cnit.yoyo.model.system.Role;
import com.cnit.yoyo.model.system.RoleWithBLOBs;
import com.cnit.yoyo.model.system.dto.RoleListDTO;
import com.cnit.yoyo.model.system.dto.UserListDTO;

public interface RoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int deleteByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int insert(RoleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int insertSelective(RoleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    RoleWithBLOBs selectByPrimaryKey(Integer roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int updateByPrimaryKeySelective(RoleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(RoleWithBLOBs record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_roles
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int updateByPrimaryKey(Role record);
    
    Role selectroleUser(Integer roleId);
    
    Role selectroleResource(Integer roleId);

	List<RoleWithBLOBs> findAll();

	List<Role> findSelect();
	
	List<RoleListDTO> selectAllRoleResource();
	
	Role findByRolename(String roleName);
}