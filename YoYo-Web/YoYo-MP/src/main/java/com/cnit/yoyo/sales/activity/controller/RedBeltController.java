package com.cnit.yoyo.sales.activity.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.Redbelt;
import com.cnit.yoyo.model.order.RedbeltDetail;
import com.cnit.yoyo.model.order.RedbeltExample;
import com.cnit.yoyo.model.order.RedbeltVo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.DateUtils;
import com.cnit.yoyo.util.StringUtil;

/**
 * 红包
 */
@Controller
@RequestMapping("/redBelt")
public class RedBeltController {

	@Autowired
	private RemoteService salesService;

	@Autowired
	private RemoteService itemService;

	@RequestMapping("/toRedBelt")
	public String toScoreBuy(HttpServletRequest request) {
		if ("edit".equals(request.getParameter("op"))) {
			ResultObject resultObject = null;
			try {
				HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060103-02");
				Long id = Long.parseLong(request.getParameter("id"));
				resultObject = salesService.doService(new RequestObject(headObject, id));
				Map<String, Object> resultmap = ((Map<String, Object>) resultObject.getContent());
				Redbelt redbelt=(com.cnit.yoyo.model.order.Redbelt) resultmap.get("redbelt");
				RedbeltVo redbeltVo=new RedbeltVo();
				redbeltVo.setRedbeltId(redbelt.getRedbeltId());
				redbeltVo.setTarget(redbelt.getTarget().toString());
				redbeltVo.setNums(redbelt.getNums());
				redbeltVo.setTotalAmount(redbelt.getTotalAmount());
				redbeltVo.setRule(redbelt.getRule());
				redbeltVo.setCategorys(redbelt.getCategorys());
				redbeltVo.setStartTime(DateUtils.dateToString(redbelt.getStartTime(),DateUtils.NORMAL_DATE_PATTERN));
				redbeltVo.setEndTime(DateUtils.dateToString(redbelt.getEndTime(),DateUtils.NORMAL_DATE_PATTERN));
				redbeltVo.setMemberLvIds(redbelt.getMemberLvIds());
				redbeltVo.setStatus(redbelt.getStatus().intValue());
				redbeltVo.setRemark(redbelt.getRemark());
				request.setAttribute("redbelt",redbeltVo);
				request.setAttribute("categorys", resultmap.get("categorys"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "pages/biz/sales/redBeltEdit";
	}

	@RequestMapping("/tolist")
	public String toList(HttpServletRequest request) {
		return "pages/biz/sales/redBeltList";
	}
	
	@RequestMapping("/toDetailList")
	public String toDetailList(HttpServletRequest request) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060103-02");
			Long id = Long.parseLong(request.getParameter("id"));
			resultObject = salesService.doService(new RequestObject(headObject, id));
			Map<String, Object> resultmap = ((Map<String, Object>) resultObject.getContent());
			Redbelt redbelt=(com.cnit.yoyo.model.order.Redbelt) resultmap.get("redbelt");
			RedbeltVo redbeltVo=new RedbeltVo();
			redbeltVo.setRedbeltId(redbelt.getRedbeltId());
			redbeltVo.setTarget(redbelt.getTarget().toString());
			redbeltVo.setNums(redbelt.getNums());
			redbeltVo.setTotalAmount(redbelt.getTotalAmount());
			redbeltVo.setRule(redbelt.getRule());
			redbeltVo.setCategorys(redbelt.getCategorys());
			redbeltVo.setStartTime(DateUtils.dateToString(redbelt.getStartTime(),DateUtils.NORMAL_DATE_PATTERN));
			redbeltVo.setEndTime(DateUtils.dateToString(redbelt.getEndTime(),DateUtils.NORMAL_DATE_PATTERN));
			redbeltVo.setMemberLvIds(redbelt.getMemberLvIds());
			redbeltVo.setStatus(redbelt.getStatus().intValue());
			redbeltVo.setRemark(redbelt.getRemark());
			request.setAttribute("redbelt",redbeltVo);
			request.setAttribute("categorys", resultmap.get("categorys"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/sales/redBeltDetailList";
	}
	@RequestMapping("/loadDetailList")
	public Object loadDetailList(HttpServletRequest request,RedbeltDetail redbeltDetail) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060103-05");
		ResultObject resultObject = null;
		try {
			resultObject = salesService.doService(new RequestObject(headObject, redbeltDetail));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	

	@ResponseBody
	@RequestMapping("/loadRedBeltList")
	public Object loadRedBeltList(HttpServletRequest request, RedbeltExample redbeltExample) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060103-01");
		ResultObject resultObject = null;
		try {
			resultObject = salesService.doService(new RequestObject(headObject, redbeltExample));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * @description 删除活动
	 */
	@ResponseBody
	@RequestMapping("/deleteRedBelt")
	public Object deleteRedBelt(HttpServletRequest request) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060103-04");
		ResultObject resultObject = null;
		try {
			resultObject = salesService.doService(new RequestObject(headObject, Long.parseLong(request.getParameter("id"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	@RequestMapping("/saveRedBelt")
	public String saveRedBelt(HttpServletRequest request, RedbeltVo redbelt) {
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060103-03");
			if(redbelt.getTarget().equals("1,2")){
				redbelt.setTarget("0");
			}
			salesService.doService(new RequestObject(headObject, redbelt));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/redBelt/tolist";
	}

	@RequestMapping("/findAllMainCategory")
	@ResponseBody
	public Object findAllMainCategory(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010401-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		JSONObject data = new JSONObject();
		data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		return itemService.doService(new RequestObject(headObject, data));
	}
}
