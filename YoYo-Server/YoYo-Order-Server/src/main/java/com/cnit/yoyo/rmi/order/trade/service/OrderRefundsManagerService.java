package com.cnit.yoyo.rmi.order.trade.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.order.OrderRefundsBillsMapper;
import com.cnit.yoyo.dao.order.OrderRefundsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.dto.OrderRefundsBillsDTO;
import com.cnit.yoyo.model.order.dto.OrderRefundsBillsQryDTO;
import com.cnit.yoyo.model.order.dto.OrderRefundsDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**  
* @Title: OrderManagerService.java
* @Package com.cnit.yoyo.rmi.order.service
* @Description: 卖家中心>>交易管理>>退款管理 Service
* @Author 王鹏
* @date 2015-4-18 下午3:06:43
* @version V1.0  
*/ 
@Service("orderRefundsManagerService")
@SuppressWarnings("unchecked")
public class OrderRefundsManagerService {
	private static final Log log = LogFactory.getLog(OrderManagerService.class);

	@Autowired
	private OrderRefundsMapper orderRefundsMapper;
	
	@Autowired
	private OrderRefundsBillsMapper orderRefundsBillsMapper;
	
	/**
	  * @description <b>分页查询退款管理列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-18
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object qryOrderRefundsList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<OrderRefundsBillsDTO> resultPage = null;
		try {
			OrderRefundsBillsQryDTO qryDTO=(OrderRefundsBillsQryDTO)data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			resultPage = new ResultPage<OrderRefundsBillsDTO>(orderRefundsBillsMapper.selectRefundsBills(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, resultPage);
	}
	
	
	public Object qryOrderRefundsById(Object data) {
		HeadObject head = new HeadObject();
		OrderRefundsDTO orderRefunds = null;
		try {
			orderRefunds = orderRefundsMapper.selectOrderRefundById((String) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, orderRefunds);
	}
	
}
