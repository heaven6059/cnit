
package com.cnit.yoyo.car.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

    
    
  
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carFactoryIndex";
    }
    
  
    
    /**
     * 
     * @Description: 获取厂商列表  
     * @author xiaox
     * @date 2015-3-28 上午10:52:31
     */
    @RequestMapping("/carFactoryList")
    @ResponseBody
    public Object getCarFactoryList(HttpServletRequest request,CarFactory carFactory,String sort, String order) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
            sort = "t_car_factory.factoryId";
            order = "desc";
        }else{
            if(sort.split(",").length>1){
                sort=sort.split(",")[sort.split(",").length-1];
            }
        }
        if(StringUtils.isNotBlank(order)){
            if(order.split(",").length>1){
                order=order.split(",")[order.split(",").length-1];
            }
        }
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carFactory", carFactory);
        data.put("sort", sort);
        data.put("order", order);
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
    
   
    
    @RequestMapping("/insertCarFactory")
    @ResponseBody
    public Object insertCarFactory(HttpServletRequest request,CarFactory carFactory){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, carFactory));
        	 
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //同一汽车厂商名称不存在，可以添加
        		  
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   
	        	   resultObject = itemService.doService(new RequestObject(headObject, carFactory));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    
    @RequestMapping("/deleteCarFactory")
    @ResponseBody
    public Object deleteCarFactory(HttpServletRequest request,@RequestParam(value="id[]",required=true)Integer[] id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, id));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    
    @RequestMapping("/updateCarFactory")
    @ResponseBody
    public Object updateCarFactory(HttpServletRequest request,CarFactory carfactory){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   resultObject = itemService.doService(new RequestObject(headObject, carfactory));
	       	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020113-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carfactory));
	       	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
