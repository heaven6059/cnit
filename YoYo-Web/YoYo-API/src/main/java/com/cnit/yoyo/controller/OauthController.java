package com.cnit.yoyo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.api.vo.MemberCarSaveVO;
import com.cnit.yoyo.api.vo.SmsSendVO;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.base.validation.ValidationUtils;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.EmailObject;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Tclientlog;
import com.cnit.yoyo.model.member.Clerks;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.EmailSenderUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.VerifyCodeUtils;
import com.cnit.yoyo.util.email.EmailService;
import com.cnit.yoyo.util.email.TempalteEmailObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户注册登录控制
 * @Author:yangyi  
 * @Date:2015年7月9日  
 * @Version:1.0.0
 */
@Controller("oauthController")
@RequestMapping("/oauth")
public class OauthController extends BaseController{
	@Autowired
	private RemoteService  otherService;
	@Autowired
	private RemoteService  memberService ;
    @Autowired
    private EmailService emailService;
    @Autowired
	private RedisClientUtil redisService;
    
    @ResponseBody
    @RequestMapping(value = "/",method =RequestMethod.POST)
    private Object oindex(HttpServletRequest request){
    	return "test";
    }
    /**
     * @Description:发送短信接口
     * @param request
     * @param mobile
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/sendSmsCode",method =RequestMethod.POST)
    public Object sendSmsCode(String data,HttpServletRequest request) throws Exception {
    	SmsSendVO sendSms = (SmsSendVO) JSONObject.toBean(JSONObject.fromObject(data), SmsSendVO.class);
    	HeadObject headObject = CommonHeadUtil.geneHeadObject("smsService.sendSmsFormTaoPing");
    	ResultObject resultObject=new ResultObject(headObject);
        ValidationResult bindingResult = ValidationUtils.validateEntity(sendSms);
        if(bindingResult.isHasErrors()){
        	return elementErrors(headObject, bindingResult);
        }
        String needCheckMember = request.getParameter("needCheck");
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("mobile", sendSms.getMobile());
        dataMap.put("platform", sendSms.getFphoneostype());
        HeadObject retObject = (HeadObject) memberService.doServiceByServer(new RequestObject(headObject, dataMap));
        log.info("返回结果:"+headObject);
        if(retObject!= null && retObject.getRetCode() != null){
        	resultObject=new ResultObject(retObject);
        	String randomCode=RandomUtils.nextLong()+"";
        	redisService.set(randomCode, retObject.getRetCode());
//        	request.getSession().setAttribute(randomCode, resultObject.getHead().getRetCode());
            Map<String,String> map=new HashMap();
            map.put("smsId", randomCode);
            resultObject.setContent(map);
            resultObject.getHead().setRetCode(ErrorCode.SUCCESS);
        }else{
        	headObject.setRetCode(ErrorCode.FAILURE);
    		headObject.setRetMsg("短信发送异常");
    		return new ResultObject(headObject);
        }
        return resultObject;
    }

}
