package com.cnit.yoyo.weixin.tencent;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import com.cnit.yoyo.weixin.tencent.pojo.AccessToken;
import com.cnit.yoyo.weixin.tencent.pojo.Button;
import com.cnit.yoyo.weixin.tencent.pojo.ClickButton;
import com.cnit.yoyo.weixin.tencent.pojo.Menu;
import com.cnit.yoyo.weixin.tencent.pojo.ViewButton;

public class WeixinUtil {
	public static Logger logger = LoggerFactory.getLogger(WeixinUtil.class);
	
	//每次切换回我的测试公众号需要做如下更改：
	//WeixinUtil.java中的APPID、APPSECRET、MY_FAPPNAME;
	//WeixinUtil.java中的sendTemplateMsgForReg方法中的template_id与url都需要更改
	//IndexController.java中引用的WeixinUtil.java中的MY_FAPPNAME(TAOPING_KEY则不用更改)
	//AccessTokenTimer.java中改成accountService.findWeixinAccountByName(WeixinUtil.TAOPING_FAPPNAME),需把test改成taoping,如果后期有多个公众号再置空
	//数据库配置文件init.properties需要更改
	//rpcProxy-taoping.xml更改为淘屏正式接口的内网地址，端口默认80不用带
	
	public static final String TOKEN = "d9239a25ca6a4b6493467601e6b1d";// 与接口配置信息中的Token要一致
	public static final String MY_FAPPNAME = "test";
//	public static final String APPID = "wx17a6f0281fd3eafc";//我的测试公众号APPID
//	public static final String APPSECRET = "501054658d6c239cdd4e2ee4943463a2";//我的测试公众号APPSECRET
	public static final String FREENUMBERS = "18319992693,13632756395,18312477657";//这些手机号码不用验证
	
