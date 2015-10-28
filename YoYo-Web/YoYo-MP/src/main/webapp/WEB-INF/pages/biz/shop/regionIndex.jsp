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
<title>经营范围</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/shop/regionIndex.js"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript">
	$(function(){
		$('#form-region-add').walidator();
	});
	function expandAll() {
		$('#region-cate').treegrid('expandAll');
	}
	function collapseAll() {
		$('#region-cate').treegrid('collapseAll');
	}
	
	
</script>
</head>
<body>
	<div id="toolbar-region">
		<!-- <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newCate()"> 添加经营范围</a> -->
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="expandAll()"> 展开经营范围</a> 
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="collapseAll()"> 收起经营范围</a>
		<!-- <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editOrderNum()"> 编辑排序</a> -->
	</div>
	<table id="region-cate" style="marge: 10px auto;"></table>
	<!-- 经营范围编辑 -->
	<div id="window-region-add" class="easyui-window" closed="true" style="height: 420px; width: 560px;" data-options="inline:false,cache:false,onBeforeClose:clearDetail,minimizable:false,model:true">
			<form id="form-region-add"  WalidateTime="realtime">
				<table class="table-form" style="border: none;">
					<tr class="hide">
						<td align="right">
							<label>经营范围ID：</label>
						</td>
						<td>
							<input type="text" name="regionId" id="regionId" style="width: 200px;"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label>经营范围名称：<span style="color:red;">*</span></label>
						</td>
						<td>
							<input type="text" name="regionName" id="regionName" style="width: 200px;" class="walidator" walitype="{require:true}" disabled/>
						</td>
					</tr>
				<!-- 	<tr id="tr_parent_cat_id">
						<td align="right">
							<label>上级经营范围：</label>
						</td>
						<td>
							<select id="combox-parent-region" class="easyui-combogrid walidator" name="parentRegionId" style="width: 215px;padding: 3px 5px;height: 28px;"></select>
							<span>未选择上级经营范围时默认一级经营范围</span>
						</td>
					</tr> -->
					
						<tr > 
					<td align="right">商品分类关联：</td>
					<td><select id="combox-cat-virt-index"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select>
					</td>
				</tr>
				</table>
			</form>
			<div  style="text-align: center; height: 42px; line-height: 42px;margin-top:50px;">
				<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdateCate()">保存</a>
				<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-region-add').window('close')">取消</a>
			</div>
		</div>
</body>
</html>
