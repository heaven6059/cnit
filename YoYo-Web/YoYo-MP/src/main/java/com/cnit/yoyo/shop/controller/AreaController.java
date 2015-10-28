package com.cnit.yoyo.shop.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.model.goods.Area;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @description 地区访问控制
 * @version 1.0.0
 */
@Controller
@RequestMapping("/areaController")
public class AreaController {

	  @Autowired 
	  private RemoteService itemService;
    
    
    @RequestMapping("/find")
    public Object find(HttpServletRequest request,Area area) throws Exception {
    	  HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020104-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	  
          return itemService.doService(new RequestObject(headObject, area));
    }

   
    
}
