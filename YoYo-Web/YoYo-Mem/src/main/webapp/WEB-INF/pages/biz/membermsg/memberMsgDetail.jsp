<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>站内信</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain.css?v=${versionVal}" />
<link rel="stylesheet" href="${path}/resources/styles/reship/reship.css?v=${versionVal}" type="text/css">
</head>
<body>
	<div class="shop_manager_right ">
		<div class="title ">站内信</div>
		<form  id='formId' class="section">
			<div id="gEditor-Body">
				<div class="FormWrap" style="background: none">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="liststyle data width1" style="border: none">

						<tr>
							<th>主题：</th>
							<td>${memberMsg.subject}</td>
						</tr>
						<tr>
							<th>发信者：</th>
							<td>${memberMsg.fromUname}</td>
						</tr>
						<tr>
							<th>收信者：</th>
							<td>${memberMsg.toUname}</td>
						</tr>
						<tr>
							<th>发送时间：</th>
							<td><fmt:formatDate value="${memberMsg.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
						<tr>
							<th>到达时间：</th>
							<td><fmt:formatDate value="${memberMsg.toTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
						</tr>
						<tr>
							<th>内容：</th>
							<td style="word-break:break-all">${memberMsg.content }</td>
						</tr>
					</table>
				</div>		
			</div>
		</form>
	</div> 
</body>
</html>