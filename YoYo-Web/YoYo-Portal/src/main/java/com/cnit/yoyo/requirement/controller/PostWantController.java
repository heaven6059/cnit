package com.cnit.yoyo.requirement.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Area;
import com.cnit.yoyo.model.requirement.PostRequirement;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 门户 -->快速发布需求
 * @author cai
 *
 */
@Controller
@RequestMapping("/requirement")
public class PostWantController {
	
    public static final Logger logger = LoggerFactory.getLogger(PostWantController.class);
    
    @Autowired
    public RemoteService otherService;
    @Autowired
    public RemoteService itemService;
    
    @RequestMapping("/index")
    public String toPaintPage(HttpServletRequest request){
    	return "/pages/biz/requirement/postWant";
    }
    
    @RequestMapping("/queryType")
    @ResponseBody
    public Object queryType(HttpServletRequest request){
		logger.info("PostWantService.queryType>>>>>>>>>start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-01", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
        try {
			resultObject = otherService.doService(new RequestObject(headObject));
		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		logger.info("PostWantService.queryType>>>>>>>>>end");
        return resultObject.getContent();
    }
    
    @RequestMapping("/queryprovince")
    @ResponseBody
    public Object getProvince(HttpServletRequest request){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020104-01", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
    	Area area = new Area();
    	area.setAreaDeep(1);
    	area.setAreaParentId(0);
    	ResultObject resultObject = null;
    	try {
    		resultObject = itemService.doService(new RequestObject(headObject, area));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultObject.getContent();
    }
    
    @RequestMapping(value="/submit")
    @ResponseBody
    public Object submit(HttpServletRequest request, PostRequirement dto){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-02", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        dto.setCreateTime(new Date());
        try {
			resultObject = otherService.doService(new RequestObject(headObject, dto));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return resultObject;
    }
}
