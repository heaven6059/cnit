package com.cnit.yoyo.rmi.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRole;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.dao.trade.AftersalesReturnProductMapper;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.model.trade.dto.ReturnProductDTO;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.order.trade.service.ReturnProductManagerService;
@Service
public class OrderTaskService {
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private AftersalesReturnProductMapper aftersalesReturnProductMapper;
	
	@Autowired
	private ReturnProductManagerService returnProductManagerService;
	/**
	  * @description <b>取消订单任务</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-7-1
	  * @param 
	  * @return void
	*/
	public void autoCancelOrderTask(){
		try{
			orderMapper.updateOrderStatusToDead();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @description <b>自动处理退款留流程</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-28
	 * void
	*/
	public void autoProcessAfterSales(Object object){
		List<ReturnProductDTO> returnProduct = aftersalesReturnProductMapper.selectOverdueReturnProductInfo();
		for (ReturnProductDTO returnProductDTO : returnProduct) {
			ReshipDTO reshipDTO=new ReshipDTO();
			reshipDTO.setReturnId(returnProductDTO.getReturnId());
			reshipDTO.setOrderId(returnProductDTO.getOrderId());
			reshipDTO.setOpName(AfterSalesRole.STATUS_SYSTEM.getValue());
			reshipDTO.setRole(AfterSalesRole.STATUS_SYSTEM.getKey());
			reshipDTO.setStatus(AfterSalesStatus.STATUS_9.getKey());
			reshipDTO.setLogText(AfterSalesStatus.STATUS_9.getValue());
			returnProductManagerService.updateReturnProductStatus(reshipDTO);
		}
	}
}
