package com.cnit.yoyo.model.member;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ProductViewHistory.java
 * @Package com.cnit.yoyo.model.member
 * @Description: 商品浏览历史表
 * @Author 王鹏
 * @date 2015-5-18 下午3:29:51
 * @version V1.0
 */
public class ProductViewHistory implements Serializable {

	private Integer id;
	private Long memberId;
	private Long productId;
	private Date viewDate;
	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}