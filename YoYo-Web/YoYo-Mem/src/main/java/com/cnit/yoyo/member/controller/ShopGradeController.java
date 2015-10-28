package com.cnit.yoyo.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.member.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @description 店铺等级访问控制
 * @version 1.0.0
 */
@Controller
@RequestMapping("/shopGradeController")
public class ShopGradeController {

    @Autowired
    private SignService signService;
    
    
    @RequestMapping("/find")
    public Object find(HttpServletRequest request,@RequestParam(value = "type", required = true) String type) throws Exception {
    	  HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020106-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	  Map<String, String> dataMap = new HashMap<String, String>();
          dataMap.put("type", type);
          return signService.doCommon(headObject, dataMap);
    }

   
    
}
