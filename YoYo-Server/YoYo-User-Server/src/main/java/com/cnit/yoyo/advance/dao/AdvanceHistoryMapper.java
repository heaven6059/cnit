package com.cnit.yoyo.advance.dao;
/**   
 * @Description: 买家 预存款
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.List;
import java.util.Map;

import com.cnit.yoyo.advance.model.Advance;


public interface AdvanceHistoryMapper {

	/** 
	 * @Description: 获取预存款列表
	 * @param paraData
	 * @return			
	*/
	public List<Advance> getAdvanceList(Map<String, Object> paraData);
	
	
}