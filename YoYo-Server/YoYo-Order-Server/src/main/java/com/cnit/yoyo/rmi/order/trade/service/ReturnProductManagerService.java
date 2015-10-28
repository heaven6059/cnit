package com.cnit.yoyo.rmi.order.trade.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.AfterSalesConstant;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.constant.OrderConstant.OrderStatus;
import com.cnit.yoyo.dao.order.OrderLogMapper;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.dao.order.OrderRefundsBillsMapper;
import com.cnit.yoyo.dao.order.PaymentLogMapper;
import com.cnit.yoyo.dao.order.PaymentRefundsDetailMapper;
import com.cnit.yoyo.dao.order.PaymentRefundsMapper;
import com.cnit.yoyo.dao.trade.AftersalesReturnLogMapper;
import com.cnit.yoyo.dao.trade.AftersalesReturnProductMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.OrderLog;
import com.cnit.yoyo.model.order.PaymentLog;
import com.cnit.yoyo.model.order.PaymentRefunds;
import com.cnit.yoyo.model.order.PaymentRefundsDetail;
import com.cnit.yoyo.model.painting.PaintingOrders;
import com.cnit.yoyo.model.trade.AftersalesReturnLogWithBLOBs;
import com.cnit.yoyo.model.trade.AftersalesReturnProductWithBLOBs;
import com.cnit.yoyo.model.trade.dto.AfterSalesQryDTO;
import com.cnit.yoyo.model.trade.dto.RefundsProductDTO;
import com.cnit.yoyo.model.trade.dto.ReturnProductDTO;
import com.cnit.yoyo.reship.model.ReshipDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @Title: OrderManagerService.java
 * @Package com.cnit.yoyo.rmi.order.service
 * @Description: 卖家中心>>交易管理>>退货管理 Service
 * @Author 王鹏
 * @date 2015-4-18 下午3:06:43
 * @version V1.0
 */
@Service("returnProductManagerService")
@SuppressWarnings("unchecked")
public class ReturnProductManagerService {

	private static final Log log = LogFactory.getLog(ReturnProductManagerService.class);

	@Autowired
	private OrderLogMapper orderLogMapper;

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private AftersalesReturnProductMapper aftersalesReturnProductMapper;

	@Autowired
	private OrderRefundsBillsMapper orderRefundsBillsMapper;
	
	@Autowired
	private AftersalesReturnLogMapper aftersalesReturnLogMapper;
	@Autowired
	private PaymentRefundsMapper paymentRefundsMapper;
	@Autowired
	private PaymentRefundsDetailMapper paymentRefundsDetailMapper;
	@Autowired
	private PaymentLogMapper paymentLogMapper;
	
	@Autowired
	private RemoteService otherService;

