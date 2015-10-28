package com.cnit.yoyo.complain.model;
/**   
 * @Package com.cnit.yoyo.complain.model 
 * @Description: 投诉
 * @author  余平 yuping@cnit.com 
 * @date 2015-4-29 下午2:58:31 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cnit.yoyo.model.order.OrderItems;

public class Complain implements Serializable {
	private static final long serialVersionUID = -336299446706860531L;
	private Long complainId;//投诉编号
	private Long orderId;//订单号
	private Integer fromMemberId;//投诉方
	private String fromUname;//投诉人
	private String mobile;//手机
	private Integer toMemberId;//被投诉方
	private String toUname;//店主名
	private String source;//投诉方1 default 'buyer' buyer => (''买家''),''seller'' => (''卖家'')'
	private String storeId;//店铺Id
	private String storeName;//店铺名
	private String reason;//投诉原因 default 'after' after => (''售后问题''),''action'' => (''行为违规'')',quality"质量问题
	private String status;//投诉状态 default 'intervene'  '''intervene'' => (''平台介入''),''success'' => (''投诉成立''),''error'' => (''投诉不成立''),''cancel'' => (''投诉撤销''),'
	private Date createtime;//申请时间
	private Date lastModified;//更新时间
	private String disabled;//失效  default 'false'  '（true：是；false：否）
	private String memo;//备注
	private String imagePath;// 投诉图片的路径
	private ComplainComments  complainComment;
	private List<ComplainComments> complainComments;

	private String comment;
	/**
	 * 
	 * overridden
	 * TODO
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("complainId=").append(complainId);
		sb.append(", orderId=").append(orderId);
		sb.append(", fromMemberId=").append(fromMemberId);
		sb.append(", fromUname=").append(fromUname);
		sb.append(", imagePath=").append(imagePath);
		sb.append(", mobile=").append(mobile);
		sb.append(", storeId=").append(storeId);
		sb.append(", toMemberId=").append(toMemberId);
		sb.append(", toUname=").append(toUname);
		sb.append(", source=").append(source);
		sb.append(", storeName=").append(storeName);
		sb.append(", reason=").append(reason);
		sb.append(", status=").append(status);
		sb.append(", createtime=").append(createtime);
		sb.append(", lastModified=").append(lastModified);
		sb.append(", disabled=").append(disabled);
		sb.append(", memo=").append(memo);
		return sb.toString();
	}
	
	
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}





	public String getImagePath() {
		return imagePath;
	}





	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}





	public String getStoreId() {
		return storeId;
	}



	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}



	public Long getComplainId() {
		return complainId;
	}

	public void setComplainId(Long complainId) {
		this.complainId = complainId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getFromMemberId() {
		return fromMemberId;
	}

	public void setFromMemberId(Integer fromMemberId) {
		this.fromMemberId = fromMemberId;
	}

	public String getFromUname() {
		return fromUname;
	}

	public void setFromUname(String fromUname) {
		this.fromUname = fromUname;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getToMemberId() {
		return toMemberId;
	}

	public void setToMemberId(Integer toMemberId) {
		this.toMemberId = toMemberId;
	}

	public String getToUname() {
		return toUname;
	}

	public void setToUname(String toUname) {
		this.toUname = toUname;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<ComplainComments> getComplainComments() {
		return complainComments;
	}

	public void setComplainComments(List<ComplainComments> complainComments) {
		this.complainComments = complainComments;
	}


	public ComplainComments getComplainComment() {
		return complainComment;
	}


	public void setComplainComment(ComplainComments complainComment) {
		this.complainComment = complainComment;
	}
	
}