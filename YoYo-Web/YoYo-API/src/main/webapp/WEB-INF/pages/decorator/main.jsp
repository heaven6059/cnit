<%@ page contentType="text/html; charset=utf-8"%>
<%@page import="com.cnit.yoyo.util.Configuration" %>  
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	session.setAttribute("imagePath", Configuration.getInstance().getConfigValue("images.url"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><sitemesh:write property='title' /></title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/base.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/common.css" />
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/placeholder/jquery.enplaceholder.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/layer1.9.3/layer.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/easyui/themes/bootstrap/easyui.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/easyui/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/scripts/bxslider/jquery.bxslider.css" rel="stylesheet" />

<script type="text/javascript">
	var _path = "${path}";
	$(function(){
		// 通过value模拟placeholder
		$(':text').placeholder();
		// 通过插入元素模拟placeholder
		$(':password').placeholder({
			isUseSpan : true
		});
	});
</script>
<sitemesh:write property='head' />
</head>
<body>

<!--右侧便捷功能-->
<div class="mui">
    <ul>
        <li><a href="javascript:;"> <span class="s01"></span><p>在线咨询</p></a></li>
        <li><a href="javascript:;"> <span class="s02"></span><p>在线咨询</p></a></li>
        <li><a href="javascript:;"> <span class="s03"></span><p>在线咨询</p></a></li>
        <li class="last"><a class="clearfix" href="javascript:;"><span class="s04 last fl"></span><p class="fr">返回<br />顶部</p></a></li>
    </ul>
</div>
<!--右侧便捷功能end-->

	<div class="header">
		<jsp:include page="../index/head.jsp" />
		<jsp:include page="../index/menu.jsp" />
	</div>
	<sitemesh:write property='body' />
	<div class="footer">
		<jsp:include page="./footer.jsp" />
	</div>
</body>
</html>