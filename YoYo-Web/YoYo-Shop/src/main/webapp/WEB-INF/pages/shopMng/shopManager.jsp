<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城-店铺管理</title>
<link type="text/css" href="${path}/resources/styles/shopMng/shopManager.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>
	<script type="text/javascript"  src="${path}/resources/scripts/biz/shopMng/shopManager.js?r=${versionVal}"></script>
	<div class="shop_manager_right">
		<div class="title"><span style="float:left">店铺管理<span class="disc">|</span>
			<span class="disc add-icon"><a href="javascript:addStoreclick();">添加分店</a></span>
		</span> 
		</div>
		<!-- 添加分店 start -->
		<div id="addShop" class="addshop">
			<form id="addShop_form" >
				<table class="addShop_table">
					<tr><td><span style="color:red;">* </span>分店名称：</td><td><input type="text" name="storeName" id="storeName" class="easyui-validatebox" data-options="required:true" missingMessage='请填写分店名称'/></td></tr>
					<tr><td align="right"><span style="color:red;">* </span>分店地址：</td><td><select  style="width:120px;" id="area_1" name="area_1" class="easyui-combobox " data-options="valueField:'id',textField:'text',editable:false,required:true, panelHeight:'auto',panelWidth:'120'" missingMessage='请选择省市地区'></select>
						<input type="text" style="width:120px;" id="area_2" name="area_2" class="easyui-combobox "  data-options="valueField:'id',textField:'text',required:true, panelHeight:'auto',editable:false,panelWidth:'120'" missingMessage='请选择省市地区'>
						<input type="text" style="width:120px;" id="area_3" name="area_3" class="easyui-combobox" data-options="valueField:'id',textField:'text', panelHeight:'auto',editable:false,panelWidth:'120'" missingMessage='请选择省市地区'>
					</td></tr> 
					<tr><td></td><td><input type="text" id="addr" name="addr" class="easyui-validatebox" data-options="required:true" missingMessage='请填写详细地址'></td></tr>
				</table>
				<input type="hidden" name="storeId" />
				<input type="hidden" name="limitStore" id="limitStore" /> <!-- 已经开店的个数 -->
			</form>
			 <a href="#" onclick="javascript:saveStore()" class="save_btn addshop-form-a"></a>
			 <a href="javascript:$('#addShop').hide();"  class="cancel_btn "></a>	
		</div>
		<!-- 添加分店 end -->
		<table id="shoplist_dg"  style="margin: 10px auto;" ></table>
		
		
	</div>
	<script type="text/javascript">
		initShopListDg();
	</script>
</body>
</html>