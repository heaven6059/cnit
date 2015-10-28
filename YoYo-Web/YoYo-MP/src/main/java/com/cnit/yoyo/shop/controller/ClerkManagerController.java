package com.cnit.yoyo.shop.controller;

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
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.ClerksDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
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
    
	
	
	@RequestMapping("/shopClerk")
    public String shopRole() {
        return "pages/biz/shop/shopClerksIndex";
    }
	/**
	 * 
	 *@description 店员列表
	 *@detail <方法详细描述>
	 *@author <a href="xiaoxiang@cnit.com">肖湘</a>
	 *@version 1.0.0
	 *@data 2015-6-9
	 *@param request
	 *@param dto
	 *@return
	 *@throws Exception
	 */
	@RequestMapping("/shopClerksList")
    @ResponseBody
    public Object shopClerksList(HttpServletRequest request, ClerksDTO dto) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020107-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("clerk", dto);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, data));
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
    @RequestMapping("/deleteShopClerk")
    @ResponseBody
    public Object deleteShopClerk(HttpServletRequest request,@RequestParam(value="ids[]",required=true)Integer[] ids) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020107-06", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,ids));
        return resultObject;
    }
}
