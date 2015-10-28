
package com.cnit.yoyo.shop.enter.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

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
import com.cnit.yoyo.model.shop.dto.CompanyRegionCatDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 *@description 店铺经营范围
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/companyRegionCat")
public class CompanyRegionCatController {
    public static final Logger log = LoggerFactory.getLogger(CompanyRegionCatController.class);
    
    
    @Autowired
    private RemoteService memberService;

    /**
     * @description 映射至店铺列表管理首页
     * @detail <方法详细描述>
     * @version 1.0.0
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/shop/regionIndex";
    }
    
    /**
     * 
     *@description 获取经营范围
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-23
     *@throws Exception
     */
    @RequestMapping("/regionTree")
    @ResponseBody
    public Object regionTree(HttpServletRequest request) {
        log.info("start[CompanyRegionCatController.regionTree]");
        String parentCatId = request.getParameter("parentCatId");
        JSONObject params = new JSONObject();
        if (StringUtil.isEmpty(parentCatId)) {
            params.put("parentCatId", 0);
        } else {
            params.put("parentCatId", Integer.parseInt(parentCatId));
        }
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030801-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = memberService.doService(new RequestObject(headObject, params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end[CompanyRegionCatController.regionTree]");
        return resultObject.getContent();
    }
    
    /**
     * 
     *@description 保存经营范围
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-23
     */
    @RequestMapping("/insert")
    @ResponseBody
    public Object insertCate(HttpServletRequest request,@RequestParam(value="goodCategory[]",required=false)Integer[] goodCategory) throws Exception {
        log.info("start[CompanyRegionCatController.insertCate]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030801-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String,Object> data = new HashMap<String, Object>();
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
            data.put("cate", catJSON);
        }
       data.put("goodCategory", goodCategory);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
        log.info("end[CompanyRegionCatController.insertCate]");
        return resultObject;
    }
    
    /**
     * 
     *@description 删除
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-24
     *@param request
     *@param regionId
     *@return
     */
    @RequestMapping("/deleteRegion")
    @ResponseBody
    public Object deleteRegion(HttpServletRequest request,CompanyRegionCatDTO dto){
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030801-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = null;
         try {
               resultObject = memberService.doService(new RequestObject(headObject, dto));
               
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
    /**
     * 
     *@description 获得经营范围的商品分类
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-24
     *@return
     */
    @RequestMapping("/findRegionShip")
    @ResponseBody
    public Object findRegionShip(HttpServletRequest request,@RequestParam(value="regionId",required=true)Integer regionId){
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030801-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = null;
         try {
               resultObject = memberService.doService(new RequestObject(headObject, regionId));
               
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
    
}