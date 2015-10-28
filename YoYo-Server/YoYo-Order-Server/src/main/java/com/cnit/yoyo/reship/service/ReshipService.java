package com.cnit.yoyo.reship.service;
/**   
 * @Title: memberOrderService.java 
 * @Package  
 * @Description: 卖家中心订单管理service 
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-24 下午5:41:07 
 * @Copyright 2015 http://www.chinacnit.com/ All rights reserved
 * @version V1.0.0 		
 */
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.constant.OrderConstant.OrderStatus;
import com.cnit.yoyo.dao.order.OrderLogMapper;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.dao.trade.AftersalesReturnLogMapper;
import com.cnit.yoyo.dao.trade.AftersalesReturnProductMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.OrderLog;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.trade.AftersalesReturnLogWithBLOBs;
import com.cnit.yoyo.model.trade.AftersalesReturnProductWithBLOBs;
import com.cnit.yoyo.model.trade.dto.RefundsProductDTO;
import com.cnit.yoyo.reship.dao.MemberReshipMapper;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("reshipService")
@SuppressWarnings("unchecked")
public class ReshipService {

	@Autowired
	private MemberReshipMapper memberReshipMapper;
	
	@Autowired
	private AftersalesReturnProductMapper aftersalesReturnProductMapper;

	@Autowired
	private OrderLogMapper orderLogMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AftersalesReturnLogMapper aftersalesReturnLogMapper;
	
    @Autowired
    private RemoteService otherService;
	
