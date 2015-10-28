package com.cnit.yoyo.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.cnit.yoyo.request.vo.RequestVO;
import com.cnit.yoyo.util.Base64;
import com.cnit.yoyo.util.HttpUtils;
import com.cnit.yoyo.util.aes.BackAES;

public class HttpsClient extends TestCase {
	private static String httpUrl = "https://10.255.8.17:8443/yoyoapi/mobileService";
	// 客户端密钥库
	private String sslKeyStorePath;
	private String sslKeyStorePassword;
	private String sslKeyStoreType;
	// 客户端信任的证书
	private String sslTrustStore;
	private String sslTrustStorePassword;

	
	/** 
     * 发送https请求 
     * 为双向请求 
     * @param url 
     * @param xmlStr 
     * @return 
     */
	@Test
	public void testSSL() {  
		final String KEY_STORE_TYPE_P12 = "PKCS12";
		final String KEY_STORE_TYPE_JKS = "jks";
		final String KEY_STORE_CLIENT_PATH = "E:/ss/client.p12";
		final String KEY_STORE_TRUST_PATH = "E:/ss/client.truststore";
		final String KEY_STORE_PASSWORD = "123456";
		final String KEY_STORE_TRUST_PASSWORD = "123456";
		final String SCHEME_HTTPS = "https";  
		final int HTTPS_PORT = 8443;  
        HttpClient httpClient = new DefaultHttpClient();  
        try {
            KeyStore keyStore  = KeyStore.getInstance(KEY_STORE_TYPE_P12);  
            KeyStore trustStore  = KeyStore.getInstance(KEY_STORE_TYPE_JKS);  
            InputStream ksIn = new FileInputStream(KEY_STORE_CLIENT_PATH);  
            InputStream tsIn = new FileInputStream(new File(KEY_STORE_TRUST_PATH));  
            try {
                keyStore.load(ksIn, KEY_STORE_PASSWORD.toCharArray());  
                trustStore.load(tsIn, KEY_STORE_TRUST_PASSWORD.toCharArray());  
            } finally {
                try { ksIn.close(); } catch (Exception ignore) {}  
                try { tsIn.close(); } catch (Exception ignore) {}  
            }  
            SSLSocketFactory socketFactory = new SSLSocketFactory(keyStore, KEY_STORE_PASSWORD, trustStore);  
            Scheme sch = new Scheme(SCHEME_HTTPS, HTTPS_PORT, socketFactory);  
            httpClient.getConnectionManager().getSchemeRegistry().register(sch);  
            HttpPost httpPost = new HttpPost(httpUrl);
            RequestVO request=new RequestVO();
            Map<String,String> map =new HashMap<>();
            map.put("mobile", "18664975981");
    		map.put("fphoneostype", "5000");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setMethod("sendSmsCode");
			request.setToken("123444444444444");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
//	    	StringEntity s = new StringEntity(reqStr,"UTF-8");
//            s.setContentType("application/json");//发送json数据需要设置contentType
//            httpPost.setEntity(s);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			String spt = System.getProperty("line.separator");
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
//            HttpGet httpget = new HttpGet(HTTPS_URL);  
//            System.out.println("executing request" + httpget.getRequestLine());  
//            HttpResponse response = httpClient.execute(httpget);  
//            HttpEntity entity = response.getEntity();  
            System.out.println("----------------------------------------");  
//            System.out.println(response.getStatusLine());  
//            if (entity != null) {  
//                System.out.println("Response content length: " + entity.getContentLength());  
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));  
//                String text;  
//                while ((text = bufferedReader.readLine()) != null) {  
//                    System.out.println(text);  
//                }  
//                bufferedReader.close();  
//            }  
//            EntityUtils.consume(entity);  
        }catch(Exception e){
        	e.printStackTrace();
        }
        finally {  
            httpClient.getConnectionManager().shutdown();  
        }  
    }  
}