/**
 * 文 件 名   :  HeadObject.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-5 上午10:01:22
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.domain;

import java.io.Serializable;
import java.util.Locale;

import com.cnit.yoyo.constant.ErrorCode;

/**
 * @description 系统间通讯的头部信息
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class HeadObject implements Serializable {
    private static final long serialVersionUID = -4086413345296784537L;
    /** 请求服务的系统标志 */
    private String consumerCode;
    /** 提供服务的系统标志 */
    private String providerCode;
    /** 服务标志 */
    private String serviceCode;
    /** 时间 */
    private String dateTime;
    /** 请求IP */
    private String ip;
    /** 序列号 */
    private String seqno;
    /** 请求来源 PC: 标准平台,MOBILE:手机触屏 */
    private String source;
    /** 语言位置 */
    private Locale locale;
    /** 请求来源Id */
    private String referId;
    /** 请求来源url */
    private String referUrl;
    /** 状态类型 1：本系统 2：外部系统 */
    private String retType;
    /** 请求状态码 */
    private String retCode;
    /** 请求信息 */
    private String retMsg;
   
    public HeadObject() {

    }

    public HeadObject(String retCode) {
        this.retCode = retCode;
    }

    public HeadObject(String retCode, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    
    public HeadObject(String retCode,String retType, String retMsg) {
        this.retCode = retCode;
        this.retMsg = retMsg;
        this.retType = retType;
    }

    public String getConsumerCode() {
        return consumerCode;
    }

    public void setConsumerCode(String consumerCode) {
        this.consumerCode = consumerCode;
    }

    public String getProviderCode() {
        return providerCode;
    }

    public void setProviderCode(String providerCode) {
        this.providerCode = providerCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSeqno() {
        return seqno;
    }

    public void setSeqno(String seqno) {
        this.seqno = seqno;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getReferId() {
        return referId;
    }

    public void setReferId(String referId) {
        this.referId = referId;
    }

    public String getReferUrl() {
        return referUrl;
    }

    public void setReferUrl(String referUrl) {
        this.referUrl = referUrl;
    }

    public String getRetType() {
        return retType;
    }

    public void setRetType(String retType) {
        this.retType = retType;
    }

    public String getRetCode() {
        return retCode;
    }

    public HeadObject setRetCode(String retCode) {
        this.retCode = retCode;
        return this;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public HeadObject setRetMsg(String retMsg) {
        this.retMsg = retMsg;
        return this;
    }


}
