package com.cnit.yoyo.advance.controller;
/**   
 * @Description: 预存款
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/advance")
public class AdvanceController {
	
	 @Autowired
	 private RemoteService  memberService ;
	 
	 @Autowired
	 private RedisClientUtil redisService;//redis缓存服务
	 
	 /**
	  * 
	  * @Description: 获取预存款历史记录
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/getAdvanceListPage")
	 public Object getAdvanceListPage() throws Exception
	 { 
		
		 return "/pages/biz/advance/advanceList";
	 }
	 
	 /**
	  * 
	  * @Description: 充值页面
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/advanceRecharge")
	 public Object advanceRecharge() throws Exception
	 { 
		
		 return "/pages/biz/advance/advanceRecharge";
	 }	 
	 
	 
	 /**
	  * 
	  * @Description: 存款列表
	  * @param request
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/getAdvanceList")
	 @ResponseBody
	 public Object getAdvanceList(HttpServletRequest request  ) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030109-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 String  userCache = redisService.get(request.getSession().getId()) ;
		 JSONObject  json =JSONObject.fromObject(userCache);
		 String memberId = "";
		/* if(json!=null){
			 memberId  = (String)json.get("memberId");
		 }*/
		 memberId = "62";
		 String num =  request.getParameter("pageNum");
		 String size = request.getParameter("pageSize");
		 int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
		 int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size); 
		 Map<String,Object> paraData = new HashMap<String, Object>();
		 paraData.put("memberId", memberId);
		 paraData.put("pageNum", pageNum);
		 paraData.put("pageSize", pageSize);
		 RequestObject  requestObject  =  new RequestObject(headObject, paraData);
		 ResultObject resultObject =(ResultObject) memberService.doService(requestObject);
		 return resultObject.getContent();
	 }
	 
	 
	 /**
	  * 
	  * @Description: 更改存款
	  * @param request
	  * @return
	  * @throws Exception
	  */
	 @RequestMapping("/updateAdvance")
	 @ResponseBody
	 public Object updateAdvance(HttpServletRequest request  ) throws Exception
	 { 
		 HeadObject headObject =  CommonHeadUtil.geneHeadObject(request, "030107-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK) ;
		 String  userCache = redisService.get(request.getSession().getId()) ;
		 JSONObject  json =JSONObject.fromObject(userCache);
		 String memberId = "";
		/* if(json!=null){
			 memberId  = (String)json.get("memberId");
		 }*/
		 memberId = "demo";
		 String num =  request.getParameter("pageNum");
		 String size = request.getParameter("pageSize");
		 int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
		 int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size); 
		 Map<String,Object> paraData = new HashMap<String, Object>();
		 paraData.put("memberId", memberId);
		 paraData.put("pageNum", pageNum);
		 paraData.put("pageSize", pageSize);
		 RequestObject  requestObject  =  new RequestObject(headObject, paraData);
		 ResultObject resultObject =(ResultObject) memberService.doService(requestObject);
		 return resultObject.getContent();
	 }

}

