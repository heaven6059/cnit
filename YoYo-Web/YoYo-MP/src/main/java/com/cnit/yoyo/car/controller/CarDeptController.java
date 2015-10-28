
package com.cnit.yoyo.car.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarDept;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 汽车车系
* @author ssd
* @date 2015-4-8 下午1:22:49
 */
@Controller
@RequestMapping("/carDept")
public class CarDeptController {
    public static final Logger log = LoggerFactory.getLogger(CarDeptController.class);
    
    @Autowired
    private RemoteService itemService;

  
    
    /**
     * 
    * @Description: 跳转到车系首页
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-8 下午1:19:13 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carDeptIndex";
    }
    
    /**
     * 
    *
    * @Description: 查询主键和名称作为页面的select标签使用
    * @param @param request
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-8 下午5:03:02 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/findSelect")
    @ResponseBody
    public Object findSelect(HttpServletRequest request,@RequestParam(value="factoryId",required=false) Integer factoryId,
    		@RequestParam(value="gradeId",required=false) Integer gradeId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	 if(null == factoryId) {
        		 factoryId = -1;
        	 }
        	 if(null == gradeId) {
        		 gradeId = -1;
        	 }
        	 Map<String, Object> data = new HashMap<String, Object>();
        	 data.put("factoryId", factoryId);
        	 data.put("gradeId", gradeId);
        	 resultObject = itemService.doService(new RequestObject(headObject,data));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    
    /**
     * 
    *
    * @Description: 获取车系列表
    * @param @param request
    * @param @param carTypeDTO
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-8 下午1:29:15 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/carDeptList")
    @ResponseBody
    public Object getCarDeptList(HttpServletRequest request, String sort, String order,CarDept carDept) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
			sort = "t_car_dept.carDeptId";
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
        data.put("carDept", carDept);
        data.put("sort", sort);
        data.put("order", order);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        return resultObject;
    }
    
    
    /**
     * 
    *
    * @Description: 插入车系信息 
    * @param @param request
    * @param @param carTypeDTO
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-8 下午2:24:47 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertCarDept")
    @ResponseBody
    public Object insertCarDept(HttpServletRequest request,CarDept carDept){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = itemService.doService(new RequestObject(headObject, carDept));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

   /**
    * 
   *
   * @Description: 删除车系信息 
   * @param @param request
   * @param @param id
   * @param @return    设定文件 
   * @author ssd
   * @date 2015-4-8 下午2:31:09 
   * @return Object    返回类型 
   * @throws
    */
    @RequestMapping("/deleteCarDept")
    @ResponseBody
    public Object deleteCarDept(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
    *
    * @Description: 更新车系信息
    * @param @param request
    * @param @param carDept
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-8 下午3:49:07 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateCarDept")
    @ResponseBody
    public Object updateCarDept(HttpServletRequest request,CarDept carDept){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = itemService.doService(new RequestObject(headObject, carDept));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
