package com.cnit.yoyo.commons.page;

/**
 * 列表分页显示控制类
 * 
 * @author wanghb
 * @date 2015-04-22
 * @version 1.0.0
 */
public class Page {

	private int page = -1;// 记录总数
	private int rows = 20;// 每页显示记录数

	private int counts = -1;// 记录总数
	private int pageSize = 20;// 每页显示记录数
	private int totalPage = 1;// 总页数
	private int currentPage = 0;// 当前页

	private int firstResult = 1;// 页面显示开始记录数
	private int lastResult = 1;// 页面显示最后记录数
	private String orderName;// 排序名称
	private String orderType;// 排序类型
	private String orderBy;

	public Page(int counts, int perPageSize) {
		// 计算所有的页面数
		this.counts = counts;
		if (counts % perPageSize == 0) {
			this.totalPage = this.counts / this.pageSize;
		} else {
			this.totalPage = this.counts / this.pageSize + 1;
		}
	}

	public Page() {
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCounts() {
		return counts;
	}

	public void setCounts(int counts, int perPageSize) {
		this.pageSize = perPageSize;
		// 计算所有的页面数
		this.counts = counts;
		if (counts % this.pageSize == 0) {
			this.totalPage = this.counts / this.pageSize;
		} else {
			this.totalPage = this.counts / this.pageSize + 1;
		}
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		if (totalPage < 1) {
			return 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getFirstResult() {
		int temp = this.currentPage - 1;
		if (temp <= 0) {
			return 0;
		}
		return this.firstResult = (this.currentPage - 1) * this.pageSize;
	}

	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}

	public int getLastResult() {
		this.lastResult = this.firstResult + this.pageSize;
		return lastResult;
	}

	public void setLastResult(int lastResult) {
		this.lastResult = lastResult;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderBy() {
		if (this.getOrderName() == null || this.getOrderName().equals("") || this.getOrderType() == null || this.getOrderType().equals("")) {
			return null;
		}
		orderBy = "order by " + this.getOrderName() + " " + this.getOrderType();
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public void setCounts(int counts) {
		this.counts = counts;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("currentPage:").append(this.currentPage).append(" counts:").append(this.counts).append(" totalPage:").append(this.totalPage);
		return sb.toString();
	}
}
