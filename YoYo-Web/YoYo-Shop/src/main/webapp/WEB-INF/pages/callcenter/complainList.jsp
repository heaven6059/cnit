<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>
<html>
<head>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<link type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投诉管理</title>
</head>
<body>
	<div class="member-main">
		<div class="title title2">投诉管理</div>
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
							<td style="text-align: right; padding: 8px 0;">订单号：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <input type="text" id="p_orderId" maxlength="18" name="p_orderId" class="member-x-input " autocomplete="off" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
							</td>
							<td style="text-align: right;">投诉编号：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <input type="text" id=p_complainId maxlength="18"  name="p_complainId" class="member-x-input " autocomplete="off" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
							</td>
							<td style="text-align: right;">投诉类型：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <select class=" member-x-input-select" required="1" name="p_reason" id="p_reason" type="select">
									<option value="">请选择</option>
									<option value="after">售后问题</option>
									<option value="action">行为违规</option>
									<option value="quality">质量问题</option>
							</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">投诉状态：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <select class="member-x-input-select" required="1" name="p_status" id="p_status" type="select">
									<option value="">请选择</option>
									<option value="intervene">平台已经介入</option>
									<option value="success">投诉成立</option>
									<option value="error">投诉不成立</option>
									<option value="cancel">投诉撤销</option>
							 </select>
							</td>
							<td style="text-align: right;">申请时间：</td>
							<td style="text-align: left; padding-left: 10px;">
							<input class="member-x-calendar" size="10" maxlength="10" autocomplete="off" type="text" name="p_startDate" id="p_startDate" vtype="date" onClick="WdatePicker()">
							<img onclick="WdatePicker({el:'p_startDate'})" src="${path}/resources/scripts/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">	 
							<input class="member-x-calendar" size="10" maxlength="10" autocomplete="off" type="text" name="p_endDate" id="p_endDate" vtype="date" onClick="WdatePicker()">
		       		        <img onclick="WdatePicker({el:'p_endDate'})" src="${path}/resources/scripts/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
							</td>
							<td colspan="2" style="text-align: center; ">
								<input id="btnsearch" class="btn search1" style="margin-bottom:15px;" type="button" value="搜索"  onclick="javascript:search();">
								<input id="btnsearch" class="btn search1" style="margin-bottom:15px;" type="button" value="重置"  onclick="javascript:resetForm();">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<div  style="margin-top: 10px; border:none;border-left: 1px solid #ddd;border-bottom: 1px solid #ddd;">
			<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId" style="border: 1px solid #ddd;">
				<tbody>
					<tr>
						<th >投诉编号</th>
						<th >订单编号</th>
						<th >被投诉方</th>
						<th >投诉原因</th>
						<th >投诉状态</th>
						<th >申请时间</th>
						<th >操作</th>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="page clearfix">
			<div id="Pagination" class="yoyoPagination"></div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/callcenter/complainList.js"></script>	
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</body>
</html>