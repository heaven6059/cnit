package com.cnit.yoyo.util.aes;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


/**
 * AES加密解密算法
 * @Author:yangyi  
 * @Date:2015年7月21日  
 * @Version:1.0.0
 */
public class BackAES {

	/**
	 * 加密解决算法
	 */
	// private static String sKey = "1234567890123456";
	private static String ivParameter = "1234567890123456";//默认偏移

	private static String WAYS = "AES";
	private static String MODE = "";
	private static boolean isPwd = false;
	private static String ModeCode = "PKCS5Padding";
	private static int type = 0;// 默认
	
	private static int pwdLenght=16;
	private static  String val="0";

	public static String selectMod(int type) {
		// ECB("ECB", "0"), CBC("CBC", "1"), CFB("CFB", "2"), OFB("OFB", "3");
		switch (type) {
		case 0:
			isPwd = false;
			MODE = WAYS + "/" + AESType.ECB.key() + "/" + ModeCode;

			break;
		case 1:
			isPwd = true;
			MODE = WAYS + "/" + AESType.CBC.key() + "/" + ModeCode;
			break;
		case 2:
			isPwd = true;
			MODE = WAYS + "/" + AESType.CFB.key() + "/" + ModeCode;
			break;
		case 3:
			isPwd = true;
			MODE = WAYS + "/" + AESType.OFB.key() + "/" + ModeCode;
			break;

		}

		return MODE;

	}
	
	/**
	 * Generates a SecretKeySpec for given password
	 *
	 * @param password
	 * @return SecretKeySpec
	 * @throws UnsupportedEncodingException
	 */
	private static SecretKeySpec getKey(String password) throws UnsupportedEncodingException {
	    
	    // You can change it to 128 if you wish
	    int keyLength = 256;
	    byte[] keyBytes = new byte[keyLength / 8];
	    // explicitly fill with zeros
	    Arrays.fill(keyBytes, (byte) 0x0);
	    
	    // if password is shorter then key length, it will be zero-padded
	    // to key length
	    byte[] passwordBytes = password.getBytes("utf-8");
	    int length = passwordBytes.length < keyBytes.length ? passwordBytes.length : keyBytes.length;
	    System.arraycopy(passwordBytes, 0, keyBytes, 0, length);
	    SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
	    return key;
	}
	
/********************************方法一，密匙必须为16位**********************************************/
	// 加密
	public static byte[] encrypt(String sSrc, String sKey, int type)
			throws Exception {
		SecretKeySpec skeySpec = getKey(sKey);
		// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
        final byte[] iv = new byte[16];
        Arrays.fill(iv, (byte) 0x00);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
		//实例化密钥生成器  
//	    Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(selectMod(type));
		byte[] raw = sKey.getBytes("utf-8");
		// ECB 不用密码
		if(isPwd == false){
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
		}else{
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);
		}
		byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));
		return AESBase64.encode(encrypted);// 此处使用BASE64做转码。
	}

	// 解密
	public static String decrypt(String sSrc, String sKey, int type)
			throws Exception {
		SecretKeySpec skeySpec = getKey(sKey);
		try {
			byte[] raw = sKey.getBytes("ASCII");
			// IMPORTANT TO GET SAME RESULTS ON iOS and ANDROID
	        final byte[] iv = new byte[16];
	        Arrays.fill(iv, (byte) 0x00);
	        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
//			SecretKeySpec skeySpec = new SecretKeySpec(raw, WAYS);
			Cipher cipher = Cipher.getInstance(selectMod(type));
//			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
			byte[] encrypted1 = AESBase64.decode(sSrc.getBytes());// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, "utf-8");
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}
		
	//key 
	public static String toMakekey(String str,int strLength,String val){
		
		int strLen=str.length();
		if (strLen<strLength) {
			while (strLen<strLength) {
				StringBuffer  buffer=new StringBuffer();
				buffer.append(str).append(val);
				str=buffer.toString();
				strLen=str.length();
			}
		}
		return str;
	}

	
/***********************************第二种***********************************************/
	
	
	public static byte[] newencrypt(String content, String password) {
        try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(256, new SecureRandom(password.getBytes()));
                SecretKey secretKey = kgen.generateKey();
                byte[] enCodeFormat = secretKey.getEncoded();
                SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                Cipher cipher = Cipher.getInstance("AES");// 创建AES加密编码器
                byte[] byteContent = content.getBytes("UTF-8");
                cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化AES加密
                byte[] result = cipher.doFinal(byteContent);
                return result; // AES加密结果
        } catch (Exception e) {
                e.printStackTrace();
        }
        return null;
}
	/*
	  * @param content  待解密内容,格式为byte数组
      * @param password AES解密使用的密钥
      * @return
      */
     public static byte[] newdecrypt(byte[] content, String password) {
             try {
                      KeyGenerator kgen = KeyGenerator.getInstance("AES");
                      kgen.init(256, new SecureRandom(password.getBytes()));
                      SecretKey secretKey = kgen.generateKey();
                      byte[] enCodeFormat = secretKey.getEncoded();
                      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                      Cipher cipher = Cipher.getInstance("AES");// 创建AES加密编码器
                     cipher.init(Cipher.DECRYPT_MODE, key);// 初始化AES加密
                     byte[] result = cipher.doFinal(content);
                     return result; // 得到AES解密结果
             } catch (Exception e) {
                     e.printStackTrace();
             }
             return null;
     }
     
     /**将二进制转换成16进制
      * @param buf
      * @return
      */
     public static String parseByte2HexStr(byte buf[]) {
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < buf.length; i++) {
                     String hex = Integer.toHexString(buf[i] & 0xFF);
                     if (hex.length() == 1) {
                             hex = '0' + hex;
                     }
                     sb.append(hex.toUpperCase());
             }
             return sb.toString();
     }
     /**java将16进制转换为二进制
      * @param hexStr
      * @return
      */
     public static byte[] parseHexStr2Byte(String hexStr) {
             if (hexStr.length() < 1)
                     return null;
             byte[] result = new byte[hexStr.length()/2];
             for (int i = 0;i< hexStr.length()/2; i++) {
                     int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
                     int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
                     result[i] = (byte) (high * 16 + low);
             }
             return result;
     }
	
}