package com.cnit.yoyo.requirement.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
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
import com.cnit.yoyo.model.requirement.PostRequirement;
import com.cnit.yoyo.model.requirement.RequirementContent;
import com.cnit.yoyo.model.requirement.dto.PostRequirementDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/requirement")
public class RequirementController {
	
	public static final Logger logger = LoggerFactory.getLogger(RequirementController.class);
	
    @Autowired
    public RemoteService otherService;
    
    @RequestMapping("/index")
    public String home() {
        return "pages/biz/requirement/requirementManage";
    }
    
    /**
     * 平台查询需求列表
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object getList(HttpServletRequest request, PostRequirementDTO dto){
    	logger.info("###########YOYOMP-->RequirementController.getList----->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-03", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
		if(dto.getEndDate() !=null && dto.getStartDate() !=null && dto.getEndDate().before(dto.getStartDate())){
			Date temp = dto.getEndDate();
			dto.setEndDate(dto.getStartDate());
			dto.setStartDate(temp);
		}
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject,dto));
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMP-->RequirementController.getList----->end");
        return resultObject;
    }
    
    @RequestMapping("/invalid")
    @ResponseBody
    public Object invalid(HttpServletRequest request, PostRequirement postRequirement){
    	logger.info("###########YOYOMP-->RequirementController.invalid----->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-06", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject,postRequirement));
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMP-->RequirementController.invalid----->end");
        return resultObject;
    }
    
    @RequestMapping("/contentList")
    public String toContentList(HttpServletRequest request, Long requirementId){
    	request.setAttribute("requirementId", requirementId);
        return "pages/biz/requirement/contentList";
    }
    
    /**
     * 评论列表
     * @param request
     * @return
     */
    @RequestMapping("/getContentList")
    @ResponseBody
    public Object getContentList(HttpServletRequest request, Long requirementId){
    	logger.info("###########YOYOMP-->RequirementController.getContentList----->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-04", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
            resultObject = otherService.doService(new RequestObject(headObject,requirementId));
//            List<RequirementContent> list = JSON.parseObject(resultObject.getContent().toString(), new TypeReference<List<RequirementContent>>() {});
//            request.setAttribute("contentList", list);
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMP-->RequirementController.getContentList----->end");
        return resultObject;
    } 
    
    /**
     * 回复需求
     * @param request
     * @param id
     * @param description
     * @return
     */
    @RequestMapping("/response")
    @ResponseBody
    public Object response(HttpServletRequest request, RequirementContent content){
    	logger.info("###########YOYOMP-->RequirementController.getContentList----->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-05", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	Integer accountId = (Integer)request.getSession().getAttribute("accountId");
    	String accountType = (String)request.getSession().getAttribute("accountType");
    	try 
    	{
    		if(null != accountId){
        		content.setAccountId(accountId);
    		}
    		if(StringUtils.isNotBlank(accountType)){
        		content.setAccountType(accountType);
    		}
    		resultObject = otherService.doService(new RequestObject(headObject,content));
    	} 
    	catch (Exception e) 
    	{
    		logger.error(e.getMessage());
    		e.printStackTrace();
    	}
    	logger.info("###########YOYOMP-->RequirementController.getContentList----->end");
    	return resultObject;
    } 
}
