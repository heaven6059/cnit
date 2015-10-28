package com.cnit.yoyo.rmi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.system.ResourceMapper;
import com.cnit.yoyo.dao.system.ResourceRoleLinkMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsCat;
import com.cnit.yoyo.model.goods.GoodsCatExample;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.model.system.Resource.ResourceType;
import com.cnit.yoyo.model.system.ResourceRoleLink;
import com.cnit.yoyo.model.system.Role;
import com.cnit.yoyo.model.system.dto.ResourceAddDTO;
import com.cnit.yoyo.model.system.dto.ResourceListDTO;
import com.cnit.yoyo.model.system.dto.ResourceQryDTO;
import com.cnit.yoyo.model.system.dto.ResourceTreeDTO;
import com.cnit.yoyo.model.system.dto.RoleAddDTO;
import com.cnit.yoyo.model.system.dto.RoleListDTO;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 資源管理類
* @author ssd
* @date 2015-4-30 下午4:58:08
 */
@Service("resourceServiceImpl")
public class ResourceServiceImpl  {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceRoleLinkMapper resourceRoleLinkMapper;
    
    /**
     * 
    *
    * @Description: 新增資源
    * @param @param resource
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:58:19 
    * @return int    返回类型 
    * @throws
     */
	public int createResource(Resource resource) {
    	int num = resourceMapper.insertSelective(resource);
		return num;
	}

	/**
	 * 
	*
	* @Description: 更新資源 
	* @param @param resource
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:58:33 
	* @return int    返回类型 
	* @throws
	 */
	public int updateResource(Resource resource) {
		int num = resourceMapper.updateByPrimaryKeySelective(resource);
		return num;
	}

	/**
	 * 
	*
	* @Description: 刪除資源 
	* @param @param resourceId    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:58:49 
	* @return void    返回类型 
	* @throws
	 */
	public void deleteResource(Integer resourceId) {
		resourceMapper.deleteByPrimaryKey(resourceId);
	}

	/**
	 * 
	*
	* @Description: 查找資源 
	* @param @param resourceId
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:59:00 
	* @return Resource    返回类型 
	* @throws
	 */
	public Resource findOne(Integer resourceId) {
		Resource resource = resourceMapper.selectByPrimaryKey(resourceId);
		return resource;
	}
	
