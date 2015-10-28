package com.cnit.yoyo.rmi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.system.ResourceRoleLinkMapper;
import com.cnit.yoyo.dao.system.RoleMapper;
import com.cnit.yoyo.dao.system.RoleUserLinkMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.model.system.ResourceRoleLink;
import com.cnit.yoyo.model.system.Role;
import com.cnit.yoyo.model.system.RoleWithBLOBs;
import com.cnit.yoyo.model.system.dto.RoleAddDTO;
import com.cnit.yoyo.model.system.dto.RoleListDTO;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 角色管理类
* @author ssd
* @date 2015-4-30 下午4:51:58
 */
@Service("roleServiceImpl")
public class RoleServiceImpl {

    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleUserLinkMapper roleUserLinkMapper;
    @Autowired
    private ResourceRoleLinkMapper resourceRoleLinkMapper;

    /**
     * 
    *
    * @Description: 新增角色 
    * @param @param role
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:52:05 
    * @return int    返回类型 
    * @throws
     */
    public int createRole(RoleWithBLOBs role) {
        return roleMapper.insertSelective(role);
    }

    /**
     * 
    *
    * @Description: 更新角色
    * @param @param role
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:52:14 
    * @return int    返回类型 
    * @throws
     */
    public int updateRole(Role role) {
        return roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 
    *
    * @Description: 删除角色
    * @param @param roleId    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:52:23 
    * @return void    返回类型 
    * @throws
     */
    public void deleteRole(int roleId) {
        roleMapper.deleteByPrimaryKey(roleId);
    }

    /**
     * 
    *
    * @Description: 查找角色
    * @param @param roleId
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:52:34 
    * @return Role    返回类型 
    * @throws
     */
    public Role findOne(int roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    /**
     * 
    *
    * @Description: 查找所有角色 
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:52:44 
    * @return List<RoleWithBLOBs>    返回类型 
    * @throws
     */
    public List<RoleWithBLOBs> findAll() {
        return roleMapper.findAll();
    }
    
    /**
     * 
    *
    * @Description: 分页查找所有角色
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:52:54 
    * @return Object    返回类型 
    * @throws
     */
    public Object getRoleManagerList(Object data) {
    	Map<String, Object> params = (Map<String, Object>) data;
    	int pageIndex = StringUtil.isEmpty(params.get("pageIndex"))?GlobalStatic.DEFAULT_PAGE_INDEX :(Integer)params.get("pageIndex");
    	int pageSize = StringUtil.isEmpty(params.get("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE:(Integer)params.get("pageSize");
		/*PageHelper.startPage(StringUtil.isEmpty(params.get("pageIndex"))?GlobalStatic.DEFAULT_PAGE_INDEX :(Integer)params.get("pageIndex"),
				StringUtil.isEmpty(params.get("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE:(Integer)params.get("pageSize"));*/
		ResultPage<RoleListDTO> page = new ResultPage<RoleListDTO>();
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		List<RoleListDTO> list = roleMapper.selectAllRoleResource();
		List<RoleListDTO> roleList = new ArrayList<RoleListDTO>();
		if(null != list) {
			int size = list.size();
			Map<Integer,RoleListDTO> map = new HashMap<Integer,RoleListDTO>();
			for(int i=0;i<size;i++) {
				RoleListDTO dto = list.get(i);
				map.put(dto.getRoleId(), dto);
			}
			
			
			for(int roleId : map.keySet()) {
				StringBuffer sb = new StringBuffer();
				RoleListDTO roleDTO = map.get(roleId);
				for(int j=0;j<size;j++) {
					RoleListDTO dto = list.get(j);
					if(roleId == dto.getRoleId()) {
						sb.append(dto.getResourceName());
						sb.append("，");
					}
				}
				String str = sb.toString();
				str=str.length()>1?str.substring(0, str.length()-1):str;
				roleDTO.setResourceName(str);
				roleList.add(roleDTO);
			}
		}
		page.setRows(roleList);
		int total = roleList.size();
		int pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
		page.setPages(pages);
		page.setTotal(total);
        JSONObject json = JSONObject.fromObject(page);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
    }
    
    /**
	 * 
	*
	* @Description: 查找角色列表
	* @param @param data
	* @param @return    设定文件 
	* @author ssd
	* @date 2015-4-30 下午4:56:20 
	* @return Object    返回类型 
	* @throws
	 */
    public Object findSelect(Object data){
      	 HeadObject head = new HeadObject();
      	 List<Role> list = null;
           try{
               list =  roleMapper.findSelect();
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
    public Object insertRole(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			RoleAddDTO dto = (RoleAddDTO) JSONObject.toBean(content,RoleAddDTO.class);
			String roleName = dto.getRoleName();
			Role roleOld = findByRolename(roleName);
			int roleId = 0;
			if(null == roleOld) {
				RoleWithBLOBs role = new RoleWithBLOBs();
				role.setRoleName(dto.getRoleName());
				role.setDescription(dto.getDescription());
				createRole(role);
				Role ro = findByRolename(roleName);
				 roleId = ro.getRoleId();
				 
				 String[] resourceIds = dto.getResourceIds().split(",");
					for(int i=0;i<resourceIds.length;i++) {
						ResourceRoleLink link = new ResourceRoleLink();
						link.setRoleId(roleId);
						link.setResourceId(Integer.parseInt(resourceIds[i]));
						resourceRoleLinkMapper.insert(link);
					}
					head.setRetCode(ErrorCode.SUCCESS);
			}else {
				head.setRetCode(ErrorCode.IS_EXIST);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
    
    /**
     * 
    *
    * @Description: 删除角色
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午8:05:52 
    * @return Object    返回类型 
    * @throws
     */
    public Object deleteRole(Object data){
	   	 HeadObject head = new HeadObject();
	        try{
	      	  Integer  id =  (Integer) data;
	      	  // 先判断是否存绑定了用，如果绑定了用，则需要先删除用户角色关系表
	      	  /*int count = roleUserLinkMapper.selectByRoleId(id);
	      	  //判断是否绑定了菜单，如果绑定了则先删除角色菜单关系表
	      	  int num = resourceRoleLinkMapper.selectByRoleId(id);
	      	  if(count == 0 && num == 0){ 
	      		roleMapper.deleteByPrimaryKey(id);
	      		  head.setRetCode(ErrorCode.SUCCESS);
	      	  } else {
	      		  head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
	      	  }*/
	      	  
	      	  
	      	  roleUserLinkMapper.deleteByRoleId(id);
	      	  resourceRoleLinkMapper.deleteByRoleId(id);
	      	  roleMapper.deleteByPrimaryKey(id);
    		  head.setRetCode(ErrorCode.SUCCESS);
			 
	        }catch(Exception e){    
	     	   e.printStackTrace();
	           head.setRetCode(ErrorCode.FAILURE);
	        }       
	        return new ResultObject(head);
    }
    
    /**
     * 
    *
    * @Description: 更新角色 
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:53:10 
    * @return Object    返回类型 
    * @throws
     */
    public Object updateRole(Object data) {
		HeadObject head = new HeadObject();
		try {
			JSONObject content = JSONObject.fromObject(data);
			RoleAddDTO dto = (RoleAddDTO) JSONObject.toBean(content,RoleAddDTO.class);
			String roleName = dto.getRoleName();
			String rId = dto.getRoleId();
			int roleId = 0;
			if(StringUtils.isNotEmpty(rId)) {
				roleId = Integer.parseInt(rId);
			}
			Role roleOld = roleMapper.selectByPrimaryKey(roleId);
			
			if(null != roleOld) {
				roleOld.setRoleName(roleName);
				roleOld.setDescription(dto.getDescription());
				roleMapper.updateByPrimaryKey(roleOld);
				resourceRoleLinkMapper.deleteByRoleId(roleId);
				String[] resourceIds = dto.getResourceIds().split(",");
				for(int i=0;i<resourceIds.length;i++) {
					ResourceRoleLink link = new ResourceRoleLink();
					link.setRoleId(roleId);
					link.setResourceId(Integer.parseInt(resourceIds[i]));
					resourceRoleLinkMapper.insert(link);
				}
				
				head.setRetCode(ErrorCode.SUCCESS);
			}else {
				head.setRetCode(ErrorCode.FAILURE);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

    /**
     * 
    *
    * @Description: 根據角色名查找角色
    * @param @param roleName
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:53:45 
    * @return Role    返回类型 
    * @throws
     */
    private Role findByRolename(String roleName) {
    	
		return roleMapper.findByRolename(roleName);
	}

    /**
     * 
    *
    * @Description: 根據角色ID查找角色
    * @param @param roleIds
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:55:59 
    * @return Set<String>    返回类型 
    * @throws
     */
	public Set<String> findRoles(Long... roleIds) {
        Set<String> roles = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = findOne(roleId);
            if(role != null) {
                roles.add(role.getRole());
            }
        }
        return roles;
    }
    

    /**
     * 
    *
    * @Description: 根據角色ID查找權限
    * @param @param roleIds
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:57:08 
    * @return Set<String>    返回类型 
    * @throws
     */
    public Set<String> findPermissions(Integer[] roleIds) {
        Set<String> permissions = new HashSet<String>();
        for(Integer roleId : roleIds) {
            Role role = roleMapper.selectroleResource(roleId);
            
            if(role != null) {
                List<Resource> resources = role.getResources();
                
                if(null != resources){
                	int len = resources.size();
                	if(len > 0) {
                		for(int i=0;i<len;i++) {
                        	String permission = resources.get(i).getPermission();
                        	permissions.add(permission);
                        }
                	}
                }
                
            }
        }
        return permissions;
    }

	public void deleteRole(Long roleId) {
		// TODO Auto-generated method stub
		
	}

	public Role findOne(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
