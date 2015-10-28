package com.cnit.yoyo.ad.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping(value="ad")
public class AdController {
    
    private static final Logger logger = LoggerFactory.getLogger(AdController.class);
    
    @Autowired
    private RemoteService otherService;
    
    @RequestMapping(value="getAdByName",method=RequestMethod.GET)
    @ResponseBody
    public ResultObject getAdByName(HttpServletRequest request,String name){
        logger.info("start[AdController.getAdByName]");
        HeadObject head = CommonHeadUtil.geneHeadObject(request,"990701-01",GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_FRONT);
        ResultObject result = null;
        Object content = null;
        try {
            if(StringUtil.isEmpty(name)){
                throw new IllegalArgumentException("请求参数name不能为空");
            }else{
                result = otherService.doService(new RequestObject(head, name));
                head = result.getHead();
                content = result.getContent();
            }
        } catch (Exception e) {
            head.setRetCode(ErrorCode.FAILURE);
            head.setRetMsg("获取广告数据失败");
            logger.error("获取广告数据失败", e);
        }
        logger.info("end[AdController.getAdByName]");
        return new ResultObject(head, content);
    }
}
    