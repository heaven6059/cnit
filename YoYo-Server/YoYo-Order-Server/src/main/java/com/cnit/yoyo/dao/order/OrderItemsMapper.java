package com.cnit.yoyo.dao.order;

import com.cnit.yoyo.model.order.OrderItems;
import com.cnit.yoyo.model.order.OrderItemsExample;
import com.cnit.yoyo.model.order.dto.OrderItemsQryDTO;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface OrderItemsMapper
{
  public abstract int deleteByExample(OrderItemsExample paramOrderItemsExample);

  public abstract int deleteByPrimaryKey(Integer paramInteger);

  public abstract int insert(OrderItems paramOrderItems);

  public abstract int insertSelective(OrderItems paramOrderItems);

  public abstract List<OrderItems> selectByExampleWithBLOBs(OrderItemsExample paramOrderItemsExample);

  public abstract List<OrderItems> selectByExample(OrderItemsExample paramOrderItemsExample);

  public abstract OrderItems selectByPrimaryKey(Integer paramInteger);

  public abstract int updateByExampleSelective(@Param("record") OrderItems paramOrderItems, @Param("example") OrderItemsExample paramOrderItemsExample);

  public abstract int updateByExampleWithBLOBs(@Param("record") OrderItems paramOrderItems, @Param("example") OrderItemsExample paramOrderItemsExample);

  public abstract int updateByExample(@Param("record") OrderItems paramOrderItems, @Param("example") OrderItemsExample paramOrderItemsExample);

  public abstract int updateByPrimaryKeySelective(OrderItems paramOrderItems);

  public abstract int updateByPrimaryKeyWithBLOBs(OrderItems paramOrderItems);

  public abstract int updateByPrimaryKey(OrderItems paramOrderItems);

  public abstract List<OrderItems> selectOrderItemsInfo(Map<String, Object> paramMap);
  
  public abstract List<OrderItemsQryDTO> selectByOrderId(Long orderId);
  
  /**
   * @Title:  selectProductIdFromSameOrder  
   * @Description:  TODO(根据货品id查询同时购买的其他货品id)  
   * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
   * @date 2015-5-29 上午10:39:48  
   * @version 1.0.0 
   * @param @param productId
   * @param @return
   * @return List<Integer>  返回类型 
   * @throws
   */
  List<Integer> selectProductIdFromSameOrder(Integer productId);
  
  	/**
	 * @Title:  selectSaleCountByGoodsIdAndStatus  
	 * @Description:  TODO(根据商品id和订单状态查询销量)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-25 下午4:02:51  
	 * @version 1.0.0 
	 * @param @param goodsId
	 * @param @param status
	 * @param @return
	 * @return int  返回类型 
	 * @throws
	 */
	int selectSaleCountByGoodsIdAndStatus(@Param("goodsId")Integer goodsId, @Param("status")String status);
	
	Integer selectActivityGoodsCount(Map<String, Integer> map);
}