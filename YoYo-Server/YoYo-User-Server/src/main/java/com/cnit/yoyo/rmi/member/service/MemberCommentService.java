
package com.cnit.yoyo.rmi.member.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.CommentConstant;
import com.cnit.yoyo.constant.CommentConstant.CommentsType;
import com.cnit.yoyo.constant.CommentConstant.ObjeType;
import com.cnit.yoyo.constant.ErrorCode;
//import com.cnit.yoyo.dao.member.CommentPraiseMapper;
import com.cnit.yoyo.dao.member.MemberCommentMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.domain.member.MemberCommentDo;
import com.cnit.yoyo.model.member.CommentPraiseExample;
import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.model.member.dto.MemberCommentDTO;
import com.cnit.yoyo.model.member.dto.MemberCommentDetailDTO;
import com.cnit.yoyo.model.member.dto.MemberCommentQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**
 * @description 会员咨询，评论等的service
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
@SuppressWarnings("unchecked")
@Service("memberCommentService")
public class MemberCommentService {
	
	private static final Log log = LogFactory.getLog(MemberCommentService.class);

	@Autowired
    private MemberCommentMapper memberCommentMapper;
//	@Autowired
//    private CommentPraiseMapper commentPraiseMapper;

    /**
      * @description <b>按条件查询咨询列表信息</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param data
      * @param @return
      * @param @throws Exception
      * @return Object
    */
    public Object findMemberAskByParam(Object data) throws Exception {
        HeadObject head = new HeadObject();
        MemberCommentQryDTO commentQryDTO=(MemberCommentQryDTO)data;
        PageHelper.startPage(commentQryDTO.getPage(), commentQryDTO.getRows());
		ResultPage<MemberCommentDo> page = new ResultPage<MemberCommentDo>(memberCommentMapper.findMemberAskByParam(commentQryDTO));
        head.setRetCode(ErrorCode.SUCCESS);      
        return new ResultObject(head, com.alibaba.fastjson.JSON.toJSON(page));
    }
   
    
    /**
      * @description <b>根据主键Id获取评论咨询信息</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-22
      * @param @param data
      * @param @return
      * @return Object
    */
    public Object findMemberAskById(Object data){
    	HeadObject head = new HeadObject();
    	MemberCommentDTO order=null;
		try{
			order=memberCommentMapper.findMemberAskById((Long) data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, order);
    }
    
    
    
   /**
	  * @description <b>查询"卖家中心>>交易管理>>评论管理" 列表数据</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-4-20
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
    public Object findCommentsForBusiness(Object data){
    	HeadObject head = new HeadObject();
    	ResultPage<Object> resultPage=null;
    	try{
    		MemberCommentQryDTO qryDTO=(MemberCommentQryDTO) data;
			PageHelper.startPage(qryDTO.getPage(),qryDTO.getRows());
			resultPage = new ResultPage<Object>(this.memberCommentMapper.findCommentsForBusinessCount(qryDTO));
			qryDTO.setCommentIds(resultPage.getRows());
			if(resultPage.getRows().size()>0){
				List<Object> result = this.memberCommentMapper.findCommentsForBusiness(qryDTO);
				resultPage.setRows(result);
			}
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		log.error("卖家中心>>交易管理>>评论管理>>查询商品评论列表失败", e);
    		e.printStackTrace();
    		head.setRetCode(ErrorCode.FAILURE);
		}
    	return new ResultObject(head,resultPage);
   }
   
    
    
    
    /**
      * @description <b>保存回复信息</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-20
      * @param @param data
      * @param @return
      * @return Object
    */
    public Object saveCommentReply(Object data){
    	log.info("start[MemberCommentService.saveCommentReply]");
    	HeadObject head = new HeadObject();
    	try{
			MemberCommentWithBLOBs memberComment=(MemberCommentWithBLOBs) data;
    		this.memberCommentMapper.insertSelective(memberComment);
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
      * @description <b>设置评论是否显示</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-21
      * @param @param data
      * @param @return
      * @return Object
    */
    public Object updateCommentDisplay(Object data){
    	log.info("start[MemberCommentService.setCommentDisPlay]");
    	HeadObject head = new HeadObject();
    	try{
    		Map<String, Object> params=(Map<String, Object>) data;
    		this.memberCommentMapper.batchUpdateMemberCommentDisplay(params);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		log.error("修改评论信息是否显示失败", e);
    		e.printStackTrace();
    		head.setRetCode(ErrorCode.FAILURE);
		}
    	log.info("end[MemberCommentService.setCommentDisPlay]");
    	return new ResultObject(head);
    }
    
    /**
      * @description <b>删除用户评论,咨询等信息(逻辑删除)</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-4-23
      * @param @param data
      * @param @return
      * @return Object
    */
    public Object deleteMemberComemnt(Object data){
    	log.info("start[MemberCommentService.deleteMemberComemnt]");
    	HeadObject head = new HeadObject();
    	try{
    		String [] commentIds=(String[]) data;
    		this.memberCommentMapper.batchDeleteMemberComment(commentIds);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		log.error("用户评论信息删除失败",e);
    		e.printStackTrace();
    		head.setRetCode(ErrorCode.FAILURE);
    	}
    	log.info("end[MemberCommentService.deleteMemberComemnt]");
    	return head;
    }
    
    
    /**
     * @Title:  selectDiscussCommentByGoodsId  
     * @Description:  TODO(根据商品id查询商品咨询列表)  
     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
     * @date 2015-5-13 下午3:19:47  
     * @version 1.0.0 
     * @param @param data
     * @param @return
     * @return Object  返回类型 
     * @throws
     */
    public Object selectDiscussCommentByGoodsId(Object data){
    	HeadObject headObject=new HeadObject();
	   	ResultPage<Object> comments = null;
	   	try{
			MemberCommentQryDTO qryDTO=(MemberCommentQryDTO) data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			qryDTO.setObjectType(ObjeType.ASK.getKey());
			comments = new ResultPage<Object>(this.memberCommentMapper.selectGoodsConsultCount(qryDTO));
			if(comments.getRows().size()>0){
				qryDTO.setCommentIds(comments.getRows());
				comments.setRows(memberCommentMapper.selectGoodsConsult(qryDTO));
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
	   	}catch (Exception e) {
	   		headObject.setRetCode(ErrorCode.FAILURE);
	   		log.error(e);
	   		e.printStackTrace();
		}
	   	return new ResultObject(headObject, com.alibaba.fastjson.JSONObject.toJSON(comments));
    	
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
    	Long commentCount = memberCommentMapper.selectCommentCountByGoodsId(goodsId);
    	List<Integer> pointList = new ArrayList<Integer>();
    	pointList.add(5);
    	Long positiveCount = memberCommentMapper.selectCommentCountByGoodsIdAndPoint(goodsId, pointList);
    	pointList.clear();
    	pointList.add(4);
    	pointList.add(3);
    	Long neutralCount = memberCommentMapper.selectCommentCountByGoodsIdAndPoint(goodsId, pointList);
    	pointList.clear();
    	pointList.add(2);
    	pointList.add(1);
    	Long negativeCount = memberCommentMapper.selectCommentCountByGoodsIdAndPoint(goodsId, pointList);
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
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			comments = new ResultPage<Object>(this.memberCommentMapper.selectMemberCommentCount(qryDTO.getMemberId()));
			if(comments.getRows().size()>0){
				qryDTO.setCommentIds(comments.getRows());
				qryDTO.setCommentsType(CommentsType.COMMENT.getKey());
				comments.setRows(memberCommentMapper.selectMemberComment(qryDTO));
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		headObject.setRetCode(ErrorCode.FAILURE);
    		log.error(e);
    		e.printStackTrace();
		}
    	return new ResultObject(headObject, com.alibaba.fastjson.JSONObject.toJSON(comments));
    }
    
    /**
      * @description <b>查询用户评论的回复信息</b>
      * @author 王鹏
      * @version 1.0.0
      * @data 2015-5-16
      * @param @param data
      * @param @return
      * @return Object
    */
    public Object findMemberCommentReply(Object data){
    	HeadObject headObject=new HeadObject();
    	List<MemberCommentWithBLOBs> list = null;
    	try{
    		MemberCommentQryDTO commentQryDTO=(MemberCommentQryDTO) data;
    		commentQryDTO.setCommentsType(CommentsType.EXPLAIN.getKey());
    		list = memberCommentMapper.selectMemberCommentReply(commentQryDTO);
    		headObject.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		headObject.setRetCode(ErrorCode.FAILURE);
    		log.error(e);
    		e.printStackTrace();
		}
    	return new ResultObject(headObject, com.alibaba.fastjson.JSONObject.toJSON(list));
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
    	Long commentCount = memberCommentMapper.selectCommentCountByProductId(productId);
    	Long sumPoint = memberCommentMapper.selectSumPointByProductId(productId);
    	JSONObject jsonObject = new JSONObject();
    	jsonObject.put("commentCount", commentCount!=null?commentCount:0);
    	jsonObject.put("sumPoint", sumPoint!=null?sumPoint:0);
    	log.info("end[MemberCommentService.selectCommentCountByProductId]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), jsonObject);
    }
    
//    /**
//     * @Title:  selectCommentById  
//     * @Description:  TODO(根据主键查询评论对象)  
//     * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
//     * @date 2015-5-18 下午7:24:23  
//     * @version 1.0.0 
//     * @param @param data
//     * @param @return
//     * @return Object  返回类型 
//     * @throws
//     */
//    public Object selectCommentById(Object data){
//    	log.info("start[MemberCommentService.selectCommentById]");
//    	MemberCommentDetailDTO comment = memberCommentMapper.selectCommentById((Integer)data);
//		if(comment != null){
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			if(comment.getOrderCreateTime()!=null){
//				comment.setOrderCreateDate(format.format(comment.getOrderCreateTime()));
//			}
//			if(comment.getTime()!=null){
//				comment.setCommentDate(format.format(comment.getTime()));
//			}
//			//查询点赞数量
//			CommentPraiseExample example = new CommentPraiseExample();
//			example.createCriteria().andCommentIdEqualTo(comment.getCommentId());
//			comment.setPraise(commentPraiseMapper.countByExample(example));
//			
//			//查询回复数量
//			comment.setReplyCount(memberCommentMapper.selectReplyCountByCommentId(comment.getCommentId()));
//		}
//    	log.info("end[MemberCommentService.selectCommentById]");
//    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), comment);
//    }
    
    /**
     * @description <b>查询用户的咨询信息以及回复</b>
     * @author 王鹏
     * @version 1.0.0
     * @data 2015-5-16
     * @param @param data
     * @param @return
     * @return Object
   */
   public Object findMemberConsult(Object data){
	   	HeadObject headObject=new HeadObject();
	   	ResultPage<Object> comments = null;
	   	try{
			MemberCommentQryDTO qryDTO=(MemberCommentQryDTO) data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			qryDTO.setObjectType(ObjeType.ASK.getKey());
			comments = new ResultPage<Object>(this.memberCommentMapper.selectMemberConsultCount(qryDTO));
			if(comments.getRows().size()>0){
				qryDTO.setCommentIds(comments.getRows());
				comments.setRows(memberCommentMapper.selectMemberConsult(qryDTO));
			}
			headObject.setRetCode(ErrorCode.SUCCESS);
	   	}catch (Exception e) {
	   		headObject.setRetCode(ErrorCode.FAILURE);
	   		log.error(e);
	   		e.printStackTrace();
		}
	   	return new ResultObject(headObject, com.alibaba.fastjson.JSONObject.toJSON(comments));
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
	public Object selectCommentByBLOBs(Object data) {
		log.info("start[MemberCommentService.selectCommentByBLOBs]");
		List<MemberCommentDetailDTO> commentList = memberCommentMapper.selectCommentByBLOBs((MemberCommentWithBLOBs) data);
		if (commentList != null && commentList.size() >= 1) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < commentList.size(); i++) {
				if (commentList.get(i).getTime() != null) {
					commentList.get(i).setCommentDate(format.format(commentList.get(i).getTime()));
				}
			}
		}
		log.info("end[MemberCommentService.selectCommentByBLOBs]");
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), commentList);
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
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	Integer pageIndex = jsonObject.getInt("pageIndex");
		Integer pageSize = jsonObject.getInt("pageSize");
		Integer commentId = jsonObject.getInt("commentId");
		PageHelper.startPage(pageIndex, pageSize);
		ResultPage<MemberCommentDetailDTO> commentPage = new ResultPage(memberCommentMapper.selectReplyByCommentId(commentId));
		if(commentPage.getRows()!=null&&commentPage.getRows().size()>=1){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for(int i=0;i<commentPage.getRows().size();i++){
				if(commentPage.getRows().get(i).getTime()!=null){
					commentPage.getRows().get(i).setCommentDate(format.format(commentPage.getRows().get(i).getTime()));
				}
			}
		}
    	log.info("end[MemberCommentService.selectReplyByCommentId]");
//    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), JSONObject.fromObject(commentPage));
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
    	MemberCommentDetailDTO comment = memberCommentMapper.selectReplyById((Integer)data);
		if(comment != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(comment.getTime()!=null){
				comment.setCommentDate(format.format(comment.getTime()));
			}
		}
    	log.info("end[MemberCommentService.selectReplyById]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), comment);
    }
	
	/**
	 * 
	 * @Title:  selectOrderCommentCountByGoodsId  
	 * @Description:  TODO(根据商品id查询评论数量)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-27 下午4:20:36  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectOrderCommentCountByGoodsId(Object data){
    	log.info("start[MemberCommentService.selectOrderCommentCountByGoodsId]");
    	Integer count = memberCommentMapper.selectOrderCommentCountByGoodsId((Integer)data);
		log.info("end[MemberCommentService.selectOrderCommentCountByGoodsId]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), count);
    }
	
	/**
	 * @Title:  saveConsultation  
	 * @Description:  TODO(保存商品咨询)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-6-8 下午6:09:58  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object saveConsultation(Object data){
    	log.info("start[MemberCommentService.saveConsultation]");
    	HeadObject head = new HeadObject();
    	try{
			MemberCommentWithBLOBs memberComment=(MemberCommentWithBLOBs) data;
			memberComment.setTime(new Date(System.currentTimeMillis()));
    		this.memberCommentMapper.insertSelective(memberComment);
    		head.setRetCode(ErrorCode.SUCCESS);
    	}catch (Exception e) {
    		log.error("卖家中心>>交易管理>>评论管理>>保存商家解释信息失败", e);
    		e.printStackTrace();
    		head.setRetCode(ErrorCode.FAILURE);
		}
    	log.info("end[MemberCommentService.saveConsultation]");
    	return new ResultObject(head);
    }
}
