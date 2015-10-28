/**
 * 
 */
package com.cnit.yoyo.good.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @author Administrator
 */
@Controller
@RequestMapping("/cate")
public class CategoryController {
	public static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private RemoteService itemService;

	/**
	 * @description 映射至商品分类管理首页
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月20日
	 * @return
	 */
	@RequestMapping("/cateIndex")
	public String cateIndex() {
		log.info("start[CategoryController.cateIndex]");
		log.info("end[CategoryController.cateIndex]");
		return "pages/biz/good/cateIndex";
	}

	/**
	 * @description 映射至商品虚拟分类管理首页
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月20日
	 * @return
	 */
	@RequestMapping("/virtualCateIndex")
	public String virtualCateIndex() {
		log.info("start[CategoryController.virtualCateIndex]");
		log.info("end[CategoryController.virtualCateIndex]");
		return "pages/biz/good/virtualCateIndex";
	}

	/**
	 * @description 删除虚拟分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteVirtualCate")
	@ResponseBody
	private Object deleteVirtualCate(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[CategoryController.deleteVirtualCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, catId));
		log.info("end[CategoryController.deleteVirtualCate]");
		return resultObject;
	}

	/**
	 * @description 获取虚拟分类详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getVirtualCateDetail")
	@ResponseBody
	private Object getVirtualCateDetail(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[CategoryController.getVirtualCateDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, catId));
		log.info("end[CategoryController.getVirtualCateDetail]");
		return resultObject;
	}

	/**
	 * @description 新增修改虚拟分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveVirtualCate")
	@ResponseBody
	private Object saveOrUpdateVirtualCate(HttpServletRequest request) throws Exception {
		log.info("start[CategoryController.saveOrUpdateVirtualCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
		String temp = request.getParameter("virtCate");
		ResultObject resultObject = null;
		if (!StringUtil.isEmpty(temp)) {
			JSONObject catJSON = JSONObject.fromObject(temp, jsonConfig);
			resultObject = itemService.doService(new RequestObject(headObject, catJSON));
			log.info("end[CategoryController.saveOrUpdateVirtualCate]");
		}
		return resultObject;
	}

	/**
	 * @description 获取虚拟分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月26日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getVirtualCateTree")
	@ResponseBody
	private Object getVirtualCateTree(HttpServletRequest request) throws Exception {
		log.info("start[CategoryController.getVirtualCateTree]");
		String parentCatId = request.getParameter("parentCatId");
		JSONObject params = new JSONObject();
		if (StringUtil.isEmpty(parentCatId)) {
			params.put("parentCatId", 0);
		} else {
			params.put("parentCatId", Integer.parseInt(parentCatId));
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
		log.info("end[CategoryController.getVirtualCateTree]");
		return resultObject.getContent();
	}

	/**
	 * @description 删除分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月24日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteCate")
	@ResponseBody
	private Object deleteCate(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[CategoryController.deleteCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, catId));
		log.info("end[CategoryController.deleteCate]");
		return resultObject;
	}

	/**
	 * @description 查询分类详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月24日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDetail")
	@ResponseBody
	private Object getDetail(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[CategoryController.getDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("catId", catId);
		//map.put("carId", 0);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, map));
		log.info("end[CategoryController.getDetail]");
		return resultObject;
	}

	/**
	 * @description 获取商品分类节点数据
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月21日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cateTree")
	@ResponseBody
	private Object getCateTree(HttpServletRequest request) throws Exception {
		log.info("start[CategoryController.getCateTree]");
		String parentCatId = request.getParameter("parentCatId");
		JSONObject params = new JSONObject();
		params.put("parentCatId", StringUtil.isEmpty(parentCatId) ? 0 : Integer.parseInt(parentCatId));
		String identifier = request.getParameter("identifier");
		if (!StringUtil.isEmpty(identifier)) {
			params.put("identifier", identifier);
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
		log.info("end[CategoryController.getCateTree]");
		return resultObject.getContent();
	}

	/**
	 * 
	 * @description 获取指定分类的数据，通过identifier
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-19
	 * @param request
	 * @param identifier
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/findCategoryTree")
	public Object findMaintainCategoryTree(HttpServletRequest request, @RequestParam(value = "identifier", required = true) String identifier) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-11");
		try {
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, identifier));
			return resultObject.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description 新增商品分类
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年3月23日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/insertCate")
	@ResponseBody
	private Object insertCate(HttpServletRequest request) throws Exception {
		log.info("start[CategoryController.insertCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
		String temp = request.getParameter("cate");
		if (!StringUtil.isEmpty(temp)) {
			JSONObject catJSON = JSONObject.fromObject(temp, jsonConfig);
			content.put("cate", catJSON);
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
		temp = request.getParameter("specs");
		if (!StringUtil.isEmpty(temp)) {
			JSONArray specJSON = JSONArray.fromObject(temp, jsonConfig);
			content.put("specs", specJSON);
		}
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[CategoryController.insertCate]");
		return resultObject;
	}

	/**
	 * 
	 * @description 通过父级id查子级树
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-20
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getChildTree")
	@ResponseBody
	public Object getCateTreeByPid(HttpServletRequest request) throws Exception {
		log.info("start[CategoryController.getCateTreeByPid]");
		String parentCatId = request.getParameter("parentCatId");
		JSONObject params = new JSONObject();
		if (StringUtil.isEmpty(parentCatId)) {
			params.put("parentCatId", 0);
		} else {
			params.put("parentCatId", Integer.parseInt(parentCatId));
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
		log.info("end[CategoryController.getCateTreeByPid]");
		return resultObject.getContent();
	}

	/**
	 * @description 获取指定分类的名称
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-4-28
	 * @param request
	 * @param catId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findCateById")
	@ResponseBody
	public Object findCateById(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[CategoryController.getDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("catId", catId);
		//map.put("carId", 0);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, map));
		log.info("end[CategoryController.getDetail]");
		return resultObject;
	}
}
