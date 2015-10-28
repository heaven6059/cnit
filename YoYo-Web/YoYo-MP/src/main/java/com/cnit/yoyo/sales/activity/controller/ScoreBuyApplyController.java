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
import com.cnit.yoyo.model.sales.activity.ScoreBuyApply;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

/**
 * @Description: 积分换购申请
 */
@Controller
@RequestMapping("/scoreBuyApply")
public class ScoreBuyApplyController {

	@Autowired
	private RemoteService salesService;

	@Autowired
	private RemoteService itemService;
	
	
	@RequestMapping("/index")
	public String toScoreBuyList(HttpServletRequest request) {
		request.setAttribute("type",request.getParameter("t"));
		return "pages/biz/sales/scoreBuyApplyList";
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
		return "pages/biz/sales/scoreBuyActivityEdit";
	}
	
	@RequestMapping("/toMainCategoryList")
	public String toMainCategoryList(HttpServletRequest request) {
		return "pages/biz/sales/mainCategoryList";
	}

	/**
	  * @description <b>加载积分换购活动列表</b>
	*/
	@ResponseBody
	@RequestMapping("/loadScoreBuyApplyList")
	public Object loadScoreBuyApplyList(HttpServletRequest request,ScoreBuyApplyQryDTO activityQryDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-01");
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
	public Object deleteBuyActivity(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060101-04");
	    ResultObject resultObject = null;
	    try {
	        resultObject = salesService.doService(new RequestObject(headObject, ids));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
	}
	/**
	  * @description 更新活动申请状态
	*/
	@ResponseBody
	@RequestMapping("/updateScoreBuyApply")
	public Object updateScoreBuyApply(HttpServletRequest request,Integer id,String type){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-03");
	    ResultObject resultObject = null;
	    try {
	    	ScoreBuyApply dto=new ScoreBuyApply();
	    	dto.setId(id);
	    	if(type.equals("1")){
	    		dto.setStatus(2);
	    	}else{
	    		dto.setStatus(3);
	    	}
	        resultObject = salesService.doService(new RequestObject(headObject, dto));
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
		return "redirect:/scoreBuyActivity/toScoreBuyActivityList";
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
