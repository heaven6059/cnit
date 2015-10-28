package com.cnit.yoyo.goods.manager.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.cnit.yoyo.model.goods.Label;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * @ClassName: LabelController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author Harder-Zhao
 * @date 2015-3-30 上午11:08:42
 * 
 */
@Controller
@RequestMapping("/label")
public class LabelController {
	public static final Logger log = LoggerFactory.getLogger(LabelController.class);

	@Autowired
	private RemoteService itemService;

	/**
	 * @Title: getBrandApplyLabels
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param brand
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/brandApplyLabels")
	@ResponseBody
	public Object getBrandApplyLabels(HttpServletRequest request, @RequestParam(value = "campanyId", required = true) Long campanyId) throws Exception {
		log.info("start[LabelController.getBrandApplyLabels]");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "4000020114-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		Map<String, Object> data = new HashMap<String, Object>();
		String pageIndex = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
		data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
		data.put("campanyId", campanyId);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, data));
		log.info("end[LabelController.getBrandApplyLabels]");
		return resultObject;
	}

	/**
	 * @Title: insertLabel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param label
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/insertLabel")
	@ResponseBody
	public Object insertLabel(HttpServletRequest request, Label label) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "4000020114-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			// FIXME TODO 以下的属性值需要重新获取，此处暂时给一个测试数据值
		    Integer companyId = (Integer) CommonUtil.getSession(request).getAttribute("companyId");
            label.setCampanyId(Long.valueOf(companyId));
			label.setLabelObject("brand");
			label.setApplication("business");
			label.setType("0");
			resultObject = itemService.doService(new RequestObject(headObject, label));
      	   if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的品牌名称不同名，可以修改
	        	   headObject = CommonHeadUtil.geneHeadObject(request, "4000020114-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	        	   resultObject = itemService.doService(new RequestObject(headObject, label));
      	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * @Title: updateLabel
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param label
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/updateLabel")
	@ResponseBody
	public Object updateLabel(HttpServletRequest request, Label label) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "4000020114-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = itemService.doService(new RequestObject(headObject, label));
			if(resultObject.getHead().getRetCode().equals(ErrorCode.SUCCESS)){  //修改后的品牌名称不同名，可以修改
        	   headObject = CommonHeadUtil.geneHeadObject(request, "4000020114-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	   resultObject = itemService.doService(new RequestObject(headObject, label));
    	   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * @Title: deleteLabels
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author Harder-Zhao
	 * @param @param request
	 * @param @param ids
	 * @param @return 设定文件
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/deleteLabels")
	@ResponseBody
	public Object deleteLabels(HttpServletRequest request, @RequestParam(value = "ids[]", required = true) Long[] ids) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "4000020114-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			resultObject = itemService.doService(new RequestObject(headObject, ids));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}
}