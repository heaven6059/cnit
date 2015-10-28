package com.cnit.yoyo.membermsg.model;

import java.io.Serializable;
import java.util.Date;

public class MemberMsg implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7600828229813723310L;
	private Integer msgId;
    private Integer forId;//回复哪个信件
    private Integer fromId;//发起会员ID
    private String fromUname;//发信者
    private Integer fromType;//发信类型
    private Integer toId;//收信者ID
    private String toUname;//收信者
    private String subject;//消息主题
    private Long orderId;//订单ID
    private Date createTime;//发送时间
    private Date toTime;//到达时间
    private String hasRead;//是否已读
    private String hasSent;//是否发送
    private String hasStar;//是否打上星标
    private String keepUnread;//保持未读
    private String content;//内容
  
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" msgId=").append(msgId);
        sb.append(", forId=").append(forId);
        sb.append(", fromId=").append(fromId);
        sb.append(", fromUname=").append(fromUname);
        sb.append(", fromType=").append(fromType);
        sb.append(", toId=").append(toId);
        sb.append(", toUname=").append(toUname);
        sb.append(", subject=").append(subject);
        sb.append(", orderId=").append(orderId);
        sb.append(", createTime=").append(createTime);
        sb.append(", toTime=").append(toTime);
        sb.append(", hasRead=").append(hasRead);
        sb.append(", hasSent=").append(hasSent);
        sb.append(", hasStar=").append(hasStar);
        sb.append(", keepUnread=").append(keepUnread);
        sb.append(", content=").append(content);
        return sb.toString();
    }

	public Integer getMsgId() {
		return msgId;
	}

	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}

	public Integer getForId() {
		return forId;
	}

	public void setForId(Integer forId) {
		this.forId = forId;
	}

	public Integer getFromId() {
		return fromId;
	}

	public void setFromId(Integer fromId) {
		this.fromId = fromId;
	}

	public String getFromUname() {
		return fromUname;
	}

	public void setFromUname(String fromUname) {
		this.fromUname = fromUname;
	}

	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	public Integer getToId() {
		return toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	public String getToUname() {
		return toUname;
	}

	public void setToUname(String toUname) {
		this.toUname = toUname;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getToTime() {
		return toTime;
	}

	public void setToTime(Date toTime) {
		this.toTime = toTime;
	}

	public String getHasRead() {
		return hasRead;
	}

	public void setHasRead(String hasRead) {
		this.hasRead = hasRead;
	}

	public String getHasSent() {
		return hasSent;
	}

	public void setHasSent(String hasSent) {
		this.hasSent = hasSent;
	}

	public String getHasStar() {
		return hasStar;
	}

	public void setHasStar(String hasStar) {
		this.hasStar = hasStar;
	}

	public String getKeepUnread() {
		return keepUnread;
	}

	public void setKeepUnread(String keepUnread) {
		this.keepUnread = keepUnread;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
    
    
}