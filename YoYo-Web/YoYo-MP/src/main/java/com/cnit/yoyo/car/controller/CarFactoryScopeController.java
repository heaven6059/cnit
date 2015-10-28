
package com.cnit.yoyo.car.controller;

import javax.servlet.http.HttpServletRequest;

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
import com.cnit.yoyo.model.car.CarFactoryScope;
import com.cnit.yoyo.model.car.dto.CarFactoryScopeQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * 
 * @ClassName: CarFactoryScopeController 
 * @Description: 汽车厂商区域
 * @author xiaox
 * @date 2015-3-28 上午10:51:57
 */
@Controller
@RequestMapping("/carFactoryScope")
public class CarFactoryScopeController {
    public static final Logger log = LoggerFactory.getLogger(CarFactoryScopeController.class);
    
    @Autowired
    private RemoteService itemService;

    @RequestMapping("/toCarFactoryScopeIndex")
    public String toCarFactoryScopeIndex(){
    	return "pages/biz/car/carFactoryScopeIndex";
    }
    
    @RequestMapping("/toCarFactoryScopeEdit")
    public String toCarFactoryScopeEdit(HttpServletRequest request)throws Exception{
    	if("edit".equals(request.getParameter("op"))){
    		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010601-01");
    		Integer id=Integer.valueOf(request.getParameter("id"));
    		ResultObject resultObject = itemService.doService(new RequestObject(headObject,id));
    		request.setAttribute("CarFactoryScope", resultObject.getContent());
    	}
    	return "pages/biz/car/carFactoryScopeEdit";
    }
    
    /**
     * 
     * @Description: 获取厂商区域列表  
     * @author xiaox
     * @date 2015-3-28 上午10:52:31
     */
    @RequestMapping("/carFactoryScopeList")
    @ResponseBody
    public Object getCarFactoryScopeList(HttpServletRequest request,CarFactoryScopeQryDTO dto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020114-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,dto));
        return resultObject;
    }
    
    
    /**
     * 
     * @Description: 保存厂商区域
     * @param @param request
     * @author xiaox
     * @date 2015-3-30 上午10:28:45
     */
    @RequestMapping("/insertCarFactoryScope")
    @ResponseBody
    public Object insertCarFactoryScope(HttpServletRequest request,CarFactoryScope carFactoryScope){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020114-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, carFactoryScope));
        	 
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //同一车型的周期名称不存在，可以添加
        		  
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020114-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   
	        	   resultObject = itemService.doService(new RequestObject(headObject, carFactoryScope));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     * @Description: 逻辑删除 
     * @param @param request
     * @param @param id
     * @param @return
     * @author xiaox
     * @date 2015-3-30 上午11:02:26
     */
    @RequestMapping("/deleteCarFactoryScope")
    @ResponseBody
    public Object deleteCarFactoryScope(HttpServletRequest request,@RequestParam(value="id",required=true)Integer [] id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020114-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, id));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     * @Description: 更新 
     * @param @param request
     * @param @param carScope
     * @param @return
     * @author xiaox
     * @date 2015-3-30 上午11:36:08
     */
    @RequestMapping("/updateCarFactoryScope")
    @ResponseBody
    public Object updateCarFactoryScope(HttpServletRequest request,CarFactoryScope carScope){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020114-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   resultObject = itemService.doService(new RequestObject(headObject, carScope));
	       	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020114-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carScope));
	       	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
