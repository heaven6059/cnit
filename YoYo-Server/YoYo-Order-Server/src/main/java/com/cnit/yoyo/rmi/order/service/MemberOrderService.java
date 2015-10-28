package com.cnit.yoyo.rmi.order.service;
/**   
 * @Title: memberOrderService.java 
 * @Package  
 * @Description: 卖家中心订单管理service 
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-24 下午5:41:07 
 * @Copyright 2015 http://www.chinacnit.com/ All rights reserved
 * @version V1.0.0 		
 */
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.complain.model.dto.ComplainDetailDTO;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.OrderConstant;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.coupon.model.MemberCoupon;
import com.cnit.yoyo.coupon.model.dto.MemberCouponDTO;
import com.cnit.yoyo.coupon.model.dto.MemberCouponQryDTO;
import com.cnit.yoyo.dao.order.OrderItemsMapper;
import com.cnit.yoyo.dao.order.OrderLogMapper;
import com.cnit.yoyo.dao.order.PaymentLogMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.Member;
import com.cnit.yoyo.model.order.OrderItems;
import com.cnit.yoyo.model.order.OrderLog;
import com.cnit.yoyo.model.order.PaymentLog;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.order.dto.ShoppingHistoryDTO;
import com.cnit.yoyo.model.trade.dto.OrderTipsDTO;
import com.cnit.yoyo.order.dao.MemberOrderMapper;
import com.cnit.yoyo.order.model.Order;
import com.cnit.yoyo.point.model.MemberPoint;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.RedisClientUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("memberOrderService")
@SuppressWarnings("unchecked")
public class MemberOrderService {

	@Autowired
	private MemberOrderMapper memberOrderMapper;
	
	@Autowired
	private OrderLogMapper orderLogMapper;
	
	@Autowired
	private OrderItemsMapper orderItemsMapper;
	
	@Autowired
	private PaymentLogMapper paymentLogMapper;
	
    @Autowired
    public RemoteService memberService;
	
	@Autowired
    private RemoteService itemService;
	
