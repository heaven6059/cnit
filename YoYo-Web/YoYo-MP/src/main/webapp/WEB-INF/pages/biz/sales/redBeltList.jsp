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
<title>红包发放列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
</head>
<body>
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="addScoreBuy">发放红包 </a>
		<a href="javascript:void(0)" onclick="openAdvaceQuery('advance_search_brand')" iconcls="icon-search" class="easyui-linkbutton l-btn l-btn-small" style="margin-left: 50px;">高级筛选</a>
	</div>
	
	<table id="redBelt_list_dataGrid" style="marge: 10px auto;"> </table>
	<div id="scoreBuyDialog" class="easyui-dialog" style="width: 884px;height:500px;" data-options="fit:true,closed:true,cache:false"></div>
	
	<div id="advance_search_brand" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="brand_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tbody>
	             	 <tr>
		                <td>开始时间：</td>
		                <td>
			                <input id="apply_start_date" type="text" name="applyStartTime" data-options="editable:false" class="easyui-datetimebox" />
						</td>
					</tr>
		            <tr>
		                <td>结束时间：</td>
						<td>
							<input id="apply_end_date" type="text" name="applyEndTime" data-options="editable:false" class="easyui-datetimebox" />
						</td>
					</tr>
		            <tr>
		                <td>状态：</td>
		                <td>
		                    <input type="radio" name="status" value="1">开启
		                    <input type="radio" name="status" value="0">关闭
		                </td>
		            </tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="advanceQuery('score_list_dataGrid','brand_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"  iconCls="icon-cancel" onclick="search_clear('brand_search_form','score_list_dataGrid')" style="width: 90px">清除条件</a>
    </div>
<script type="text/javascript" src="${path}/resources/scripts/biz/sales/redBelt.js?t=${time} "></script>
</body>
</html>

