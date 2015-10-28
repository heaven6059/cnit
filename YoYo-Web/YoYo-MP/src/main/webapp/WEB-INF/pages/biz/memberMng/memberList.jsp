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
<title>会员列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/member/memberList.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/common/yoyo.js?v=${versionVal}"></script>
</head>
<body>
	<!-- <div region="north" style="height: 30px;">
		<a href="#" class="easyui-splitbutton" onclick="turnChannel()">全部</a>
		<a href="#" class="easyui-splitbutton" onclick="turnChannel('2')">APP平台</a>
		<a href="#" class="easyui-splitbutton" onclick="turnChannel('1')">WEB平台</a>
	</div> -->
	<div id="toolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add"  onclick="newUser()"> 添加会员 </a>
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit"  onclick="editUser()"> 群发邮件</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"  onclick="destroyUser()">群发站内信 </a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove"  onclick="destroyUser()"> 群发短信 </a>-->
		<!-- <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" onclick="destroyUser()"> 删除 </a> -->
		<a id="member-ads-button" href="javascript:void(0)" class="easyui-linkbutton">高级筛选</a>
	</div>
	<table id="dg" style="marge: 10px auto;">
	</table>
	<div id="dlg" class="easyui-dialog" style="width: 500px; height: 380px; padding: 10px 20px" closed="true"
		buttons="#dlg-buttons">
		<form id="fm" class="easyui-form" method="post"  >
			<table class="table-form">
				<tr>
					<td><label>用户名</label></td>
					<td><input name="loginName" class="easyui-validatebox" data-options="required:true" validType="maxLength[16]" /></td>
				</tr>
				<tr>
					<td><label>密码</label></td>
					<td><input id="password" name="password" class="easyui-validatebox" type="password" validType="length[8,16]"  data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>确认密码</label></td>
					<td><input name="checkpassword" class="easyui-validatebox" type="password"   validType="equalTo['#password']"  data-options="required:true" validType="length[8,16]" ></td>
				</tr>
				<tr>
					<td><label>企业账户</label></td>
					<td><select class="easyui-combobox" name="isEtps"  data-options="required:true">
							<option value="0">否</option>
							<option value="1">是</option>
						</select></td>
				</tr>
				<tr>
					<td><label>Email</label></td>
					<td><input name="email" class="easyui-validatebox"   validType="email" data-options="required:true" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dlg-buttons" style="width:320px !important;">
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveUser()" >保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" >取消</a>
	</div>
	<div id="dt"  title="编辑会员" class="easyui-dialog" style="width: 1000px;; height:580px; padding: 10px 10px" data-options="closed:true,modal:true,cache:false"></div>
	</div>
	<div id="table-member-advance-searcher"></div>
</body>
</html>
