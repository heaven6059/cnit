package com.cnit.yoyo.spider.common.dto.agent.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProxyIp implements Serializable {
	private static final long serialVersionUID = 3204567090918267869L;

	private Long id;
	private String ip;
	private String port;
	private String anonymous;
	private String addr;
	private String responseSpeed;
	private String lastCheckTime;
	private Date spiderTime;
	private Integer protyType; // 0为http代理,1为socket代理
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(String anonymous) {
		this.anonymous = anonymous;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getResponseSpeed() {
		return responseSpeed;
	}

	public void setResponseSpeed(String responseSpeed) {
		this.responseSpeed = responseSpeed;
	}

	public String getLastCheckTime() {
		return lastCheckTime;
	}

	public void setLastCheckTime(String lastCheckTime) {
		this.lastCheckTime = lastCheckTime;
	}

	public Date getSpiderTime() {
		return spiderTime;
	}

	public void setSpiderTime(Date spiderTime) {
		this.spiderTime = spiderTime;
	}

	public Integer getProtyType() {
		return protyType;
	}

	public void setProtyType(Integer protyType) {
		this.protyType = protyType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