	@Autowired
    private RedisClientUtil redisService;
	
	
	/**
	 * 
	 * @Description: 查询买家订单列表
	 * @param data
	 * @return
	 */
	public Object getOrderList(Object o) {
		HeadObject head = new HeadObject();
		ResultPage<Object> result=null;
		try{
			Map<String, Object> params = (Map<String, Object>) o;
			int  pageNum = (Integer)params.get("pageNum") ;
			int  pageSize = (Integer)params.get("pageSize") ;
			PageHelper.startPage(pageNum,pageSize );
			result = new ResultPage<Object>(this.memberOrderMapper.getOrderIdsList(params));
			if(result.getRows().size()>0){
				params.put("orderIds", result.getRows());
				result.setRows(this.memberOrderMapper.getOrderList(params));
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head, result);
	}
	
	/**
	 * 
	 * @Description: 查询指定的订单信息
	 * @param data
	 * @return
	 */
	public Object getOrderByOrderId(Object data){
		HeadObject head = new HeadObject();
		Order order=null;
		Map<String, Object> map=(Map<String, Object>) data;
		order=memberOrderMapper.getOrderByOrderId(map);
		head.setRetCode(ErrorCode.SUCCESS);
		return new ResultObject(head, order);
	}
	
	
	/**
	 * 
	 * @Description: 查询订单投诉详情信息
	 * @param data
	 * @return
	 */
	public Object getOrderComplainByOrderId(Object data){
		HeadObject head = new HeadObject();
		ComplainDetailDTO detail=null;
		try{
			Map<String, Object> map=(Map<String, Object>) data;
			detail=memberOrderMapper.getOrderComplainByOrderId(map);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head, detail);
	}
	
	
	/**
	 * 
	 * @Description: 修改订单状态
	 * @param data
	 * @return
	 */
	public Object updateOrderStatus(Object data){
		HeadObject head = new HeadObject();
		try{
			OrderLog orderLog=new OrderLog();
			Map<String, Object> map=(Map<String, Object>) data;
			//查询主单和子单信息
			Order orderInfo=memberOrderMapper.getOrderByOrderId(map);
			if(orderInfo.getAddon().equals(map.get("addon").toString())){
				map.put("orderId", orderInfo.getOrderId());
		    	//取消订单并且已付款，走退款流程
				orderLog.setOrderId(orderInfo.getOrderId());
				orderLog.setOpId(Integer.valueOf(map.get("opId").toString()));
				orderLog.setOpName(map.get("opName").toString());
				orderLog.setBillType("order");
				orderLog.setAlttime(new Date());
				//设置订单退款状态
		    	if(map.get("status").equals(OrderConstant.OrderStatus.DEAD.getKey())){
		    		orderLog.setBehavior(OrderLogBehavior.CANCEL.getKey());
		    		orderLog.setLogText("买家用户取消订单");
					addOrderProductStock(map);
					orderLogMapper.insertSelective(orderLog);
					updateAppointment(orderInfo);
				}else if(map.get("status").equals(OrderConstant.OrderStatus.FINISH.getKey())){
					orderLog.setBehavior(OrderLogBehavior.FINISH.getKey());
					orderLog.setLogText("买家用户完成订单");
					Member member=(Member) map.get("member");
					addPoint(orderInfo, member);
					addGoodsBuyCount(map);
					orderLogMapper.insertSelective(orderLog);
				}
		    	map.put("addon", CommonUtil.getRandomChar());
		    	memberOrderMapper.updateOrderStatus(map);
		    	head.setRetCode(ErrorCode.SUCCESS);
			}else{
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("订单信息已被修改,请刷新后再尝试操作");
			}
		}catch(Exception e){
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	/**
	 * @description <b>增加用户积分</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-21
	 * @param order
	 * @param member
	 * void
	*/
	private void addPoint(Order order,Member member){
		//订单使用了优惠卷
		if(1==order.getIsCoupons()){
			HeadObject couponHead=CommonHeadUtil.geneHeadObject("memberCouponService.selectMemberCouponsByOrder");
			MemberCouponQryDTO memberCouponQry=new MemberCouponQryDTO();
			memberCouponQry.setMemberId(member.getMemberId().longValue());
			memberCouponQry.setOrderId(order.getOrderId());
			ResultObject resultObject = (ResultObject)memberService.doServiceByServer(new RequestObject(couponHead, memberCouponQry));
			MemberCouponDTO memberCouponDTO = (MemberCouponDTO) resultObject.getContent();
			if(null!=memberCouponDTO&&null!=memberCouponDTO.getSalesRuleGoods()){
				if(memberCouponDTO.getSalesRuleGoods().getsTemplate().equals("promotion_solutions_getcoupon")){
					//送优惠券
					MemberCoupon memberCoupon=new MemberCoupon();
					memberCoupon.setMemberId(member.getMemberId());
					memberCoupon.setDisabled(0);
					memberCoupon.setCpnsId(Integer.valueOf(memberCouponDTO.getSalesRuleGoods().getActionSolution()));
					couponHead=CommonHeadUtil.geneHeadObject("memberCouponService.saveMemberCoupon");
					memberService.doServiceByServer(new RequestObject(couponHead, memberCoupon));	
				}else if(memberCouponDTO.getSalesRuleGoods().getsTemplate().equals("promotion_solutions_addscore")){
					//送积分
					order.setScoreG(order.getScoreG().add(new BigDecimal(memberCouponDTO.getSalesRuleGoods().getActionSolution())));
				}
			}
		}
		
		HeadObject memberHead=CommonHeadUtil.geneHeadObject("memberService.modifyMember");
		member.setPointSummation(member.getPointSummation().add(order.getScoreG()));
		member.setPointUseable(member.getPointUseable().add(order.getScoreG()).subtract(order.getScoreU()));
		member.setPointUsed(member.getPointUsed().add(order.getScoreU()));
		memberService.doServiceByServer(new RequestObject(memberHead, JSONObject.toJSON(member)));
		
		//写入获取积分
		MemberPoint memberPoint=new MemberPoint();
		memberPoint.setMemberId(member.getMemberId());
		memberPoint.setRemainPoint(member.getPointUseable().intValue());			
		HeadObject pointOutHead=CommonHeadUtil.geneHeadObject("pointService.saveMemberPoint");
		memberPoint.setInPoint(order.getScoreG().intValue());
		memberPoint.setCreatetime(new Date());
		memberPoint.setRemark("订单："+order.getOrderId()+"完成获取积分");
		memberService.doServiceByServer(new RequestObject(pointOutHead, memberPoint));
	}
	
	/**
	 * @description <b>修改预约数量</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-19
	 * @param order
	 * void
	*/
	private void updateAppointment(Order order){
		for (OrderItems orderItems : order.getOrderItems()) {
			if(!StringUtil.isEmpty(orderItems.getAppointment())){
				String key = orderItems.getProduct().getGoodsId()+"_"+orderItems.getAppointment().replace("-", "").replace(":", "").replace("|", "_");
				String count = redisService.get(key.toString());
				if(!StringUtil.isEmpty(count)){
					count = String.valueOf(Integer.valueOf(count)-1);
					redisService.set(key.toString(), count, 60*60*24);
				}
			}
		}
	}
	
	/**
	  * @description <b>增加订单商品库存</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-7-1
	  * @param @return
	  * @return Object
	*/
	private void addOrderProductStock(Map<String, Object> params){
		Order order = memberOrderMapper.getOrderByOrderId(params);
		if(null!=order&&order.getOrderItems().size()>0){
			for (OrderItems item : order.getOrderItems()) {
				JSONObject stockJSON=new JSONObject();
				stockJSON.put("productId", item.getProductId());
				stockJSON.put("stockNum", item.getNums());
				HeadObject headObject = CommonHeadUtil.geneHeadObject("productService.addStock");
				itemService.doServiceByServer(new RequestObject(headObject, stockJSON));
			}
		}
	}
	
	private void addGoodsBuyCount(Map<String, Object> params){
		Order order = memberOrderMapper.getOrderByOrderId(params);
		if(null!=order&&order.getOrderItems().size()>0){
			for (OrderItems item : order.getOrderItems()) {
				JSONObject stockJSON=new JSONObject();
				stockJSON.put("goodsId", item.getProduct().getGoodsId());
				stockJSON.put("buyCount", item.getNums());
				HeadObject headObject = CommonHeadUtil.geneHeadObject("goodsService.addGoodsBuyCount");
				itemService.doServiceByServer(new RequestObject(headObject, stockJSON));
			}
		}
	}
	
	/**
	 * @Description: 订单付款
	 * @param data
	 * @return
	 */
	public Object payOrder(Object data){
		Map<String,Object> result=new IdentityHashMap<String,Object>();
		HeadObject head = new HeadObject();
		Map<String, Object> map=(Map<String, Object>) data;
		//插入支付记录表
		PaymentLog paymentLog=JSONObject.parseObject(map.get("paymentLog").toString(), PaymentLog.class);
		paymentLogMapper.insertSelective(paymentLog);
		//查询主单和子单信息
		List<Order> orderlist=memberOrderMapper.getAllOrderInfoList(map);
		//更新主单状态，并且显示隐藏。更新子单状态，并且显示子单
		for(Order orderInfo:orderlist){
			if(orderInfo.getParentOrderId() == null){
				result.put("mainOrderId", orderInfo);
				Map<String, Object> dataMap=new HashMap<String, Object>();
	        	dataMap.put("payStatus", "1");
	        	dataMap.put("isDisplay", "0");//隐藏
	        	dataMap.put("status", "unconfirm");
	        	dataMap.put("orderId", orderInfo.getOrderId());
	        	map.put("addon", CommonUtil.getRandomChar());
	        	memberOrderMapper.updateOrderStatusAndDisable(dataMap);
			}else{
				result.put(new String("childOrderId"), orderInfo);
				Map<String, Object> dataMap=new HashMap<String, Object>();
	        	dataMap.put("payStatus", "1");
	        	dataMap.put("isDisplay", "1");//隐藏
	        	dataMap.put("status", "unconfirm");
	        	dataMap.put("orderId", orderInfo.getOrderId());
	        	map.put("addon", CommonUtil.getRandomChar());
	        	memberOrderMapper.updateOrderStatusAndDisable(dataMap);
			}
		}
		//返回主单和子单
		if(result.size() > 0){
			head.setRetCode(ErrorCode.SUCCESS);
			return new ResultObject(head, result);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
	
	/**
	 * 
	 * @Description: 删除订单或还原逻辑彻底删除
	 * @param data
	 * @return
	 */
	public Object updateOrderDisableStatus(Object data){
		HeadObject head = new HeadObject();
		try{
			Map<String, Object> map=(Map<String, Object>) data;
			memberOrderMapper.updateOrderDisableStatus(map);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return head;
	}
	
	
	public Object selectNeedAfterSalesOrder(Object data){
		HeadObject head = new HeadObject();
		ResultPage<Object> result=null;
		try{
			OrderQryDTO dto=(OrderQryDTO) data;
			PageHelper.startPage(dto.getPage(),dto.getRows());
			result=new ResultPage<Object>(memberOrderMapper.selectNeedAfterSalesOrdersCount(dto));
			if(result.getRows().size()>0){
				dto.setOrderIds(result.getRows());
				result.setRows(memberOrderMapper.selectNeedAfterSalesOrders(dto));
			}			
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, JSON.toJSON(result));
	}
	
	/**
	  * @description <b>查询购买历史记录</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-16
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findShoppingHistory(Object data){
		HeadObject head = new HeadObject();
		List<ShoppingHistoryDTO> result=null;
		try{
			OrderQryDTO dto=(OrderQryDTO) data;
			result=memberOrderMapper.selectShoppingHistory(dto);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, result);
	}
	
	
	/**
	 * @description <b>查询用户未支付的商品</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-13
	 * @param data
	 * @return
	 * Object
	*/
	public Object selectMemberUnPayProductIds(Object data){
		HeadObject head = new HeadObject();
		List<Long> result=null;
		try{
			result=memberOrderMapper.selectMemberUnPayProductIds((Integer) data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, result);
	}
	
	/**
	 * @description <b>查询用户订单统计提醒</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月29日
	 * @param data
	 * @return
	 * Object
	*/
	public Object findMemberOrderTips(Object data){
		HeadObject head = new HeadObject();
		OrderTipsDTO orderTips=null;
		try{
			orderTips=memberOrderMapper.selectMemberOrderTips((Integer) data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, orderTips);
	}
	public Object updatePayLog(Object data){
		HeadObject head = new HeadObject();
		try{
			String str=data.toString();
			String [] strs=str.split("\\#");
			for(int i=0;i<strs.length;i++){
				PaymentLog record=new PaymentLog();
				record.setTradeNo(strs[i].split("\\^")[0]);
				record.setReturnid(strs[i].split("\\^")[1]);
				paymentLogMapper.update(record);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, null);
	}
	
	public Object selectActivityGoodsCount(Object data){
		HeadObject head=new HeadObject();
		Integer count=0;
		try{
			count = orderItemsMapper.selectActivityGoodsCount((Map<String, Integer>) data);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResultObject(head, count);
	}
	
}
