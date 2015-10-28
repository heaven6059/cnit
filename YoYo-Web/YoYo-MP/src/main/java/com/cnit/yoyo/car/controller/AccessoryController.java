/**
 * 文 件 名   :  AccessoryController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年3月30日 下午4:23:14
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.car.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 汽车配件控制器
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/accessory")
public class AccessoryController {
	public static final Logger log = LoggerFactory.getLogger(AccessoryController.class);
	@Autowired
	public RemoteService itemService;

	/**
	 * 
	 * @description 根据参数配置类别id查找配件列表
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-6
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/catalogDataList")
	@ResponseBody
	public Object getAccessoryCatalogDataList(HttpServletRequest request, Integer catalogId) throws Exception {
		log.info("start[AccessoryController.getAccessoryCatalogDataList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010501-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("catalogId",catalogId);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, map));
		log.info("end[AccessoryController.getAccessoryCatalogDataList]");
		return resultObject;
	}

	/**
	 * 
	 * @description 根据配置id查找配置类型信息
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-14
	 * @param request
	 * @param accId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findAccessoryTypeInfo")
	@ResponseBody
	public Object findAccessoryTypeInfo(HttpServletRequest request, Integer accId) throws Exception {
		log.info("start[AccessoryController.findAccessoryTypeInfo]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010501-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, accId));
		log.info("end[AccessoryController.findAccessoryTypeInfo]");
		return resultObject;
	}

	/**
	 * @description 查询参数类型下所有的参数项
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param catalogId
	 * @return
	 * @throws Exception
	 */
	public Object getAccessoryParams(HttpServletRequest request, Integer catalogId) throws Exception {
		log.info("start[AccessoryController.getAccessoryParams]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, catalogId));
		log.info("end[AccessoryController.getAccessoryParams]");
		return resultObject;
	}

	/**
	 * @description 根据类型id获取配件参数类型详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param catalogId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/accCatelogDetail")
	@ResponseBody
	public Object getAccessoryCatalogDetail(HttpServletRequest request, Integer catalogId) throws Exception {
		log.info("start[AccessoryController.getAccessoryCatalogDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, catalogId));
		log.info("end[AccessoryController.getAccessoryCatalogDetail]");
		return resultObject;
	}

	/**
	 * @description 分页查询配件参数类型
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param querydto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/catalogList")
	@ResponseBody
	public Object getAccessoryCatalogList(HttpServletRequest request, Integer page, Integer rows, String sort, String order) throws Exception {
		log.info("start[AccessoryController.getAccessoryCatalogList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		QueryParamObject paramObject = null;
		String params = request.getParameter("params");
		if (!StringUtil.isEmpty(params)) {
			paramObject = (QueryParamObject) JSONObject.toBean(JSONObject.fromObject(params), QueryParamObject.class);
		} else {
			paramObject = new QueryParamObject();
		}
		paramObject.setPage(page);
		paramObject.setRows(rows);
		if (!StringUtils.isNotBlank(sort)) {
			sort = "catalogId";
			order = "desc";
		}
		paramObject.setSort(sort);
		paramObject.setOrder(order);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		log.info("end[AccessoryController.getAccessoryCatalogList]");
		return resultObject;
	}

	/**
	 * @description 保存配件参数类型信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAccCatalog")
	@ResponseBody
	public Object saveAccessoryCatalog(HttpServletRequest request) throws Exception {
		log.info("start[AccessoryController.saveAccessoryCatalog]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (value == null)
					return true;
				if (value instanceof String && "".equalsIgnoreCase((String) value))
					return true;
				return false;
			}
		});
		String temp = request.getParameter("catalog");
		if (!StringUtil.isEmpty(temp)) {
			JSONObject catJSON = JSONObject.fromObject(temp, jsonConfig);
			content.put("catalog", catJSON);
		}
		temp = request.getParameter("inserted");
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
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[AccessoryController.saveAccessoryCatalog]");
		return resultObject;
	}

	/**
	 * @description 删除配件参数类型
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param catalogId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAccCatalog")
	@ResponseBody
	public Object deleteAccessoryCatalog(HttpServletRequest request, Integer catalogId) throws Exception {
		log.info("start[AccessoryController.deleteAccessoryCatalog]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, catalogId));
		log.info("end[AccessoryController.deleteAccessoryCatalog]");
		return resultObject;
	}

	/**
	 * @description 删除配件参数
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param catalogId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAccParam")
	@ResponseBody
	public Object deleteAccessoryParam(HttpServletRequest request, Integer paramId, String dataType) throws Exception {
		log.info("start[AccessoryController.deleteAccessoryCatalog]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		content.put("paramId", paramId);
		content.put("dataType", dataType);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[AccessoryController.deleteAccessoryCatalog]");
		return resultObject;
	}

	/**
	 * @description 查询配件列表
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月30日
	 * @param request
	 * @return
	 */
	@RequestMapping("/accessoryList")
	@ResponseBody
	public Object getAccessoryList(HttpServletRequest request, Integer page, Integer rows, String sort, String order) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		QueryParamObject paramObject = null;
		String params = request.getParameter("params");
		if (!StringUtil.isEmpty(params)) {
			paramObject = (QueryParamObject) JSONObject.toBean(JSONObject.fromObject(params), QueryParamObject.class);
		} else {
			paramObject = new QueryParamObject();
		}
		if (!StringUtils.isNotBlank(sort)) {
			sort = "accId";
			order = "desc";
		}
		paramObject.setPage(page);
		paramObject.setRows(rows);
		paramObject.setSort(sort);
		paramObject.setOrder(order);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		return resultObject;
	}

	/**
	 * @description 保存配件信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveAccessory")
	@ResponseBody
	public Object saveAccessory(HttpServletRequest request) throws Exception {
		log.info("start[AccessoryController.saveAccessory]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (value == null)
					return true;
				if (value instanceof String && "".equalsIgnoreCase((String) value))
					return true;
				return false;
			}
		});
		String temp = request.getParameter("accessory");
		if (!StringUtil.isEmpty(temp)) {
			JSONObject accJSON = JSONObject.fromObject(temp, jsonConfig);
			content.put("accessory", accJSON);
		}
		temp = request.getParameter("params");
		if (!StringUtil.isEmpty(temp)) {
			JSONArray paramJSON = JSONArray.fromObject(temp, jsonConfig);
			content.put("params", paramJSON);
		}
		temp = request.getParameter("cars");
		if (!StringUtil.isEmpty(temp)) {
			JSONArray paramJSON = JSONArray.fromObject(temp, jsonConfig);
			content.put("cars", paramJSON);
		}
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[AccessoryController.saveAccessory]");
		return resultObject;
	}

	/**
	 * @description 根据id查询配件详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAccessoryDetail")
	@ResponseBody
	public Object getAccessoryDetail(HttpServletRequest request, Integer accId, Integer catalogId) throws Exception {
		log.info("start[AccessoryController.getAccessoryDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, accId));
		log.info("end[AccessoryController.getAccessoryDetail]");
		return resultObject;
	}

	/**
	 * @description 根据id查询配件参数值详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月31日
	 * @param request
	 * @param paramId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAccParams")
	@ResponseBody
	public Object getAccessoryValueDetail(HttpServletRequest request, Integer accId, Integer catalogId) throws Exception {
		log.info("start[AccessoryController.getAccessoryDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		if (catalogId != null) {
			content.put("catalogId", catalogId);
			if (accId != null) {
				content.put("accId", accId);
			}
			log.info("end[AccessoryController.getAccessoryDetail]");
			return itemService.doService(new RequestObject(headObject, content));
		} else {
			return new RequestObject(new HeadObject(ErrorCode.FAILURE, "未知配件参数类型！"));
		}
	}

	/**
	 * @description 根据配件id删除配件信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月2日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteAccessory")
	@ResponseBody
	public Object deleteAccessory(HttpServletRequest request, Integer accId) throws Exception {
		log.info("start[AccessoryController.deleteAccessory]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		if (accId != null) {
			log.info("end[AccessoryController.deleteAccessory]");
			return itemService.doService(new RequestObject(headObject, accId));
		} else {
			log.info("end[AccessoryController.deleteAccessory]");
			return new RequestObject(new HeadObject(ErrorCode.FAILURE, "未知配件编号！"));
		}
	}

	@RequestMapping("/accessoryIndex")
	public String accessoryIndex() {
		return "pages/biz/car/accessoryIndex";
	}

	@RequestMapping("/accessoryCataIndex")
	public String accessoryCataIndex() {
		return "pages/biz/car/accessoryCatalogIndex";
	}
	
	
	/**
	 * 
	 *@description 获取保养配件类型
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-16
	 *@param request
	 *@throws Exception
	 */
	@RequestMapping("/maintainAccessory")
    @ResponseBody
    public Object maintainAccessory(HttpServletRequest request) throws Exception {
        log.info("start[AccessoryController.getAccessoryCatalogList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
      
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data ));
        log.info("end[AccessoryController.getAccessoryCatalogList]");
        return resultObject;
    }
}
