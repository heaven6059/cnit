/**
 * 文 件 名   :  SignController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-22 下午2:57:27
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

import com.cnit.yoyo.member.service.SignService;


/**
 * 买家中心
* @author ssd
* @date 2015-4-9 下午8:04:45
 */
@Controller
@RequestMapping("/memberManager")
public class MemberManagerController {
    @Autowired
    private SignService signService;

    /**
     * 
    *
    * @Description: 买家中心主页
    * @param @param request
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-9 下午8:04:24 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/manager")
    public String memberManager(HttpServletRequest request) throws Exception {
        return "pages/manager/memberManager";
    }
}  
	   
