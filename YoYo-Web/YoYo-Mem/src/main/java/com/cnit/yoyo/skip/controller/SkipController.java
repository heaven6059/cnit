package com.cnit.yoyo.skip.controller;
/**   
 * @Description: 跳转页面
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/skip")
public class SkipController {
	
	 
	 /**
	  * 
	  * @Description: 跳转到投诉列表页面
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/complain")
	 public Object complain() { 
		 return "/pages/biz/skip/complain";
	 }
	 
	 /**
	  * 
	  * @Description: 跳转到投诉详细页面
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/complainComments")
	 public Object complainComments(HttpServletRequest request,@Param(value="complainId")String complainId) { 
		 request.setAttribute("complainId", complainId);
		 return "/pages/biz/skip/complainComments";
	 }
	 
	 

	 /**
	  * 
	  * @Description: 跳转到举报详细页面
	  * @param request
	  * @param userId
	  * @return
	 * @throws Exception 
	  */
	 @RequestMapping("/reportComment")
	 public Object reportComment(HttpServletRequest request,@Param(value="reportId")String reportId) { 
		 request.setAttribute("reportId", reportId);
		 return "/pages/biz/skip/reportComment";
	 }
	 

}

