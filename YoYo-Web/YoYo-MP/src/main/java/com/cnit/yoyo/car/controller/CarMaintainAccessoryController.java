
package com.cnit.yoyo.car.controller;

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
import com.cnit.yoyo.model.car.dto.CarMaintainAccessoryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 *@description 保养配件管理
 *@detail <功能详细描述>
 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
 *@version 1.0.0
 */
@Controller
@RequestMapping("/carMaintainAccessory")
public class CarMaintainAccessoryController {
    public static final Logger log = LoggerFactory.getLogger(CarMaintainAccessoryController.class);
    
    @Autowired
    private RemoteService itemService;

    /**
     * @description 映射至保养周期管理首页
     * @detail <方法详细描述>
     * @version 1.0.0
     * @data 2015年3月21日
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carMaintainAccessory";
    }
    
    /**
     * 
     *@description 添加保养配件
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-19
     *@param request
     *@param carTypeDTO
     *@return
     */
    @RequestMapping("/saveMaintainAccessory")
    @ResponseBody
    public Object saveMaintainAccessory(HttpServletRequest request,CarMaintainAccessoryDTO maintainAccessory){
         ResultObject resultObject = null;
         try {
             HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "011301-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
                   
             resultObject = itemService.doService(new RequestObject(headObject, maintainAccessory));
        } catch (Exception e) {
            e.printStackTrace();
        }
         return  resultObject ;
    }
    
    
}
