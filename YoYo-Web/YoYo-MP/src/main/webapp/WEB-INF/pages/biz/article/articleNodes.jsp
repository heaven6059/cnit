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
<title>文章栏目</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.comdropdown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/article/articleNodes.js"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript">

	$(function() {
		$('#form-article-nodes-add').walidator();
	});
	function expandAll() {
		$('#table-article-nodes').treegrid('expandAll');
	}
	function collapseAll() {
		$('#table-article-nodes').treegrid('collapseAll');
	}
	$.extend($.fn.validatebox.defaults.rules, {
		rangeCheck : {
			validator : function(value, param) {
				var s = $("input[name=" + param[0] + "]").val();
				if (s && value) {
					return value >= s;
				} else {
					return true;
				}
			},
			message : '非法数据'
		}
	});
</script>
</head>
<body>
	<div id="toolbar-article-nodes">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addNodes(0)"> 添加栏目</a> 
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="expandAll()"> 展开栏目</a> 
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="collapseAll()"> 收起栏目</a>
	</div>
	<table id="table-article-nodes" style="marge: 10px auto;"></table>
	
	<!-- 分类编辑 -->
	<div id="window-article-nodes-add" class="easyui-window" closed="true" style="height: 200px; width: 700px;" data-options="inline:false,cache:false,onOpen:getArticleNodesDetail,onBeforeClose:clearArticleNodesDetail,minimizable:false,collapsible:false">
		<div>
			<form id="form-article-nodes-add"    style="position: relative; left: 20px; width: 630px;">
				<table class="table-form"  style="border: none;">
					<tr class="hide">
						<td><label>栏目ID：</label></td>
						<td><input type="text" name="nodeId"  /></td>
					</tr>
					<tr>
						<td align="right"><label><span style="color: red;">*</span>文章栏目名称：</label></td>
						<td align="left"><input type="text"  name="nodeNames" style="width: 300px;" class="easyui-textbox" data-options="required:true,maxlength:25" MaxLength="25" /></td>
					</tr>
					<tr id="tr_parent_article_nodes_id">
						<td align="right"><label>上级栏目：</label></td>
						<td align="left">
							<select id="combox-parent-article-nodes"  name="parentId" style="width: 300px;padding: 3px 5px;height: 28px;">
							</select>
						</td>
					</tr>
					<tr>
						<td align="right"><label>排序：</label></td>
						<td> 
							<input type="text" name="ordernum" style="width: 300px;" class="easyui-numberbox"  data-options="precision:0,max:99999,min:0" />
							<span class="notice-inline">数字越小越靠前 </span>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center;">
			<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdate()">保存</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-article-nodes-add').window('close');$('#MODALPANEL').hide();">取消</a>
		</div>
	</div>
	<div id="MODALPANEL" style="position: absolute; width: 100%; height: 100%; top: 0px; left: 0px; z-index: 6000; opacity: 0.4; background: rgb(51, 51, 51);display:none;"></div>
</body>
</html>
