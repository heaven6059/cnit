package com.cnit.yoyo.membercoupon.controller;

/**   
 * @Description: 会员优惠券
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-5 下午2:13:50 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.coupon.model.dto.MemberCouponDTO;
import com.cnit.yoyo.coupon.model.dto.MemberCouponQryDTO;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.sun.tools.internal.ws.processor.model.Request;

@Controller
@RequestMapping("/memberCoupon")
public class MemberCouponController {

	@Autowired
	private RemoteService memberService;
	

	/**
	 * 
	 * @Description: to会员优惠券列表页面
	 * @param request
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/memberCoupon")
	public Object getMemberCouponListPage(HttpServletRequest request, Integer type) throws Exception {
		request.setAttribute("type", type);
		return "/pages/biz/membercoupon/memberCouponList";
	}

	/**
	 * 
	 * @Description: 积分记录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getMemberCouponList")
	public Object getMemberCouponList(HttpServletRequest request, MemberCouponQryDTO couponQryDTO) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030115-04");
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		couponQryDTO.setMemberId(Long.valueOf(memberListDo.getMemberId()));
		RequestObject requestObject = new RequestObject(headObject, couponQryDTO);
		ResultObject resultObject = (ResultObject) memberService.doService(requestObject);
		return resultObject.getContent();
	}

	/**
	 * @description <b>删除用户优惠卷</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-27
	 * @param request
	 * @param memberCouponDTO
	 * @return
	 * @throws Exception
	 *             Object
	 */
	@ResponseBody
	@RequestMapping("/deleteMemberCoupon")
	public Object deleteMemberCoupon(HttpServletRequest request, MemberCouponDTO memberCouponDTO) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030115-05");
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		memberCouponDTO.setMemberId(Integer.valueOf(memberListDo.getMemberId()));
		memberCouponDTO.setDisabled(1);
		RequestObject requestObject = new RequestObject(headObject, memberCouponDTO);
		ResultObject resultObject = (ResultObject) memberService.doService(requestObject);
		return resultObject;
	}

}
