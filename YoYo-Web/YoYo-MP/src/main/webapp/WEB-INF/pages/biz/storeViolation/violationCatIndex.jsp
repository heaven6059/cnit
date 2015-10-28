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
<title>违规类型</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.comdropdown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/storeViolation/violationCatIndex.js"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript">
	$(function() {
		$('#form-violation-cate-add').walidator();
	});
	function expandAll() {
		$('#table-violation-cate').treegrid('expandAll');
	}
	function collapseAll() {
		$('#table-violation-cate').treegrid('collapseAll');
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
	<div id="toolbar-violation-cate">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addCate(0)"> 添加分类</a> <a
			class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="expandAll()"> 展开分类</a> <a
			class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="collapseAll()"> 收起分类</a>
	</div>
	<table id="table-violation-cate" style="marge: 10px auto;"></table>
	<!-- 分类编辑 -->
	<div id="window-violation-cate-add" class="easyui-window" closed="true" style="height: 250px; width: 600px;"
		data-options="inline:false,cache:false,onOpen:getVirtCateDetail,onBeforeClose:clearVirtCateDetail,minimizable:false,collapsible:false">
		<div>
			<form id="form-violation-cate-add"    style="position: relative; left: 20px; width: 500px;">
				<table class="table-form"  style="border: none;">
					<tr class="hide">
						<td><label>分类ID：</label></td>
						<td><input type="text" name="catId"  /></td>
					</tr>
					<tr>
						<td align="right"><label><span style="color: red;">*</span>分类名称：</label></td>
						<td align="left"><input type="text"  name="catName" style="width: 300px;" class="easyui-textbox" data-options="required:true,maxlength:5" MaxLength="5" size="5" /></td>
					</tr>
					<tr id="tr_parent_violation_cat_id">
						<td align="right"><label>上级分类：</label></td>
						<td align="left"><select id="combox-parent-violation-cat"  name="parentId" style="width: 300px;padding: 3px 5px;height: 28px;">
						</select></td>
					</tr>
					<tr>
						<td align="right"><label>排序：</label></td>
						<td><input type="text" name="pOrder" style="width: 300px;" class="easyui-numberbox"  data-options="precision:0,max:99999,min:0" /></td>
					</tr>
					<!-- <tr>
						<td align="right"><label><span style="color: red;">*</span>扣除分数：</label></td>
						<td><input type="text" name="score" style="width: 300px;" class="easyui-numberbox"  data-options="precision:0,min:0,max:99999,required:true" /></td>
					</tr> -->
					<!-- <tr>
						<td><label>筛选条件：</label></td>
						<td>
							<div style="border: 1px solid #999">
								<form id="form-virt-cate-filter">
									<table class="table-form-nest">
										<tr>
											<td><label>按价格区间筛选：</label></td>
											<td>
												<input type="text" name="pmi"  class="easyui-numberbox"  data-options="precision:2,max:999999999" style="width: 95px;">
												<span>到</span>
												<input type="text"  name="pma" class="easyui-numberbox"  data-options="precision:2,max:999999999" style="width: 95px;">
											</td>
										</tr>
										<tr>
											<td><label>按商品关键词筛选：</label></td>
											<td><input type="text" name="kw" style="width: 225px;" class="walidator"  /></td>
										</tr>
										<tr>
											<td><label>商品分类：</label></td>
											<td><select id="combox-cat-violation-index"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select>
											</td>
										</tr>
										<tr>
											<td><label>商品品牌：</label></td>
											<td><select  id="combox-brand-virtcat-index" name="kw"  style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select></td>
										</tr>
										<tr>
											<td><label>标签：</label></td>
											<td><select  id="combox-tag-virtcat-index" name="tag"  style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></td>
										</tr>
									</table>
								</form>
							</div>
						</td>
					</tr> -->
				</table>
			</form>
		</div>
		<div style="text-align: center;">
			<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdateCate()">保存</a>
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-violation-cate-add').window('close');$('#MODALPANEL').hide();">取消</a>
		</div>
	</div>
	<div id="MODALPANEL" style="position: absolute; width: 100%; height: 100%; top: 0px; left: 0px; z-index: 6000; opacity: 0.4; background: rgb(51, 51, 51);display:none;"></div>
</body>
</html>
