package com.cnit.yoyo.index.model;

import java.util.List;

public class ResultJson<T> {

	private Object content;
	private int pageIndex;
	private int pageSize;
	private int currPageSize;
	private long total;
	private int pages;
	private List<T> rows;

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrPageSize() {
		return currPageSize;
	}

	public void setCurrPageSize(int currPageSize) {
		this.currPageSize = currPageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
