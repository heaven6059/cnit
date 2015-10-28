package com.cnit.yoyo.membermsg.service;

import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.complain.dao.ComplainMapper;
import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.complain.model.ComplainComments;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.membermsg.dao.MemberMsgMapper;
import com.cnit.yoyo.membermsg.model.MemberMsg;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("memberMsgService")
public class MemberMsgService {
	@Autowired
	private ComplainMapper  complainMapper;
	
	@Autowired
	private MemberMsgMapper  memberMsgMapper;
	
	/**
	  * @description <b>获取会员消息列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-9
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object getMemberMsgList(Object object){
		HeadObject  head =  new  HeadObject();
		ResultPage<MemberMsg> dataList = null;
		try{
			Map<String,Object> paraData = (Map<String,Object>)object;
			Integer pageNum = (Integer) paraData.get("pageNum");
			Integer pageSize = (Integer) paraData.get("pageSize");
			PageHelper.startPage(pageNum, pageSize);
			dataList = new ResultPage<MemberMsg>(memberMsgMapper.getMemberMsgList(paraData));
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, dataList);
	}
	public Object saveMemberMsg(Object object){
		HeadObject  head =  new  HeadObject();
		ResultPage<MemberMsg> dataList = null;
		try{
			MemberMsg memberMsg=(MemberMsg)object;
			memberMsgMapper.insert(memberMsg);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, dataList);
	}
	
	/**
	  * @description <b>获取会员消息详情</b>
	  * @author 
	  * @version 1.0.0
	  * @data 2015-6-9
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object getMemberMsgDetailById(Object data){
		HeadObject head = new HeadObject();
		MemberMsg memberMsg=null;
		try{
			Map<String, Object> map=(Map<String, Object>) data;
			memberMsg=memberMsgMapper.getMemberMsgDetailById(map);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, memberMsg);
	}
	
	/**
	  * @description <b>修改阅读状态为已读</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-6-9
	  * @param @param map
	  * @param @return
	  * @return Object
	*/
	public Object updateMemberMsgReadStatus(Object object){
		HeadObject head = new HeadObject();
		try{
			memberMsgMapper.updateMemberMsgReadStatus((Map<String, Object>) object);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
	
	/**
	 * 
	 * @Description: 投诉卖家
	 * @param object
	 * @return
	 */
	public Object saveComplain(Object object){
		HeadObject  head =  new  HeadObject();
		Complain  complain = (Complain)JSONObject.toBean(JSONObject.fromObject(object), Complain.class);
		int complainResult = complainMapper.saveComplain(complain);
		complain.getComplainComment().setComplainId(complain.getComplainId());
		complain.getComplainComment().setSource(complain.getSource());
		complain.getComplainComment().setAuthorId(complain.getFromMemberId());
		complain.getComplainComment().setAuthor(complain.getFromUname());
		complain.getComplainComment().setDisabled("false"); 
		String imagePath = complain.getImagePath();
		if(imagePath != null && imagePath.trim().length() >0 ){
			imagePath = imagePath.substring(0, imagePath.length()-1);
		}
		complain.getComplainComment().setImagePath(imagePath); 
		if(complainResult > 0){
			int complainCommentResult = complainMapper.saveComplainComment(complain.getComplainComment());
			if(complainCommentResult > 0){
				head.setRetCode(ErrorCode.SUCCESS);
			}else{
				head.setRetCode(ErrorCode.FAILURE);
			}
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		
		return new ResultObject(head);
	}
	
	/**
	 * 
	 * @Description: 投诉内容表
	 * @param object
	 * @return
	 */
	public Object saveComplainComment(Object object){
		HeadObject  head =  new  HeadObject();
		ComplainComments  complainComment = (ComplainComments)JSONObject.toBean(JSONObject.fromObject(object), ComplainComments.class);
		String imagePath = complainComment.getImagePath();
		if(imagePath != null && imagePath.trim().length() >0 ){
			imagePath = imagePath.substring(0, imagePath.length()-1);
		}
		complainComment.setImagePath(imagePath); 
		int result = complainMapper.saveComplainComment(complainComment);
		if(result > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}	
}