/**
 * 文 件 名   :  GoodsQryDTO.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnitcloud.com">李明</a>
 * 创建时间  :  2015年4月3日 下午4:05:25
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.model.order.dto;

import java.io.Serializable;


/**
 * 更新订单备注信息
* @author ssd
* @date 2015-4-13 下午1:52:06
 */
public class OrderRemarkDTO implements Serializable {
    private static final long serialVersionUID = 3972668715243253431L;
    private Long orderId;//订单ID
	private String markType;//订单类型
    private String markText;//订单备注
	public String getMarkType() {
		return markType;
	}
	public void setMarkType(String markType) {
		this.markType = markType;
	}
	public String getMarkText() {
		return markText;
	}
	public void setMarkText(String markText) {
		this.markText = markText;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}


}
