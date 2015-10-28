package com.cnit.yoyo.rmi.order.service;

import java.util.List;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.order.CommentPraiseMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.CommentPraise;
import com.cnit.yoyo.model.member.CommentPraiseExample;

/**
 * @ClassName: CommentPraiseService  
 * @Description: TODO(评论点赞对象的业务逻辑层)  
 * @detail <详细说明>
 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
 * @date 2015-5-19 上午9:29:21  
 * @version 1.0.0
 */
@Service("commentPraiseService")
public class CommentPraiseService {

	private static final Log log = LogFactory.getLog(CommentPraiseService.class);

	@Autowired
    private CommentPraiseMapper commentPraiseMapper;
	
	/**
	 * @Title:  selectByMemberAndComment  
	 * @Description:  TODO(根据用户id和评论id查询点赞对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-19 上午9:30:59  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectByMemberAndComment(Object data){
    	log.info("start[MemberCommentService.selectByMemberAndComment]");
    	JSONObject jsonObject = JSONObject.fromObject(data);
    	Integer commentId = jsonObject.getInt("commentId");
    	Integer memberId = jsonObject.getInt("memberId");
    	CommentPraiseExample example = new CommentPraiseExample();
    	example.createCriteria().andMemberIdEqualTo(memberId).andCommentIdEqualTo(commentId);
//    	example.createCriteria().andCommentIdEqualTo(commentId);
    	List<CommentPraise> commentPraiseList = commentPraiseMapper.selectByExample(example);
    	log.info("end[MemberCommentService.selectByMemberAndComment]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), commentPraiseList);
    }
	
	/**
	 * @Title:  saveCommentPraise  
	 * @Description:  TODO(保存点赞对象)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-19 上午9:49:40  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object saveCommentPraise(Object data){
    	log.info("start[MemberCommentService.saveCommentPraise]");
    	commentPraiseMapper.insertSelective((CommentPraise)data);
    	log.info("end[MemberCommentService.saveCommentPraise]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS));
    }
	
	/**
	 * @Title:  selectPraiseCountByCommentId  
	 * @Description:  TODO(根据评论id查询点赞次数)  
	 * @author <a href="cmlai@chinacnit.com">赖彩妙</a> 
	 * @date 2015-5-19 上午10:16:43  
	 * @version 1.0.0 
	 * @param @param data
	 * @param @return
	 * @return Object  返回类型 
	 * @throws
	 */
	public Object selectPraiseCountByCommentId(Object data){
    	log.info("start[MemberCommentService.selectPraiseCountByCommentId]");
    	CommentPraiseExample example = new CommentPraiseExample();
    	example.createCriteria().andCommentIdEqualTo((Integer)data);
    	int count = commentPraiseMapper.countByExample(example);
    	log.info("end[MemberCommentService.selectPraiseCountByCommentId]");
    	return new ResultObject(new HeadObject(ErrorCode.SUCCESS), count);
    }
}
