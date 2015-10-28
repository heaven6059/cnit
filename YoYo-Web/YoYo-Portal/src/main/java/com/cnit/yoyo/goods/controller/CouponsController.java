package com.cnit.yoyo.goods.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.goods.dto.CompanyDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/coupons")
public class CouponsController {

	public static final Logger log = LoggerFactory.getLogger(CouponsController.class);
	@Autowired
	private RemoteService itemService;
	@Autowired
	private RemoteService memberService;

	@RequestMapping("/detail")
	public Object goodsMain(HttpServletRequest request, Integer cpnsId) throws Exception {
		ModelAndView mv = new ModelAndView();
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = itemService.doService(new RequestObject(headObject, cpnsId));
			CouponsDTO coupon=(CouponsDTO) resultObject.getContent();
			Integer companyId=coupon.getStoreId().intValue();
			selectCompanyInfo(request, companyId);
			findTopSales(request, companyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.addObject("coupons", resultObject.getContent());
		mv.setViewName("pages/biz/coupons/couponsDetail");
		return mv;
	}
	
	private void findTopSales(HttpServletRequest request,Integer companyId){
    	try{
			HeadObject headObject = null;
			headObject = CommonHeadUtil.geneHeadObject(request, "010201-18");
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("companyId", companyId);
			ResultObject resultObject = itemService.doService(new RequestObject(headObject,param));
		    request.setAttribute("topSales", resultObject.getContent());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
	}
	private void selectCompanyInfo(HttpServletRequest request,Integer compayId){
    	try{
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	    	JSONObject content = new JSONObject();
	    	content.put("companyId", compayId);
	    	ResultObject resultObject = memberService.doService(new RequestObject(headObject, content));
	    	request.setAttribute("company", JSONObject.toBean(((JSONObject)resultObject.getContent()),CompanyDTO .class));
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
