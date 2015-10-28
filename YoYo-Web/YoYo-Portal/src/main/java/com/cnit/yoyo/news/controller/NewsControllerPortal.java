package com.cnit.yoyo.news.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.news.dto.NewsQueryDto;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.Configuration;

@Controller
@RequestMapping("/news")
public class NewsControllerPortal {
	
	public static final Logger logger = LoggerFactory.getLogger(NewsControllerPortal.class);
	
	@Autowired
    public RemoteService otherService;
	
	@RequestMapping("/indexNews")
	@ResponseBody
	public Object index(HttpServletRequest request){
		logger.info("NewsControllerPortal.index------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        String num = Configuration.getInstance().getConfigValue("INDEX_NEWS_NUM");
        Integer number = StringUtils.isNotEmpty(num) ? Integer.valueOf(num) : 8; 
        try {
			resultObject = otherService.doService(new RequestObject(headObject,number));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("NewsControllerPortal.index------>end");
		System.out.println(resultObject.toString());
		return resultObject.getContent();
	}
	
	@RequestMapping("/detail")
	public Object detail(HttpServletRequest request, Integer newsId){
		logger.info("NewsControllerPortal.detail------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
			resultObject = otherService.doService(new RequestObject(headObject,newsId));
			request.setAttribute("news", resultObject.getContent());
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("NewsControllerPortal.index------>end");
		System.out.println(resultObject.toString());
		return "pages/biz/news/detail";
	}
	
	@RequestMapping("more")
	public Object more(){
		return "pages/biz/news/more";
	}
	
	/**
	 * @Title:  getMore  
	 * @Description:  首页更多列表
	 * @author <a href="zjcai@chinacnit.com">蔡志杰</a> 
	 * @date 2015-8-20 上午10:16:41  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param dto
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/getMore")
	@ResponseBody
	public Object getMore(HttpServletRequest request, NewsQueryDto dto){
		logger.info("NewsControllerPortal.getMore------>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "991001-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
			resultObject = otherService.doService(new RequestObject(headObject,dto));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("NewsControllerPortal.getMore------>end");
		JSON.parse((String)resultObject.getContent());
		return resultObject.getContent();
	}
}
