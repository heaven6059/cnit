
package com.cnit.yoyo.oam.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.cnit.yoyo.model.car.CarSpiderCompare;
import com.cnit.yoyo.model.car.CarYear;
import com.cnit.yoyo.model.car.dto.CarYearDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 商品数据维护
* @author ssd
 */
@Controller
@RequestMapping("/oamGoods")
public class OamGoodsController {
    public static final Logger log = LoggerFactory.getLogger(OamGoodsController.class);
    
    @Autowired
    private RemoteService itemService;

    /**
     * 
    *
    * @Description: 进入商品数据维护主页 
    * @param @return    设定文件 
    * @author ssd
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String index() {
        return "pages/biz/oam/oamGoodsIndex";
    }
    
    /**
     * 
     * @Description: 获取商品数据维护列表  
     * @author ssd
     */
    @RequestMapping("/oamGoodsList")
    @ResponseBody
    public Object getOamGoodsList(HttpServletRequest request,String sort, String order,CarSpiderCompare carSpiderCompare) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "011401-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
       if (!StringUtils.isNotBlank(sort)) {
			sort = "createTime";
			order = "desc";
		}else{
			if(sort.split(",").length>1){
				sort=sort.split(",")[sort.split(",").length-1];
			}
		}
		if(StringUtils.isNotBlank(order)){
			if(order.split(",").length>1){
				order=order.split(",")[order.split(",").length-1];
			}
		}
        data.put("pageIndex", StringUtil.isEmpty(pageIndex) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.parseInt(pageIndex));
        data.put("pageSize", StringUtil.isEmpty(pageSize) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.parseInt(pageSize));
        data.put("carSpiderCompare", carSpiderCompare);
        data.put("sort", sort);
        data.put("order", order);
        ResultObject resultObject = itemService.doService(new RequestObject(headObject,data));
        return resultObject;
    }
    
    /**
     * @throws Exception 
     * 
    *
    * @Description: 获取车系信息,用来做页面的下拉框 
    * @param @param request
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午4:28:19 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/batchCheck")
    @ResponseBody
    public Object batchCheck(HttpServletRequest request){
    	 HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "011401-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	 JSONObject jsonObject = new JSONObject();
    	 jsonObject.put("rows", request.getParameter("rows"));
    	 jsonObject.put("operator", request.getSession().getAttribute("loginName"));
    	 String ok=request.getParameter("ok");
    	 if(StringUtils.isNotBlank(ok)){
			if(ok.equals("1")){
				jsonObject.put("status",0);//审核状态。0审核通过，1审核拒绝
				jsonObject.put("cause", "");
			}else{
				jsonObject.put("status",1);
				jsonObject.put("cause", request.getParameter("cause"));
			}
    	 }
			
    	 ResultObject resultObject = null;
		try {
			resultObject = itemService.doService(new RequestObject(headObject, jsonObject));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject;
    }
    
    
  
}
