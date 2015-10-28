
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

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.car.CarBrandGrade;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 汽车级别
* @author ssd
* @date 2015-4-8 下午2:06:17
 */
@Controller
@RequestMapping("/carBrandGrade")
public class CarBrandGradeController {
    public static final Logger log = LoggerFactory.getLogger(CarBrandGradeController.class);
    
    @Autowired
    private RemoteService itemService;
    
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/car/carGradeIndex";
    }

    /**
     * 获取汽车级别信息
     * @param @param request
     * @param @return
     * @author ssd
     */
    @RequestMapping("/findSelect")
    @ResponseBody
    public Object findSelect(HttpServletRequest request){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020118-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
   
    
    /**
     * 
    *
    * @Description: 获取汽车级别
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-8 下午2:52:03 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/carBrandGradeList")
    @ResponseBody
    public Object getCarBrandGradeList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020118-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    
    /**
     * 
     * @Description: 保存汽车级别
     * @param @param request
     * @author ssd
     * @date 2015-3-30 上午10:28:45
     */
    @RequestMapping("/insertCarBrandGrade")
    @ResponseBody
    public Object insertCarBrandGrade(HttpServletRequest request,CarBrandGrade carBrandGrade){
    	 ResultObject resultObject = null;
         try {
        		   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020118-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, carBrandGrade));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     * @Description: 逻辑删除汽车级别 
     * @param @param request
     * @param @param id
     * @param @return
     * @author ssd
     */
    @RequestMapping("/deleteCarBrandGrade")
    @ResponseBody
    public Object deleteCarBrandGrade(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020118-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
     * @Description: 更新 汽车级别
     * @param @param request
     * @param @param carScope
     * @param @return
     * @author ssd
     */
    @RequestMapping("/updateCarBrandGrade")
    @ResponseBody
    public Object updateCarBrandGrade(HttpServletRequest request,CarBrandGrade carBrandGrade){
    	 ResultObject resultObject = null;
         try {
	       	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020118-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        resultObject = itemService.doService(new RequestObject(headObject, carBrandGrade));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
