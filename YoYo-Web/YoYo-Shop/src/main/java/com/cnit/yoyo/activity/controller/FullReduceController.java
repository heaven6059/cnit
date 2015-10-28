package com.cnit.yoyo.activity.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.dto.BaseQryDTO;
import com.cnit.yoyo.model.activity.dto.ActivityQryDTO;
import com.cnit.yoyo.model.activity.dto.CouponsDTO;
import com.cnit.yoyo.model.activity.dto.FullReduceDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @Description: 满减活动
 */
@Controller
@RequestMapping("/fullReduce")
public class FullReduceController {

    @Autowired
    private RemoteService itemService;

    @RequestMapping("/fullReduceList")
    public String fullReduceList(){
        return "pages/activityMng/fullReduceList";
    }
    
    @RequestMapping("/fullDiscountList")
    public String fullDiscountList(){
        return "pages/activityMng/fullDiscountList";
    }

    @RequestMapping("/addActivityRule")
    public String addActivityRule(){
        return "pages/activityMng/addActivityRule";
    }
    
    @RequestMapping("/editActivity/{actId}")
    public String editIndex(HttpServletRequest request,@PathVariable Integer actId) {
        request.setAttribute("actId", actId);
        return "pages/activityMng/addActivityRule";
    }
    
    @RequestMapping("/editDiscount/{actId}")
    public String editDiscount(HttpServletRequest request,@PathVariable Integer actId) {
        request.setAttribute("actId", actId);
        return "pages/activityMng/addActivityDiscountRule";
    }
    
    @RequestMapping("/addfullDicountRule")
    public String addfullDicountRule(){
        return "pages/activityMng/addActivityDiscountRule";
    }
    
   
    
    
    /**
     * 
     *@description 获取满减活动列表
     */
    @RequestMapping("/getFullReduceList")
    public Object getFullReduceList(HttpServletRequest request,ActivityQryDTO qryDTO){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        
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
        try {
            resultObject = itemService.doService(new RequestObject(headObject,JSONObject.fromObject(qryDTO,jsonConfig)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject.getContent();
    }
    
    /**
     * 
     * @Description: 保存满减活动
     */
    @RequestMapping("/saveFullReduce")
    @ResponseBody
    public Object saveFullReduce(HttpServletRequest request, FullReduceDTO fullReduceDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 设置店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        fullReduceDTO.setCompanyId(Long.valueOf(companyId));
        fullReduceDTO.setStoreId(Long.valueOf(storeId));
        fullReduceDTO.setActivityType("1"); //设置为满减活动类型
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
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, fullReduceDTO));
        
        return resultObject;
    }
    
    /**
     * 
     *@description 更新满减活动
     */
    @RequestMapping("/updateFullReduce")
    @ResponseBody
    public Object updateFullReduce(HttpServletRequest request,FullReduceDTO fullReduceDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-06", GlobalStatic.SYSTEM_CODE_DATA,
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
        fullReduceDTO.setActivityType("1"); //设置为满减活动类型
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, fullReduceDTO));
        
        return resultObject;
    }
    
    /**
     * 
     * @Description: 保存满减活动
     */
    @RequestMapping("/editFullReduce")
    @ResponseBody
    public Object editFullReduce(HttpServletRequest request, Long actId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-04", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, actId));
        
        return resultObject;
    }
    
    /**
     * 
     * @Description: 根据活动ID获取关联的商品ID
     */
    @RequestMapping("/getGoodIds")
    @ResponseBody
    public Object getGoodIds(HttpServletRequest request, Long actId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-05", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, actId));
        
        return resultObject;
    }
    
    /**
     * 
     *@description 获取满折活动列表
     */
    @RequestMapping("/getFullDiscountList")
    public Object getFullDiscountList(HttpServletRequest request,ActivityQryDTO qryDTO){
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        
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
        try {
            resultObject = itemService.doService(new RequestObject(headObject,JSONObject.fromObject(qryDTO,jsonConfig)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject.getContent();
    }
    
    /**
     * 
     * @Description: 保存满折活动
     */
    @RequestMapping("/saveFullDiscount")
    @ResponseBody
    public Object saveFullDiscount(HttpServletRequest request, FullReduceDTO fullReduceDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 设置店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        fullReduceDTO.setCompanyId(Long.valueOf(companyId));
        fullReduceDTO.setStoreId(Long.valueOf(storeId));
        fullReduceDTO.setActivityType("2"); //设置为满折活动类型
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
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, fullReduceDTO));
        
        return resultObject;
    }
    
    
    /**
     * 
     *@description 更新满折活动
     */
    @RequestMapping("/updateFullDiscount")
    @ResponseBody
    public Object updateFullDiscount(HttpServletRequest request,FullReduceDTO fullReduceDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-06", GlobalStatic.SYSTEM_CODE_DATA,
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
        fullReduceDTO.setActivityType("2"); //设置为满折活动类型
        ResultObject resultObject = itemService.doService(new RequestObject(headObject, fullReduceDTO));
        
        return resultObject;
    }
    
    @RequestMapping("/deleteFullDiscount")
    @ResponseBody
    public Object deleteFullDiscount(HttpServletRequest request, long actId,int ruleId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-07", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("ruleId",ruleId);
        dataMap.put("actId", actId);
        return itemService.doService(new RequestObject(headObject, dataMap));
        
    }
    
    
    @RequestMapping("/deleteFullReduce")
    @ResponseBody
    public Object deleteFullReduce(HttpServletRequest request, long actId,int ruleId) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "010102-07", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("ruleId",ruleId);
        dataMap.put("actId", actId);
        return itemService.doService(new RequestObject(headObject, dataMap));
        
    }
    
}
