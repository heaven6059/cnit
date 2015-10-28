package com.cnit.yoyo.thirdPlatform.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientSendData {
	static Log log = LogFactory.getLog(ClientSendData.class);

	private String Url;

	// 初始化数据
	public ClientSendData() {
		Url = "https://test.yihaodian.com:8443/ims/feedbackToPingAn_getData.action";
	}

	public String sendData(String data) {
		String receivedData = null;
		try {

			Map<String, String> paramsData = new HashMap<String, String>();
			paramsData.put("data", data);
			receivedData = send(Url, paramsData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return receivedData;
	}

	public static String send(String url, Map<String, String> paramsMap) {
		String result = null;
		PostMethod postMethod = null;
		HttpClient httpClient = new HttpClient();

		httpClient.getParams().setParameter(
				HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		postMethod = new PostMethod(url);

		if (paramsMap != null && paramsMap.size() > 0) {
			NameValuePair[] datas = new NameValuePair[paramsMap.size()];
			int index = 0;
			for (String key : paramsMap.keySet()) {
				datas[index++] = new NameValuePair(key, paramsMap.get(key));
			}
			postMethod.setRequestBody(datas);

		}

		HttpClientParams httparams = new HttpClientParams();
		httparams.setSoTimeout(60000);
		postMethod.setParams(httparams);

		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				result = postMethod.getResponseBodyAsString();
				log.info("发送成功！");
			} else {
				log.error(" http response status is " + statusCode);
			}

		} catch (HttpException e) {
			log.error("error url=" + url, e);
		} catch (IOException e) {
			log.error("error url=" + url, e);
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}

		return result;
	}

	public static void main(String[] args) {
		ClientSendData t = new ClientSendData();
		t.sendData("测试SSL单项连接，向服务端发送数据!");
	}
}
