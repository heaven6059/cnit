package com.cnit.yoyo.rmi.basedata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.consult.ConsultCommentArgumentsMapper;
import com.cnit.yoyo.dao.consult.ConsultCommentBaseArgumentsMapper;
import com.cnit.yoyo.dao.consult.ConsultItemsMapper;
import com.cnit.yoyo.dao.consult.GoodsPointMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.other.consult.ConsultCommentArguments;
import com.cnit.yoyo.model.other.consult.ConsultCommentBaseArguments;
import com.cnit.yoyo.model.other.consult.ConsultItems;
import com.cnit.yoyo.model.other.consult.GoodsPoint;
import com.cnit.yoyo.model.other.consult.dto.ConsultCommentArgumentsDTO;
import com.cnit.yoyo.model.other.consult.dto.ConsultItemsDTO;
import com.cnit.yoyo.model.other.consult.dto.GoodsPointDTO;

/**  
* @Title: ConsultCommentArgumentsService.java
* @Package com.cnit.yoyo.rmi.basedata.service
* @Description: 平台管理咨询评论参数设置Service
* @Author 王鹏
* @date 2015-4-28 上午9:27:47
* @version V1.0  
*/ 
@SuppressWarnings("unchecked")
@Service("consultCommentArgumentsService")
public class ConsultCommentArgumentsService {
	
	private static final Log log = LogFactory.getLog(ConsultCommentArgumentsService.class);
	
	@Autowired
	private ConsultCommentArgumentsMapper argumentsMapper;
	
	@Autowired
	private ConsultCommentBaseArgumentsMapper baseArgumentsMapper;
	
	@Autowired
	private ConsultItemsMapper consultItemsMapper;
	
	@Autowired
	private GoodsPointMapper goodsPointMapper;
	
	private final String ARGS_TYPE_ASK="ask";
	
	private final String ARGS_TYPE_COMMENT="comment";
	
