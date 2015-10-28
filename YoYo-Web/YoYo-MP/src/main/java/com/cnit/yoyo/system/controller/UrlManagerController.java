
package com.cnit.yoyo.system.controller;

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
import com.cnit.yoyo.model.system.UrlFilter;
import com.cnit.yoyo.myshiro.system.service.UrlFilterService;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 路径信息管理类
* @author ssd
* @date 2015-4-22 下午3:14:08
 */
@Controller
@RequestMapping("/urlManager")
public class UrlManagerController {
    public static final Logger log = LoggerFactory.getLogger(UrlManagerController.class);
    
    @Autowired
    private RemoteService memberService;
    
    @Autowired
    private UrlFilterService urlFilterService;

    /**
     * 
    *
    * @Description: 跳转到用户管理首页
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:15:00 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/system/urlManager";
    }
    
    /**
     * 
    *
    * @Description:获取路径信息列表
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:27:06 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/urlManagerList")
    @ResponseBody
    public Object getUrlManagerList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990105-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    
    
    /**
     * 
    *
    * @Description: 添加路径信息
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午4:59:55 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertUrl")
    @ResponseBody
    public Object insertUrl(HttpServletRequest request,UrlFilter urlFilter){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "990105-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = memberService.doService(new RequestObject(headObject, urlFilter));
        	 urlFilterService.createUrlFilter();
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

    /**
     * 
    *
    * @Description: 删除路径信息
    * @param @param request
    * @param @param id
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午5:44:11 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/deleteUrl")
    @ResponseBody
    public Object deleteUrl(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990105-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = memberService.doService(new RequestObject(headObject, id));
        	   urlFilterService.deleteUrlFilter();
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
    *
    * @Description: 更新路径信息
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午7:22:48 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateUrl")
    @ResponseBody
	public Object updateUrl(HttpServletRequest request,UrlFilter urlFilter) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"990105-03", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject, urlFilter));
			urlFilterService.updateUrlFilter();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
  
}
