package com.cnit.yoyo.painting.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.CommentConstant.CommentsType;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.Store;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.painting.dto.CarDamageQueryDTO;
import com.cnit.yoyo.model.painting.dto.PaintingOrderDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;

/**
 * 买家 我的钣金
 * @author caizhijie
 *
 */
@Controller
@RequestMapping("/mypainting")
public class MyPaintingController 
{
    public static final Logger logger = LoggerFactory.getLogger(MyPaintingController.class);
    @Autowired
    private RemoteService otherService;
    @Autowired
    private RemoteService memberService;
    @Autowired
    private RemoteService orderService;
    
	public final static Map<Integer, String> map = new HashMap<Integer, String>();  
	static {  
		map.put(0, "在线支付");
		map.put(1, "到店支付");
	}  
	
    @RequestMapping("/myDamageList")
    public Object getMyList(HttpServletRequest request, CarDamageQueryDTO dto)
    {
        logger.info("###########YOYOMem.PaintingController.getMyList-->start");
       	if(null == dto.getMemberId())
    	{
       		String memberId = (String) CommonUtil.getSession(request).getAttribute("memberId");
    		dto.setMemberId(Integer.valueOf(memberId));
    	}
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
        	resultObject = otherService.doService(new RequestObject(headObject,dto));
            request.setAttribute("damageList", resultObject.getContent());
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMem.PaintingController.getMyList-->end");
    	return "/pages/biz/painting/myList";
    }
    
    /**
     * 分页插件ajax请求
     * @param request
     * @param dto
     * @return
     */
    @RequestMapping("/ajaxData")
    @ResponseBody
    public Object ajaxGetData(HttpServletRequest request, CarDamageQueryDTO dto)
    {
        logger.info("###########YOYOMem.PaintingController.ajaxGetData-->start");
       	if(null == dto.getMemberId())
    	{
       		String memberId = (String) CommonUtil.getSession(request).getAttribute("memberId");
    		dto.setMemberId(Integer.valueOf(memberId));
    	}
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
        	resultObject = otherService.doService(new RequestObject(headObject,dto));
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        logger.info("###########YOYOMem.PaintingController.ajaxGetData-->end");
    	return resultObject.getContent();
    	
    }
    
