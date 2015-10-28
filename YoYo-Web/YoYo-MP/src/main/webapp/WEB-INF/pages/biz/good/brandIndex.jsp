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
<title>商品品牌</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
</head>
<body>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/previewImg.js?v=${versionVal}"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <script type="text/javascript"  src="${path}/resources/scripts/biz/good/brandIndex.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
	<div id="toolbar-brand">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true"
			onclick="newbrandAndValue()"> 添加品牌</a> <a class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="deleteBrand()"> 删除 </a>
			<a href="#"   style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openAdvaceQuery('advance_search_brand')">高级筛选</a>
	</div>
	<table id="table-brand" ></table>


	<!-- 新增品牌-->
	<div id="window-add-brand" class="easyui-window" title="添加品牌"
		closed="true" style="height: 700px; width: 800px;"
		data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

		<form id="form-brand-add" class="easyui-form">
			<input type="hidden" name="brandId">
			<table class="add_brand_table">
				<tr>
					<td style="width:30%;text-align:right;"><span style="color: red;">*</span>品牌名称：</td>
					<td><input type='text' name="brandName" id="brandNames"
						class="tab_input easyui-validatebox" data-options="required:true" validType="special"/></td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">品牌别名：</td>
					<td><input type='text' name="brandKeywords"
						class="tab_input easyui-validatebox" validType="special"/>(|分隔，以|开头与结尾)</td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">品牌网址：</td>
					<td><input type='text' name="brandUrl" class="tab_input" /></td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">前端排序：</td>
					<td><input type='text' name="ordernum" style="width: 90px;"
						class="tab_input easyui-numberbox" data-options="required:false"/></td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">LOGO：</td>
					<td>
						<div>
							<form method="post" enctype="multipart/form-data">
								<div class="upload_btn">
									<div class="fileInputContainer">
										<input type="file" name="imageFile" id="brandLogo"	class="fileInput"
											onchange="previewImage(this,'preBrandLogo','imgBrandLogo',260,150)" />

									</div>
									<input type="button" onclick="submitForm('brandLogo','brandLogo1','imgBrandLogo')"
										class="upLoad" value="上传" />
								</div>
								<div id="preBrandLogo" class="shop_logo">
									<img id="imgBrandLogo" width="260" height="150">
								</div>
							</form>
						</div> <input type="hidden" name="brandLogo" id="brandLogo1">
					</td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">品牌资质文件：</td>
					<td>
						<div>
							<form method="post" enctype="multipart/form-data">
								<div class="upload_btn">
									<div class="fileInputContainer">
										<input type="file" name="imageFile" id="brandAptitude"
											class="fileInput"
											onchange="previewImage(this,'preBrandAptitude','imgBrandAptitude',260,150)" />

									</div>
									<input type="button" onclick="submitForm('brandAptitude','brandAptitude1','imgBrandAptitude')"
										class="upLoad" value="上传" />
								</div>
								<div id="preBrandAptitude" class="shop_logo">
									<img id="imgBrandAptitude" width="260" height="150">
								</div>
							</form>
						</div> <input type="hidden" name="brandAptitude" id="brandAptitude1">
					</td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">TITLE (页面标题)</td>
					<td><input type='text' name="title" class="tab_input" /></td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">META_KEYWORDS (页面关键词)</td>
					<td><input type='text' name="metaKeywords" class="tab_input" /></td>
				</tr>
				<tr>
					<td style="width:30%;text-align:right;">META_DESCRIPTION (页面描述)</td>
					<td><input type='text' name="metaDesc" class="tab_input" /></td>
				</tr>
				<tr > 
					<td style="width:30%;text-align:right;">商品分类关联：</td>
					<!-- <td>
					<select id="tab_select_parent" style="width:160px;" >
                     </select>
                      <select id="tab_select_child" style="width:160px;" >
                      </select>
					</td> -->
					<td><select id="combox-cat-virt-index"  name="parentCatId" style="width: 240px;padding: 3px 5px;height: 28px;" class="walidator" ></select>
					</td>
				</tr>
               
				<tr>
					<td style="width:30%;text-align:right;">详细说明：</td>
					<td><textarea cols="50" rows="6" name="brandDesc" id="desc_txt"  placeholder="最多输入1000个字符"></textarea></td>
				</tr>


			</table>
		</form>

		<div region="south" border="false"
			style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
			<a class="easyui-linkbutton" id="add-brand-btn" icon="icon-save"
				onclick="savebrandAndValue()" style="display: none;">保存</a> <a
				class="easyui-linkbutton" id="update-brand-btn" icon="icon-save"
				onclick="updateBrand()" style="display: none;">保存</a> <a
				class="easyui-linkbutton" id="update-brand-btn" icon="icon-cancel"
				onclick="javascript:closeDailog('window-add-brand');" style="margin-left: 15px;">取消</a>
		</div>
	</div>

    <div id="advance_search_brand" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="brand_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right"><span>品牌名称: </span></td>
					<td><input id="brandName" name="brandName"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>品牌别名: </span></td>
					<td><input id="brandKeywords" name="brandKeywords"
						class="easyui-textbox search_class" /></td>
				</tr>
				<tr>
					<td align="right"><span>品牌网址: </span></td>
					<td><input id="brandUrl" name="brandUrl"
						class="easyui-textbox search_class" /></td>
				</tr>
				
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('table-brand','brand_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="search_clear('brand_search_form','table-brand')" style="width: 90px">清除条件</a>
    </div>
</body>
</html>
