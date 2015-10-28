
package com.cnit.yoyo.good.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
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
     * @description 映射至商品品牌管理首页
     * @detail <方法详细描述>
     * @version 1.0.0
     * @data 2015年3月21日
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/good/brandIndex";
    }
    
    /**
     * 
     *@description 整车品牌页
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-7-6
     *@return
     */
    @RequestMapping("/carIndex")
    public String carIndex() {
        return "pages/biz/good/brandCarIndex";
    }
    
    /**
     * 
     * @Title: getBrandList 
     * @Description: 获取商品品牌列表
     * @param @param request
     * @param @param specQry
     * @param @return
     * @param @throws Exception    设定文件 
     * @author xiaox
     * @date 2015-3-21 下午2:36:07
     */
    @RequestMapping("/brandList")
    @ResponseBody
    public Object getBrandList(HttpServletRequest request, Brand brand ,String sort, String order) throws Exception {
        log.info("start[BrandController.getBrandList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
            sort = "t_brand.brandId";
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
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ?1 : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? 10000 : Integer.parseInt(pageSize));
        data.put("brand", brand);
        data.put("sort", sort);
        data.put("order", order);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        log.info("end[BrandController.getBrandList]");
        return resultObject;
    }
    
    /**
     * 
     * @Title: insertBrand 
     * @Description: 新增品牌
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-23 上午9:49:24
     */
    @RequestMapping("/insertBrand")
    @ResponseBody
    public Object insertBrand(HttpServletRequest request,Brand brand,@RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory,
            @RequestParam(value="identifiers[]",required=false)String[] identifiers){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, brand));
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //品牌名称不存在，可以添加
        		   Map<String,Object> data = new HashMap<String, Object>();
        		   data.put("brand", brand);
        		   data.put("goodCateIds", goodCategory);
        		   data.put("identifiers", identifiers);
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   brand.setDisabled(GlobalStatic.DISABLED_DEFAULT_ON);
	        	   resultObject = itemService.doService(new RequestObject(headObject, data));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    /**
     * 
     * @Title: insertBrand 
     * @Description: 更新品牌
     * @param goodCategory    商品分类的id数组 
     * @author xiaox
     * @date 2015-3-23 上午9:49:24
     */
    @RequestMapping("/updateBrand")
    @ResponseBody
    public Object updateBrand(HttpServletRequest request,Brand brand,@RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory,
            @RequestParam(value="identifiers[]",required=false)String[] identifiers){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   Map<String,Object> data = new HashMap<String, Object>();
	  		   data.put("brand", brand);
	  		   data.put("goodCateIds", goodCategory);
	  		   data.put("identifiers", identifiers);
        	   resultObject = itemService.doService(new RequestObject(headObject, brand));
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的品牌名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, data));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    /**
     * 
     * @Title: insertBrand 
     * @Description: 删除品牌
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-23 上午9:49:24
     */
    @RequestMapping("/deleteBrand")
    @ResponseBody
    public Object deleteBrand(HttpServletRequest request,@RequestParam(value="brandIds[]",required=true)Integer [] brandIds){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, brandIds));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /** 
    * @Title: applyIndex 
    * @Description: TODO(这里用一句话描述这个方法的作用) 
    * @author Harder-Zhao
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws 
    */
    @RequestMapping("/apply_index")
    public String applyIndex() {
    	return "pages/biz/good/brandApplyIndex";
    }

    /** 
    * @Title: getBbrandApplyAll 
    * @Description: 查询全部品牌申请记录
    * @author Harder-Zhao
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    @RequestMapping("/brandApplyAll")
    @ResponseBody
    public Object getBrandApplyAll(HttpServletRequest request, ShopBrandWithBLOBs shopBrand) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("shopBrand", shopBrand);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        return resultObject;
    }

    /**
     * 
     * @Title: findBrandCateShip 
     * @Description: 查找该品牌与分类的关系
     * @param @param request
     * @param @param brandIds
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-25 下午7:36:28
     */
    @RequestMapping("/findBrandCateShip")
    @ResponseBody
    public Object findBrandCateShip(HttpServletRequest request,@RequestParam(value="brandId",required=true)Integer brandId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020110-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, brandId));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /** 
    * @Title: getBbrandApplying 
    * @Description: 查询品牌申请待审核记录 
    * @author Harder-Zhao
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    @RequestMapping("/brandApplying")
    @ResponseBody
    public Object getBrandApplying(HttpServletRequest request) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	Map<String, Object> data = new HashMap<String, Object>();
    	String pageIndex = request.getParameter("page");
    	String pageSize = request.getParameter("rows");
    	data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
    	data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
    	data.put("status", "0");
    	ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
    	return resultObject;
    }
    
    
    /** 
    * @Title: getBbrandApplyPass 
    * @Description: 查询品牌申请审核通过记录  
    * @author Harder-Zhao
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    @RequestMapping("/brandApplyPass")
    @ResponseBody
    public Object getBrandApplyPass(HttpServletRequest request) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	Map<String, Object> data = new HashMap<String, Object>();
    	String pageIndex = request.getParameter("page");
    	String pageSize = request.getParameter("rows");
    	data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
    	data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
    	data.put("status", "1");
    	ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
    	return resultObject;
    }
    
    /** 
    * @Title: getBrandApplyRefusePass 
    * @Description: 查询品牌申请审核不通过记录  
    * @author Harder-Zhao
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @return Object    返回类型 
    * @throws 
    */
    @RequestMapping("/brandApplyRefuse")
    @ResponseBody
    public Object getBrandApplyRefusePass(HttpServletRequest request) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	Map<String, Object> data = new HashMap<String, Object>();
    	String pageIndex = request.getParameter("page");
    	String pageSize = request.getParameter("rows");
    	data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
    	data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
    	data.put("status", "2");
    	ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
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
	* @Title: deleteBrandApply 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author Harder-Zhao
	* @param @param request
	* @param @param ids
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	@RequestMapping("/deleteBrandApply")
    @ResponseBody
    public Object deleteBrandApply(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer [] ids){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, ids));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }

	
	@RequestMapping("/markLabelForBrand")
	@ResponseBody
	public Object markLabelForBrandBusiness(HttpServletRequest request, @RequestParam(value="brandIds[]",required=true)Integer[] brandIds, @RequestParam(value="labelIds[]",required=false)Long[] labelIds) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("brandIds", brandIds);
			data.put("labelIds", labelIds);
			resultObject = memberService.doService(new RequestObject(headObject, data));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	/**
     * 
     * @Description: 根据分类ID查询品牌信息
     */
	@RequestMapping("/findBrandByCatId")
	@ResponseBody
	public Object findBrandByCatId(HttpServletRequest request, Integer catId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-24", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = itemService.doService(new RequestObject(headObject, catId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
	
	/**
	 * 
	 *@description 撤回审核记录
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-20
	 *@param request
	 *@return
	 *@throws Exception
	 */
    @RequestMapping("/brandApplyCancel")
    @ResponseBody
    public Object brandApplyCancel(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020109-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("status", "3");
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        return resultObject;
    }
    
}