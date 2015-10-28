/**
 * 文 件 名   :  SmsService.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  &lt;描述&gt;
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-1-26 下午3:01:23
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.rmi.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.apache.commons.codec.binary.Base64;

import cn.taoping.jsonrpc.core.dto.ReqParamsObj;
import cn.taoping.jsonrpc.core.dto.ResponseObj;
import cn.taoping.mainserver.service.IMainService;

import com.cnit.yoyo.constant.ErrorCode;
import com.cnit.yoyo.constant.GlobalStatic;
import com.cnit.yoyo.domain.HeadObject;
import com.cnit.yoyo.domain.ResultObject;
import com.cnit.yoyo.util.CryptUtils;
import com.cnit.yoyo.util.HttpUtils;
import com.cnit.yoyo.util.StringUtil;

/**
 * @description 短信中心
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class SmsService {
	@Autowired
	private IMainService mainService;
    private static final Logger log = LoggerFactory.getLogger(SmsService.class);
    private static Properties smsTemplate;
    private static Resource configLocation;

    public Resource getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(Resource configLocation) {
        SmsService.configLocation = configLocation;
        if (SmsService.configLocation != null) {
            SmsService.smsTemplate = new Properties();
            try {
                SmsService.smsTemplate.load(configLocation.getInputStream());
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    public Object sendSms(Object reqData) throws Exception {
    	Map dataMap = (Map) reqData;
        String url=smsTemplate.getProperty("sms.center.url");
        HeadObject headObject = new HeadObject();
        Map<String,String> params = new HashMap<>();
        params.put("un", smsTemplate.getProperty("sms.center.account"));
        params.put("pw", smsTemplate.getProperty("sms.center.password"));
        params.put("pc", (String) dataMap.get("mobile"));
        params.put("msg", StringUtil.replace(smsTemplate.getProperty((String) dataMap.get("template")),smsTemplate.getProperty("sms.template.placeholder"), (String) dataMap.get("smsCode")));
        String retSult=HttpUtils.get(url, params);
        Document doc = DocumentHelper.parseText(retSult); 
        Element root = doc.getRootElement();
        headObject.setRetType(GlobalStatic.RET_TYPE_OTHER);
        headObject.setRetCode((String) dataMap.get("smsCode"));
        String tempStr=getSmsResult(root.elementText("result"));
        headObject.setRetMsg(tempStr);
        log.info("--短信发送结果:{}",tempStr);
        return headObject;
    }
    
    private String getSmsResult(String smsResult) {
    	switch (smsResult) {
		case "0":
			return "短信成功";
		case "1":
			return "登陆失败";
		case "2":
			return "余额不足";
		case "3":
			return "内容违法，“|”后面追加违法关键字";
		case "4":
			return "号码为空";
		case "5":
			return "内容为空，请求中没有“msg”";
		case "6":
			return "时间格式不对，请求中包括sendTime的时候";
		case "7":
			return "请求中有“msg”，内容为空或者超过最大长度70";
		case "8":
			return "请求参数正确，但是系统异常，具体错误要看异常";
		default:
			return "发短信异常";
		}
	}

	public Object sendSmsFormTaoPing1(Object reqData) throws Exception {
    	HeadObject headObject = new HeadObject();
    	headObject.setRetType(GlobalStatic.RET_TYPE_OTHER);
    	JSONObject content=JSONObject.fromObject(reqData);
    	Map<String, Object> map = new HashMap<String, Object>();
		map.put("userMobile", content.getString("mobile"));//发送手机号码 
		ReqParamsObj reqparam = new ReqParamsObj();
		reqparam.setParamMap(map);
    	ResponseObj resp=mainService.sendVerifySMS(reqparam);
    	headObject.setRetCode(resp.getResultCode()+"");
        headObject.setRetMsg(resp.getMsg());
		return headObject;
    }
	
	public Object sendSmsFromTaoPing(Object reqData) throws Exception {
    	HeadObject headObject = new HeadObject();
    	headObject.setRetType(GlobalStatic.RET_TYPE_OTHER);
    	JSONObject content=JSONObject.fromObject(reqData);
		Map<String,String> mapReq=new HashMap<String, String>();
		mapReq.put("userMobile", content.getString("mobile"));
		mapReq.put("appKey", smsTemplate.getProperty("appKey"));
		String appId = smsTemplate.getProperty("appId");
		String json = JSONObject.fromObject(mapReq).toString();
		String param2 = Base64.encodeBase64String(json.getBytes());
		String key =CryptUtils.hmacSHA1Encrypt(param2 + appId, CryptUtils.PRIVATE_KEY);
		String url = "http://app.cnitcloud.com/pub/regUserController/sendSMS";
		Map<String,String> params=new HashMap<String, String>();
		params.put("appId", appId);
		params.put("key", key);
		params.put("param2", param2);
		String retVal=HttpUtils.get(url, params);
		return new ResultObject(new HeadObject(ErrorCode.SUCCESS), retVal);
	}
	
	public Object verifySMSFromTaoPing(Object reqData) throws Exception {
    	HeadObject headObject = new HeadObject();
    	headObject.setRetType(GlobalStatic.RET_TYPE_OTHER);
    	JSONObject content=JSONObject.fromObject(reqData);
		Map<String,String> mapReq=new HashMap<String, String>();
		mapReq.put("userMobile", content.getString("userMobile"));
		mapReq.put("verificationCode", content.getString("smsCode"));
		mapReq.put("taskID", content.getString("taskID"));
		mapReq.put("appKey", smsTemplate.getProperty("appKey"));
		String appId = smsTemplate.getProperty("appId");
		String json = JSONObject.fromObject(mapReq).toString();
		String param2 = Base64.encodeBase64String(json.getBytes());
		String key =CryptUtils.hmacSHA1Encrypt(param2 + appId, CryptUtils.PRIVATE_KEY);
		String url = "http://app.cnitcloud.com/pub/regUserController/verifySMS";
		Map<String,String> params=new HashMap<String, String>();
		params.put("appId", appId);
		params.put("key", key);
		params.put("param2", param2);
		String retVal=HttpUtils.get(url, params);
        return new ResultObject(new HeadObject(ErrorCode.SUCCESS), retVal);
	}
}