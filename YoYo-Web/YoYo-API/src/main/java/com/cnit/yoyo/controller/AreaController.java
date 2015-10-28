package com.cnit.yoyo.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Area;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller("areaController")
@RequestMapping("/areaController")
public class AreaController extends BaseController {
	@Autowired
	private RemoteService itemService;

	@RequestMapping("/findArea")
	@ResponseBody
	public Object findArea(String data, HttpServletRequest request)
			throws Exception {
		log.info("###########AreaController.findArea-->start");
		log.info("----------------------data:" + data+ "-------------------------");
		ResultObject result = null;
		try{
			//获取请求参数
			JSONObject jsonData =  JSONObject.fromObject(data);
			String areaParentId = (String) CommonUtil.getJsonValue(jsonData, "areaParentId"); 
			String areaDeep = (String) CommonUtil.getJsonValue(jsonData, "areaDeep");
			
			Area area = new Area(); 
			area.setAreaParentId(areaParentId==null?0:Integer.valueOf(areaParentId));
			area.setAreaDeep(areaDeep==null?1:Integer.valueOf(areaDeep));
			HeadObject headObject = CommonHeadUtil.geneHeadObject("areaService.findByDeepAndPid");
			result =  (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, area));
			log.info("###########AreaController.findArea-->end");
			log.info("----------------------data:" + data+ "-------------------------");
			return result;
		}catch(Exception e){
			log.error(e.getMessage(),e);
			return processExpction(e.getMessage());
		}
	}

}
