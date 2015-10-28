package com.cnit.yoyo.shop.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.model.goods.dto.ClerksDTO;
import com.cnit.yoyo.model.member.Clerks;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;
/**
 * 
 * @ClassName: ClerkManagController 
 * @Description: 店员管理 
 * @author xiaox
 * @date 2015-3-20 下午2:26:31
 */
@Controller
@RequestMapping("/clerkManager")
public class ClerkManagerController {
    
	@Autowired
	private RemoteService memberService;
    
    
    /**
     * @Description: 店员管理
     * @param @return    设定文件 
     * @author xiaox
     * @date 2015-3-20 下午2:13:32
     */
    @RequestMapping("findClerk")
    public String findClerk(HttpServletRequest request,Map<String, Object> map){
    	//TODO 从session中判断是集团，还是单店
        String gradeType = (String) CommonUtil.getSession(request).getAttribute("gradeType");
        
    	map.put("companyType", gradeType); 
    	//map.put("companyType", GlobalStatic.SHOP_TYPE_UNIT);  //单店
        return "pages/shopMng/clerkManager";
    }
    
    
    @RequestMapping("/clerkInfoInsert")
    @ResponseBody
    public Object clerkInfoInsert(HttpServletRequest request,ClerksDTO clerksDTO)throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020107-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
          
        return memberService.doService(new RequestObject(headObject,clerksDTO));
    }
    
    /**
     * 
     *@description 判断用户名是否存在或是否为店员
     *@detail <方法详细描述>
     *@author <a href="liguanghua@chinacnit.com">李光华</a>
     *@version 1.0.0
     *@data 2015-3-17
     *@param request
     *@return
     *@throws Exception
     */
    
    @RequestMapping("/clerkLoginName")
    @ResponseBody
    public Object clerkLoginName(HttpServletRequest request,@RequestParam(value="loginName",required=true) String loginName)throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String,Object> data = new HashMap<String,Object> ();
        data.put("loginName", loginName);
        return memberService.doService(new RequestObject(headObject,data));
    }
    
    
    
    @RequestMapping("/clerkInfoSelect")
    @ResponseBody
    public Object clerkInfoSelect(HttpServletRequest request)throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020107-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page"); 
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        data.put("companyId",Long.valueOf(companyId));
        data.put("storeId",Long.valueOf(storeId));
        return memberService.doService(new RequestObject(headObject,data)) ;
    }
    
    @RequestMapping("/clerkInfoDelete")
    @ResponseBody
    public Object clerkInfoDelete(HttpServletRequest request)throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020107-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();  
        data.put("attachId", request.getParameter("attachId"));   
        return memberService.doService(new RequestObject(headObject,data));
    }
    
    /**
     * 
     * @Title: clerkInfoUpdate 
     * @Description: 修改店员的角色或分店id
     * @param @param request
     * @param @param clerk
     * @param @return
     * @param @throws Exception    设定文件 
     * @author xiaox
     * @date 2015-3-21 上午9:13:09
     */
    @RequestMapping("/clerkInfoUpdate")
    @ResponseBody
    public Object clerkInfoUpdate(HttpServletRequest request,Clerks clerk)throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020107-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
       
        return memberService.doService(new RequestObject(headObject,clerk));
    }
}
