package com.cnit.yoyo.rmi.order.trade.service;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.constant.OrderConstant;
import com.cnit.yoyo.constant.OrderConstant.OrderLogBehavior;
import com.cnit.yoyo.constant.OrderConstant.OrderStatus;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.order.OrderItems;
import com.cnit.yoyo.model.order.OrderLog;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.trade.dto.OrderSellsInfoDTO;
import com.cnit.yoyo.model.trade.dto.OrderTipsDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.rmi.order.service.OrderLogService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.CommonUtil;
import com.cnit.yoyo.util.StringUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**  
* @Title: OrderManagerService.java
* @Package com.cnit.yoyo.rmi.order.service
* @Description: 卖家中心>>交易管理>>订单管理 Service
* @Author 王鹏
* @date 2015-4-18 下午3:06:43
* @version V1.0  
*/ 
@Service("orderManagerService")
@SuppressWarnings("unchecked")
public class OrderManagerService {
	private static final Log log = LogFactory.getLog(OrderManagerService.class);

	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
    private RemoteService itemService;
	
	@Autowired
	private OrderLogService orderLogService;
	
	/**
	  * @description <b>分页查询商家订单列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-21
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object qryOrderList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<Object> result=null;
		try {
			OrderQryDTO qryDTO=(OrderQryDTO)data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows()<=0?GlobalStatic.DEFAULT_PAGE_SIZE_10:qryDTO.getRows());
			result = new ResultPage<Object>(this.orderMapper.selectOrderIdsListInfo(qryDTO));
			if(result.getRows().size()>0){
				qryDTO.setOrderIds(result.getRows());
				result.setRows(this.orderMapper.selectOrderListInfo(qryDTO));
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,result);
	}
	
	/**
	  * @description <b>查询指定的订单信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-21
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object qryOrderByOrderId(Object data){
		HeadObject head = new HeadObject();
		Order order=null;
		try{
			order=orderMapper.selectOrderByOrderId((Long) data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, order);
	}
	
		/**
	     * @description 修改订单状态
	     * @detail <方法详细描述>
	     * @author <a href="">王鹏</a>
	     * @version 1.0.0
	     * @data 2015-4-15
	     * @param 
	     * @return
	     */
	public Object updateOrderStatus(Object data){
		log.info("start[OrderManagerService.updateOrderStatus]");
		HeadObject head = new HeadObject();
		try{
			Map<String, Object> map=(Map<String, Object>) data;
			Order order = orderMapper.selectByPrimaryKey(Long.valueOf(map.get("orderId").toString()));
			if(order.getAddon().equals(map.get("addon").toString())){
				map.put("addon", CommonUtil.getRandomChar());
				orderMapper.updateOrderStatus(map);
				//取消订单，库存还原
				if(map.get("status").equals(OrderConstant.OrderStatus.DEAD.getKey())){
					addOrderProductStock(map);
				}
				addOrderLog(map);
				head.setRetCode(ErrorCode.SUCCESS);
			}else{
				head.setRetCode(ErrorCode.FAILURE);
				head.setRetMsg("订单信息已被修改,请刷新后再尝试操作");
			}
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[OrderManagerService.updateOrderStatus]");
		return head;
	}
	
	public void addOrderLog(Map<String, Object> map){
		OrderLog orderLog=new OrderLog();
		orderLog.setOrderId(Long.parseLong(map.get("orderId").toString()));
		orderLog.setBillType("order");
		orderLog.setOpId(Integer.parseInt(map.get("memberId").toString()));
		orderLog.setOpName(map.get("memberName").toString());
		if(OrderStatus.UNCONFIRM.getKey().equals(map.get("status").toString())){
			orderLog.setLogText("订单等待卖家确认");
		}else if(OrderStatus.CONFIRM.getKey().equals(map.get("status").toString())){
			StringBuffer logText=new StringBuffer("卖家已确认订单");
			if(StringUtil.isNotEmpty(map.get("saleName").toString())&&StringUtil.isNotEmpty(map.get("salePhone").toString())){
				logText.append(",销售顾问:"+map.get("saleName").toString()+" 联系方式："+map.get("salePhone").toString());
			}
			orderLog.setLogText(logText.toString());
		}else if(OrderStatus.INSTALL.getKey().equals(map.get("status").toString())){
			orderLog.setLogText("卖家处理完成订单");
		}else if(OrderStatus.DEAD.getKey().equals(map.get("status").toString())){
			orderLog.setLogText("卖家取消当前订单");
		}
		orderLog.setBehavior(OrderLogBehavior.CREATE.getKey());
		orderLogService.addOrderLog(orderLog);	   
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
		Order order = orderMapper.selectOrderByOrderId(Long.valueOf(params.get("orderId").toString()));
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
	
	
    /**
     * @description <b> 查询卖家中心提示信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-11
     * @param data
     * @return
     * Object
    */
    public Object selectOrderTips(Object data){
    	HeadObject headObject=new HeadObject();
    	OrderTipsDTO orderTipsDTO=null;
    	try{
    		orderTipsDTO = orderMapper.selectOrderTips((Map<String, Object>) data);
    		headObject.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		headObject.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
    	}
    	return new ResultObject(headObject, orderTipsDTO);
    }
    
    /**
     * @description <b> 查询卖家中心订单销售信息</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-8-11
     * @param data
     * @return
     * Object
    */
    public Object selectShopSellsInfo(Object data){
    	HeadObject headObject=new HeadObject();
    	OrderSellsInfoDTO orderSellsInfoDTO=null;
    	try{
    		orderSellsInfoDTO = orderMapper.selectShopSellsInfo((Map<String, Object>) data);
    		headObject.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		headObject.setRetCode(ErrorCode.FAILURE);
    		e.printStackTrace();
    	}
    	return new ResultObject(headObject, orderSellsInfoDTO);
    }
    
}