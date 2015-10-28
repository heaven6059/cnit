package com.cnit.yoyo.complain.service;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.*;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.complain.dao.ComplainMapper;
import com.cnit.yoyo.complain.model.Complain;
import com.cnit.yoyo.complain.model.ComplainComments;
import com.cnit.yoyo.complain.model.dto.ComplainDetailDTO;
import com.cnit.yoyo.complain.model.dto.ComplainQryDTO;
import com.cnit.yoyo.constant.ComplainConstant.ComplainStatus;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.report.model.Report;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**   
 * @Package com.cnit.yoyo.complain.service 
 * @Description: 
 * @author  余平 yuping@chinacnit.com 
 * @date 2015-4-28 下午8:12:03 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
@Service("complainService")
public class ComplainService {
	
    public static final Logger logger = LoggerFactory.getLogger(ComplainService.class);

	@Autowired
	private ComplainMapper  complainMapper;
	
	public Object getComplainList(Object object){
		HeadObject  head =  new  HeadObject();
		ComplainQryDTO complainQryDTO = (ComplainQryDTO)object;
		PageHelper.startPage(complainQryDTO.getPage(), complainQryDTO.getRows());
		ResultPage<Complain> dataList = new ResultPage<Complain>(complainMapper.getComplainList(complainQryDTO));
		if(dataList != null && dataList.getRows().size() > 0){
			head.setRetCode(ErrorCode.SUCCESS);
		}else{
			head.setRetCode(ErrorCode.FAILURE);
		}
		ResultObject  result = new ResultObject(head, JSONObject.toJSON(dataList));
		return result;
	}
	
	/**
	 * 
	 * @Description: 投诉卖家
	 * @param object
	 * @return
	 */
	public Object saveComplain(Object object){
		HeadObject  head =  new  HeadObject();
		try{
			Complain  complain = (Complain)object;
			int complainResult = complainMapper.saveComplain(complain);
			String comment = complain.getComment();
			String imagePath = complain.getImagePath();
			if( StringUtils.isNotBlank(comment) || StringUtils.isNotBlank(imagePath)){
				ComplainComments complainComments = new ComplainComments();
				complainComments.setComplainId(complain.getComplainId());
				complainComments.setComment(comment);
				complainComments.setImagePath(imagePath);
				complainComments.setSource(complain.getSource());
				if(null != complain.getFromMemberId() && complain.getFromMemberId() >0){
					complainComments.setAuthorId(complain.getFromMemberId());
				}
				if(StringUtils.isNotBlank(complain.getFromUname())){
					complainComments.setAuthor(complain.getFromUname());
				}
				complainMapper.saveComplainComment(complainComments);
			}
			if(complainResult > 0){
				head.setRetCode(ErrorCode.SUCCESS);
			}else{
				head.setRetCode(ErrorCode.FAILURE);
			}
		}catch (Exception e) {
			e.printStackTrace();
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
		ComplainComments  complainComment = (ComplainComments)object;
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
	
	/**
	  * @description <b>撤销投诉</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-28
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object cancelComplain(Object object) {
		HeadObject  head =  new  HeadObject();
		try{
			Map<String, Object> paraMap=new HashMap<String, Object>();
			paraMap.put("status", ComplainStatus.CANCEL.getKey());
			paraMap.put("id", object);
			complainMapper.updateComplainStatus(paraMap);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head);
	}

	/**
	 * 查询投诉单
	 * @param data
	 * @return
	 */
	public Object findComplain(Object data){
        logger.info("###########ComplainService.findComplain-->start");
		HeadObject  head =  new  HeadObject();
		Long complainId = (Long)data;
		Complain complain = new Complain();
		try{
			complain = complainMapper.selectByPrimaryKey(complainId);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			head.setRetCode(ErrorCode.FAILURE);
		}
        logger.info("###########ComplainService.findComplain-->start");
		return new ResultObject(head, complain);
	}
	
	
	
	/**
	 * 
	 * @Description: 查询订单投诉详情信息
	 * @param data
	 * @return
	 */
	public Object findComplainDetailById(Object data){
		HeadObject head = new HeadObject();
		ComplainDetailDTO detail=null;
		try{
			detail=complainMapper.getComplainDetailById((Long) data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			e.printStackTrace();
		}
		return new ResultObject(head, detail);
	}
	
	/**
	  * @description <b>修改投诉</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015年7月8日
	  * @param @param object
	  * @param @return
	  * @return Object
	*/
	public Object updateComplain(Object object){
		HeadObject headObject=new HeadObject();
		try{
			Complain record=(Complain)object;
			if(null==record.getComplainId()){
				headObject.setRetCode(ErrorCode.FAILURE);
			}else{
				complainMapper.updateByPrimaryKeySelective(record);
				headObject.setRetCode(ErrorCode.SUCCESS);
			}
		}catch(Exception e){
			e.printStackTrace();
			headObject.setRetCode(ErrorCode.FAILURE);
		}
		return headObject;
	}
}

