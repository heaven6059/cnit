package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.util.Date;

public class OrderComment implements Serializable {
	private Long commentId;

	private Long orderId;

	private Long orderItemId;

	private Long productId;

	private Long goodsId;

	private String title;

	private String comment;

	private Integer commentStar;

	private Long forCommentId;

	private Long memberId;

	private String commentsType;

	private String display;

	private Date createTime;

	private Long toId;

	private String toName;

	private static final long serialVersionUID = 1L;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getCommentStar() {
		return commentStar;
	}

	public void setCommentStar(Integer commentStar) {
		this.commentStar = commentStar;
	}

	public Long getForCommentId() {
		return forCommentId;
	}

	public void setForCommentId(Long forCommentId) {
		this.forCommentId = forCommentId;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getCommentsType() {
		return commentsType;
	}

	public void setCommentsType(String commentsType) {
		this.commentsType = commentsType;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getToId() {
		return toId;
	}

	public void setToId(Long toId) {
		this.toId = toId;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(commentId);
		sb.append(", orderId=").append(orderId);
		sb.append(", orderItemId=").append(orderItemId);
		sb.append(", productId=").append(productId);
		sb.append(", title=").append(title);
		sb.append(", comment=").append(comment);
		sb.append(", commentStar=").append(commentStar);
		sb.append(", forCommentId=").append(forCommentId);
		sb.append(", memberId=").append(memberId);
		sb.append(", commentsType=").append(commentsType);
		sb.append(", display=").append(display);
		sb.append(", createTime=").append(createTime);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}