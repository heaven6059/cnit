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

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.model.member.MemberLevel;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

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

    @RequestMapping("/")
    public String toMemberLevelMng() {
        return "pages/biz/memberMng/memberLevelList";
    }

    /**
     * @description 会员等级列表查询
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-11
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/memberLevels")
    @ResponseBody
    public Object getMemberLevelList(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        return memberService.doService(new RequestObject(headObject, data));
    }
    
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


    /**
     * @description 新增会员等级
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-11
     * @param request
     * @param memberLevel
     * @return
     * @throws Exception
     */
    @RequestMapping("/newMemberLevel")
    @ResponseBody
    public Object saveOrUpdateMemberLevel(HttpServletRequest request) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-14", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        MemberLevel memberLevel = new MemberLevel();
        String temp = request.getParameter("memberLvId");
        if (StringUtil.isEmpty(temp)) {
            memberLevel.setMemberLvId(null);
        } else {
            memberLevel.setMemberLvId(Integer.parseInt(temp));
        }
        memberLevel.setName(request.getParameter("name"));
        memberLevel.setType(request.getParameter("type"));
        memberLevel.setIsDefault(request.getParameter("isDefault"));
        temp = request.getParameter("discountRate");
        if (StringUtil.isEmpty(temp)) {
            memberLevel.setDiscountRate(null);
        } else {
            memberLevel.setDiscountRate(new BigDecimal(temp));
        }
        temp = request.getParameter("point");
        if (StringUtil.isEmpty(temp)) {
            memberLevel.setPoint(null);
        } else {
            memberLevel.setPoint(new BigDecimal(temp));
        }
        temp = request.getParameter("experience");
        if (StringUtil.isEmpty(temp)) {
            memberLevel.setExperience(null);
        } else {
            memberLevel.setExperience(new BigDecimal(temp));
        }
        if (memberLevel.getMemberLvId() == null) {
            data.put("opType", GlobalStatic.DATA_OP_INSERT);
        } else {
            data.put("opType", GlobalStatic.DATA_OP_MODIFY);
        }
        data.put("data", memberLevel);
        return memberService.doService(new RequestObject(headObject, data));
    }

    /**
     * @description 删除会员等级（逻辑删除）
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-11
     * @param request
     * @param memberLevel
     * @return
     * @throws Exception
     */
    @RequestMapping("/deleteMemberLevel")
    @ResponseBody
    public Object deleteMemberLevel(HttpServletRequest request, String memberLvId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020102-15", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        return memberService.doService(new RequestObject(headObject, memberLvId));
    }
}
