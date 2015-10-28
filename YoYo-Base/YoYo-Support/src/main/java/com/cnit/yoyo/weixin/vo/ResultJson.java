/*
 * 文 件 名  :  ResultJson.java
 * 版    权    :  Ltd. Copyright (c) 2013 深圳市商巢互联网软件有限公司,All rights reserved
 * 描    述    :  <描述>
 * 创建人    :  彭彩云
 * 创建时间:  下午3:37:26
 */
package com.cnit.yoyo.weixin.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

/**
 * JSON对象
 * 
 * @author 彭彩云
 * @version [版本号, 2013-7-1]
 */
public class ResultJson implements Serializable {
    
    /**
     * 注释
     */
    private static final long serialVersionUID = -8948747420144666605L;
    
    private boolean success = false;
    
    private String msg;
    
    private Object object;
    
    public ResultJson(boolean success, String msg, Object object) {
        this.success = success;
        this.msg = msg;
        this.object = object;
    }
    
    public ResultJson(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }
    
    public ResultJson(boolean success, Object object) {
        this.success = success;
        this.object = object;
    }
    
    public ResultJson(boolean success) {
        this.success = success;
    }
    
    public ResultJson() {
        
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public Object getObject() {
        return object;
    }
    
    public void setObject(Object object) {
        this.object = object;
    }
    
    /**
     * 返回该类的json字符串
     * @return
     */
    public String toString() {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		resultMap.put("success", success);
		resultMap.put("msg", msg);
		resultMap.put("object", object);
		JSONObject jsonObject=JSONObject.fromObject(resultMap);
		return jsonObject.toString();
	}
}
