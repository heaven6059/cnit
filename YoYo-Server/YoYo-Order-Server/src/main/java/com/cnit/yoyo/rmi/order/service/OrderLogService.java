package com.cnit.yoyo.rmi.order.service;

import java.util.Date;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.dao.order.OrderLogMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.model.order.OrderLog;

@Service("orderLogService")
public class OrderLogService {

	@Autowired
	private OrderLogMapper orderLogMapper;
	
	/**
	 * @description <b>保存订单日志</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年8月5日
	 * @param object
	 * @return
	 * Object
	*/
	public Object addOrderLog(Object object){
		HeadObject headObject=new HeadObject();
		try{
			OrderLog orderLog=(OrderLog)object;
			orderLog.setBillType("order");
			orderLog.setAlttime(new Date());
//			orderLog.setOpId(0);
			orderLogMapper.insertSelective(orderLog);
			headObject.setRetCode(ErrorCode.SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return headObject;
	}
}
