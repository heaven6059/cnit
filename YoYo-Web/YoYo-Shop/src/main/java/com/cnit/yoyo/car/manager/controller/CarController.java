
package com.cnit.yoyo.car.manager.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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

/**
 * 
 * @ClassName: CarController 
 * @Description: 车型
 * @author xiaox
 * @date 2015-3-26 下午7:00:40
 */
@Controller
@RequestMapping("/car")
public class CarController {
    public static final Logger log = LoggerFactory.getLogger(CarController.class);
    
    @Autowired
    private RemoteService itemService;

  
    
  /**
   * 
   * @Title: getCarList 
   * @Description: 获取车型列表
   * @param @param request
   * @author xiaox
   * @date 2015-3-26 下午5:13:58
   */
    @RequestMapping("/carList")
    @ResponseBody
    public Object getCarList(HttpServletRequest request,@RequestParam(value="carYearId",required=false) Integer carYearId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,carYearId));
        return resultObject;
    }
    
  
  /**
   * 
   *@description 根据carId，获取车系，年款，厂商等信息
   *@detail <方法详细描述>
   *@author <a href="xiaoxiang@cnit.com">肖湘</a>
   *@version 1.0.0
   *@data 2015-4-28
   *@param request
   *@param carId
   *@return
   *@throws Exception
   */
    @RequestMapping("/carInfo")
    @ResponseBody
    public Object getCarInfo(HttpServletRequest request,@RequestParam(value="carId",required=false) Integer carId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010301-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,carId));
        return resultObject;
    }
    
    
    /**
     * @description <b>查找车型</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-11
     * @param @param request
     * @param @return
     * @return Object
   */
   @ResponseBody
   @RequestMapping("/findCar")
   public Object findCar(HttpServletRequest request,@RequestParam(value="deptId" ,required=true)Integer deptId){
     HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010301-11");
        try {
         ResultObject resultObject = itemService.doService(new RequestObject(headObject, deptId));
         return resultObject.getContent();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
   }
    
    
   /**
    * 
    *@description 通过品牌id查车型
    *@detail <方法详细描述>
    *@author <a href="xiaoxiang@cnit.com">肖湘</a>
    *@version 1.0.0
    *@data 2015-7-1
    *@param request
    *@param carYearId
    *@return
    *@throws Exception
    */
   @RequestMapping("/getCarByBrandId")
   @ResponseBody
   public Object getCarByBrandId(HttpServletRequest request,@RequestParam(value="brandId",required=false) Integer brandId) throws Exception {
       HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010301-16", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
       
       ResultObject resultObject = itemService.doService(new RequestObject(headObject,brandId));
       return resultObject;
   }
  
}
