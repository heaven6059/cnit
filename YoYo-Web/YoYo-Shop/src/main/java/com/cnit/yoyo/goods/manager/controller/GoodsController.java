/**
 * 文 件 名   :  GoodsController.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午3:11:42
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

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.dto.GoodsQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 商品管理功能
 * @detail <功能详细描述>
 * @author <a href="liming@cnitcloud.com">李明</a>
 * @version 1.0.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    public static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    @Autowired
    public RemoteService itemService;
    
    @Autowired
    public RemoteService memberService;
    /**
     * @description 获取商品信息列表
     * @detail <方法详细描述>
     * @author <a href="liming@cnitcloud.com">李明</a>
     * @version 1.0.0
     * @data 2015年4月3日
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/loadGoodsList")
    public Object loadGoodsList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadGoodsList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-01", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        /*TODO 打开  qryDTO.setCompanyId(14L);
        qryDTO.setStoreId(1L);*/
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        if(StringUtils.isBlank(qryDTO.getMarketableQ())){ //默认设置为：上架的商品
            qryDTO.setMarketableQ("1");
        }
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        request.setAttribute("goodslist", resultObject.getContent());
        log.info("end[GoodsController.loadGoodsList]");
        return "pages/goodsMng/sellingGoods";
    }
    
    
    /**
     * @description 卖家中心，获取需要参加活动的商品列表
     */
    @RequestMapping("/findActivityGoods")
    @ResponseBody
    public Object findActivityGoods(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.findActivityGoods]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-31", GlobalStatic.SYSTEM_CODE_DATA,
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
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.findActivityGoods]");
        return resultObject;
    }
    
    /**
     * @description 卖家中心，获取需要参加活动的商品列表
     */
    @RequestMapping("/selectGoodsForActivity")
    @ResponseBody
    public Object selectGoodsForActivity(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.selectGoodsForActivity]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-32", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
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
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.selectGoodsForActivity]");
        return resultObject;
    }
    
    /**
     * @description 卖家中心，获取添加活动的商品列表
     */
    @RequestMapping("/findAddActivityGoods")
    @ResponseBody
    public Object findAddActivityGoods(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.findActivityGoods]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-28", GlobalStatic.SYSTEM_CODE_DATA,
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
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.findActivityGoods]");
        return resultObject;
    }
    
    
    @RequestMapping("/updateActivityGoods")
    @ResponseBody
    public Object updateActivityGoods(HttpServletRequest request,@RequestParam(value="goodsId[]",required=true)Integer[] goodsId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-29", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    @RequestMapping("/updateActivityGoodsById")
    @ResponseBody
    public Object updateActivityGoodsById(HttpServletRequest request,@RequestParam(value="goodsId",required=true)Integer goodsId){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-30", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 ResultObject resultObject = null;
         try {
        	   resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        	   
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
    
    /**
     * 
     *@description 分页查询，按条件查询
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-27
     *@param request
     *@param goods
     *@return
     *@throws Exception
     */
    @RequestMapping("/goodsList")
    @ResponseBody
    public Object getGoodsList(HttpServletRequest request,GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.getGoodsList]");
        ResultObject resultObject = null;
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-01", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        getGoodsCatChildList(request,qryDTO);
        /*TODO 打开本店铺的商品  qryDTO.setCompanyId(14L);
        qryDTO.setStoreId(1L);*/
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        if(StringUtils.isBlank(qryDTO.getMarketableQ())){ //默认设置为：上架的商品
            qryDTO.setMarketableQ("1");
        }
        resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.getGoodsList]");
        return resultObject;
    }
    
    @RequestMapping("/editIndex/{identifier}/{goodsId}")
    public String editIndex(HttpServletRequest request,@PathVariable Integer goodsId,@PathVariable String identifier) {
        request.setAttribute("goodsId", goodsId);
        request.setAttribute("catType", identifier);
        //编辑时，需要先判断该店铺是否禁止发布商品，
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-16", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        try {
            ResultObject resultObject = memberService.doService(new RequestObject(headObject, companyId));
            if((Integer)resultObject.getContent()==0){ //店铺禁止发布商品
                return "pages/goodsMng/forbidden";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/goodsMng/goodsPublishMain";
    }
    
    /**
     * @description 获取商品详细信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnitcloud.com">李明</a>
     * @version 1.0.0
     * @data 2015年4月9日
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/goodsDetail")
    @ResponseBody
    public Object getGoodsDetailById(HttpServletRequest request, Integer goodsId) throws Exception {
        log.info("start[GoodsController.getGoodsDetailById]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        log.info("end[GoodsController.getGoodsDetailById]");
        return resultObject;
    }
    
    /**
     * @description 根据id查询配件商品信息
     * @detail <方法详细描述>
     * @author <a href="liming@cnitcloud.com">李明</a>
     * @version 1.0.0
     * @data 2015年4月14日
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getAccessoryGoods")
    @ResponseBody
    public Object getAccessoryGoodsById(HttpServletRequest request,Integer goodsId) throws Exception {
        log.info("start[GoodsController.getAccessoryGoodsById]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-10", GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        log.info("end[GoodsController.getAccessoryGoodsById]");
        return resultObject;
    }
    
    @RequestMapping("/batchEdit")
    @ResponseBody
    public Object batchEdit(HttpServletRequest request) throws Exception {
        log.info("start[GoodsController.batchEdit]");
        String op = request.getParameter("op");
        ResultObject resultObject = null;
        int alertValue = 0;
        /* 重新生成图片 */
        if ("regen".equalsIgnoreCase(op)) {
            resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE, "未知操作类型！"));
        } else {
            HeadObject headObject = null;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rows", request.getParameter("rows"));
            /* 批量上架 */
            if ("up".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-02", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
            }
            
            /* 批量下架 */
            else if ("down".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-03", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
            }
            /* 批量设置库存预警值 */
            else if ("alert".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-14", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
                if(!StringUtil.isEmpty(request.getParameter("alertValue"))) {
                	alertValue = Integer.parseInt(request.getParameter("alertValue"));
                }
                jsonObject.put("alertValue", alertValue);
            }
            /* 商品除权 */
            else if ("exd".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-04", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
            }
            /* 批量删除 */
            else if ("delete".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-05", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
            }
            /* 设置标签 */
            else if ("tag".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-06", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
                jsonObject.put("tags", request.getParameter("tags"));
            }
            /*批量上架/提交审核*/
            else if ("upOrCheck".equalsIgnoreCase(op)) {
                headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-32", GlobalStatic.SYSTEM_CODE_DATA,
                        GlobalStatic.SYSTEM_CODE_BACK);
            }
            if (headObject != null) {
                jsonObject.put("preType", "preType");
                
                resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
            } else {
                resultObject = new ResultObject(new HeadObject(ErrorCode.FAILURE, "未知操作类型！"));
            }
        }
        log.info("end[GoodsController.batchEdit]");
        return resultObject;
    }
    
    /**
     * 
     *@description 获取等待上架的商品列表
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/loadPutawayList")
    public Object loadPutawayList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadPutawayList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-01", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        /*TODO 打开  qryDTO.setCompanyId(14L);
        qryDTO.setStoreId(1L);*/
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        if(StringUtils.isBlank(qryDTO.getMarketableQ())){ //默认设置为：上架的商品
            qryDTO.setMarketableQ("1");
        }
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        request.setAttribute("goodslist", resultObject.getContent());
        log.info("end[GoodsController.loadPutawayList]");
        return "pages/goodsMng/storageGoods";
    }
    
    /**
     * 
     *@description 获取等待上架的商品列表，分页查询，按条件查询
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/putawayList")
    public Object putawayList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadPutawayList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-01", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        getGoodsCatChildList(request,qryDTO);
        /*TODO 打开  qryDTO.setCompanyId(14L);
        qryDTO.setStoreId(1L);*/
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        if(StringUtils.isBlank(qryDTO.getMarketableQ())){ //默认设置为：上架的商品
            qryDTO.setMarketableQ("1");
        }
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.loadPutawayList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 预警中的商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/loadWarningList")
    public Object loadWarningList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadPutawayList]");
        //先查找库存数量
        /*HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031001-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 获取当前登陆者的店铺id与分店id
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        dataMap.put("companyId", Long.valueOf(companyId));
        dataMap.put("storeId", Long.valueOf(storeId));
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, dataMap));
        request.setAttribute("storeNum", resultObject.getContent());
        qryDTO.setStoreNum((Integer)resultObject.getContent());*/
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setDisabledQ("0");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, qryDTO));
        request.setAttribute("goodslist", resultObject.getContent());
        log.info("end[GoodsController.loadPutawayList]");
        return "pages/goodsMng/warningGoods";
    }
    
    /**
     * 
     *@description 获取预警中的商品列表，分页查询，按条件查询
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/warningList")
    public Object warningList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.warningList]");
        //先查找库存数量
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031001-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 获取当前登陆者的店铺id与分店id
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        dataMap.put("companyId", Long.valueOf(companyId));
        dataMap.put("storeId", Long.valueOf(storeId));
        ResultObject resultObject = memberService.doService(new RequestObject(headObject, dataMap));
        request.setAttribute("storeNum", resultObject.getContent());
        qryDTO.setStoreNum((Integer)resultObject.getContent());
        qryDTO.setDisabledQ("0");
        getGoodsCatChildList(request,qryDTO);
        /*TODO 打开  qryDTO.setCompanyId(14L);
        qryDTO.setStoreId(1L);*/
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        headObject = CommonHeadUtil.geneHeadObject(request, "010201-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        resultObject = itemService.doService(new RequestObject(headObject, qryDTO));
        log.info("end[GoodsController.warningList]");
        return resultObject;
    }
    
    
    /**
     * 
     *@description 更新预警库存
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-4-29
     *@param request
     *@param storeNum
     *@return
     *@throws Exception
     */
    @RequestMapping("/updateStoreNum")
    public Object updateStoreNum(HttpServletRequest request, Integer storeNum) throws Exception {
        log.info("start[GoodsController.updateStoreNum]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "031001-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 获取当前登陆者的店铺id与分店id
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        dataMap.put("companyId", Long.valueOf(companyId));
        dataMap.put("storeId", Long.valueOf(storeId));
        dataMap.put("storeNum",storeNum);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,dataMap));
        log.info("end[GoodsController.updateStoreNum]");
        return resultObject;
    }
    
    /**
     * @description 获取扩展属性的值
     * @detail <方法详细描述>
     * @author <a href="liming@cnitcloud.com">李明</a>
     * @version 1.0.0
     * @data 2015年4月13日
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/attributeValue")
    @ResponseBody
    public Object getGoodAttributeValue(HttpServletRequest request) throws Exception {
        log.info("start[GoodsController.getGoodAttributeValue]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-08", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JSONObject content = new JSONObject();
        content.put("catId", request.getParameter("catId"));
        content.put("goodsId", request.getParameter("goodsId"));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
        log.info("end[GoodsController.getGoodAttributeValue]");
        return resultObject;
    }
    
    
    /**
     * 
     *@description 通过goodsId获得商品所有的图片
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-4
     *@param request
     *@param goodsId
     *@return
     *@throws Exception
     */
    @RequestMapping("/productPic")
    @ResponseBody
    public Object getProductPic(HttpServletRequest request,Integer goodsId) throws Exception {
        log.info("start[GoodsController.getProductPic]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-04", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, goodsId));
        log.info("end[GoodsController.getProductPic]");
        return resultObject;
    }
    
    /**
     * 
     *@description 获取扩展属性的所有值，包括父级的
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-9
     *@param request
     *@return
     *@throws Exception
     */
    @RequestMapping("/findAllAttrValue")
    @ResponseBody
    public Object findAttributeByParam(HttpServletRequest request) throws Exception {
        log.info("start[GoodsController.getGoodAttributeValue]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020105-31", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        JSONObject content = new JSONObject();
        content.put("catId", request.getParameter("catId"));
        content.put("goodsId", request.getParameter("goodsId"));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, content));
        log.info("end[GoodsController.getGoodAttributeValue]");
        return resultObject;
    }
    
  /**
     * 
     *@description 获取审核中的商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-25
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/loadCheckingList")
    public Object loadCheckingList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadCheckingList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-01", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");  
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        request.setAttribute("goodslist", resultObject.getContent());
        log.info("end[GoodsController.loadCheckingList]");
        return "pages/goodsMng/checkingGoods";
    }
    
    
    
   /**
     * 
     *@description 获取审核中的商品列表，分页查询，按条件查询
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-25
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/checkingList")
    public Object checkingList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.checkingList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-01", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        getGoodsCatChildList(request,qryDTO);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.checkingList]");
        return resultObject;
    }
    
    
    /**
     * 
     *@description 获取暂存本地的商品
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-11
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/loadLocationList")
    public Object loadLocationList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadLocationList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-20", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");  
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        request.setAttribute("goodslist", resultObject.getContent());
        log.info("end[GoodsController.loadLocationList]");
        return "pages/goodsMng/locationGoods";
    }
    
    
    
   /**
     * 
     *@description 获取暂存本地的商品列表，分页查询，按条件查询
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-5-25
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/locationList")
    public Object locationList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.locationList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-20", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        getGoodsCatChildList(request,qryDTO);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.locationList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 获取平台下架的商品(违规中的商品)
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-23
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/violationList")
    public Object violationList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.violationList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-21", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        getGoodsCatChildList(request,qryDTO);
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        log.info("end[GoodsController.violationList]");
        return resultObject;
    }
    
    /**
     * 
     *@description 获取平台下架的商品(违规中的商品)
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-6-23
     *@param request
     *@param qryDTO
     *@return
     *@throws Exception
     */
    @RequestMapping("/loadViolationList")
    public Object loadViolationList(HttpServletRequest request, GoodsQryDTO qryDTO) throws Exception {
        log.info("start[GoodsController.loadViolationList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-21", GlobalStatic.SYSTEM_CODE_DATA,
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
        qryDTO.setDisabledQ("0");
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        qryDTO.setCompanyId(Long.valueOf(companyId));
        qryDTO.setStoreId(Long.valueOf(storeId));
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        request.setAttribute("goodslist", resultObject.getContent());
        log.info("end[GoodsController.loadViolationList]");
        return "pages/goodsMng/violationGoods";
    }
    
    

    /**
     * 
     *@description 根据父类id查所有子类id
     *@detail <方法详细描述>
     *@author <a href="xiaoxiang@cnit.com">肖湘</a>
     *@version 1.0.0
     *@data 2015-7-10
     *@param request
     *@param qryDTO
     *@throws Exception
     */
    private void getGoodsCatChildList(HttpServletRequest request,GoodsQryDTO qryDTO) throws Exception{
        ResultObject resultObject = null;
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010201-25", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        String[] childArr = null;
        if(qryDTO.getCatId()!=null && qryDTO.getCatId()!=0){ //查父级分类下的所有子分类id
            resultObject = itemService.doService(new RequestObject(headObject,qryDTO.getCatId()));
            if(resultObject!=null && resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){
                String childArrStr = resultObject.getContent().toString();
                if(StringUtils.isNotBlank(childArrStr)){
                    childArr = childArrStr.split(",");
                    qryDTO.setChildArr(childArr);
                }
            }
        }
    }
  
    
}
