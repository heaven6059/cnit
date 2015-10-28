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
<title>店铺审核列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/shop/shopCheckIndex.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/shop/shopCheckIndex.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
</head>
<body>
	<div id="toolbar">
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="shopCheck()"> 店铺审核 </a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteCheck()"> 删除 </a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editUser()"> 群发邮件</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"  onclick="destroyUser()">群发站内信 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"  onclick="destroyUser()"> 群发短信 </a>-->
		
		<a id="member-ads-button" href="javascript:openAdvaceQuery('advance_search_shopcheck')" class="easyui-linkbutton" iconCls="icon-search">高级筛选</a>
	</div>
	<table id="shopCheckDataGrid" style="marge: 10px auto;">
	</table>
	<input type="hidden" id="datagrid_index" > <!-- 点击的父级datagrid的index -->
	<input type="hidden" id="companyId" >
	
	<div id="window_shop_check" class="easyui-window" title="店铺审核"
		closed="true" style="height: 400px; width: 600px;"
		data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

		<form id="form_shop_check" >
			<input type="hidden" name="gradeId">
			<table class="add_brand_table">
			<tr>
					<td align="right"><span style="color: red;">*</span>同意修改审核：</td>
					<td><select name="approved" style="width: 90px;" class="tab_input"  id="approved">
						<!-- <option value="0">待审核</option> -->
						<option value="1">审核通过</option>
						<option value="2">审核未通过</option>
					</select></td>
				</tr>
				<tr>
					<td align="right">备注：</td>
					<td>
					<textarea cols="50" rows="6" name="approvedremark" id="approvedremark" placeholder="最多输入1000个字符"></textarea></td>
				</tr>
				<tr id="checkTr" style="display:none;"><td align="right"><span style="color: red;">*</span>商品是否审核：</td>
					<td><input type='radio' name="isCheck" id="isCheck" value="1" >
						<label for="isCheck">是</label>
					<input type='radio' name="isCheck" id="l2" value="0" style="margin-left:10px;" checked="checked"><label for="l2">否</label></td>
				</tr>
			    <tr id="cateTr" style="display:none;">
			    	<td align="right"><span style="color: red;">*</span>分类审核：</td>
			    	<td><select id="combox-cate"  name="cateIds" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select></td>
			    </tr>
			</table>
		</form>

		<div region="south" border="false"
			style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
			<a class="easyui-linkbutton" id="shop_check_btn" icon="icon-save"
				onclick="saveShopCheck()" >保存</a>  <a
				class="easyui-linkbutton"  icon="icon-cancel"
				onclick="javascript:closeDailog('window_shop_check');" style="margin-left: 15px;">取消</a>
		</div>
	</div>	
		<!-- 高级刷选 -->
	<div id="advance_search_shopcheck" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="shopcheck_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
			<td align="right"><span>公司名称: </span></td>
			<td><input id="companyName" name="companyName"
				class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>店铺名称: </span></td>
					<td><input  name="storeName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>店主姓名: </span></td>
					<td><input  name="shopName"
						class="easyui-textbox search_class" /></td>
				</tr>
				
				<tr>
					<td align="right"><span>营业执照经营范围: </span></td>
					<td><input  name="companyRemark"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>审核状态: </span></td>
					<td><select name="approved" style="width: 95px;" class="tab_input"  >
						<option value="0">待审核</option>
						<option value="2">审核未通过</option>
					</select></td>
				</tr>
				
				
				<tr>
					<td align="right"><span>审核通过时间: </span></td>
					<td><input  name="approvedTimeStart" class="easyui-datebox" style="width: 95px;"/>~
					<input  name="approvedTimeEnd"	class="easyui-datebox" style="width: 95px;"/></td>
				</tr>
				<tr>
					<td align="right"><span>申请时间: </span></td>
					<td><input  name="applyTimeStart" class="easyui-datebox" style="width: 95px;"/>
					~<input  name="applyTimeEnd"class="easyui-datebox" style="width: 95px;"/></td>
				</tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('shopCheckDataGrid','shopcheck_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="search_clear('shopcheck_search_form','shopCheckDataGrid')" style="width: 90px">清除条件</a>
    </div>
	
</body>
</html>
