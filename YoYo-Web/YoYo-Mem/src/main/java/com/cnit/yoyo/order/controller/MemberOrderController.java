package com.cnit.yoyo.order.controller;
/**   
 * @Title: MemberOrderController.java 
 * @Package  
 * @Description: 卖家中心订单管理Controller 
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-24 下午5:41:07 
 * @version V1.0.0 		
 */
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesReason;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRequire;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/memberOrder")
public class MemberOrderController {

	@Autowired
    private RemoteService orderService;
	
	@Autowired
	private RemoteService memberService;

	/**
	 * 
	 * @Description: 订单列表页面
	 * @return
	 */
    @RequestMapping("/orderList")
    public String orderList(HttpServletRequest request,OrderQryDTO qryDTO){
    	request.setAttribute("orderQry", qryDTO);
        return "pages/biz/order/orderList";
    }
    
	/**
	 * 
	 * @Description: 订单回收站
	 * @return
	 */
    @RequestMapping("/orderRecycleBin")
    public String orderRecycleBin(){
        return "pages/biz/order/orderRecycleBin";
    }

    
	/**
	 * 
	 * @Description: 申请返修列表页
	 * @return
	 */
    @RequestMapping("/applyReship")
    public String applyReship(HttpServletRequest request){
    	String orderId  = request.getParameter("orderId");
    	request.setAttribute("orderId", orderId);
        return "pages/biz/order/applyReship";
    }
    
