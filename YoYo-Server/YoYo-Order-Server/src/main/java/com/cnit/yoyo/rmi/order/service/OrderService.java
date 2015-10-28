/**
 * 文 件 名   :  GoodsService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午5:56:20
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.order.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.order.OrderItemsMapper;
import com.cnit.yoyo.dao.order.OrderLogMapper;
import com.cnit.yoyo.dao.order.OrderMapper;
import com.cnit.yoyo.dao.order.OrderPaymentsMapper;
import com.cnit.yoyo.dao.order.OrderPmtMapper;
import com.cnit.yoyo.dao.order.OrderRefundsMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.order.OrderLogWithBLOBs;
import com.cnit.yoyo.model.order.OrderPayments;
import com.cnit.yoyo.model.order.OrderPmt;
import com.cnit.yoyo.model.order.OrderRefunds;
import com.cnit.yoyo.model.order.dto.OrderDTO;
import com.cnit.yoyo.model.order.dto.OrderDetailDTO;
import com.cnit.yoyo.model.order.dto.OrderItemsQryDTO;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.model.order.dto.OrderRemarkDTO;
import com.cnit.yoyo.order.util.JsonDateValueProcessor;
import com.cnit.yoyo.order.util.JsonDecimalValueProcessor;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * 订单列表
* @author ssd
* @date 2015-4-13 下午1:25:52
 */
@Service("orderServerService")
public class OrderService {
    public static final Logger log = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderItemsMapper orderItemsMapper;
    
    @Autowired
    private OrderPaymentsMapper orderPaymentsMapper;
    
    @Autowired
    private OrderRefundsMapper orderRefundsMapper;
    
    @Autowired
    private OrderPmtMapper orderPmtMapper;
    
