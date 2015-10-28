package com.cnit.yoyo.advance.model;
/**   
 * @Description: t_member_advance  会员预存款日志表
 * @author  余平 yuping@cnit.com 
 * @date 2015-5-9 下午2:46:45 
 * @Copyright 2015 cnit
 * @version V1.0.0 		
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class AdvanceHistory implements Serializable {
    /***/
	private static final long serialVersionUID = 1422739624596226736L;
	private Integer logId;//日志id
    private Integer memberId;//用户id
    private BigDecimal money;//出入金额
    private String message;//管理备注
    private Timestamp mtime;//交易时间
    private String paymentId;//支付单号
    private Long orderId;//订单号
    private String paymethod;//支付方式
    private String memo;//业务摘要
    private BigDecimal importMoney;//存入金额
    private BigDecimal shopAdvance;//商店余额
    private BigDecimal memberAdvance;//当前余额
    private BigDecimal explodeMoney;//支出金额
    private String disabled;//失效
  
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("logId=").append(logId);
        sb.append(", memberId=").append(memberId);
        sb.append(", money=").append(money);
        sb.append(", message=").append(message);
        sb.append(", mtime=").append(mtime);
        sb.append(", paymentId=").append(paymentId);
        sb.append(", orderId=").append(orderId);
        sb.append(", paymethod=").append(paymethod);
        sb.append(", memo=").append(memo);
        sb.append(", importMoney=").append(importMoney);
        sb.append(", shopAdvance=").append(shopAdvance);
        sb.append(", memberAdvance=").append(memberAdvance);
        sb.append(", explodeMoney=").append(explodeMoney);
        sb.append(", disabled=").append(disabled);
        return sb.toString();
    }

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public Timestamp getMtime() {
		return mtime;
	}

	public void setMtime(Timestamp mtime) {
		this.mtime = mtime;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public BigDecimal getImportMoney() {
		return importMoney;
	}

	public void setImportMoney(BigDecimal importMoney) {
		this.importMoney = importMoney;
	}

	public BigDecimal getShopAdvance() {
		return shopAdvance;
	}

	public void setShopAdvance(BigDecimal shopAdvance) {
		this.shopAdvance = shopAdvance;
	}

	public BigDecimal getMemberAdvance() {
		return memberAdvance;
	}

	public void setMemberAdvance(BigDecimal memberAdvance) {
		this.memberAdvance = memberAdvance;
	}

	public BigDecimal getExplodeMoney() {
		return explodeMoney;
	}

	public void setExplodeMoney(BigDecimal explodeMoney) {
		this.explodeMoney = explodeMoney;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
    
    
}