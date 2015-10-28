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
<title>商品列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/good/goodsIndex.js?v=${versionVal}"></script>
<style>
	a:link{text-decoration:none;}
	a {color: #00E; }
	a:visited{text-decoration:none;}
	a:hover{text-decoration:underline;} 
	.datagrid-cell-rownumber {line-height:50px; width: 25px;height:50px; text-align: center;margin: 0;padding: 0 ! important}
}
</style>
</head>
<body>
	<div id="tabs-goods" class="easyui-tabs">
		<div title="全部" selected="true"></div>
		<div title="已上架商品"></div>
		<div title="已下架/待上架商品"></div>
		<div title="待审核商品"></div>
		<div title="审核不通过商品"></div>
	</div>
	<div id="panel-goods">
		<div id="toolbar-goods">
			<a href="#" class="easyui-linkbutton" style="width: 80px;" id="upBtn">上架</a>
			<a href="#" class="easyui-linkbutton" style="width: 80px;" id="downBtn">下架</a>
			<a href="#" class="easyui-linkbutton" style="width: 80px;" id="checkBtn">审核</a>
			<a href="#" class="easyui-linkbutton" style="width: 80px;" id="editBtn">编辑</a>
			<a href="#" class="easyui-linkbutton" style="width: 80px;" id="delBtn">删除</a>
			<!-- <a href="#" class="easyui-splitbutton" data-options="menu:'#toolbar-goods-batch-edit'" style="width: 80px;">批量操作</a> -->
			<!-- <a href="#" class="easyui-splitbutton" data-options="menu:'#toolbar-goods-tag'" style="width: 80px;">标签</a> -->
			<!-- <a href="javascript:$(this).goodsIndex('doBatchDelete')" class="easyui-linkbutton" style="width: 80px;">删除</a> -->
			<!-- <a href="#" class="easyui-linkbutton" style="width: 80px;">导出</a>
			<a href="#" class="easyui-linkbutton" style="width: 80px;">导入</a> -->
			<a id="acc-good-ads-button" class="easyui-linkbutton" style="width: 80px;">高级筛选</a>
			<!-- <div id="toolbar-goods-batch-edit" style="width: 150px;">
				<div>商品上架</div>
				<div>商品下架</div>
				<div class="menu-sep"></div>
				<div>商品降权</div>
				<div>重新生成图片</div>
			</div> -->
		<!-- 	<div id="toolbar-goods-tag" style="width: 150px;">
				<div>为选中项打标签</div>
				<div class="menu-sep"></div>
				<div>标签设置</div>
			</div> -->
		</div>
		<table id="table-goods"></table>
	</div>
<!-- <div id="window-goods-edit" class="easyui-window" title="编辑商品" closed="true" data-options="inline:true,modal:true" style="width: 1000px; height: 600px"></div>
	<div id="window-product-edit"></div>
	<div id="window-goods-review"></div> -->
	<div id="table-good-advance-searcher"></div>
	
	<div id="checkWindow" class="easyui-dialog" title="审核商品" closed="true" style="width:500px; height: 200px; padding: 2px;">
		<div style="padding:5px;text-align:center;">
			 <div style="text-align:left;">审核备注:&nbsp;&nbsp;<textarea rows="5" cols="40" id="cause"></textarea><br/><br/></div>
			 
			<a href="#" class="easyui-linkbutton" icon="icon-ok" id="checkok">审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" icon="icon-cancel" id="checkcancel">审核不通过</a>
		</div>
	</div>
	
	<div id="downWindow" class="easyui-dialog" title="下架商品" closed="true" style="width:500px; height: 200px; padding: 2px;">
		<div style="padding:5px;text-align:center;">
			<div style="text-align:left;">下架违规原因:&nbsp;&nbsp;&nbsp;&nbsp;<textarea rows="5" cols="40" id="downcause"></textarea><br/><br/></div>
			<a href="#" class="easyui-linkbutton" icon="icon-ok" id="downcheckok">确认</a>&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
	</div>
	
</body>
</html>