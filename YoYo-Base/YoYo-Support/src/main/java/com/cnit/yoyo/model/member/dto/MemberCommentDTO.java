package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;
import java.util.List;

import com.cnit.yoyo.model.goods.Product;
import com.cnit.yoyo.model.member.MemberCommentWithBLOBs;
import com.cnit.yoyo.model.order.Order;

public class MemberCommentDTO extends MemberCommentWithBLOBs implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1882450548385240254L;
	private List<MemberCommentWithBLOBs> replyComment;// 回复列表集合
	private String storeName;// 商铺名称
	private Product product;
	private Order order;
	private Integer replyCount;// 回复数量

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<MemberCommentWithBLOBs> getReplyComment() {
		return replyComment;
	}

	public void setReplyComment(List<MemberCommentWithBLOBs> replyComment) {
		this.replyComment = replyComment;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Integer getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
