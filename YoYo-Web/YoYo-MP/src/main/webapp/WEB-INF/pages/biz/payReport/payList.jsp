<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>对账报表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/payReport/payReport.js?v=${versionVal}"></script>
</head>
<body class="easyui-layout" fit="true">
<form method="post" id="searchForm">
	<div style="padding:10px 10px 10px 20px">
		<table width="1500px" border="0"  >
			<tr height="40px">
				<td align="right"><span>订单号：</span></td>
				<td><input id="orderId" name="orderId" type="text" class="easyui-textbox"/></td>
				<td align="right"><span>买家：</span></td>
				<td><input id="name" name="name" type="text" class="easyui-textbox"/></td>
				<td align="right"><span>买家支付账号：</span></td>
				<td><input id="buyerEmail" name="buyerEmail" type="text" class="easyui-textbox"/></td>
				<td align="right"><span>卖家：</span></td>
				<td><input id="shopName" name="shopName" type="text" class="easyui-textbox"/></td>
				<td align="right"><span>卖家支付账号：</span></td>
				<td><input id="sellerEmail" name="sellerEmail" type="text" class="easyui-textbox"/></td>
				<td align="right"><span>支付交易号：</span></td>
				<td><input id="tradeNo" name="tradeNo" type="text" class="easyui-textbox"/></td>
			</tr>
			<tr height="20px">
			  <td align="right"><span id="searchTimeTitel">交易时间：</span></td>
			  <td colspan="6">
					<input class="easyui-datebox" name="startCreateTime" id="startCreateTimes" /> 至
	                <input class="easyui-datebox" name="endCreateTime" id="endCreateTimes" />
	                <a href="javascript:check.selectDate('work');" style="color:#000099">最近7天</a> &nbsp;&nbsp;&nbsp;&nbsp;
	                <a href="javascript:check.selectDate('month');" style="color:#000099">最近30天</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                <a href="javascript:check.selectDate('yeah');" style="color:#000099">最近半年</a>
			  </td>
			  <td colspan="5" align="left"><a href="#" onclick="javascript:check.search();" id="search" class="easyui-linkbutton" style="width: 80px">查询</a></td>
			</tr>
		</table>
	</div>
</form>
	<div id="InsureListTabs" class="easyui-tabs" style="height: 670px">
		<div title="支付报表">
			<table id="paylog" style="height: 600px"></table>
		</div>
		<div title="退款报表">
			<table id="refund" style="height: 600px"></table>
		</div>
		<div title="买家报表">
			<table id="buyer" style="height: 600px"></table>
		</div>
		<div title="卖家报表">
			<table id="sell" style="height: 600px"></table>
		</div>
	</div>
</body>
</html>