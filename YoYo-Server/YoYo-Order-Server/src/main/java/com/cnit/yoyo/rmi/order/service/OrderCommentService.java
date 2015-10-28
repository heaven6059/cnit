package com.cnit.yoyo.rmi.order.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.constant.CommentConstant.CommentsType;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.order.CommentPraiseMapper;
import com.cnit.yoyo.dao.order.OrderCommentMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.CommentPraiseExample;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.model.member.dto.OrderCommentDetailDTO;
import com.cnit.yoyo.model.order.OrderComment;
import com.cnit.yoyo.model.order.dto.OrderCommentDTO;
import com.cnit.yoyo.model.order.dto.OrderQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("orderCommentService")
public class OrderCommentService {
		public static final Logger log = LoggerFactory.getLogger(OrderService.class);
		
	    @Autowired
	    public RemoteService itemService;
	    
		@Autowired
		private OrderCommentMapper orderCommentMapper;
		
		@Autowired
		private CommentPraiseMapper commentPraiseMapper;
		
		/**
		  * @description <b>保存订单评论</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-6-13
		  * @param @param object
		  * @param @return
		  * @return Object
		*/
		public Object saveOrderComment(Object object){
			HeadObject head=new HeadObject();
			OrderComment record=(OrderComment) object;
			try{
				record.setCreateTime(new Date());
				orderCommentMapper.insertSelective(record);
				HeadObject headObject = CommonHeadUtil.geneHeadObject("goodsService.addCommentsCount");
				itemService.doServiceByServer(new RequestObject(headObject,record.getGoodsId()));
				head.setRetCode(ErrorCode.SUCCESS);
			}catch (Exception e) {
				head.setRetCode(ErrorCode.FAILURE);
				log.error("订单评论保存失败",e);
			}
			return new ResultObject(head, record);
		}
		
		/**
		  * @description <b>修改订单评论</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-6-13
		  * @param @param object
		  * @param @return
		  * @return Object
		*/
		public Object updateOrderComment(Object object){
			HeadObject head=new HeadObject();
			OrderComment record=(OrderComment) object;
			try{
				orderCommentMapper.updateByPrimaryKeySelective(record);
				head.setRetCode(ErrorCode.SUCCESS);
			}catch (Exception e) {
				head.setRetCode(ErrorCode.FAILURE);
				log.error("订单评论保存失败",e);
			}
			return new ResultObject(head, record);
		}
		
		
		/**
		  * @description <b>修改订单评论</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-6-13
		  * @param @param object
		  * @param @return
		  * @return Object
		*/
		public Object updateOrderCommentDisply(Object object){
			HeadObject head=new HeadObject();
			try{
				orderCommentMapper.updateOrderCommentDisply((Map<String, Object>) object);
				head.setRetCode(ErrorCode.SUCCESS);
			}catch (Exception e) {
				head.setRetCode(ErrorCode.FAILURE);
				log.error("订单评论保存失败",e);
			}
			return new ResultObject(head);
		}
		
		
		/**
		  * @description <b>修改订单评论</b>
		  * @author 王鹏
		  * @version 1.0.0
		  * @data 2015-6-13
		  * @param @param object
		  * @param @return
		  * @return Object
		*/
		public Object deleteOrderComment(Object object){
			HeadObject head=new HeadObject();
			try{
				orderCommentMapper.deleteByPrimaryKey((Long) object);
				head.setRetCode(ErrorCode.SUCCESS);
			}catch (Exception e) {
				head.setRetCode(ErrorCode.FAILURE);
				log.error("订单评论保存失败",e);
			}
			return new ResultObject(head);
		}
		
