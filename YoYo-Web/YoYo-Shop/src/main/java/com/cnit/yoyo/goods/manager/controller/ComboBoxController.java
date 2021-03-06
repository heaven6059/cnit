/**
 * 文 件 名   :  ComboBoxController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-3 下午5:04:45
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.goods.manager.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 下拉数据
 * @detail 页面下拉数据查询类
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/comboBox")
public class ComboBoxController {
    public static final Logger log = LoggerFactory.getLogger(ComboBoxController.class);
    @Autowired
    private RemoteService itemService;

    /**
     * @description 会员等级下拉列表
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-3
     * @return
     */
    public Object memberLevelComboBox(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        String temp = request.getParameter("page");
        data.put("pageIndex", StringUtil.isEmpty(temp) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(temp));
        temp = request.getParameter("rows");
        data.put("pageSize", StringUtil.isEmpty(temp) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(temp));
        temp = request.getParameter("type");
        data.put("type", StringUtil.isEmpty(temp) ? GlobalStatic.DEFAULT_PAGE_SIZE : temp);
        return commonRmiRequest(request, null, data);
    }

    /**
     * @description 远程方法调用通用方法
     * @detail 只需传入请求、服务码、查询参数
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-3
     * @param request
     * @param serviceCode
     * @param data
     * @return
     */
    private Object commonRmiRequest(HttpServletRequest request, String serviceCode, Object data) {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, serviceCode, GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Object returnObject = null;
        try {
            ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
            if (ErrorCode.SUCCESS.equalsIgnoreCase(resultObject.getRetCode())) {
                returnObject = resultObject.getContent();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnObject;
    }

    @RequestMapping("/cateTreeCombox")
    @ResponseBody
    public Object getCateTree(HttpServletRequest request) throws Exception {
        log.info("start[ComboBoxController.getCateTree]");
        String parentCatId = request.getParameter("parentCatId");
        JSONObject params = new JSONObject();
        if (StringUtil.isEmpty(parentCatId)) {
            params.put("parentCatId", 0);
        } else {
            params.put("parentCatId", Integer.parseInt(parentCatId));
        }
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
        log.info("end[ComboBoxController.getCateTree]");
        return JSONArray.fromObject(resultObject.getContent());
    }

    @RequestMapping("/virtCateTreeCombox")
    @ResponseBody
    public Object getVirtCateTree(HttpServletRequest request) throws Exception {
        log.info("start[ComboBoxController.getVirtCateTree]");
        String parentCatId = request.getParameter("parentCatId");
        JSONObject params = new JSONObject();
        if (StringUtil.isEmpty(parentCatId)) {
            params.put("parentCatId", 0);
        } else {
            params.put("parentCatId", Integer.parseInt(parentCatId));
        }
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-06", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
        log.info("end[ComboBoxController.getVirtCateTree]");
        return JSONArray.fromObject(resultObject.getContent());
    }
}
