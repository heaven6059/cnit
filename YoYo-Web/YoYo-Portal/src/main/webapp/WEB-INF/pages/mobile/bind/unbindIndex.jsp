<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	response.setHeader("Access-Control-Allow-Origin", "*"); 
	String path = request.getContextPath();
	request.setAttribute("path", path);
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	request.setAttribute("basePath", basePath);
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>解除绑定</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" href="${path}/resources/styles/mobile/css/common.css" />
<script type="text/javascript" src="${path}/resources/scripts/mobile/jquery-mobile/jquery.js" ></script>
</head>
<body>
<script type="text/javascript">
	function unbindEvent(){
		var sure = confirm("确定要解除绑定吗？");
		if(sure){
			$.ajax({
				type: "POST",
				url: "${path}/weixinUnbind",
				data: "",
				async:false,
				success: function(data){
					if(data.success){
						$("#closeBtn").click();
						WeixinJSBridge.invoke('closeWindow',{},function(res){
					        //alert(res.err_msg);
					    });
					}else{
						alert(data.msg);
						return false;
					}
				}
			});
		}else{
			WeixinJSBridge.invoke('closeWindow',{},function(res){
		        //alert(res.err_msg);
		    });
		}
	}
	
	if(typeof WeixinJSBridge === "undefined"){
		document.addEventListener('WeixinJSBridgeReady', unbindEvent, false);
	}else{
		unbindEvent();
	}
</script>
	
</body>

</html>