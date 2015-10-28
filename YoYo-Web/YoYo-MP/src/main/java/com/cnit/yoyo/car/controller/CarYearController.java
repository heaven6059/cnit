
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

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarYear;
import com.cnit.yoyo.model.car.dto.CarYearDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 车系年款
* @author ssd
* @date 2015-4-8 下午7:01:12
 */
@Controller
@RequestMapping("/carYear")
public class CarYearController {
    public static final Logger log = LoggerFactory.getLogger(CarYearController.class);
    
    @Autowired
    private RemoteService itemService;

    /**
     * 
    *
    * @Description: 进入年款主页 
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:27:55 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carYearIndex";
    }
    
    /**
     * 
     * @Description: 获取车型列表  
     * @author ssd
     */
    @RequestMapping("/carYearList")
    @ResponseBody
    public Object getCarYearList(HttpServletRequest request,String sort, String order,CarYearDTO carYearDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020119-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
			sort = "t_car_year.id";
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
        data.put("carYearDTO", carYearDTO);
        data.put("sort", sort);
        data.put("order", order);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    /**
     * 
    *
    * @Description: 获取车系信息,用来做页面的下拉框 
    * @param @param request
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:28:19 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/findSelect")
    @ResponseBody
    public Object findSelect(HttpServletRequest request,@RequestParam(value="deptId",required=false) Integer deptId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020119-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject,deptId));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    /**
     * 
    * @Title: insertCarType 
    * @Description: 插入车型信息，插入之前会根据车型名称进行判断重复
    * @param @param request
    * @param @param carTypeDTO
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-7 上午11:13:31 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertCarYear")
    @ResponseBody
    public Object insertCarYear(HttpServletRequest request,CarYear carYear){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "2000020119-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = itemService.doService(new RequestObject(headObject, carYear));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

    /**
     * 
    * @Title: deleteCarType 
    * @Description: 删除车型信息
    * @param @param request
    * @param @param id
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-7 下午2:40:18 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/deleteCarYear")
    @ResponseBody
    public Object deleteCarYear(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020119-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, ids));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     *
    * @Title: updateCarType 
    * @Description: 更新车型信息，更新前先根据车型名称查询是否有重复 
    * @param @param request
    * @param @param carTypeDTO
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-7 下午2:39:29 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateCarYear")
    @ResponseBody
	public Object updateCarYear(HttpServletRequest request,CarYear carYear) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"2000020119-05", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = itemService.doService(new RequestObject(headObject, carYear));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
  
}
