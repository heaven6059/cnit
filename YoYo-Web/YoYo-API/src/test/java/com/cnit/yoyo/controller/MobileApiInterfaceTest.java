package com.cnit.yoyo.controller;  

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.hibernate.validator.constraints.NotBlank;
import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianProxyFactory;
import com.cnit.yoyo.file.FileDataInerface;
import com.cnit.yoyo.request.vo.RequestVO;
import com.cnit.yoyo.util.Base64;
import com.cnit.yoyo.util.HttpUtils;
import com.cnit.yoyo.util.aes.BackAES;

/**
 * 手机api接口测试类
 * @Author:yangyi  
 * @Date:2015年7月10日  
 * @Version:1.0.0
 */
public class MobileApiInterfaceTest extends TestCase {
	private static String url = "http://127.0.0.1:8083/yoyoapi/mobileService";
	final String KEY_STORE_TYPE_P12 = "PKCS12";
	final String KEY_STORE_TYPE_JKS = "jks";
	final String KEY_STORE_CLIENT_PATH = "E:/ssll/client.p12";
	final String KEY_STORE_TRUST_PATH = "E:/ssll/client.truststore";
	final String KEY_STORE_PASSWORD = "123456";
	final String KEY_STORE_TRUST_PASSWORD = "123456";
	final String SCHEME_HTTPS = "https";  
	final int HTTPS_PORT = 8443;  
	
