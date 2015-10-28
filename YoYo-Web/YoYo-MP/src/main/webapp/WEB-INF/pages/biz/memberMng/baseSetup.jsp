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
<title>店铺信息</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
<script type="text/javascript" src="${path}/resources/scripts/bxslider/plugins/jquery.easing.1.3.js"></script>
	
	<div id="dialog-memberLevel" class="easyui-dialog" style="width: 400px; height: 280px; padding: 10px 20px" closed="true" buttons="#dlg-buttons">
		<form id="fm" class="easyui-form" method="post" data-options="novalidate:true">
		<tr><td><span style="font-size:16px;font-weight:bold;">店铺信息：</span></td></tr>
			<table>
				<tr><td align="center">店主姓名:</td><td><input type="text" id="companyName" name="companyName" class="tab_input easyui-validatebox" data-options="required:true" missingMessage='此处填写企业名称'></td></tr>
				<tr>
					<td><label>店主姓名：</label></td>
					<td><input name="" class="easyui-textbox" placeholder="用户名" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>身份证号：</label></td>
					<td><input name="" class="easyui-textbox" type="password" placeholder="密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>店铺名称</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>经营范围</label></td>
					<td><select class="easyui-combobox" name="isEtps" placeholder="企业账户" data-options="required:true">
							<option value="0">否</option>
							<option value="1">是</option>
					</select></td>
				</tr>
					<tr>
					<td><label>所在地区</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
					<tr>
					<td><label>详细地址</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>店铺标志</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>申请公司信息</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>企业名称</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>营业执照号</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>税务登记证号</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>企业组织机构代码：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>法定代表人：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>法人身份证号：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>公司负责人：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>负责人身份证号：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>企业联系电话：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>银行名称：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>银行卡号：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>公司注册地址：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>公司注册资金：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>公司成立时间：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>公司官网地址：</label></td>
					<td><input name="password" class="easyui-textbox" type="password" placeholder="确认密码" data-options="required:true" /></td>
				</tr>
				<tr>
					<td><label>营业执照有效期：</label></td>
					<td><div class="easyui-calendar" style="width:50%;height:250px;"></div>~<div class="easyui-calendar" style="width:50%;height:250px;"></div></td>
				</tr>
				<tr>
					<td><label>营业执照经营范围</label></td>
					<td><input name="password" class="easyui-textbox" type="text"/></td>
				</tr>
			</table>
			<div id="dlg-buttons">
				<a class="easyui-linkbutton c6" iconCls="icon-ok" onclick="" style="width: 90px">保存</a>	
			</div>
		</form>
	</div>
</body>
</html>
