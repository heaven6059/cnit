<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5, maximum-scale=2.0, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">

<!-- <meta name="viewport"  content="width=640,target-densitydpi=device-dpi,user-scalable=no"> -->
<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> -->

<!-- jqueryMobile -->
<link rel="stylesheet" href="${path}/resources/styles/mobile/css/themes/default/jquery.mobile-1.4.2.min.css" />
<link rel="stylesheet" href="${path}/resources/styles/mobile/css/common.css" />

<!-- jqueryMobile -->

<script type="text/javascript" src="${path}/resources/scripts/mobile/jquery-mobile/jquery.js" ></script>
<script type="text/javascript" src="${path}/resources/scripts/mobile/jquery/jquery.imgareaselect.pack.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/mobile/jquery-mobile/jquery.mobile-1.4.2.min.js"  charset="utf-8"></script>
<script type="text/javascript" src="${path}/resources/scripts/mobile/jquery-mobile/slip-min.js"  charset="utf-8"></script>
<script type="text/javascript" src="${path}/resources/scripts/mobile/jquery-mobile/ai.js"  charset="utf-8"></script>
<!-- layer.js -->
<%-- <script type="text/javascript" src="${path}/resources/scripts/mobile/layer/layer.min.js" charset="utf-8"></script> --%>

<%-- <script type="text/javascript">
window.onscroll = function() {
	//滚动条在最底端的时候 
	if (document.documentElement.scrollHeight == (document.documentElement.scrollTop | document.body.scrollTop)
			+ document.documentElement.clientHeight) {
		if (typeof scrollToBottom == "function") {
			scrollToBottom();
		}
	}
};
</script> --%>
   
  
  
  
  
  
  
  
  