package com.cnit.yoyo.rmi.app.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.app.TapplogMapper;
import com.cnit.yoyo.dao.app.TclientlogMapper;
import com.cnit.yoyo.dao.app.TtokenMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.app.Tapplog;
import com.cnit.yoyo.model.app.Tclientlog;
import com.cnit.yoyo.model.app.Ttoken;
import com.cnit.yoyo.model.other.consult.GoodsPoint;

/**
 * app手机端日志服务层
 * @Author:yangyi  
 * @Date:2015年7月16日
 * @Version:1.0.0
 */
@SuppressWarnings("unchecked")
@Service("appBaseService")
public class AppBaseService {
	
	private static final Logger log = LoggerFactory.getLogger(AppBaseService.class);
	
	@Autowired
	private TapplogMapper tapplogMapper;
	
	@Autowired
	private TclientlogMapper tclientlogMapper;
	
	@Autowired
	private TtokenMapper ttokenMapper;
	
	private final String ARGS_TYPE_ASK="ask";
	
	private final String ARGS_TYPE_COMMENT="comment";
	
	/**
	 * @Description:删除token相关
	 * @param data
	 * @return
	 */
	public Object deleteTokenList(Object data){
		HeadObject head = new HeadObject();
		List<Ttoken> list=(List<Ttoken>) data;
		try {
			ttokenMapper.deleteTokenList(list);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error(e.toString());
			e.printStackTrace();
		}
		return new ResultObject(head);
	}
	
	/**
	 * @Description:查询所有token记录
	 * @return
	 */
	public Object queryTokenList(Object data){
		HeadObject head = new HeadObject();
		List<Ttoken> list=null;
		try {
			list=ttokenMapper.queryTokenList((String)data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error(e.toString());
			e.printStackTrace();
		}
		return new ResultObject(head, list);
	}
	
	/**
	 * @Description:查询token
	 * @param data
	 * @return
	 */
	public Object findToken(Object data){
		HeadObject head = new HeadObject();
		Ttoken token=null;
		try {
			token=ttokenMapper.selectByPrimaryKey((String)data);
			head.setRetCode(ErrorCode.SUCCESS);
		}catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error(e.toString());
			e.printStackTrace();
		}
		return new ResultObject(head, token);
	}
	
	/**
	 * @Description:保存token
	 * @param data
	 * @return
	 */
	public Object saveToken(Object data){
		HeadObject head = new HeadObject();
		Ttoken token=(Ttoken)data;
		try {
			if(token != null){
				ttokenMapper.insertSelective(token);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
	
	/**
	 * @Description:更新token
	 * @param data
	 * @return
	 */
	public Object updateToken(Object data){
		HeadObject head = new HeadObject();
		Ttoken token=(Ttoken)data;
		try {
			if(token != null){
				ttokenMapper.updateByPrimaryKeySelective(token);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
	
	/**
	 * @Description:删除token
	 * @param data
	 * @return
	 */
	public Object deleteToken(Object data){
		HeadObject head = new HeadObject();
		try {
			if(data != null){
				ttokenMapper.deleteByPrimaryKey((String)data);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
	
	
	/**
	 * 保存tclientlog
	 */
	public Object saveTclientlog(Object data){
		HeadObject head = new HeadObject();
		Tclientlog tclientlog=(Tclientlog)data;
		try {
			if(tclientlog != null){
				tclientlogMapper.insertSelective(tclientlog);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return head;
	}
	
	/**
	 * 保存tapplog
	 */
	public Object saveTappLog(Object data){
		HeadObject head = new HeadObject();
		Tapplog tapplog=(Tapplog)data;
		try {
			if(tapplog != null){
				tapplogMapper.insertSelective(tapplog);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e.toString());
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
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
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ConsultCommentArgumentsService.saveGoodsPoint]");
		return head;
	}
	
}
