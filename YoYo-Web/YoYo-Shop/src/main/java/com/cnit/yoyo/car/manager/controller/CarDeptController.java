
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
    public Object findSelect(HttpServletRequest request,@RequestParam(value="factoryId",required=false) Integer factoryId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
             if(null == factoryId) {
                 factoryId = -1;
             }
             Map<String, Object> data = new HashMap<String, Object>();
             data.put("factoryId", factoryId);
             data.put("gradeId", ""); //为了匹配service
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
    public Object getCarDeptList(HttpServletRequest request,CarDept carDept) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020117-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carDept", carDept);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    
   
  
}
