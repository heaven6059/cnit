package com.cnit.yoyo.dao.system;

import java.util.List;

import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.model.system.ResourceRoleLink;
import com.cnit.yoyo.model.system.dto.ResourceListDTO;
import com.cnit.yoyo.model.system.dto.ResourceQryDTO;
import com.cnit.yoyo.model.system.dto.ResourceTreeDTO;

public interface ResourceMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int insert(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int insertSelective(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    Resource selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int updateByPrimaryKeySelective(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(Resource record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_desktop_resource
     *
     * @mbggenerated Wed Mar 25 19:51:14 CST 2015
     */
    int updateByPrimaryKey(Resource record);
    
    Resource selectResourceRole(Integer id);
    
    int saveResourceRoleLink(ResourceRoleLink rrl);
    
    int deleteResourceRoleLink(ResourceRoleLink rrl);
    
    /**
     * 
     * @Description: 查找所有菜单  
     * @param @return
     * @author xiaox
     * @date 2015-4-2 上午10:28:12
     */
    List<Resource> findAllMenu(Integer pid);
    
    List<Resource> selectByParentId(Integer parentId);
    
    List<ResourceListDTO> selectAll();
    
    List<ResourceTreeDTO> selectAllTree();
    
    List<Resource> selectByAccountId(ResourceQryDTO qry);

	List<Resource> findMenuByRoleId(Integer roleId);
}