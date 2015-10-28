package com.cnit.yoyo.rmi.shop.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.dao.member.ClerkMapper;
import com.cnit.yoyo.dao.member.RolesMapper;
import com.cnit.yoyo.dao.member.StoreRolesResourceMapper;
import com.cnit.yoyo.dao.system.SiteResourceRoleLinkMapper;
import com.cnit.yoyo.dao.system.SiteRoleMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.Roles;
import com.cnit.yoyo.model.member.StoreRolesResource;
import com.cnit.yoyo.model.member.dto.RolesDTO;
import com.cnit.yoyo.model.system.SiteResourceRoleLink;
import com.cnit.yoyo.model.system.SiteRole;
import com.cnit.yoyo.model.system.SiteRoleWithBLOBs;
import com.cnit.yoyo.model.system.dto.SiteRoleAddDTO;
import com.cnit.yoyo.model.system.dto.SiteRoleListDTO;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;
/**
 * 
 * @ClassName: RoleService 
 * @Description: 店员角色service 
 * @date 2015-3-30 上午11:24:34
 */
@Service("shopRoleService")
public class RoleService {
    private static final Log log = LogFactory.getLog(RoleService.class);
    
    @Autowired
    private RolesMapper rolesMapper;
    
    @Autowired
    private SiteRoleMapper siteRoleMapper;
    @Autowired
    private ClerkMapper clerksMapper;
    
    @Autowired
    private SiteResourceRoleLinkMapper siteResourceRoleLinkMapper;
    
    @Autowired
    private StoreRolesResourceMapper storeRolesResourceMapper;
    
    public Object findByRoleId(Object data){
           HeadObject head = new HeadObject();
           List<Roles> list = null;
           try{
               JSONObject content = JSONObject.fromObject(data);
               Roles role = (Roles) JSONObject.toBean(content, Roles.class);
               list = rolesMapper.findRolesNameById(role);
               head.setRetCode(ErrorCode.SUCCESS);
           }catch(Exception e){
               log.error(e.getMessage());
               head.setRetCode(ErrorCode.FAILURE);
           }
           
           return new ResultObject(head, list);
       }
    
