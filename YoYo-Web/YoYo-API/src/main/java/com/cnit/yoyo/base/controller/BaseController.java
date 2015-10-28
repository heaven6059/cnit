package com.cnit.yoyo.base.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;

/**
 * 接口控制层基类
 * @Author:yangyi  
 * @Date:2015年7月8日  
 * @Version:1.0.0
 */
public class BaseController {
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 注册类型转换，比如String值转换为Date,在从前端传值到Controller时,spring
     * mvc需要知道如何将字符串值转换为相应的java类型
     * 
     * @see http
     *      ://docs.spring.io/spring/docs/current/spring-framework-reference/
     *      html/mvc.html#mvc-ann-webdatabinder
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    
    /**
     * @Description:异常报错处理
     * @param headObject
     * @return
     */
    public static ResultObject processExpction(HeadObject headObject) {
    	if(headObject==null){
    		headObject = new HeadObject();
    	}
    	
		headObject.setRetCode(ErrorCode.FAILURE);
		headObject.setRetMsg("数据处理异常");
		return new ResultObject(headObject);
	}
    
    /**
     * @Description:异常报错处理
     * @param retCode
     * @param retMsg
     * @return
     */
    public static ResultObject processExpction(String retCode,String retMsg) {
    	HeadObject headObject = new HeadObject();
		headObject.setRetCode(retCode);
		headObject.setRetMsg(retMsg);
		return new ResultObject(headObject);
	}
    
    /**
     * @Description:异常报错处理
     * @param retCode
     * @param retMsg
     * @return
     */
    public static ResultObject processExpction(String retMsg) {
    	HeadObject headObject = new HeadObject();
		headObject.setRetCode(ErrorCode.FAILURE);
		headObject.setRetMsg(retMsg);
		return new ResultObject(headObject);
	}
    
    /**
     * @Description:字段校验报错处理
     * @param headObject
     * @return
     */
    public ResultObject processElementsExpction(HeadObject headObject,JSONObject jsonObject) {
		headObject.setRetCode(ErrorCode.VALIDATE_ELEMENT_ERROR);
		headObject.setRetMsg("字段校验异常");
		return new ResultObject(headObject,jsonObject);
	}
    
    /**
     * @Description:处理字段报错信息
     * @param headObject
     * @param bindingResult
     * @return
     */
    public ResultObject elementErrors(HeadObject headObject,ValidationResult bindingResult){
    	Map<String, String>  errMap=bindingResult.getErrorMsg();
    	return processElementsExpction(headObject,JSONObject.fromObject(errMap));
    }

    /**
     * 捕获认证异常抛出
     * 
     * @return 响应信息
     */
//    @ExceptionHandler(value = ImageAuthRuntimeException.class)
//    public Object handleImageAuthRuntimeException(HttpServletRequest request, HttpServletResponse response,
//            ImageAuthRuntimeException ex) {
//        BaseResponse baseResponse = new BaseResponse();
//        baseResponse.setAck(EnumAck.Failure);
//        baseResponse.addErrors(ex.getErrCode(), ex.getErrMsg());
//        String json = JSONObject.toJSONString(baseResponse);
//        PrintWriter writer = null;
//        try {
//            writer = response.getWriter();
//            response.addHeader("content-type", MediaType.APPLICATION_JSON_VALUE);
//            writer.write(json);
//            writer.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * 获取httpsession
     * 
     * @return httpsession null or object
     */
    protected HttpSession getHttpSession() {
        HttpSession session = null;
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        session = attrs.getRequest().getSession();
        return session;
    }
}
