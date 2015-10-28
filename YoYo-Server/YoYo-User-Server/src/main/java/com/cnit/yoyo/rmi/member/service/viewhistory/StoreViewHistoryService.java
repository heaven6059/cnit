package com.cnit.yoyo.rmi.member.service.viewhistory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.member.StoreViewHistoryMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.StoreViewHistory;
import com.cnit.yoyo.model.member.dto.StoreViewHistoryDTO;
import com.cnit.yoyo.model.member.dto.StoreViewHistoryQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

/**  
* @Title: StoreViewHistoryService.java
* @Package com.cnit.yoyo.rmi.member.service.viewhistory
* @Description: 店铺浏览历史
* @Author 王鹏
* @date 2015-5-18 下午3:32:42
* @version V1.0  
*/ 
@Service("storeViewHistoryService")
public class StoreViewHistoryService {
	private static final Log log = LogFactory.getLog(ProductViewHistoryService.class);
	
	@Autowired
	private StoreViewHistoryMapper storeViewHistoryMapper;
	
	/**
	  * @description <b>查询会员店铺收藏信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object qryStoreViewHistoryList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<StoreViewHistoryDTO> result=null;
		try {
			StoreViewHistoryQryDTO qryDTO=(StoreViewHistoryQryDTO)data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			result = new ResultPage<StoreViewHistoryDTO>(this.storeViewHistoryMapper.selectStoreViewHistory(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,result);
	}
	
	/**
	  * @description <b>删除浏览的店铺</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-4
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object deleteStoretViewHistory(Object data){
		log.info("start[ProductViewHistoryService.deleteStoretViewHistory]");
		HeadObject head = new HeadObject();
		try {
			this.storeViewHistoryMapper.deleteStoreViewHistory((Integer[]) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error("删除浏览商品失败",e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ProductViewHistoryService.deleteStoretViewHistory]");
		return head;
	}
	
	/**
	  * @description <b>查询会员店铺收藏信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object saveStoretViewHistory(Object data) {
		HeadObject head = new HeadObject();
		try {
			StoreViewHistoryQryDTO qryDTO=(StoreViewHistoryQryDTO)data;
			String [] viewSotres = qryDTO.getViewData().split(",");
			for (String viewStore : viewSotres) {
				try{
					StoreViewHistoryDTO historyDTO=new StoreViewHistoryDTO();
					historyDTO.setCompanyId(Long.parseLong(viewStore.split("\\|")[0]));
					historyDTO.setViewDate(new Date(Long.parseLong(viewStore.split("\\|")[1])));
					historyDTO.setMemberId(qryDTO.getMemberId());
					this.storeViewHistoryMapper.insertStoreViewHistory(historyDTO);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head);
	}
}
