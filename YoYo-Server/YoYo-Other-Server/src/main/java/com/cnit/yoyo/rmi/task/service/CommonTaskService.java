package com.cnit.yoyo.rmi.task.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.QueryParamObject;
import com.cnit.yoyo.domain.RequestObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.rmi.interfaces.RemoteService;
import com.cnit.yoyo.util.CommonHeadUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @Description: 公共任务跳转
 * @author wanghb
 * @date 2015年8月28日
 */
@Service
public class CommonTaskService {
	public final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RemoteService memberService;
	@Autowired
	private RemoteService searchService;
	@Autowired
	private RemoteService orderService;
	/**
	 * 全量重构索引库
	 * @author wanghb
	 * @return
	 */
	public Object seachIndexTask(Object data) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
			QueryParamObject paramObject = new QueryParamObject();
			paramObject.setPage(1000);
			HeadObject headObject = CommonHeadUtil.geneHeadObject("searchService.builderSearchIndex");
			searchService.doServiceByServer(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error("全量构建索引异常！" + e);
		}
		return new ResultObject(head);
	}
	/**
	 * 处理违规商品
	 * @author wanghb
	 * @return
	 */
	public Object dealStoreViolation(Object data) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
			QueryParamObject paramObject = new QueryParamObject();
			paramObject.setPage(1000);
			HeadObject headObject = CommonHeadUtil.geneHeadObject("violationTask.dealStoreViolation");
			memberService.doServiceByServer(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error("全量构建索引异常！" + e);
		}
		return new ResultObject(head);
	}
	/**
	 * 查找库存预警的商品，通知相应的卖家
	 * @author wanghb
	 * @return
	 */
	public Object dealAlertStore(Object data) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
			QueryParamObject paramObject = new QueryParamObject();
			paramObject.setPage(1000);
			HeadObject headObject = CommonHeadUtil.geneHeadObject("findAlertStoreOfGoodsTask.dealAlertStore");
			memberService.doServiceByServer(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error("查找库存预警的商品，通知相应的卖家异常！" + e);
		}
		return new ResultObject(head);
	}
	/**
	 * 自动取消订单任务
	 * @author wanghb
	 * @return
	 */
	public Object autoCancelOrderTask(Object data) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
			QueryParamObject paramObject = new QueryParamObject();
			paramObject.setPage(1000);
			HeadObject headObject = CommonHeadUtil.geneHeadObject("orderTaskService.autoCancelOrderTask");
			orderService.doServiceByServer(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error("自动取消订单任务异常！" + e);
		}
		return new ResultObject(head);
	}
	/**
	 * 自动处理退款留流程
	 * @author wanghb
	 * @return
	 */
	public Object autoProcessAfterSales(Object data) {
		HeadObject head = new HeadObject(ErrorCode.SUCCESS);
		try {
			QueryParamObject paramObject = new QueryParamObject();
			paramObject.setPage(1000);
			HeadObject headObject = CommonHeadUtil.geneHeadObject("orderTaskService.autoProcessAfterSales");
			orderService.doServiceByServer(new RequestObject(headObject, JSONObject.fromObject(paramObject)));
		} catch (Exception e) {
			head.setRetCode(ErrorCode.FAILURE);
			log.error("自动处理退款留流程异常！" + e);
		}
		return new ResultObject(head);
	}
}
