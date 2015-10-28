package com.cnit.yoyo.sales.activity.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @ClassName: CyberauctionController  
 * @Description: 一元秒杀
 * @detail <详细说明>
 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
 * @date 2015-7-27 下午4:19:52  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/cyberauction")
public class CyberauctionController {

	@Autowired
	private RemoteService salesService;

	@Autowired
	private RemoteService itemService;
	
	/**
	 * @Title:  toCyberauctionList  
	 * @Description:  拍卖活动列表页面
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-7-27 下午6:08:30  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/cyberauctionList")
	public String toCyberauctionList(HttpServletRequest request) {
		return "pages/biz/activity/cyberauctionList";
	}
	
	/**
	 * @Title:  loadCyberauction  
	 * @Description:  加载拍卖列表  
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-7-27 下午6:11:04  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param activityQryDTO
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@ResponseBody
	@RequestMapping("/loadCyberauction")
	public Object loadCyberauction(HttpServletRequest request,ScoreBuyActivityQryDTO activityQryDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060101-02");
	    ResultObject resultObject = null;
	    try {
	        resultObject = salesService.doService(new RequestObject(headObject, activityQryDTO));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
	}
	
	/**
	 * @Title:  toScoreBuy  
	 * @Description:  新增或者编辑拍卖活动
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-7-27 下午6:09:34  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @return
	 * @return String  返回类型 
	 * @throws
	 */
	@RequestMapping("/cyberauctionDetail")
	public String toCyberauction(HttpServletRequest request) {
		if("edit".equals(request.getParameter("op"))){
			   ResultObject resultObject = null;
			    try {
			    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060101-03");
			    	Integer act_id=Integer.parseInt(request.getParameter("id"));
			        resultObject = salesService.doService(new RequestObject(headObject, act_id));
			        Map<String, Object> resultmap=(Map<String, Object>) resultObject.getContent();
			        request.setAttribute("ScoreBuyActivity",resultmap.get("scoreBuyActivity"));
			        request.setAttribute("categorys",resultmap.get("categorys"));
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
		}
		return "pages/biz/activity/cyberauction";
	}
}
