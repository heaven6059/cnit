/**
 * 文 件 名   :  CouponsController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="xiaoxiang@cnit.com">肖湘</a>
 * 创建时间  :  2015-4-20 下午1:04:45
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.activity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * 
 * @ClassName: CouponsController 
 * @Description: 优惠券
 * @author xiaox
 * @date 2015-4-20 下午1:07:30
 */
@Controller
@RequestMapping("/coupons")
public class CouponsController {

    @Autowired
    private RemoteService itemService;

    
    /**
     * 
     *@description 获取优惠券列表
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-21
     *@param request
     *@return
     */
    @RequestMapping("/couponsList")
    public String couponsList(HttpServletRequest request,Integer t){
    	request.setAttribute("t", t);
        return "pages/activityMng/couponsList";
    }
    
    @RequestMapping("/couponsListJson")
    @ResponseBody
    public Object couponsListJson(HttpServletRequest request,Integer type){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //TODO 获取当前登陆者店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        dataMap.put("companyId",companyId);
        dataMap.put("storeId", storeId);
        dataMap.put("type", type);
        try {
            resultObject = itemService.doService(new RequestObject(headObject,dataMap));
            request.setAttribute("coupons", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject.getContent();
    }
    
    @RequestMapping("/editCoupons")
    public String editCoupons(HttpServletRequest request,@RequestParam(value="couponId",required=true) int couponId){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = itemService.doService(new RequestObject(headObject, couponId));
            request.setAttribute("coupons", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/goodsMng/goodsPublishVirtual";
    }
    
    @RequestMapping("/pic")
    @ResponseBody
    public Object getProductPic(HttpServletRequest request,Integer cpnsId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-08", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, cpnsId));
        return resultObject;
    }
    
    
    /**
     * 
     * @Description: 保存优惠券  
     * @param @param request
     * @author xiaox
     * @date 2015-4-20 下午1:08:08
     */
    @RequestMapping("/saveCoupons")
    @ResponseBody
    public Object saveCoupons(HttpServletRequest request, CouponsDTO couponsDto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 设置店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        couponsDto.setCompanyId(Long.valueOf(companyId));
        couponsDto.setStoreId(Long.valueOf(storeId));
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
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, couponsDto));
        
        return resultObject;
    }

    
    /**
     * 
     *@description 更新优惠券
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-21
     *@throws Exception
     */
    @RequestMapping("/updateCoupons")
    @ResponseBody
    public Object updateCoupons(HttpServletRequest request, CouponsDTO couponsDto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, couponsDto));
        
        return resultObject;
    }
    
    
    @RequestMapping("/deleteCoupons")
    @ResponseBody
    public Object deleteCoupons(HttpServletRequest request, int couponId,int ruleId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("ruleId",ruleId);
        dataMap.put("couponId", couponId);
        return itemService.doService(new RequestObject(headObject, dataMap));
        
    }
    @RequestMapping("/optCoupons")
    @ResponseBody
    public Object optCoupons(HttpServletRequest request, int couponId,int ruleId,String opt) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("ruleId",ruleId);
        dataMap.put("couponId", couponId);
        dataMap.put("opt", opt);
        return itemService.doService(new RequestObject(headObject, dataMap));
        
    }
    
    /**
     * 
     *@description 获得优惠券的适用范围
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-7-13
     */
    @RequestMapping("/getCatAndBrand")
    @ResponseBody
    public Object getCatAndBrand(HttpServletRequest request, int couponId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000030101-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        return itemService.doService(new RequestObject(headObject, couponId));
        
    }
}
