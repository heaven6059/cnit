<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车配件</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<link rel="stylesheet" href="${path}/resources/scripts/biz/jquery.walidator.css" type="text/css">
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/car/accessoryIndex.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript">
	$(function() {
		$('#form-accessory').walidator();
	});
</script>
<style type="text/css">
#table-car-index {
	display: inline-block;
}

#table-car-index thead span {
	display: inline-block;
	width: 130px !important;
	text-align: center !important;
	background-color: #8095D6 !important;
	color: white !important;
	padding: 3px 5px !important;
	border: 1px dotted gray !important;
	margin: 2px !important;
}
</style>
</head>
<body>
	<div id="toolbar-accessory" style="height: 30px; width: 100%">
		<a class="easyui-linkbutton" iconCls="icon-add" onclick="newAccessory()"> 添加配件</a> <a id="accessory-ads-button"
			class="easyui-linkbutton">高级筛选</a>
	</div>
	<!-- 配件类型列表-->
	<table id="table-accessory" style="marge: 10px auto; width: 100%;"></table>
	<!-- 配件类型编辑-->
	<div id="window-accessory" class="easyui-window" closed="true" style="height: 850px; width: 800px;"
		data-options="top:50,cache:false,onOpen:getAccDetail,onBeforeClose:clearAccDetail,minimizable:false,modal:true">
		<div id="tabs-accessory-edit" class="easyui-tabs" fit=true>
			<div title="配件信息">
				<form id="form-accessory" WalidateTime="realtime" style="padding: 10px;">
					<table class="table-form" style="border: 0px none;">
						<tr class="hide">
							<td><label>配件Id：</label></td>
							<td><input type="text" name="accId" style="width: 200px; padding: 3px 5px;"></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>名称：</label></td>
							<td><input type="text" name="accName" class="walidator" walitype="{require:true}"
								style="width: 200px; padding: 3px 5px;"></td>
							<td class="table-form-label"><label>配件类别：</label></td>
							<td><select id="select-accessory-catalog" name="catId" class="walidator"
									style="width: 215px; padding: 3px 5px; height: 28px;"></select></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>规格：</label></td>
							<td><input type="text" name="accSpec" class="walidator" style="width: 200px; padding: 3px 5px;"></td>
							<td class="table-form-label"><label>单位：</label></td>
							<td><input type="text" name="accUnit" class="walidator" style="width: 200px; padding: 3px 5px;"></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>简称：</label></td>
							<td><input type="text" name="accForshort" class="walidator" style="width: 200px; padding: 3px 5px;">
							</td>
							<td class="table-form-label"><label>编码：</label></td>
							<td><input type="text" name="accCode" class="walidator" style="width: 200px; padding: 3px 5px;"></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>OEM号：</label></td>
							<td><input type="text" name="accOem" class="walidator" style="width: 200px; padding: 3px 5px;" value=""></td>
							<td class="table-form-label"><label>品牌：</label></td>
							<td><select id="select-accessory-brand" name="brandId" class="walidator"
									style="width: 215px; padding: 3px 5px; height: 28px;"></select></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>主机厂：</label></td>
							<td><input type="text" name="accMainPlants" class="walidator" style="width: 200px; padding: 3px 5px;">
							</td>
							<td class="table-form-label"><label>SC价格：</label></td>
							<td><input type="text" name="accScPrice" class="walidator" walitype="{match:'number'}"
								style="width: 200px; padding: 3px 5px;"></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>指导价：</label></td>
							<td><input type="text" name="accPrice" class="walidator" walitype="{match:'number'}"
								style="width: 200px; padding: 3px 5px;"></td>
							<td class="table-form-label"><label>包装：</label></td>
							<td><input type="text" name="accPack" class="walidator" style="width: 200px; padding: 3px 5px;"></td>
						</tr>
						<tr>
							<td class="table-form-label"><label>物流属性：</label></td>
							<td><input type="text" name="accLogistics" class="walidator" style="width: 200px; padding: 3px 5px;">
							</td>
							<td class="table-form-label"><label>弃用：</label></td>
							<td><input type="radio" name="disabled" class="walidator" value=1 >是 
								<input type="radio" name="disabled" class="walidator" value=0 checked="checked">否</td>
						</tr>
						<tr>
							<td class="table-form-label"><label>排序：</label></td>
							<td><input type="text" name="orderNum" class="walidator" walitype="{match:'number'}"
								style="width: 200px; padding: 3px 5px;"></td>
							<td class="table-form-label"><label>备注：</label></td>
							<td colspan="3"><input type="text" name="remark" class="walidator" style="width: 200px; padding: 3px 5px;">
							</td>
						</tr>
					</table>
				</form>
				<div>
					<label>关联车型：</label>
					<div style="padding: 10px;" class="clearfix">
						<div style="float: left; margin: 5px 10px;">
							<label style="display: inline-block;">厂商：</label>
							<select id="select-factory" style="width: 120px; height: 28px;"></select>
						</div>
						<div style="float: left; margin: 5px 10px;">
							<label>车系：</label>
							<select id="select-dept" style="width: 120px; height: 28px;"></select>
						</div>
						<div style="float: left; margin: 5px 10px;">
							<label>年款：</label>
							<select id="select-year" style="width: 120px; height: 28px;"></select>
						</div>
						<div style="float: left; margin: 5px 10px;">
							<label>车型：</label>
							<select id="select-car" style="width: 120px; height: 28px;"></select>
						</div>
					</div>
					<div style="text-align: center; height: 30px; line-height: 30px;">
						<a class="easyui-linkbutton" icon="icon-add" onclick="addAccAndCarShip()">添加关联</a>
					</div>
				</div>
				<div style="padding: 10px; text-align: center; height: 200px; overflow-y: scroll;min-height: 300px;">
					<table id="table-car-index">
						<thead>
							<tr>
								<th><span>厂商</span></th>
								<th><span>车系</span></th>
								<th><span>年款</span></th>
								<th><span>车型</span></th>
								<th><span>操作</span></th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
				</div>
				<div style="text-align: center; height: 30px; line-height: 30px;">
					<a class="easyui-linkbutton" icon="icon-save" onclick="saveAccessory()">保存</a>
					<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-accessory').window('close')">取消</a>
				</div>
			</div>
			<div title="配件参数信息">未选择配件类型或该配件类型下未配置配件参数</div>
		</div>
	</div>
	<div id="table-acc-advance-searcher"></div>
</body>
</html>