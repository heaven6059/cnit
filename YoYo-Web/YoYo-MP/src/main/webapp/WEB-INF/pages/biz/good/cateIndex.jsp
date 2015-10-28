<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分类</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/good/cateIndex.js?v=${versionVal}"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript">
	$(function(){
		$('#form-cate-add').walidator();
	});
	function expandAll() {
		$('#table-cate').treegrid('expandAll');
	}
	function collapseAll() {
		$('#table-cate').treegrid('collapseAll');
	}
	function attrEditor(value, row, index) {
		return '<a onclick="javascript:deleteTableAttrRow(' + index + ')">删除</a>'
	}
	function deleteTableAttrRow(index) {
		$('#table-attr-add').datagrid('deleteRow', index);
	}
	function onDblClickCell(index, field, value) {
		if (editIndex != index) {
			if (endEditing()) {
				$('#table-attr-add').datagrid('selectRow', index).datagrid('beginEdit', index);
				var ed = $('#table-attr-add').datagrid('getEditor', { index : index , field : field });
				($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
				editIndex = index;
			} else {
				$('#table-attr-add').datagrid('selectRow', editIndex);
			}
		}
	}
	function newAttrValue() {
		if (endEditing()) {
			$('#table-attr-add').datagrid('appendRow', {});
			editIndex = $('#table-attr-add').datagrid('getRows').length - 1;
		}
		$('#table-attr-add').datagrid('selectRow', editIndex).datagrid('beginEdit', editIndex);
	}
	var editIndex = undefined;
	function endEditing() {
		if (editIndex == undefined) {
			return true
		}
		var $table = $('#table-attr-add');
		var attrName = $table.datagrid('getEditor', {
			index : editIndex,
			field : 'attrName'
		}).target;
		var attrValues = $table.datagrid('getEditor', {
			index : editIndex,
			field : 'attrValues'
		}).target;
		if(attrName.val() && attrValues.val()){
			if ($('#table-attr-add').datagrid('validateRow', editIndex)) {
				$('#table-attr-add').datagrid('endEdit', editIndex);
				editIndex = undefined;
				return true;
			} else {
				return false;
			}
		}else{
			return false;
		}
		
	}
	function onSelect(index, row) {
		if (editIndex != index) {
			if (endEditing()) {
				return true;
			} else {
				$('#table-attr-add').datagrid('selectRow', editIndex);
				return false;
			}
		}
	}
</script>
</head>
<body>
	<div id="toolbar-cate">
		<!-- <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCate()"> 添加分类</a> -->
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="expandAll()"> 展开分类</a> 
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="collapseAll()"> 收起分类</a>
		<!-- <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrderNum()"> 编辑排序</a> -->
	</div>
	<table id="table-cate" style="marge: 10px auto;"></table>
	<!-- 分类编辑 -->
	<div id="window-cate-add" class="easyui-window" closed="true" style="height: 500px; width: 950px;" data-options="modal:true,inline:false,cache:false,onOpen:getDetail,onBeforeClose:clearDetail,minimizable:false"">
		<div class="easyui-layout" fit="true">
			<div region="center" border="true" style="min-height: 200px;">
				<div id="tab-cate-add" class="easyui-tabs" >
					<div title="基本信息">
						<form id="form-cate-add"  WalidateTime="realtime">
							<table class="table-form" style="border: none;">
								<tr class="hide">
									<td align="right">
										<label>分类ID：</label>
									</td>
									<td>
										<input type="text" name="catId"  style="width: 200px;"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>分类名称：<span style="color:red;">*</span></label>
									</td>
									<td>
										<input type="text" name="catName"  style="width: 200px;" class="walidator" walitype="{require:true}" />
									</td>
								</tr>
								<tr id="tr_parent_cat_id">
									<td align="right">
										<label>上级分类：</label>
									</td>
									<td>
										<select id="combox-parent-cat" class="easyui-combogrid walidator" name="parentCatId" style="width: 215px;padding: 3px 5px;height: 28px;"></select>
										<span>未选择上级分类时默认添加一级分类</span>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>排序：</label>
									</td>
									<td>
										<input type="text" name="orderNum" class="walidator" walitype="{match:'integer'}" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>交易佣金费率：</label>
									</td>
									<td>
										<input type="text" name="profitPoint" class="easyui-numberbox"  value="0" precision="2" min="0" max="99" size="4" maxlength="4"  /> %
									</td>
								</tr>
								<tr style="display:none;">
									<td align="right">
										<label>在前台隐藏：</label>
									</td>
									<td>
										<input type="checkbox" name="hidden"  id="hiddenId"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>TITLE （页面标题）：</label>
									</td>
									<td>
										<input type="text" name="title"  class="walidator" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>META_KEYWORDS (页面关键词)：</label>
									</td>
									<td>
										<input type="text" name="metaKeywords"  class="walidator" />
									</td>
								</tr>
								<tr>
									<td align="right">
										<label>META_DESCRIPTION （页面描述）：</label>
									</td>
									<td>
										<textarea type="text" name="metaDescription"  rows="5"  cols="28" class="walidator" ></textarea>
									</td>
								</tr>
							</table>
						</form>
					</div>
					<div title="扩展属性">
						<div id="toolbar-attr-add">
							<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newAttrValue()">添加扩展属性</a>
						</div>
						<table id="table-attr-add" style="marge: 10px auto; min-height: 200px;" class="easyui-datagrid"
							data-options="singleSelect: true,toolbar:'#toolbar-attr-add',collapsible:true,onDblClickCell:onDblClickCell,rownumbers:true,onSelect:onSelect">
							<thead>
								<tr>
									<th data-options="field:'attrName',width:100,editor:{type:'text',options:{required:true}}">属性名 <span style="color:red">*</span></th>
									<th data-options="field:'attrAlias',width:100,editor:{type:'text'}">属性别名(|分割)</th>
									<th data-options="field:'attrValues',width:120,editor:{type:'text',options:{required:true}}">选择项可选值(|分割)<span style="color:red">*</span></th>
									<th data-options="field:'attrInputType',width:120,formatter:formatInputType,editor:{type:'combobox',options:{required:true,valueField: 'id',textField: 'name',data:[{id:'select',name:'下拉选择'}]}}">填值方式<span style="color:red">*</span></th>
									<th data-options="field:'attrShow',width:120,formatter:formatAttrShow,editor:{type:'checkbox',options:{on:'1',off:'0',required:true}}">是否可用<span style="color:red">*</span></th>
									<th data-options="field:'orderNum',width:100,editor:{type:'numberbox'}">排序</th>
									<th data-options="field:'editor',width:100,formatter:attrEditor">编辑</th>
								</tr>
							</thead>
						</table>
					</div>
					<div title="规格">
						<table>
							<tr>
								<td>
									<label>规格：</label>
								</td>
								<td>
									<select id="select-ajax-spec" style="width: 400px;padding: 3px 5px;height: 28px;"></select>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
			<div region="south" style="text-align: center; height: 40px; line-height: 40px;">
				<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdateCate()">保存</a>
				<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-cate-add').window('close')">取消</a>
			</div>
		</div>
	</div>
</body>
</html>
