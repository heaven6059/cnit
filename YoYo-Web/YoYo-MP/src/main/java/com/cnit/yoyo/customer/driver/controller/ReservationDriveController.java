package com.cnit.yoyo.customer.driver.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.other.drive.dto.ReservationDriveQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

@Controller
@RequestMapping("/reservationDrive")
public class ReservationDriveController {
	@Autowired
	private RemoteService otherService;

	/**
	  * @description <b>进入预约试驾界面</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-27
	  * @param @return
	  * @return Object
	*/
	@RequestMapping("/toReservationDrive")
	public Object toReservationDrive(){
		return "pages/biz/driveOrReplace/driveList";
	}
	
	/**
	  * @description <b>加载预约试驾数据</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-27
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	@ResponseBody
    @RequestMapping("/loadReservationDrive")
    public Object loadReservationDrive(HttpServletRequest request, ReservationDriveQryDTO qryDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990401-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try{
        	resultObject = otherService.doService(new RequestObject(headObject,qryDTO));
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return resultObject;
    }
	
	/**
	  * @description <b>删除预约试驾</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-27
	  * @param @param request
	  * @param @param qryDTO
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	@ResponseBody
   @RequestMapping("/deleteReservationDrive")
   public Object deleteReservationDrive(HttpServletRequest request, @RequestParam(value="ids",required=true)Integer [] ids) throws Exception {
       HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990401-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
       ResultObject resultObject = null;
       try{
       	resultObject = otherService.doService(new RequestObject(headObject,ids));
       }catch (Exception e) {
       	e.printStackTrace();
       }
       return resultObject;
   }
}