    @Autowired
    private OrderLogMapper orderLogMapper;

    
   /**
    * 
   *
   * @Description: 查询订单列表
   * @param @param data
   * @param @return    设定文件 
   * @author ssd
   * @date 2015-4-13 下午1:26:54 
   * @return Object    返回类型 
   * @throws
    */
    public Object getOrderByParam(Object data) {
        JSONObject content = JSONObject.fromObject(data);
        PageHelper.startPage(content.getInt("pageIndex"), content.getInt("pageSize"));
        OrderQryDTO qryDTO = (OrderQryDTO)JSONObject.toBean(content.getJSONObject("qryDTO"), OrderQryDTO.class);
        //PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
        ResultPage<OrderDTO> page = new ResultPage<OrderDTO>(orderMapper.selectOrderByParam(qryDTO.getOrderId(),qryDTO.getPayStatus(),qryDTO.getStatus(),qryDTO.getShipStatusQ(),
        		qryDTO.getPayment(),qryDTO.getOrderType(),qryDTO.getSource(),
        		qryDTO.getRefundStatus(),QueryParamObject.getOrderByCause(content)));
        JsonConfig jsonConfig = new JsonConfig();  
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonDecimalValueProcessor());
        JSONObject json = JSONObject.fromObject(page,jsonConfig);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
    }
    
    /**
     * 
    *
    * @Description: 获取订单详情信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-16 下午5:52:25 
    * @return Object    返回类型 
    * @throws
     */
    public Object getOrderDetail(Object data) {
    	Long orderId = (Long)data;
        OrderDetailDTO dto = orderMapper.selectOrderDetail(orderId);
        JsonConfig jsonConfig = new JsonConfig();  
        jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonDecimalValueProcessor());
        JSONObject json = JSONObject.fromObject(dto,jsonConfig);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
    }
    
    /**
     * 
    *
    * @Description: 获取订单项信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:06:35 
    * @return Object    返回类型 
    * @throws
     */
    public Object getOrderItem(Object data) {
    	Long orderId = (Long)data;
    	List<OrderItemsQryDTO> items = orderItemsMapper.selectByOrderId(orderId);
    	JsonConfig jsonConfig = new JsonConfig();  
    	jsonConfig.registerJsonValueProcessor(BigDecimal.class, new JsonDecimalValueProcessor());
    	JSONArray array = JSONArray.fromObject(items,jsonConfig);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), array);
    }


    /**
     * 
    *
    * @Description: 獲取訂單付款信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:05:38 
    * @return Object    返回类型 
    * @throws
     */
    public Object getOrderPayment(Object data) {
    	Long orderId = (Long)data;
        OrderPayments dto = orderPaymentsMapper.selectOrderPayment(orderId);
        JsonConfig jsonConfig = new JsonConfig();  
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
        JSONObject json = JSONObject.fromObject(dto,jsonConfig);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), json);
    }
    
    /**
     * 
    *
    * @Description: 獲取訂單退款信息 
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:06:00 
    * @return Object    返回类型 
    * @throws
     */
    public Object getOrderRefund(Object data) {
    	Long orderId = (Long)data;
        OrderRefunds dto = orderRefundsMapper.selectOrderRefund(orderId);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), dto);
    }
    
    /**
     * 
    *
    * @Description: 获取订单优化方案
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:11:32 
    * @return Object    返回类型 
    * @throws
     */
    public Object getOrderPmt(Object data) {
    	Long orderId = (Long)data;
    	List<OrderPmt> pmts = orderPmtMapper.selectByOrderId(orderId);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), pmts);
    }
    
    /**
     * 
    *
    * @Description: 获取订单日志信息
    * @param @param data
    * @param @return    设定文件 
    * @author ssd
    * @date 2015-4-30 下午5:08:17 
    * @return Object    返回类型 
    * @throws
     */
    public Object getOrderLog(Object data) {
    	Long orderId = (Long)data;
    	List<OrderLogWithBLOBs> logs = orderLogMapper.selectByOrderId(orderId);
    	JsonConfig jsonConfig = new JsonConfig();  
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    	JSONArray array = JSONArray.fromObject(logs,jsonConfig);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), array);
    }
    
    /**
     * 更新订单备注信息
     */
    public Object updateOrderRemark(Object data){
		HeadObject head = new HeadObject();
		try{
			OrderRemarkDTO dto = (OrderRemarkDTO) JSONObject.toBean(JSONObject.fromObject(data), OrderRemarkDTO.class);
			int exist = orderMapper.selectCountByPrimaryKey(dto.getOrderId());
			if(exist > 0) {
				orderMapper.updateOrderRemark(dto);
				head.setRetCode(ErrorCode.SUCCESS);
			}else{
				head.setRetCode(ErrorCode.NO_DATA);
			}
			
		}catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
    
    
    /**
     * @Title:  selectProductIdFromSameOrder  
     * @Description:  TODO(根据货品id查询同时购买的其他货品id)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-29 上午10:31:10  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectProductIdFromSameOrder(Object data){
    	JSONObject jsonObject = JSONObject.fromObject(data);
		Integer productId = jsonObject.getInt("productId");
		Integer pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		PageHelper.startPage(pageIndex, pageSize);
		ResultPage<Integer> page = new ResultPage<Integer>(orderItemsMapper.selectProductIdFromSameOrder(productId));
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(page));
    }
    
    /**
     * @Title:  selectSaleCountByGoodsIdAndStatus  
     * @Description:  TODO(根据商品id和订单状态查询销量)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-6-25 下午4:09:09  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectSaleCountByGoodsIdAndStatus(Object data){
    	JSONObject jsonObject = JSONObject.fromObject(data);
		Integer goodsId = jsonObject.getInt("goodsId");
		String status = jsonObject.getString("status");
		int count = orderItemsMapper.selectSaleCountByGoodsIdAndStatus(goodsId, status);
//    	int count = orderItemsMapper.selectSaleCountByGoodsIdAndStatus(null, null);
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), count);
    }

}