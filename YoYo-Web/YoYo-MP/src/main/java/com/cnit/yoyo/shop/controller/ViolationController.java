package com.cnit.yoyo.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.shop.Violation;
import com.cnit.yoyo.model.shop.dto.ViolationQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

/**
 * @ClassName: ViolationController  
 * @Description: TODO(违规处理)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-6-3 下午4:32:22  
 * @version 1.0.0
 */
@Controller
@RequestMapping("/violation")
public class ViolationController {

	public static final Logger log = LoggerFactory.getLogger(ViolationController.class);
    @Autowired
    private RemoteService memberService;
	
	
    /**
     * @Title:  index  
     * @Description:  TODO(违规处理管理)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 下午4:31:38  
     * @version 1.0.0 
     * @param @return
     * @return String  返回类型 
     * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/storeViolation/violation";
    }
	
    
    /**
     * @Title:  findViolationList  
     * @Description:  TODO(获取违规处理列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-3 下午4:31:54  
     * @version 1.0.0 
     * @param @param request
     * @param @param carYearId
     * @param @return
     * @param @throws Exception
     * @return Object  返回类型 
     * @throws
     */
    @RequestMapping("/findViolationList")
    @ResponseBody
    public Object findViolationList(HttpServletRequest request, ViolationQryDTO dto) throws Exception {
        log.info("start[ViolationController.findViolationList]");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030701-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = memberService.doService(new RequestObject(headObject,dto));
        log.info("end[ViolationController.findViolationList]");
        return resultObject;
    }
	
	/**
	 * @Title:  saveOrUpdateViolation  
	 * @Description:  TODO(保存违规处理对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-4 下午6:14:39  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param violation
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/saveOrUpdateViolation")
	@ResponseBody
	public Object saveOrUpdateViolation(HttpServletRequest request, Violation violation) throws Exception {
		log.info("start[ViolationController.saveOrUpdateViolation]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030701-02", 
				GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = memberService.doService(new RequestObject(headObject, violation));
		log.info("start[ViolationController.saveOrUpdateViolation]");
		return resultObject;
	}
	
	/**
	 * @Title:  deleteViolation  
	 * @Description:  TODO(删除违规处理对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-5 上午11:18:09  
	 * @version 1.0.0 
	 * @param @param request
	 * @param @param id
	 * @param @return
	 * @param @throws Exception
	 * @return Object  返回类型 
	 * @throws
	 */
	@RequestMapping("/deleteViolation")
	@ResponseBody
	public Object deleteViolation(HttpServletRequest request, @RequestParam(value = "id", required = true) List<Integer> id) throws Exception {
		log.info("start[ViolationController.deleteViolation]");
		ResultObject resultObject = new ResultObject(new HeadObject(ErrorCode.NO_DATA));
		if(id!=null&&id.size()>=1){
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030701-03", 
					GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
			resultObject = memberService.doService(new RequestObject(headObject,id));
		}
		log.info("start[ViolationController.deleteViolation]");
		return resultObject;
	}
	
}
