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
<title>广告列表</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<style type="text/css">
div.window{
	width:650px;
}
table.select{
	margin:10px auto
}
.select td{
	padding: 5px 50px;
}
.select .radio{
	padding: 5px 90px;
}
.easyui-linkbutton{
	margin:5px 15px;
}
</style>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/site/adIndex.js?v=${time}"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
</head>
	<body>
		<div id="toolbar" >
	        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openDialog()"> 添加广告 </a>
	        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteAd()"> 删除广告  </a>
	    </div>
	    
		<table id="adListDataGrid" style="marge: 10px auto;"></table>
		
		<div id="window" class="easyui-window" closed="true" data-options="title:'广告配置',iconCls:'icon-save',minimizable:false,maximizable:false,collapsible:false" >
			<input type="hidden" id="addOrEdit"/>
			<input type="hidden" id="adId"/>
			
			<form id="addForm">
				<table class="select">
					<tr>
						<td>广告类型：
							<select id="adType" class="easyui-combobox" name="adType" style="width:153px;height: auto;">
							    <option value="0">轮播</option>
							    <option value="1">图片</option>
							    <option value="2">文字</option>
							</select>
						</td>
						<td>广告名称：<input type="text" id="name" name="name" /></td>
					</tr>
					<tr>
						<td >广告个数：<input type="text" id="adNum" value="0" class="easyui-validatebox" data-options="validType:'oneToTen'"/></td>
						<td >图片尺寸：
							<input type="text" disabled id="picWidth" style="width:60px" class="easyui-validatebox" data-options="validType:'positiveNumFour'"/> X 
							<input type="text" disabled id="picHeight" style="width:60px"  class="easyui-validatebox" data-options="validType:'positiveNumFour'"/>
						</td>
					</tr>
					<tr >
						<td><input type="checkbox" id="adUrl" value="adUrl" />广告地址</td>
						<td><input type="checkbox" id="adTitle" value="adTitle" />广告标题</td>
					</tr>
					<tr >
						<td><input type="checkbox" class="pic" id="needPic" value="needPic" onclick="linkage(this,'pic')"/>需要图片</td>
					</tr>
					<tr >
						<td><input type="checkbox" class="pic" id="picUrl" value="picUrl" onclick="linkage(this,'pic')"/>图片地址</td>
						<td><input type="checkbox" id="orderNo" value="orderNo" />排列序号</td>
					</tr>
					<tr >
						<td><input type="checkbox" id="adDescription" value="adDescription" />广告说明</td>
						<td><input type="checkbox" id="intervalTime" value="intervalTime" />轮播间隔</td>
					</tr>
					<tr >
						<td><input type="checkbox" id="backgroundColor" value="backgroundColor"/>背景颜色</td>
						<td><input type="checkbox" id="adContent" value="adContent" />内容描述</td>
					</tr>
				</table>
				<div style="padding:5px;text-align:center;">
					<a id="save" href="javascript:save()" class="easyui-linkbutton" icon="icon-ok">保存</a>
					<a id="cancle" href="javascript:close()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
				</div>
			</form>
		</div>
	</body>
</html>
