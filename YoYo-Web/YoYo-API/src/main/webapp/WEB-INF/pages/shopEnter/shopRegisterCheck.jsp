<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城-商家资料审核</title>
<link type="text/css" rel="stylesheet" href="${path}/resources/styles/shopEnter/shopRegister.css">

<script type="text/javascript">
	$(function(){
		$(".shop_reg_data").click(function(){
			$(this).css({"background-color":"#FFFAE1"}).siblings().css("background-color", "#F3F3F3");;
		});
	});
	
</script>
</head>
<body>
	<div class="shop_reg_main" >
		<div class="shop_reg_center"><img src="${path}/resources/images/shop/shop_reg_check.png" />	</div>
		<div class="shop_reg_check_info">
			您申请店铺的资料提交成功，请邮寄纸质资料至XXX省XXX市XXX收。
			邮寄资料请参考：《招商标准》
			
		</div>
		<div style="margin-left:380px;font-size:14px;">你现在可以：<a href="${portalPath}/index">到处逛逛</a></div>
	</div>
</body>
</html>