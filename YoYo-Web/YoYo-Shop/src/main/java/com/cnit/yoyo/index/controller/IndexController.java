package com.cnit.yoyo.index.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.util.CommonUtil;

/**
 * 店铺首页
 * @author wanghb
 * @date 2015-05-26
 * @version 1.0.0
 */
@Controller
public class IndexController {

	public static final Logger log = LoggerFactory.getLogger(IndexController.class);
	@RequestMapping("/shop/index")
    public void shopIndex(HttpServletRequest request,HttpServletResponse response){
		try{
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			String portalPath = (String)request.getSession().getServletContext().getAttribute("portalPath");		
			response.getWriter().print("<script>window.location='"+portalPath+"/shop/index?companyId="+memberListDo.getCompanyId()+"'</script>");
		}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
}
