package com.cnit.yoyo.member.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.other.consult.ConsultCommentArguments;
import com.cnit.yoyo.model.other.consult.ConsultCommentBaseArguments;
import com.cnit.yoyo.model.other.consult.ConsultItems;
import com.cnit.yoyo.model.other.consult.GoodsPoint;
import com.cnit.yoyo.model.other.consult.dto.ConsultItemsDTO;
import com.cnit.yoyo.model.other.consult.dto.GoodsPointDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**  
* @Title: ConsultCommentArgsCntroller.java
* @Package com.cnit.yoyo.member.controller
* @Description: 咨询评论参数设置Controller
* @Author 王鹏
* @date 2015-4-27 下午2:00:12
* @version V1.0  
*/ 
@Controller
@RequestMapping("/consultCommentArgs")
public class ConsultCommentArgsController {
	
	@Autowired
	private RemoteService otherService;
	
	/**
	  * @description <b>描述</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-27
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/toConsultCommentArgs")
	public String toConsultCommentArgs(HttpServletRequest request){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990101-06");
		try{
			ResultObject resultObject=otherService.doService(new RequestObject(headObject));
			request.setAttribute("args", resultObject.getContent());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/comment/consultCommentArgs";
	}
	
	/**
	  * @description <b>保存咨询评论基础设置</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-28
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/saveConsultCommentBaseArgs")
	public String saveConsultCommentBaseArgs(HttpServletRequest request,ConsultCommentBaseArguments baseArguments){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990101-01");
		try{
			ResultObject resultObject=otherService.doService(new RequestObject(headObject, baseArguments));
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return "redirect:/consultCommentArgs/toConsultCommentArgs";
	}
	
	
	/**
	  * @description <b>保存咨询设置</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-28
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/saveConsultArgs")
	public String saveConsultArgs(HttpServletRequest request,ConsultCommentArguments arguments,ConsultItemsDTO itemsDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990101-02");
		try{
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("arguments", arguments);
			params.put("items", itemsDTO);
			
			ResultObject resultObject=otherService.doService(new RequestObject(headObject, params));
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return "redirect:/consultCommentArgs/toConsultCommentArgs";
	}
	
	/**
	  * @description <b>保存评论设置</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-28
	  * @param @param request
	  * @param @return
	  * @return String
	*/
	@RequestMapping("/saveCommentArgs")
	public String saveCommentArgs(HttpServletRequest request,ConsultCommentArguments arguments){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990101-07");
		try{
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("arguments", arguments);
			params.put("point", request.getParameter("goodsPoint"));
			ResultObject resultObject=otherService.doService(new RequestObject(headObject, params));
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return "redirect:/consultCommentArgs/toConsultCommentArgs";
	}
	
	@RequestMapping("/deleteConsultItems")
	public Object deleteConsultItems(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990101-08");
		try{
			ResultObject resultObject=otherService.doService(new RequestObject(headObject, id));
			return resultObject.getHead();
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}
	
	@RequestMapping("/deleteGoodsPoint")
	public Object deleteGoodsPoint(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990101-05");
		try{
			ResultObject resultObject=otherService.doService(new RequestObject(headObject, id));
			return resultObject.getHead();
		}catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return headObject;
		}
	}
}
