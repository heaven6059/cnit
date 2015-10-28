package com.cnit.yoyo.shop.enter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.cnit.yoyo.model.goods.Brand;
import com.cnit.yoyo.model.shop.ShopBrandWithBLOBs;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @ClassName: BrandController
 * @Description: 商品品牌管理
 * @author xiaox
 * @date 2015-3-21 下午2:19:49
 */
@Controller
@RequestMapping("/brand")
public class BrandController {
	public static final Logger log = LoggerFactory.getLogger(BrandController.class);

	@Autowired
	private RemoteService itemService;
	
	@Autowired
	private RemoteService memberService;

	/**
	 * @Title: businessManager
	 * @Description: 转向品牌列表
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@RequestMapping("/businessManager")
	public String businessManager(HttpServletRequest request) throws Exception {
		return "pages/goodsMng/brandBusiness";
	}

	/**
	 * 
	 * @Title: getBrandList
	 * @Description: 获取商品品牌列表
	 * @param @param request
	 * @param @param specQry
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @author xiaox
	 * @date 2015-3-21 下午2:36:07
	 */
	@RequestMapping("/brandList")
	@ResponseBody
	public Object getBrandList(HttpServletRequest request, Brand brand) throws Exception {
		log.info("start[BrandController.getBrandList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		data.put("brand", brand);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
		log.info("end[BrandController.getBrandList]");
		return resultObject;
	}

	/**
	 * @Title: getBrandBusinessList
	 * @Description: 获取申请品牌列表
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param shopBrand
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/brandBusinessList")
	@ResponseBody
	public Object getBrandBusinessList(HttpServletRequest request, ShopBrandWithBLOBs shopBrand) throws Exception {
		log.info("start[BrandController.getBrandBusinessList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		data.put("shopBrand", shopBrand);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
		log.info("end[BrandController.getBrandBusinessList]");
		System.out.println(resultObject.getContent());
		return resultObject;
	}

	/**
	 * @Title: insertBrandBusiness
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param shopBrand
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/insertBrandBusiness")
	@ResponseBody
	public Object insertBrandBusiness(HttpServletRequest request, ShopBrandWithBLOBs shopBrand) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = memberService.doService(new RequestObject(headObject, shopBrand));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * @Title: updateBrandBusiness
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param shopBrand
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/updateBrandBusiness")
	@ResponseBody
	public Object updateBrandBusiness(HttpServletRequest request, ShopBrandWithBLOBs shopBrand) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = memberService.doService(new RequestObject(headObject, shopBrand));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * @Title: deleteBrandBusiness
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param shopBrand
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/deleteBrandBusiness")
	@ResponseBody
	public Object deleteBrandBusiness(HttpServletRequest request, ShopBrandWithBLOBs shopBrand) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = memberService.doService(new RequestObject(headObject, shopBrand));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	
    /**
     * @description <b>查询汽车品牌</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-11
     * @param @param request
     * @param @return
     * @return Object
   */
   @ResponseBody
   @RequestMapping("/findCarBrand")
   public Object findCarBrand(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010701-01");
        try {
        	Map<String, Object> params=new HashMap<String,Object>();
       		params.put("identifier", identifier);
            ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
            return resultObject.getContent();
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
   }

}