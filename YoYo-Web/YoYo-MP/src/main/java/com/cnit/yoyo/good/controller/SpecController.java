/**
 * 文 件 名   :  SpecController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午3:49:34
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.good.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.spec.SpecQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/spec")
public class SpecController {
	public static final Logger log = LoggerFactory.getLogger(SpecController.class);

	@Autowired
	private RemoteService itemService;

	@RequestMapping("/specIndex")
	public String index() {
		return "pages/biz/good/specIndex";
	}

	/**
	 * @description 查询商品规格列表
	 * @detail 可分页传参
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/specList")
	@ResponseBody
	public Object getSpecList(HttpServletRequest request, Integer page, Integer rows, String sort, String order) throws Exception {
		log.info("start[SpecController.getSpecList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		QueryParamObject paramObject = null;
		String params = request.getParameter("params");
		if (!StringUtils.isNotBlank(sort)) {
			sort = "specId";
			order = "desc";
		}
		
		if (!StringUtil.isEmpty(params)) {
			paramObject = (QueryParamObject) JSONObject.toBean(JSONObject.fromObject(params), QueryParamObject.class);
		} else {
			paramObject = new QueryParamObject();
		}
		paramObject.setPage(page);
		paramObject.setRows(rows);
		paramObject.setSort(sort);
		paramObject.setOrder(order);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		log.info("end[SpecController.getSpecList]");
		return resultObject;
	}

	/**
	 * @description 查询商品规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSpec")
	@ResponseBody
	public Object getSpecValueList(HttpServletRequest request, SpecQryDTO specQry) throws Exception {
		log.info("start[SpecController.getSpecValueList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(specQry)));
		log.info("end[SpecController.getSpecValueList]");
		return resultObject;
	}

	/**
	 * @description 删除规格及规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteSpec")
	@ResponseBody
	public Object deleteSpecAndValues(HttpServletRequest request) throws Exception {
		log.info("start[SpecController.deleteSpecAndValues]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		String specId = request.getParameter("specId");
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, specId));
		log.info("end[SpecController.deleteSpecAndValues]");
		return resultObject;
	}

	/**
	 * @description 获取规格及规格值详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	public Object getSpecAndValuesById(HttpServletRequest request, Integer specId) throws Exception {
		log.info("start[SpecController.getSpecAndValuesById]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, specId));
		log.info("end[SpecController.getSpecAndValuesById]");
		return resultObject;
	}

	/**
	 * @description 修改规格及规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateSpec")
	@ResponseBody
	public Object modifySpecAndValues(HttpServletRequest request) throws Exception {
		log.info("start[SpecController.deleteSpecAndValues]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		String spec = request.getParameter("spec");
		if (!StringUtil.isEmpty(spec)) {
			JSONObject specJSON = JSONObject.fromObject(spec);
			content.put("spec", specJSON);
		}
		String values = request.getParameter("values");
		if (!StringUtil.isEmpty(values)) {
			JSONArray valuesJSON = JSONArray.fromObject(values);
			content.put("values", valuesJSON);
		}
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[SpecController.deleteSpecAndValues]");
		return resultObject;
	}

	/**
	 * @description 新增规格及规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnit.com">李明</a>
	 * @version 1.0.0
	 * @data 2015-3-18
	 * @param data
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertSpec")
	@ResponseBody
	public Object insertSpecAndValues(HttpServletRequest request) throws Exception {
		log.info("start[SpecController.insertSpecAndValues]");
		JSONObject content = new JSONObject();
		String spec = request.getParameter("spec");
		if (!StringUtil.isEmpty(spec)) {
			JSONObject specJSON = JSONObject.fromObject(spec);
			content.put("spec", specJSON);
		}
		String temp = request.getParameter("inserted");
		if (!StringUtil.isEmpty(temp)) {
			JSONArray valuesJSON = JSONArray.fromObject(temp);
			content.put("inserted", valuesJSON);
		}
		temp = request.getParameter("updated");
		if (!StringUtil.isEmpty(temp)) {
			JSONArray valuesJSON = JSONArray.fromObject(temp);
			content.put("updated", valuesJSON);
		}
		temp = request.getParameter("deleted");
		if (!StringUtil.isEmpty(temp)) {
			JSONArray valuesJSON = JSONArray.fromObject(temp);
			content.put("deleted", valuesJSON);
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[SpecController.insertSpecAndValues]");
		return resultObject;
	}

	/**
	 * @description 根据分类ID查询规格及规格值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月14日
	 * @param request
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/specAndValuesByCatId")
	@ResponseBody
	public Object getSpecAndValuesByCatId(HttpServletRequest request, Integer catId, Long companyId) throws Exception {
		log.info("start[SpecController.getSpecAndValuesByCatId]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("catId", catId);
		data.put("companyId", companyId);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
		log.info("end[SpecController.getSpecAndValuesByCatId]");
		return resultObject;
	}
}