	private HttpClient getHttpClient(){
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
        }catch(Exception e){
        	e.printStackTrace();
        }
		return httpClient;
	}
	
	@Test
	/**
	 * @Description:图片上传接口测试
	 */
	public void testPaintingUploadImg(){
		try {
			HttpClient httpClient = new DefaultHttpClient();
			FileInputStream fileForInput = new FileInputStream("E:\\QQ图片20150715130942.jpg");  
            byte[] bytes = new byte[fileForInput.available()];  
            if(bytes.length<102400){  
                System.out.print(bytes.length);  
            }  
            fileForInput.read(bytes);  
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("file", Base64.encode(bytes));
	        fileForInput.close();  
			map.put("finterfacename", "");
			map.put("finterfacecode", "");
			map.put("fphoneos", "ios");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			request.setData(map);
			request.setMethod("paintingUploadImg");
			request.setToken("123444444444444");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:登录接口测试
	 */
	public void testMemberLogin(){
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("loginName", "seller");
	        map.put("fappname", "优优");
	        map.put("fappcode", "1");
	        map.put("loginPassword", "test1234");
			map.put("finterfacename", "");
			map.put("finterfacecode", "");
			map.put("testflag", "1");
			map.put("fphoneos", "ios");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "2");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setMethod("memberLogin");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
//			HttpUtils.postForm(content, params)
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpclient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:注册接口测试
	 */
	public void testSignUp(){
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("loginName", "18664975981");
			map.put("loginPassword", "123456");
			map.put("zone", "86");
			map.put("appkey", "939ff286c56f");
			map.put("smsCode", "3587");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "5000");
			map.put("finterfacename", "5000");
			map.put("fphonemodel", "5000");
			map.put("fphoneos", "5000");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setMethod("personSignUp");
			request.setToken("123444444444444");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:短信发送接口测试
	 */
	public void testSendSmsCode(){
		try{
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("mobile", "18664975981");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			String content=new String(Base64.encode(reqStr.getBytes("Utf-8")));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:我的车型列表页面接口测试
	 */
	public void testMemberCarList(){
		try{
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("memberId", "62");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setMethod("memberCarList");
			request.setToken("123444444444444");
			request.setSessionid("41492F3B379AB82E93D94402ED10FEB1");
	    	String reqStr=JSONObject.toJSONString(request);
	    	System.out.println(reqStr);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:设为默认车型接口测试
	 */
	public void testSetDefaulCar(){
		try{
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("id", "123");
			map.put("isDefault", "1");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setMethod("setDefaulCar");
			request.setToken("123444441144444ww44");
			request.setSessionid("41492F3B379AB82E93D94402ED10FEB1");
	    	String reqStr=JSONObject.toJSONString(request);
	    	System.out.println(reqStr);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:保存车型接口测试
	 */
	public void testSaveMemberCar(){
		try{
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
	        map.put("carBrandId", "6045");
			map.put("carDeptId", "16411");
			map.put("carId", "40375");
			map.put("carYear", "2015");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setMethod("saveMemberCar");
			request.setToken("123444441144444ww44");
			request.setSessionid("41492F3B379AB82E93D94402ED10FEB1");
	    	String reqStr=JSONObject.toJSONString(request);
	    	System.out.println(reqStr);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:编辑车型接口测试
	 */
	public void testUpdateMemberCar(){
		String url ="http://localhost:8082/yoyoapi/membercar/updateMemberCar";
		Map<String,String> map =new HashMap<>();
		map.put("id", "111");
		map.put("carNickName", "测试侧新");
		map.put("carBrandId", "6045");
		map.put("carDeptId", "16411");
		map.put("carId", "38380");
		map.put("carYear", "2015");
		map.put("platform", "50000");
		map.put("sessionId", "31EA75B149F9B90B33129704B2D19438");
		try {
			String reqStr=JSONObject.toJSONString(map);
			String ret=HttpUtils.post(url, reqStr, 1000000);
			System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:删除我的车型接口测试
	 */
	public void testDeleteMemberCar(){
		String url ="http://localhost:8082/yoyoapi/membercar/deleteMemberCar";
		Map<String,String> map =new HashMap<>();
		map.put("id", "115");
		map.put("platform", "50000");
		map.put("sessionId", "31EA75B149F9B90B33129704B2D19438");
		try {
			String reqStr=JSONObject.toJSONString(map);
			String ret=HttpUtils.post(url, reqStr, 1000000);
			System.out.println(ret);
		} catch (Exception e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:我的默认车型接口测试
	 */
	public void testSelectMemberDefaultCar(){
		
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("deptId", "16411");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setMethod("findMemberDefaultCar");
			request.setToken("123444444444444");
			request.setSessionid("94A7A198F2239874F86446CBCF0B6893");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(Base64.encode(reqStr.getBytes("Utf-8")));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:查询汽车品牌接口测试
	 */
	public void testFindCarBrand() throws Exception{
		try {
		HttpPost httpPost = new HttpPost(url);
		RequestVO request=new RequestVO();
        Map<String,String> map =new HashMap<>();
		map.put("identifier", "999");
		map.put("fphoneostype", "5000");
		map.put("finterfacecode", "001001");
		map.put("finterfacename", "查询可预约医院列表");
		map.put("fphoneos", "iOS");
		map.put("fphonemodel", "");
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
		request.setMethod("findCarBrand");
		request.setToken("123444444444444");
    	String reqStr=JSONObject.toJSONString(request);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		String content=new String(Base64.encode(reqStr.getBytes("Utf-8")));
		nvps.add(new BasicNameValuePair("data", content));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse httpResponse = getHttpClient().execute(httpPost);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		StringBuffer stb = new StringBuffer();
		String line = null;
		while ((line = buffer.readLine()) != null) {
			stb.append(line);
		}
		buffer.close();
		String result = stb.toString();
		System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:查询汽车厂商以及车系接口测试
	 */
	public void testFindCarDept() throws Exception{
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("brandId", "6045");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setMethod("findCarDept");
			request.setToken("123433344444499944444");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:查询车型接口测试
	 */
	public void testFindCar(){
		try{
			HttpPost httpPost = new HttpPost(url);
		RequestVO request=new RequestVO();
        Map<String,String> map =new HashMap<>();
        map.put("fappcode", "1");
		map.put("finterfacename", "");
		map.put("finterfacecode", "");
		map.put("deptId", "16411");
		map.put("fphoneos", "ios");
		map.put("fphonemodel", "");
		map.put("fphoneostype", "2");
		request.setData(map);
		request.setMethod("findCar");
		request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
    	String reqStr=JSONObject.toJSONString(request);
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		String content=new String(Base64.encode(reqStr.getBytes("Utf-8")));
		nvps.add(new BasicNameValuePair("data", content));
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		HttpResponse httpResponse = getHttpClient().execute(httpPost);
		BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
		StringBuffer stb = new StringBuffer();
		String line = null;
		while ((line = buffer.readLine()) != null) {
			stb.append(line);
		}
		buffer.close();
		String result = stb.toString();
		System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
	} catch (Exception e) {
		// TODO Auto-generated catch block  
		e.printStackTrace();
	}
	}
	
	@Test
	/**
	 * @Description:查询车年份接口测试
	 */
	public void testFindCarYear() throws Exception{
		try {
			HttpPost httpPost = new HttpPost(url);
			RequestVO request=new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("deptId", "16411");
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setMethod("findCarYear");
			request.setToken("123444444444444");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = getHttpClient().execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:查询加载订单列表
	 */
	public void testLoadOrderList() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("status", "unconfirm");
			map.put("payStatus", "1");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("019FA8953F8836B7FB642ACE546E7B0D");
			request.setMethod("loadOrderList");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:查询订单详情
	 */
	public void testViewOrder() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("orderId", "999211508212432");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("019FA8953F8836B7FB642ACE546E7B0D");
			request.setMethod("viewOrder");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:取消订单
	 */
	public void testCanelOrder() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("orderId", "888101508248091");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("5DE5BAB2458D0B400A7FF489540C1FFC");
			request.setMethod("canelOrder");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:修改订单状态
	 */
	public void testUpdateStatus() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("orderId", "888101508249482");
			map.put("status", "unconfirm");
			map.put("payStatus", "1");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("5DE5BAB2458D0B400A7FF489540C1FFC");
			request.setMethod("updateStatus");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();
			}
	}
	
	@Test
	/**
	 * @Description:文件分块上传
	 */
	public void testUpload() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("orderId", "888101508249482");
			map.put("status", "unconfirm");
			map.put("payStatus", "1");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("5DE5BAB2458D0B400A7FF489540C1FFC");
			request.setMethod("updateStatus");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:退换货列表 
	 */
	public void testReshipList() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
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
			request.setSessionid("7B6C4C0882531E3DE9E92C9E134E47D3");
			request.setMethod("reshipList");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:退换货信息 
	 */
	public void testReturnProduct() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("isSafeguard", "2");
			map.put("returnId", "205");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("7B6C4C0882531E3DE9E92C9E134E47D3");
			request.setMethod("returnProduct");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:评论列表 
	 */
	public void testRefundsList() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			map.put("page", "1");
			map.put("rows", "20");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("7B6C4C0882531E3DE9E92C9E134E47D3");
			request.setMethod("commentsListData");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:回复评论
	 */
	public void testReplyComment() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			
			map.put("orderId", "999101508043246");
			map.put("forCommentId", "181");
			map.put("orderItemId", "617");
			map.put("productId", "955");
			map.put("toName", "buyer");
			map.put("toId", "350");
			map.put("comment", "一定会的，谢谢33333。");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("7B6C4C0882531E3DE9E92C9E134E47D3");
			request.setMethod("replyComment");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * @Description:评论显示设置
	 */
	public void testCommentDisplay() throws Exception{
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			RequestVO request = new RequestVO();
	        Map<String,String> map =new HashMap<>();
			map.put("fphoneostype", "5000");
			map.put("finterfacecode", "001001");
			map.put("finterfacename", "查询可预约医院列表");
			map.put("fphoneos", "iOS");
			map.put("fphonemodel", "");
			map.put("fphoneostype", "5000");
			
			map.put("commentId", "194");
			map.put("display", "1");
			request.setData(map);
			List listreq=new ArrayList();
			listreq.add("getCommpnInfo");
			listreq.add("2");
			listreq.add("0");
			listreq.add("1.185929");
			listreq.add("20150714094909");
			listreq.add("000000");
			request.setLastReqNote(listreq);
			request.setSessionid("7B6C4C0882531E3DE9E92C9E134E47D3");
			request.setMethod("commentDisplay");
			request.setToken("E7C9C892-6259-422E-8024-0CD080066CCD");
	    	String reqStr=JSONObject.toJSONString(request);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			String content=new String(BackAES.encrypt(reqStr, "123456789", 1));
			nvps.add(new BasicNameValuePair("data", content));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			BufferedReader buffer = new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer stb = new StringBuffer();
			String line = null;
			while ((line = buffer.readLine()) != null) {
				stb.append(line);
			}
			buffer.close();
			String result = stb.toString();
			System.out.println("result=" + new String(Base64.decode(result),"utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
