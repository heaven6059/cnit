/**
 * 文 件 名   :  SpecController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-3-18 下午3:49:34
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.goods.manager.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.spec.SpecQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * @description <一句话功能简述>
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/spec")
public class SpecController {
    public static final Logger log = LoggerFactory.getLogger(SpecController.class);

    @Autowired
    private RemoteService itemService;

   

    /**
     * @description 查询商品规格列表
     * @detail 可分页传参
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-18
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping("/specList")
    @ResponseBody
    public Object getSpecList(HttpServletRequest request, SpecQryDTO specQry) throws Exception {
        log.info("start[SpecController.getSpecList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (value == null) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        String rqstr = JSONObject.fromObject(specQry, jsonConfig).toString();
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, rqstr));
        log.info("end[SpecController.getSpecList]");
        return resultObject;
    }

    /**
     * @description 查询商品规格值
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-18
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping("/getSpec")
    @ResponseBody
    public Object getSpecValueList(HttpServletRequest request, SpecQryDTO specQry) throws Exception {
        log.info("start[SpecController.getSpecValueList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject
                .fromObject(specQry)));
        log.info("end[SpecController.getSpecValueList]");
        return resultObject;
    }

    
    /**
     * @description 获取规格及规格值详细信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnit.com">李明</a>
     * @version 1.0.0
     * @data 2015-3-18
     * @param data
     * @return
     * @throws Exception
     */
    @RequestMapping("/getDetail")
    @ResponseBody
    public Object getSpecAndValuesById(HttpServletRequest request, Integer specId) throws Exception {
        log.info("start[SpecController.getSpecAndValuesById]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-05", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, specId));
        log.info("end[SpecController.getSpecAndValuesById]");
        return resultObject;
    }

  

    /**
     * @description 根据分类ID查询规格及规格值
     * @detail <方法详细描述>
     * @author <a href="liming@cnitcloud.com">李明</a>
     * @version 1.0.0
     * @data 2015年4月14日
     * @param request
     * @param catId
     * @return
     * @throws Exception
     */
    @RequestMapping("/specAndValuesByCatId")
    @ResponseBody
    public Object getSpecAndValuesByCatId(HttpServletRequest request, Integer catId, Integer carId) throws Exception {
        log.info("start[SpecController.getSpecAndValuesByCatId]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020102-09", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("catId", catId);
        data.put("carId", carId);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        String gradeType = (String) CommonUtil.getSession(request).getAttribute("gradeType");   //店铺类型
        data.put("companyId", companyId);
        data.put("gradeType", gradeType);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
        log.info("end[SpecController.getSpecAndValuesByCatId]");
        return resultObject;
    }
}
