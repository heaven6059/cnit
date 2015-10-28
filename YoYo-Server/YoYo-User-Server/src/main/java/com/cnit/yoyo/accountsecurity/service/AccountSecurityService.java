package com.cnit.yoyo.accountsecurity.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;
import cn.taoping.mainserver.service.IMainService;

import com.cnit.yoyo.accountsecurity.dao.AccountSecurityMapper;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.member.MemberMapper;
import com.cnit.yoyo.dao.member.PamAccountMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.rmi.system.service.impl.PasswordHelper;
import com.cnit.yoyo.rmi.system.service.impl.UserServiceImpl;

/**   
 * @Description: 修改个人密码
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午2:01:56 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Service("accountSecurityService")
public class AccountSecurityService {
	private static final Logger log = LoggerFactory.getLogger(AccountSecurityService.class);
	@Autowired
	private AccountSecurityMapper accountSecurityMapper ;
	
	
	@Autowired
	private PasswordHelper passwordHelper;//注入密码服务
	
	@Autowired
	private UserServiceImpl userServiceImpl;// 注入用户服务
	
	@Autowired
	private PamAccountMapper pamAccountMapper;
	@Autowired
    private MemberMapper memberMapper;
	@Resource
	private IMainService mainService;
	
	/**
	 * @Description:验证用户密码有效性(卖家)
	 * @param data
	 * @return
	 */
	 public ResponseObj verifyUserFromTaoPing2(JSONObject data){
		 	log.info("验证用户密码有效性(卖家)有效性开始>>data:{}",data);
	    	Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", data.get("loginName"));//用户名(别名) String类型
			map.put("password", data.get("oldPassword"));//密码 String类型
			ReqParamsObj reqparam = new ReqParamsObj();
			reqparam.setParamMap(map);
			ResponseObj result = this.mainService.verifyUser(reqparam);
			log.info("验证用户密码有效性(卖家)有效性返回>>result>>{}",result);
			if (result == null) {
				result=new ResponseObj();
				result.setMsg("用户中心校验用户失败。");
				result.setSuccess(false);
			}else if (result != null && result.isSuccess()) {
				result.setMsg("用户中心校验用户成功。");
				result.setSuccess(true);
			}
			return result;
	    }
	 
	/**
	 * @Description:验证用户密码有效性(买家)
	 * @param data
	 * @return
	 */
	 public ResponseObj verifyUserFromTaoPing(JSONObject data){
		 	JSONObject content=JSONObject.fromObject(data.get("pamAccount"));
	    	Map<String, Object> map = new HashMap<String, Object>();
			map.put("userName", content.get("loginName"));//用户名(别名) String类型
			map.put("password", content.get("loginPassword"));//密码 String类型
			ReqParamsObj reqparam = new ReqParamsObj();
			reqparam.setParamMap(map);
			log.info("验证用户密码有效性(买家)有效性开始>>reqparam:{}",reqparam);
			ResponseObj result = this.mainService.verifyUser(reqparam);
			log.info("验证用户密码有效性(买家)有效性返回>>result>>{}",result);
			if (result == null) {
				result=new ResponseObj();
				result.setMsg("用户中心校验用户失败。");
				result.setSuccess(false);
			}else if (result != null && result.isSuccess()) {
				result.setMsg("用户中心校验用户成功。");
				result.setSuccess(true);
			}
			return result;
	    }
	 
	 /**
	  * @Description:通知用户中心修改密码
	  * @param data
	  * @return
	  */
	 private ResponseObj modifyPasswordToTaoPing(JSONObject data){
		 Map<String, Object> map = new HashMap<String, Object>();
			map.put("userID", Long.valueOf(data.get("userID").toString()));//用户名(别名) String类型
			map.put("oldPassword", data.get("oldPassword"));//密码 String类型
			map.put("password", data.get("newPassword"));//密码 String类型
			ReqParamsObj reqparam = new ReqParamsObj();
			reqparam.setParamMap(map);
			log.info("通知用户中心修改密码开始>>reqparam:{}",reqparam);
			ResponseObj result = this.mainService.modifyUserLoginPassword(reqparam);
			log.info("通知用户中心修改密码结束>>result:{}",result);
			if (result == null) {
				result=new ResponseObj();
				result.setMsg("用户中心修改密码失败。");
				result.setSuccess(false);
			}else if (result != null && result.isSuccess()) {
				result.setMsg("用户中心修改密码成功。");
				result.setSuccess(true);
			}
			return result;
	 }
	/**
	 * 
	 * @Description: 获取原密码
	 * @param paraData
	 * @return
	 */
	public Object getOldPassword(Object  paraData){
		HeadObject head = new HeadObject() ;
		JSONObject jsonObject = JSONObject.fromObject(paraData);
		ResponseObj resp=verifyUserFromTaoPing(jsonObject);
    	if(!resp.isSuccess()){
    		ResultObject resultObject = new ResultObject(head);
    		head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("用户中心验证密码失败");  
    		return resultObject;
    	}
	    PamAccount  pamAccount = (PamAccount)JSONObject.toBean((JSONObject) jsonObject.get("pamAccount"), PamAccount.class);
	    
	    PamAccount dataPamAccount = userServiceImpl.findByUsername(pamAccount.getLoginName());
	    pamAccount.setSalt(dataPamAccount.getSalt());
	    
	    String encryptPassword = passwordHelper.getEncryptPassword(pamAccount);
		String  dataPassword= accountSecurityMapper.getOldPassword(pamAccount.getAccountId()) ; 
		ResultObject resultObject = new ResultObject(head,"");
		if(dataPassword.equals(encryptPassword)){  
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("旧密码输入错误！");  
		}
		return resultObject;
	}
	
	/**
	  * @description <b>修改账户密码</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-5
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object updateAccountPwd(Object object){
		HeadObject head = new HeadObject();
		Map<String, Object> result=new HashMap<String, Object>();
		try{
			JSONObject jsonObject = JSONObject.fromObject(object);
			ResponseObj resp=verifyUserFromTaoPing2(jsonObject);
	    	if(!resp.isSuccess()){
	    		ResultObject resultObject = new ResultObject(head,result);
	    		result.put("result", false);
		    	result.put("resultMsg", "用户中心修改失败");
	    		head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("用户中心验证密码失败");  
	    		return resultObject;
	    	}
			Map<String, Object>  accountMap = (Map<String, Object>)object;
			PamAccount pamAccount = userServiceImpl.findByUsername((String) accountMap.get("loginName"));
			pamAccount.setLoginPassword((String)accountMap.get("oldPassword"));
		    String inputOldPwd = passwordHelper.getEncryptPassword(pamAccount);
		    PamAccount account=pamAccountMapper.findByUsername(pamAccount.getLoginName());
		    //对比原始密码
		    if(inputOldPwd.equals(account.getLoginPassword())){
		    	Member record=new Member();
				record.setAccountId(pamAccount.getAccountId());
				Member member=memberMapper.findByMember(record).get(0);
				String newpassword=JSONObject.fromObject(object).getString("password");
				JSONObject data=JSONObject.fromObject(JSONObject.fromObject(object).element("newPassword",newpassword).element("userID", member.getUserCenterId()));
				log.info("通知用户中心之前的data数据>>>{}",data);
				ResponseObj resp1=modifyPasswordToTaoPing(data);
		    	if(!resp1.isSuccess()){
		    		ResultObject resultObject = new ResultObject(head,result);
		    		result.put("result", false);
			    	result.put("resultMsg", "用户中心修改失败");
		    		head.setRetCode(ErrorCode.FAILURE);
					head.setRetMsg("用户中心修改失败！");
		    		return resultObject;
		    	}
		    	pamAccount.setLoginPassword((String)accountMap.get("password"));
			    pamAccount.setLoginPassword(passwordHelper.getEncryptPassword(pamAccount));
			    pamAccountMapper.updateByPrimaryKeySelective(pamAccount);
			    result.put("result", true);
		    	result.put("resultMsg", "密码修改成功");
		    }else{
		    	result.put("result", false);
		    	result.put("resultMsg", "原始密码输入错误");
		    }
	    	head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			result.put("result", false);
	    	result.put("resultMsg", "密码检查错误");
		}
    	return new ResultObject(head,result);
	}
	
	/**
	 * 
	 * @Description: 修改为新密码
	 * @param paraData
	 * @return
	 */
	public  Object updateNewPassword(Object  paraData){
		HeadObject head =  new  HeadObject();
		String  newPassword =(String)JSONObject.fromObject(paraData).get("newPassword"); 
		String  confirmPassword =(String)JSONObject.fromObject(paraData).get("confirmPassword");
		String  loginName =(String)JSONObject.fromObject(paraData).get("loginName");
		int  accountId =(Integer)JSONObject.fromObject(paraData).get("accountId");
		
		PamAccount dataPamAccount = userServiceImpl.findByUsername(loginName);
		Member record=new Member();
		record.setAccountId(dataPamAccount.getAccountId());
		Member member=memberMapper.findByMember(record).get(0);
		JSONObject data=JSONObject.fromObject(JSONObject.fromObject(paraData).element("userID", member.getUserCenterId()));
		log.info("通知用户中心之前的data数据>>>{}",data);
		ResponseObj resp=modifyPasswordToTaoPing(data);
    	if(!resp.isSuccess()){
    		ResultObject resultObject = new ResultObject(head);
    		head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("用户中心修改失败！");
    		return resultObject;
    	}
        PamAccount  pamAccount =  new PamAccount();// 账号设值
        pamAccount.setLoginName(loginName);
        pamAccount.setLoginPassword(newPassword);
        pamAccount.setAccountId(accountId); 
        pamAccount.setSalt(dataPamAccount.getSalt());
        String encryptPassword = passwordHelper.getEncryptPassword(pamAccount);
        
	    Map<String,Object> para = new HashMap<String, Object>(); 
	    para.put("accountId", accountId);
	    para.put("encryptPassword",encryptPassword );
	    
		if(newPassword != null && newPassword.equals(confirmPassword)){
			int result = accountSecurityMapper.updateNewPassword(para);
			if(result > 0){
				head.setRetCode(ErrorCode.SUCCESS);
				head.setRetMsg("修改成功！");
			}else{
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("修改失败！");
			}
		}else{
			head.setRetCode(ErrorCode.FAILURE);
			head.setRetMsg("新密码与旧密码不一致！"); 
		}
		ResultObject resultObject = new ResultObject(head);
		return  resultObject;
	}
}

