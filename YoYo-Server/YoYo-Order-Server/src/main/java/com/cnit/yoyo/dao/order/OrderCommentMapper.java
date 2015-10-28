package com.cnit.yoyo.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.model.member.dto.MemberCommentDetailDTO;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.model.member.dto.OrderCommentDetailDTO;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.model.order.dto.OrderCommentDTO;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;

public interface OrderCommentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderComment record);

    int insertSelective(OrderComment record);

    OrderComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderComment record);

    int updateByPrimaryKey(OrderComment record);
    
    /**
      * @description <b>查询需要评论订单</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-6-15
      * @param @param qryDTO
      * @param @return
      * @return Order
    */
    List<OrderCommentDTO> selectNeedCommentOrder(OrderQryDTO qryDTO);
    
	/**
	  * @description <b>查询会员评论数量</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-16
	  * @param @param memberId
	  * @param @return
	  * @return List<Integer>
	*/
	List<Object> selectMemberCommentCount(MemberCommentQryDTO qryDTO);
	
	
	/**
	  * @description <b>查询用户的评论信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-15
	  * @param @param memberId
	  * @param @return
	  * @return List<MemberCommentWithBLOBs>
	*/
	List<Object> selectMemberComment(MemberCommentQryDTO commentQryDTO);
	
	OrderCommentDTO selectMemberCommentByCommentId(MemberCommentQryDTO commentQryDTO);
	
	/**
	  * @description <b>修改订单评论展示</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-17
	  * @param @param map
	  * @param @return
	  * @return int
	*/
	int updateOrderCommentDisply(Map<String, Object> map);
	
	/**
	 * @Title:  selectOrderCommentByGoodsId  
	 * @Description:  TODO(根据商品id查询评论)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-13 下午2:30:49  
	 * @version 1.0.0 
	 * @param @param goodsId
	 * @param @return
	 * @return List<Object>  返回类型 
	 * @throws
	 */
	List<OrderCommentDetailDTO> selectOrderCommentByGoodsId(Integer goodsId);
	
	/**
	 * @Title:  selectReplyCountByCommentId  
	 * @Description:  TODO(根据评论id查询回复数量)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-19 下午8:15:38  
	 * @version 1.0.0 
	 * @param @param commentId
	 * @param @return
	 * @return Integer  返回类型 
	 * @throws
	 */
	Integer selectReplyCountByCommentId(Integer commentId);
	
	/**
	 * @Title:  selectCommentCountByGoodsId  
	 * @Description:  TODO(根据商品id查询评论数量)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-16 下午6:36:33  
	 * @version 1.0.0 
	 * @param @param goodsId
	 * @param @return
	 * @return Long  返回类型 
	 * @throws
	 */
	Long selectCommentCountByGoodsId(Integer goodsId);
	
	/**
	 * @Title:  selectCommentCountByGoodsIdAndPoint  
	 * @Description:  TODO(根据商品id和评分列表查询评论数量)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-16 下午6:37:34  
	 * @version 1.0.0 
	 * @param @param goodsId
	 * @param @param pointList
	 * @param @return
	 * @return Long  返回类型 
	 * @throws
	 */
	Long selectCommentCountByGoodsIdAndPoint(@Param("goodsId")Integer goodsId, @Param("list")List<Integer> pointList);
	
	/**
	 * @Title:  selectCommentById  
	 * @Description:  TODO(根据评论id查询评论对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-20 下午5:03:03  
	 * @version 1.0.0 
	 * @param @param commentId
	 * @param @return
	 * @return List<MemberCommentDetailDTO>  返回类型 
	 * @throws
	 */
	OrderCommentDetailDTO selectCommentById(Integer commentId);
	
	/**
	 * @Title:  selectReplyByCommentId  
	 * @Description:  TODO(根据评论id查询回复列表)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-19 下午7:50:05  
	 * @version 1.0.0 
	 * @param @param commentId
	 * @param @return
	 * @return Integer  返回类型 
	 * @throws
	 */
	List<OrderCommentDetailDTO> selectReplyByCommentId(Integer commentId);
	
	/**
	 * @Title:  selectCommentOrReplyById  
	 * @Description:  TODO(根据评论id查询评论或回复对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-21 上午9:52:30  
	 * @version 1.0.0 
	 * @param @param commentId
	 * @param @return
	 * @return MemberCommentDetailDTO  返回类型 
	 * @throws
	 */
	OrderCommentDetailDTO selectReplyById(Integer commentId);
	
	/**
	 * @Title:  selectCommentByExample  
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-23 下午6:42:33  
	 * @version 1.0.0 
	 * @param @param orderComment
	 * @param @return
	 * @return List<OrderCommentDetailDTO>  返回类型 
	 * @throws
	 */
	List<OrderCommentDetailDTO> selectCommentByExample(OrderComment orderComment);
	
	/**
	 * @Title:  selectCommentCountByProductId  
	 * @Description:  TODO(根据货品id查询评论总数)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-18 下午5:15:48  
	 * @version 1.0.0 
	 * @param @param productId
	 * @param @return
	 * @return Long  返回类型 
	 * @throws
	 */
	Long selectCommentCountByProductId(Long productId);
	
	/**
	 * @Title:  selectSumPointByProductId  
	 * @Description:  TODO(根据货品id查询评分总数)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-18 下午5:22:54  
	 * @version 1.0.0 
	 * @param @param productId
	 * @param @return
	 * @return Long  返回类型 
	 * @throws
	 */
	Long selectSumPointByProductId(Long productId);
	
	OrderComment findCommentByOrderId(Long orderId);
}