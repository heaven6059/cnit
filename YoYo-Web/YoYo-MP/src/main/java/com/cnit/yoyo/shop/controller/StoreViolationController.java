package com.cnit.yoyo.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.PamAccount;
import com.cnit.yoyo.model.shop.dto.StoreViolationQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * @ClassName: StoreViolationController  
 * @Description: TODO(店铺违规)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-5 下午4:59:28  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/storeViolation")
public class StoreViolationController {

	public static final Logger log = LoggerFactory.getLogger(StoreViolationController.class);
    @Autowired
    private RemoteService memberService;
	
	
    /**************************************************       店铺违规      *********************************************************************/
    
    /**
     * @Title:  index  
     * @Description:  TODO(店铺违规管理)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 下午4:31:38  
     * @version 1.0.0 
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/storeViolation/storeViolation";
    }
	
    /**
     * @Title:  findStoreViolationList  
     * @Description:  TODO(根据条件查询店铺违规列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-5 下午6:26:03  
     * @version 1.0.0 
     * @param @param request
     * @param @param dto
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findStoreViolationList")
    @ResponseBody
    public Object findStoreViolationList(HttpServletRequest request, StoreViolationQryDTO dto) throws Exception {
        log.info("start[StoreViolationController.findStoreViolationList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        List<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        dto.setProcessed(list);
        dto.setStatus(0);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,dto));
        log.info("end[StoreViolationController.findStoreViolationList]");
        return resultObject;
    }
    
    /**
     * @Title:  deleteStoreViolation  
     * @Description:  TODO(删除店铺违规对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-8 下午1:22:09  
     * @version 1.0.0 
     * @param @param request
     * @param @param id
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/deleteStoreViolation")
	@ResponseBody
	public Object deleteStoreViolation(HttpServletRequest request, @RequestParam(value = "id", required = true) List<Long> id) throws Exception {
		log.info("start[StoreViolationController.deleteStoreViolation]");
		ResultObject resultObject = new ResultObject(new HeadObject(ErrorCode.NO_DATA));
		if(id!=null&&id.size()>=1){
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030104-03", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject,id));
		}
		log.info("start[StoreViolationController.deleteStoreViolation]");
		return resultObject;
	}
    
    /**
     * @Title:  saveStoreViolation  
     * @Description:  TODO(保存店铺违规对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-9 下午6:02:35  
     * @version 1.0.0 
     * @param @param request
     * @param @param storeId
     * @param @param catId
     * @param @param remark
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/saveStoreViolation")
	@ResponseBody
	public Object saveStoreViolation(HttpServletRequest request, @RequestParam(value = "storeId", required = true) List<Integer> storeId, 
			@RequestParam(value = "catId", required = true) List<Integer> catId, @RequestParam(value = "remark", required = true) String remark) throws Exception {
		log.info("start[StoreViolationController.saveStoreViolation]");
//		System.out.println("storeId..."+storeId.toString());
//		System.out.println("catId..."+catId.toString());
//		System.out.println("remark..."+remark);
		ResultObject resultObject = null;
		if(storeId!=null&&storeId.size()>=1&&catId!=null&&catId.size()>=1&&remark!=null&&!"".equals(remark.trim())){
			//获取当前用户名
			String loginName = (String) CommonUtil.getSession(request).getAttribute("loginName");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("storeId", storeId);
			jsonObject.put("catId", catId);
			jsonObject.put("remark", remark);
			jsonObject.put("loginName", loginName!=null?loginName:"");
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030104-04", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject,jsonObject));
		}else{
			resultObject = new ResultObject(new HeadObject(ErrorCode.NO_DATA));
		}
		log.info("start[StoreViolationController.saveStoreViolation]");
		return resultObject;
	}
	
    
    /**************************************************       店铺处理      *********************************************************************/
    

    /**
     * @Title:  actionIndex  
     * @Description:  TODO(店铺处理管理)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-8 下午3:04:52  
     * @version 1.0.0 
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/actionIndex")
    public String actionIndex() {
        return "pages/biz/storeViolation/storeViolationAction";
    }
    
    /**
     * @Title:  findStoreViolationActionList  
     * @Description:  TODO(根据条件查询店铺处理列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-9 下午3:03:25  
     * @version 1.0.0 
     * @param @param request
     * @param @param dto
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findStoreViolationActionList")
    @ResponseBody
    public Object findStoreViolationActionList(HttpServletRequest request, StoreViolationQryDTO dto) throws Exception {
        log.info("start[StoreViolationController.findStoreViolationActionList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        dto.setProcessed(list);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,dto));
        log.info("end[StoreViolationController.findStoreViolationActionList]");
        return resultObject;
    }
    
}
