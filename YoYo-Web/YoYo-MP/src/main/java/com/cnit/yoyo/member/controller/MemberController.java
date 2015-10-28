/**
 * 文 件 名   :  MemberController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-27 上午9:34:26
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.dto.MemberDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 会员管理
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private RemoteService memberService;

	/**
	 * @Title: getMemberList
	 * @Description: 根据不同注册渠道获得会员列表
	 * @param @param channel 注册渠道
	 * @param @param request
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Object 返回类型
	 * @throws
	 */

	@RequestMapping("/members")
	public String listMembers() {
		return "pages/biz/memberMng/memberList";
	}

	@RequestMapping("/getMemberList")
	@ResponseBody
	public Object getMemberList(@RequestParam(value = "channel", required = false) String channel,
			HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-09", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		String param = request.getParameter("paramType");
		String paramVal = request.getParameter("paramVal");
		data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		data.put("nickName", paramVal);
		if (StringUtils.isNotBlank(channel)) {
			data.put("channel", channel);
		}
		return memberService.doService(new RequestObject(headObject, data));
	}

	/*
	 * @RequestMapping("/findMemberList")
	 * 
	 * @ResponseBody public Object findMemberList(HttpServletRequest request,
	 * MemberDTO member) throws Exception { HeadObject headObject =
	 * CommonHeadUtil.geneHeadObject(request, "1000020102-16",
	 * GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	 * Map<String, Object> data = new HashMap<String, Object>(); String
	 * pageIndex = request.getParameter("page"); String pageSize =
	 * request.getParameter("rows"); String paramVal =
	 * request.getParameter("paramVal"); data.put("pageIndex",
	 * StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX :
	 * Integer.parseInt(pageIndex)); data.put("pageSize",
	 * StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE :
	 * Integer.parseInt(pageSize)); data.put("nickName", paramVal); if (member
	 * != null && StringUtils.isNotBlank(member.getStartTime())) { // 注册日期的判断
	 * member.setStartTime(DateUtils.parserStr2Str(member.getStartTime())); } if
	 * (member != null && StringUtils.isNotBlank(member.getEndTime())) {
	 * member.setEndTime(DateUtils.parserStr2Str(member.getEndTime())); }
	 * data.put("member", member); return memberService.doService(new
	 * RequestObject(headObject, data)); }
	 */

	@RequestMapping("/findMemberList")
	@ResponseBody
	public Object findMemberList(HttpServletRequest request, Integer page, Integer rows, String sort, String order,
			String channel) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-16", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = null;
		String params = request.getParameter("params");
		if (!StringUtil.isEmpty(params)) {
			content = JSONObject.fromObject(params);
		} else {
			content = new JSONObject();
		}
		if (!StringUtil.isEmpty(channel)) {
			JSONArray likes = content.optJSONArray("likes");
			if (likes == null) {
				likes = new JSONArray();
			}
			JSONObject likeParam = new JSONObject();
			likeParam.put("paramName", "sourceQ");
			likeParam.put("paramValue", channel);
			likeParam.put("wildType", "right");
			likes.add(likeParam);
			content.put("likes", likes);
		}
		content.put("page", page);
		content.put("rows", rows);
		content.put("sort", sort);
		content.put("order", order);
		return memberService.doService(new RequestObject(headObject, content));
	}

	@RequestMapping("/newMember")
	@ResponseBody
	public Object newMember(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-10", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("loginName", request.getParameter("loginName"));
		data.put("password", request.getParameter("password"));
		String isEtps = request.getParameter("isEtps");
		if (!StringUtil.isEmpty(isEtps) && "1".equalsIgnoreCase(isEtps)) {
			data.put("accountType", GlobalStatic.ACCOUNT_TYPE_SELLER);
		} else {
			data.put("accountType", GlobalStatic.ACCOUNT_TYPE_BUYER);
		}
		data.put("memberLvId", request.getParameter("memberLvId"));
		data.put("email", request.getParameter("email"));
		data.put("regIp", request.getRemoteHost());
		return memberService.doService(new RequestObject(headObject, data));
	}

	@RequestMapping("/deleteMember")
	@ResponseBody
	public Object deleteMember(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-13", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("accountId", request.getParameter("accountId"));
		return memberService.doService(new RequestObject(headObject, data));
	}

	@RequestMapping("/memberDetail")
	public Object memberDetail(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-11", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_BACK);
		ModelAndView modelView = new ModelAndView();
		String accountId = request.getParameter("accountId");
		if (!StringUtil.isEmpty(accountId)) {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("accountId", Integer.parseInt(accountId));
			ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
			if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
				modelView.addObject("content", resultObject.getContent());
			}
		}
		modelView.setViewName("pages/biz/memberMng/memberEditor");
		return modelView;
	}

	@RequestMapping("/modifyMember")
	@ResponseBody
	public Object modifyMember(HttpServletRequest request, Member member) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		String temp = request.getParameter("memberId");
		if (!StringUtil.isEmpty(temp)) {
			member.setMemberId(Integer.parseInt(temp));
		}
		member.setEmail(request.getParameter("email"));
		temp = request.getParameter("memberLvId");
		if (!StringUtil.isEmpty(temp)) {
			member.setMemberLvId(Integer.parseInt(temp));
		}
		member.setName(request.getParameter("name"));
		member.setNickName(request.getParameter("nickName"));
		member.setIdcard(request.getParameter("idcard"));
		member.setArea(request.getParameter("area"));
		member.setAddr(request.getParameter("addr"));
		member.setSex(request.getParameter("sex"));
		member.setMobile(request.getParameter("mobile"));
		member.setTel(request.getParameter("tel"));
		member.setZip(request.getParameter("zip"));
		temp = request.getParameter("birth");
		if (!StringUtil.isEmpty(temp)) {
			member.setbYear(temp.split("\\-")[0]);
			member.setbMonth(temp.split("\\-")[1]);
			member.setbDay(temp.split("\\-")[2]);
		}
		return memberService.doService(new RequestObject(headObject, JSONObject.fromObject(member)));
	}

}
