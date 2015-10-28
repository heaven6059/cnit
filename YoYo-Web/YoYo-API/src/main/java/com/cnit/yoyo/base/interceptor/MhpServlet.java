package com.cnit.yoyo.base.interceptor;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnit.yoyo.service.IMhpService;
import com.cnit.yoyo.util.ApplicationContextUtil;
import com.cnit.yoyo.util.Base64;
import com.cnit.yoyo.util.aes.BackAES;

/**
 * 手机api入口
 * @Author:yangyi  
 * @Date:2015年7月17日  
 * @Version:1.0.0
 */
public class MhpServlet extends HttpServlet {
	/**
	 * 
	 */
	private IMhpService mhpService =ApplicationContextUtil.getBean("mhpService");
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("data : " + data);
		logger.info("data : " + data);
		String skey="123456789";
		try {
			if(StringUtils.isBlank(data)){
				String resultJson = "入参为空！";
				response.setCharacterEncoding("UTF-8");
				response.getOutputStream().write(resultJson.getBytes());
			}else {
				data = BackAES.decrypt(data,skey, 1);
				logger.info("方法-解密后>>："+data);
				if(data == null){
					String resultJson = "解密失败！";
					response.setCharacterEncoding("UTF-8");
					response.getOutputStream().write(resultJson.getBytes());
				}else{
					String resultJson = mhpService.mhpServiceTest(data,request);
					response.setCharacterEncoding("UTF-8");
					response.getOutputStream().write(resultJson.getBytes());
				}
			}
		} catch (Exception e) {
			logger.error("系统异常{}",e.toString());
			String resultJson = "系统异常！";
			response.setCharacterEncoding("UTF-8");
			response.getOutputStream().write(resultJson.getBytes());
			e.printStackTrace();
		}
	}
}
