package com.cnit.yoyo.model.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderRefunds implements Serializable {

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.money
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private BigDecimal money;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.cur_money
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private BigDecimal curMoney;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.bank
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String bank;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_account
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundAccount;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.account
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String account;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.currency
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String currency;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.paycost
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private BigDecimal paycost;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.pay_type
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String payType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.status
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String status;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_name
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundName;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_ver
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundVer;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.op_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private Integer opId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_bn
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundBn;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_app_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundAppId;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.t_payed
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	//@JSONField (format="yyyy-MM-dd HH:mm:ss") 
	private Date tPayed;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.t_begin
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private Date tBegin;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.t_confirm
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private Date tConfirm;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.memo
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String memo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.disabled
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String disabled;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.trade_no
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String tradeNo;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.refund_type
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String refundType;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.profit
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private BigDecimal profit;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.score_cost
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private BigDecimal scoreCost;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database column t_order_refunds.is_safeguard
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private String isSafeguard;

	/**
	 * This field was generated by MyBatis Generator. This field corresponds to
	 * the database table t_order_refunds
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_id
	 * 
	 * @return the value of t_order_refunds.refund_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundId() {
		return refundId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_id
	 * 
	 * @param refundId
	 *            the value for t_order_refunds.refund_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.money
	 * 
	 * @return the value of t_order_refunds.money
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public BigDecimal getMoney() {
		return money;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.money
	 * 
	 * @param money
	 *            the value for t_order_refunds.money
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.cur_money
	 * 
	 * @return the value of t_order_refunds.cur_money
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public BigDecimal getCurMoney() {
		return curMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.cur_money
	 * 
	 * @param curMoney
	 *            the value for t_order_refunds.cur_money
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setCurMoney(BigDecimal curMoney) {
		this.curMoney = curMoney;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.bank
	 * 
	 * @return the value of t_order_refunds.bank
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.bank
	 * 
	 * @param bank
	 *            the value for t_order_refunds.bank
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_account
	 * 
	 * @return the value of t_order_refunds.refund_account
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundAccount() {
		return refundAccount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_account
	 * 
	 * @param refundAccount
	 *            the value for t_order_refunds.refund_account
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundAccount(String refundAccount) {
		this.refundAccount = refundAccount;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.account
	 * 
	 * @return the value of t_order_refunds.account
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.account
	 * 
	 * @param account
	 *            the value for t_order_refunds.account
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.currency
	 * 
	 * @return the value of t_order_refunds.currency
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.currency
	 * 
	 * @param currency
	 *            the value for t_order_refunds.currency
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.paycost
	 * 
	 * @return the value of t_order_refunds.paycost
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public BigDecimal getPaycost() {
		return paycost;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.paycost
	 * 
	 * @param paycost
	 *            the value for t_order_refunds.paycost
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setPaycost(BigDecimal paycost) {
		this.paycost = paycost;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.pay_type
	 * 
	 * @return the value of t_order_refunds.pay_type
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getPayType() {
		return payType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.pay_type
	 * 
	 * @param payType
	 *            the value for t_order_refunds.pay_type
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.status
	 * 
	 * @return the value of t_order_refunds.status
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.status
	 * 
	 * @param status
	 *            the value for t_order_refunds.status
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_name
	 * 
	 * @return the value of t_order_refunds.refund_name
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundName() {
		return refundName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_name
	 * 
	 * @param refundName
	 *            the value for t_order_refunds.refund_name
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundName(String refundName) {
		this.refundName = refundName;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_ver
	 * 
	 * @return the value of t_order_refunds.refund_ver
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundVer() {
		return refundVer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_ver
	 * 
	 * @param refundVer
	 *            the value for t_order_refunds.refund_ver
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundVer(String refundVer) {
		this.refundVer = refundVer;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.op_id
	 * 
	 * @return the value of t_order_refunds.op_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public Integer getOpId() {
		return opId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.op_id
	 * 
	 * @param opId
	 *            the value for t_order_refunds.op_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setOpId(Integer opId) {
		this.opId = opId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_bn
	 * 
	 * @return the value of t_order_refunds.refund_bn
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundBn() {
		return refundBn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_bn
	 * 
	 * @param refundBn
	 *            the value for t_order_refunds.refund_bn
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundBn(String refundBn) {
		this.refundBn = refundBn;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_app_id
	 * 
	 * @return the value of t_order_refunds.refund_app_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundAppId() {
		return refundAppId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_app_id
	 * 
	 * @param refundAppId
	 *            the value for t_order_refunds.refund_app_id
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundAppId(String refundAppId) {
		this.refundAppId = refundAppId;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.t_payed
	 * 
	 * @return the value of t_order_refunds.t_payed
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public Date gettPayed() {
		return tPayed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.t_payed
	 * 
	 * @param tPayed
	 *            the value for t_order_refunds.t_payed
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void settPayed(Date tPayed) {
		this.tPayed = tPayed;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.t_begin
	 * 
	 * @return the value of t_order_refunds.t_begin
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	//@JSONField(format="yyyy-MM-dd HH:mm:ss")
	public Date gettBegin() {
		return tBegin;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.t_begin
	 * 
	 * @param tBegin
	 *            the value for t_order_refunds.t_begin
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void settBegin(Date tBegin) {
		this.tBegin = tBegin;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.t_confirm
	 * 
	 * @return the value of t_order_refunds.t_confirm
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public Date gettConfirm() {
		return tConfirm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.t_confirm
	 * 
	 * @param tConfirm
	 *            the value for t_order_refunds.t_confirm
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void settConfirm(Date tConfirm) {
		this.tConfirm = tConfirm;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.memo
	 * 
	 * @return the value of t_order_refunds.memo
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.memo
	 * 
	 * @param memo
	 *            the value for t_order_refunds.memo
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.disabled
	 * 
	 * @return the value of t_order_refunds.disabled
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.disabled
	 * 
	 * @param disabled
	 *            the value for t_order_refunds.disabled
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.trade_no
	 * 
	 * @return the value of t_order_refunds.trade_no
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getTradeNo() {
		return tradeNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.trade_no
	 * 
	 * @param tradeNo
	 *            the value for t_order_refunds.trade_no
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.refund_type
	 * 
	 * @return the value of t_order_refunds.refund_type
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getRefundType() {
		return refundType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.refund_type
	 * 
	 * @param refundType
	 *            the value for t_order_refunds.refund_type
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.profit
	 * 
	 * @return the value of t_order_refunds.profit
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public BigDecimal getProfit() {
		return profit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.profit
	 * 
	 * @param profit
	 *            the value for t_order_refunds.profit
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.score_cost
	 * 
	 * @return the value of t_order_refunds.score_cost
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public BigDecimal getScoreCost() {
		return scoreCost;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.score_cost
	 * 
	 * @param scoreCost
	 *            the value for t_order_refunds.score_cost
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setScoreCost(BigDecimal scoreCost) {
		this.scoreCost = scoreCost;
	}

	/**
	 * This method was generated by MyBatis Generator. This method returns the
	 * value of the database column t_order_refunds.is_safeguard
	 * 
	 * @return the value of t_order_refunds.is_safeguard
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public String getIsSafeguard() {
		return isSafeguard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method sets the
	 * value of the database column t_order_refunds.is_safeguard
	 * 
	 * @param isSafeguard
	 *            the value for t_order_refunds.is_safeguard
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	public void setIsSafeguard(String isSafeguard) {
		this.isSafeguard = isSafeguard;
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_order_refunds
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", refundId=").append(refundId);
		sb.append(", money=").append(money);
		sb.append(", curMoney=").append(curMoney);
		sb.append(", bank=").append(bank);
		sb.append(", refundAccount=").append(refundAccount);
		sb.append(", account=").append(account);
		sb.append(", currency=").append(currency);
		sb.append(", paycost=").append(paycost);
		sb.append(", payType=").append(payType);
		sb.append(", status=").append(status);
		sb.append(", refundName=").append(refundName);
		sb.append(", refundVer=").append(refundVer);
		sb.append(", opId=").append(opId);
		sb.append(", refundBn=").append(refundBn);
		sb.append(", refundAppId=").append(refundAppId);
		sb.append(", tPayed=").append(tPayed);
		sb.append(", tBegin=").append(tBegin);
		sb.append(", tConfirm=").append(tConfirm);
		sb.append(", memo=").append(memo);
		sb.append(", disabled=").append(disabled);
		sb.append(", tradeNo=").append(tradeNo);
		sb.append(", refundType=").append(refundType);
		sb.append(", profit=").append(profit);
		sb.append(", scoreCost=").append(scoreCost);
		sb.append(", isSafeguard=").append(isSafeguard);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_order_refunds
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		OrderRefunds other = (OrderRefunds) that;
		return (this.getRefundId() == null ? other.getRefundId() == null : this
				.getRefundId().equals(other.getRefundId()))
				&& (this.getMoney() == null ? other.getMoney() == null : this
						.getMoney().equals(other.getMoney()))
				&& (this.getCurMoney() == null ? other.getCurMoney() == null
						: this.getCurMoney().equals(other.getCurMoney()))
				&& (this.getBank() == null ? other.getBank() == null : this
						.getBank().equals(other.getBank()))
				&& (this.getRefundAccount() == null ? other.getRefundAccount() == null
						: this.getRefundAccount().equals(
								other.getRefundAccount()))
				&& (this.getAccount() == null ? other.getAccount() == null
						: this.getAccount().equals(other.getAccount()))
				&& (this.getCurrency() == null ? other.getCurrency() == null
						: this.getCurrency().equals(other.getCurrency()))
				&& (this.getPaycost() == null ? other.getPaycost() == null
						: this.getPaycost().equals(other.getPaycost()))
				&& (this.getPayType() == null ? other.getPayType() == null
						: this.getPayType().equals(other.getPayType()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getRefundName() == null ? other.getRefundName() == null
						: this.getRefundName().equals(other.getRefundName()))
				&& (this.getRefundVer() == null ? other.getRefundVer() == null
						: this.getRefundVer().equals(other.getRefundVer()))
				&& (this.getOpId() == null ? other.getOpId() == null : this
						.getOpId().equals(other.getOpId()))
				&& (this.getRefundBn() == null ? other.getRefundBn() == null
						: this.getRefundBn().equals(other.getRefundBn()))
				&& (this.getRefundAppId() == null ? other.getRefundAppId() == null
						: this.getRefundAppId().equals(other.getRefundAppId()))
				&& (this.gettPayed() == null ? other.gettPayed() == null : this
						.gettPayed().equals(other.gettPayed()))
				&& (this.gettBegin() == null ? other.gettBegin() == null : this
						.gettBegin().equals(other.gettBegin()))
				&& (this.gettConfirm() == null ? other.gettConfirm() == null
						: this.gettConfirm().equals(other.gettConfirm()))
				&& (this.getMemo() == null ? other.getMemo() == null : this
						.getMemo().equals(other.getMemo()))
				&& (this.getDisabled() == null ? other.getDisabled() == null
						: this.getDisabled().equals(other.getDisabled()))
				&& (this.getTradeNo() == null ? other.getTradeNo() == null
						: this.getTradeNo().equals(other.getTradeNo()))
				&& (this.getRefundType() == null ? other.getRefundType() == null
						: this.getRefundType().equals(other.getRefundType()))
				&& (this.getProfit() == null ? other.getProfit() == null : this
						.getProfit().equals(other.getProfit()))
				&& (this.getScoreCost() == null ? other.getScoreCost() == null
						: this.getScoreCost().equals(other.getScoreCost()))
				&& (this.getIsSafeguard() == null ? other.getIsSafeguard() == null
						: this.getIsSafeguard().equals(other.getIsSafeguard()));
	}

	/**
	 * This method was generated by MyBatis Generator. This method corresponds
	 * to the database table t_order_refunds
	 * 
	 * @mbggenerated Fri Apr 17 11:32:57 CST 2015
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getRefundId() == null) ? 0 : getRefundId().hashCode());
		result = prime * result
				+ ((getMoney() == null) ? 0 : getMoney().hashCode());
		result = prime * result
				+ ((getCurMoney() == null) ? 0 : getCurMoney().hashCode());
		result = prime * result
				+ ((getBank() == null) ? 0 : getBank().hashCode());
		result = prime
				* result
				+ ((getRefundAccount() == null) ? 0 : getRefundAccount()
						.hashCode());
		result = prime * result
				+ ((getAccount() == null) ? 0 : getAccount().hashCode());
		result = prime * result
				+ ((getCurrency() == null) ? 0 : getCurrency().hashCode());
		result = prime * result
				+ ((getPaycost() == null) ? 0 : getPaycost().hashCode());
		result = prime * result
				+ ((getPayType() == null) ? 0 : getPayType().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getRefundName() == null) ? 0 : getRefundName().hashCode());
		result = prime * result
				+ ((getRefundVer() == null) ? 0 : getRefundVer().hashCode());
		result = prime * result
				+ ((getOpId() == null) ? 0 : getOpId().hashCode());
		result = prime * result
				+ ((getRefundBn() == null) ? 0 : getRefundBn().hashCode());
		result = prime
				* result
				+ ((getRefundAppId() == null) ? 0 : getRefundAppId().hashCode());
		result = prime * result
				+ ((gettPayed() == null) ? 0 : gettPayed().hashCode());
		result = prime * result
				+ ((gettBegin() == null) ? 0 : gettBegin().hashCode());
		result = prime * result
				+ ((gettConfirm() == null) ? 0 : gettConfirm().hashCode());
		result = prime * result
				+ ((getMemo() == null) ? 0 : getMemo().hashCode());
		result = prime * result
				+ ((getDisabled() == null) ? 0 : getDisabled().hashCode());
		result = prime * result
				+ ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
		result = prime * result
				+ ((getRefundType() == null) ? 0 : getRefundType().hashCode());
		result = prime * result
				+ ((getProfit() == null) ? 0 : getProfit().hashCode());
		result = prime * result
				+ ((getScoreCost() == null) ? 0 : getScoreCost().hashCode());
		result = prime
				* result
				+ ((getIsSafeguard() == null) ? 0 : getIsSafeguard().hashCode());
		return result;
	}
}