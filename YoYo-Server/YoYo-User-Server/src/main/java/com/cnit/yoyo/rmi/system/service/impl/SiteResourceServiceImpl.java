package com.cnit.yoyo.rmi.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.system.SiteResourceMapper;
import com.cnit.yoyo.dao.system.SiteRoleResourceLinkMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.model.system.SiteResource;
import com.cnit.yoyo.model.system.Resource.ResourceType;
import com.cnit.yoyo.model.system.SiteResource.SiteResourceType;
import com.cnit.yoyo.model.system.dto.ResourceAddDTO;
import com.cnit.yoyo.model.system.dto.ResourceListDTO;
import com.cnit.yoyo.model.system.dto.ResourceQryDTO;
import com.cnit.yoyo.model.system.dto.ResourceTreeDTO;
import com.cnit.yoyo.model.system.dto.SiteResourceAddDTO;
import com.cnit.yoyo.model.system.dto.SiteResourceListDTO;
import com.cnit.yoyo.model.system.dto.SiteResourceQryDTO;
import com.cnit.yoyo.model.system.dto.SiteResourceTreeDTO;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 门户资源管理类
* @author ssd
* @date 2015-4-30 下午4:49:53
 */
@Service("siteResourceServiceImpl")
public class SiteResourceServiceImpl  {

    @Autowired
    private SiteResourceMapper siteResourceMapper;
    
    @Autowired
    private SiteRoleResourceLinkMapper siteRoleResourceLinkMapper;
    
    /**
     * 
    *
    * @Description: 新增门户资源 
    * @param @param siteResource
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:50:33 
    * @return int    返回类型 
    * @throws
     */
	public int createResource(SiteResource siteResource) {
    	int num = siteResourceMapper.insertSelective(siteResource);
		return num;
	}

	/**
	 * 
	*
	* @Description: 更新门户资源
	* @param @param siteResource
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:50:48 
	* @return int    返回类型 
	* @throws
	 */
	public int updateResource(SiteResource siteResource) {
		int num = siteResourceMapper.updateByPrimaryKeySelective(siteResource);
		return num;
	}

	/**
	 * 
	*
	* @Description: 删除门户资源 
	* @param @param resourceId    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:50:57 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteResource(Long resourceId) {
		siteResourceMapper.deleteByPrimaryKey(resourceId);
	}

	/**
	 * 
	*
	* @Description: 查找门户资源 
	* @param @param resourceId
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:51:08 
	* @return SiteResource    返回类型 
	* @throws
	 */
	public SiteResource findOne(Integer resourceId) {
		SiteResource siteResource = siteResourceMapper.selectByPrimaryKey(resourceId);
		return siteResource;
	}
	
	/**
	 * 
	*
	* @Description: 查找所有菜单 
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-10 下午1:37:35 
	* @return Object    返回类型 
	* @throws
	 */
	public Object findAllMenu(Object data) {
		
		 HeadObject head = new HeadObject();
		 List<SiteResource>  list = null;
		 JSONObject content = JSONObject.fromObject(data);
		 ResourceQryDTO qry = (ResourceQryDTO) JSONObject.toBean(content,ResourceQryDTO.class);
         try{
        	 //Integer parentId = (Integer) data;
        	 //list = siteResourceMapper.findAllMenu(parentId);
        	 list = siteResourceMapper.findAllMenuByAccoutId(qry);
             head.setRetCode(ErrorCode.SUCCESS);
         }catch(Exception e){    
        	 e.printStackTrace();
             head.setRetCode(ErrorCode.FAILURE);
         }       
         return new ResultObject(head, list);
		
	}
	
