package com.cnit.yoyo.order.controller;

/**   
 * @Title: MemberOrderController.java 
 * @Package  
 * @Description: 卖家中心订单管理Controller 
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-24 下午5:41:07 
 * @version V1.0.0 		
 */
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesReason;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRequire;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.MemberListDo;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.PaymentLog;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.paycenter.util.AlipayNotify;
//import com.cnit.yoyo.paycenter.util.AlipayNotify;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;

@Controller
@RequestMapping("/memberPayOrder")
public class MemberPayOrderController {

	public static final Logger logger = LoggerFactory.getLogger(MemberPayOrderController.class);
	
	@Autowired
	private RemoteService orderService;
	@Autowired
	private RemoteService otherService;

	/**
	 * 
	 * @Description: 订单列表页面
	 * @return
	 */
	@RequestMapping("/orderList")
	public String orderList() {
		return "pages/biz/order/orderList";
	}

	/**
	 * 
	 * @Description: 订单回收站
	 * @return
	 */
	@RequestMapping("/orderRecycleBin")
	public String orderRecycleBin() {
		return "pages/biz/order/orderRecycleBin";
	}

	/**
	 * 
	 * @Description: 申请返修列表页
	 * @return
	 */
	@RequestMapping("/applyReship")
	public String applyReship(HttpServletRequest request) {
		String orderId = request.getParameter("orderId");
		request.setAttribute("orderId", orderId);
		return "pages/biz/order/applyReship";
	}

	/**
	 * 
	 * @Description:增加返修基本信息
	 * @return
	 */
	@RequestMapping("/addReship")
	public String addReship(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId, @RequestParam(value = "itemId", required = true) Long itemId) {
		request.setAttribute("afterSalesType", AfterSalesReason.getAfterSalesReasons());
		request.setAttribute("afterSalesRequires", AfterSalesRequire.getAfterSalesRequires());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orderId", orderId);
		paramMap.put("itemId", itemId);
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02");
		try {
			ResultObject resultObject = orderService.doService(new RequestObject(headObject, paramMap));
			request.setAttribute("order", resultObject.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/order/addReship";
	}

	/**
	 * 
	 * @Description: 获取我的订单 回收站的订单列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getOrderList")
	@ResponseBody
	public Object getOrderList(HttpServletRequest request) throws Exception {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-01", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		String accountInfo = (String) CommonUtil.getSession(request).getAttribute(GlobalStatic.USER_INFO);// 获取当前用户信息
		JSONObject account = JSONObject.fromObject(accountInfo);
		MemberListDo memberDo = (MemberListDo) JSONObject.toBean(account, MemberListDo.class);
		int memberId = Integer.parseInt(memberDo.getMemberId());
		String num = request.getParameter("pageNum");
		String size = request.getParameter("pageSize");
		boolean beforeOneYear = false;
		Date createtime = null;
		int pageNum = StringUtil.isEmpty(num) ? GlobalStatic.DEFAULT_PAGE_INDEX : Integer.valueOf(num);
		int pageSize = StringUtil.isEmpty(size) ? GlobalStatic.DEFAULT_PAGE_SIZE : Integer.valueOf(size);

		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberId", memberId);
		dataMap.put("pageNum", pageNum);
		dataMap.put("pageSize", pageSize);
		dataMap.put("orderId", request.getParameter("orderId"));
		dataMap.put("name", request.getParameter("name"));
		String orderKind = request.getParameter("orderKind");
		dataMap.put("status", request.getParameter("status"));
		if (!"reshipList".equals(orderKind)) {
			dataMap.put("payStatus", request.getParameter("payStatus"));
			dataMap.put("disabled", request.getParameter("disabled"));
			int subtractMonth = Integer.parseInt(request.getParameter("createtime"));
			if (subtractMonth != 0) {
				if (subtractMonth == 13) {
					subtractMonth = 12;
					beforeOneYear = true;
				}
				createtime = getDifferentDate(subtractMonth);
			}
			dataMap.put("createtime", createtime);
			dataMap.put("beforeOneYear", beforeOneYear);
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
	public Object applyAfterSales(HttpServletRequest request, OrderQryDTO dto) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-08", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		try {
			return orderService.doService(new RequestObject(headObject, dto));
		} catch (Exception e) {
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
			return new ResultObject(headObject);
		}
	}

	/**
	 * 
	 * @Description: 得到3月内，今年内的时间等
	 * @param mon
	 * @return
	 */
	private Date getDifferentDate(int mon) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -mon);// 减去月份
		return cal.getTime();
	}

