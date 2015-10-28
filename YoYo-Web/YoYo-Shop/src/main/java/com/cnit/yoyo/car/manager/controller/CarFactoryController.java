
package com.cnit.yoyo.car.manager.controller;

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
import com.cnit.yoyo.model.car.CarFactory;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @ClassName: CarFactoryController 
 * @Description: 汽车厂商
 * @author xiaox
 * @date 2015-3-28 上午10:51:57
 */
@Controller
@RequestMapping("/carFactory")
public class CarFactoryController {
    public static final Logger log = LoggerFactory.getLogger(CarFactoryController.class);
    
    @Autowired
    private RemoteService itemService;

    
   
  
    
    /**
     * 
     * @Description: 获取厂商列表  
     * @author xiaox
     * @date 2015-3-28 上午10:52:31
     */
    @RequestMapping("/carFactoryList")
    @ResponseBody
    public Object getCarFactoryList(HttpServletRequest request,CarFactory carFactory) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carFactory", carFactory);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    /**
     * 
     * @param @param request
     * @param @param categoryId
     * @param @return
     * @author ssd
     */
    @RequestMapping("/findSelect")
    @ResponseBody
    public Object findSelect(HttpServletRequest request,@RequestParam(value="brandId",required=false) Integer brandId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject,brandId));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
   
   
  
}