	    /**
	      * @description <b>查询需要评论订单</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-6-15
	      * @param @param qryDTO
	      * @param @return
	      * @return Order
	    */
		public Object findNeedCommentOrder(Object data){
			HeadObject head = new HeadObject();
			List<OrderCommentDTO> order=null;
			order=orderCommentMapper.selectNeedCommentOrder((OrderQryDTO) data);
			head.setRetCode(ErrorCode.SUCCESS);
			return new ResultObject(head, order);
		}
		
		
	    /**
	      * @description <b>获取用户的评论信息</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-5-15
	      * @param @param data
	      * @param @return
	      * @return Object
	    */
	    public Object findMemberComment(Object data){
	    	HeadObject headObject=new HeadObject();
	    	ResultPage<Object> comments = null;
	    	try{
				MemberCommentQryDTO qryDTO=(MemberCommentQryDTO) data;
				qryDTO.buildOrderByField();
				PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
				comments = new ResultPage<Object>(this.orderCommentMapper.selectMemberCommentCount(qryDTO));
				if(comments.getRows().size()>0){
					qryDTO.setCommentIds(comments.getRows());
					qryDTO.setCommentsType(CommentsType.COMMENT.getKey().toString());
					comments.setRows(orderCommentMapper.selectMemberComment(qryDTO));
				}
				headObject.setRetCode(ErrorCode.SUCCESS);
	    	}catch (Exception e) {
	    		headObject.setRetCode(ErrorCode.FAILURE);
	    		log.error("获取用户评论失败", e);
	    		e.printStackTrace();
			}
	    	return new ResultObject(headObject, JSONObject.toJSON(comments));
	    }
	    
