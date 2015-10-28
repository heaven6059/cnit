package com.cnit.yoyo.good.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private RemoteService itemService;
	
	/**
	 * 根据商品id查询货品列表信息
	 * @param request
	 * @param goodsId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/productSpec")
	@ResponseBody
	public Object getProductSpecByGoodsId(HttpServletRequest request, Integer goodsId) throws Exception{
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
		return itemService.doService(new RequestObject(headObject, goodsId));
	}
	
	@RequestMapping("/productList")
    @ResponseBody
    public Object getProductByCompanyId(HttpServletRequest request,Long companyId) throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-06", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("companyId", companyId);   //TODO 获取店铺id
        return itemService.doService(new RequestObject(headObject, data));  
    }  
	
	/**
	 * 
	 *@description 根据货品id查找货品
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-5-13
	 *@param request
	 *@param productIds
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/findProductByIds")
    @ResponseBody
    public Object findProductByIds(HttpServletRequest request,@RequestParam(value = "productId[]", required = true)Integer[] productId,Long companyId) throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020121-07", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("companyId", companyId);
        data.put("idsArray", productId);
        return itemService.doService(new RequestObject(headObject, data));  
    } 
}
