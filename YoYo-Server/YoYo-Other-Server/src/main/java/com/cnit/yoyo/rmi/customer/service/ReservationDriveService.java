package com.cnit.yoyo.rmi.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;


/**  
* @Title: ReservationDriveService.java
* @Package com.cnit.yoyo.rmi.customer.service
* @Description: 客服中心 预约试驾Serivce
* @Author 王鹏
* @date 2015-5-27 下午1:13:52
* @version V1.0  
*/ 
@Service("reservationDriveService")
public class ReservationDriveService {
	@Autowired
	private RemoteService itemService;
	
	/**
	  * @description <b>查询预约试驾</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-27
	  * @param @return
	  * @return Object
	*/
	public Object findReservationDrive(Object data){
		HeadObject headObject = CommonHeadUtil.geneHeadObject("driveOrConsultService.selectReservationDrive");
		Object resultObject=null;
		try{
			resultObject=itemService.doServiceByServer(new RequestObject(headObject,data));
			return resultObject;
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return new ResultObject(headObject);
		}
	}
	
	  /**
		  * @description <b>删除询问最低价</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-5-27
		  * @param @param data
		  * @param @return
		  * @return Object
		  */
		   public Object deleteReservationDrive(Object data){
			   HeadObject headObject = CommonHeadUtil.geneHeadObject("driveOrConsultService.deleteDriveOrConsult");
			   Object resultObject=null;
			   try{
					resultObject=itemService.doServiceByServer(new RequestObject(headObject,data));
					return resultObject;
			   }catch (Exception e) {
				   	e.printStackTrace();
					headObject.setRetCode(ErrorCode.FAILURE);
					return new ResultObject(headObject);
			   }
		   }
}
