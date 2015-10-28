package com.cnit.yoyo.myshiro.system.service.impl;

import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.myshiro.system.service.ResourceService;

import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 资源管理类
* @author ssd
* @date 2015-4-30 下午4:26:25
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
    
	public int createResource(Resource resource) {
    	int num = 0;
		return num;
	}

	public int updateResource(Resource resource) {
		int num = 0;
		return num;
	}

	public void deleteResource(Long resourceId) {
		//resourceMapper.deleteByPrimaryKey(resourceId);
	}

	public Resource findOne(Long resourceId) {
		Resource resource = null;//resourceMapper.selectByPrimaryKey(resourceId);
		return resource;
	}

	public List<Resource> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

    

    /**
     * 根据权限查找菜单
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
    * @Description: 判断是否有权限
    * @param @param permissions
    * @param @param resource
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:26:46 
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
