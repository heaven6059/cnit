package com.cnit.yoyo.myshiro.system.service;


import java.util.List;
import java.util.Set;

import com.cnit.yoyo.model.system.Resource;

/**
 * 资源管理接口
* @author ssd
* @date 2015-4-30 下午4:19:11
 */
public interface ResourceService {


	/**
	 * 
	*
	* @Description: 新增资源 
	* @param @param resource
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:19:23 
	* @return int    返回类型 
	* @throws
	 */
    public int createResource(Resource resource);
    
    /**
     * 
    *
    * @Description:更新资源 
    * @param @param resource
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:19:38 
    * @return int    返回类型 
    * @throws
     */
    public int updateResource(Resource resource);
    
    /**
     * 
    *
    * @Description: 删除资源
    * @param @param resourceId    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:19:50 
    * @return void    返回类型 
    * @throws
     */
    public void deleteResource(Long resourceId);

    /**
     * 
    *
    * @Description: 查找资源 
    * @param @param resourceId
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:20:02 
    * @return Resource    返回类型 
    * @throws
     */
    Resource findOne(Long resourceId);
    
    /**
     * 
    *
    * @Description: 查找所有资源 
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:20:13 
    * @return List<Resource>    返回类型 
    * @throws
     */
    List<Resource> findAll();
    
    /**
     * 根据用户权限得到菜单
     * @param permissions
     * @return
     */
    List<Resource> findMenus(Set<String> permissions);

}
