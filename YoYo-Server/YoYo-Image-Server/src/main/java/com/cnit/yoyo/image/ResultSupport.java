package com.cnit.yoyo.image; 

import java.util.HashMap;
import java.util.Map;

/** 
 * @author  wanghb
 * @version V1.0 
 * @createDateTime：2015-05-12
 * @Company: cnit
 * @Copyright: Copyright (c) 2015
 **/
public class ResultSupport implements Result{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6577349985018598129L;
	
	private boolean success;
	private String resultCode;
	private Object model;
	private Object error;
	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	private Map map = new HashMap();
	private Map<String, String> errorMessages = new HashMap<String, String>();

	public Object getDefaultModel() {
		return model;
	}

	public String getDefaultModelKey() {
		return null;
	}

	public Map getModels() {
		return map;
	}

	public String getResultCode() {
		return resultCode;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setDefaultModel(Object model) {
		this.model = model;

	}

	public void setDefaultModel(String key, Object model) {
		map.put(key, model);
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setErrorMessages(String key, String error) {
		errorMessages.put(key, error);
		// 如果抛出错误信息一定返回false
		this.setSuccess(false);
	}

	public Map<String, String> getErrorMessages() {
		return errorMessages;
	}

}
 