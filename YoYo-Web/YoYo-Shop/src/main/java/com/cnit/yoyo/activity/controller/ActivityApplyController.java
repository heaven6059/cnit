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
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.activity.dto.ActivityQryDTO;
import com.cnit.yoyo.model.activity.dto.FullReduceDTO;
import com.cnit.yoyo.model.sales.activity.ScoreBuyApply;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyDTO;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyApplyQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * 
 * @Description: 活动报名
 */
@Controller
@RequestMapping("/activityApply")
public class ActivityApplyController {

    @Autowired
    private RemoteService itemService;
    @Autowired
	private RemoteService salesService;

    @RequestMapping("/scoreActivityList")
    public String fullReduceList(){
        return "pages/activityMng/scoreActivityList";
    }
    @RequestMapping("/myActivityList")
    public String myActivityList(){
        return "pages/activityMng/myScoreActivityList";
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
    
    @RequestMapping("/addScoreActivity/{type}/{limitNum}/{actName}/{startTime}/{endTime}/{actId}")
    public String addScoreActivity(HttpServletRequest request,@PathVariable String type,@PathVariable String limitNum,@PathVariable String actName,@PathVariable String startTime,@PathVariable String endTime,@PathVariable int actId){
    	request.setAttribute("name", actName);
    	request.setAttribute("startTime", startTime);
    	request.setAttribute("endTime", endTime);
    	request.setAttribute("actId", actId);
    	request.setAttribute("limitNum", limitNum);
    	request.setAttribute("type", type);
        return "pages/activityMng/addScoreActivity";
    }
    
   
    
	@RequestMapping("/loadScoreBuyActivity")
	@ResponseBody
	public Object loadScoreBuyActivity(HttpServletRequest request,ScoreBuyActivityQryDTO activityQryDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060101-02");
	    ResultObject resultObject = null;
	    try {
	        resultObject = salesService.doService(new RequestObject(headObject, activityQryDTO));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
	}
	/**
	 * 我的活动列表-积分换购
	 * @Description: 
	 * @param request
	 * @param activityQryDTO
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/loadScoreBuyApplyList")
	public Object loadScoreBuyApplyList(HttpServletRequest request,ScoreBuyApplyQryDTO activityQryDTO){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-01");
	    ResultObject resultObject = null;
	    try {
	    	Integer memberId =Integer.parseInt(CommonUtil.getSession(request).getAttribute("memberId").toString());
	    	activityQryDTO.setMemberId(memberId);
	        resultObject = salesService.doService(new RequestObject(headObject, activityQryDTO));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
	}
	@RequestMapping("/loadScoreBuyApplyDetail")
	public Object loadScoreBuyApplyDetail(HttpServletRequest request,Integer id){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-04");
	    ResultObject resultObject = null;
	    try {
	        resultObject = salesService.doService(new RequestObject(headObject, id));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    request.setAttribute("detail", resultObject.getContent());
	    return "pages/activityMng/myScoreActivityDetail";
	}
	@ResponseBody
	@RequestMapping("/closeApplyActivity")
	public Object closeApplyActivity(HttpServletRequest request,Integer id){
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-03");
	    ResultObject resultObject = null;
	    try {
	    	ScoreBuyApply apply=new ScoreBuyApply();
	    	apply.setId(id);
	    	apply.setIsdel("1");
	    	apply.setRemark("商家退出活动");
	        resultObject = salesService.doService(new RequestObject(headObject, apply));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return resultObject;
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
    @RequestMapping("/saveScoreActivity")
    @ResponseBody
    public Object saveScoreActivity(HttpServletRequest request, ScoreBuyApplyDTO scoreBuyApplyDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "060102-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        //TODO 设置店铺id
        Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
        Integer storeId = (Integer) CommonUtil.getSession(request).getAttribute("storeId");
        Integer memberId = Integer.parseInt((String) CommonUtil.getSession(request).getAttribute("memberId"));
        scoreBuyApplyDTO.setCompanyId(Long.valueOf(companyId));
        scoreBuyApplyDTO.setStoreId(Long.valueOf(storeId));
        scoreBuyApplyDTO.setMemberId(memberId);
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
        ResultObject resultObject = salesService.doService(new RequestObject(headObject, scoreBuyApplyDTO));
        
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
