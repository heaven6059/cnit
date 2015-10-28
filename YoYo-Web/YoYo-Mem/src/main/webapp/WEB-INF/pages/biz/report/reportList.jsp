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
<title>举报管理</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css" />
<script type="text/javascript" src="${path}/resources/scripts/dataUtil.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/report/reportList.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<style>
select{outline:none;} select:focus{outline:#fff solid 1px;}
</style>
</head>
<body>
	<div class="member-main">
		<div class="title title2">举报管理</div>
		<div >
		  <div class="FormWrap" style="background-color: #FFFFFF;">
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
							<td style="text-align: right; padding: 8px 0;">商品名称：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <input type="text" vtype="text" id="search_productName" maxlength="20" name="search_productName" class="member-x-input " autocomplete="off" style="border:1px solid #ccc; border-radius:3px;">
							</td>
							<td style="text-align: right;">举报编号：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <input type="text" vtype="text" id="search_reportId" maxlength="18"  name="search_reportId" class="member-x-input " autocomplete="off" style="border:1px solid #ccc; border-radius:3px;" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
							</td>
							<td style="text-align: right;">举报原因：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <select class=" member-x-input-select" required="1" name="search_reason" id="search_reason" type="select" style="border:1px solid #ccc; border-radius:3px;">
									<option value="">请选择</option>
									<option value="false">虚假信息</option>
									<option value="unconformity">图片不符</option>
							</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">举报状态：</td>
							<td style="text-align: left; padding-left: 10px;">
							 <select class="member-x-input-select" required="1" name="search_status" id="search_status" type="select" style="border:1px solid #ccc; border-radius:3px;">
									<option value="">请选择</option>
									<option value="intervene">平台介入</option>
									<option value="success">投诉成立</option>
									<option value="error">投诉不成立</option>
									<option value="cancel">举报撤销</option>
							 </select>
							</td>
							<td style="text-align: right;">申请时间：</td>
							<td style="text-align: left; padding-left: 10px;">
							<input class="member-x-calendar" size="10" maxlength="10" autocomplete="off" type="text" name="search_startDate" id="search_startDate"
								  vtype="date" onClick="WdatePicker()" style="border:1px solid #ccc; border-radius:3px;">
							<img onclick="WdatePicker({el:'search_startDate'})" src="${path}/resources/scripts/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">	 
							<input class="member-x-calendar" size="10" maxlength="10" autocomplete="off" type="text" name="search_endDate" id="search_endDate"
								 vtype="date" onClick="WdatePicker()" style="border:1px solid #ccc; border-radius:3px;">
		       		        <img onclick="WdatePicker({el:'search_endDate'})" src="${path}/resources/scripts/My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle">
							</td>
							<td colspan="2" style="text-align: center; ">
								<input id="btnsearch" class="btn search1" type="button" value="搜索" " onclick="javascript:search();" style="border:1px solid #ccc; border-radius:3px;">
								<input id="btnsearch" class="btn search1" type="button" value="重置" " onclick="javascript:resetForm();" style="border:1px solid #ccc; border-radius:3px;">
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		</div>
			<div>
				<table class="gridlist border-all gridlist_member" width="100%" border="0" cellspacing="0" cellpadding="0" id = "tableId">
					<tbody>
						<tr>
								<th >举报编号</th>
								<th >商品名称</th>
								<th >被举报方</th>
								<th >举报原因</th>
								<th >举报状态</th>
								<th >申请时间</th>
								<th >操作</th>
						</tr>
					</tbody>
					<tbody>
					</tbody>
				</table>
			</div>	
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
	</div>
</body>
</html>