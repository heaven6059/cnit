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
<title>结算中心</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/settle/settle.js"></script>
</head>
<body>
	<div class="member-main">
		<div class="title title2">结算中心</div>
		<div >
		  <div class="FormWrap" style="background-color: #FFFFFF;border: 1px solid #eee;padding: 13px 5px;">
			<form id = "formId"  >
				<table>
					<colgroup>
						<col style="width: 88px;">
						<col style="width: 170px;">
						<col style="width: 88px;">
						<col style="width: 240px;">
						<col style="width: 88px;">
						<col style="width: 170px;">
					</colgroup>
					<tbody>
						<tr>
							<td style="text-align: right;">对账时间段：</td>
							<td  colspan="4" style="text-align: left; padding-left: 10px;">
								<input class="member-x-calendar" size="10" maxlength="10" autocomplete="off" type="text" name="startDate" id="startDate" vtype="date" onClick="WdatePicker()">
								<img onclick="WdatePicker({el:'startDate'})" src="${path}/resources/scripts/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">	 
								<input class="member-x-calendar" size="10" maxlength="10" autocomplete="off" type="text" name="endDate" id="endDate"  vtype="date" onClick="WdatePicker()">
			       		        <img onclick="WdatePicker({el:'endDate'})" src="${path}/resources/scripts/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
								<a href="javascript:settle.selectDate('work');" style="color:#000099">最近7天</a> &nbsp;&nbsp;&nbsp;&nbsp;
				                <a href="javascript:settle.selectDate('month');" style="color:#000099">最近30天</a>&nbsp;&nbsp;&nbsp;&nbsp;
				                <a href="javascript:settle.selectDate('yeah');" style="color:#000099">最近半年</a>
							</td>
							<td style="text-align: right; padding: 8px 0;">订单号：</td>
							<td style="text-align: left; padding-left: 10px;">
							 	<input type="text" vtype="text" id="orderId" maxlength="20" name="orderId" class="member-x-input " autocomplete="off">
							</td>
						</tr>
						<tr>
							<td></td><td></td><td></td><td></td><td></td>
							<td colspan="2" style="text-align: center; ">
								<input id="btnsearch" class="btn search1" type="button" value="搜索" onclick="javascript:settle.search();">
								<input id="btnsearch" class="btn search1" type="button" value="重置" onclick="javascript:settle.resetForm();">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div style=' height:50px;line-height:50px;overflow:hidden;border:1px;background:#fff4e8;font-size:14px;' >
			<span>订单总金额：￥  <span id="totalAmount">0.00</span></span>
			<span>实付金额：￥  <span id="finalAmount">0.00</span></span>
			<span>退款金额：￥  <span id="refundsFeel">0.00</span></span>
			<span style="padding-left:100px;">总订单数：<span class="point" id="orderTotal">0</span></span>
			<span style="padding-left:100px;">总商品数：    <span class="point" id="itemTotal">0</span></span>
		</div>
		<div  style="margin-top: 10px; border:none;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;">
			<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id ="reportTableList" style="border: 1px solid #ddd;text-align:center;">
				<tr>
					<th>订单号</th>
					<th>订单总金额</th>
					<th>实际支付金额</th>
					<th>商品总数</th>
					<th>买家</th>
					<th>退款金额</th>
					<th>操作</th>
				</tr>
			</table>
		</div>
		<div class="page clearfix">
			<div id="Pagination" class="yoyoPagination"></div>
		</div>
		</div>
		</div>
</body>
</html>