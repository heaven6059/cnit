package com.cnit.yoyo.system.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.member.service.SignService;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.system.service.UserService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 账号管理类
* @author ssd
* @date 2015-4-30 下午4:36:04
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private SignService signService;

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     * @throws Exception 
     */
    public PamAccount findByUsername(String username) {
    	HeadObject headObject = CommonHeadUtil
        .geneHeadObject(null, "990104-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
    	Map<String,Object> data = new HashMap<String, Object>();
    	data.put("userName", username);
		ResultObject resultObject = null;
		try {
			resultObject = signService.doCommon(headObject, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		PamAccount user = (PamAccount) JSONObject.toBean((JSONObject) resultObject.getContent(), PamAccount.class);
		return user;
    }

    /**
     * 根据用户名查找其角色，由于买家没有角色之分，因此统一分配一个mem角色，为了权限控制时可以区分买家和卖家，不让卖家访问买家的权限
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findRoles(String username) {
    	HeadObject headObject = CommonHeadUtil
    	        .geneHeadObject(null, "3000020103-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_FRONT);
    	Map<String,Object> data = new HashMap<String, Object>();
    	data.put("userName", username);
		ResultObject resultObject = null;
		try {
			resultObject = signService.doCommon(headObject, data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject content = JSONObject.fromObject(resultObject.getContent());
		PamAccount pa = (PamAccount) JSONObject.toBean(content,PamAccount.class);
		String accountType = pa.getAccountType();
		Set<String> roles = new HashSet<String>();
		if("100".equals(accountType)) {
			roles.add("mem");
		}
		return roles;
    }

    /**
     * 根据用户名查找其权限,由于买家没有权限之分，所以所有买家都具有一样的权限和菜单
     * @param username
     * @return
     * @throws Exception 
     */
    public Set<String> findPermissions(String username) {
		Set<String> permissions = new HashSet<String>();
		return permissions;
    }

}
