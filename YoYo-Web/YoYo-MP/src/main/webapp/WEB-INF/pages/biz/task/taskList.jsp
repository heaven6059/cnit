<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/biz/system/task.js?v=${versionVal}"></script>
</head>
<body class="easyui-layout" fit="true">
<div>
	<div id="toolbar-task" style="padding-top:5px;padding-left:10px;">
		<a href="javascript:task.newJob();" class="easyui-linkbutton" style="width: 80px;" id="upBtn">添加</a>
	</div>
	<table id="taskList" ></table>
</div>

<div id="dlg" class="easyui-dialog" style="width: 550px; height: 380px; padding: 30px 20px" closed="true" buttons="#dlg-buttons">
	<form id="fm" class="easyui-form" method="post"  >
		<input type="hidden" name="jobId" id="jobId"/>
		<table class="table-form">
			<tr>
				<td width="width:150px;"><label>任务名称</label></td>
				<td width="width:350px;"><input name="jobName" class="easyui-validatebox"  size="50" data-options="required:true"/></td>
			</tr>
			<tr>
				<td><label>执行类</label></td>
				<td><input name="beanClass" id="beanClass" class="easyui-validatebox"  size="50" data-options="required:true"/></td>
			</tr>
			<tr>
				<td><label>执行方法</label></td>
				<td><input name="methodName" id="methodName" class="easyui-validatebox" class="easyui-validatebox" size="30"  data-options="required:true"/></td>
			</tr>
			<tr>
				<td><label>运行状态</label></td>
				<td>
					<select id='jobStatus' name="jobStatus" class="easyui-combobox" style="width: 180px;">
					 <option value='1'>运行</option>
					 <option value='0'>停止</option>
				   </select>
				</td>
			</tr>
			<tr>
				<td><label>时间表达式</label></td>
				<td><input name="cronExpression" id="cronExpression" class="easyui-validatebox" data-options="required:true"/></td>
			</tr>
		</table>
	</form>
</div>
<div id="dlg-buttons" style="width:320px !important;">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="task.updateJob();" >保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')" >取消</a>
</div>	

</body>
</html>