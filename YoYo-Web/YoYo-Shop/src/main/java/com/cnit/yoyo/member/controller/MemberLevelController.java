/**
 * 文 件 名   :  MemberLevelController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-4 下午4:55:08
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @description 会员等级管理
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/memberLevel")
public class MemberLevelController {
    @Autowired
    private RemoteService memberService;

  
    /**
     * @description 会员等级列表查询
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-11
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Object findAll(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-27", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
      
        return memberService.doService(new RequestObject(headObject));
    }

   
}
