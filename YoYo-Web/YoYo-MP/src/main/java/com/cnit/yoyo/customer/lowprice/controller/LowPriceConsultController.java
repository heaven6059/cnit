package com.cnit.yoyo.customer.lowprice.controller;

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
@RequestMapping("/lowPriceConsult")
public class LowPriceConsultController {
	@Autowired
	private RemoteService otherService;

	/**
	  * @description <b>进入最低价咨询界面</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-27
	  * @param @return
	  * @return Object
	*/
	@RequestMapping("/toLowPriceConsult")
	public Object toLowPriceConsult(){
		return "pages/biz/driveOrReplace/lowPriceConsultList";
	}
	
	/**
	  * @description <b>加载最低价咨询列表数据</b>
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
    @RequestMapping("/loadLowPriceConsult")
    public Object loadLowPriceConsult(HttpServletRequest request, ReservationDriveQryDTO qryDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990402-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try{
        	resultObject = otherService.doService(new RequestObject(headObject,qryDTO));
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return resultObject;
    }
	
	
	/**
	  * @description <b>删除最低价咨询列表数据</b>
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
    @RequestMapping("/deleteLowPriceConsult")
    public Object deleteLowPriceConsult(HttpServletRequest request, @RequestParam(value="ids",required=true)Integer [] ids) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990402-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try{
        	resultObject = otherService.doService(new RequestObject(headObject,ids));
        }catch (Exception e) {
        	e.printStackTrace();
        }
        return resultObject;
    }
}
