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
<title>虚拟分类</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript"	src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript"	src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.comdropdown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/good/virtualCateIndex.js?v=${versionVal}"></script>
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript"  src="${path}/resources/scripts/webUploader/webuploader.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#form-virt-cate-add').walidator();
	});
	function expandAll() {
		$('#table-virt-cate').treegrid('expandAll');
	}
	function collapseAll() {
		$('#table-virt-cate').treegrid('collapseAll');
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
	<div id="toolbar-virt-cate">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newVirtCate()"> 添加分类</a> <a
			class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="expandAll()"> 展开分类</a> <a
			class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="collapseAll()"> 收起分类</a>
	</div>
	<table id="table-virt-cate" style="marge: 10px auto;"></table>
	<!-- 分类编辑 -->
	<div id="window-virt-cate-add" class="easyui-window" closed="true" style="height: 650px; width: 630px;"
		data-options="inline:false,cache:false,onOpen:getVirtCateDetail,onBeforeClose:clearVirtCateDetail,minimizable:false,collapsible:false,modal:true">
		<div>
			<form id="form-virt-cate-add"    style="position: relative; left: 20px; width: 500px;">
				<table class="table-form"  style="border: none;">
					<tr class="hide">
						<td style="width:20%;text-align:right;"><label>分类ID：</label></td>
						<td><input type="text" name="catId"  /></td>
					</tr>
					<tr>
						<td style="width:20%;text-align:right;"><label>分类名称：</label></td>
						<td align="left"><input type="text"  name="catName" style="width: 300px;" class="easyui-textbox" data-options="required:true" /></td>
					</tr>
					<tr id="tr_parent_virtual_cat_id">
						<td style="width:20%;text-align:right;"><label>上级虚拟分类：</label></td>
						<td align="left"><select id="combox-parent-vir-cat"  name="parentCatId" style="width: 315px;padding: 3px 5px;height: 28px;">
						</select></td>
					</tr>
					<tr>
						<td style="width:20%;text-align:right;"><label>排序：</label></td>
						<td><input type="text" name="orderNum" style="width: 300px;" class="easyui-numberbox"  data-options="precision:0,max:99999" /></td>
					</tr>
					<tr>
						<td style="width:20%;text-align:right;"><label>是否隐藏：</label></td>
						<td><input type="checkbox" name="hidden" id="hiddenId"/></td>
					</tr>
					<tr>
						<td style="width:20%;text-align:right;"><label>虚拟分类图标：</label></td>
						<td>
							<div>
								<form method="post" enctype="multipart/form-data">
									<div class="upload_btn">
										<div class="fileInputContainer">
											<input type="file" name="imageFile" id="cateImage"	class="fileInput" onchange="previewImage(this,'preCateImage','imgCateImage',100,100)" />
										</div>
										<input type="button" onclick="submitForm('cateImage','cateIcon','imgCateImage')" class="upLoad" value="上传" />
									</div>
									<div id="preCateImage" >
										<img id="imgCateImage" width="100" height="100" style="border:1px solid #cbcaca; border-radiu:5px;" >
									</div>
								</form>
							</div> <input type="hidden" name="icon" id="cateIcon">
						</td>					
					</tr>
					<tr>
						<td style="width:20%;text-align:right;"><label>描述：</label></td>
						<td><input type="text" name="title" style="width: 300px;"></td>					
					</tr>
					<tr>
						<td style="width:20%;text-align:right;"><label>筛选条件：</label></td>
						<td>
							<div style="border: 1px solid #999">
								<form id="form-virt-cate-filter">
									<table class="table-form-nest">
										<!-- <tr>
											<td><label>按价格区间筛选：</label></td>
											<td>
												<input type="text" name="pmi"  class="easyui-numberbox"  data-options="precision:2,max:999999999" style="width: 95px;">
												<span>到</span>
												<input type="text"  name="pma" class="easyui-numberbox"  data-options="precision:2,max:999999999" style="width: 95px;">
											</td>
										</tr> -->
										<tr>
											<td><label>按商品关键词筛选：</label></td>
											<td><input type="text" name="kw" style="width: 225px;" class="walidator"  /></td>
										</tr>
										<tr>
											<td><label>商品分类：</label></td>
											<td><select id="combox-cat-virt-index"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select>
											</td>
										</tr>
										<tr>
											<td><label>商品品牌：</label></td>
											<td><select  id="combox-brand-virtcat-index" name="kw"  style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select></td>
										</tr>
										<!-- <tr>
											<td><label>标签：</label></td>
											<td><select  id="combox-tag-virtcat-index" name="tag"  style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></td>
										</tr> -->
									</table>
								</form>
							</div>
						</td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center;">
			<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdateVirtCate()">保存</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a class="easyui-linkbutton" icon="icon-cancel" onclick="javascript:$('#window-virt-cate-add').window('close')">取消</a>
		</div>
	</div>
</body>
</html>
