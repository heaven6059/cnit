<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	Long time = System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>促销公告列表</title>
	<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
	<script type="text/javascript" src="${path}/resources/scripts/biz/news/newsIndex.js?v=${time}"></script>    
</head>
<body>
	<div id="toolbar-violation">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="addNews()"> 添加</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteNews()"> 删除 </a>
        <a href="javascript:;" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openSeachWindow()">高级筛选</a>
    </div>
    <table id="table-news"></table>
	
	<div id="advance_search_news" class="easyui-dialog advance_search" title="高级筛选" style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;">
		<form id="news_search_form" class="easyui-form" method="post" data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;width:100%;">
				<tbody>
					<tr>
						<td align="right"><label>标题：</label></td>
						<td><input type="text" name="title" style="width: 120px;" class="easyui-text" data-options="precision:0,min:0" /></td>
					</tr>
					<tr>
						<td align="right"><label>作者：</label></td>
						<td><input type="text" name="author" style="width: 120px;" class="easyui-text" data-options="precision:0,min:0" /></td>
					</tr>
					<tr>
	                    <td align="right">类型：</td>
	                    <td>
	                    	<input class="easyui-combobox" name="type" data-options="valueField:'value',textField:'name',data:[{name:'',value:null},{name:'公告',value:'true'},{name:'特惠',value:'false'}]">
	                    </td>
	                </tr>
					<tr>
						<td align="right"><label>日期：</label></td>
						<td style="text-align: left;">
							<input id="startDate" name="startDate" type="text" class="easyui-datetimebox" />
							至
							<input id="endDate" name="endDate" type="text" class="easyui-datetimebox" />
						</td>
					</tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="advanceSearch()" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="cleanAndSearch()" style="width: 90px">清除条件</a>
    </div>
	<div id="MODALPANEL" style="position: absolute; width: 100%; height: 100%; top: 0px; left: 0px; z-index: 6000; opacity: 0.4; background: rgb(51, 51, 51);display:none;"></div>
	
</body>
</html>