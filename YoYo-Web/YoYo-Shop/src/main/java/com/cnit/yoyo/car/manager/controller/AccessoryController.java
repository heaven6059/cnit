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
package com.cnit.yoyo.car.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
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
     *@description 查询参数配置项类别
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-6
     *@param request
     *@return
     *@throws Exception
     */
    @RequestMapping("/catalogList")
    @ResponseBody
    public Object getAccessoryCatalogList(HttpServletRequest request,Integer catId) throws Exception {
        log.info("start[AccessoryController.getAccessoryCatalogList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010501-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,catId));
        log.info("end[AccessoryController.getAccessoryCatalogList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 根据参数配置类别id查找配件列表
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-6
     *@param request
     *@return
     *@throws Exception
     */
    @RequestMapping("/catalogDataList")
    @ResponseBody
    public Object getAccessoryCatalogDataList(HttpServletRequest request, Integer catalogId,Integer brandId) throws Exception {
        log.info("start[AccessoryController.getAccessoryCatalogDataList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010501-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put("catalogId",catalogId);
        map.put("brandId", brandId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,map));
        log.info("end[AccessoryController.getAccessoryCatalogDataList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 根据配置id查找配置类型信息
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-14
     *@param request
     *@param accId
     *@return
     *@throws Exception
     */
    @RequestMapping("/findAccessoryTypeInfo")
    @ResponseBody
    public Object findAccessoryTypeInfo(HttpServletRequest request, Integer accId) throws Exception {
        log.info("start[AccessoryController.findAccessoryTypeInfo]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010501-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,accId));
        log.info("end[AccessoryController.findAccessoryTypeInfo]");
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
    public Object getAccessoryDetail(HttpServletRequest request, Integer accId) throws Exception {
        log.info("start[AccessoryController.getAccessoryDetail]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, accId));
        log.info("end[AccessoryController.getAccessoryDetail]");
        return resultObject;
    }
    
    /**
     * 
     *@description 显示配件适用车型
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-7-14
     *@return
     * @throws Exception 
     */
    @RequestMapping("/getCars")
    public String getCars(HttpServletRequest request,Integer accId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize));
        data.put("accId",accId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        request.setAttribute("cars", resultObject.getContent());
        request.setAttribute("accId", accId);
        return "pages/goodsMng/accessoryDetail";
    }
    
    /**
     * 
     *@description 显示配件适用车型
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-7-14
     *@return
     * @throws Exception 
     */
    @RequestMapping("/getCarsList")
    @ResponseBody
    public Object getCarsList(HttpServletRequest request,Integer accId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020104-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? 10 : Integer.parseInt(pageSize));
        data.put("accId",accId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        request.setAttribute("accId", accId);
        return resultObject;
    }
}
