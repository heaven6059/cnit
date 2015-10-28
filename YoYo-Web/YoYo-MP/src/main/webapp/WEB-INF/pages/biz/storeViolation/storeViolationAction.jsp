<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
	<title>店铺违规</title>
	<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
	<script type="text/javascript" src="${path}/resources/scripts/biz/storeViolation/storeViolationAction.js"></script>    
</head>
<body>
    <div id="toolbar-store-violation">
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteStoreViolation()"> 删除 </a>
        <a href="javascript:;" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openSeachWindow()">高级筛选</a>
    </div>
    <table id="table-store-violation"></table>

	<div id="advance_search_store_violation" class="easyui-dialog" title="高级筛选" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="store_violation_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tbody>
		            <tr>
	                    <td width="35%" align="right">违规类型：</td>
	                    <td>
	                    	<select id="search-combox-violation-cat" style="width: 120px;" name="catId"></select>
	                    </td>
	                </tr>
					<tr>
						<td align="right"><label><span>支付违约金：</span></label></td>
						<td>
							<div style="float: left;">
							<select name="earnestMoneySearchType"><option value="gt">大于</option>
								<option value="ge">大于等于</option>
								<option value="eq">等于</option>
								<option value="le">小于等于</option>
								<option value="lt">小于</option>
								<option value="bt">介于</option>
							</select>
							</div>
							<div class="nbt" style="float: left;">
							<input type="text" name="earnestMoney" style="width: 120px;" class="easyui-numberbox" data-options="precision:2,min:0" />&nbsp;&nbsp;万元
							</div>
							<div class="bt" style="display:none;float: left;">
								<input type="text" name="earnestMoney1" style="width: 60px;" class="easyui-numberbox" data-options="precision:2,min:0" />
								至
								<input type="text" name="earnestMoney2" style="width: 60px;" class="easyui-numberbox" data-options="precision:2,min:0" />&nbsp;&nbsp;万元
							</div>
						</td>
					</tr>
		            <tr>
						<td align="right">下架店铺商品时间：</td>
						<td>
							<input type="text" name="goodsdownStarttime" class="easyui-datebox" required="required"></input>
							<br>至
							<input type="text" name="goodsdownEndtime" class="easyui-datebox" required="required"></input>
						</td>
					</tr>
					
					<tr>
						<td align="right">限制发布商品时间：</td>
						<td>
							<input type="text" name="goodsStarttime" class="easyui-datebox" required="required"></input>
							<br>至
							<input type="text" name="goodsEndtime" class="easyui-datebox" required="required"></input>
						</td>
					</tr>
					
					<tr>
						<td align="right">店铺屏蔽时间 ：</td>
						<td>
							<input type="text" name="storeStarttime" class="easyui-datebox" required="required"></input>
							<br>至
							<input type="text" name="storeEndtime" class="easyui-datebox" required="required"></input>
						</td>
					</tr>
					
					<tr>
						<td align="right">关闭店铺时间：</td>
						<td>
							<input type="text" name="storeDownStartTime" class="easyui-datebox" required="required"></input>
							<br>至
							<input type="text" name="storeDownEndTime" class="easyui-datebox" required="required"></input>
						</td>
					</tr>
					
					
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('table-store-violation','store_violation_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="clearCondition('store_violation_search_form')" style="width: 90px">清除条件</a>
    </div>
	
</body>
</html>
