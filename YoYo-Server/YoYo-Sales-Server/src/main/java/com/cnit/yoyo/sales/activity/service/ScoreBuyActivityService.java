package com.cnit.yoyo.sales.activity.service;


import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.goods.GoodsCatWithBLOBs;
import com.cnit.yoyo.model.sales.activity.ScoreBuyActivity;
import com.cnit.yoyo.model.sales.activity.dto.ScoreBuyActivityQryDTO;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.sales.activity.dao.ScoreBuyActivityMapper;
import com.cnit.yoyo.util.CommonHeadUtil;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("scoreBuyActivityService")
public class ScoreBuyActivityService {
	private static final Log log = LogFactory.getLog(ScoreBuyActivityService.class);

	@Autowired
	private RemoteService itemService;
	
	@Autowired
	private ScoreBuyActivityMapper scoreBuyActivityMapper;
	
	
	/**
	  * @description <b>查询积分换购列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-5
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findScoreBuyActivity(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ScoreBuyActivity> resultPage=null;
		try {
			ScoreBuyActivityQryDTO record=(ScoreBuyActivityQryDTO)data;
			PageHelper.startPage(record.getPage(), record.getRows());
			resultPage=new ResultPage<ScoreBuyActivity>(scoreBuyActivityMapper.selectScoreBuyActivity(record));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,JSON.toJSON(resultPage));
	}
	
	/**
	  * @description <b>查询积分换购列表</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-5
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object findScoreBuyActivityById(Object data) {
		HeadObject head = new HeadObject();
		ScoreBuyActivity scoreBuyActivity=null;
		Map<String, Object> resultMap=new HashMap<String, Object>();
		try {
			scoreBuyActivity = scoreBuyActivityMapper.selectByPrimaryKey((Integer)data);
			String[] ids =scoreBuyActivity.getBusinessType().split(",") ;
			Long [] list=new Long[ids.length];
			for(int i=0;i<ids.length;i++){
				list[i]=Long.parseLong(ids[i]);
			}
			head = CommonHeadUtil.geneHeadObject("010401-03");
			ResultObject object=itemService.doService(new RequestObject(head, list));
			List<GoodsCatWithBLOBs> cats=(List<GoodsCatWithBLOBs>) object.getContent();
			resultMap.put("scoreBuyActivity", scoreBuyActivity);
			resultMap.put("categorys", cats);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,resultMap);
	}
	
	/**
	  * @description <b>修改或保存积分换购活动</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-5
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object saveOrUpdateScoreBuyActivity(Object data) {
		log.info("start[ScoreBuyActivityService.saveOrUpdateScoreBuyActivity]");
		HeadObject head = new HeadObject();
		try {
			ScoreBuyActivity record = (ScoreBuyActivity) data;
			record.setLastModified(new Date());
			if(null!=record.getActId()&&record.getActId()>0){
				scoreBuyActivityMapper.updateByPrimaryKeySelective(record);
			}else{
				scoreBuyActivityMapper.insertSelective(record);
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ScoreBuyActivityService.saveOrUpdateScoreBuyActivity]");
		return head;
	}
	
	
	/**
	  * @description 删除活动
	*/
	public Object deleteBuyActivity(Object data){
	   	 HeadObject head = new HeadObject();
	        try{
	          String [] ids=data.toString().split(",");
	      	  for(int i=0;i<ids.length;i++){
	      		ScoreBuyActivity dto=scoreBuyActivityMapper.selectByPrimaryKey(Integer.parseInt(ids[i]));
	      		if(dto.getActOpen().equals(1)){
	      			head.setRetCode(ErrorCode.IS_EXIST_CASCADE);
	      			 return new ResultObject(head);
	      		}else{
	      			scoreBuyActivityMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
	      		}
	      	  }
	      	head.setRetCode(ErrorCode.SUCCESS);
	        }catch(Exception e){    
	     	   e.printStackTrace();
	           head.setRetCode(ErrorCode.FAILURE);
	        }       
	        return new ResultObject(head);
  }
}
