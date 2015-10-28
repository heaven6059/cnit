package com.cnit.yoyo.request.vo;  

import java.util.List;
import java.util.Map;
  
public class RequestVO {
	private Map data;
	private List lastReqNote;
	private String method;
	private String sessionid;
	private String token;
	public Map getData() {
		return data;
	}
	public void setData(Map data) {
		this.data = data;
	}
	public List getLastReqNote() {
		return lastReqNote;
	}
	public void setLastReqNote(List lastReqNote) {
		this.lastReqNote = lastReqNote;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
