package com.cnit.yoyo.rmi.member.service.viewhistory;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.dao.member.ProductViewHistoryMapper;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.model.member.dto.ProductViewHistoryDTO;
import com.cnit.yoyo.model.member.dto.ProductViewHistoryQryDTO;
import com.cnit.yoyo.util.domain.ResultPage;
import com.github.pagehelper.PageHelper;

@Service("productViewHistoryService")
public class ProductViewHistoryService {

	private static final Log log = LogFactory.getLog(ProductViewHistoryService.class);
	
	@Autowired
	private ProductViewHistoryMapper productViewHistoryMapper;
	
	/**
	  * @description <b>查询会员商品收藏信息</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-18
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object qryProdcutViewHistoryList(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ProductViewHistoryDTO> result=null;
		try {
			ProductViewHistoryQryDTO qryDTO=(ProductViewHistoryQryDTO)data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			result = new ResultPage<ProductViewHistoryDTO>(this.productViewHistoryMapper.selectProductViewHistory(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,result);
	}
	
	
	/**
	 * @description <b>查询卖家店铺被浏览的商品</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-8-13
	 * @param data
	 * @return
	 * Object
	*/
	public Object qryShopViewHistory(Object data) {
		HeadObject head = new HeadObject();
		ResultPage<ProductViewHistoryDTO> result=null;
		try {
			ProductViewHistoryQryDTO qryDTO=(ProductViewHistoryQryDTO)data;
			PageHelper.startPage(qryDTO.getPage(), qryDTO.getRows());
			result = new ResultPage<ProductViewHistoryDTO>(this.productViewHistoryMapper.selectShopProductViewHistory(qryDTO));
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		return new ResultObject(head,result);
	}
	
	
	/**
	  * @description <b>删除浏览的商品</b>
	  * @author 王鹏
	  * @version 1.0.0
	  * @data 2015-5-4
	  * @param @param data
	  * @param @return
	  * @return Object
	*/
	public Object deleteProductViewHistory(Object data){
		log.info("start[ProductViewHistoryService.deleteProductViewHistory]");
		HeadObject head = new HeadObject();
		try {
			this.productViewHistoryMapper.deleteProductViewHistory((Map<String, Object>) data);
			head.setRetCode(ErrorCode.SUCCESS);
		} catch (Exception e) {
			log.error("删除浏览商品失败",e);
			e.printStackTrace();
			head.setRetCode(ErrorCode.FAILURE);
		}
		log.info("end[ProductViewHistoryService.deleteProductViewHistory]");
		return head;
	}
}
