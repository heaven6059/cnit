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
<title>商品规格编辑</title>
</head>
<body>
	<script type="text/javascript">
		var _goodsId = '${goodsId}';
	</script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/good/goodSpecEditIndex.js?v=${versionVal}"></script>
	<link type="text/css" href="${path}/resources/scripts/biz/good/goodSpecEditIndex.css?v=${versionVal}" rel="stylesheet" />
	<div class="spec-panel">
		<div class="clearfix">
			<div class="specs"></div>
			<div class="spec-value">
				<div class="spec-toolbar">
					全选：<input type="checkbox" />
				</div>
				<div class="spec-values-list"></div>
				<div class="spec-value-table">
					<table width="100%"></table>
				</div>
			</div>
		</div>
		<div class="spec-action">
			<button type="button" id="spec-action-gen-prod" class="btn btn-primary">生成所有货品</button>
		</div>
		<div class="product-table">
			<table width="100%">
				<thead class="product-table-head">
					<tr>
						<th>上架</th>
						<th>规格值</th>
						<th>图片</th>
						<th>货号</th>
						<th><em><font color="red">*</font></em>库存</th>
						<th><em><font color="red">*</font></em>销售价</th>
						<th>成本价</th>
						<th>市场价</th>
						<th>货位</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody class="product-table-body"></tbody>
			</table>
		</div>
		<div class="spec-panel-action">
			<button type="button" id="spec-action-cache-prod" class="btn btn-primary">保存</button>
		</div>
	</div>
</body>
</html>