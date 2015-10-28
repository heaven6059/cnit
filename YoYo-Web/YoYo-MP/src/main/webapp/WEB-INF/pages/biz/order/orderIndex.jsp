<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/orderIndex.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/orderDetail.js?v=${versionVal}"></script>
</head>
<body>
	<div id="tabs-order" class="easyui-tabs">
		<div title="全部" selected="true"></div>
		<div title="未处理"></div>
		<div title="已付款未安装"></div>
		<div title="已完成"></div>
		<div title="已退款"></div>
		<div title="已取消"></div>
	</div>
	<div id="panel-goods">
		<div id="toolbar-order-list">
			<!-- <a href="#" class="easyui-splitbutton" data-options="menu:'#toolbar-roder-tag'" style="width: 80px;" >标签</a> -->
			<a href="#" class="easyui-linkbutton" style="width: 80px;" >导出</a>
			<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_order')">高级筛选</a>
			<!-- <div id="toolbar-roder-tag" style="width: 150px;">
				<div>为选中项打标签</div>
				<div class="menu-sep"></div>
				<div>标签设置</div>
			</div> -->
		</div>
		<table id="table-order-list"></table>
	</div>
	
	<!-- 高级查询 -->
	<div id="advance_search_order" class="easyui-dialog advance_search" title="高级筛选"
		style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
		data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				
				<tr>
					<td align="right"><span>订单号: </span></td>
					<td><input id="orderId" style="width: 160px;"  name="orderId"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
                    <td align="right">订单状态：</td>
                    <td><select id="status" style="width: 160px;" class="search_class">
                    	<option value="-1" selected="selected">--请选择--</option>
                    	<option value="active">活动订单</option>
                        <option value="dead">已作废</option>
                        <option value="finish">已完成</option>
                        <option value="unconfirm">待确认</option>
                        <option value="create">创建订单</option>
                        <option value="uninstall">未安装</option>
                        <option value="install">安装中</option>
                    </select></td>
                </tr>
				<tr>
                    <td align="right">付款状态：</td>
                    <td><select id="payStatus" style="width: 160px;" class="search_class">
                    	<option value="-1" selected="selected">--请选择--</option>
                    	<option value="0">未支付</option>
                        <option value="1">已支付</option>
                        <option value="2">已付款至担保方</option>
                        <option value="3">部分付款</option>
                        <option value="4">部分退款</option>
                        <option value="5">全额退款</option>
                        <option value="6">到店支付</option>
                    </select></td>
                </tr>
				<tr>
					<td align="right"><span>支付方式: </span></td>
					<td><select id="payment" style="width: 160px;" class="search_class">
						<option value="-1" selected="selected">--请选择--</option>
						<option value="在线支付">在线支付</option>
                        <option value="到店支付">到店支付</option>
                    </select></td>
				</tr>
				<tr>
					<td align="right"><span>订单类型: </span></td>
					<td><select id="orderType" style="width: 160px;" class="search_class">
						<option value="-1" selected="selected">--请选择--</option>
						<option value="entity">实体物品订单</option>
                        <option value="virtual">虚拟物品订单</option>
                    </select></td>
				</tr>
				<tr>
					<td align="right"><span>平台来源: </span></td>
					<td><select id="source" style="width: 160px;" class="search_class">
						<option value="-1" selected="selected">--请选择--</option>
						<option value="pc">标准平台</option>
                        <option value="wap">手机触屏</option>
                        <option value="weixin">微信等商城</option>
                    </select></td>
				</tr>
				<tr>
					<td align="right"><span>退款状态: </span></td>
					<td><select id="refundStatus" style="width: 160px;" class="search_class">
						<option value="-1" selected="selected">--请选择--</option>
						<option value="0">未申请退款</option>
                        <option value="1">退款申请中,等待卖家审核</option>
                        <option value="2">卖家拒绝退款</option>
                        <option value="3">卖家同意退款,等待买家退货</option>
                        <option value="4">卖家已退款</option>
                        <option value="5">买家已退货,等待卖家确认收货</option>
                        <option value="6">卖家不同意协议,等待买家修改</option>
                        <option value="7">买家已退货,卖家不同意协议,等待买家修改</option>
                        <option value="8">平台介入,等待卖家举证</option>
                        <option value="9">平台介入,等待平台处理</option>
                        <option value="10">平台介入已处理</option>
                        <option value="11">卖家同意退款，等待卖家打款至平台</option>
                        <option value="12">卖家已退款，等待系统结算</option>
                    </select></td>
				</tr>
				
			</table>
		</form>

	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok"
			onclick="advanceSearch()"
			style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="advance_clear()" style="width: 90px">清除条件</a>
	</div>
	
	<!-- <div id="window-goods-edit"></div>
	<div id="window-product-edit">
		<div class="easyui-layout" fit="true">
			<div region="west"></div>
			<div region="center"></div>
			<div region="south"></div>
		</div>
	</div>
	<div id="window-goods-review">
		<div class="easyui-layout" fit="true">
			<div region="west"></div>
			<div region="center"></div>
			<div region="south"></div>
		</div>
	</div> -->
</body>
</html>