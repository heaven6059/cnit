/**
 * 文 件 名   :  OauthController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-22 下午2:57:27
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.EmailObject;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.member.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.EmailSenderUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.VerifyCodeUtils;
import com.qq.connect.utils.RandomStatusGenerator;

/**
 * @description 访问控制
 * @detail 用户注册登录控制
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {

    @Autowired
    private SignService signService;

    /**
     * @description <一句话方法简述>
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-2
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAuthorizeURL")
    @ResponseBody
    public ResultObject getAuthorizeURL(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-03", GlobalStatic.SYSTEM_CODE_THIRD,GlobalStatic.SYSTEM_CODE_FRONT);
        String thirdType = request.getParameter("thirdType");
        String callback = request.getParameter("callback");
        String state = RandomStatusGenerator.getUniqueState();

        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("thirdPart", thirdType);
        dataMap.put("callback", callback);
        dataMap.put("state", state);

        return signService.doCommon(headObject, dataMap);
    }

    /**
     * @description 发送短信验证码
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-12
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendSmsCode")
    @ResponseBody
    public Object sendSmsCode(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-02", GlobalStatic.SYSTEM_CODE_THIRD,
                GlobalStatic.SYSTEM_CODE_FRONT);
        String mobile = request.getParameter("mobile");
        String needCheckMember = request.getParameter("needCheck");
        if (!StringUtil.isEmpty(mobile)) {
            ResultObject resultObject = null;
            if (needCheckMember == "need") {
                headObject.setServiceCode("1000020102-08");
                resultObject = signService.doCommon(headObject, mobile);
                if (!ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getHead().getRetCode())) {
                    return resultObject;
                }
            }
            headObject.setServiceCode("1000020102-02");
            resultObject = signService.sendSms(headObject, mobile);
            request.getSession().setAttribute(GlobalStatic.SMS_CODE, resultObject.getContent());
            return resultObject;
        } else {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("手机号码没有正确输入！");
            return new ResultObject(headObject);
        }
    }

    /**
     * @description 验证短信验证码
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-12
     * @param request
     * @param smsCode
     * @return
     * @throws Exception
     */
    @RequestMapping("/validateSmsCode")
    @ResponseBody
    public Object validateSmsCode(HttpServletRequest request, String smsCode) throws Exception {
        HeadObject headObject = new HeadObject();
        String smsCodeSes = (String) request.getSession().getAttribute(GlobalStatic.SMS_CODE);
        if (StringUtil.isEmpty(smsCodeSes)) {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("请输入手机号码后点击免费获取验证码！");
        } else if (smsCodeSes.equalsIgnoreCase(smsCode)) {
            headObject.setRetCode(ErrorCode.SUCCESS);
            headObject.setRetMsg("短信验证码验证通过！");
        } else {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("短信验证码输入不正确！");
        }
        return headObject;
    }

    /**
     * @description 验证图形验证码
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-2-12
     * @param request
     * @param smsCode
     * @return
     * @throws Exception
     */
    @RequestMapping("/validateImgCode")
    @ResponseBody
    public Object validateImgCode(HttpServletRequest request, String imgCode) {
        HeadObject headObject = new HeadObject();
        String imgCodeSes = (String) request.getSession().getAttribute(GlobalStatic.VALIDATE_CODE);
        if (StringUtil.isEmpty(imgCodeSes)) {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("请点击验证码图片重新获取验证码！");
        } else if (imgCodeSes.equalsIgnoreCase(imgCode)) {
            headObject.setRetCode(ErrorCode.SUCCESS);
            headObject.setRetMsg("验证码验证通过！");
        } else {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("验证码输入不正确！");
        }
        return headObject;
    }

    /**
     * @description 发送邮箱验证码
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-17
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendEmailCode")
    @ResponseBody
    public Object sendEmailCode(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-02", GlobalStatic.SYSTEM_CODE_THIRD,
                GlobalStatic.SYSTEM_CODE_FRONT);
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        try {
            EmailObject emailObject = new EmailObject();
            emailObject.setContent("验证码："+verifyCode);
            emailObject.setSubject("验证码");
            emailObject.setSender("379454125@qq.com");
            List<String> recipients = new ArrayList<String>();
            recipients.add(request.getParameter("email"));
            emailObject.setRecipients(recipients);
            EmailSenderUtil.sendEmail(emailObject);
            request.getSession().setAttribute(GlobalStatic.EMAIL_CODE, verifyCode);
            headObject.setRetCode(ErrorCode.SUCCESS);
        } catch (Exception e) {
            headObject.setRetCode(ErrorCode.FAILURE);
            headObject.setRetMsg("邮件发送异常，请稍后再试！");
        }
        return headObject;
    }
    
    /**
     * @description 验证邮箱验证码
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-17
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/validateEmailCode")
    @ResponseBody
    public Object validateEmailCode(HttpServletRequest request,String emailCode) throws Exception {
        HeadObject headObject = new HeadObject();
        String emailCodeSes = (String) request.getSession().getAttribute(GlobalStatic.EMAIL_CODE);
        if (StringUtil.isEmpty(emailCodeSes)) {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("请点击验证码图片重新获取验证码！");
        } else if (emailCodeSes.equalsIgnoreCase(emailCode)) {
            headObject.setRetCode(ErrorCode.SUCCESS);
            headObject.setRetMsg("验证码验证通过！");
        } else {
            headObject.setRetCode(ErrorCode.PDE0001);
            headObject.setRetMsg("验证码输入不正确！");
        }
        return headObject;
    }
}
