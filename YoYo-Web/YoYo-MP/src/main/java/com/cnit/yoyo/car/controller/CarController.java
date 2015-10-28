
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
import com.cnit.yoyo.model.car.dto.CarTypeDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

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
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carTypeIndex";
    }
    
    /**
     * 
     * @Description: 根据车系ID获取车型列表
     */
      @RequestMapping("/getCarByDeptId")
      @ResponseBody
      public Object getCarByDeptId(HttpServletRequest request,@RequestParam(value="deptId",required=false) Integer deptId) throws Exception {
          HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
          
          ResultObject resultObject = itemService.doService(new RequestObject(headObject,deptId));
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
     * 
     * @Description: 获取车型列表  
     * @author ssd
     */
    @RequestMapping("/carTypeList")
    @ResponseBody
    public Object getCarTypeList(HttpServletRequest request,String sort, String order,CarTypeDTO carTypeDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
			sort = "t_car.lastUpdate";
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
        data.put("carTypeDTO", carTypeDTO);
        data.put("sort", sort);
        data.put("order", order);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    /**
     * 
    * @Title: carDetailList 
    * @Description: 查询车型详情
    * @param @param request
    * @param @param carTypeDTO
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-7 下午3:23:20 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public Object getCarDetail(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,id));
        return resultObject;
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
    @RequestMapping("/insertCarType")
    @ResponseBody
    public Object insertCarType(HttpServletRequest request,CarTypeDTO carTypeDTO){
    	 //HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	 //carTypeDTO.setCarName(carTypeDTO.getCarYearValue()+"-"+carTypeDTO.getCarDeptName()+"-"+carTypeDTO.getCargearbox()+"-"+carTypeDTO.getGradename());
        	  // resultObject = remoteService.doService(new RequestObject(headObject, carTypeDTO));
        	 
        	   //if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //同一汽车厂商名称不存在，可以添加
        		  
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   
	        	   resultObject = itemService.doService(new RequestObject(headObject, carTypeDTO));
        	   //}
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
    @RequestMapping("/deleteCarType")
    @ResponseBody
    public Object deleteCarType(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
    @RequestMapping("/updateCarType")
    @ResponseBody
    public Object updateCarType(HttpServletRequest request,CarTypeDTO carTypeDTO){
    	 //HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   //resultObject = remoteService.doService(new RequestObject(headObject, carTypeDTO));
	       	   //if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
        	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020112-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carTypeDTO));
	       	  // }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