	/**
	 * @description 获取退货申请列表
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-4-16
	 * @param
	 * @return
	 */
	public Object qryReturnProductList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ReturnProductDTO> resultPage = null;
		try {
			Map<String, Object> params = (Map<String, Object>) data;
			PageHelper.startPage((Integer) params.get("pageIndex"), (Integer) params.get("pageSize"));
			resultPage = new ResultPage<ReturnProductDTO>(this.aftersalesReturnProductMapper.selectReturnProductInfo(params));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, resultPage);
	}

	/**
	 * @description 获取退货申请列表
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-4-16
	 * @param
	 * @return
	 */
	public Object qryReturnProductById(Object data) {
		HeadObject head = new HeadObject();
		RefundsProductDTO returnProductDTO = null;
		try {
			returnProductDTO = this.aftersalesReturnProductMapper.selectReturnProductById((Long) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, returnProductDTO);
	}

	/**
	 * @description 获取退货申请列表
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-4-16
	 * @param
	 * @return
	 */
	public Object findRefundsProductById(Object data) {
		HeadObject head = new HeadObject();
		RefundsProductDTO refundsProduct = null;
		try {
			refundsProduct = this.aftersalesReturnProductMapper.selectRefundsProductById((Map<String, Object>) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, refundsProduct);
	}

	/**
	 * @description 卖家商品退货处理 修改处理状态
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-4-16
	 * @param
	 * @return
	 */
	public Object updateReturnProductStatus(Object data) {
		HeadObject head = new HeadObject();
		log.info("star[ReturnProductManagerService.updateReshipStatus]");
		try {
			ReshipDTO dto = (ReshipDTO) data;
			Long orderId = dto.getOrderId();
			if(null != orderId && orderId.toString().startsWith("333")){
				HeadObject headObject = CommonHeadUtil.geneHeadObject("paintingService.updateOrder");
				PaintingOrders order = new PaintingOrders();
				order.setId(orderId);
				order.setRefundStatus(dto.getStatus().toString());
				otherService.doServiceByServer(new RequestObject(headObject,order));
			}else if(null != orderId){
				Order order = orderMapper.selectByPrimaryKey(dto.getOrderId());
				order.setOrderId(dto.getOrderId());
				order.setRefundStatus(dto.getStatus().toString());
				this.orderMapper.updateByPrimaryKeySelective(order);// 修改订单退货状态
			}

			AftersalesReturnProductWithBLOBs aftersalesReturnProductWithBLOBs = aftersalesReturnProductMapper.selectByPrimaryKey(dto.getReturnId());
			aftersalesReturnProductWithBLOBs.setReturnId(dto.getReturnId());
			aftersalesReturnProductWithBLOBs.setStatus(dto.getStatus());
			aftersalesReturnProductWithBLOBs.setSellerReason(dto.getSellerReason());
			aftersalesReturnProductWithBLOBs.setAmount(dto.getAmount());
			aftersalesReturnProductWithBLOBs.setSellerComment(dto.getSellerComment());
			aftersalesReturnProductWithBLOBs.setInterevenComment(dto.getInterevenComment());
			String interevenImage = dto.getInterevenImage();
			if (StringUtils.isNotEmpty(interevenImage)) {
				interevenImage = interevenImage.substring(0, interevenImage.length() - 1);
				aftersalesReturnProductWithBLOBs.setInterevenImage(interevenImage);
			}
			this.aftersalesReturnProductMapper.updateByPrimaryKeySelective(aftersalesReturnProductWithBLOBs);
			AftersalesReturnLogWithBLOBs log = new AftersalesReturnLogWithBLOBs();
			log.setOrderId(dto.getOrderId());
			log.setReturnId(dto.getReturnId());
			log.setLogText(dto.getLogText());
			log.setBehavior(dto.getStatus());
			log.setOpId(dto.getOpId());
			log.setOpName(dto.getOpName());
			log.setAlttime(new Date());
			log.setRole(dto.getRole());
			this.aftersalesReturnLogMapper.insertSelective(log);// 写入操作日志
			// 同意退款
			if ((dto.getStatus().equals(AfterSalesConstant.AfterSalesStatus.STATUS_2.getKey()) && aftersalesReturnProductWithBLOBs.getSafeguardType().equals(AfterSalesConstant.AfterSalesRequire.NEED_REFUND.getKey())) || aftersalesReturnProductWithBLOBs.getStatus().equals(AfterSalesConstant.AfterSalesStatus.STATUS_7.getKey())) {
				OrderLog orderLog = new OrderLog();
				orderLog.setOrderId(dto.getOrderId());
				orderLog.setBillType("order");
				orderLog.setAlttime(new Date());
				orderLog.setOpId(0);
				orderLog.setOpName("系统");
				orderLog.setLogText("退款申请提交成功平台正在进行退款审核，请耐心等待。");
				orderLog.setBehavior(OrderLogBehavior.REFUNDS.getKey());
				orderLogMapper.insertSelective(orderLog);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ReturnProductManagerService.updateReshipStatus]");
		return new ResultObject(head);
	}

	/**
	 * @description <b>加载平台介入的售后申请</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月6日
	 * @param @param
	 *            object
	 * @param @return
	 * @return Object
	 */
	public Object findAdminInterevenAfterSales(Object object) {
		HeadObject head = new HeadObject();
		ResultPage<ReturnProductDTO> resultPage = null;
		try {
			AfterSalesQryDTO qryDTO = (AfterSalesQryDTO) object;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			resultPage = new ResultPage<>(aftersalesReturnProductMapper.selectAfterSalesAppeal(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSONObject.toJSON(resultPage));
	}

	/**
	 * @description <b>查找需要退款审核的退款单</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月24日
	 * @param object
	 * @return Object
	 */
	public Object findNeedRefundsList(Object object) {
		HeadObject head = new HeadObject();
		ResultPage<RefundsProductDTO> resultPage = null;
		try {
			AfterSalesQryDTO qryDTO = (AfterSalesQryDTO) object;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			resultPage = new ResultPage<RefundsProductDTO>(aftersalesReturnProductMapper.selectNeedRefundsList(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSONObject.toJSON(resultPage));
	}

	/**
	 * @description <b>修改退款状态</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月24日
	 * @param object
	 * @return Object
	 */
	public Object updateRefundsStatus(Object object) {
		HeadObject head = new HeadObject();
		try {
			ReshipDTO dto = (ReshipDTO) object;
			AftersalesReturnProductWithBLOBs withBLOBs = aftersalesReturnProductMapper.selectByPrimaryKey(dto.getReturnId());
			if (withBLOBs.getIsSafeguard() == 1) {
				Long orderId = dto.getOrderId();
				if(null != orderId && orderId.toString().startsWith("333")){
					HeadObject headObject = CommonHeadUtil.geneHeadObject("paintingService.updateOrder");
					PaintingOrders order = new PaintingOrders();
					order.setId(orderId);
					order.setRefundStatus(dto.getStatus().toString());
					otherService.doServiceByServer(new RequestObject(headObject,order));
				}else if(null != orderId){
					Order order = orderMapper.selectByPrimaryKey(dto.getOrderId());
					order.setOrderId(dto.getOrderId());
					order.setRefundStatus(dto.getStatus().toString());
					// 退款完成，且是售前退款将订单修改为取消订单
					if (dto.getStatus().equals(AfterSalesConstant.AfterSalesStatus.STATUS_10.getKey()) && withBLOBs.getIsSafeguard() == 1) {
						order.setStatus(OrderStatus.DEAD.getKey());
					}
					orderMapper.updateByPrimaryKeySelective(order);
				}
				
			}

			OrderLog orderLog = new OrderLog();
			orderLog.setOrderId(dto.getOrderId());
			orderLog.setOpId(dto.getOpId());
			orderLog.setOpName(dto.getOpName());
			orderLog.setBillType("order");
			orderLog.setAlttime(new Date());
	    	if(dto.getStatus().equals(AfterSalesConstant.AfterSalesStatus.STATUS_10.getKey())){
	    		orderLog.setBehavior(OrderLogBehavior.REFUNDS.getKey());
	    		orderLog.setLogText("退款平台审核通过，退款金额将退回至支付账户");
			}else if(dto.getStatus().equals(AfterSalesConstant.AfterSalesStatus.STATUS_11.getKey())){
				orderLog.setLogText("退款平台审核不通过");
				orderLog.setBehavior(OrderLogBehavior.REFUNDS.getKey());
			}else if(dto.getStatus().equals(AfterSalesConstant.AfterSalesStatus.STATUS_12.getKey())){
				orderLog.setLogText("已退款至支付账户,退款完成");
				orderLog.setBehavior(OrderLogBehavior.REFUNDS.getKey());
			}
			orderLogMapper.insertSelective(orderLog);

			withBLOBs.setStatus(dto.getStatus());
			aftersalesReturnProductMapper.updateByPrimaryKeySelective(withBLOBs);
			// 写入日志
			AftersalesReturnLogWithBLOBs log = new AftersalesReturnLogWithBLOBs();
			log.setOrderId(withBLOBs.getOrderId());
			log.setReturnId(withBLOBs.getReturnId());
			log.setLogText(dto.getLogText());
			log.setBehavior(withBLOBs.getStatus());
			log.setOpId(dto.getOpId());
			log.setOpName(dto.getOpName());
			log.setAlttime(new Date());
			log.setRole(dto.getRole());
			this.aftersalesReturnLogMapper.insertSelective(log);// 写入操作日志
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}

	/**
	 * 支付宝异步回调
	 * @Description: 
	 * @param object
	 * @return
	 */
	public Object updateRefundsStatusNotify(Object object) {
		HeadObject head = new HeadObject();
		try {
			PaymentRefunds dto = (PaymentRefunds) object;
			dto.setCreateTime(new Date());
			paymentRefundsMapper.insertSelective(dto);
			String details=dto.getResultDetails();
			String [] detailArray=details.split("\\#");//交易号^退款金额^处理结果
			for(int i=0;i<detailArray.length;i++){
				String tradeNo=detailArray[i].split("\\^")[0];//交易号
				String feel=detailArray[i].split("\\^")[1];//退款金额
				String result=detailArray[i].split("\\^")[2];//处理结果
				PaymentRefundsDetail detail=new PaymentRefundsDetail();
				detail.setBatchNo(dto.getBatchNo());
				detail.setFeel(feel);
				detail.setTradeNo(tradeNo);
				detail.setResult(result);
				paymentRefundsDetailMapper.insertSelective(detail);
				if(result.equals(ErrorCode.SUCCESS)){
					PaymentLog selectLog=new PaymentLog();
					selectLog.setTradeNo(tradeNo);
					selectLog.setIsSuccess("T");
					List<PaymentLog> paymentLogList=paymentLogMapper.selectPaymentLog(selectLog);
					if(null!=paymentLogList && paymentLogList.size()>0){
						PaymentLog paymentLog=paymentLogList.get(0);
						AftersalesReturnProductWithBLOBs withBLOBs =new AftersalesReturnProductWithBLOBs();
						withBLOBs.setStatus(12);
						withBLOBs.setReturnId(Long.parseLong(paymentLog.getReturnid()));
						withBLOBs.setOrderId(paymentLog.getOrderId());
						aftersalesReturnProductMapper.updateByPrimaryKeySelective(withBLOBs);
					}else{
						log.error("交易流水未找到对应的支付信息："+tradeNo);
						head.setRetCode(ErrorCode.FAILURE);
					}
				}
			}
			
		} catch (Exception e) {
			log.error(e);
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
}
