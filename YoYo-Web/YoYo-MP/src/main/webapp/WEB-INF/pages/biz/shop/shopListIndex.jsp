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
<title>店铺列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/shop/shopCheckIndex.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/shop/shopCheckIndex.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/shop/shopListIndex.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
</head>
<body>
	<div id="toolbar">
		<a href="${path}/shop/add"  iconCls="icon-add" id="addShop"  target="_blank" class="easyui-menubutton" data-options="plain:false"> 添加店铺 </a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="violationShop()"> 店铺违规 </a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="setShopTime()"> 店铺有效期 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteShop()"> 删除 </a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editUser()"> 群发邮件</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"  onclick="destroyUser()">群发站内信 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"  onclick="destroyUser()"> 群发短信 </a>-->
		
		<a id="member-ads-button" href="javascript:openAdvaceQuery('advance_search_shoplist')" class="easyui-linkbutton" iconCls="icon-search">高级筛选</a>
	</div>
	
	 <div id="shopTag">
		<div id="group">添加4S店</div>
		<div id="unitShop">添加快修店</div>
	</div>
	<table id="shopListDataGrid" style="marge: 10px auto;">
	</table>
	<input type="hidden" id="shop_datagrid_index" > <!-- 点击的父级datagrid的index -->
	<input type="hidden" id="shopCompanyId" >
	
	<div id="window_shop_date" class="easyui-window" title="店铺有效期"
		closed="true" style="height: 300px; width: 400px;"
		data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save'">

		<form id="form_shop_date" style="height:200px;">
			<table class="add_brand_table">
			<tr>
					<td align="right"><span style="color: red;">*</span>统一设置有效期：</td>
					<td><input type="text" style="width:120px;" id="lastTime" name="lastTime" class="tab_input  easyui-datebox" data-options="editable:false,required:true" missingMessage='此处填写有效期'></td>
				</tr>
				
			</table>
		</form>

		<div region="south" border="false"
			style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
			<a class="easyui-linkbutton" id="shop_check_btn" icon="icon-save"
				onclick="saveShopTime()" >保存</a>  <a
				class="easyui-linkbutton"  icon="icon-cancel"
				onclick="javascript:closeDailog('window_shop_date');" style="margin-left: 15px;">取消</a>
		</div>
	</div>	
		<!-- 高级刷选 -->
	<div id="advance_search_shoplist" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="shoplist_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
			<tr>
				<td align="right"><span>店铺类型: </span></td>
				<td><select name="issueType" style="width: 90px;" class="tab_input">
					<option value="0">单店</option>
					<option value="1">集团</option>
				</select></td>
			</tr>
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
            iconCls="icon-ok" onclick="advanceQuery('shopListDataGrid','shoplist_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="search_clear('shoplist_search_form','shopListDataGrid')" style="width: 90px">清除条件</a>
    </div>
    
    
    <!-- 新增违规-->
    <div id="window-add-violation" class="easyui-window" title="店铺违规"
        closed="true" style="height: 350px; width: 500px;"
        data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

        <form id="form-violation-add" class="easyui-form">
            <table class="add_brand_table">
            	<tr class="hide">
					<td><label>集团id：</label></td>
					<td><input type="text" name="companyId"  /></td>
				</tr>
				<tr>
                    <td align="right"><span style="color: red;">*</span>选择分店：</td>
                    <td>
                    	<select id="combox-store" type="text" multiple="multiple" style="width: 200px;" name="storeId" data-options="required:true"></select>
                    </td>
                </tr>
				<tr>
                    <td align="right"><span style="color: red;">*</span>选择违规类型：</td>
                    <td>
                    	<select id="combox-violation-cat" type="text" multiple="multiple" style="width: 200px;" name="catId" data-options="required:true"></select>
                    </td>
                </tr>
				<tr>
					<td align="right"><label><span style="color: red;">*</span>备注：</label></td>
					<td><textarea rows="4" cols="26" name="remark" data-options="required:true"></textarea> </td>
				</tr>
            </table>
        </form>

        <div region="south" border="false" style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-btn" icon="icon-save" onclick="saveStoreViolation()">保存</a>
            <a class="easyui-linkbutton" id="update-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-violation');" style="margin-left: 15px;">取消</a>
        </div>
    </div>
    
    
    
    <div id="window_shop_good_list" class="easyui-window" title="设置"
		closed="true" style="height: 300px; width: 460px;"
		data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

		<form id="form_shop_good_list" >
		    <input type="hidden" name="companyId" id="companyId">
			<table class="add_brand_table">
				<tr><td align="right"><span style="color: red;">*</span>商品是否审核：</td>
					<td><input type='radio' name="isCheck" id="isCheck" value="1" >
						<label for="isCheck">是</label>
					<input type='radio' name="isCheck" id="l2" value="0" style="margin-left:10px;" checked="checked"><label for="l2">否</label></td>
				</tr>
			    <tr id="catesTr" style="display:none;">
			    	<td align="right"><span style="color: red;">*</span>分类审核：</td>
			    	<td><select id="combox-cates"  name="cateIds" class="easyui-combotree" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select></td>
			    </tr>
			</table>
		</form>

		<div region="south" border="false"
			style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
			<a class="easyui-linkbutton" icon="icon-save"	onclick="saveShopGoodsCheck()" >保存</a>
			<a class="easyui-linkbutton"  icon="icon-cancel" onclick="javascript:closeDailog('window_shop_good_list');" style="margin-left: 15px;">取消</a>
		</div>
	</div>	
    
	
</body>
</html>
