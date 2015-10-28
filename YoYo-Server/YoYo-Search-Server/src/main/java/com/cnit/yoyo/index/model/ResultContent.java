package com.cnit.yoyo.index.model;

public class ResultContent<T> {

	private ResultJson<T> content;

	public ResultJson<T> getContent() {
		return content;
	}

	public void setContent(ResultJson<T> content) {
		this.content = content;
	}

}
