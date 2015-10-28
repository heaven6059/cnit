package com.cnit.yoyo.util.sms.spi;

import net.sf.json.JSONObject;

import com.cnit.yoyo.util.sms.utils.MobClient;



/**
 * 服务端发起验证请求验证移动端(手机)发送的短信
 * @Author:yangyi  
 * @Date:2015年7月30日  
 * @Version:1.0.0
 */
public class SmsVerifyKit {
	
	private String appkey;
	private String phone ;
	private String zone ;
	private String code ;

	/**
	 * 
	 * @param appkey 应用KEY
	 * @param phone 电话号码 xxxxxxxxx
	 * @param zone 区号 86
	 * @param code 验证码 xx
	 */
	public SmsVerifyKit(String appkey, String phone, String zone, String code) {
		super();
		this.appkey = appkey;
		this.phone = phone;
		this.zone = zone;
		this.code = code;
	}

	/**
	 * 服务端发起验证请求验证移动端(手机)发送的短信
	 * @return
	 * @throws Exception
	 */
	public  String go() throws Exception{
		
		String address = "https://api.sms.mob.com/sms/verify";
		MobClient client = null;
		try {
			client = new MobClient(address);
			client.addParam("appkey", appkey).addParam("phone", phone)
					.addParam("zone", zone).addParam("code", code);
			client.addRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
			client.addRequestProperty("Accept", "application/json");
			String result = client.post();
			return result;
		} finally {
			client.release();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		String retStr=new SmsVerifyKit("939ff286c56f", "18620390082", "86", "3587").go();
		System.out.println(JSONObject.fromObject(retStr));
		
	}

}