    @RequestMapping("/offers")
    public Object viewOffers(HttpServletRequest request)
    {
    	logger.info("###########YOYOMem.PaintingController.viewOffers-->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
    	String damageId = request.getParameter("id");
        Map<String,String> map = new HashMap<String, String>();
    	map.put("damageId", damageId);
    	if( StringUtils.isNotBlank(damageId))
    	{
    		 try
    		 {
    			 resultObject = otherService.doService(new RequestObject(headObject,map));
	             request.setAttribute("offerList", resultObject.getContent());
	         } 
	         catch (Exception e) 
	         {
	             logger.error(e.getMessage());
	             e.printStackTrace();
	         }
    	}
    	logger.info("###########YOYOMem.PaintingController.viewOffers-->end");
    	return "/pages/biz/painting/offerList";
    }
    
    /**
     * 选择分店
     * @param request
     * @return
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("/chooseStore")
    public Object chooseStore(HttpServletRequest request)
    {
    	logger.info("###########YOYOMem.PaintingController.generateOrder-->start");
    	String area = "广东";
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null) 
    	{
    	    Cookie cookie;
    	    for(int i = 0; i < cookies.length; i++) 
    	    {
    	        cookie = cookies[i];
    	        if("myProvince".equals(cookie.getName()))
    	        {
    	        	try 
    	        	{
						area = URLDecoder.decode(cookie.getValue(),"UTF-8");
						break;
					} 
    	        	catch (UnsupportedEncodingException e) 
    	        	{
						logger.error("MyPaintingController.chooseStore中COOKIE取默认省份转码错误！");
						e.printStackTrace();
					}
    	        }
    	    }
    	}
    	
    	String companyId = request.getParameter("companyId");
    	String damageId = request.getParameter("damageId");
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("companyId", companyId);
    	map.put("area", area);
    	Map<String,String> map2 = new HashMap<String, String>();
    	map2.put("companyId", companyId);
    	map2.put("damageId", damageId);
    	
    	String memberId = (String)CommonUtil.getSession(request).getAttribute("memberId");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        HeadObject headObject2 = CommonHeadUtil.geneHeadObject(request, "990201-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        HeadObject headObject3 = CommonHeadUtil.geneHeadObject(request, "1000020102-34", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = new ResultObject();
        ResultObject resultObject2 = new ResultObject();
        ResultObject resultObject3 = new ResultObject();
    	if( StringUtils.isNotBlank(companyId))
    	{
    		 try
    		 {
    			 resultObject = memberService.doService(new RequestObject(headObject,map));
    			 resultObject3 = memberService.doService(new RequestObject(headObject3,memberId));
    			 resultObject2 = otherService.doService(new RequestObject(headObject2,map2));
    			 Map<String, Object> resultMap = (Map<String, Object>)resultObject.getContent();
    			 List<Store> storeList = (List<Store>)resultMap.get("storeList");
    			 String issueType = (String)resultMap.get("issueType");
	             request.setAttribute("storeList", storeList);
	             request.setAttribute("area", area);
	             request.setAttribute("issueType", issueType);
	             request.setAttribute("member", resultObject3.getContent());
	             request.setAttribute("offerList", resultObject2.getContent());
	         } 
	         catch (Exception e) 
	         {
	             logger.error(e.toString());
	             e.printStackTrace();
	         }
    	}
    	logger.info("###########YOYOMem.PaintingController.generateOrder-->end");
    	return "/pages/biz/painting/order";
    }
    
    /**
     * 提交钣金订单
     * @param request
     * @param order
     * @return
     */
    @RequestMapping("/submitOrder")
    @ResponseBody
    public Object submitOrder(HttpServletRequest request, HttpServletResponse response)
    {
    	logger.info("###########YOYOMem.PaintingController.submitOrder-->start");
    	String memberId = (String)CommonUtil.getSession(request).getAttribute("memberId");
    	Integer accountId = (Integer)CommonUtil.getSession(request).getAttribute("accountId");

    	PaintingOrders order = new PaintingOrders();
    	
    	if(null != memberId){
    		order.setMemberId(Integer.valueOf(memberId));
    	}
    	if(null != accountId){
    		order.setAccountId(accountId);
    	}
    	order.setId(Long.valueOf(generateOrderNum(request)));
    	order.setStoreId(Integer.valueOf(request.getParameter("storeId")));
    	order.setStoreName(request.getParameter("storeName"));
    	order.setCarDamageOfferId(Integer.valueOf(request.getParameter("carDamageOfferId")));
    	order.setConsumptionTime(formatDate(request.getParameter("consumptionTime"),"yyyy-MM-dd"));
    	Integer paymentId = Integer.valueOf(request.getParameter("paymentId"));
    	order.setPaymentId(paymentId);
    	order.setPayment(map.get(paymentId));
    	order.setCarDamageId(Integer.valueOf(request.getParameter("carDamageId")));
    	Date now = new Date();
    	order.setCreatetime(now);
    	order.setLastModified(now);
    	order.setStatus("create");
    	order.setPayed(new BigDecimal(request.getParameter("payed")));
    	order.setName(request.getParameter("name"));
    	order.setPhone(request.getParameter("phone"));
    	order.setMarkText(request.getParameter("markText"));
    	order.setCompanyId(Long.valueOf(request.getParameter("companyId")));
    	
    	if(null != request.getParameter("source")){
    		order.setSource(request.getParameter("source"));
    	}
    	
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-14", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = new ResultObject();
    	try {
    		Map<String , Integer> map = new HashMap<String , Integer>();
    		map.put("damageId", Integer.valueOf(request.getParameter("carDamageId")));
    		map.put("memberId", Integer.valueOf(memberId));
			resultObject = otherService.doService(new RequestObject(headObject,map));
		} catch (Exception e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
    	Integer count = (Integer)resultObject.getContent();
    	if(count !=-1 && count > 0){
    		return new ResultObject(new HeadObject(ErrorCode.FAILURE,"请勿重复提交订单，如需重新下单请先取消已下的订单！"));
    	}
        headObject = CommonHeadUtil.geneHeadObject(request, "990201-09", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		 try
		 {
			 synchronized (this) {
				 resultObject = otherService.doService(new RequestObject(headObject,order));
				 if(resultObject.getRetCode().equals(ErrorCode.FAILURE)){
					 return new ResultObject(new HeadObject(ErrorCode.FAILURE),JSONObject.fromObject("{result:false,msg:'订单提交失败'}"));
				 }
			 }
         } 
         catch (Exception e) 
         {
        	 logger.error(e.getMessage());
             e.printStackTrace();
         }
    	logger.info("###########YOYOMem.PaintingController.submitOrder-->end");
    	return resultObject;
    }
    
    private Date formatDate(String dateStr, String format)
    {
    	SimpleDateFormat simpleDateFormat  =   new  SimpleDateFormat(format); 
    	Date date = new Date();
    	try {
    		simpleDateFormat.parse(dateStr);
		} catch (ParseException e1) {
			logger.error(e1.getMessage());
			e1.printStackTrace();
		}
    	return date;
    }
    
    /**
     * 查看钣金订单详情
     * @param request
     * @param damageId
     * @return
     */
    @RequestMapping("orderDetail")
    public Object orderDetail(HttpServletRequest request, int damageId)
    {
    	logger.info("###########YOYOMem.PaintingController.orderDetail-->start");
        HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try 
        {
        	resultObject = otherService.doService(new RequestObject(headObject,damageId));
            request.setAttribute("order", resultObject.getContent());
        } 
        catch (Exception e) 
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    	logger.info("###########YOYOMem.PaintingController.orderDetail-->end");
    	return "/pages/biz/painting/orderDetail";
    }
    
    /**
     * 通过订单编号查看订单详情
     * @param request
     * @param ordrId
     * @return
     */
    @RequestMapping("viewOrder")
    public Object viewOrder(HttpServletRequest request, Long orderId)
    {
    	logger.info("###########YOYOMem.PaintingController.viewOrder-->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try 
    	{
    		resultObject = otherService.doService(new RequestObject(headObject,orderId));
    		System.out.println(    		JSONObject.fromObject(resultObject.getContent()));
    		request.setAttribute("order", resultObject.getContent());
    	} 
    	catch (Exception e) 
    	{
    		logger.error(e.getMessage());
    		e.printStackTrace();
    	}
    	logger.info("###########YOYOMem.PaintingController.viewOrder-->end");
    	return "/pages/biz/painting/orderDetail";
    }
    
     @RequestMapping("queryStoreByArea")
	 @ResponseBody
	 public Object queryStoreByArea(HttpServletRequest request){
	 	logger.info("###########YOYOMem.PaintingController.queryStoreByArea-->start");
	 	String area = request.getParameter("area");
	 	String companyId = request.getParameter("companyId");
	 	Map<String,String> map = new HashMap<String,String>();
	 	map.put("area", area);
	 	map.put("companyId", companyId);
	     HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "1000020103-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
	     ResultObject resultObject = null;
	     try 
	     {
				 resultObject = memberService.doService(new RequestObject(headObject,map));
	     } 
	     catch (Exception e) 
	     {
	         logger.error(e.getMessage());
	         e.printStackTrace();
	     }
	 	logger.info("###########YOYOMem.PaintingController.queryStoreByArea-->end");
	 	return resultObject;
	 }
     
     /**
      * 钣金订单首页
     * @return
     */
    @RequestMapping("orderList")
     public String orderList(){
    	 return "/pages/biz/painting/orderList";
     }
    
    /**
     * 查询我的钣金订单列表
     * @param request
     * @return
     */
    @RequestMapping("getOrderList")
    @ResponseBody
    public Object getOrderList(HttpServletRequest request,PaintingOrderDTO dto){
	 	logger.info("###########YOYOMem.PaintingController.getOrderList-->start");
    	int memberId = Integer.valueOf((String)CommonUtil.getSession(request).getAttribute("memberId"));
    	dto.setMemberId(memberId);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-11", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try 
		{
			 resultObject = otherService.doService(new RequestObject(headObject,dto));
		} 
		catch (Exception e) 
		{
		    logger.error(e.getMessage());
		    e.printStackTrace();
		}
		JSONObject json = JSONObject.fromObject(resultObject.getContent());
		System.out.println(json);
		logger.info("###########YOYOMem.PaintingController.getOrderList-->end");
		return resultObject.getContent();
    }
    
    /**
     * 生成订单号
     * @return
     */
    private String generateOrderNum(HttpServletRequest request) {
//    	整车 999 + 来源= 年（xx）月（xx）日（xx）+ XXXX
//    	配件 888
//    	保养 777
//    	精品 666
//    	其他 555
//    	钣金 333  
//    	10：web前端  11：web管理平台  20：appAndroid 21:appIOS
    	StringBuilder sb = new StringBuilder("333");
    	sb.append(checkFrom(request));
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String date = format.format(new Date()).replaceAll("-", "").substring(2);
		sb.append(date);
    	return sb.toString();
    }
    
    /**
     * @Title: checkFromMobile 
     * @Description: TODO(判断是否是移动设备访问) 
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-4-18
     * @version 1.0.0 
     * @param request
     * @param @return    
     * @return boolean    返回类型 
     * @throws
     */
    private String checkFrom(HttpServletRequest request) 
	{
		// \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
		// 字符串在编译时会被转码一次,所以是 "\\b"
		// \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
		String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
				+ "|windows (phone|ce)|blackberry"
				+ "|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
				+ "|laystation portable)|nokia|fennec|htc[-_]"
				+ "|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
		String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
				+ "|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

		String userAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (null == userAgent) 
		{
			userAgent = "";
		}

		// 移动设备正则匹配：手机端、平板
		Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
		Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

		// 匹配
		Matcher matcherPhone = phonePat.matcher(userAgent);
		Matcher matcherTable = tablePat.matcher(userAgent);
		if (matcherPhone.find() || matcherTable.find()) 
		{
			if (userAgent.contains("android")) 
			{
				return "20";
			} else {
				return "21";
			}
		} else {
			return "10";
		}
	} 
    
    @RequestMapping("handleOrder")
    @ResponseBody
    public Object handleOrder(HttpServletRequest request,String orderId, Integer type){
	 	logger.info("###########YOYOMem.PaintingController.handleOrder-->start");
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		HttpSession session = CommonUtil.getSession(request);
		
		ResultObject resultObject = null;
		JSONObject obj = new JSONObject();
		obj.put("orderId", orderId);
		obj.put("type", type);
		
		//日志 操作人，会员ID
		obj.put("opId", session.getAttribute("memberId"));
		obj.put("opName", session.getAttribute("loginName"));
		
		if(null != request.getParameter("oldStarus")){
			obj.put("oldStarus", request.getParameter("oldStarus"));
		}

		try 
		{
			 resultObject = otherService.doService(new RequestObject(headObject,obj));
		} 
		catch (Exception e) 
		{
		    logger.error(e.toString());
		    e.printStackTrace();
		}
		logger.info("###########YOYOMem.PaintingController.handleOrder-->end");
		return resultObject;
    }
    
    /**
     * 支付成功跳转
     * @param request
     * @param orderId
     * @param payed
     * @return
     */
    @RequestMapping("paySuccess")
    public String paySuccess(HttpServletRequest request,Long orderId, String payed){
	 	logger.info("###########YOYOMem.PaintingController.paySuccess-->start");
    	Order order = new Order();
    	order.setOrderId(orderId);
    	payed = payed.replace(",", "");
    	order.setFinalAmount(new BigDecimal(payed));
        request.setAttribute("order", order);
	 	logger.info("###########YOYOMem.PaintingController.paySuccess-->end");
        return "pages/biz/painting/paySuccess";
    }
    
    /**
     * 
     * @Description: 投诉卖家
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/complain")
    public String toComplain(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId){
	 	logger.info("###########YOYOMem.PaintingController.toComplain-->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = otherService.doService(new RequestObject(headObject, orderId));
            request.setAttribute("order", resultObject.getContent());
        } catch (Exception e) {
        	logger.error(e.toString());
            e.printStackTrace();
        }
	 	logger.info("###########YOYOMem.PaintingController.toComplain-->end");
        return "/pages/biz/painting/complain";
    }
    
    /**
     * @Description: 投诉详情页
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/complainDetail")
    public String complainDetail(HttpServletRequest request,@RequestParam(value="complainId",required=true)Long complainId){
	 	logger.info("###########YOYOMem.PaintingController.complainDetail-->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "030105-05", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
        ResultObject resultObject = null;
        try {
            resultObject = memberService.doService(new RequestObject(headObject, complainId));
            JSONObject json = JSONObject.fromObject(resultObject.getContent());
            System.err.println(json.toString());
            request.setAttribute("complain", resultObject.getContent());
        } catch (Exception e) {
        	logger.error(e.toString());
            e.printStackTrace();
        }
	 	logger.info("###########YOYOMem.PaintingController.complainDetail-->end");
        return "/pages/biz/painting/complainDetail";
    }   
    
	 /**
     * 
     * @Description: 订单评论界面
     * @param request
     * @param orderid
     * @return
     */
    @RequestMapping("/orderComment")
    public String toComment(){
        return "pages/biz/painting/comment";
    }
    
    /**
     * 增加评论
     * @param request
     * @param orderComment
     * @return
     */
    @ResponseBody
    @RequestMapping("/addOrderComment")
    public Object addOrderComment(HttpServletRequest request,OrderComment orderComment){
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-01");
    	try{
    		MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
    		if(null!=memberListDo){
    			orderComment.setCreateTime(new Date());
    			orderComment.setCommentsType(CommentsType.COMMENT.getKey().toString());
    			orderComment.setMemberId(Long.parseLong(memberListDo.getMemberId()));
	    		ResultObject resultObject = orderService.doService(new RequestObject(headObject, orderComment));
	    		return resultObject;
    		}else{
    			return CommonUtil.notLoign(headObject);
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    		return CommonUtil.processExpction(headObject);
		}
    }
    
    /**
     * 获取评论
     * @param request
     * @param orderId
     * @return
     */
    @RequestMapping("getComment")
    @ResponseBody
    public Object getComment(HttpServletRequest request,Long orderId)
    {
	 	logger.info("###########YOYOMem.MyPaintingController.getComment-->start");
    	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020105-16", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try 
    	{
    		resultObject = orderService.doService(new RequestObject(headObject,orderId));
    	} 
    	catch (Exception e) 
    	{
    		logger.error(e.toString());
    		e.printStackTrace();
    	}
    	logger.info("###########YOYOMem.MyPaintingController.getComment-->end");
    	return resultObject;
    }
    
    @RequestMapping("/applyRefunds")
    public String applyRefunds(HttpServletRequest request,@RequestParam(value="orderId",required=true)Long orderId){
	 	logger.info("###########YOYOMem.MyPaintingController.applyRefunds-->start");
	  	HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
    	ResultObject resultObject = null;
    	try 
    	{
    		resultObject = otherService.doService(new RequestObject(headObject,orderId));
    		System.out.println(    		JSONObject.fromObject(resultObject.getContent()));
    		request.setAttribute("order", resultObject.getContent());
    	}catch(Exception e){
        	e.printStackTrace();
    	 	logger.error(e.toString());
    	}
	 	logger.info("###########YOYOMem.MyPaintingController.applyRefunds-->end");

    	return "/pages/biz/painting/applyRefunds";
    }
}
