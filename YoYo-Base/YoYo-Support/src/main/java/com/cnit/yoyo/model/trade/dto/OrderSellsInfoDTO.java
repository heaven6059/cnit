package com.cnit.yoyo.model.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description: 订单提示DTO
 * @author 王鹏
 * @date 2015年7月29日 下午2:37:24
 * @Copyright 2015 cnit
 * @version V1.0.0
 */
public class OrderSellsInfoDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5316512707137861966L;
	private Integer dayCount;
	private BigDecimal daySumMoney;
	private Integer dayPayCount;
	private BigDecimal dayPayMoney;
	private Integer monthCount;
	private BigDecimal monthSumMoney;
	private Integer monthPayCount;
	private BigDecimal monthPayMoney;

	public Integer getDayCount() {
		return dayCount;
	}

	public void setDayCount(Integer dayCount) {
		this.dayCount = dayCount;
	}

	public BigDecimal getDaySumMoney() {
		return daySumMoney;
	}

	public void setDaySumMoney(BigDecimal daySumMoney) {
		this.daySumMoney = daySumMoney;
	}

	public Integer getDayPayCount() {
		return dayPayCount;
	}

	public void setDayPayCount(Integer dayPayCount) {
		this.dayPayCount = dayPayCount;
	}

	public BigDecimal getDayPayMoney() {
		return dayPayMoney;
	}

	public void setDayPayMoney(BigDecimal dayPayMoney) {
		this.dayPayMoney = dayPayMoney;
	}

	public Integer getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}

	public BigDecimal getMonthSumMoney() {
		return monthSumMoney;
	}

	public void setMonthSumMoney(BigDecimal monthSumMoney) {
		this.monthSumMoney = monthSumMoney;
	}

	public Integer getMonthPayCount() {
		return monthPayCount;
	}

	public void setMonthPayCount(Integer monthPayCount) {
		this.monthPayCount = monthPayCount;
	}

	public BigDecimal getMonthPayMoney() {
		return monthPayMoney;
	}

	public void setMonthPayMoney(BigDecimal monthPayMoney) {
		this.monthPayMoney = monthPayMoney;
	}

}
