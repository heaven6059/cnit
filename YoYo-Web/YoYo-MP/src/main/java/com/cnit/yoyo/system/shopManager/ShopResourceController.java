
package com.cnit.yoyo.system.shopManager;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
import com.cnit.yoyo.model.system.dto.SiteResourceAddDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 卖家权限管理类
* @author ssd
* @date 2015-4-22 下午3:14:08
 */
@Controller
@RequestMapping("/shopResource")
public class ShopResourceController {
    public static final Logger log = LoggerFactory.getLogger(ShopResourceController.class);
    
    @Autowired
    private RemoteService memberService;

    /**
     * 
    *
    * @Description: 跳转到卖家资源管理首页
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:15:00 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/resourceIndex")
    public String resourceIndex() {
        return "pages/biz/system/shopManager/shopResource";
    }
    
    
    /**
     * 
    *
    * @Description:获取卖家资源列表
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:27:06 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/shopResourceList")
    @ResponseBody
    public Object getShopResourceList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990303-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    @RequestMapping("/findSelect")
    @ResponseBody
    public Object findSelect(HttpServletRequest request){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "3000020103-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = memberService.doService(new RequestObject(headObject));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    @RequestMapping("/resourceTreeCombox")
    @ResponseBody
    private Object getResourceTree(HttpServletRequest request) throws Exception {
        String parentId = request.getParameter("parentId");
        JSONObject params = new JSONObject();
        if (StringUtil.isEmpty(parentId)) {
            params.put("parentId", 0);
        } else {
            params.put("parentId", Integer.parseInt(parentId));
        }
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990102-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, params));
        //return JSONArray.fromObject(resultObject.getContent());
        return resultObject;
    }
    
    @RequestMapping("/resourceTree")
    @ResponseBody
    private Object getTree(HttpServletRequest request,@RequestParam(value="roleId",required=false)Integer roleId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990303-06", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,roleId));
        return JSONArray.fromObject(resultObject.getContent());
    }
    
    
    /**
     * 
    *
    * @Description: 添加用户
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午4:59:55 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertShopResource")
    @ResponseBody
    public Object insertShopResource(HttpServletRequest request,SiteResourceAddDTO siteResourceAddDTO){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "990303-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = memberService.doService(new RequestObject(headObject, siteResourceAddDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

    /**
     * 
    *
    * @Description: 删除资源
    * @param @param request
    * @param @param id
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 上午9:33:57 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/deleteShopResource")
    @ResponseBody
    public Object deleteShopResource(HttpServletRequest request,@RequestParam(value="id",required=true)Long id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990303-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = memberService.doService(new RequestObject(headObject, id));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
    *
    * @Description: 更新资源
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 上午11:08:34 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateShopResource")
    @ResponseBody
	public Object updateShopResource(HttpServletRequest request,SiteResourceAddDTO siteResourceAddDTO) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"990303-04", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject, siteResourceAddDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
  
}