	/**
	 * 
	 * @Description: 查看订单详情页面
	 * @param request
	 * @param orderid
	 * @return
	 */
	@RequestMapping("/viewOrder")
	public String viewOrder(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
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
	public String viewPayOrder(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("orderId", orderId);
			resultObject = orderService.doService(new RequestObject(headObject, dataMap));
			request.setAttribute("order", resultObject.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/order/viewPayOrder";
	}

	@RequestMapping("/payOrder")
	public String payOrder(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-10", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("payStatus", "1");
			dataMap.put("isDisplay", "0");
			dataMap.put("status", "unconfirm");
			dataMap.put("orderId", orderId);
			resultObject = orderService.doService(new RequestObject(headObject, dataMap));
			request.setAttribute("ordermap", resultObject.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pages/biz/order/paySuccess";
	}

	/**
	 * 支付宝支付
	 * 
	 * @Description:
	 * @param request
	 * @return
	 */
	@RequestMapping("/payOrderByAli")
	public String payOrder(HttpServletRequest request, PaymentLog paymentLog) {
		logger.info("开始支付回调单号"+ paymentLog.getOut_trade_no());
		
		ResultObject resultObject = null;
		try {
			// 获取支付宝GET过来反馈信息
			Map<String, String> params = new HashMap<String, String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				params.put(name, valueStr);
			}
			// 商户订单号
			String out_trade_no = paymentLog.getOut_trade_no();
			// 交易状态
			String trade_status = request.getParameter("trade_status");
			boolean verify_result = AlipayNotify.verify(params);
			if (verify_result) {
				logger.info("支付成功");
				if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
					if(out_trade_no.startsWith("333")){
						logger.info("钣金订单"+ paymentLog.getOut_trade_no());
						HeadObject headObject1 = CommonHeadUtil.geneHeadObject(request, "990201-12", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
						JSONObject obj = new JSONObject();
						obj.put("orderId", out_trade_no);
						obj.put("type", 5);
					    resultObject = otherService.doService(new RequestObject(headObject1,obj));
					    logger.info("更新状态成功！");
						headObject1 = CommonHeadUtil.geneHeadObject(request, "990201-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
					    resultObject = otherService.doService(new RequestObject(headObject1,Long.valueOf(out_trade_no)));
					    PaintingOrders order = (PaintingOrders) resultObject.getContent();
				        request.setAttribute("order", order);
					    return "pages/biz/painting/paySuccess";
					}else{
						HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-13", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
						logger.info("普通订单"+ paymentLog.getOut_trade_no());
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("payStatus", "1");
						dataMap.put("isDisplay", "0");
						dataMap.put("status", "unconfirm");
						dataMap.put("orderId", out_trade_no);
						dataMap.put("paymentLog", JSONObject.fromObject(paymentLog));
						RequestObject requestObject=new RequestObject(headObject, dataMap);
						resultObject = orderService.doService(requestObject);
						request.setAttribute("ordermap", resultObject.getContent());
					}
					return "pages/biz/order/paySuccess";
				}
			}

		} catch (Exception e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return "error";
	}

	/**
	 * @Title: toAddSuccess
	 * @Description: TODO(成功加入购物车提示页面)
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a>
	 * @date 2015-5-13 下午6:25:28
	 * @version 1.0.0
	 * @param @param request
	 * @param @return
	 * @return Object 返回类型
	 * @throws
	 */
	@RequestMapping("/toAddSuccess")
	public Object toAddSuccess(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("productId", request.getParameter("productId"));
		mv.setViewName("pages/biz/cart/addSuccess");
		return mv;
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
	public Object canelOrder(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId, @RequestParam(value = "addon", required = true) Long addon) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-03", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			MemberListDo memberListDo = CommonUtil.getMemberListDo(request);
			if (null != memberListDo) {
				dataMap.put("status", "dead");
				dataMap.put("orderId", orderId);
				dataMap.put("opId", memberListDo.getMemberId());
				dataMap.put("opName", memberListDo.getLoginName());
				dataMap.put("addon", addon);
				resultObject = orderService.doService(new RequestObject(headObject, dataMap));
			} else {
				return CommonUtil.notLoign(headObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * 
	 * @Description: 删除订单
	 * @param request
	 * @param orderid
	 * @return
	 */
	@RequestMapping("/deleteOrder")
	@ResponseBody
	public Object deleteOrder(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long[] orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
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
	public Object orderRestore(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long[] orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
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
	public Object deleteOrderComplete(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long[] orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-07", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
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
	public String toComplain(HttpServletRequest request, @RequestParam(value = "orderId", required = true) Long orderId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-02", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
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
	public String complainDetail(HttpServletRequest request, @RequestParam(value = "complainId", required = true) Long complainId) {
		HeadObject headObject = CommonHeadUtil.geneHeadObject(request, "020104-04", GlobalStatic.SYSTEM_CODE_DATA, GlobalStatic.SYSTEM_CODE_BACK);
		ResultObject resultObject = null;
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("complainId", complainId);
			resultObject = orderService.doService(new RequestObject(headObject, dataMap));
			request.setAttribute("order", resultObject.getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/pages/biz/order/complainDetail";
	}

}
