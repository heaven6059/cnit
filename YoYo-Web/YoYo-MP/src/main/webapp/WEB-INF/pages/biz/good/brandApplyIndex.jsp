<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>品牌审核管理</title>
<style type="text/css">
.full-spectrum .sp-palette {
max-width: 200px;
}
</style>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
<script type="text/javascript" src="${path}/resources/scripts/biz/good/brandApply.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/spectrum/spectrum.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<link type="text/css" href="${path}/resources/styles/spectrum/spectrum.css?v=${versionVal}" rel="stylesheet" />
    <div id="brandTabs" class="easyui-tabs" >
	    <div id="all" title="" cache="false">
		    <div id="toolbar-all">
				<!-- <a id="label_1" class="easyui-menubutton" data-options="plain:false"> 标签</a> -->
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deletedBrandApply()"> 删除 </a>
			</div>
			<table id="brandApplyAll" ></table>
	    </div>
	    <div id="ing" title="" cache="false">
	    	<div id="toolbar-ing">
				<!-- <a id="label_2" class="easyui-menubutton" data-options="plain:false"> 标签</a> -->
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deletedBrandApply()"> 删除 </a>
			</div>
			<table id="brandApplying" ></table>
	    </div>
	    <div id="pass" title="" cache="false">
	    	<div id="toolbar-pass">
				<!-- <a id="label_3" class="easyui-menubutton" data-options="plain:false"> 标签</a> -->
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deletedBrandApply()"> 删除 </a>
			</div>
			<table id="brandApplyPass" ></table>
	    </div>
	     <div id="cancel" title="" cache="false">
	    	<div id="toolbar-cancel">
				<!-- <a id="label_4" class="easyui-menubutton" data-options="plain:false"> 标签</a> -->
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deletedBrandApply()"> 删除 </a>
			</div>
			<table id="brandApplyCancel" ></table>
	    </div>
	    <div id="refuse" title="" cache="false">
	    	<div id="toolbar-Refuse">
				<!-- <a id="label_4" class="easyui-menubutton" data-options="plain:false"> 标签</a> -->
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deletedBrandApply()"> 删除 </a>
			</div>
			<table id="brandApplyRefuse" ></table>
	    </div>
    </div>
  <!--   <div id="labelTag">
		<div id="labeling">为选中项打标签</div>
		<div id="labelSet">标签设置</div>
	</div> -->
	<div id="labelSetWindow" class="easyui-window" title="标签管理" data-options="closed: true,minimizable:false" style="position:relative;width:800px;height:500px;overflow:auto;">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="false" onclick="addNewLabel()" style="margin-left:15px">新增</a>
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="false" onclick="deleteLabel()" style="margin-left:15px"> 删除 </a>
		<table id="brandApplyLabels" ></table>
		<div id="modelWindow" class="easyui-window" title="新建标签" data-options="inline:false,modal:true,closed:true,minimizable:false" style="top: 130px;width:400px;height:200px;padding:10px;">
			<div style="padding:10px 60px 20px 60px">
				<form id="dataForm" method="post">
					<input type="hidden" name="campanyId">
					<input type="hidden" name="id">
					<table cellpadding="5">
						<tr>
							<td>标签名:</td>
							<td><input id="labelName" class="easyui-validatebox" type="text" name="name"  data-options="required:true"></input></td>
						</tr>
						<tr>
							<td>标签备注:</td>
							<td><input class="easyui-textbox" type="text" name="remark"></input></td>
						</tr>
						<tr>
							<td>字体颜色:</td>
							<td><input id="fontColor" type="text" name="fontColor"></input></td>
						</tr>
						<tr>
							<td>背景颜色:</td>
							<td><input id="bgColor" type="text" name="bgColor"></input></td>
						</tr>
					</table>
				</form>
				<div style="text-align:center;padding:5px">
					<a class="easyui-linkbutton" href="#" onclick="javascript:submitLabelForm()">确定</a>
				</div>
			</div>
		</div>	
	</div>
	<div id="modelLabelWindow" class="easyui-window" title="设置标签" data-options="modal:true,closed:true" style="position: relative;width:300px;height:400px;padding:10px;">
		<div>
 				对选择的<strong id="itemNum"></strong>个条目应用标签
 			</div>
		<div style="margin:10px;">
			<ul id="labelsForSelect"></ul>
		</div>
		<div style="left:230px;position:absolute;bottom:15px;">
			<a class="easyui-linkbutton" href="#" onclick="javascript:saveBrandLabel()">保存</a>
		</div>
	</div>
	
	
	<!-- 审核不通过弹出窗口 -->
<!-- 	<div id="refuseWindow" class="easyui-window" title="审核不通过" data-options="modal:true,closed:true," style="position: relative;width:400px;height:300px;padding:10px;">
                        不通过的原因：</br>
        <div><textarea clos='20' rows='8' id="reason_textarea" style="width:320px;"></textarea>   </div>        
        <div style="left:170px;position:absolute;bottom:15px;">
            <a class="easyui-linkbutton" href="#" onclick="javascript:saveRefuse()">保存</a>
        </div>
    </div> -->
</body>
</html>