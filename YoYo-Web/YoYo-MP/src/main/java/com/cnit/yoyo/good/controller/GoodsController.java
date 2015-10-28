/**
 * 文 件 名   :  GoodsController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午3:11:42
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.good.controller;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.GoodsProductDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 商品管理功能
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	public static final Logger log = LoggerFactory.getLogger(GoodsController.class);
	@Autowired
	public RemoteService itemService;

	public void main(String[] args, HttpServletRequest request, Object obj) {
		String url = goodsIndex();
		// obj = getAccessoryGoodsById(request);
	}

	/**
	 * @description 根据id查询配件商品信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月14日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getAccessoryGoods")
	@ResponseBody
	private Object getAccessoryGoodsById(HttpServletRequest request, Integer goodsId) throws Exception {
		log.info("start[GoodsController.getAccessoryGoodsById]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
		log.info("end[GoodsController.getAccessoryGoodsById]");
		return resultObject;
	}

	/**
	 * 
	 * @description 卖家中心更新商品
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-4-30
	 * @param request
	 * @param goodsDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateGoods")
	@ResponseBody
	public Object updateGoods(HttpServletRequest request, GoodsProductDTO goodsDto) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object source, String name, Object value) {
				if (value == null) {
					return true;
				} else {
					return false;
				}
			}
		});
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsDto));

		return resultObject;
	}

	/**
	 * @description 获取扩展属性的值
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月13日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/attributeValue")
	@ResponseBody
	private Object getGoodAttributeValue(HttpServletRequest request) throws Exception {
		log.info("start[GoodsController.getGoodAttributeValue]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		content.put("catId", request.getParameter("catId"));
		content.put("goodsId", request.getParameter("goodsId"));
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[GoodsController.getGoodAttributeValue]");
		return resultObject;
	}

	/**
	 * @description 获取商品详细信息
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月9日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goodsDetail")
	@ResponseBody
	private Object getGoodsDetailById(HttpServletRequest request, Integer goodsId) throws Exception {
		log.info("start[GoodsController.getGoodsDetailById]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
		log.info("end[GoodsController.getGoodsDetailById]");
		return resultObject;
	}

	/**
	 * @description 批量操作
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月7日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/batchEdit")
	@ResponseBody
	private Object batchEdit(HttpServletRequest request) throws Exception {
		log.info("start[GoodsController.batchEdit]");
		String op = request.getParameter("op");
		ResultObject resultObject = null;
		/* 重新生成图片 */
		if ("regen".equalsIgnoreCase(op)) {
			resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE, "未知操作类型！"));
		} else {
			HeadObject headObject = null;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("rows", request.getParameter("rows"));
			/* 批量上架 */
			if ("up".equalsIgnoreCase(op)) {
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			}
			/* 批量下架 */
			else if ("down".equalsIgnoreCase(op)) {
				jsonObject.put("cause", request.getParameter("cause"));
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			}
			/* 批量审核 */
			else if ("check".equalsIgnoreCase(op)) {
				String ok=request.getParameter("ok");
				if(StringUtils.isNotBlank(ok)){
					if(ok.equals("1")){
						jsonObject.put("status",0);
						jsonObject.put("cause", "");
					}else{
						jsonObject.put("status",3);
						jsonObject.put("cause", request.getParameter("cause"));
					}
				}else{
				   resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE, "未知操作类型！"));
				}
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				
			}
			/* 商品除权 */
			else if ("exd".equalsIgnoreCase(op)) {
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			}
			/* 批量删除 */
			else if ("delete".equalsIgnoreCase(op)) {
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			}
			/* 设置标签 */
			else if ("tag".equalsIgnoreCase(op)) {
				headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				jsonObject.put("tags", request.getParameter("tags"));
			}
			if (headObject != null) {
			    jsonObject.put("preType", "");
				resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
			} else {
				resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE, "未知操作类型！"));
			}
		}
		log.info("end[GoodsController.batchEdit]");
		return resultObject;
	}

	/**
	 * @description 获取商品信息列表
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月3日
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/goodsList")
	@ResponseBody
	private Object getGoodsList(HttpServletRequest request, Integer page, Integer rows, String sort, String order, String marketableQ) throws Exception {
		log.debug("start[GoodsController.getGoodsList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		if (!StringUtils.isNotBlank(sort)) {
			sort = "lastModify";
			order = "desc";
		}else{
			if(sort.split(",").length>1){
				sort=sort.split(",")[sort.split(",").length-1];
			}
		}
		if(StringUtils.isNotBlank(order)){
			if(order.split(",").length>1){
				order=order.split(",")[order.split(",").length-1];
			}
		}
		QueryParamObject paramObject = null;
		JSONObject content = null;
		String params = request.getParameter("params");
		if (!StringUtil.isEmpty(params)) {
			content = JSONObject.fromObject(params);
		} else {
			content = new JSONObject();
		}
		JSONArray equals = content.optJSONArray("equals");
		if (equals == null) {
			equals = new JSONArray();
		}
		JSONObject disabled = new JSONObject();
		disabled.put("paramName", "A.disabledQ");
		disabled.put("paramValue", '0');
		equals.add(disabled);
		if (!StringUtil.isEmpty(marketableQ)) {
			JSONObject equal = new JSONObject();
			equal.put("paramName", "A.marketableQ");
			equal.put("paramValue", marketableQ);
			equals.add(equal);
		}
		content.put("equals", equals);
		paramObject = (QueryParamObject) JSONObject.toBean(content, QueryParamObject.class);
		paramObject.setPage(page);
		paramObject.setRows(rows);
		paramObject.setSort(sort);
		paramObject.setOrder(order);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		log.info("end[GoodsController.getGoodsList]");
		return resultObject;
	}

	/**
	 * 店铺商品查询
	 * 
	 * @param request
	 * @param page
	 * @param rows
	 * @param companyIdQ
	 * @param storeIdQ
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/storeGoodsList")
	@ResponseBody
	private Object getStoreGoodsList(HttpServletRequest request, Integer page, Integer rows, @RequestParam(required = true) Long companyIdQ, @RequestParam(required = true) Long storeIdQ) throws Exception {
		log.info("start[GoodsController.getGoodsList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		QueryParamObject paramObject = null;
		JSONObject content = new JSONObject();
		JSONArray equals = new JSONArray();

		JSONObject disabled = new JSONObject();
		disabled.put("paramName", "A.disabledQ");
		disabled.put("paramValue", "0");
		equals.add(disabled);
		JSONObject marketable = new JSONObject();
		marketable.put("paramName", "A.marketableQ");
		marketable.put("paramValue", "1");
		equals.add(marketable);
		JSONObject companyId = new JSONObject();
		companyId.put("paramName", "A.companyIdQ");
		companyId.put("paramValue", companyIdQ);
		equals.add(companyId);
		JSONObject storeId = new JSONObject();
		storeId.put("paramName", "A.storeIdQ");
		storeId.put("paramValue", storeIdQ);
		equals.add(storeId);

		content.put("equals", equals);
		paramObject = (QueryParamObject) JSONObject.toBean(content, QueryParamObject.class);
		paramObject.setPage(page);
		paramObject.setRows(rows);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		log.info("end[GoodsController.getGoodsList]");
		return resultObject;
	}

	/**
	 * @description 通过goodsId获得商品所有的图片
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-4
	 * @param request
	 * @param goodsId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productPic")
	@ResponseBody
	public Object getProductPic(HttpServletRequest request, Integer goodsId) throws Exception {
		log.info("start[GoodsController.getProductPic]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
		log.info("end[GoodsController.getProductPic]");
		return resultObject;
	}

	/**
	 * 
	 * @description 获取扩展属性的所有值，包括父级的
	 * @detail <方法详细描述>
	 * @author <a href="xiaoxiang@cnit.com">肖湘</a>
	 * @version 1.0.0
	 * @data 2015-5-9
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findAllAttrValue")
	@ResponseBody
	public Object findAttributeByParam(HttpServletRequest request) throws Exception {
		log.info("start[GoodsController.getGoodAttributeValue]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-31", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		JSONObject content = new JSONObject();
		content.put("catId", request.getParameter("catId"));
		content.put("goodsId", request.getParameter("goodsId"));
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
		log.info("end[GoodsController.getGoodAttributeValue]");
		return resultObject;
	}

	/**
	 * @description 页面跳转至商品管理主页
	 * @detail <方法详细描述>
	 * @author <a href="liming@cnitcloud.com">李明</a>
	 * @version 1.0.0
	 * @data 2015年4月3日
	 * @return
	 */
	@RequestMapping("/index")
	private String goodsIndex() {
		return "pages/biz/good/goodsIndex";
	}

	@RequestMapping("/editIndex/{goodsId}")
	private String editIndex(HttpServletRequest request, @PathVariable Integer goodsId) {
		request.setAttribute("goodsId", goodsId);
		return "pages/biz/good/goodsPublishMain";
	}

	@RequestMapping("/specEditIndex")
	private String editIndex(Integer goodsId) {
		return "pages/biz/good/goodSpecEditIndex";
	}
}
