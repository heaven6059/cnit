
package com.cnit.yoyo.shop.controller;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 
 * @ClassName: StoreViolationCatController  
 * @Description: TODO(店铺违规类型)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-2 下午1:50:48  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/storeViolationCat")
public class StoreViolationCatController {
    public static final Logger log = LoggerFactory.getLogger(StoreViolationCatController.class);
    
    
    @Autowired
    private RemoteService memberService;

    /**
     * @Title:  index  
     * @Description:  TODO(店铺违规类型管理页面)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午1:53:02  
     * @version 1.0.0 
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/storeViolation/violationCatIndex";
    }
    
    /**
     * @Title:  findViolationCatList  
     * @Description:  TODO(根据条件获取违规类型列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午2:09:57  
     * @version 1.0.0 
     * @param @param request
     * @param @param company
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findViolationCatList")
    @ResponseBody
    public Object findViolationCatList(HttpServletRequest request) throws Exception {
        log.info("start[StoreViolationCatController.findViolationCatList]");
        String parentCatId = request.getParameter("parentCatId");
		JSONObject params = new JSONObject();
		if (StringUtil.isEmpty(parentCatId)) {
			params.put("parentCatId", 0);
		} else {
			params.put("parentCatId", Integer.parseInt(parentCatId));
		}
		if("false".equals(request.getParameter("exist"))){
			params.put("exist", false);
		}else{
			params.put("exist", true);
		}
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, params));
        log.info("end[StoreViolationCatController.findViolationCatList]");
        return resultObject.getContent();
    }
    
    /**
     * @Title:  saveOrUpdateCate  
     * @Description:  TODO(新增或修改违规类型)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-2 下午5:10:12  
     * @version 1.0.0 
     * @param @param request
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/saveOrUpdateCate")
	@ResponseBody
	public Object saveOrUpdateCate(HttpServletRequest request) throws Exception {
		log.info("start[StoreViolationCatController.saveOrUpdateCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		String cate = request.getParameter("cate");
		ResultObject resultObject = null;
		if (!StringUtil.isEmpty(cate)) {
			resultObject = memberService.doService(new RequestObject(headObject, cate));
		}
		log.info("end[StoreViolationCatController.saveOrUpdateCate]");
		return resultObject;
	}
    
    /**
     * @Title:  getViolationCateDetail  
     * @Description:  TODO(获取指定违规类型对象)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 上午10:49:48  
     * @version 1.0.0 
     * @param @param request
     * @param @param catId
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
	@RequestMapping("/getViolationCateDetail")
	@ResponseBody
	public Object getViolationCateDetail(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[StoreViolationCatController.getViolationCateDetail]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, catId));
		log.info("end[StoreViolationCatController.getViolationCateDetail]");
		return resultObject;
	}
    
	/**
	 * @Title:  deleteCate  
	 * @Description:  TODO(删除违规类型)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-3 下午2:23:10  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param catId
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteCate")
	@ResponseBody
	public Object deleteCate(HttpServletRequest request, Integer catId) throws Exception {
		log.info("start[StoreViolationCatController.deleteCate]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, catId));
		log.info("end[StoreViolationCatController.deleteCate]");
		return resultObject;
	}
	
	/**
	 * @Title:  findChildViolationCatList  
	 * @Description:  TODO(查询除第一级分类外的违规类型)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-9 下午4:49:03  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/findChildViolationCatList")
    @ResponseBody
    public Object findChildViolationCatList(HttpServletRequest request) throws Exception {
        log.info("start[StoreViolationCatController.findChildViolationCatList]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030601-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, null));
        log.info("end[StoreViolationCatController.findChildViolationCatList]");
        return resultObject.getContent();
    }
    
}