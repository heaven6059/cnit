
package com.cnit.yoyo.order.controller;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.order.dto.OrderRemarkDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.StringUtil;

/**
 * 订单列表
* @author ssd
* @date 2015-4-10 下午4:18:36
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    public static final Logger log = LoggerFactory.getLogger(OrderController.class);
    
    @Autowired
    private RemoteService orderService;
    
    /**
     * 
    *
    * @Description: 进入订单列表主页
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-10 下午4:21:30 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/index")
    public String home() {
        return "pages/biz/order/orderIndex";
    }
    
    /**
     * 
    *
    * @Description: 获取订单列表信息 
    * @param @param request
    * @param @param qryDTO
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-10 下午5:34:22 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/orderList")
    @ResponseBody
    private Object getOrderList(HttpServletRequest request,String sort, String order, OrderQryDTO qryDTO) throws Exception {
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-01", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
        Map<String, Object> data = new HashMap<String, Object>();
       /* JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (value == null) {
                    return true;
                } else {
                    return false;
                }
            }
        });*/
        String pageIndex = request.getParameter("page");
        String pageSize = request.getParameter("rows");
        if (!StringUtils.isNotBlank(sort)) {
			sort = "t_orders.lastModified";
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
        data.put("qryDTO", qryDTO);
        data.put("sort", sort);
        data.put("order", order);
        ResultObject resultObject = orderService.doService(new RequestObject(headObject,data));
        //ResultObject resultObject = orderService.doService(new RequestObject(headObject, JSONObject.fromObject(qryDTO,jsonConfig)));
        return resultObject;
    }
    
    /**
     * 
    *
    * @Description:获取订单列表详情信息 
    * @param @param request
    * @param @param model
    * @param @param orderId
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午3:42:01 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/orderDetail")
    private String getOrderDetail(HttpServletRequest request,Model model, Long orderId) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "2000020107-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject2 = CommonHeadUtil.geneHeadObject(request, "2000020107-04", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject3 = CommonHeadUtil.geneHeadObject(request, "2000020107-05", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject4 = CommonHeadUtil.geneHeadObject(request, "2000020107-06", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject5 = CommonHeadUtil.geneHeadObject(request, "2000020107-08", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = orderService.doService(new RequestObject(headObject, orderId));
    	ResultObject resultObject1 = orderService.doService(new RequestObject(headObject1, orderId));
    	ResultObject resultObject2 = orderService.doService(new RequestObject(headObject2, orderId));
    	ResultObject resultObject3 = orderService.doService(new RequestObject(headObject3, orderId));
    	ResultObject resultObject4 = orderService.doService(new RequestObject(headObject4, orderId));
    	ResultObject resultObject5 = orderService.doService(new RequestObject(headObject5, orderId));
    	model.addAttribute("detail", resultObject.getContent());
    	model.addAttribute("orderItem", resultObject5.getContent());
    	model.addAttribute("payment", resultObject1.getContent());
    	model.addAttribute("refund", resultObject2.getContent());
    	model.addAttribute("pmt", resultObject3.getContent());
    	model.addAttribute("log", resultObject4.getContent());
        request.setAttribute("orderId", orderId);
        return "pages/biz/order/orderDetail";
    }
    
    /**
     * 
    *
    * @Description: 获取订单列表详情信息 ,该详情信息是显示在订单列表每行展开方式的详情 
    * @param @param request
    * @param @param model
    * @param @param orderId
    * @param @return
    * @param @throws Exception    设定文件 
    * @author ssd
    * @date 2015-4-30 下午3:42:24 
    * @return String    返回类型 
    * @throws
     */
    @RequestMapping("/orderDetailTab")
    private String getOrderDetailTab(HttpServletRequest request,Model model, Long orderId) throws Exception {
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-02", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "2000020107-03", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject2 = CommonHeadUtil.geneHeadObject(request, "2000020107-04", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject3 = CommonHeadUtil.geneHeadObject(request, "2000020107-05", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject4 = CommonHeadUtil.geneHeadObject(request, "2000020107-06", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	HeadObject headObject5 = CommonHeadUtil.geneHeadObject(request, "2000020107-08", GlobalStatic.SYSTEM_CODE_DATA,
                GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = orderService.doService(new RequestObject(headObject, orderId));
    	ResultObject resultObject1 = orderService.doService(new RequestObject(headObject1, orderId));
    	ResultObject resultObject2 = orderService.doService(new RequestObject(headObject2, orderId));
    	ResultObject resultObject3 = orderService.doService(new RequestObject(headObject3, orderId));
    	ResultObject resultObject4 = orderService.doService(new RequestObject(headObject4, orderId));
    	ResultObject resultObject5 = orderService.doService(new RequestObject(headObject5, orderId));
    	model.addAttribute("detail", resultObject.getContent());
    	model.addAttribute("orderItem", resultObject5.getContent());
    	model.addAttribute("payment", resultObject1.getContent());
    	model.addAttribute("refund", resultObject2.getContent());
    	model.addAttribute("pmt", resultObject3.getContent());
    	model.addAttribute("log", resultObject4.getContent());
        request.setAttribute("orderId", orderId);
        return "pages/biz/order/orderDetailTab";
    }
    
    /**
     * 
    *
    * @Description: 添加订单备注 
    * @param @param request
    * @param @param orderRemarkDTO
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午3:43:35 
    * @return Object    返回类型 
    * @throws
     */
    @RequestMapping("/insertOrderRemark")
    @ResponseBody
    public Object insertOrderRemark(HttpServletRequest request,OrderRemarkDTO orderRemarkDTO){
    	 ResultObject resultObject = null;
         try {
        	 HeadObject  headObject = CommonHeadUtil.geneHeadObject(request, "2000020107-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        	 resultObject = orderService.doService(new RequestObject(headObject, orderRemarkDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
         return  resultObject ;
    }
  
}