    /**
     * @Title: findByStoreId 
     * @Description: TODO(根据店铺id分页查询店铺角色) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-22 下午3:52:14 
     * @version 1.0.0 
     * @param @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object findByStoreId(Object data){
    	log.info("start[RoleService.findByStoreId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
//		System.out.println("service..."+data);
		Integer  pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		PageHelper.startPage(pageIndex, pageSize);
		Long storeId = jsonObject.getLong("storeId");
		ResultPage<RolesDTO> page = new ResultPage( rolesMapper.findByStoreId(storeId));
//		System.out.println("service....page...."+page.getCurrPageSize()+"..."+page.getPageIndex()+"..."+page.getPages()+"..."+page.getPageSize()+"..."+page.getTotal());
		if(page!=null&&page.getRows()!=null&&page.getRows().size()>=1){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<page.getRows().size();i++){
				if(page.getRows().get(i).getLastModifyTime()!=null){
					page.getRows().get(i).setLastModifyDate(format.format(new Date(page.getRows().get(i).getLastModifyTime())));
				}
			}
		}
		jsonObject.clear();
		jsonObject.put("rolesList", page.getRows());
		log.info("end[RoleService.findByStoreId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS),JSONObject.fromObject(page));
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
    public Object getShopRoleList(Object data) {
    	Map<String, Object> params = (Map<String, Object>) data;
    	int pageIndex = StringUtil.isEmpty(params.get("pageIndex"))?GlobalStatic.DEFAULT_PAGE_INDEX :(Integer)params.get("pageIndex");
    	int pageSize = StringUtil.isEmpty(params.get("pageSize"))?GlobalStatic.DEFAULT_PAGE_SIZE:(Integer)params.get("pageSize");
		ResultPage<SiteRoleListDTO> page = new ResultPage<SiteRoleListDTO>();
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		List<SiteRoleListDTO> list = rolesMapper.selectAllRoleResource();
		List<SiteRoleListDTO> roleList = new ArrayList<SiteRoleListDTO>();
		if(null != list) {
			int size = list.size();
			Map<Long,SiteRoleListDTO> map = new HashMap<Long,SiteRoleListDTO>();
			for(int i=0;i<size;i++) {
				SiteRoleListDTO dto = list.get(i);
				map.put(dto.getRolesId(), dto);
			}
			
			
			for(long roleId : map.keySet()) {
				StringBuffer sb = new StringBuffer();
				SiteRoleListDTO roleDTO = map.get(roleId);
				for(int j=0;j<size;j++) {
					SiteRoleListDTO dto = list.get(j);
					if(roleId == dto.getRolesId()) {
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
      	 List<Roles> list = null;
           try{
        	   long companyId = 0L;
        	   if(null != data){
        		   companyId = (Long)data;  
        	   }
               list =  rolesMapper.findSelect(companyId);
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
    * @Description: 根据用户名查询角色
    * @param @param data
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:44:40 
    * @return Object    返回类型 
    * @throws
     */
    public Object findSiteRoles(Object data) throws Exception {
        HeadObject head = new HeadObject();
        JSONObject content = JSONObject.fromObject(data);
        String userName = content.getString("userName");
        Roles role = rolesMapper.findRoleByUserName(userName);
        /*Set<String> roles = new HashSet<String>();
        if(null != role) {
        	roles.add(role.getRolesName());
        }*/
        JSONObject json = new JSONObject();
        json.put("role", role.getRolesName());
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head, json);
    }
    
    /**
     * @Title: deleteByRolesIdAndStore 
     * @Description: TODO(根据rolesId和storeId删除Roles对象以及相关联的storeRolesResource对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-23 上午11:31:05 
     * @version 1.0.0 
     * @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object deleteByRolesIdAndStore(Object data){
    	log.info("start[RoleService.deleteByPrimaryKey]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		long rolesId = jsonObject.getLong("rolesId");
		long storeId = jsonObject.getLong("storeId");
		int roleId = (int)rolesId;
		//判断是否绑定了菜单，如果绑定了则先删除角色菜单关系表
//    	int num = siteResourceRoleLinkMapper.selectByRoleId(roleId);
//	  	  if(num == 0){ 
	  		siteRoleMapper.deleteByPrimaryKey(roleId);
	  		rolesMapper.deleteByRolesIdAndStore(rolesId, storeId);
			siteResourceRoleLinkMapper.deleteByRoleId(roleId);
//	  		new ResultObject(new HeadObject(ErrorCode.SUCCESS));
//	  	  } else {
//	  		new ResultObject(new HeadObject(ErrorCode.IS_EXIST_CASCADE));
//	  	  }
		
		/*int deleteResult = rolesMapper.deleteByRolesIdAndStore(rolesId, storeId);
		if(deleteResult==1){
			StoreRolesResourceExample example = new StoreRolesResourceExample();
			example.createCriteria().andRolesIdEqualTo(rolesId);
			storeRolesResourceMapper.deleteByExample(example);
		}*/
		log.info("end[RoleService.deleteByPrimaryKey]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
    }
    
    /**
     * @Title: findByRolesName 
     * @Description: TODO(根据角色名称查询角色) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午4:22:03 
     * @version 1.0.0 
     * @param @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object findByStoreAndName(Object data){
    	log.info("start[RoleService.selectByExample]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		List<Roles> rolesList = rolesMapper.findByStoreAndName(jsonObject.getLong("storeId"), jsonObject.getString("rolesName"));
		log.info("end[RoleService.selectByExample]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONArray.fromObject(rolesList));
    }
    
    /**
     * @Title: saveRoles 
     * @Description: TODO(保存角色对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 上午10:33:10 
     * @version 1.0.0 
     * @param @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object saveRoles(Object data){
    	log.info("start[RoleService.saveRoles]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
		Roles roles = (Roles) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("roles")),Roles.class);
		List<Long> resourceIdList = (List<Long>) JSONArray.toCollection(JSONArray.fromObject(jsonObject.get("resourceIdList")), Long.class);
		long storeId = jsonObject.getLong("storeId");
		String roleName = roles.getRolesName()+"-"+storeId;
		
		SiteRoleWithBLOBs role = new SiteRoleWithBLOBs();
		role.setRoleName(roleName);
		role.setDescription(roles.getDescription());
		siteRoleMapper.insertSelective(role);
		SiteRole ro = siteRoleMapper.findByRolename(roleName);
		int roleId = ro.getRoleId();
		roles.setRolesId((long) roleId);
		int insertResult = rolesMapper.insertSelective(roles);
	
		if(insertResult == 1){
			int len = resourceIdList.size();
			for(int i=0;i<len;i++) {
				SiteResourceRoleLink link = new SiteResourceRoleLink();
				link.setRoleId(roleId);
				link.setResourceId(resourceIdList.get(i));
				siteResourceRoleLinkMapper.insert(link);
			}
			/*roles = rolesMapper.findByStoreAndName(roles.getStoreId(), roles.getRolesName()).get(0);
			StoreRolesResource storeRolesResource = null;
			for(int i=0;i<resourceIdList.size();i++){
				storeRolesResource = new StoreRolesResource();
				storeRolesResource.setRolesId(roles.getRolesId());
				storeRolesResource.setResourceId(resourceIdList.get(i));
				insertResult = storeRolesResourceMapper.insertSelective(storeRolesResource);
				if(insertResult != 1){
					log.info("end[RoleService.saveRoles]");
					return new ResultObject(new HeadObject(ErrorCode.FAILURE));
				}
			}*/
			log.info("end[RoleService.saveRoles]");
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
		}else{
			log.info("end[RoleService.saveRoles]");
			return new ResultObject(new HeadObject(ErrorCode.FAILURE));
		}
    }
    
    /**
     * 
    *
    * @Description: 后台添加卖家角色
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
			SiteRoleAddDTO dto = (SiteRoleAddDTO) JSONObject.toBean(content,SiteRoleAddDTO.class);
			String roleName = dto.getRoleName();
			long storeId = dto.getStoreId();
			 List<Roles> list = rolesMapper.findByStoreAndName(storeId, roleName);
			 Long roleId = 0L;
			 if(null == list || list.size() <= 0) {
				 Roles role = new Roles();
					role.setRolesName(roleName);
					role.setDescription(dto.getDescription());
					role.setCompanyId(dto.getCompanyId());
					role.setStoreId(storeId);
					role.setLastModifyTime(new Date().getTime());
					rolesMapper.insertSelective(role);
					Roles ro = rolesMapper.findByStoreAndName(storeId, roleName).get(0);
					roleId = ro.getRolesId();
			 }else {
				 Roles roleOld = list.get(0);
				 roleId = roleOld.getRolesId();
			 }
			 
			String[] resourceIds = dto.getResourceIds().split(",");
			for(int i=0;i<resourceIds.length;i++) {
				StoreRolesResource link = new StoreRolesResource();
				link.setRolesId(roleId);
				link.setResourceId(Long.parseLong(resourceIds[i]));
				storeRolesResourceMapper.insert(link);
			}
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
    * @Description: 后台更新卖家角色 
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
			SiteRoleAddDTO dto = (SiteRoleAddDTO) JSONObject.toBean(content,SiteRoleAddDTO.class);
			String roleName = dto.getRoleName();
			long rId = dto.getRoleId();
			long roleId = 0;
			if(rId > 0) {
				roleId = rId;
			}
			Roles roleOld = rolesMapper.selectByPrimaryKey(roleId);
			
			if(null != roleOld) {
				roleOld.setRolesName(roleName);
				roleOld.setDescription(dto.getDescription());
				roleOld.setCompanyId(dto.getCompanyId());
				roleOld.setStoreId(dto.getStoreId());
				roleOld.setLastModifyTime(new Date().getTime());
				rolesMapper.updateByPrimaryKey(roleOld);
				storeRolesResourceMapper.deleteByRoleId(roleId);
				String[] resourceIds = dto.getResourceIds().split(",");
				for(int i=0;i<resourceIds.length;i++) {
					StoreRolesResource link = new StoreRolesResource();
					link.setRolesId(roleId);
					link.setResourceId(Long.parseLong(resourceIds[i]));
					storeRolesResourceMapper.insert(link);
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
    * @Description: 后台删除卖家角色
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
	        	Long  id =  (Long) data;
	      	  //判断是否绑定了菜单，如果绑定了则先删除角色菜单关系表
	      	  int num = storeRolesResourceMapper.selectByRoleId(id);
	      	  if(num == 0){ 
	      		rolesMapper.deleteByPrimaryKey(id);
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
    
    /**
     * @Title: findByRolesId 
     * @Description: TODO(根据rolesId查询Roles对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:05:49 
     * @version 1.0.0 
     * @param @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object findByRolesId(Object data){
    	log.info("start[RoleService.findByRolesId]");
		JSONObject jsonObject = JSONObject.fromObject(data);
		int rolesId=jsonObject.getInt("rolesId");
		Roles roles = rolesMapper.selectByPrimaryKey((long)rolesId);
		List<SiteResourceRoleLink> resourceList = null;
		if(roles!=null){
			resourceList = siteResourceRoleLinkMapper.findByRoleId(rolesId);
			/*StoreRolesResourceExample example = new StoreRolesResourceExample();
			example.createCriteria().andRolesIdEqualTo(roles.getRolesId());
			resourceList = storeRolesResourceMapper.selectByExample(example);*/
		}
		jsonObject.clear();
		jsonObject.put("roles", roles);
		jsonObject.put("resourceList", resourceList);
		log.info("end[RoleService.findByRolesId]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
    }
    
    /**
     * @Title: updateRoles 
     * @Description: TODO(更新roles) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-24 下午5:31:19 
     * @version 1.0.0 
     * @param @param data
     * @param @return    
     * @return Object    返回类型 
     * @throws
     */
    public Object updateRoles(Object data){
    	log.info("start[RoleService.updateRoles]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
		Roles roles = (Roles) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("roles")),Roles.class);
		List<Long> resourceIdList = (List<Long>) JSONArray.toCollection(JSONArray.fromObject(jsonObject.get("resourceIdList")), Long.class);
		long storeId = jsonObject.getLong("storeId");
		
		String roleName = roles.getRolesName()+"-"+storeId;
		long rId = roles.getRolesId();
		int roleId = 0;
		roleId = (int)rId;
		SiteRole roleOld = siteRoleMapper.selectByPrimaryKey(roleId);
		
		if(null != roleOld) {
			roleOld.setRoleName(roleName);
			roleOld.setDescription(roles.getDescription());
			siteRoleMapper.updateByPrimaryKey(roleOld);
			rolesMapper.updateNameByRolesId(roles.getRolesId(), roles.getRolesName(), roles.getLastModifyTime());
			siteResourceRoleLinkMapper.deleteByRoleId(roleId);
			int len = resourceIdList.size();
			for(int i=0;i<len;i++) {
				SiteResourceRoleLink link = new SiteResourceRoleLink();
				link.setRoleId(roleId);
				link.setResourceId(resourceIdList.get(i));
				siteResourceRoleLinkMapper.insert(link);
			}
			
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
		}else {
			return new ResultObject(new HeadObject(ErrorCode.FAILURE));
		}
		
		
		
		/*if(updateResult == 1){
			StoreRolesResourceExample example = new StoreRolesResourceExample();
			example.createCriteria().andRolesIdEqualTo(roles.getRolesId());
			storeRolesResourceMapper.deleteByExample(example);
			StoreRolesResource storeRolesResource = null;
			for(int i=0;i<resourceIdList.size();i++){
				storeRolesResource = new StoreRolesResource();
				storeRolesResource.setRolesId(roles.getRolesId());
				storeRolesResource.setResourceId(resourceIdList.get(i));
				updateResult = storeRolesResourceMapper.insertSelective(storeRolesResource);
				if(updateResult != 1){
					log.info("end[RoleService.updateRoles]");
					return new ResultObject(new HeadObject(ErrorCode.FAILURE));
				}
			}
			log.info("end[RoleService.updateRoles]");
			return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
		}else{
			log.info("end[RoleService.updateRoles]");
			return new ResultObject(new HeadObject(ErrorCode.FAILURE));
		}*/
    }
    
    /**
     * 
     *@description 店铺管理中：获取所有店铺角色 
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-9
     *@param data
     *@return
     */
    public Object findShopAllRoles(Object data){
        HeadObject head = new HeadObject();
        ResultPage<RolesDTO> page = null;
        JSONObject content = JSONObject.fromObject(data);
        RolesDTO dto = (RolesDTO) JSONObject.toBean(content.getJSONObject("roles"), RolesDTO.class);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        if(dto!=null){
            dto.setOrderStmt(QueryParamObject.getOrderByCause(content));
        }
        page = new ResultPage<RolesDTO>(rolesMapper.findShopAllRoles(dto));
        head.setRetCode(ErrorCode.SUCCESS);
        return new ResultObject(head,JSONObject.fromObject(page));
    }
    
    /**
     * 
     *@description 删除店铺的角色
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-9
     *@param data
     *@return
     */
    public Object deleteShopRole(Object data){
        HeadObject head = new HeadObject();
        Integer[] ids = (Integer[])data;
        //判断是否有会员关联了该角色
        int count=0;
        boolean flag = false;
        for(int i=0;i<ids.length;i++){
            count = clerksMapper.findClerksByRoleId(ids[i]);
            if(count==0){ 
                rolesMapper.deleteRole(ids);
            } else {
                flag = true;
            }
            
        }
        
        if(flag){
            head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
        }else{
            head.setRetCode(ErrorCode.SUCCESS);
        }
        
        return new ResultObject(head);
        
    }
    
    
}
