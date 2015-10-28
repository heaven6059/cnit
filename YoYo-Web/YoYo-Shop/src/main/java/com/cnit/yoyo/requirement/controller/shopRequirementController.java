package com.cnit.yoyo.requirement.controller;

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
import com.cnit.yoyo.model.requirement.RequirementContent;
import com.cnit.yoyo.model.requirement.dto.PostRequirementDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

@Controller
@RequestMapping("/requirement")
public class shopRequirementController {

    public static final Logger logger = LoggerFactory.getLogger(shopRequirementController.class);

    @Autowired
    private RemoteService otherService;

    @RequestMapping("/myRequirement")
    public String toMyContent() {
        
        return "/pages/requirement/myRequiements";
    }

    /**
     * 获得我的需求列表
     * 
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping("/getList")
    @ResponseBody
    public Object getMyContent(HttpServletRequest request,
            PostRequirementDTO dto) {
        logger.info("###########YOYOMEM-->myRequirementController.getMyContent----->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990801-07", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        Integer accountId = (Integer) CommonUtil.getSession(request).getAttribute("accountId");
        dto.setAccountId(accountId);
        ResultObject resultObject = null;
        try {
            resultObject = otherService.doService(new RequestObject(headObject,
                    dto));
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMEM-->myRequirementController.getMyContent----->start");
        return resultObject;
    }

    /**
     * 回复需求
     * 
     * @param request
     * @param id
     * @param description
     * @return
     */
    @RequestMapping("/response")
    @ResponseBody
    public Object response(HttpServletRequest request,RequirementContent content) {
        logger.info("###########YOYOMP-->RequirementController.getContentList----->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"990801-05", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        Integer accountId = (Integer) request.getSession().getAttribute("accountId");
        Integer accountType = (Integer) request.getSession().getAttribute("accountType");
        try {
            if (null != accountId) {
                content.setAccountId(accountId);
            }
            if (null != accountType) {
                content.setAccountType(accountType + "");
            }
            resultObject = otherService.doService(new RequestObject(headObject,
                    content));
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMP-->RequirementController.getContentList----->end");
        return resultObject;
    }
}
