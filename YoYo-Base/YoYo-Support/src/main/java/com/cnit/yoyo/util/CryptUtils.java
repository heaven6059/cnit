/*
 * 文 件 名 : CryptUtils.java 版 权 : Ltd. Copyright (c) 2013 深圳市商巢互联网软件有限公司,All rights reserved 描 述 : &lt;描述&gt; 创建人 : 彭彩云 创建时间: 2015-1-22 下午4:08:31
 */
package com.cnit.yoyo.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;


/**
 * 加密解密工具类
 * 
 * @author 彭彩云
 * @version [版本号, 2015-1-22 下午4:08:31]
 */
public class CryptUtils {
	private static final String HMAC_SHA1 = "HmacSHA1";
	public static final String PRIVATE_KEY = "8e4679_CNIT_TAOPING_API_9c694e6";

	/**
	 * 加密字符串
	 * 
	 * @param str
	 * @param privatekey
	 * @return [参数说明]
	 * @return String
	 * @exception throws [违例类型] [违例说明]
	 */
	public static String hmacSHA1Encrypt(String data, String key) {
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
		Mac mac;
		StringBuilder sb = new StringBuilder();
		try {
			mac = Mac.getInstance(HMAC_SHA1);
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(data.getBytes());
			for (byte b : rawHmac) {
				sb.append(byteToHexString(b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 将字节转换成16进制
	 * 
	 * @param ib
	 * @return [参数说明]
	 * @return String
	 * @exception throws [违例类型] [违例说明]
	 */
	private static String byteToHexString(byte ib) {
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		char[] ob = new char[2];
		ob[0] = Digit[(ib >>> 4) & 0X0f];
		ob[1] = Digit[ib & 0X0F];
		String s = new String(ob);
		return s;
	}

	public static void main(String[] args) {
		// 发送短信
		// http://10.10.10.31:8081/apps/pub/regUserController/sendSMS?appId=a53e5823d22a10f3cnit&key=dad2d06750498517fed9f3853c296ae80b30e9a3&param2=eyJ1c2VyTW9iaWxlIjoxMzc1MTA3ODQ2Mn0=
		
		  //String appId = "a53e5823d22a10f3cnit"; String json = "{\"userMobile\":13751078462}"; String param2 = Base64.encodeBase64String(json.getBytes());
		  //System.out.println("param2:" + param2); System.out.println("key:" + CryptUtils.hmacSHA1Encrypt(param2 + appId, PRIVATE_KEY));
		

		// 登陆
//		 String appId = "a53e5823d22a10f3cnit";
//		 String json = "{\"userName\":\"admin\",\"password\":\"123456\",\"mac\":\"EBNN-DDVV-MOOO-D5DD\"}";
//		 String param2 = Base64.encodeBase64String(json.getBytes());
//		 String key = CryptUtils.hmacSHA1Encrypt(param2 + appId, PRIVATE_KEY);
//		 String loginUrl = "http://10.10.10.31:8081/apps/pub/regUserController/appLogin?appId=a53e5823d22a10f3cnit&key=" + key + "&param2=" + param2;
//		 System.out.println(loginUrl);

		// 修改头像
//		String appId = "a53e5823d22a10f3cnit";
//		String param1 = Base64.encodeBase64String("{\"sid\":6,\"uid\":-1}".getBytes());
//		String json = "{\"userID\":-1,\"profilePhoto\":\"profilePhoto.jpg\"}";
//		String param2 = Base64.encodeBase64String(json.getBytes());
//		String key = CryptUtils.hmacSHA1Encrypt(param1 + param2 + appId, PRIVATE_KEY);
//		String loginUrl = "http://10.10.10.31:8081/apps/apps/userController/uploadIcon?appId=a53e5823d22a10f3cnit&key=" + key + "&param1=" + param1
//				+ "&param2=" + param2;
//		System.out.println(loginUrl);
		String param1="eyJ1aWQiOiI1ODYwMSIsInNpZCI6IjYifQ==";
		String param2="eyJyZWFsTmFtZSI6Iua1i+ivlSIsInVzZXJJRCI6IjU4NjAxIiwidXNlckdlbmRlciI6Ik0iLCJiaXJ0aGRheSI6IjE5OTEtMDctMTMiLCJwcm9maWxlUGhvdG8iOiI2ZjRjNzc3ZTc4MjYzNWMyNWM2ZGJkODRkMDk4YWYwMy5wbmcifQ==";
		String appId = "YTUzZTU4MjNkMjJhMTBmM2NuaXQ=";
		String enCrypt = CryptUtils.hmacSHA1Encrypt(param1 + param2 + appId, PRIVATE_KEY);
		System.out.println(enCrypt);
	}
}