	/**
	 * 
	 * @Description:增加返修基本信息
	 * @return
	 */
    @RequestMapping("/addReship")
    public String addReship(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId,@RequestParam(value="itemId",required=true)Long itemId){
    	request.setAttribute("afterSalesType", AfterSalesReason.getAfterSalesReasons());
    	request.setAttribute("afterSalesRequires", AfterSalesRequire.getAfterSalesRequires());
    	Map<String, Object> paramMap=new HashMap<String, Object>();
    	paramMap.put("orderId", orderId);
    	paramMap.put("itemId", itemId);
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02");
    	try{
    		ResultObject resultObject = orderService.doService(new RequestObject(headObject, paramMap));
    		request.setAttribute("order", resultObject.getContent());
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        return "pages/biz/order/addReship";
    }
    
    /**
     * @description <b>退换货申诉界面</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年8月4日
     * @param request
     * @param orderId
     * @param itemId
     * @param returnId
     * @return
     * String
    */
    @RequestMapping("/appealReship")
    public String appealReship(HttpServletRequest request,Long orderId,Long itemId,@RequestParam(value="returnId",required=true)Long returnId){
    	try{
	    	request.setAttribute("afterSalesType", AfterSalesReason.getAfterSalesReasons());
	    	request.setAttribute("afterSalesRequires", AfterSalesRequire.getAfterSalesRequires());
	    	Map<String, Object> paramMap=new HashMap<String, Object>();
	    	paramMap.put("orderId", orderId);
	    	paramMap.put("itemId", itemId);
	    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02");
    		ResultObject resultObject = orderService.doService(new RequestObject(headObject, paramMap));
    		request.setAttribute("order", resultObject.getContent());
    		request.setAttribute("returnId", returnId);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
        return "pages/biz/order/appealReship";
    }
    
    /**
     * 
     * @Description: 获取我的订单  回收站的订单列表
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/getOrderList")
    @ResponseBody
    public Object getOrderList(HttpServletRequest request) throws Exception{ 
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
		String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);//获取当前用户信息
	    JSONObject account = JSONObject.fromObject(accountInfo);
	    MemberListDo memberDo=(MemberListDo) JSONObject.toBean(account,MemberListDo.class);
	    int memberId = Integer.parseInt(memberDo.getMemberId());
		String num =  request.getParameter("pageNum");
		String size = request.getParameter("pageSize");
		boolean  beforeOneYear = false;
		Date createtime = null; 
		int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
		int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size); 
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberId", memberId);
		dataMap.put("pageNum", pageNum);
		dataMap.put("pageSize", pageSize);
        dataMap.put("orderId",request.getParameter("orderId"));
        dataMap.put("name",request.getParameter("name"));
        String  orderKind = request.getParameter("orderKind");
        dataMap.put("status",request.getParameter("status"));
        dataMap.put("qryType", request.getParameter("qryType"));
        if(!"reshipList".equals(orderKind)){
        	dataMap.put("payStatus",request.getParameter("payStatus"));
        	dataMap.put("disabled",request.getParameter("disabled"));  
        	int subtractMonth = Integer.parseInt(request.getParameter("createtime"));
        	if(subtractMonth != 0){
        		if(subtractMonth == 13){
        			subtractMonth = 12 ;
        			beforeOneYear = true;
        		}
        		createtime = getDifferentDate(subtractMonth);
        	}
        	dataMap.put("createtime",createtime);
        	dataMap.put("beforeOneYear",beforeOneYear);
        }
      	
        resultObject = orderService.doService(new RequestObject(headObject, dataMap));
        return resultObject.getContent();
    }
    
    /**
      * @description <b>获取需要售后服务的订单</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-6-1
      * @param @param request
      * @param @return
      * @return Object
    */
    @ResponseBody
    @RequestMapping("/applyAfterSales")
    public Object applyAfterSales(HttpServletRequest request,OrderQryDTO dto){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	try{
    		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
    		if(null!=memberListDo){
	    		dto.setMemberId(Long.parseLong(memberListDo.getMemberId()));
	    		return orderService.doService(new RequestObject(headObject, dto));
    		}else{
    			return CommonUtil.notLoign(headObject);
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    		return CommonUtil.notLoign(headObject);    		
    	}
    }
    
    
   /**
    * 
    * @Description: 得到3月内，今年内的时间等
    * @param mon
    * @return
    */
    private  Date   getDifferentDate(int mon){
 		Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH,-mon);//减去月份
        return  cal.getTime(); 
     }


    /**
     * 
     * @Description: 查看订单详情页面
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/viewOrder")
    public String viewOrder(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	dataMap.put("orderId", orderId);       	
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/biz/order/viewOrder";
    }

    /**
     * 
     * @Description: 查看订单详情页面
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/viewPayOrder")
    public String viewPayOrder(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	dataMap.put("orderId", orderId);       	
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "pages/biz/order/viewPayOrder";
    }
    
    
    

    /**
     * 
     * @Description: 取消订单
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/canelOrder")
    @ResponseBody
    public Object canelOrder(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId,@RequestParam(value="addon",required=true)String addon){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
        	if(null!=memberListDo){
	        	Map<String, Object> dataMap=new HashMap<String, Object>();
	        	dataMap.put("status", "dead");
	        	dataMap.put("orderId", orderId);
	        	dataMap.put("opId", memberListDo.getMemberId());
	        	dataMap.put("opName", memberListDo.getLoginName());
	        	dataMap.put("addon", addon);
	            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }
    
    /**
     * @Title: selectMember 
     * @Description: (根据accountId查询member对象) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-20 
     * @version 1.0.0 
     * @param accountId
     * @param loginStatus
     * @param request
     * @param @return
     * @param @throws Exception    
     * @return Member    返回类型 
     * @throws
     */
    private Member selectMember(Integer accountId,HttpServletRequest request) throws Exception{
		if (accountId != null && accountId != 0) 
		{
			HeadObject headObject = CommonHeadUtil.geneHeadObject(request,"1000020102-17", 
					GlobalStatic.SYSTEM_CODE_DATA,GlobalStatic.SYSTEM_CODE_BACK);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("accountId", accountId);
			ResultObject resultObject = memberService.doService(new RequestObject(headObject, jsonObject));
			return (Member) JSONObject.toBean(JSONObject.fromObject(resultObject.getContent()),Member.class);
		}
		return null;
	}
    
    /**
     * @description <b>修改订单状态</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-6-25
     * @param @param request
     * @param @param orderid
     * @param @param status
     * @param @return
     * @return Object
   */
   @ResponseBody
   @RequestMapping("/updateStatus")
   public Object updateStatus(HttpServletRequest request,@RequestParam(value="orderid",required=true)Long orderid,@RequestParam(value="status",required=true)String status,@RequestParam(value="addon",required=true)String addon){
	   HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
       try {
	       	Map<String, Object> dataMap=new HashMap<String, Object>();
	       	MemberListDo memberListDo=CommonUtil.getMemberListDo(request);
	    	if(null!=memberListDo){
		       	dataMap.put("status", status);
		       	dataMap.put("orderId", orderid);
	        	dataMap.put("opId", memberListDo.getMemberId());
	        	dataMap.put("opName", memberListDo.getLoginName());
	        	dataMap.put("member", this.selectMember(memberListDo.getAccountId(), request));
	        	dataMap.put("addon", addon);
		       	ResultObject resultObject = orderService.doService(new RequestObject(headObject, dataMap));
		       	return resultObject;
	    	}else{
	    		return CommonUtil.notLoign(headObject);
	    	}
       } catch (Exception e) {
           e.printStackTrace();
           return CommonUtil.processExpction(headObject);
       }
   }
 
    /**
     * 
     * @Description: 删除订单
     * @param request
     * @param orderid
     * @return
     */
  	@ResponseBody
    @RequestMapping("/deleteOrder")
    public Object deleteOrder(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long [] orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	dataMap.put("disabled", "1");
        	dataMap.put("ids", orderId);
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }

    /**
     * 
     * @Description: 还原订单
     * @param request
     * @param orderid
     * @return
     */
    @ResponseBody
    @RequestMapping("/orderRestore")
    public Object orderRestore(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long [] orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	dataMap.put("disabled", "0");
        	dataMap.put("ids", orderId);
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultObject;
    }   
    
    
    /**
     * 
     * @Description: 彻底删除订单
     * @param request
     * @param orderid
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteOrderComplete")
    public Object deleteOrderComplete(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long [] orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try {
    		Map<String, Object> dataMap=new HashMap<String, Object>();
    		dataMap.put("disabled", "2");
    		dataMap.put("ids", orderId);
    		resultObject = orderService.doService(new RequestObject(headObject, dataMap));
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return resultObject;
    }
    
    
    /**
     * 
     * @Description: 投诉卖家
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/complainPage")
    public String toComplain(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	dataMap.put("orderId", orderId);
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/biz/order/complainPage";
    }
    
    /**
     * 
     * @Description: 投诉详情页
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/complainDetail")
    public String complainDetail(HttpServletRequest request,@RequestParam(value="complainId",required=true)Long complainId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
        	Map<String, Object> dataMap=new HashMap<String, Object>();
        	MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
        	dataMap.put("complainId", complainId);
            resultObject = orderService.doService(new RequestObject(headObject, dataMap));
            request.setAttribute("order", resultObject.getContent());
            request.setAttribute("memberListDo", memberListDo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/pages/biz/order/complainDetail";
    }   
    
    
    /**
     * @description <b>申请退款</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015年7月23日
     * @param request
     * @return
     * String
    */
    @RequestMapping("/applyRefunds")
    public String applyRefunds(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
    	try{
	    	Map<String, Object> dataMap=new HashMap<String, Object>();
	    	dataMap.put("orderId", orderId);       	
	        resultObject = orderService.doService(new RequestObject(headObject, dataMap));
	        request.setAttribute("order", resultObject.getContent());
    	}catch(Exception e){
        	e.printStackTrace();
    	}
    	return "/pages/biz/order/applyRefunds";
    }
}
