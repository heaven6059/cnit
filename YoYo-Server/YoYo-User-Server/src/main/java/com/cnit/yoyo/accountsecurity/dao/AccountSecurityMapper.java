package com.cnit.yoyo.accountsecurity.dao;

import java.util.Map;


/**   
 * @Description:修改密码
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-27 下午2:05:24 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
public interface AccountSecurityMapper {



	/** 
	 * @Description: 获取旧密码
	 * @param integer
	 * @return			
	*/
	public String getOldPassword(Integer accountId);  

	/** 
	 * @Description: 修改为新密码 
	 * @param para
	 * @return			
	*/
	public int updateNewPassword(Map<String, Object> para);        

}

