<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
			Long time = System.currentTimeMillis();
			request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置保养商品</title>
<link type="text/css"
	href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}"
	rel="stylesheet" />
<style type="text/css">
.defaultGoodsTable{border:solid #0083CE; border-width:1px 0px 0px 1px;}
.defaultGoodsTable td,.defaultGoodsTable th{border:solid #0083CE; border-width:0px 1px 1px 0px; padding:10px 0px;text-align:center;}
</style>
</head>
<body>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/goodsMng/setMaintainGoods.js?v=${versionVal}"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/select2/select2.min.js"></script>
	<link type="text/css"	href="${path}/resources/styles/select2/select2.min.css"	rel="stylesheet" />
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">设置保养商品</div>
			<div class="clr"></div>
			<div class="lineh b4">
				<form id="maintain_search_form">
					<table width="100%" cellspacing="0" cellpadding="0" border="0"
						style="height: 100px;">
						<tr>
							<td align="right">选择品牌：</td>
							<td><select id="car_brand_qry" name="carBrand"
								style="width: 160px;">
							</select></td>
							<td align="right">选择厂商：</td>
							<td><select id="car_factory_qry" name="carFactory"
								style="width: 160px;">
							</select></td>
						</tr>
						<tr>
							<td align="right">选择车系：</td>
							<td><select id="car_dept_qry" name="carDept"
								style="width: 160px;">
							</select></td>
							<td align="right">选择车型：</td>
							<td><select id="car_type_qry" name="carId"
								style="width: 160px;">
							</select> <input type="hidden" name="invert"></td>
						</tr>
						<tr>
							<td align="right">默认商品：</td>
							<td><select id="default_goods" name="num" style="width: 160px;">
								<!-- <option value="-1">--请选择--</option>
								<option value="1">已设置</option>
								<option value="0">未设置</option> -->
							</select></td>
							<td></td>
							<td><button id="btnsearch" class="btn search1" type="button" onclick="searchMaintain()">
								<span><span>搜索</span></span>
							</button><button  class="btn search1" type="button" onclick="clearFun()">
								<span><span>清空</span></span>
							</button></td> 
						</tr>
						<!-- <tr style="margin-top:10px;">
						    <td></td><td></td><td></td>
							<td><button id="btnsearch" class="btn search1" type="button" onclick="searchMaintain()">
								<span><span>搜索</span></span>
							</button><button  class="btn search1" type="button" onclick="clearFun()">
								<span><span>清空</span></span>
							</button></td> 
						</tr> -->
					</table>
				</form>
			</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0"
				class="gridlist">
				<tbody>
					<tr>
						<th width="20%" 	style="border-left: 1px solid #f2f2f2;">车型</th>
						<th width="8%">保养里程(KM)</th>
						<th width="8%">保养时间(月)</th>
						<th width="26%">官方保养项目</th>
						<th width="20%">YOYO保养项目</th>
						<th width="8%">默认商品</th>
						<th width="10%">操作</th>
					</tr>
					
				</tbody>
			
			</table>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
		</div>
	</div>
	
	<div id="defaulGoodsWindow" class="easyui-window" title="选择默认商品"  closed="true"  style="width:600px;height:616px;" data-options="cache:false,minimizable:false,collapsible:false,modal:true">
		<table width="100%" cellspacing="0" cellpadding="0" border="1" class="defaultGoodsTable">
				<tbody>
					<tr>
						<th width="20%" 	style="border-left: 1px solid #f2f2f2;">保养项目</th>
						<th width="20%" 	style="border-left: 1px solid #f2f2f2;">保养配件</th>
						<th width="40%">默认商品</th>
						<th width="20%">操作</th>
					</tr>
				</tbody>
			
		</table>
	 </div>
	
		<!-- 选择默认货品BEGIN -->
		<div id="accessoryDefaultWindow" class="easyui-window" title="选择默认货品"  closed="true"  style="width:60%;height:616px;">
			<div style="padding: 5px;" class="datagrid-toolbar" id="product_sub_toolbar" >
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="confirmAccessoryGoods()"> 确认选择</a>
			</div>
			<div class="zTreeDemoBackground left">
				<table id="productDefaultTable" style="marge: 10px auto; width: 100%;"></table>
			</div>
		 </div>
		<!-- 选择默认货品 END -->	
		<!-- 保存需要提交的数据 -->
		<form id="submintform">
			<input type="hidden" id="maintainId" name="maintainId" >  <!-- 保养周期id -->
			<input type="hidden" id="maintainItemId" name="maintainItemId">  <!-- 保养项目配件所在表的id ，即：t_car_maintain_accessory的id-->
			<input type="hidden" id="productId" name="productId">  <!-- 默认货品id -->
		</form>
		
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?t=${time}"></script>
	<script type="text/javascript">
		total = "${goodslist.total}";
		rows = "${goodslist.pageSize}";
	</script>
</body>
</html>

