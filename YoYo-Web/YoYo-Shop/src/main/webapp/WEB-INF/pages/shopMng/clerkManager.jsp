<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店员管理</title>
<link type="text/css" href="${path}/resources/styles/shopMng/shopManager.css?r=${versionVal}" rel="stylesheet" />
</head>
<body>

<script type="text/javascript" src="${path}/resources/scripts/biz/shopMng/clerkManager.js?r=${versionVal}"></script>
<script type="text/javascript">
var companyType = '${companyType}';  //公司类型
$(function(){
	
	DataGrid();
	$("#addClerk").dialog({onBeforeOpen:function(){
		$("#password_tr1").hide();
		$("#password_tr2").hide();
		$("#email_tr3").hide();
		$("#store_tr").hide();		
		$("#memberId").val('');
	}});
	
	$("#editClerk").dialog({onBeforeOpen:function(){
		$("#edit_store_tr").hide();
		$("#attachId").val('');
	}});
});

</script>

	<div class="shop_manager_right">
		<div class="title"><span style="float:left">店员管理<span class="disc">|</span>
			<span class="disc add-icon"><a href="javascript:add();">+添加新店员</a></span>
		</span> 
		</div>
	
	<table id="clerk_datagrid" ></table>
	</div>
	<div id="addClerk" class="easyui-dialog" title="新增店成员" align="center" style="width: 400px; height: 330px; padding: 10px 20px" data-options="closed:true,modal:true,cache:false" >
		 <form  id="addClerk_form" class="easyui-form" method="post" > 
		 <table id="add_table" class="addclerk_table">
			<tr>
				<td>用户名:</td>
				<td><input type="text" id="loginName" onblur="checkloginName()" class="easyui-validatebox" name="loginName" data-options="required:true" validType="userName"/></td>
			</tr>
			<div id="checkname"></div>
			<tr id="password_tr1" style="display:none;">
				<td>密码：</td>
				<td><input type="password" id="password1" name="password1" class="easyui-validatebox" validType='password'  data-options="required:true"/>
			</tr>	
			<tr id="password_tr2" style="display:none;">
				<td>确认密码：</td>
				<td><input type="password" id="password2" name="password2" class="easyui-validatebox" validType='password' data-options="required:true" onblur="checkPass('password1','password2');"/>
			</tr>	
			<div id="hint"> </div>
			<tr id="email_tr3">
				<td>Email:</td>
				<td><input id="email" name="email" class="easyui-validatebox" validType='email' data-options="required:true"/></td>
			</tr>
			<div id="spantest"></div>
			<tr id='store_tr'>
				<td>分店名称 :</td>
				<td><input type="text" class="easyui-combobox" name="storeId" id="storeId" data-options="valueField:'id',textField:'text'" /></td>
			</tr>
			<tr>
				<td>角色 :</td>
				<td><input type="text" class="easyui-combobox" name="roleId" id="rolesName" data-options="valueField:'id',textField:'text'"/></td>
			</tr>
			<input type="hidden" id="memberId" name="memberId"  />
			<input type="hidden" id="companyId" name="companyId" value="${sessionScope.companyId}"  />				
		 </table>
		 	 <a href="#" onclick="javascript:saveClerk()" class="save_btn addshop-form-a" style="margin-left: 40px;"></a>
			 <a href="javascript:cancel();"  class="cancel_btn "></a>	
		</form>
	</div>
	
	
	
	<div id="editClerk" class="easyui-dialog"  align="center" style="width: 400px; height: 280px; padding: 10px 20px" data-options="closed:true,modal:true,cache:false" >
		 <form  id="editClerk_form" class="easyui-form" method="post" data-options="novalidate:true"> 
		 <table class="addclerk_table">
		 	<div id="edit_hint"> </div>
			<tr>
				<td>当前用户名:</td>
				<td><span id="cur_loginname"></span>
			</tr>
			<tr id='edit_store_tr'>
				<td>分店名称 :</td>
				<td><input type="text" class="easyui-combobox" name="storeId" id="edit_storeId" data-options="valueField:'id',textField:'text'" /></td>
			</tr>
			<tr >
				<td>角色名 :</td>
				<td><input type="text" class="easyui-combobox" name="roleId" id="rolesName1" data-options="valueField:'id',textField:'text'"/></td>
			</tr>			
		</table>
			 <a href="#" onclick="javascript:save()" class="save_btn addshop-form-a" style="margin-left: 40px;"></a>
			 <a href="javascript:cancel();"  class="cancel_btn "></a>	
			 <input type="hidden" id="attachId" name="attachId"  />	
		</form>
	</div>

</body>
</html>