	    /**
	      * @description <b>获取用户的评论信息</b>
	      * @author 王鹏
	      * @version 1.0.0
	      * @data 2015-5-15
	      * @param @param data
	      * @param @return
	      * @return Object
	    */
	    public Object findMemberCommentByCommentId(Object data){
	    	HeadObject headObject=new HeadObject();
	    	OrderCommentDTO comments = null;
	    	try{
				MemberCommentQryDTO qryDTO=(MemberCommentQryDTO) data;
				comments=orderCommentMapper.selectMemberCommentByCommentId(qryDTO);
				headObject.setRetCode(ErrorCode.SUCCESS);
	    	}catch (Exception e) {
	    		headObject.setRetCode(ErrorCode.FAILURE);
	    		log.error("获取用户评论失败", e);
	    		e.printStackTrace();
			}
	    	return new ResultObject(headObject,comments);
	    }
	    
	    
	    /**
	     * @Title:  selectOrderCommentByGoodsId  
	     * @Description:  TODO(根据商品id查询评论列表)  
	     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	     * @date 2015-5-13 下午2:19:50  
	     * @version 1.0.0 
	     * @param @param data
	     * @param @return
	     * @return Object  返回类型 
	     * @throws
	     */
	    public Object selectOrderCommentByGoodsId(Object data){
	    	log.info("start[OrderCommentService.selectOrderCommentByGoodsId]");
	    	net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
	    	Integer pageIndex = jsonObject.getInt("pageIndex");
			Integer pageSize = jsonObject.getInt("pageSize");
			Integer goodsId = jsonObject.getInt("goodsId");
			PageHelper.startPage(pageIndex, pageSize);
			ResultPage<OrderCommentDetailDTO> commentPage = new ResultPage(orderCommentMapper.selectOrderCommentByGoodsId(goodsId));
			if(commentPage.getRows()!=null&&commentPage.getRows().size()>=1){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				CommentPraiseExample example = null;
				for(int i=0;i<commentPage.getRows().size();i++){
					if(commentPage.getRows().get(i).getOrderCreateTime()!=null){
						commentPage.getRows().get(i).setOrderCreateDate(format.format(commentPage.getRows().get(i).getOrderCreateTime()));
					}
					//查询点赞数量
//					example = new CommentPraiseExample();
//					example.createCriteria().andCommentIdEqualTo(commentPage.getRows().get(i).getCommentId().intValue());
//					commentPage.getRows().get(i).setPraise(commentPraiseMapper.countByExample(example));
					commentPage.getRows().get(i).setPraise(commentPraiseMapper.selectCountByCommentId(commentPage.getRows().get(i).getCommentId().intValue()));
					
					//查询回复数量
					commentPage.getRows().get(i).setReplyCount(orderCommentMapper.selectReplyCountByCommentId(commentPage.getRows().get(i).getCommentId().intValue()));
				}
			}
	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(commentPage));
	    }
	    
	    
	    /**
	     * @Title:  selectOrderCommentRateByGoodsId  
	     * @Description:  TODO(根据商品id查询好评度)  
	     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	     * @date 2015-5-15 下午6:54:17  
	     * @version 1.0.0 
	     * @param @param data
	     * @param @return
	     * @return Object  返回类型 
	     * @throws
	     */
	    public Object selectOrderCommentRateByGoodsId(Object data){
	    	log.info("start[MemberCommentService.selectOrderCommentRateByGoodsId]");
	    	Integer goodsId = (Integer)data;
	    	Long commentCount = orderCommentMapper.selectCommentCountByGoodsId(goodsId);
	    	List<Integer> pointList = new ArrayList<Integer>();
	    	pointList.add(5);
	    	pointList.add(4);
	    	Long positiveCount = orderCommentMapper.selectCommentCountByGoodsIdAndPoint(goodsId, pointList);
	    	pointList.clear();
	    	pointList.add(3);
	    	pointList.add(2);
	    	Long neutralCount = orderCommentMapper.selectCommentCountByGoodsIdAndPoint(goodsId, pointList);
	    	pointList.clear();
	    	pointList.add(1);
	    	Long negativeCount = orderCommentMapper.selectCommentCountByGoodsIdAndPoint(goodsId, pointList);
	    	JSONObject jsonObject = new JSONObject();
	    	if(commentCount!=null&&commentCount!=0l){
	    		jsonObject.put("positiveRate", ((double)positiveCount/commentCount)*100);
	        	jsonObject.put("neutralRate", ((double)neutralCount/commentCount)*100);
	        	jsonObject.put("negativeRate", ((double)negativeCount/commentCount)*100);
	    	}else{
	    		jsonObject.put("positiveRate", 0l);
	        	jsonObject.put("neutralRate", 0l);
	        	jsonObject.put("negativeRate", 0l);
	    	}
	    	log.info("end[MemberCommentService.selectOrderCommentRateByGoodsId]");
	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
	    	
	    }
	    
	    /**
	     * @Title:  selectCommentById  
	     * @Description:  TODO(根据主键查询评论对象)  
	     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	     * @date 2015-5-18 下午7:24:23  
	     * @version 1.0.0 
	     * @param @param data
	     * @param @return
	     * @return Object  返回类型 
	     * @throws
	     */
	    public Object selectCommentById(Object data){
	    	log.info("start[MemberCommentService.selectCommentById]");
	    	OrderCommentDetailDTO comment = orderCommentMapper.selectCommentById((Integer)data);
			if(comment != null){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(comment.getOrderCreateTime()!=null){
					comment.setOrderCreateDate(format.format(comment.getOrderCreateTime()));
				}
				if(comment.getCreateTime()!=null){
					comment.setCommentDate(format.format(comment.getCreateTime()));
				}
				//查询点赞数量
				comment.setPraise(commentPraiseMapper.selectCountByCommentId(comment.getCommentId().intValue()));
				
				//查询回复数量
				comment.setReplyCount(orderCommentMapper.selectReplyCountByCommentId(comment.getCommentId().intValue()));
			}
	    	log.info("end[MemberCommentService.selectCommentById]");
	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), comment);
	    }
	    
	    /**
		 * @Title:  selectReplyByCommentId  
		 * @Description:  TODO(根据评论id查询回复列表)  
		 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
		 * @date 2015-5-20 下午2:31:00  
		 * @version 1.0.0 
		 * @param @param data
		 * @param @return
		 * @return Object  返回类型 
		 * @throws
		 */
		public Object selectReplyByCommentId(Object data) {
			log.info("start[MemberCommentService.selectReplyByCommentId]");
	    	net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject.fromObject(data);
	    	Integer pageIndex = jsonObject.getInt("pageIndex");
			Integer pageSize = jsonObject.getInt("pageSize");
			Integer commentId = jsonObject.getInt("commentId");
			PageHelper.startPage(pageIndex, pageSize);
			ResultPage<OrderCommentDetailDTO> commentPage = new ResultPage(orderCommentMapper.selectReplyByCommentId(commentId));
			if(commentPage.getRows()!=null&&commentPage.getRows().size()>=1){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				for(int i=0;i<commentPage.getRows().size();i++){
					if(commentPage.getRows().get(i).getCreateTime()!=null){
						commentPage.getRows().get(i).setCommentDate(format.format(commentPage.getRows().get(i).getCreateTime()));
					}
				}
			}
	    	log.info("end[MemberCommentService.selectReplyByCommentId]");
//	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(commentPage));
	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), com.alibaba.fastjson.JSONObject.toJSON(commentPage));
		}
		
		/**
		 * @Title:  selectReplyById  
		 * @Description:  TODO(根据评论id查询评论或回复对象)  
		 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
		 * @date 2015-5-21 上午9:55:05  
		 * @version 1.0.0 
		 * @param @param data
		 * @param @return
		 * @return Object  返回类型 
		 * @throws
		 */
		public Object selectReplyById(Object data){
	    	log.info("start[MemberCommentService.selectReplyById]");
	    	OrderCommentDetailDTO comment = orderCommentMapper.selectReplyById((Integer)data);
			if(comment != null){
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(comment.getCreateTime()!=null){
					comment.setCommentDate(format.format(comment.getCreateTime()));
				}
			}
	    	log.info("end[MemberCommentService.selectReplyById]");
	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), comment);
	    }
		
		/**
		 * @Title:  saveCommentReply  
		 * @Description:  TODO(这里用一句话描述这个方法的作用)  
		 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
		 * @date 2015-6-23 下午6:03:39  
		 * @version 1.0.0 
		 * @param @param data
		 * @param @return
		 * @return Object  返回类型 
		 * @throws
		 */
	    public Object saveCommentReply(Object data){
	    	log.info("start[MemberCommentService.saveCommentReply]");
	    	HeadObject head = new HeadObject();
	    	try{
	    		OrderComment orderComment = (OrderComment)data;
	    		orderComment.setCreateTime(new Date());
	    		this.orderCommentMapper.insertSelective(orderComment);
	    		head.setRetCode(ErrorCode.SUCCESS);
	    	}catch (Exception e) {
	    		log.error("卖家中心>>交易管理>>评论管理>>保存商家解释信息失败", e);
	    		e.printStackTrace();
	    		head.setRetCode(ErrorCode.FAILURE);
			}
	    	log.info("end[MemberCommentService.saveCommentReply]");
	    	return new ResultObject(head);
	    }
	    
	    /**
	     * @Title:  selectCommentByBLOBs  
	     * @Description:  TODO(根据给定的例子查询对象)  
	     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	     * @date 2015-5-20 上午10:21:05  
	     * @version 1.0.0 
	     * @param @param data
	     * @param @return
	     * @return Object  返回类型 
	     * @throws
	     */
	 	public Object selectCommentByExample(Object data) {
	 		log.info("start[MemberCommentService.selectCommentByBLOBs]");
	 		List<OrderCommentDetailDTO> commentList = orderCommentMapper.selectCommentByExample((OrderComment) data);
	 		if (commentList != null && commentList.size() >= 1) {
	 			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 			for (int i = 0; i < commentList.size(); i++) {
	 				if (commentList.get(i).getCreateTime() != null) {
	 					commentList.get(i).setCommentDate(format.format(commentList.get(i).getCreateTime()));
	 				}
	 			}
	 		}
	 		log.info("end[MemberCommentService.selectCommentByBLOBs]");
	 		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), commentList);
	 	}
	 	
	 	/**
	     * @Title:  selectCommentCountByProductId  
	     * @Description:  TODO(根据货品id查询评论数量)  
	     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	     * @date 2015-5-18 下午5:14:12  
	     * @version 1.0.0 
	     * @param @param data
	     * @param @return
	     * @return Object  返回类型 
	     * @throws
	     */
	    public Object selectCommentCountByProductId(Object data){
	    	log.info("start[MemberCommentService.selectCommentCountByProductId]");
	    	Long productId = (Long)data;
	    	Long commentCount = orderCommentMapper.selectCommentCountByProductId(productId);
	    	Long sumPoint = orderCommentMapper.selectSumPointByProductId(productId);
	    	JSONObject jsonObject = new JSONObject();
	    	jsonObject.put("commentCount", commentCount!=null?commentCount:0);
	    	jsonObject.put("sumPoint", sumPoint!=null?sumPoint:0);
	    	log.info("end[MemberCommentService.selectCommentCountByProductId]");
	    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
	    }
	    
	    /**
	     * 查询订单评论
	     * @param data
	     * @return
	     */
	    public Object findCommentByOrderId(Object data){
	    	log.info("start[MemberCommentService.selectCommentCountByProductId]");
	    	Long orderId = (Long)data;
	    	OrderComment orderComment = new OrderComment();
	    	HeadObject head = new HeadObject();
	    	try{
	    		orderComment = orderCommentMapper.findCommentByOrderId(orderId);
	    		head.setRetCode(ErrorCode.SUCCESS);
	    	}catch (Exception e){
	    		e.printStackTrace();
	    		head.setRetCode(ErrorCode.FAILURE);
	    		log.error(e.toString());
	    	}
	    	log.info("end[MemberCommentService.selectCommentCountByProductId]");
	    	ResultObject result = new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.toJSON(orderComment));
	    	return result;
	    }
}
