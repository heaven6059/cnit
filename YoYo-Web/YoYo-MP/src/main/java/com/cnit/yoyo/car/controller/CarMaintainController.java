
package com.cnit.yoyo.car.controller;

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

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.dto.CarMaintainDTO;
import com.cnit.yoyo.model.car.dto.CarTypeDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @ClassName: CarMaintainController 
 * @Description: 保养周期管理
 * @author xiaox
 * @date 2015-3-26 下午4:12:19
 */
@Controller
@RequestMapping("/carMaintain")
public class CarMaintainController {
    public static final Logger log = LoggerFactory.getLogger(CarMaintainController.class);
    
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
        return "pages/biz/car/carMaintainIndex";
    }
    
    /**
     * @description 映射至保养周期管理首页
     * @detail <方法详细描述>
     * @version 1.0.0
     * @data 2015年3月21日
     * @return
     */
    @RequestMapping("/toCarMaintainEdit")
    public String toCarMaintainEdit(HttpServletRequest request,CarMaintainDTO carMaintainDTO)throws Exception {
    	HeadObject headObject =null;
    	if(null!=carMaintainDTO.getMaintainId()&&carMaintainDTO.getMaintainId()>0){
    		headObject=CommonHeadUtil.geneHeadObject(request, "010803-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    		ResultObject resultObject = itemService.doService(new RequestObject(headObject, carMaintainDTO.getMaintainId()));
    		request.setAttribute("carMaintain", resultObject.getContent());
    	}else{
    		headObject=CommonHeadUtil.geneHeadObject(request, "010301-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    		ResultObject resultObject = itemService.doService(new RequestObject(headObject,carMaintainDTO.getCarId()));
    		CarTypeDTO carTypeDTO=(CarTypeDTO)resultObject.getContent();
    		carMaintainDTO.setCarBrand(carTypeDTO.getBrandId());
    		carMaintainDTO.setCarFactory(carTypeDTO.getFactoryId());
    		carMaintainDTO.setCarDept(carTypeDTO.getDeptId());
    		request.setAttribute("carMaintain", carMaintainDTO);
    	}
        return "pages/biz/car/carMaintainEdit";
    }
    
  /**
   * 
   * @Title: getCarMaintainList 
   * @Description: 获取保养周期列表
   * @param @param request
   * @author xiaox
   * @date 2015-3-26 下午4:13:58
   */
    @RequestMapping("/carMaintainList")
    @ResponseBody
    public Object getCarMaintainList(HttpServletRequest request, CarMaintainDTO carMaintainDto) throws Exception {
        log.info("start[CarMaintainController.getCarMaintainList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carMaintainDto", carMaintainDto);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        log.info("end[CarMaintainController.getCarMaintainList]");
        return resultObject;
    }
    
    /**
     * 
     * @Title: insertCarMaintain 
     * @Description: 插入
     * @param @param request
     * @param @param carMaintainDto
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-27 下午2:14:37
     */
    @RequestMapping("/insertCarMaintain")
    @ResponseBody
    public Object insertCarMaintain(HttpServletRequest request,CarMaintainDTO carMaintainDto){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, carMaintainDto));
        	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //同一车型的周期名称不存在，可以添加
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carMaintainDto));
        	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     * @Title: insertBrand 
     * @Description: 删除
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-27 上午9:49:24
     */
    @RequestMapping("/deleteCarMaintain")
    @ResponseBody
    public Object deleteBrand(HttpServletRequest request,@RequestParam(value="carMaintainIds[]",required=true)Integer [] carMaintainIds){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, carMaintainIds));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     * @Title: updateCarMaintain 
     * @Description: 更新 
     * @param @param request
     * @param @param carMaintainDto
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-27 下午3:26:29
     */
    @RequestMapping("/updateCarMaintain")
    @ResponseBody
    public Object updateCarMaintain(HttpServletRequest request,CarMaintainDTO carMaintainDto){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   resultObject = itemService.doService(new RequestObject(headObject, carMaintainDto));
	       	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carMaintainDto));
	       	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    /**
     * 
     * @Title: updateMaintainCate 
     * @Description: 更新保养周期与保养分类的关系
     * @param @param categoryIds 分类id集合
     * @param @param maintainId  保养周期的id
     * @author xiaox
     * @date 2015-3-27 下午5:30:07
     */
    @RequestMapping("/updateMaintainCate")
    @ResponseBody
    public Object updateMaintainCate(HttpServletRequest request,@RequestParam(value="categoryIds[]",required=true)Integer [] categoryIds,
    		@RequestParam(value="maintainId" ,required=true)Integer maintainId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   Map<String, Object> data = new HashMap<String, Object>();
               data.put("categoryIds",categoryIds);
               data.put("maintainId", maintainId);
	       	   resultObject = itemService.doService(new RequestObject(headObject, data));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    @ResponseBody
    @RequestMapping("/findMaintainShip")
    public Object findMaintainShip(HttpServletRequest request,@RequestParam(value="maintainId" ,required=true)Integer maintainId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020111-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
	       	   resultObject = itemService.doService(new RequestObject(headObject, maintainId));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
      * @description <b>查询汽车品牌</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-5-11
      * @param @param request
      * @param @return
      * @return Object
    */
    @ResponseBody
    @RequestMapping("/findCarBrand")
    public Object findCarBrand(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010701-01");
         try {
        	 Map<String, Object> params=new HashMap<String,Object>();
        	 params.put("identifier", identifier);
        	 ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
        	 return resultObject.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
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
     * @description <b>根据厂商查车系</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-11
     * @param @param request
     * @param @return
     * @return Object
   */
   @ResponseBody
   @RequestMapping("/findCarDept")
   public Object findCarDept(HttpServletRequest request,@RequestParam(value="factoryId" ,required=true)Integer factoryId){
   	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010802-06");
        try {
       	 ResultObject resultObject = itemService.doService(new RequestObject(headObject, factoryId));
       	 return resultObject.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
   }
   
   /**
    * @description <b>根据品牌查厂商</b>
    * @author 王鹏
    * @version 1.0.0
    * @data 2015-5-11
    * @param @param request
    * @param @return
    * @return Object
  */
  @ResponseBody
  @RequestMapping("/findCarFactory")
  public Object findCarFactory(HttpServletRequest request,@RequestParam(value="brandId" ,required=true)Integer brandId){
  	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010801-07");
       try {
      	 ResultObject resultObject = itemService.doService(new RequestObject(headObject, brandId));
      	 return resultObject.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  }
  
  /**
   * @description <b></b>
   * @author 王鹏
   * @version 1.0.0
   * @data 2015-5-11
   * @param @param request
   * @param @return
   * @return Object
 */
  	@ResponseBody
 	@RequestMapping("/findMaintainCategory")
  	public Object findMaintainCategory(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
  		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010401-05");
  		try {
  			ResultObject resultObject = itemService.doService(new RequestObject(headObject, identifier));
  			return resultObject.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  	}
  	
	/**
	 * 查询官方保养信息
	 * @param request
	 * @param identifier
	 * @return
	 */
	@ResponseBody
   	@RequestMapping("/findOfficialMaintainCategory")
	public Object findOfficialMaintainCategory(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010401-07");
		try {
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, identifier));
			return resultObject.getContent();
  		} catch (Exception e) {
  			e.printStackTrace();
  			return null;
  		}
	}
	
	/**
	 * 查询自选保养信息
	 * @param request
	 * @param identifier
	 * @return
	 */
	@ResponseBody
  	@RequestMapping("/findOptionalMaintainCategory")
	public Object findOptionalMaintainCategory(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010401-08");
		try {
			ResultObject resultObject = itemService.doService(new RequestObject(headObject, identifier));
			return resultObject.getContent();
 		} catch (Exception e) {
 			e.printStackTrace();
 			return null;
 		}
	}
    	
  	
    /**
     * @description <b></b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-11
     * @param @param request
     * @param @return
     * @return Object
   */
    	@ResponseBody
   	@RequestMapping("/findMaintainCategoryTree")
    	public Object findMaintainCategoryTree(HttpServletRequest request,@RequestParam(value="identifier" ,required=true)String identifier){
    		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010401-06");
    		try {
    			ResultObject resultObject = itemService.doService(new RequestObject(headObject, identifier));
    			return resultObject.getContent();
  		} catch (Exception e) {
  			e.printStackTrace();
  			return null;
  		}
    }
  	
 
	/**
	  * @description <b>查询具体车型的保养信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-12
	  * @param @param request
	  * @param @param identifier
	  * @param @return
	  * @return Object
	*/
	@ResponseBody
 	@RequestMapping("/findMaintainByCar")
  	public Object findMaintainByCar(HttpServletRequest request,@RequestParam(value="carId" ,required=true)Integer carId){
  		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010803-01");
  		try {
  			ResultObject resultObject = itemService.doService(new RequestObject(headObject, carId));
  			return resultObject.getContent();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
  	}
  
}
