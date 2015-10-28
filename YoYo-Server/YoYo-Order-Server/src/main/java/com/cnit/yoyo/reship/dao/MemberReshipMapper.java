package com.cnit.yoyo.reship.dao;
/**   
 * @Title: MemberOrderMapper.java 
 * @Package  
 * @Description: 卖家中心订单管理DAO 
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-24 下午5:41:07 
 * @Copyright 2015 http://www.chinacnit.com/ All rights reserved
 * @version V1.0.0 		
 */
import java.util.List;
import java.util.Map;

import com.cnit.yoyo.reship.model.AftersalesReturnProduct;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.reship.model.ReshipItems;

public interface MemberReshipMapper {





	/** 
	 * @Description: 查询申请退货返修列表
	 * @param params
	 * @return			
	*/
	public List<ReshipDTO> getReshipList(Map<String, Object> params);

	
	/** 
	 * @Description: 保存退货返修列表
	 * @param reshipDTO
	 * @return			
	*/
	public int saveReship(ReshipDTO reshipDTO);


	/** 
	 * @Description: 
	 * @param afterProduct
	 * @return			
	*/
	public int saveAftersalesReturnProduct(AftersalesReturnProduct afterProduct);


	/** 
	 * @Description:  
	 * @param reshipItems
	 * @return			
	*/
	public int saveReshipItems(ReshipItems reshipItems);


	/** 
	 * @Description: 查询申请退货详细信息 
	 * @param params
	 * @return			
	*/
	public ReshipDTO getReshipDetailById(Map<String, Object> params); 
	
}