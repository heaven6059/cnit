<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>保养配件管理</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/car/carMaintainAccessory.js"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>

</head>
<body>
	<div id="toolbar_maintian_acc">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="expandAll()"> 展开分类</a> 
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="collapseAll()"> 收起分类</a>
	</div>
	<table id="table_maintian_acc" style="marge: 10px auto;"></table>
	   <!-- 设置保养配件对话框 -->
    <div id="maintain_acc_window" class="easyui-window" title="设置保养配件" closed="true" style="height: 500px; width: 660px;" data-options="cache:false,modal:true,shadow:false,left:'400px',top:50,minimizable:false,iconCls:'icon-save'">
        <table id="car_accessory_tables"></table>
        <div style=" margin-left: 200px;  margin-top: 60px;">
            <a onclick="javascript:getAccessoryId()" class="easyui-linkbutton" style="margin-right:50px;">确定</a>
            <a onclick="javascript:closeMaintainDialog()" class="easyui-linkbutton" >取消</a>
        </div>
     </div>
     <!-- 选择的分类id -->
     <input type="hidden" id="catId">
</body>
</html>
