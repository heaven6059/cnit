package com.cnit.yoyo.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cnit.yoyo.weixin.tencent.MessageUtil;
import com.cnit.yoyo.weixin.tencent.SHA1;
import com.cnit.yoyo.weixin.tencent.WeixinUtil;
import com.cnit.yoyo.weixin.tencent.pojo.TextMsg;

@Controller
@RequestMapping("/tencent")
public class TencentController {

	/**
	 * 确认请求来自微信服务器
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入TencentController---doGet方法");
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		String[] tmpArr = new String[]{WeixinUtil.TOKEN, timestamp, nonce};
		Arrays.sort(tmpArr);
		String bigStr = tmpArr[0] + tmpArr[1] + tmpArr[2];
		//SHA1加密
		String digest = new SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase();
		if(digest.equals(signature)){
			out.print(echostr);
		}
		out.close();
	}
	
	/**
	 * 处理微信服务器发来的消息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(method = RequestMethod.POST)
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入TencentController---doPost方法");
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//淘屏公众号为编辑模式，测试用，暂不处理
		PrintWriter out = response.getWriter();
		try {
			Map<String,String> map = MessageUtil.xmlToMap(request);
			String toUserName = map.get("ToUserName");
			String fromUserName = map.get("FromUserName");
			String msgType = map.get("MsgType");
			String respMessage = null;
			if(MessageUtil.RESP_MESSAGE_TYPE_TEXT.equals(msgType)){
				String content = map.get("Content");
				if("1".equals(content)){
	 				TextMsg textMsg = new TextMsg();
	 				textMsg.setToUserName(fromUserName);
	 				textMsg.setFromUserName(toUserName);
	 				textMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	 				textMsg.setContent("链接<a href=\"http://health.cnitcloud.com/index\">CNITCLOUD</a>");
//	 				textMsg.setContent("美女！<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx497a6a5bc6f7a406&redirect_uri=http://zxx.tunnel.mobi/myweixin/servlet/TestServlet&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">领取红包</a>");
	 				textMsg.setCreateTime(new Date().getTime());
					respMessage = MessageUtil.textMsgToXml(textMsg);
	 			}
			}else if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
				String event = map.get("Event");
				if(event.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)){//订阅
					TextMsg textMsg = new TextMsg();
	 				textMsg.setToUserName(fromUserName);
	 				textMsg.setFromUserName(toUserName);
	 				textMsg.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	 				textMsg.setContent("欢迎关注淘屏网！请<a href=\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx497a6a5bc6f7a406&redirect_uri=http://taoping.tunnel.mobi/TaoPingWap/getRedPacket&response_type=code&scope=snsapi_base&state=1#wechat_redirect\">领取红包</a>");
	 				textMsg.setCreateTime(new Date().getTime());
					respMessage = MessageUtil.textMsgToXml(textMsg);
				}
			}
			System.out.println("responseMessage-----:\n"+respMessage);
	 		out.print(respMessage);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
	
}
