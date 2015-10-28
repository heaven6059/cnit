<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">   
<meta http-equiv="cache-control" content="no-cache">   
<meta http-equiv="expires" content="0">   
<title>活动列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/sales/scoreBuyApply.js?t=${time} "></script>
<link type="text/css" href="${path}/resources/styles/style.css?t=${time}" rel="stylesheet" />
</head>
<body>
<input type="hidden" id="type" value="${type}"/>
	<div id="tabs-goods" class="easyui-tabs">
		<div title="全部" selected="true"></div>
		<div title="待审核"></div>
		<div title="审核通过"></div>
		<div title="审核未通过"></div>
	</div>
	<div id="toolbar">
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteActivity()"> 删除</a> 
		&nbsp;
		<a href="javascript:void(0)" onclick="openAdvaceQuery('advance_search_brand')" iconcls="icon-search" class="easyui-linkbutton l-btn l-btn-small" style="margin-left: 50px;">高级筛选</a>
	</div>
	
	<table id="score_list_dataGrid" style="marge: 10px auto;">
	</table>
	<div id="scoreBuyDialog" class="easyui-dialog" style="width: 884px;height:500px; padding: 10px 20px" data-options="fit:true,closed:true,cache:false"></div>
	</div>
	
	<div id="advance_search_brand" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="brand_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tbody>
		            <tr>
		                <td>活动开始时间：</td>
		                <td>
							<input id="activity_start_date" type="text" name="startTime" data-options="editable:false" class="easyui-datetimebox" />
						</td>
		            </tr>
		            <tr>
		                <td>活动结束时间：</td>
						<td>
							<input id="activity_end_date" type="text" name="endTime" data-options="editable:false" class="easyui-datetimebox" />
						</td>
		            </tr>
		              <!-- <tr>
		                <td>申请开始时间：</td>
		                <td>
			                <input id="apply_start_date" type="text" name="applyStartTime" data-options="editable:false" class="easyui-datetimebox" />
						</td>
					</tr>
		            <tr>
		                <td>申请结束时间：</td>
						<td>
							<input id="apply_end_date" type="text" name="applyEndTime" data-options="editable:false" class="easyui-datetimebox" />
						</td>
					</tr> -->
		            <tr>
		                <td>活动是否开启：</td>
		                <td>
		                    <input type="radio" name="actOpen" value="1">开启
		                    <input type="radio" name="actOpen" value="0">关闭
		                </td>
		            </tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('score_list_dataGrid','brand_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="search_clear('brand_search_form','score_list_dataGrid')" style="width: 90px">清除条件</a>
    </div>
</body>
</html>

