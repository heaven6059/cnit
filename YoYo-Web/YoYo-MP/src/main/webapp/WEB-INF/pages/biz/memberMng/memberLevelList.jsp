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
<title>会员等级</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
	<script type="text/javascript" src="${path}/resources/scripts/biz/member/memberLevelList.js"></script>
	<div id="toolbar-memberLevel">
		<!-- <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newMemberLevel()"> 添加会员等级</a> -->
		<!-- <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteMemberLevel()"> 删除 </a> -->
	</div>
	<table id="table-memberLevel" style="marge: 10px auto;"></table>
	<div id="dialog-memberLevel" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
		<form id="fm" class="easyui-form" method="post" data-options="novalidate:true">
			<table>
				<tr>
					<td><label>等级编号：</label></td>
					<td><input name="memberLvId" class="easyui-textbox" data-options="readonly:true" /></td>
				</tr>
				<tr>
					<td><label>等级名称：</label></td>
					<td><input name="name" class="easyui-textbox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>等级类型：</label></td>
					<td><select class="easyui-combobox" name="type" data-options="required:true">
							<option value="1">个人会员</option>
							<option value="2">企业会员</option>
					</select></td>
				</tr>
				<tr>
					<td><label>是否为会员默认等级：</label></td>
					<td><select class="easyui-combobox" name="isDefault" data-options="required:true">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>
				<tr>
					<td><label>会员折扣率：</label></td>
					<td><input name="discountRate" class="easyui-textbox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>所需积分：</label></td>
					<td><input name="point" class="easyui-textbox" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>所需经验值：</label></td>
					<td><input name="experience" class="easyui-textbox" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons">
		<a class="easyui-linkbutton c6" iconCls="icon-ok" onclick="savaOrUpdateMemberLevel()" style="width: 90px">保存</a>
		<a class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dialog-memberLevel').dialog('close')" style="width: 90px">取消</a>
	</div>
</body>
</html>
