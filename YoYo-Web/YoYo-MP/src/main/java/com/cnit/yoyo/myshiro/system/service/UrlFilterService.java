package com.cnit.yoyo.myshiro.system.service;


import java.util.List;

import com.cnit.yoyo.model.system.UrlFilter;

/**
 * 添加路径过滤器接口
* @author ssd
* @date 2015-4-30 下午4:09:30
 */
public interface UrlFilterService {

	/**
	 * 
	*
	* @Description: 新增路径过滤器 
	* @param @param urlFilter
	* @param @return
	* @param @throws Exception    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:10:03 
	* @return UrlFilter    返回类型 
	* @throws
	 */
    public void createUrlFilter() throws Exception;
    
    /**
     * 
    *
    * @Description: 更新路径过滤器 
    * @param @param urlFilter
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:10:20 
    * @return UrlFilter    返回类型 
    * @throws
     */
    public void updateUrlFilter() throws Exception;
    
    /**
     * 
    *
    * @Description: 删除路径过滤器
    * @param @param urlFilterId
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:10:38 
    * @return void    返回类型 
    * @throws
     */
    public void deleteUrlFilter() throws Exception;

    /**
     * 
    *
    * @Description: 查找路径过滤器 
    * @param @param urlFilterId
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:11:00 
    * @return UrlFilter    返回类型 
    * @throws
     */
    public UrlFilter findOne(Long urlFilterId);
    
    /**
     * 
    *
    * @Description:查找所有路径过滤器
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:11:15 
    * @return List<UrlFilter>    返回类型 
    * @throws
     */
    public List<UrlFilter> findAll() throws Exception;
}
