
package com.cnit.yoyo.shop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.ShopGrade;
import com.cnit.yoyo.model.shop.dto.ShopGradeDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 * @ClassName: ShopGradeController 
 * @Description: 店铺等级管理
 * @author xiaox
 * @date 2015-5-25 下午1:19:49
 */
@Controller
@RequestMapping("/shopGrade")
public class ShopGradeController {
    public static final Logger log = LoggerFactory.getLogger(ShopGradeController.class);
    
    
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
        return "pages/biz/shop/shopGradeIndex";
    }
    
  /**
   * 
   *@description 获取店铺等级列表
   *@detail <方法详细描述>
   *@author <a href="xiaoxiang@cnit.com">肖湘</a>
   *@version 1.0.0
   *@data 2015-5-25
   *@param request
   *@param brand
   *@return
   *@throws Exception
   */
    @RequestMapping("/shopGradeList")
    @ResponseBody
    public Object shopGradeList(HttpServletRequest request, ShopGradeDTO shopGrade) throws Exception {
        log.info("start[ShopGradeController.shopGradeList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, shopGrade));
        log.info("end[ShopGradeController.shopGradeList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 新增店铺等级
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-25
     *@param request
     *@param brand
     *@param goodCategory
     *@param identifiers
     *@return
     */
    @RequestMapping("/insertShopGrade")
    @ResponseBody
    public Object insertShopGrade(HttpServletRequest request,ShopGrade shopGrade){
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = null;
         try {
               resultObject = memberService.doService(new RequestObject(headObject, shopGrade));
               if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //等级名称不存在，可以添加
                   JsonConfig jsonConfig = new JsonConfig();
                   jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
                       public boolean apply(Object source, String name, Object value) {
                           if (value == null || value.equals("")) {
                               return true;
                           } else {
                               return false;
                           }
                       }
                   });
                   headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
                   resultObject = memberService.doService(new RequestObject(headObject, JSONObject.fromObject(shopGrade,jsonConfig)));
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
    /**
     * 
     *@description 更新等级
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-26
     *@param request
     *@param brand
     *@param goodCategory
     *@param identifiers
     *@return
     */
    @RequestMapping("/updateShopGrade")
    @ResponseBody
    public Object updateShopGrade(HttpServletRequest request,ShopGrade shopGrade){
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = null;
         try {
               resultObject = memberService.doService(new RequestObject(headObject, shopGrade));
               if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
                   JsonConfig jsonConfig = new JsonConfig();
                   jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
                       public boolean apply(Object source, String name, Object value) {
                           if (value == null || value.equals("")) {
                               return true;
                           } else {
                               return false;
                           }
                       }
                   });
                   headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
                   resultObject = memberService.doService(new RequestObject(headObject, JSONObject.fromObject(shopGrade,jsonConfig)));
               }
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
    /**
     * 
     *@description 批量删除
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-26
     *@param request
     *@param brandIds
     *@return
     */
    @RequestMapping("/deleteShopGrade")
    @ResponseBody
    public Object deleteBrand(HttpServletRequest request,@RequestParam(value="shopGradeIds[]",required=true)Integer [] shopGradeIds){
         HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
         ResultObject resultObject = null;
         try {
               resultObject = memberService.doService(new RequestObject(headObject, shopGradeIds));
               
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
    /**
     * 
     *@description 查找店铺等级
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-8
     *@param request
     *@param type
     *@return
     *@throws Exception
     */
    @RequestMapping("/find")
    public Object find(HttpServletRequest request,@RequestParam(value = "type", required = true) String type) throws Exception {
          HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
          Map<String, String> dataMap = new HashMap<String, String>();
          dataMap.put("type", type);
          return memberService.doService(new RequestObject(headObject,dataMap));
    }
    
}