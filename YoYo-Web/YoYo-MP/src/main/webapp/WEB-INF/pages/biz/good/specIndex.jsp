<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>商品规格</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/biz/good/specIndex.js?v=${versionVal}"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript">
	var editIndex = undefined;
	$(function() {
		$('#form-spec-add').walidator();
	});
	function specValuesEditor(value, row, index) {
		return '<a onclick="editrow('+index+')">编辑</a><a onclick="javascript:deleteTableSpecValuesRow(' + index + ')">删除</a>';
	}
	function deleteTableSpecValuesRow(index) {
		var row = $('#table-spec-values-add').datagrid('getSelected');
		if (row) {
			var rowIndex = $('#table-spec-values-add').datagrid('getRowIndex', row);
			$('#table-spec-values-add').datagrid('deleteRow', rowIndex);
		}
		//$('#table-spec-values-add').datagrid('deleteRow', index);
		editIndex = undefined;
	}
	function onDblClickCell(index, field, value) {
		if (editIndex != index) {
			if (endEditing()) {
				$('#table-spec-values-add').datagrid('selectRow', index)
						.datagrid('beginEdit', index);
				var ed = $('#table-spec-values-add').datagrid('getEditor', {
					index : index,
					field : field
				});
				($(ed.target).data('textbox') ? $(ed.target).textbox('textbox')
						: $(ed.target)).focus();
				editIndex = index;
			} else {
				$('#table-spec-values-add').datagrid('selectRow', editIndex);
			}
		}
	}
	function newSpecValue() {
		if (endEditing()) {
			$('#table-spec-values-add').datagrid('appendRow', {});
			editIndex = $('#table-spec-values-add').datagrid('getRows').length - 1;
		}
		$('#table-spec-values-add').datagrid('selectRow', editIndex).datagrid(
				'beginEdit', editIndex);
	}

	function endEditing() {
		if (editIndex == undefined) {
			return true;
		}
		if ($('#table-spec-values-add').datagrid('validateRow', editIndex)) {
			var $table = $('#table-spec-values-add');
			var specValueName = $table.datagrid('getEditor', {
				index : editIndex,
				field : 'specValueName'
			}).target.val();
			var specValue = $table.datagrid('getEditor', {
				index : editIndex,
				field : 'specValue'
			}).target.val();
			if (specValueName && specValue) {
				var rows = $('#table-spec-values-add').datagrid('getRows');
				//for ( var i = 0; i < rows.length - 1; i++) {
				//	if (specValueName == rows[i].specValueName
				//			|| specValue == rows[i].specValue) {
				//		return false;
				//	}
				//}
				$('#table-spec-values-add').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	function onSelect(index, row) {
		if (editIndex != index) {
			if (endEditing()) {
				editIndex = undefined;
				return true;
			} else {
				$('#table-spec-values-add').datagrid('selectRow', editIndex);
				return false;
			}
		}
	}
	function editrow(index) {
		$('#table-spec-values-add').datagrid('selectRow', index).datagrid(
				'beginEdit', index);
		editIndex = index;
	};
</script>
</head>
<body>
	<div id="toolbar-spec">
		<a class="easyui-linkbutton" iconCls="icon-add" onclick="newSpecAndValue()"> 添加规格</a> <a id="acc-spec-ads-button"
			class="easyui-linkbutton" style="width: 80px;">高级筛选</a>
		<!-- <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteSpecAndValue()"> 删除 </a> -->
	</div>
	<!-- 规格列表-->
	<table id="table-spec" style="marge: 10px auto;"></table>
	<!-- 规格编辑-->
	<div id="window-add-spec" class="easyui-window" closed="true" style="height: 500px; width: 800px;"
		data-options="cache:false,onOpen:getDetail,onBeforeClose:clearDetail,minimizable:false,modal:true">
		<form id="form-spec-add" style="position: relative; left: 200px; width: 500px;" WalidateTime="realtime">
			<table class="table-form" style="border: none;">
				<tr class="hide">
					<td align="right"><label>规格ID：</label></td>
					<td align="left"><input type="text" name="specId" /></td>
				</tr>
				<tr>
					<td align="right"><label>规格名称：</label></td>
					<td align="left"><input type="text" class="walidator" walitype="{require:true}" name="specName"
						style="width: 200px; padding: 3px 5px;" /></td>
				</tr>
				<tr>
					<td align="right"><label>规格备注：</label></td>
					<td align="left"><input type="text" class="walidator" name="specMemo" style="width: 200px; padding: 3px 5px;" /></td>
				</tr>
				<tr>
					<td align="right"><label>规格别名：</label></td>
					<td align="left"><input type="text" class="walidator" name="specAlias" style="width: 200px; padding: 3px 5px;" /></td>
				</tr>
				<!-- <tr>
					<td align="right"><label>显示类型：</label></td>
					<td align="left"><select name="specShowType" class="walidator" walitype="{require:true}" style="width: 215px; padding: 3px 5px;">
							<option value="TEXT">文字</option>
							<option value="IMAGE">图片</option>
					</select></td>
				</tr>-->
				<tr>
					<td align="right"><label>显示方式：</label></td>
					<td align="left"><select name="specSelectMode" class="walidator" walitype="{require:true}"
							style="width: 215px; padding: 3px 5px;">
							<option value="FLAT">平铺</option>
							<option value="SELECT">下拉</option>
						</select></td>
				</tr>
			</table>
		</form>
		<div style="min-height: 200px;">
			<div id="toolbar-spec-values">
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newSpecValue()"> 添加规格值</a>
			</div>
			<table id="table-spec-values-add" class="easyui-datagrid"
				data-options="toolbar:'#toolbar-spec-values',collapsible:true,singleSelect: true,onDblClickCell:onDblClickCell,rownumbers:true,onSelect:onSelect">
				<thead>
					<tr>
						<th data-options="field:'specValueName',width:100, editor: {type:'text',options: {required: true}}">规格值名称 <span style="color:red;">*</span></th>
						<th data-options="field:'specValueAlias',width:100,editor:{type:'text'}">规格值别名</th>
						<th data-options="field:'specValue',width:100, editor: { type: 'text', options: { required: true } }">规格值 <span style="color:red;">*</span></th>
						<th data-options="field:'orderNum',width:100,editor:{type:'numberbox',options:{min:1}}">排序</th>
						<th data-options="field:'disabled',width:100,editor:{type:'checkbox',options:{on:'1',off:'0',required:true}},formatter:isDisable">是否可用<span style="color:red;">*</span></th>
						<th data-options="field:'editor',width:100,formatter:specValuesEditor">编辑</th>
					</tr>
				</thead>
			</table>
		</div>
		<div style="text-align: center; bottom: 0px;">
			<a class="easyui-linkbutton" icon="icon-save" onclick="saveSpecAndValue()">保存</a> 
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-add-spec').window('close');editIndex = undefined;">取消</a>
		</div>
	</div>
	<div id="table-spec-advance-searcher"></div>
</body>
</html>