	/**
	 * 
	 * @Description: 查询申请退货返修列表
	 * @param data
	 * @return
	 */
	public Object getReshipList(Object o) {
		HeadObject head = new HeadObject();
		ResultPage<ReshipDTO> result=null;
		Map<String, Object> params = (Map<String, Object>) o;
		int  pageNum = (Integer)params.get("pageNum") ;
		int  pageSize = (Integer)params.get("pageSize") ;
		PageHelper.startPage(pageNum,pageSize );
		result = new ResultPage<ReshipDTO>(this.memberReshipMapper.getReshipList(params));
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, result);
	}
	
	
	/**
	 * 
	 * @Description: 查询申请退货详细信息
	 * @param data
	 * @return
	 */
	public Object getReshipDetailById(Object o) {
		HeadObject head = new HeadObject();
		RefundsProductDTO reshipDTO=null;
		try{
			reshipDTO=aftersalesReturnProductMapper.selectReturnProductById((Long) o);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, reshipDTO);		
	}
	
	/**
	 * 
	 * @Description: 保存退货返修列表
	 * @param data
	 * @return
	 */
	public Object saveReship(Object data){
		HeadObject head = new HeadObject();
		ReshipDTO afterProduct = (ReshipDTO)data;
		Order order=new Order();
		order.setOrderId(afterProduct.getOrderId());
		order.setRefundStatus(afterProduct.getStatus().toString());
		this.orderMapper.updateByPrimaryKeySelective(order);//修改订单退货状态
		
		afterProduct.setAddTime(new Date());
		afterProduct.setCreateTime(new Date());
		aftersalesReturnProductMapper.insertSelective(afterProduct);
		
		/**写入售后日志**/
		AftersalesReturnLogWithBLOBs aftersalesReturnLog=new AftersalesReturnLogWithBLOBs();
		aftersalesReturnLog.setOpId(Integer.valueOf(afterProduct.getOpId()));
		aftersalesReturnLog.setOpName(afterProduct.getOpName());
		aftersalesReturnLog.setOrderId(afterProduct.getOrderId());
		aftersalesReturnLog.setReturnId(afterProduct.getReturnId());
		aftersalesReturnLog.setRole(AfterSalesRole.STATUS_MEMBER.getKey());
		aftersalesReturnLog.setLogText(AfterSalesStatus.STATUS_1.getValue());
		aftersalesReturnLog.setAlttime(new Date());
		aftersalesReturnLogMapper.insertSelective(aftersalesReturnLog);
		head.setRetCode(ErrorCode.SUCCESS);
		return head;
	}
	
	
	/**
	 * @description <b>保存售后申诉</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年8月4日
	 * @param data
	 * @return
	 * Object
	*/
	public Object saveAppealReship(Object data){
		HeadObject head = new HeadObject();
		try{
			ReshipDTO afterProduct = (ReshipDTO)data;
			/**将之前的申请单改为已申述*/
			if(null==afterProduct.getReturnId()){
				head.setRetCode(ErrorCode.FAILURE);
			}else{
				AftersalesReturnProductWithBLOBs old=new AftersalesReturnProductWithBLOBs();
				old.setReturnId(afterProduct.getReturnId());
				old.setAppeal(1);
				aftersalesReturnProductMapper.updateByPrimaryKeySelective(old);
				
				/**修改订单退货状态**/
				Order order=new Order();
				order.setOrderId(afterProduct.getOrderId());
				order.setRefundStatus(afterProduct.getStatus().toString());
				this.orderMapper.updateByPrimaryKeySelective(order);
				
				/**保存申述申请**/
				afterProduct.setAddTime(new Date());
				afterProduct.setCreateTime(new Date());		
				afterProduct.setAppeal(2);
				afterProduct.setAppealReturnId(afterProduct.getReturnId());
				aftersalesReturnProductMapper.insertSelective(afterProduct);
				
				/**写入售后日志**/
				AftersalesReturnLogWithBLOBs aftersalesReturnLog=new AftersalesReturnLogWithBLOBs();
				aftersalesReturnLog.setOpId(Integer.valueOf(afterProduct.getOpId()));
				aftersalesReturnLog.setOpName(afterProduct.getOpName());
				aftersalesReturnLog.setOrderId(afterProduct.getOrderId());
				aftersalesReturnLog.setReturnId(afterProduct.getReturnId());
				aftersalesReturnLog.setRole(AfterSalesRole.STATUS_MEMBER.getKey());
				aftersalesReturnLog.setLogText(AfterSalesStatus.STATUS_1.getValue());
				aftersalesReturnLog.setAlttime(new Date());
				aftersalesReturnLogMapper.insertSelective(aftersalesReturnLog);
				head.setRetCode(ErrorCode.SUCCESS);
			}
		}catch(Exception e){
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return head;
	}
	
	public Object applyRefunds(Object data){
		HeadObject head=new HeadObject();
		try{
			ReshipDTO dto = (ReshipDTO)data;
			Order order=new Order();
			order.setOrderId(dto.getOrderId());
			order.setStatus(OrderStatus.REFUNDS.getKey());//设置状态为退款中
			if(!dto.getOrderId().toString().startsWith("333")){
				this.orderMapper.updateByPrimaryKeySelective(order);//修改订单退货状态
			}
			
			OrderLog orderLog=new OrderLog();
			orderLog.setOrderId(dto.getOrderId());
			orderLog.setOpId(dto.getOpId());
			orderLog.setOpName(dto.getOpName());
			orderLog.setBillType("order");
			orderLog.setAlttime(new Date());
    		orderLog.setLogText("等待卖家处理退款申请，请耐心等待。");
    		orderLog.setBehavior(OrderLogBehavior.REFUNDS.getKey());
    		orderLogMapper.insertSelective(orderLog);
			
    		dto.setAddTime(new Date());
    		dto.setCreateTime(new Date());
			aftersalesReturnProductMapper.insertSelective(dto);
			
			/**写入售后日志**/
			AftersalesReturnLogWithBLOBs aftersalesReturnLog=new AftersalesReturnLogWithBLOBs();
			aftersalesReturnLog.setOpId(Integer.valueOf(dto.getOpId()));
			aftersalesReturnLog.setOpName(dto.getOpName());
			aftersalesReturnLog.setOrderId(dto.getOrderId());
			aftersalesReturnLog.setReturnId(dto.getReturnId());
			aftersalesReturnLog.setRole(AfterSalesRole.STATUS_MEMBER.getKey());
			aftersalesReturnLog.setLogText("提退款申请,等待卖家处理申请");
			aftersalesReturnLog.setAlttime(new Date());
			aftersalesReturnLogMapper.insertSelective(aftersalesReturnLog);
			
			head.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
	
	
}