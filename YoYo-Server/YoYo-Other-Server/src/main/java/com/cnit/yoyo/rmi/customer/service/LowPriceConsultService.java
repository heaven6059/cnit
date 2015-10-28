package com.cnit.yoyo.rmi.customer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**  
* @Title: LowPriceConsultService.java
* @Package com.cnit.yoyo.rmi.customer.service
* @Description: 最低价咨询
* @Author 王鹏
* @date 2015-5-27 下午2:47:39
* @version V1.0  
*/ 
@Service("lowPriceConsultService")
public class LowPriceConsultService {
	@Autowired
	private RemoteService itemService;
	
	public static final Logger log = LoggerFactory.getLogger(LowPriceConsultService.class);
	
	/**
	  * @description <b>查询最低价咨询列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-27
	  * @param @return
	  * @return Object
	*/
	public Object findLowPriceConsult(Object data){
		HeadObject headObject = CommonHeadUtil.geneHeadObject("driveOrConsultService.selectLowPriceConsult");
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
	   public Object deleteLowPriceConsult(Object data){
		   log.info("satart[LowPriceConsultService.deleteLowPriceConsult]");
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
