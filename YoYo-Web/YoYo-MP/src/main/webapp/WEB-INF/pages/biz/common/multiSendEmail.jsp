<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>群发邮件</title>
<script type="text/javascript" src="${path}/resources/scripts/cleditor/jquery.cleditor.min.js"></script>
<link type="text/css" href="${path}/resources/scripts/cleditor/jquery.cleditor.css" rel="stylesheet" />
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript">
	$(function(){
		$("#mailContent").cleditor();
	});
</script>
</head>
<body>
	<form action="multiSendEmail">
		<table>
			<tr>
				<td><label>收件人：</label></td>
				<td><input /></td>
			</tr>
			<tr>
				<td colspan="2"><textarea id="mailContent"></textarea></td>
			</tr>
		</table>
	</form>
</body>
</html>