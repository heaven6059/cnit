package com.cnit.yoyo.myshiro.system.service;


import java.util.List;
import java.util.Set;

import com.cnit.yoyo.model.system.Role;

/**
 * 角色管理接口
* @author ssd
* @date 2015-4-30 下午4:17:36
 */
public interface RoleService {


	/**
	 * 
	*
	* @Description: 新增角色
	* @param @param role
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:17:49 
	* @return int    返回类型 
	* @throws
	 */
    public int createRole(Role role);
    
    /**
     * 
    *
    * @Description: 更新角色 
    * @param @param role
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:18:09 
    * @return int    返回类型 
    * @throws
     */
    public int updateRole(Role role);
    
    /**
     * 
    *
    * @Description: 删除角色 
    * @param @param roleId    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:18:22 
    * @return void    返回类型 
    * @throws
     */
    public void deleteRole(Long roleId);

    /**
     * 
    *
    * @Description: 查找角色
    * @param @param roleId
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:18:36 
    * @return Role    返回类型 
    * @throws
     */
    public Role findOne(Long roleId);
    
    /**
     * 
    *
    * @Description: 查找所有角色 
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:18:48 
    * @return List<Role>    返回类型 
    * @throws
     */
    public List<Role> findAll();

    /**
     * 根据角色编号得到角色标识符列表
     * @param roleIds
     * @return
     */
    Set<String> findRoles(Long... roleIds);

    /**
     * 根据角色编号得到权限字符串列表
     * @param roleIds
     * @return
     */
    Set<String> findPermissions(Long[] roleIds);
}
