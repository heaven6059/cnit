/**
 * 文 件 名   :  ComboBoxController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-3 下午5:04:45
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.api.vo.CarBrandVO;
import com.cnit.yoyo.api.vo.CarFactoryIncludeCarDeptVO;
import com.cnit.yoyo.api.vo.CarTypeVO;
import com.cnit.yoyo.api.vo.CarYearVO;
import com.cnit.yoyo.api.vo.MemberCarGetdefVO;
import com.cnit.yoyo.api.vo.MemberCarQryVO;
import com.cnit.yoyo.base.controller.BaseController;
import com.cnit.yoyo.base.validation.ValidationResult;
import com.cnit.yoyo.base.validation.ValidationUtils;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.JsonDateValueProcessor;
import com.cnit.yoyo.util.JsonDecimalValueProcessor;
import com.cnit.yoyo.util.StringUtil;

/**
 * 车属性相关
 * @Author:yangyi  
 * @Date:2015年7月11日  
 * @Version:1.0.0
 */
@Controller("comboBoxController")
@RequestMapping("/comboBox")
public class ComboBoxController extends BaseController{
	@Autowired
	private RemoteService itemService;

	/**
	 * 查询汽车品牌
	 */
	@ResponseBody
	@RequestMapping(value = "/findCarBrand",method =RequestMethod.GET)
	public Object findCarBrand(String data,HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			CarBrandVO carBrand = (CarBrandVO) JSONObject.toBean(JSONObject.fromObject(data),CarBrandVO.class);
			headObject = CommonHeadUtil.geneHeadObject("brandService.findCarBrand");
			ValidationResult bindingResult = ValidationUtils.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			Map<String, Object> params=new HashMap<String,Object>();
       		params.put("identifier", carBrand.getIdentifier());
			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, params));
		} catch (Exception e) {
			log.error("数据处理异常{}",e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

	/**
	 * @Description:查询汽车厂商以及车系
	 * @param request
	 * @param brandId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findCarDept",method =RequestMethod.GET)
	public Object findCarDept(@Valid String data,HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		JSONObject resultObjectJson =null;
		try {
			CarFactoryIncludeCarDeptVO carFactoryIncludeCarDept = (CarFactoryIncludeCarDeptVO) JSONObject.toBean(JSONObject.fromObject(data),CarFactoryIncludeCarDeptVO.class);
			headObject = CommonHeadUtil.geneHeadObject("carFactoryService.findCarFactoryIncludeCarDept");
			ValidationResult bindingResult = ValidationUtils.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, carFactoryIncludeCarDept.getBrandId()));
			JsonConfig jsonConfig = new JsonConfig();  
	        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
	        jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonDecimalValueProcessor());
	        resultObjectJson = JSONObject.fromObject(resultObject,jsonConfig);
		} catch (Exception e) {
			log.error("数据处理异常{}",e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObjectJson;
	}

	/**
	 * @description <b>查询车型</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-5-27
	 * @param @param request
	 * @param @param deptId
	 * @param @return
	 * @return Object
	 */
	@ResponseBody
	@RequestMapping(value = "/findCar",method =RequestMethod.GET)
	public Object findCar(String data,HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			CarTypeVO carType = (CarTypeVO) JSONObject.toBean(JSONObject.fromObject(data),CarTypeVO.class);
			headObject = CommonHeadUtil.geneHeadObject("carService.findCarByDept");
			ValidationResult bindingResult = ValidationUtils.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject, carType.getDeptId()));
		} catch (Exception e) {
			log.error("数据处理异常{}",e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}

	@ResponseBody
	@RequestMapping(value = "/findCarYear",method =RequestMethod.GET)
	public Object findCarYear(String data,HttpServletRequest request) {
		HeadObject headObject = null;
		ResultObject resultObject = null;
		try {
			CarYearVO carYear= (CarYearVO) JSONObject.toBean(JSONObject.fromObject(data),CarYearVO.class);
			headObject = CommonHeadUtil.geneHeadObject("carYearService.findSelect");
			ValidationResult bindingResult = ValidationUtils.validateEntity(data);
			if (bindingResult.isHasErrors()) {
				return elementErrors(headObject, bindingResult);
			}
			resultObject = (ResultObject) itemService.doServiceByServer(new RequestObject(headObject,carYear.getDeptId()));
		} catch (Exception e) {
			log.error("数据处理异常{}",e);
			e.printStackTrace();
			return processExpction(headObject);
		}
		return resultObject;
	}
	
}
