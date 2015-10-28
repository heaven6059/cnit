package com.cnit.yoyo.myshiro.system.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.model.system.Resource;
import com.cnit.yoyo.model.system.Role;
import com.cnit.yoyo.myshiro.system.service.ResourceService;
import com.cnit.yoyo.myshiro.system.service.RoleService;

/**
 * 角色管理类
* @author ssd
* @date 2015-4-30 下午4:25:04
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private ResourceService resourceService;

    public int createRole(Role role) {
        return 0;//roleMapper.insertSelective((RoleWithBLOBs) role);
    }

    public int updateRole(Role role) {
        return 0;//roleMapper.updateByPrimaryKey(role);
    }

    public void deleteRole(int roleId) {
        //roleMapper.deleteByPrimaryKey(roleId);
    }

    
    public Role findOne(int roleId) {
        return null;//roleMapper.selectByPrimaryKey(roleId);
    }

    
    public List<Role> findAll() {
        return null;//roleMapper.findAll();
    }

    /**
     * 根据角色ID查找角色
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
     * 根据角色ID查找权限
     */
    public Set<String> findPermissions(Long[] roleIds) {
        Set<Long> resourceIds = new HashSet<Long>();
        Set<String> permissions = new HashSet<String>();
        for(Long roleId : roleIds) {
            Role role = null;//roleMapper.selectroleUser(roleId);
            if(role != null) {
                List<Resource> resources = role.getResources();
                for(int i=0;i<resources.size();i++) {
                	String permission = resources.get(i).getPermission();
                	permissions.add(permission);
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
