/**
 * 文 件 名   :  MemberPasswordController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-16 上午11:23:04
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.system.login.controller;

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
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.EmailSenderUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 用户密码管理
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/password")
public class MemberPasswordController {
	@Autowired
	private RemoteService memberService;

	/**
	 * @description 修改密码
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-2-9
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changePwd")
	public Object changePwd(HttpServletRequest request) throws Exception {
		request.setAttribute("loginName", request.getParameter("loginName"));
		request.setAttribute("loginNameType", request.getParameter("loginNameType"));
		if (!StringUtil.isEmpty(request.getParameter("validateCode"))) {
			request.setAttribute("valiStatus", true);
		}
		return "pages/sign/changePwd";
	}

	/**
	 * @description 修改密码
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-2-9
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/doChangePwd")
	@ResponseBody
	public Object doChangePwd(HttpServletRequest request) throws Exception {
		String loginNameType = request.getParameter("loginNameType");
		String loginPassword = request.getParameter("loginPassword");
		String loginName = request.getParameter("loginName");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-05", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_FRONT);
		Map<String, String> dataMap = new HashMap<String, String>();
		dataMap.put("loginNameType", loginNameType);
		dataMap.put("loginName", loginName);
		dataMap.put("loginPassword", loginPassword);
		return memberService.doService(new RequestObject(headObject, dataMap));
	}

	/**
	 * @description 发送修改密码邮件
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-2-9
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/sendChangePwdEmail")
	@ResponseBody
	public Object sendChangePwdEmail(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-06", GlobalStatic.SYSTEM_CODE_DATA,
				GlobalStatic.SYSTEM_CODE_FRONT);
		String email = request.getParameter("email");
		ResultObject resultObject = null;
		if (!StringUtil.isEmpty(email)) {
			// 获得临时密钥
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("loginNameType", GlobalStatic.EMAIL);
			dataMap.put("loginName", email);
			resultObject = memberService.doService(new RequestObject(headObject, dataMap));
			if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getHead().getRetCode())) {
				dataMap = (Map<String, String>) resultObject.getContent();
				StringBuffer emailContent = new StringBuffer();
				emailContent.append("http://127.0.0.1:8080/CarShop/password/changePwd?");
				emailContent.append("loginNameType=1&loginName=" + email + "&validateCode=" + dataMap.get("tempKey"));
				emailContent.append("&account=" + dataMap.get("account"));
				EmailObject emailObject = new EmailObject();
				emailObject.setContent(emailContent.toString());
				emailObject.setSubject("测试邮件");
				emailObject.setSender("379454125@qq.com");
				List<String> recipients = new ArrayList<String>();
				recipients.add(email);
				emailObject.setRecipients(recipients);
				EmailSenderUtil.sendEmail(emailObject);
			}
			return resultObject;
		} else {
			headObject.setRetCode(ErrorCode.PDE0001);
			headObject.setRetMsg("输入数据不完整！");
			return new ResultObject(headObject);
		}
	}

	@RequestMapping("/getEmailStatus")
	public String getEmailStatus(HttpServletRequest request) throws Exception {
		String status = request.getParameter("status");
		String email = request.getParameter("email");
		if ("0".equalsIgnoreCase(status)) {
			request.setAttribute("tips", "邮件服务器异常，请稍后再试！");
		} else if ("1".equalsIgnoreCase(status)) {
			request.setAttribute("tips", "密码变更邮件已发送至" + email + "，请及时查收！");
		}
		return "pages/biz/memberMng/emailStatus";
	}
}
