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
<title>钣金订单列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/orderList.js"></script>
</head>
<body>
	<div id="tabs-order" class="easyui-tabs">
		<div title="全部" selected="true"></div>
		<div title="待付款"></div>
		<div title="待确认"></div>
		<div title="待安装"></div>
		<div title="已安装"></div>
		<div title="已完成"></div>
		<div title="已取消"></div>
		<div title="已删除"></div>
	</div>
	<div id="panel-goods">
		<div id="toolbar-order-list">
			<a href="#" class="easyui-linkbutton" style="width: 80px;" id="export" title="导出勾选的订单">导出</a>
			<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" style="width: 80px;" id="exportAll" title="全量导出当前状态订单">全量导出</a>
			<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_order')" title="当前状态中高级筛选">高级筛选</a>
		</div>
		<form id="excelForm" method="post" target="_blank">
			<input type="hidden" id="ids" name="ids"/>
			<input type="hidden" id="status" name="status"/>
			<input type="hidden" id="id" name="id"/>
			<input type="hidden" id="payStatus" name="payStatus"/>
			<input type="hidden" id="paymentId" name="paymentId"/>
			<input type="hidden" id="storeId" name="storeId"/>
			<input type="hidden" id="storeName" name="storeName"/>
			<input type="hidden" id="name" name="name"/>
			<input type="hidden" id="minPayed" name="minPayed"/>
			<input type="hidden" id="maxPayed" name="maxPayed"/>
			<input type="hidden" id="startDate" name="startDate"/>
			<input type="hidden" id="endDate" name="endDate"/>
		</form>
		<table id="table-order-list"></table>
	</div>
	
	<!-- 高级查询 -->
	<div id="advance_search_order" class="easyui-dialog advance_search" title="高级筛选" style="padding: 10px 20px; background: #F5F5F5;" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="search_form" class="easyui-form" method="post" data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right"><span>订单号: </span></td>
					<td><input id="id" name="id" class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
                    <td align="right">付款状态：</td>
                    <td>
                    	<select name="payStatus" id="payStatus" style="width: 160px;" class="search_class">
	                    	<option value="" selected="selected">--请选择--</option>
	                    	<option value="0">未支付</option>
	                        <option value="1">已支付</option>
	                        <option value="2">已付款至担保方</option>
	                        <option value="3">部分付款</option>
	                        <option value="4">部分退款</option>
	                        <option value="5">全额退款</option>
	                    </select>
	                </td>
                </tr>
				<tr>
					<td align="right"><span>支付方式: </span></td>
					<td>
						<select id="paymentId" name="paymentId"  style="width: 160px;" class="search_class">
							<option value="" selected="selected">--请选择--</option>
							<option value="0">在线支付</option>
	                        <option value="1">到店支付</option>
	                    </select>
	                </td>
				</tr>
				<tr>
					<td align="right"><span>店铺Id: </span></td>
					<td><input id="storeId" name="storeId" class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>店铺名称: </span></td>
					<td><input id="storeName" name="storeName" class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>买家账号: </span></td>
					<td><input id="name" name="name" class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>交易金额: </span></td>
					<td style="text-align: left;">
						<input name="minPayed" id="minPayed" type="text" class="easyui-numberbox" precision="2"  size="8" maxlength="8" max="9999999.99" style="text-align:right;"/>
						至
						<input name="maxPayed" id="maxPayed" type="text" class="easyui-numberbox" precision="2"  size="8" maxlength="8" max="9999999.99" style="text-align:right;"/>
					</td>
				</tr>
				<tr>
					<td align="right"><span>交易日期: </span></td>
					<td style="text-align: left;">
						<input id="startDate" name="startDate" type="text" class="easyui-datebox" />
						至
						<input id="endDate" name="endDate" type="text" class="easyui-datebox" />
					</td>
				</tr>
				<!-- <tr>
					<td align="right"><span>退款状态: </span></td>
					<td>
						<select id="refundStatus" style="width: 160px;" class="search_class">
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
                   		</select>
                   	</td>
				</tr> -->
			</table>
		</form>
	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="advanceSearch()" style="width: 90px">立即筛选</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="searchClear()" style="width: 90px">清除条件</a>
	</div>
</body>
</html>