package com.cnit.yoyo.model.goods.dto;

import java.io.Serializable;

public class GoodsStatisticsDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 807500668797734938L;
	private Integer totalCount;
	private Integer sellsCount;
	private Integer putawayCount;
	private Integer checkCount;
	private Integer backCount;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getSellsCount() {
		return sellsCount;
	}

	public void setSellsCount(Integer sellsCount) {
		this.sellsCount = sellsCount;
	}

	public Integer getPutawayCount() {
		return putawayCount;
	}

	public void setPutawayCount(Integer putawayCount) {
		this.putawayCount = putawayCount;
	}

	public Integer getCheckCount() {
		return checkCount;
	}

	public void setCheckCount(Integer checkCount) {
		this.checkCount = checkCount;
	}

	public Integer getBackCount() {
		return backCount;
	}

	public void setBackCount(Integer backCount) {
		this.backCount = backCount;
	}

}
