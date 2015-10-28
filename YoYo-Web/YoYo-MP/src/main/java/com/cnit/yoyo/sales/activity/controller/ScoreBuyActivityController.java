package com.cnit.yoyo.sales.activity.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.sales.activity.ScoreBuyActivity;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

/**
 * @Title: ScoreBuyController.java
 * @Package com.cnit.yoyo.sales.activity.controller
 * @Description: 积分换购Controller
 * @Author 王鹏
 * @date 2015-5-5 下午4:49:11
 * @version V1.0
 */
@Controller
@RequestMapping("/scoreBuyActivity")
public class ScoreBuyActivityController {

	@Autowired
	private RemoteService salesService;

	@Autowired
	private RemoteService itemService;
	
	/**
	 * 积分换购
	 * @Description: 
	 * @param request
	 * @return
	 * @author  wanghb
	 */
	@RequestMapping("/toScoreBuyActivityList")
	public String toScoreBuyList(HttpServletRequest request) {
		request.setAttribute("type",request.getParameter("t"));
		return "pages/biz/sales/scoreBuyActivityList";
	}
	/**
	 * 限时抢购
	 * @Description: 
	 * @param request
	 * @return
	 * @author  wanghb
	 */
	@RequestMapping("/toTimeBuyList")
	public String toTimeBuyList(HttpServletRequest request) {
		return "pages/biz/sales/timeBuyList";
	}
	/**
	 * 团购
	 * @Description: 
	 * @param request
	 * @return
	 * @author  wanghb
	 */
	@RequestMapping("/toGroupList")
	public String toGroupList(HttpServletRequest request) {
		return "pages/biz/sales/groupList";
	}
	/**
	 * 秒杀
	 * @Description: 
	 * @param request
	 * @return
	 * @author  wanghb
	 */
	@RequestMapping("/toSeckillList")
	public String toSeckillList(HttpServletRequest request) {
		return "pages/biz/sales/seckillList";
	}
	

	@RequestMapping("/toScoreBuyActivity")
	public String toScoreBuy(HttpServletRequest request) {
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
		 request.setAttribute("type",request.getParameter("type"));
		return "pages/biz/sales/scoreBuyActivityEdit";
	}
	
	@RequestMapping("/toMainCategoryList")
	public String toMainCategoryList(HttpServletRequest request) {
		return "pages/biz/sales/mainCategoryList";
	}

	/**
	  * @description <b>加载积分换购活动列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-6
	  * @param @param request
	  * @param @param activityQryDTO
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
	@RequestMapping("/loadScoreBuyActivity")
	public Object loadScoreBuyActivity(HttpServletRequest request,ScoreBuyActivityQryDTO activityQryDTO){
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
	  * @description 删除活动
	*/
	@ResponseBody
	@RequestMapping("/deleteBuyActivity")
	public Object deleteBuyActivity(HttpServletRequest request,String ids){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060101-04");
	    ResultObject resultObject = null;
	    try {
	        resultObject = salesService.doService(new RequestObject(headObject, ids));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
	}

	@ResponseBody
	@RequestMapping("/optActivity")
	public Object optActivity(HttpServletRequest request,ScoreBuyActivity activity){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060101-04");
	    ResultObject resultObject = null;
	    try {
	    	headObject = CommonHeadUtil.geneHeadObject(request,"060101-01");	
	    	resultObject=salesService.doService(new RequestObject(headObject, activity));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
	}
	
	
	/**
	  * @description <b>保存积分换购活动</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-6
	  * @param @param request
	  * @param @param activity
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/saveScoreBuyActivity")
	public String saveScoreBuyAcitvity(HttpServletRequest request,ScoreBuyActivity activity) {
		try {
			activity.setActStatus(1);
			activity.setEndTime(DateUtils.parser(request.getParameter("end_time")));
			activity.setStartTime(DateUtils.parser(request.getParameter("start_time")));
			activity.setApplyStartTime(DateUtils.parser(request.getParameter("apply_start_time")));
			activity.setApplyEndTime(DateUtils.parser(request.getParameter("apply_end_time")));
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"060101-01");	
			salesService.doService(new RequestObject(headObject, activity));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/scoreBuyActivity/toScoreBuyActivityList?t="+activity.getType()+"";
	}
	
	
	
	/**
	  * @description <b>获取所有主分类信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-6
	  * @param @param request
	  * @param @return
	  * @param @throws Exception
	  * @return Object
	*/
	@RequestMapping("/findAllMainCategory")
	@ResponseBody
	public Object findAllMainCategory(HttpServletRequest request)throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010401-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        JSONObject data=new JSONObject();
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        return itemService.doService(new RequestObject(headObject, data));
	}
}
