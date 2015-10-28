<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保证金</title>
<link type="text/css" href="${path}/resources/styles/shopMng/roleManager.css?r=${versionVal}" rel="stylesheet" />
<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js?r=${versionVal}"></script>
<script src="${path}/resources/scripts/biz/shopMng/earnestManager.js?r=${versionVal}"></script>
</head>
<body>
	<div class="shop_manager_right">
		<div class="title">
			<span style="float: left">我的保证金<!-- <span class="disc">|</span> <span
				class="disc add-icon"><a href="javascript:addRole();">添加新角色</a></span> -->
			</span>
		</div>
		
		<div class="panel datagrid">
		<span style="padding-top:20px;font-size: 15px;">您当前的保证金余额为：<span name="earnestValue">￥0.00</span><span style="display:none;">，需要交纳保证金：<span name="earnestValue2">￥0.00</span></span></span>
			<div class="datagrid-wrap panel-body panel-body-noheader" title=""
				style="width: 962px;margin-top:20px;">
				<div style="padding: 10px;color: red;font-size: 15px;">保证金交易记录:</div>
				<table class="datagrid-btable" style="table-layout: auto;border: 1px solid #F0F0F0;width: 100%;">
						<tr>
							<th style="border: 1px solid #DEDEDE;">保证金</th>
							<th style="border: 1px solid #DEDEDE;">操作员</th>
							<th style="border: 1px solid #DEDEDE;">备注</th>
							<th style="border: 1px solid #DEDEDE;">日期</th>
						</tr>
						<tr id="earnest_1" class="datagrid-row" style="height: 50px;">
								<td field="earnest"></td>
								<td field="operator"></td>
								<td field="remark">
								</td>
								<td field="date">
								</td>
							</tr>
				</table>
				<div class="datagrid-pager pagination">
					<table cellspacing="0" cellpadding="0" border="0">
						<tbody>
							<tr>
								<td><select class="pagination-page-list"><option value="10">10</option>
										<option value="20">20</option>
										<option value="30">30</option>
										<option value="40">40</option>
										<option value="50">50</option></select></td>
								<td><div class="pagination-btn-separator"></div></td>
								<td><a href="javascript:void(0)"
									class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled"
									group="" id=""><span class="l-btn-left l-btn-icon-left"><span
											class="l-btn-text l-btn-empty">&nbsp;</span><span
											class="l-btn-icon pagination-first">&nbsp;</span></span></a></td>
								<td><a href="javascript:void(0)"
									class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled"
									group="" id=""><span class="l-btn-left l-btn-icon-left"><span
											class="l-btn-text l-btn-empty">&nbsp;</span><span
											class="l-btn-icon pagination-prev">&nbsp;</span></span></a></td>
								<td><div class="pagination-btn-separator"></div></td>
								<td><span style="padding-left: 6px;">第</span></td>
								<td><input class="pagination-num" type="text" value="1"
									size="2"></td>
								<td><span style="padding-right: 6px;" name="sumPages"></span></td>
								<td><div class="pagination-btn-separator"></div></td>
								<td><a href="javascript:void(0)"
									class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled"
									group="" id=""><span class="l-btn-left l-btn-icon-left"><span
											class="l-btn-text l-btn-empty">&nbsp;</span><span
											class="l-btn-icon pagination-next">&nbsp;</span></span></a></td>
								<td><a href="javascript:void(0)"
									class="l-btn l-btn-small l-btn-plain l-btn-disabled l-btn-plain-disabled"
									group="" id=""><span class="l-btn-left l-btn-icon-left"><span
											class="l-btn-text l-btn-empty">&nbsp;</span><span
											class="l-btn-icon pagination-last">&nbsp;</span></span></a></td>
								<td><div class="pagination-btn-separator"></div></td>
								<td><a href="javascript:void(0)"
									class="l-btn l-btn-small l-btn-plain" group="" id=""><span
										class="l-btn-left l-btn-icon-left"><span
											class="l-btn-text l-btn-empty">&nbsp;</span><span
											class="l-btn-icon pagination-load">&nbsp;</span></span></a></td>
							</tr>
						</tbody>
					</table>
					<div class="pagination-info"></div>
					<div style="clear: both;"></div>
				</div>
			</div>
		</div>


	</div>
</body>
</script>
</html>