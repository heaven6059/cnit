package com.cnit.yoyo.model.member;

import java.io.Serializable;
import java.math.BigDecimal;

public class Member implements Serializable {

    /***/
	private static final long serialVersionUID = -5911063220119693800L;
	private Integer memberId;
    private Integer memberLvId;
    private Integer accountId;
    private String nickName;
    private String name;
    private String idcard;
    private String firstName;
    private String lastName;
    private String bYear;
    private String bMonth;
    private String bDay;
    private String sex;
    private String mobile;
    private String tel;
    private String email;
    private String area;
    private String addr;
    private String zip;
    private String wedlock;
    private String education;
    private String vocation;
    private String cur;
    private String lang;
    private BigDecimal advance;
    private BigDecimal advanceFreeze;
    private BigDecimal experience;
    private BigDecimal pointSummation;
    private BigDecimal pointUseable;
    private BigDecimal pointUsed;
    private BigDecimal pointDisrate;
    private String settledTime;
    private BigDecimal bizMoney;
    private String referId;
    private String referUrl;
    private BigDecimal infoIntegrity;
    private String state;
    private Integer orderNum;
    private String channel;
    private String regUserCenter;
    private Integer userCenterId;
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" memberId=").append(memberId);
        sb.append(", memberLvId=").append(memberLvId);
        sb.append(", accountId=").append(accountId);
        sb.append(", nickName=").append(nickName);
        sb.append(", name=").append(name);
        sb.append(", idcard=").append(idcard);
        sb.append(", firstName=").append(firstName);
        sb.append(", lastName=").append(lastName);
        sb.append(", bYear=").append(bYear);
        sb.append(", bMonth=").append(bMonth);
        sb.append(", bDay=").append(bDay);
        sb.append(", sex=").append(sex);
        sb.append(", mobile=").append(mobile);
        sb.append(", tel=").append(tel);
        sb.append(", email=").append(email);
        sb.append(", area=").append(area);
        sb.append(", addr=").append(addr);
        sb.append(", zip=").append(zip);
        sb.append(", wedlock=").append(wedlock);
        sb.append(", education=").append(education);
        sb.append(", vocation=").append(vocation);
        sb.append(", cur=").append(cur);
        sb.append(", lang=").append(lang);
        sb.append(", advance=").append(advance);
        sb.append(", advanceFreeze=").append(advanceFreeze);
        sb.append(", experience=").append(experience);
        sb.append(", pointSummation=").append(pointSummation);
        sb.append(", pointUseable=").append(pointUseable);
        sb.append(", pointUsed=").append(pointUsed);
        sb.append(", pointDisrate=").append(pointDisrate);
        sb.append(", settledTime=").append(settledTime);
        sb.append(", bizMoney=").append(bizMoney);
        sb.append(", referId=").append(referId);
        sb.append(", referUrl=").append(referUrl);
        sb.append(", infoIntegrity=").append(infoIntegrity);
        sb.append(", state=").append(state);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", channel=").append(channel);
        return sb.toString();
    }

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getMemberLvId() {
		return memberLvId;
	}

	public void setMemberLvId(Integer memberLvId) {
		this.memberLvId = memberLvId;
	}

	public Integer getAccountId() {
		return accountId;
	}

	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getbYear() {
		return bYear;
	}

	public void setbYear(String bYear) {
		this.bYear = bYear;
	}

	public String getbMonth() {
		return bMonth;
	}

	public void setbMonth(String bMonth) {
		this.bMonth = bMonth;
	}

	public String getbDay() {
		return bDay;
	}

	public void setbDay(String bDay) {
		this.bDay = bDay;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getWedlock() {
		return wedlock;
	}

	public void setWedlock(String wedlock) {
		this.wedlock = wedlock;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getVocation() {
		return vocation;
	}

	public void setVocation(String vocation) {
		this.vocation = vocation;
	}

	public String getCur() {
		return cur;
	}

	public void setCur(String cur) {
		this.cur = cur;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public BigDecimal getAdvance() {
		return advance;
	}

	public void setAdvance(BigDecimal advance) {
		this.advance = advance;
	}

	public BigDecimal getAdvanceFreeze() {
		return advanceFreeze;
	}

	public void setAdvanceFreeze(BigDecimal advanceFreeze) {
		this.advanceFreeze = advanceFreeze;
	}

	public BigDecimal getExperience() {
		return experience;
	}

	public void setExperience(BigDecimal experience) {
		this.experience = experience;
	}

	public BigDecimal getPointSummation() {
		return pointSummation;
	}

	public void setPointSummation(BigDecimal pointSummation) {
		this.pointSummation = pointSummation;
	}

	public BigDecimal getPointUseable() {
		return pointUseable;
	}

	public void setPointUseable(BigDecimal pointUseable) {
		this.pointUseable = pointUseable;
	}

	public BigDecimal getPointUsed() {
		return pointUsed;
	}

	public void setPointUsed(BigDecimal pointUsed) {
		this.pointUsed = pointUsed;
	}

	public BigDecimal getPointDisrate() {
		return pointDisrate;
	}

	public void setPointDisrate(BigDecimal pointDisrate) {
		this.pointDisrate = pointDisrate;
	}

	public String getSettledTime() {
		return settledTime;
	}

	public void setSettledTime(String settledTime) {
		this.settledTime = settledTime;
	}

	public BigDecimal getBizMoney() {
		return bizMoney;
	}

	public void setBizMoney(BigDecimal bizMoney) {
		this.bizMoney = bizMoney;
	}

	public String getReferId() {
		return referId;
	}

	public void setReferId(String referId) {
		this.referId = referId;
	}

	public String getReferUrl() {
		return referUrl;
	}

	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}

	public BigDecimal getInfoIntegrity() {
		return infoIntegrity;
	}

	public void setInfoIntegrity(BigDecimal infoIntegrity) {
		this.infoIntegrity = infoIntegrity;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
    
	public String getRegUserCenter() {
		return regUserCenter;
	}

	public void setRegUserCenter(String regUserCenter) {
		this.regUserCenter = regUserCenter;
	}

	public Integer getUserCenterId() {
		return userCenterId;
	}

	public void setUserCenterId(Integer userCenterId) {
		this.userCenterId = userCenterId;
	}
	
}