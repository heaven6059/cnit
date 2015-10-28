<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("t", System.currentTimeMillis());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<title>车型对比</title>
<!--css文件-->
<script type="text/javascript" src="${path}/resources/scripts/biz/parameter/goodsCompare.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/base.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/common.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/parameter.css" />
<!--js文件-->
</head>
<body>
<!--面包屑导航-->
<div class="root_nav">
	<div class="root_navin">
    	<span><a href="javascript:;">首页</a></span>&nbsp;>&nbsp;
    	<span><a href="javascript:;">配件对比</a></span>
    </div>
</div>
<script type="text/javascript">
var info=${compareResult};
var goods=${goods};
</script>
<div class="row clearfix" id="content">
    <div class="column fr">
    	<div class="title">
        	<div class="title-content" style=" padding-top: 0;">
            	<!--车型图片-->
          	</div>
        </div>
    </div>
</div>
</body>
</html>
