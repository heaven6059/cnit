
<%
/* *
 *功能：即时到账交易接口接入页
 *版本：3.3
 *日期：2012-08-14
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。

 *************************注意*****************
 *如果您在接口集成过程中遇到问题，可以按照下面的途径来解决
 *1、商户服务中心（https://b.alipay.com/support/helperApply.htm?action=consultationApply），提交申请集成协助，我们会有专业的技术工程师主动联系您协助解决
 *2、商户帮助中心（http://help.alipay.com/support/232511-16307/0-16307.htm?sh=Y&info_type=9）
 *3、支付宝论坛（http://club.alipay.com/read-htm-tid-8681712.html）
 *如果不想使用扩展功能请把扩展功能参数赋空值。
 **********************************************
 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.cnit.yoyo.util.Configuration"%>
<%@ page import="com.cnit.yoyo.paycenter.util.*"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>支付宝即时到账交易接口</title>
</head>
<%
	////////////////////////////////////请求参数//////////////////////////////////////

		//商户订单号
		String out_trade_no = request.getParameter("WIDout_trade_no");
		//订单名称
		String subject = request.getParameter("WIDsubject");
		//付款金额
		String total_fee = request.getParameter("WIDtotal_fee");
		//订单描述		
		String body = request.getParameter("WIDbody");
		//商品展示地址
		String show_url = request.getParameter("WIDshow_url");

		//防钓鱼时间戳
		String anti_phishing_key = "";
		//若要使用请调用类文件submit中的query_timestamp函数
		//客户端的IP地址
		String exter_invoke_ip = "";
		//非局域网的外网IP地址，如：221.0.0.1
		
		//默认支付方式
		String paymethod = "bankPay";
		//默认网银必填，银行简码请参考接口技术文档
		String defaultbank = request.getParameter("WIDdefaultbank");
	   
		//把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner",Configuration.getInstance().getConfigValue("ali_MerId"));
        sParaTemp.put("seller_email", Configuration.getInstance().getConfigValue("ali_email"));
        sParaTemp.put("_input_charset",  Configuration.getInstance().getConfigValue("input_charset"));
		sParaTemp.put("payment_type", "1");
		sParaTemp.put("notify_url", Configuration.getInstance().getConfigValue("ali_notifyUrl"));
		sParaTemp.put("return_url", Configuration.getInstance().getConfigValue("ali_returnUrl"));
		sParaTemp.put("out_trade_no", out_trade_no);
		sParaTemp.put("subject", subject);
		sParaTemp.put("total_fee", total_fee);
		sParaTemp.put("body", body);
		if(null!=defaultbank && defaultbank!=""){
		 	sParaTemp.put("paymethod", paymethod);
			sParaTemp.put("defaultbank", defaultbank);
		 }
		sParaTemp.put("show_url", show_url);
		sParaTemp.put("anti_phishing_key", anti_phishing_key);
		sParaTemp.put("exter_invoke_ip", exter_invoke_ip);
		
		//建立请求
		String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
		out.println(sHtmlText);
%>
<body>
</body>
</html>
