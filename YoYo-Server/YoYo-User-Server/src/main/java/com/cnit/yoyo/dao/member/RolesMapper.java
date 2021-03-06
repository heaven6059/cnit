package com.cnit.yoyo.dao.member;

import java.util.List;

import com.cnit.yoyo.model.member.Roles;
import com.cnit.yoyo.model.member.dto.RolesDTO;
import com.cnit.yoyo.model.system.Role;
import com.cnit.yoyo.model.system.dto.SiteRoleListDTO;

public interface RolesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    int deleteByPrimaryKey(Long rolesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    int insert(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    int insertSelective(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    Roles selectByPrimaryKey(Long rolesId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    int updateByPrimaryKeySelective(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    int updateByPrimaryKeyWithBLOBs(Roles record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_business_storeroles
     *
     * @mbggenerated Fri Mar 13 16:52:56 CST 2015
     */
    int updateByPrimaryKey(Roles record);
    
    /**
     * 
     *@description 通过ID拿到角色名
     *@detail <方法详细描述>
     *@author <a href="liguanghua@chinacnit.com">李光华</a>
     *@version 1.0.0
     *@data 2015-3-16
     *@param role
     *@return
     */
    List<Roles> findRolesNameById(Roles role);
    
    /**
     * @Title: findByStoreId 
     * @Description: TODO(根据店铺id分页查询店铺角色列表) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-22 下午4:10:34 
     * @version 1.0.0 
     * @param storeId
     * @param @return    
     * @return List<Roles>    返回类型 
     * @throws
     */
    List<RolesDTO> findByStoreId(Long storeId);
    
    /**
     * @Title: findByStoreAndName 
     * @Description: TODO(根据店铺id和角色名称查询Roles对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午4:48:11 
     * @version 1.0.0 
     * @param storeId
     * @param rolesName
     * @param @return    
     * @return List<Roles>    返回类型 
     * @throws
     */
    List<Roles> findByStoreAndName(Long storeId, String rolesName);
    
    /**
     * @Title: updateNameByRolesId 
     * @Description: TODO(根据rolesId修改角色名称) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:33:29 
     * @version 1.0.0 
     * @param @param rolesId
     * @param @param rolesName
     * @param @return    
     * @return int    返回类型 
     * @throws
     */
    int updateNameByRolesId(Long rolesId, String rolesName, Long lastModifyTime);
    
    /**
     * @Title:  deleteByRolesIdAndStore  
     * @Description:  TODO(根据rolesId和storeId删除roles对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-27 下午1:17:00  
     * @version 1.0.0 
     * @param @param rolesId
     * @param @param storeId
     * @param @return
     * @return int  返回类型 
     * @throws
     */
    int deleteByRolesIdAndStore(Long rolesId, Long storeId);
    
    List<Roles> findSelect(Long storeId);
    
    Roles findRoleByUserName(String loginName);

	List<SiteRoleListDTO> selectAllRoleResource();
	
	List<RolesDTO> findShopAllRoles(RolesDTO dto);
	
	void deleteRole(Integer[] ids);
}