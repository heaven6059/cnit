
package com.cnit.yoyo.system.controller;

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
import com.cnit.yoyo.model.member.dto.RolesDTO;
import com.cnit.yoyo.model.system.dto.RoleAddDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 角色管理类
* @author ssd
* @date 2015-4-22 下午3:14:08
 */
@Controller
@RequestMapping("/roleManager")
public class RoleManagerController {
    public static final Logger log = LoggerFactory.getLogger(RoleManagerController.class);
    
    @Autowired
    private RemoteService memberService;

    /**
     * 
    *
    * @Description: 跳转到角色管理首页
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:15:00 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/system/roleManager";
    }
    
    /**
     * 
    *
    * @Description:获取角色列表
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-22 下午3:27:06 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/roleManagerList")
    @ResponseBody
    public Object getRoleManagerList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990103-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
    * @Description: 添加角色
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-22 下午4:59:55 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertRole")
    @ResponseBody
    public Object insertRole(HttpServletRequest request,RoleAddDTO roleAddDTO){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "990103-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = memberService.doService(new RequestObject(headObject, roleAddDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    

    /**
     * 
    *
    * @Description: 删除角色
    * @param @param request
    * @param @param id
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午5:44:11 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public Object deleteRole(HttpServletRequest request,@RequestParam(value="id",required=true)Integer id){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990103-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
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
    * @Description: 更新角色 
    * @param @param request
    * @param @param carYear
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-29 下午7:22:48 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/updateRole")
    @ResponseBody
	public Object updateRole(HttpServletRequest request,RoleAddDTO roleAddDTO) {
		ResultObject resultObject = null;
		try {
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"990103-04", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject, roleAddDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
    
    
    
    
    /**
     * 
     *@description 跳转到角色管理
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-9
     *@return
     */
    @RequestMapping("/shopRole")
    public String shopRole() {
        return "pages/biz/shop/shopRoleIndex";
    }
    /**
     * 
     *@description 店铺角色的管理
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-8
     *@param request
     *@return
     *@throws Exception
     */
    @RequestMapping("/shopRoleList")
    @ResponseBody
    public Object shopRoleList(HttpServletRequest request,RolesDTO dto ,String sort, String order) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990302-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
            sort = "rolesId";
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
        data.put("roles", dto);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    /**
     * 
     *@description 删除
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-9
     *@param request
     *@return
     *@throws Exception
     */
    @RequestMapping("/deleteShopRole")
    @ResponseBody
    public Object deleteShopRole(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990302-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
      
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,ids));
        return resultObject;
    }
  
}
