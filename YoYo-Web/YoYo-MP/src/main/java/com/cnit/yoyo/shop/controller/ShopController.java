
package com.cnit.yoyo.shop.controller;

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
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 *@description 店铺列表
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/shop")
public class ShopController {
    public static final Logger log = LoggerFactory.getLogger(ShopController.class);
    
    
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
        return "pages/biz/shop/shopListIndex";
    }
    
   /**
    * 
    *@description 添加店铺
    *@detail <方法详细描述>
    *@author <a href="xiaoxiang@cnit.com">肖湘</a>
    *@version 1.0.0
    *@data 2015-6-8
    *@return
    */
    @RequestMapping("/add")
    public String addShop(@RequestParam(value = "type", required = true) String type,Integer companyId, Map<String, Object> map,HttpServletRequest request){
        map.put("type", type);
        if(companyId!=null){
            HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
            ResultObject resultObject = new ResultObject();
            try {
                resultObject = memberService.doService(new RequestObject(headObject, companyId));
            } catch (Exception e) {
                e.printStackTrace();
            }
            request.setAttribute("shopInfo", resultObject.getContent());
        }
        return "pages/biz/shop/shopRegisterSubmit";
    }
  
    /**
     * 
     *@description 批量修改有效期
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-8
     *@param request
     *@param company
     *@param ids
     *@return
     *@throws Exception
     */
    @RequestMapping("/setShopTime")
    @ResponseBody
    public Object setShopTime(HttpServletRequest request, @RequestParam(value="ids[]",required=true) Integer []ids) throws Exception {
        log.info("start[ShopController.setShopTime]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("ids", ids);
        data.put("lastTime", (String)request.getParameter("lastTime"));
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
        log.info("end[ShopController.setShopTime]");
        return resultObject;
    }
    
    
    /**
     * 
     *@description 获得店铺信息详情
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-29
     *@param request
     *@param companyId
     *@return
     *@throws Exception
     */
    @RequestMapping("/getDetail")
    public String getDetail(HttpServletRequest request,Integer companyId) throws Exception{
        log.info("start[ShopCheckController.getDetail]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
        request.setAttribute("shopInfo", resultObject.getContent());
        log.info("end[ShopCheckController.getDetail]");
        return "pages/biz/shop/shopListDetail";
    }
    
    /**
     * 
     * @Title: findShopList 
     * @Description: 根据集团id查找所有分店
     * @param @param request
     * @param @return
     * @param @throws Exception    设定文件 
     * @author xiaox
     * @date 2015-6-12 下午13:34:03
     */
    @RequestMapping("/findShopList")
    @ResponseBody
    public Object findShopList(HttpServletRequest request,Integer companyId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020105-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("companyId",Long.valueOf(companyId));
        return memberService.doService(new RequestObject(headObject, data));
    }
    
    
}