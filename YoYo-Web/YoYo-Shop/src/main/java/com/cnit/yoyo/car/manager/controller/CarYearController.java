
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
     * @Description: 获取车型列表  
     * @author ssd
     */
    @RequestMapping("/carYearList")
    @ResponseBody
    public Object getCarYearList(HttpServletRequest request,CarYearDTO carYearDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020119-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carYearDTO", carYearDTO);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
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
    
    
   
  
}
