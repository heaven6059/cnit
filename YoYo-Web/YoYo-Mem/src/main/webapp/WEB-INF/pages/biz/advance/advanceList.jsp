<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的预存款</title>
<%@include file="/resources/include/head.jsp" %>
</head>
<body>
	<div class="member-main">
		<div>
			<div class="title title2">我的预存款</div>

			<div>
				<p class="admin-title textright">
				  <span class="flt">
					您当前的预存款总额为：<em class="font-orange fontbold font14px" id = "advance"></em>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  </span>
				</p>
				<p class="admin-title textleft"  >
				  <span class="flt">
					<font color="red">预存款充值记录</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					 <span class="admin-title textright"  id = "pointFreeze"><img src="${path}/resources/images/index/down-icon.gif">
					 <a href="javascript:void(0);" onclick="downloadAdvanceRecords()" ><font color="red"  >下载交易记录</font></a></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				  </span>				
				</p>				  
				<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId">
					<tbody>
						<tr>
							<th>时间</th>
							<th>事件</th>
							<th>存入金额</th>
							<th>支出金额</th>
							<th>当前余额</th>
						</tr>
					</tbody>
					<tbody>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<script type="text/javascript" >
    function  downloadAdvanceRecords(){
	}
</script>		
<script type="text/javascript" src="${path}/resources/scripts/biz/advance/advanceList.js?v=${versionVal}"></script>	
</body>
</html>