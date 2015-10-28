<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>配件参数类型</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/car/accessoryCatalogIndex.js?v=${versionVal}"></script>
</head>
<body>
	<div id="toolbar-acc-catalog" style="height: 30px; width: 100%">
		<a class="easyui-linkbutton" iconCls="icon-add" onclick="newAccCatalog()"> 添加配件类型</a> <a id="acc-catalog-ads-button"
			class="easyui-linkbutton">高级筛选</a>
	</div>
	<!-- 配件类型列表-->
	<table id="table-acc-catalog" style="marge: 10px auto; width: 100%;"></table>
	<!-- 配件类型编辑-->
	<!-- <div id="window-acc-catalog" class="easyui-window" closed="true" style="height: 600px; width: 800px;"
		data-options="inline:true,top:50,cache:false,onOpen:getAccCatalogDetail,onBeforeClose:clearDetail,minimizable:false,modal:true"> -->
	<div id="window-acc-catalog" class="easyui-window" closed="true" style="height: 600px; width: 800px;"
		data-options="top:50,cache:false,onOpen:getAccCatalogDetail,onBeforeClose:clearDetail,minimizable:false,modal:true">
		<div style="margin: 20px 0px 20px 100px">
			<form id="form-acc-catalog" WalidateTime="realtime">
				<table class="table-form">
					<tr class="hide">
						<td class="table-form-label">类型Id：</td>
						<td><input class="input" type="text" name="catalogId"></td>
					</tr>
					<tr>
						<td class="table-form-label">类型名称：</td>
						<td><input type="text" name="catalogName" style="width: 285px; height: 25px;" class="walidator"
							walitype="{require:true}"></td>
					</tr>
					<tr>
						<td class="table-form-label">商品分类：</td>
						<td><select id="id-good-cat" style="width: 300px; height: 28px;"></select></td>
					</tr>
					<tr>
						<td class="table-form-label">排序：</td>
						<td><input type="text" name="orderNum" class="walidator" style="width: 285px; height: 25px;"
							walitype="{match:'integer'}"></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="min-height: 300px;">
			<div id="toolbar-acc-catalog-params">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAccCatalogParam()"> 添加参数项</a>
			</div>
			<table id="table-acc-catalog-params" class="easyui-datagrid" data-options="toolbar:'#toolbar-acc-catalog-params',collapsible:true,singleSelect: true,rownumbers:true,onDblClickCell:onDblClickCell,onSelect:onSelect">
				<thead>
					<th data-options="field:'paramId',width:100,hidden:true,editor:{type:'text'}">参数Id</th>
					<th data-options="field:'catalogId',width:100,hidden:true,editor:{type:'text'}">参数类型Id</th>
					<th data-options="field:'paramName',width:150,editor:{type:'text',options:{required:true}}">参数名称 <span style="color:red;">*</span></th>
					<th data-options="field:'paramValues',width:300,editor:{type:'text'}">参数可选值</th>
					<th data-options="field:'dataType',disabled:'true',width:100,editor:{type:'combobox',options:{valueField: 'id',textField: 'name',data:[{id:'STR',name:'字符'}],required:true},formatter:function(value){
							if(value=='STR'){return '字符';};if(value=='INT'){return '整数';};if(value=='BOL'){return '布尔';};if(value=='DEC'){return '小数';}
						}}">值类型</th>
					<th data-options="field:'orderNum',width:100,editor:{type:'numberbox'}">排序</th>
					<th data-options="field:'edit',width:100,formatter:accCatalogParamsEditor">编辑</th>
					</tr>
				</thead>
			</table>
		</div>
		<div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px;">
			<a class="easyui-linkbutton" icon="icon-save" onclick="saveAccCatalog()">保存</a> <a class="easyui-linkbutton"
				icon="icon-cancel" onclick="javascript:$('#window-acc-catalog').window('close');editIndex = undefined;">取消</a>
		</div>
	</div>
	<div id='table-acc-catalog-advance-searcher'></div>
</body>
</html>