	/**
	 * 
	*
	* @Description: 獲取資源列表
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:59:20 
	* @return Object    返回类型 
	* @throws
	 */
	 public Object getResourceManagerList(Object data) {
	    	Map<String, Object> params = (Map<String, Object>) data;
			PageHelper.startPage(StringUtil.isEmpty(params.get("pageIndex"))?GlobalStatic.DEFAULT_PAGE_INDEX :(Integer)params.get("pageIndex"), StringUtil.isEmpty(params.get("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE:(Integer)params.get("pageSize"));
	        ResultPage<ResourceListDTO> page = new ResultPage<ResourceListDTO>(resourceMapper.selectAll());
	        JSONObject json = JSONObject.fromObject(page);
	        //JSON.toJSONString(page)
	        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
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
	 public Object deleteResource(Object data){
	   	 HeadObject head = new HeadObject();
	        try{
	      	  Integer  id =  (Integer) data;
	      	  //判断是否绑定了菜单，如果绑定了则先删除角色菜单关系表
	      	  int num = resourceRoleLinkMapper.selectByResourceId(id);
	      	  if(num == 0){ 
	      		resourceMapper.deleteByPrimaryKey(id);
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
	 public Object updateResource(Object data) {
		 HeadObject head = new HeadObject();
			try {
				JSONObject content = JSONObject.fromObject(data);
				ResourceAddDTO dto = (ResourceAddDTO) JSONObject.toBean(content,ResourceAddDTO.class);
				Resource resource = new Resource();
				resource.setId(dto.getResourceId());
				resource.setResourceName(dto.getResourceName());
				resource.setPermission(dto.getPermission());
				resource.setParentId(dto.getParentId());
				resource.setMenuOrder(dto.getMenuOrder());
				resource.setTarget(dto.getTarget());
				if(ResourceType.button.equals(dto.getResourceType())) {
					resource.setResourceType(ResourceType.button);
				}else{
					resource.setResourceType(ResourceType.menu);
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
	 * @Description: 查找所有菜单 
	 * @param @return
	 * @author xiaox
	 * @date 2015-4-2 上午10:26:40
	 */
	public Object findAllMenu(Object data) {
		
		 HeadObject head = new HeadObject();
		 List<Resource>  list = null;
		 JSONObject content = JSONObject.fromObject(data);
		 /*int accountId = content.getInt("accountId");
		 int pid = content.getInt("pid");
		 ResourceQryDTO qry = new ResourceQryDTO();
		 qry.setAccountId(accountId);
		 qry.setParentId(pid);*/
		 
		 ResourceQryDTO qry = (ResourceQryDTO) JSONObject.toBean(content,ResourceQryDTO.class);
         try{
        	 //Integer parentId = (Integer) data;
        	 //list = resourceMapper.findAllMenu(parentId);
        	 list = resourceMapper.selectByAccountId(qry);
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
    * @Description: 添加角色
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午5:40:15 
    * @return Object    返回类型 
    * @throws
     */
    public Object insertResource(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			ResourceAddDTO dto = (ResourceAddDTO) JSONObject.toBean(content,ResourceAddDTO.class);
			Resource resource = new Resource();
			resource.setResourceName(dto.getResourceName());
			resource.setPermission(dto.getPermission());
			resource.setParentId(dto.getParentId());
			resource.setMenuOrder(dto.getMenuOrder());
			resource.setTarget(dto.getTarget());
			if(ResourceType.button.equals(dto.getResourceType())) {
				resource.setResourceType(ResourceType.button);
			}else{
				resource.setResourceType(ResourceType.menu);
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
	
    /**
     * 
    *
    * @Description: 獲取資源信息并生成頁面樹
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:01:17 
    * @return Object    返回类型 
    * @throws
     */
    public Object getResourceTree(Object data) {
        JSONObject content = JSONObject.fromObject(data);
        int parentId = 0;
        if (!StringUtil.isEmpty(StringUtil.getValueIgnoreExecption(content, "parentId"))) {// 上级分类Id
        	parentId = (Integer) content.get("parentId");
        }
        List<Resource> list = resourceMapper.findAllMenu(parentId);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
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
    	List<Resource> rList = null;
    	List<ResourceTreeDTO> list = resourceMapper.selectAllTree();
    	if(null != roleId) {
    		rList = resourceMapper.findMenuByRoleId(roleId);
    		int size = rList.size();
            int len = list.size();
            for(int i=0;i<size;i++) {
            	int id = rList.get(i).getId();
            	for(int j=0;j<len;j++) {
            		ResourceTreeDTO dto = list.get(j);
            		int jId = dto.getId();
            		if(id == jId) {
            			dto.setChecked("true");
            		}
            	}
            }
    	}
        
        
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), list);
    }

	
	public List<Resource> findAll() {
		
		return null;
	}

    /**
     * 
    *
    * @Description: 根據權限查找菜單 
    * @param @param permissions
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:02:06 
    * @return List<Resource>    返回类型 
    * @throws
     */
    public List<Resource> findMenus(Set<String> permissions) {
        List<Resource> allResources = findAll();
        List<Resource> menus = new ArrayList<Resource>();
        for(Resource resource : allResources) {
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
    }

    /**
     * 
    *
    * @Description: 判斷是否有權限
    * @param @param permissions
    * @param @param resource
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:02:22 
    * @return boolean    返回类型 
    * @throws
     */
    private boolean hasPermission(Set<String> permissions, Resource resource) {
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


	
}
