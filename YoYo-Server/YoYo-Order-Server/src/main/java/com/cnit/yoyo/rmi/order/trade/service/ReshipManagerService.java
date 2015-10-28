package com.cnit.yoyo.rmi.order.trade.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.dao.trade.ReshipLogMapper;
import com.cnit.yoyo.dao.trade.ReshipMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.trade.Reship;
import com.cnit.yoyo.model.trade.ReshipLog;
import com.cnit.yoyo.model.trade.dto.ReshipDTO;
import com.cnit.yoyo.util.OrderUtils;

/**  
* @Title: ReshipManagerService.java
* @Package com.cnit.yoyo.rmi.trade.service
* @Description: 卖家中心>>交易管理>>退货管理Service
* @Author 王鹏
* @date 2015-4-16 下午2:26:26
* @version V1.0  
*/ 
@Service("reshipManagerService")
@SuppressWarnings("unchecked")
public class ReshipManagerService {
	
	private static final Log log = LogFactory.getLog(OrderManagerService.class);
	
	@Autowired
	private ReshipMapper reshipMapper;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private ReshipLogMapper reshipLogMapper;
	
	/**
	  * @description
	  * 	获取退货申请列表 
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-16
	  * @param 
	  * @return
	*/
	public Object qryReshipList(Object data) {
		HeadObject head = new HeadObject();
		List<Reship> list = null;
		try {
			Map<String, Object> params = (Map<String, Object>) data;
			list = this.reshipMapper.selectReshipInfo(params);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, list);
	}
	
	
	/**
	  * @description
	  * 	获取退货申请列表 
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-16
	  * @param 
	  * @return
	*/
	public Object qryReshipById(Object data) {
		HeadObject head = new HeadObject();
		ReshipDTO reshipDTO = null;
		try {
			reshipDTO = this.reshipMapper.selectReshipById((Long) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, reshipDTO);
	}
	
	/**
	  * @description
	  * 	获取退货申请列表 
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-16
	  * @param 
	  * @return
	*/
	public Object updateReshipStatus(Object data) {
		HeadObject head = new HeadObject();
		try {
			ReshipDTO reshipDTO=(ReshipDTO)data;
			Order order=new Order();
			order.setOrderId(reshipDTO.getOrderId());
			order.setRefundStatus(reshipDTO.getStatus().toString());
			this.orderMapper.updateByPrimaryKeySelective(order);//修改订单退货状态
			
			ReshipLog reshipLog=new ReshipLog();
			reshipLog.setOrderId(reshipDTO.getOrderId());
			reshipLog.setReshipId(reshipDTO.getReshipId());
			reshipLog.setOpLog(OrderUtils.getPropertyValue(reshipDTO.getStatus().toString()));
			reshipLog.setOpId(1);
			reshipLog.setOpName("店小二");
			reshipLog.setOpDate(new Date());
			reshipLog.setOpUserType(2);
			this.reshipLogMapper.insertSelective(reshipLog);//写入操作日志
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
}

