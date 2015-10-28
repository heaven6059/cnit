package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesReason;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesRequire;
import com.cnit.yoyo.constant.AfterSalesConstant.AfterSalesStatus;
import com.cnit.yoyo.model.order.Order;
import com.cnit.yoyo.model.trade.AftersalesReturnLogWithBLOBs;
import com.cnit.yoyo.reship.model.AftersalesReturnProduct;

public class ReturnProductDTO extends AftersalesReturnProduct implements
		Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8734922546838309247L;
	private Order order;// 订单
	private List<AftersalesReturnLogWithBLOBs> logs;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<AftersalesReturnLogWithBLOBs> getLogs() {
		return logs;
	}

	public void setLogs(List<AftersalesReturnLogWithBLOBs> logs) {
		this.logs = logs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @description 获取付款状态文文本
	 * @detail <方法详细描述>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-4-16
	 * @param
	 * @return
	 */
	public String getRefundText() {
		if (null == super.getStatus())
			return "";
		try {
			return AfterSalesStatus.getAfterSalesStausText(this.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * @description <b>获取退货原因文本</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-6-1
	 * @param @return
	 * @return String
	 */
	public String getAfterSalesReasonText() {
		if (null == super.getSafeguardType())
			return "";
		return AfterSalesReason.getAfterSalesReason(super.getSafeguardType());
	}

	/**
	 * @description <b>售后服务要求文本</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015年7月27日
	 * @return
	 * String
	*/
	public String getSafeguardRequireText(){
		if(null==super.getSafeguardRequire())return "";
		return AfterSalesRequire.getAfterSalesRequire(super.getSafeguardRequire());
	}
}
