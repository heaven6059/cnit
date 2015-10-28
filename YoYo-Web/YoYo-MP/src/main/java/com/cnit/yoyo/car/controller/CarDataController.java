
package com.cnit.yoyo.car.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.dto.CarDataDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @ClassName: CarDataController 
 * @Description: 汽车数据项 
 * @author xiaox
 * @date 2015-3-31 上午10:56:41
 */
@Controller
@RequestMapping("/carData")
public class CarDataController {
    public static final Logger log = LoggerFactory.getLogger(CarDataController.class);
    
    @Autowired
    private RemoteService itemService;

    
    
  
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carDataIndex";
    }
    
  
    /**
     * 
     * @Description: 获取数据项列表 
     * @author xiaox
     * @date 2015-3-31 上午11:06:19
     */
    @RequestMapping("/carDataList")
    @ResponseBody
    public Object getCarDataList(HttpServletRequest request,CarDataDTO carData,String sort, String order) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
            sort = "dataId";
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
        data.put("sort", sort);
        data.put("order", order);
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carData", carData);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    /**
     * 
    *
    * @Description: 查询所有车型数据项，用于编辑数据项的值
    * @param @param request
    * @param @param model
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-5-8 下午4:20:15 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/carDataEditList")
    public String carDataEditList(HttpServletRequest request,Model model,@RequestParam(value="carId",required=true)Integer carId,@RequestParam(value="isconfig",required=true)Integer isconfig) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "011001-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("carId", carId);
        data.put("isconfig", isconfig);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        model.addAttribute("carData", resultObject.getContent());
        model.addAttribute("carId", carId);
        model.addAttribute("isconfig", isconfig);
        return "pages/biz/car/carConfiginfoIndex";
    }
    
    
    @RequestMapping("/insertCarData")
    @ResponseBody
    public Object insertCarData(HttpServletRequest request,CarDataDTO carData){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, carData));
        	 
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //同一名称不存在，可以添加
        		  
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   
	        	   resultObject = itemService.doService(new RequestObject(headObject, carData));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    /**
     * 
     * @Description: 逻辑删除： 
     * @param @param request
     * @param @param id ：数据项的id
     * @param @return
     * @author xiaox
     * @date 2015-3-31 下午7:41:42
     */
    @RequestMapping("/deleteCarData")
    @ResponseBody
    public Object deleteCarData(HttpServletRequest request,@RequestParam(value="id[]",required=true)Integer[] id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	  resultObject = itemService.doService(new RequestObject(headObject, id));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    
    @RequestMapping("/updateCarData")
    @ResponseBody
    public Object updateCarData(HttpServletRequest request,CarDataDTO carData){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   resultObject = itemService.doService(new RequestObject(headObject, carData));
	       	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020115-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carData));
	       	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
