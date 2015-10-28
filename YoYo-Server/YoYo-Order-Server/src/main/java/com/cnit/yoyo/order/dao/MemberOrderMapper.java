package com.cnit.yoyo.order.dao;
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

import com.cnit.yoyo.complain.model.dto.ComplainDetailDTO;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.order.dto.ShoppingHistoryDTO;
import com.cnit.yoyo.model.trade.dto.OrderTipsDTO;
import com.cnit.yoyo.order.model.Order;

public interface MemberOrderMapper {



	/**
	 * 
	 * @Description: 查询买家中心 订单列表
	 * @param @param arams
	 * @param @return
	 * @throws		
	 * @return List<Order>
	 */
	public List<Object> getOrderList(Map<String, Object> arams);
	

	/**
	 * 
	 * @Description: 查询买家中心 订单列表
	 * @param @param arams
	 * @param @return
	 * @throws		
	 * @return List<Order>
	 */
	public List<Object> getOrderIdsList(Map<String, Object> arams);
	

   /**
	* 
	* @Description: 据orderId 查询订单信息
	* @param @param orderId
	* @param @return
	* @throws		
	* @return Order
	*/
	public Order getOrderByOrderId(Map<String, Object> params);
	

   /**
	* 
	* @Description: 修改订单状态
	* @param @param params
	* @param @return
	* @throws		
	* @return int
	*/
	public int updateOrderStatus(Map<String, Object> params);
	
   /**
	* 
	* @Description: 修改订单状态与显示状态
	* @param @param params
	* @param @return
	* @throws		
	* @return int
	*/
	public int updateOrderStatusAndDisable(Map<String, Object> params);


	/** 
	 * @Description: 查询订单详投诉信息
	 * @param map
	 * @return			
	*/
	public ComplainDetailDTO getOrderComplainByOrderId(Map<String, Object> map);


	/** 
	 * @Description:  删除订单
	 * @param map
	 * @return			
	*/
	public int updateOrderDisableStatus(Map<String, Object> map);
	
	
	/**
	  * @description <b>查询需要售后的订单</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-1
	  * @param @param orderId
	  * @param @return
	  * @return OrderAfterSales
	*/
	public List<Object> selectNeedAfterSalesOrdersCount(OrderQryDTO dto);
	
	/**
	  * @description <b>查询需要售后的订单</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-1
	  * @param @param orderId
	  * @param @return
	  * @return OrderAfterSales
	*/
	public List<Object> selectNeedAfterSalesOrders(OrderQryDTO dto);
	
	public List<ShoppingHistoryDTO> selectShoppingHistory(OrderQryDTO qryDTO);

	/**
	 * 
	 * @Description:查询主单和子单集合
	 * @param map
	 * @return
	 */
	public List<Order> getAllOrderInfoList(Map<String, Object> map);
	
	/**
	 * @description <b>查询用户订单提醒</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月29日
	 * @param memberId
	 * @return
	 * OrderTipsDTO
	*/
	public OrderTipsDTO selectMemberOrderTips(Integer memberId);
	
	/**
	 * @description <b>查询用户未支付的商品Id</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-13
	 * @param memberId
	 * @return
	 * List<Long>
	*/
	public List<Long> selectMemberUnPayProductIds(Integer memberId);
}