	/**
     * @description 查询咨询评论所有设置信息(基础设置、评论设置、咨询设置)
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object selectAllConsultCommentArgs(Object data) {
		HeadObject head = new HeadObject();
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			resultMap.put("BaseArguments", baseArgumentsMapper.selectBaseArguments());
			resultMap.put("ConsultArguments", argumentsMapper.selectByArgsType(ARGS_TYPE_ASK));
			resultMap.put("CommentComment", argumentsMapper.selectByArgsType(ARGS_TYPE_COMMENT));
			resultMap.put("GoodsPoints",goodsPointMapper.selectAllGoodsPoint());
			resultMap.put("ConsultItems",consultItemsMapper.selectAllCnsultItems());
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head, resultMap);
	}
	
	/**
     * @description 保存咨询评论基础设置
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object saveConsultCommentBaseArguments(Object data) {
		log.info("start[ConsultCommentArgumentsService.saveConsultCommentBaseArguments]");
		HeadObject head = new HeadObject();
		try {
			ConsultCommentBaseArguments record=(ConsultCommentBaseArguments)data;
			if(null!=record.getId()){
				baseArgumentsMapper.updateByPrimaryKeySelective(record);
			}else{
				baseArgumentsMapper.insertSelective(record);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.saveConsultCommentBaseArguments]");
		return head;
	}
	
	/**
     * @description 保存咨询设置
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object saveConsultArguments(Object data) {
		log.info("start[ConsultCommentArgumentsService.saveConsultArguments]");
		HeadObject head = new HeadObject();
		try {
			Map<String, Object> param=(HashMap<String, Object>)data;
			//批量修改或插入咨询项开始
			ConsultItemsDTO dto=(ConsultItemsDTO)param.get("items");
			List<ConsultItems> addItems=new ArrayList<ConsultItems>();
			List<ConsultItems> updateItems=new ArrayList<ConsultItems>();
			for (int i = 0; null!=dto&&null!=dto.getItemId()&&i < dto.getItemId().length; i++) {
				ConsultItems item=new ConsultItems();
				item.setConsultTitle(dto.getTitle()[i]);
				item.setEnabled(1);
				if(null!=dto.getItemId()[i]&&dto.getItemId()[i]>0){
					item.setId(dto.getItemId()[i]);
					updateItems.add(item);
				}else{
					addItems.add(item);
				}
			}
			if(addItems.size()>0){
				consultItemsMapper.batchSaveConsultItems(addItems);
			}
			if(updateItems.size()>0){
				consultItemsMapper.batchUpdateConsultItems(updateItems);
			}
			//批量修改或插入咨询项结束
			ConsultCommentArguments record=(ConsultCommentArguments)param.get("arguments");
			record.setArgsType(ARGS_TYPE_ASK);
			if (record.getId()==null) {
				argumentsMapper.insertSelective(record);
			}else{
				argumentsMapper.updateByPrimaryKeySelective(record);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.saveConsultArguments]");
		return head;
	}
	
	/**
     * @description 保存评论设置
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object saveCommentArguments(Object data) {
		log.info("start[ConsultCommentArgumentsService.saveCommentArguments]");
		HeadObject head = new HeadObject();
		try {
			Map<String, Object> param=(HashMap<String, Object>)data;
			//批量修改评分项开始
			String dto=(String)param.get("point");
			List<GoodsPoint> add=new ArrayList<GoodsPoint>();
			List<GoodsPoint> modify=new ArrayList<GoodsPoint>();
			buidGoodsPoint(dto, add, modify);
			if(add.size()>0){
				goodsPointMapper.batchSaveGoodsPoint(add);
			}
			if(modify.size()>0){
				goodsPointMapper.batchUpdateGoodsPoint(modify);
			}
			//批量修改评分项结束
			ConsultCommentArguments record=(ConsultCommentArguments)param.get("arguments");
			record.setArgsType(ARGS_TYPE_COMMENT);
			if (record.getId()==null) {
				argumentsMapper.insertSelective(record);
			}else{
				argumentsMapper.updateByPrimaryKeySelective(record);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.saveCommentArguments]");
		return head;
	}
	
	private void buidGoodsPoint(String dto,List<GoodsPoint> add,List<GoodsPoint> modify){
		JSONArray arry=JSONArray.fromObject(dto);
		List<GoodsPoint> list = (List<GoodsPoint>) JSONArray.toCollection(arry, GoodsPoint.class);
		for (GoodsPoint goodsPoint : list) {
			if(null==goodsPoint.getId()){
				add.add(goodsPoint);
			}else{
				goodsPoint.setId(goodsPoint.getId());
				modify.add(goodsPoint);
			}
		}
	}
	
	
	
	/**
     * @description 保存咨询项目设置
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object saveConsultItems(Object data) {
		log.info("start[ConsultCommentArgumentsService.saveConsultItems]");
		HeadObject head = new HeadObject();
		try {
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.saveConsultItems]");
		return head;
	}
	
	/**
     * @description 保存商品评分项设置
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object saveGoodsPoint(Object data) {
		log.info("start[ConsultCommentArgumentsService.saveGoodsPoint]");
		HeadObject head = new HeadObject();
		try {
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.saveGoodsPoint]");
		return head;
	}
	
	/**
     * @description 删除商品评分项
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object deleteGoodsPoint(Object data) {
		log.info("start[ConsultCommentArgumentsService.deleteGoodsPoint]");
		HeadObject head = new HeadObject();
		try {
			goodsPointMapper.deleteByPrimaryKey((Integer)data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.deleteGoodsPoint]");
		return head;
	}
	
	/**
     * @description 删除咨询项
     * @detail <方法详细描述>
     * @author <a href="">王鹏</a>
     * @version 1.0.0
     * @data 2015-4-15
     * @param 
     * @return
     */
	public Object deleteConsultItems(Object data) {
		log.info("start[ConsultCommentArgumentsService.deleteConsultItems]");
		HeadObject head = new HeadObject();
		try {
			consultItemsMapper.deleteByPrimaryKey((Integer)data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.deleteConsultItems]");
		return head;
	}
}
