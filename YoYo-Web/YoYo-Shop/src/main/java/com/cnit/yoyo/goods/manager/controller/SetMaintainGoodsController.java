
package com.cnit.yoyo.goods.manager.controller;

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
import com.cnit.yoyo.model.car.dto.CarMaintainGoodsDTO;
import com.cnit.yoyo.model.car.dto.CarMaintainReferenceDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
/**
 * 
 *@description 设置保养商品
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/setMaintainGoods")
public class SetMaintainGoodsController {
    public static final Logger log = LoggerFactory.getLogger(SetMaintainGoodsController.class);
    
    @Autowired
    private RemoteService itemService;

    /**
     * 
     *@description 映射到设置保养商品页
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-18
     *@return
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/goodsMng/setMaintainGoods";
    }
    
    /**
     * 
     *@description 获得保养周期列表
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-18
     *@param request
     *@param dto
     *@return
     *@throws Exception
     */
    @RequestMapping("/getMaintainList")
    @ResponseBody
    public Object getMaintainList(HttpServletRequest request, CarMaintainReferenceDTO dto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010803-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        dto.setCompanyId(companyId);
        dto.setStoreId(storeId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, dto));
        return resultObject;
    }
    
    /**
     * 
     *@description 通过分类id集合获得指定的保养项目的保养配件类别
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-21
     *@param request
     *@param dto
     *@return
     *@throws Exception
     */
    @RequestMapping("/getMaintainItem")
    @ResponseBody
    public Object getMaintainItem(HttpServletRequest request,@RequestParam(value="maintainItemIds[]",required=true)Integer [] maintainItemIds,@RequestParam(value="maintianId",required=true)Integer maintianId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010803-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("ids", maintainItemIds);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        data.put("companyId", companyId);
        data.put("maintianId", maintianId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        return resultObject;
    }
    
    
    /**
     * 
     *@description 保存或更新默认商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-22
     *@param request
     *@param maintainItemIds
     *@return
     *@throws Exception
     */
    @RequestMapping("/saveDefaultGoods")
    @ResponseBody
    public Object saveDefaultGoods(HttpServletRequest request,CarMaintainGoodsDTO dto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010803-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        dto.setCompanyId(companyId);
        dto.setStoreId(storeId);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, dto));
        return resultObject;
    }
    
    
    
    
    
}
