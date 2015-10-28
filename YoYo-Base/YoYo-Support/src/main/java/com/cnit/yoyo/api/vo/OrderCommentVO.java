package com.cnit.yoyo.api.vo;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.cnit.yoyo.api.base.vo.Parameter;

public class OrderCommentVO  extends Parameter implements Serializable{

	/***/
	private static final long serialVersionUID = 2403459302122420898L;

	private Long commentId;
	
	@NotNull(message="{orderId不能为空}")
	private Long orderId;

	private Long orderItemId;

	private Long productId;

	private Long goodsId;

	private String title;

	@Size(min=10,max=300, message="comment长度应为{10}-{300}")
	private String comment;

	private Integer commentStar;

	@NotNull(message="{forCommentId不能为空}")
	private Long forCommentId;

	private Long memberId;

	private String commentsType;

	private String display;

	private Date createTime;

	@NotNull(message="{toId不能为空}")
	private Long toId;

	@NotBlank(message="{toName不能为空}")
	private String toName;

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
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
	
}
