package com.cnit.yoyo.myshiro.system.service.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.PamAccount;
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
    private RemoteService memberService;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws Exception 
     */
    public PamAccount findByUsername(String username) {
    	HeadObject headObject = CommonHeadUtil
        .geneHeadObject(null, "990104-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
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
     * 根据用户名查找其角色,由于门户只验证登录，不做权限控制，因此提供空的角色
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findRoles(String username) {
		Set<String> roles = new HashSet<String>();
		return roles;
    }

    /**
     * 根据用户名查找其权限,由于门户只验证登录，不做权限控制，因此提供空的权限
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findPermissions(String username) {
    	Set<String> permissions = new HashSet<String>();
		return permissions;
    }

	@Override
	public void changePassword(Long userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}

}