	/**
	 * 
	*
	* @Description: 获取卖家资源列表
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:59:20 
	* @return Object    返回类型 
	* @throws
	 */
	 public Object getShopResourceList(Object data) {
	    	Map<String, Object> params = (Map<String, Object>) data;
			PageHelper.startPage(StringUtil.isEmpty(params.get("pageIndex"))?GlobalStatic.DEFAULT_PAGE_INDEX :(Integer)params.get("pageIndex"), StringUtil.isEmpty(params.get("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE:(Integer)params.get("pageSize"));
	        ResultPage<SiteResourceListDTO> page = new ResultPage<SiteResourceListDTO>(siteResourceMapper.selectAll());
	        JSONObject json = JSONObject.fromObject(page);
	        //JSON.toJSONString(page)
	        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
	 }

	public List<SiteResource> findAll() {
		
		return null;
	}
	
	/**
     * 
    *
    * @Description:获取菜单做成树，然后用预角色添加和编辑功能中
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:01:50 
    * @return Object    返回类型 
    * @throws
     */
    public Object getTree(Object data) {
    	Integer  roleId =  (Integer) data;
    	List<SiteResource> rList = null;
    	List<SiteResourceTreeDTO> list = siteResourceMapper.selectAllTree();
    	if(null != roleId) {
    		rList = siteResourceMapper.findMenuByRoleId(roleId);
    		int size = rList.size();
            int len = list.size();
            for(int i=0;i<size;i++) {
            	Long id = rList.get(i).getId();
            	for(int j=0;j<len;j++) {
            		SiteResourceTreeDTO dto = list.get(j);
            		long jId = dto.getId();
            		if(id == jId) {
            			dto.setChecked("true");
            		}
            	}
            }
    	}
        
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
    }
	
	/**
     * 
    *
    * @Description: 添加菜单
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午5:40:15 
    * @return Object    返回类型 
    * @throws
     */
    public Object insertShopResource(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			SiteResourceAddDTO dto = (SiteResourceAddDTO) JSONObject.toBean(content,SiteResourceAddDTO.class);
			SiteResource resource = new SiteResource();
			resource.setResourceName(dto.getResourceName());
			resource.setPermission(dto.getPermission());
			resource.setParentId(dto.getParentId());
			resource.setMenuOrder(dto.getMenuOrder());
			resource.setTarget(dto.getTarget());
			if(SiteResourceType.button.equals(dto.getResourceType())) {
				resource.setResourceType(SiteResourceType.button);
			}else{
				resource.setResourceType(SiteResourceType.menu);
			}
			
			resource.setUrl(dto.getUrl());
			
			createResource(resource);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
    
    /**\
	  * 
	 *
	 * @Description: 更新資源 
	 * @param @param data
	 * @param @return    设定文件 
	 * @author ssd
	 * @date 2015-4-30 下午5:00:50 
	 * @return Object    返回类型 
	 * @throws
	  */
	 public Object updateShopResource(Object data) {
		 HeadObject head = new HeadObject();
			try {
				JSONObject content = JSONObject.fromObject(data);
				SiteResourceAddDTO dto = (SiteResourceAddDTO) JSONObject.toBean(content,SiteResourceAddDTO.class);
				SiteResource resource = new SiteResource();
				resource.setId(dto.getResourceId());
				resource.setResourceName(dto.getResourceName());
				resource.setPermission(dto.getPermission());
				resource.setParentId(dto.getParentId());
				resource.setMenuOrder(dto.getMenuOrder());
				resource.setTarget(dto.getTarget());
				if(SiteResourceType.button.equals(dto.getResourceType())) {
					resource.setResourceType(SiteResourceType.button);
				}else{
					resource.setResourceType(SiteResourceType.menu);
				}
				
				resource.setUrl(dto.getUrl());
				
				updateResource(resource);
				head.setRetCode(ErrorCode.SUCCESS);
			} catch (Exception e) {
				e.printStackTrace();
				head.setRetCode(ErrorCode.FAILURE);
			}
			return new ResultObject(head);
	}
	 
	 /**
	  * 
	 *
	 * @Description: 刪除資源
	 * @param @param data
	 * @param @return    设定文件 
	 * @author ssd
	 * @date 2015-4-30 下午5:00:35 
	 * @return Object    返回类型 
	 * @throws
	  */
	 public Object deleteShopResource(Object data){
	   	 HeadObject head = new HeadObject();
	        try{
	        	Long  id =  (Long) data;
	      	  //判断是否绑定了菜单，如果绑定了则先删除角色菜单关系表
	      	  int num = siteRoleResourceLinkMapper.selectByResourceId(id);
	      	  if(num == 0){ 
	      		siteResourceMapper.deleteByPrimaryKey(id);
	      		  head.setRetCode(ErrorCode.SUCCESS);
	      	  } else {
	      		  head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
	      	  }
			 
	        }catch(Exception e){    
	     	   e.printStackTrace();
	           head.setRetCode(ErrorCode.FAILURE);
	        }       
	        return new ResultObject(head);
	 }

    
  /*  public List<SiteResource> findMenus(Set<String> permissions) {
        List<SiteResource> allResources = findAll();
        List<SiteResource> menus = new ArrayList<SiteResource>();
        for(SiteResource resource : allResources) {
            if(resource.isRootNode()) {
                continue;
            }
            if(resource.getResourceType() != Resource.ResourceType.menu) {
                continue;
            }
            if(!hasPermission(permissions, resource)) {
                continue;
            }
            menus.add(resource);
        }
        return menus;
    }*/

	/**
	 * 
	*
	* @Description: 判断是否有权限
	* @param @param permissions
	* @param @param resource
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:51:22 
	* @return boolean    返回类型 
	* @throws
	 */
    private boolean hasPermission(Set<String> permissions, SiteResource resource) {
        if(StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }
        for(String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if(p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @Title: findAllMenuByOrder 
     * @Description: TODO(按指定字段顺序查询全部菜单) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-23 下午3:10:28 
     * @version 1.0.0 
     * @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object findAllMenuByOrder(Object data) {
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	String orderColumn = jsonObject.getString("orderColumn");
    	long companyId = jsonObject.getLong("companyId");
    	SiteResourceQryDTO qry = new SiteResourceQryDTO();
    	qry.setCompanyId(companyId);
    	qry.setOrderColumn(orderColumn);
    	List<SiteResource> resourceList = siteResourceMapper.findAllMenuByOrder(qry);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), resourceList);
	}
    
    /**
     * 
    *
    * @Description:根据用户名查询权限
    * @param @param data
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:45:14 
    * @return Object    返回类型 
    * @throws
     */
    public Object findSitePermissions(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        String userName = content.getString("userName");
        List<SiteResource> resourceList = siteResourceMapper.findAllMenuByUserName(userName);
        Set<String> permissions = new HashSet<String>();
        if(null != resourceList){
        	int len = resourceList.size();
        	if(len > 0) {
        		for(int i=0;i<len;i++) {
                	String permission = resourceList.get(i).getPermission();
                	if(!StringUtil.isEmpty(permission)) {
                		permissions.add(permission);
                	}
                }
        	}
        }
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, permissions);
    }
	
}
