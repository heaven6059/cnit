package com.cnit.yoyo.myshiro.system.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.myshiro.system.service.RoleService;
import com.cnit.yoyo.myshiro.system.service.UserService;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 账号管理类
* @author ssd
* @date 2015-4-30 下午4:21:17
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleService roleService;
    
    @Autowired
    private RemoteService memberService;


    
    public int updateUser(PamAccount user) {
        return 0;//userMapper.updateByPrimaryKey(user);
    }

    
    public void deleteUser(int userId) {
        //userMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     */
    public void changePassword(int userId, String newPassword) {
        //User user =userMapper.selectByPrimaryKey(userId);
       // user.setLoginPassword(newPassword);
       // passwordHelper.encryptPassword(user);
       // userMapper.updateByPrimaryKey(user);
    }

    
    public PamAccount findOne(int userId) {
        return null;//userMapper.selectByPrimaryKey(userId);
    }

    
    public List<PamAccount> findAll() {
        return null;
    }

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws Exception 
     */
    public PamAccount findByUsername(String username) {
    	HeadObject headObject = CommonHeadUtil
        .geneHeadObject(null, "990104-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
    	Map<String,Object> data = new HashMap<String, Object>();
    	data.put("userName", username);
		ResultObject resultObject = null;
		try {
			resultObject = memberService.doService(new RequestObject(headObject, data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PamAccount user = (PamAccount) JSONObject.toBean((JSONObject) resultObject.getContent(), PamAccount.class);
		return user;
    }

    /**
     * 根据用户名查找其角色
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findRoles(String username) {
    	HeadObject headObject = CommonHeadUtil
    	        .geneHeadObject(null, "3000020103-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
    	Map<String,Object> data = new HashMap<String, Object>();
    	data.put("userName", username);
		ResultObject resultObject = null;
		try {
			resultObject = memberService.doService(new RequestObject(headObject, data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//JSONArray jsonArray = (JSONArray)resultObject.getContent();
		Set<String> roles = (Set<String>) resultObject.getContent();
		return roles;
    }

    /**
     * 根据用户名查找其权限
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findPermissions(String username) {
    	HeadObject headObject = CommonHeadUtil
    	        .geneHeadObject(null, "3000020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
    	Map<String,Object> data = new HashMap<String, Object>();
    	data.put("userName", username);
		ResultObject resultObject = null;
		try {
			resultObject = memberService.doService(new RequestObject(headObject, data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*JSONArray jsonArray = (JSONArray)resultObject.getContent();
		List<String> permissionList = (List<String>) jsonArray.toCollection(jsonArray);*/
		Set<String> permissions = (Set<String>) resultObject.getContent();
		return permissions;
    }


	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		
	}


	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}


	public PamAccount findOne(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}


	public int createUser(PamAccount arg0) {
		// TODO Auto-generated method stub
		return 0;
	}




}
