package com.cnit.yoyo.goods.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 商品分类、虚拟分类
 * 
 * @author wanghb
 * @date 2015-04-22
 * @version 1.0.0
 */
@Controller
@RequestMapping("/cate")
public class CategoryController {
	public static final Logger log = LoggerFactory.getLogger(CategoryController.class);

	@Autowired
	private RemoteService itemService;

	/**
	 * @description 获取虚拟分类
	 * @version 1.0.0
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getVirtualCateTree")
	@ResponseBody
	private Object getVirtualCateTree(HttpServletRequest request) throws Exception {
		log.info("start[CategoryController.getVirtualCateTree]");
		String parentCatId = request.getParameter("parentCatId");
		JSONObject params = new JSONObject();
		if (StringUtil.isEmpty(parentCatId)) {
			params.put("parentCatId", 0);
		} else {
			params.put("parentCatId", Integer.parseInt(parentCatId));
		}
		params.put("hidden",0);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020103-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = itemService.doService(new RequestObject(headObject, params));
		log.info("end[CategoryController.getVirtualCateTree]");
		return resultObject.getContent();
	}

}