	public static final String TAOPING_KEY = "cnit-tpw";//淘屏公众号的微信号
//	public static final String TAOPING_FAPPNAME = "taoping";//自定义的淘屏公众号名称，获取accesstoken时用
	private static final String APPID = "wx17a6f0281fd3eafc";//淘屏公众号APPID
	private static final String APPSECRET = "501054658d6c239cdd4e2ee4943463a2";//淘屏公众号APPSECRET
	private static final String CODE_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";	
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String SEND_TEMPLATE_MSG_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	
	//创建菜单url暂时只保留我本地测试用
	private static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	//session
	public static final String SESSION_TP_OPEN_ID = "OPEN_ID";
	public static final String SESSION_TP_USERID = "userID";
	public static final String SESSION_TP_ACCOUNTID="accountID";
	public static final String SESSION_TP_SID = "sid";
	public static final String SESSION_TP_USERMOBILE = "userMobile";
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
//			log.error("Weixin server connection timed out.");
		} catch (Exception e) {
//			log.error("https request error:{}", e);
		}
		return jsonObject;
	}
	
	/**
	 * 根据code获取用户openid
	 * @param code
	 * @return
	 */
	public static JSONObject getAccessTokenOpenIdByCode(String code){
		String url = CODE_ACCESS_TOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);		
		JSONObject jsonObj = httpRequest(url, HttpMethod.POST.toString(), null);
		System.out.println("getAccessTokenOpenIdByCode-----:"+jsonObj);
		return jsonObj;
	}
	
	/**
	 * 注册成功后发送模板消息
	 * @param token
	 * @param fphoneNumber
	 */
	public static void sendTemplateMsgForReg(String token, String fphoneNumber, String openid){
		String url = SEND_TEMPLATE_MSG_URL.replace("ACCESS_TOKEN", token);
		StringBuffer postData = new StringBuffer();
		postData.append("{");
		postData.append("'first':{'value':'恭喜您成功注册优优车！','color': '#000'},");
		postData.append("'keyword1':{'value': '"+fphoneNumber+"','color': '#000'},");
		postData.append("'keyword2':{'value': '您已经是优优车的会员啦~优惠券已经放入您的账号中！快来优优车吧！','color': '#000'},");
		postData.append("'remark': {'value': '','color': '#000'}");
		postData.append("}");
		JSONObject paramObj = JSONObject.fromObject(postData.toString());
		logger.info("sendTemplateMsg paramObj------:{}",paramObj);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("template_id", "RwBIGBrecOLvq908YXIpIu2KMSrRYWX4HWa1aGxOdCk");//templateId
		data.put("url", "http://taoping.tunnel.mobi/TaoPingWap/description");
		data.put("touser", openid);//openId
//		data.put("template_id", "J7Tv7ODrugdMJCw7038zMGvqt1DUQXHwaQ6i7EJuHFA");//taoping templateId
//		data.put("url", "http://xmpp.cnitcloud.com/TaoPingWap/description");
		data.put("topcolor", "#FF0000");
		data.put("data", paramObj);
		
		JSONObject resultObj = httpRequest(url, HttpMethod.POST.toString(), JSONObject.fromObject(data).toString());
		logger.info("------sendTemplateMsg resultObj------:{}",resultObj);
	}
	
	/**
	 * 获取access_token
	 * @return
	 */
	public static AccessToken getAccessToken(String appid, String appsecret){
		AccessToken accessToken = null;
		String url = ACCESS_TOKEN_URL.replace("APPID", appid).replace("APPSECRET", appsecret);
		JSONObject jsonObject = httpRequest(url, HttpMethod.GET.toString(), null);
		if(jsonObject!=null){
			accessToken = new AccessToken();
			accessToken.setToken(jsonObject.getString("access_token"));
			accessToken.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		return accessToken;
	}
	
	
	
	
	
	//--------------------------------测试-------------------------------------
	/**
	 * 初始化菜单
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu = new Menu();
		ClickButton btn1 = new ClickButton();
		btn1.setType("click");
		btn1.setName("狂欢聚\"惠\"城");
		btn1.setKey("1");
		
		ViewButton btn21 = new ViewButton();
		btn21.setType("view");
		btn21.setName("我的红包");
		btn21.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx497a6a5bc6f7a406&redirect_uri=http://taoping.tunnel.mobi/TaoPingWap/mainController/queryMyRedPacket&response_type=code&scope=snsapi_base&state=wxgzh#wechat_redirect");
		
		ViewButton btn22 = new ViewButton();
		btn22.setType("view");
		btn22.setName("领取红包");
		btn22.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx497a6a5bc6f7a406&redirect_uri=http://taoping.tunnel.mobi/TaoPingWap/getRedPacket&response_type=code&scope=snsapi_base&state=wxgzh#wechat_redirect");
						
		ViewButton btn23 = new ViewButton();
		btn23.setType("view");
		btn23.setName("解除绑定");
		btn23.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx497a6a5bc6f7a406&redirect_uri=http://taoping.tunnel.mobi/TaoPingWap/unbindIndex&response_type=code&scope=snsapi_base&state=wxgzh#wechat_redirect");
		
		Button btn2 = new Button();
		btn2.setName("个人中心");
		btn2.setSub_button(new Button[]{btn21, btn22, btn23});
		
		ViewButton btn31 = new ViewButton();
		btn31.setType("view");
		btn31.setName("下载APP");
//		btn31.setUrl("http://taoping.tunnel.mobi/TaoPingWap/test/");
		btn31.setUrl("http://xmpp.cnitcloud.com");
//		btn31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx497a6a5bc6f7a406&redirect_uri=http://taoping.tunnel.mobi/TaoPingWap/test&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
		
		Button btn3 = new Button();
		btn3.setName("更多");
		btn3.setSub_button(new Button[]{btn31});
		
		menu.setButton(new Button[]{btn1, btn2, btn3});
		return menu;
	}
	
	/**
	 * 创建菜单
	 * @param menu
	 * @param token
	 * @return
	 */
	public static int createMenu(String menu, String token){
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = httpRequest(url, HttpMethod.POST.toString(), menu);
		System.out.println("createMenu--------:\n"+jsonObject);
		return jsonObject.getInt("errcode");
	}
	
	
	
}
