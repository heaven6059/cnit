
package com.cnit.yoyo.model.member.dto;

import java.io.Serializable;

/**
 * @ClassName: MemberDTO 
 * @Description: 会员信息传输对象
 * @author xiaox
 * @date 2015-3-12 下午1:45:21
 */
public class MemberDTO implements Serializable {
    

	private static final long serialVersionUID = 6979666041591009161L;
	private String loginName;
    private String accountType;
    private String accountStatus;
    private String name;
    private String nickName;
    private String idcard;
    private String mobile;
    private String email;
    private String channel;
    private Integer memberLvId;
    
    private Integer memberId; //会员编号
    private Integer accountId; 
    private Float advance; //预存款
    private Float pointSummation; //积分
    private Integer orderNum; //订单数
    private String startTime;
    private String endTime;
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Integer getMemberLvId() {
		return memberLvId;
	}
	public void setMemberLvId(Integer memberLvId) {
		this.memberLvId = memberLvId;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
	public Float getAdvance() {
		return advance;
	}
	public void setAdvance(Float advance) {
		this.advance = advance;
	}
	public Float getPointSummation() {
		return pointSummation;
	}
	public void setPointSummation(Float pointSummation) {
		this.pointSummation = pointSummation;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	

}
