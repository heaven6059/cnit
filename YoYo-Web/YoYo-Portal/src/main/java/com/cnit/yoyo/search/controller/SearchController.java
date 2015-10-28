package com.cnit.yoyo.search.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.search.model.SearchGoods;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.Configuration;
import com.cnit.yoyo.util.StringUtil;

/**
 * 搜索商品
 * 
 * @author wanghb
 * @date 2015-04-22
 * @version 1.0.0
 */
@Controller
public class SearchController {

	public static final Logger log = LoggerFactory.getLogger(SearchController.class);

	@Autowired
	public RemoteService searchClientService;
	@Autowired
	public RemoteService itemService;
	@Autowired
	public RemoteService memberService;

	/**
	 * 关键字查询
	 * 
	 * @param request
	 * @param query
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/search")
	public Object searchKey(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String currrentPage = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		dataMap.put("currrentPage", StringUtil.isEmpty(currrentPage) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(currrentPage));
		dataMap.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		dataMap.put("searchKey", request.getParameter("q"));
		dataMap.put("sq", request.getParameter("sq"));// 条件内关键字
		dataMap.put("orderFile", request.getParameter("orderFile"));
		dataMap.put("order", request.getParameter("order"));
		dataMap.put("cateId", request.getParameter("cateId"));// 虚拟分类ID
		dataMap.put("cname", request.getParameter("cname"));
		dataMap.put("aId", request.getParameter("aId"));// 属性ID
		dataMap.put("atv", request.getParameter("atv"));// 属性值ID
		dataMap.put("atV", request.getParameter("atV"));// 属性值名称
		dataMap.put("atN", request.getParameter("atN"));// 属性名称
		dataMap.put("brandId", request.getParameter("brandId"));
		dataMap.put("bname", request.getParameter("bname"));
		dataMap.put("cId", request.getParameter("cId"));// 实体分类ID
		dataMap.put("store", request.getParameter("store"));
		dataMap.put("goodsKind", request.getParameter("goodsKind"));// 商品描述关键字
		dataMap.put("st", "1");
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, dataMap));
			JSONObject resultJson = JSONObject.fromObject(resultObject.getContent());
			String cateIds = resultJson.get("cateList").toString();
			cateIds = cateIds.split("\\[")[1].split("\\]")[0];
			if (StringUtils.isNotBlank(cateIds)) {
				HeadObject cateHeadObject = CommonHeadUtil.geneHeadObject(request, "2000020103-14", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
				ResultObject cateResultObject = itemService.doService(new RequestObject(cateHeadObject, cateIds));
				mv.addObject("cateResultObject", cateResultObject.getContent());
			}
			mv.addObject("result", resultObject.getContent());

			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("currrentPage", 1);
			dataMap1.put("pageSize", 3);
			dataMap1.put("orderFile", "buyCount");
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject1 = searchClientService.doService(new RequestObject(headObject1, dataMap1));
			mv.addObject("resultHot", resultObject1.getContent());

			String brandIds = resultJson.get("brandIdList").toString();// 品牌集合

			HeadObject headObject2 = CommonHeadUtil.geneHeadObject(request, "2000020109-20", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject2 = itemService.doService(new RequestObject(headObject2, brandIds));
			mv.addObject("brandIds", resultObject2.getContent());

		} catch (Exception e) {
			log.error("关键字搜索异常!" + e);
			mv.addObject("message", "关键字搜索异常!");
		}
		mv.setViewName("pages/productList");
		mv.addObject("mapList", dataMap);
		request.getSession().setAttribute("imagePath", Configuration.getInstance().getConfigValue("images.url"));
		return mv;
	}

	/**
	 * 搜店铺
	 * 
	 * @Description:
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/searchShop")
	public Object searchShopKey(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String currrentPage = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		dataMap.put("currrentPage", StringUtil.isEmpty(currrentPage) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(currrentPage));
		dataMap.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		dataMap.put("searchKey", request.getParameter("q"));
		dataMap.put("orderFile", request.getParameter("orderFile"));
		dataMap.put("order", request.getParameter("order"));
		dataMap.put("st", "2");
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, dataMap));
			mv.addObject("result", resultObject.getContent());

			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("currrentPage", 1);
			dataMap1.put("pageSize", 3);
			dataMap1.put("orderFile", "buyCount");
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject1 = searchClientService.doService(new RequestObject(headObject1, dataMap1));
			mv.addObject("resultHot", resultObject1.getContent());

		} catch (Exception e) {
			log.error("关键字搜索异常!" + e);
			mv.addObject("message", "关键字搜索异常!");
		}
		mv.setViewName("pages/productList");
		mv.addObject("mapList", dataMap);
		request.getSession().setAttribute("imagePath", Configuration.getInstance().getConfigValue("images.url"));
		return mv;
	}

	/**
	 * 搜优惠券
	 * 
	 * @Description:
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/searchCoupons")
	public Object searchCouponsKey(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		String currrentPage = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		dataMap.put("currrentPage", StringUtil.isEmpty(currrentPage) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(currrentPage));
		dataMap.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		dataMap.put("searchKey", request.getParameter("q"));
		dataMap.put("orderFile", request.getParameter("orderFile"));
		dataMap.put("order", request.getParameter("order"));
		dataMap.put("st", "3");
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, dataMap));
			mv.addObject("result", resultObject.getContent());

			Map<String, Object> dataMap1 = new HashMap<String, Object>();
			dataMap1.put("currrentPage", 1);
			dataMap1.put("pageSize", 3);
			dataMap1.put("orderFile", "buyCount");
			HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			ResultObject resultObject1 = searchClientService.doService(new RequestObject(headObject1, dataMap1));
			mv.addObject("resultHot", resultObject1.getContent());

		} catch (Exception e) {
			log.error("关键字搜索异常!" + e);
			mv.addObject("message", "关键字搜索异常!");
		}
		mv.setViewName("pages/productList");
		mv.addObject("mapList", dataMap);
		request.getSession().setAttribute("imagePath", Configuration.getInstance().getConfigValue("images.url"));
		return mv;
	}

	/**
	 * 全量创建索引
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/search/create")
	public Object createInitialData(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		searchClientService.doService(new RequestObject(headObject, null));
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "商品索引已创建成功!");
		return mv;
	}

	/**
	 * 全量创建店铺索引
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/searchShop/create")
	public Object createShopData(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		searchClientService.doService(new RequestObject(headObject, null));
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "店铺索引已创建成功!");
		return mv;
	}

	/**
	 * 全量删除索引
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/search/deleteIndex")
	public Object deleteIndex(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		searchClientService.doService(new RequestObject(headObject, null));
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "商品索引已创建成功!");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/update")
	public Object updateIndex(HttpServletRequest request) throws Exception {
		SearchGoods searchGoods = new SearchGoods();
		searchGoods.setBrandId(47);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		searchClientService.doService(new RequestObject(headObject, JSONObject.fromObject(searchGoods)));
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "商品索引更新成功!");
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/search/deleteIndexByGoodId")
	public Object deleteIndexByGoodId(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		searchClientService.doService(new RequestObject(headObject, null));
		ModelAndView mv = new ModelAndView("forward:/");
		mv.addObject("message", "商品索引更新成功!");
		return mv;
	}

	/**
	 * 品牌ID查询
	 * 
	 * @param request
	 * @param searchGoods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/searchByBrandId")
	public Object searchByBrandName(HttpServletRequest request, SearchGoods searchGoods) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, JSONObject.fromObject(searchGoods)));
		ModelAndView mv = new ModelAndView();
		mv.addObject("result", resultObject.getContent());
		mv.setViewName("pages/productList");
		return mv;
	}

	/**
	 * 热门商品(推广)
	 * 
	 * @param request
	 * @param searchGoods
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/searchByHot")
	@ResponseBody
	public Object searchByHot(HttpServletRequest request, SearchGoods searchGoods) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("currrentPage", 1);
		dataMap.put("pageSize", 3);
		dataMap.put("orderFile", "buyCount");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "050101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = searchClientService.doService(new RequestObject(headObject, JSONObject.fromObject(searchGoods)));
		return resultObject;
	}

	@ResponseBody
	@RequestMapping("/saveMemberCoupon")
	public Object saveMemberCoupon(HttpServletRequest request, MemberCoupon memberCouponDTO) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030115-03");
		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
		if (null == memberListDo) {
			headObject.setRetCode(ErrorCode.NOT_LOGIN);
			return headObject;
		}
		memberCouponDTO.setMemberId(Integer.valueOf(memberListDo.getMemberId()));
		memberCouponDTO.setDisabled(0);
		memberCouponDTO.setMemcIsvalid(0);
		RequestObject requestObject = new RequestObject(headObject, memberCouponDTO);
		ResultObject resultObject = (ResultObject) memberService.doService(requestObject);
		return resultObject;
	}
}
