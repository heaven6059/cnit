package com.cnit.yoyo.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.member.service.SignService;
import com.cnit.yoyo.util.CommonHeadUtil;

public class AccountController {
    @Autowired
    private SignService signService;
    
    @RequestMapping("/find")
    @ResponseBody
    public Object findLoginName(HttpServletRequest request,String loginName)throws Exception{
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020109-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        return signService.doCommon(headObject,loginName );
    }
}
