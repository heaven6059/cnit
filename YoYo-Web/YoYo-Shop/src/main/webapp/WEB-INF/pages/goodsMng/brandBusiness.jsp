<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城-品牌申请</title>
<style type="text/css">
.datagrid-row {
	height: 80px;
}
#brand-edit-relate li{  border: 1px solid #DDDDDD;
  line-height: 16px;
  margin: 5px 0;
  overflow: hidden;
  padding: 5px; 
  }
</style>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/ImageUpload.css">
<link type="text/css" href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}" rel="stylesheet" />
<script type="text/javascript"  src="${path}/resources/scripts/biz/goodsMng/brandBusiness.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<script type="text/javascript"  src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css"  href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
</head>
<body>
	<div class="shop_manager_right">
		<div class="title">
			<span style="float: left">品牌列表<span class="disc">|</span> 
			<span class="disc add-icon"><a href="javascript:applyNewBrand();">品牌申请</a></span>
			<span class="disc">|</span> 
			<span class="disc add-icon"><a href="javascript:applyOldBrand();">品牌使用申请</a></span>
			</span>
		</div>
		<!-- 添加品牌 start -->
		<div id="addBrand" class="addshop">
			<form id="addBrand_form">
				<input type="hidden" name="companyBrandId">
				<!-- FIXME companyId需要后续的操作值，此处暂时给一个定值 -->
				<input type="hidden" name="companyId">
				<input type="hidden" name="brandId">
				<input type="hidden" name="type">
				<input type="hidden" name="status">
				<input type="hidden" name="disabled">
				<table class="addShop_table">
					<tr>
						<td  align="right"><span style="color: red;" >* </span>品牌名称：</td>
						<td>
							<input type="text" name="brandName" id="brandName" class="easyui-validatebox" data-options="required:true" missingMessage='请填写品牌名称' />
						</td>
					</tr>
				<!-- 	<tr>
						<td><span style="color: red;">* </span>所属分类：</td>
						<td>
							<select style="width:310px;" id="storeCat" name="storeCat" class="easyui-combobox" data-options="valueField:'id',textField:'text',required:true, panelHeight:'auto',editable:false" missingMessage='请选择品牌分类'>
								<option value="0">新车</option>
								<option value="1">配件</option>
								<option value="2">保养</option>
							</select>
						</td>
					</tr> -->
					<tr><td align="right"><span style="color:red;">* </span>所属分类:</td>
					<td>
					<select id="storeCat" name="storeCat" style="width:160px;" >
                     </select>
		             </tr>
					<tr>
						<td  align="right">品牌别名：</td>
						<td>
							<input type="text" id="brandKeywords" name="brandKeywords">
						</td>
					</tr>
					<tr>
						<td  align="right">网址：</td>
						<td>
							<input type="text" id="brandUrl" name="brandUrl">
						</td>
					</tr>
					<tr>
						<td  align="right">LOGO：</td>
						<td>
							<div>
								<form method="post" enctype="multipart/form-data">
									<div class="upload_btn">
										<div class="fileInputContainer">
											<input type="file" name="imageFile" id="brandLogo" class="fileInput" onchange="previewImage(this,'preBrandLogo','imgBrandLogo', 260, 150)" />
										</div>
										<input type="button" onclick="submitForm('brandLogo','brandLogo1','imgBrandLogo')" class="upLoad" value="上传"/>
									</div>
									<div id="preBrandLogo" class="shop_logo">
										<img id="imgBrandLogo" width="260" height="150">
									</div>
								</form>
							</div>
							<input type="hidden" id="brandLogo1" name="brandLogo">
						</td>
					</tr>
					<tr>
						<td  align="right">品牌资质扫描件：</td>
						<td>
							<div>
								<form method="post" enctype="multipart/form-data">
									<div class="upload_btn">
										<div class="fileInputContainer">
											<input type="file" name="imageFile" id="brandAptitude" class="fileInput" onchange="previewImage(this,'preBrandAptitude','imgBrandAptitude', 260, 150)" />
										</div>
										<input type="button" onclick="submitForm('brandAptitude','brandAptitude1','imgBrandAptitude')" class="upLoad" value="上传"/>
									</div>
									<div id="preBrandAptitude" class="shop_logo">
										<img id="imgBrandAptitude" width="260" height="150">
									</div>
								</form>
							</div>
							<input type="hidden" id="brandAptitude1" name="brandAptitude">
						</td>
					</tr>
					<tr>
						<td  align="right">品牌介绍：</td>
						<td>
							<textarea id="brandDesc" name="brandDesc" style="height: 100px; width: 310px;" class="textarea easyui-validatebox" ></textarea>
						</td>
					</tr>
				</table>
			</form>
			<a href="#" onclick="javascript:saveBrandBusiness()" class="save_btn addshop-form-a"></a> 
			<a href="javascript:$('#addBrand').hide();" class="cancel_btn "></a>
		</div>
		<!-- 添加品牌 end -->
		<!-- 使用旧品牌 -->
		<div id="addOldBrand" class="addshop">
			品牌使用申请:<div class="object-select clearfix"  onclick="selectOldBrand();" style="margin-left:10px;">
				<div class="label">请选择品牌</div>
				<div class="handle">&nbsp;</div>
			</div>
			<ul id="brand-edit-relate" style="margin-left:90px;">
			</ul>
			<div style="margin-top:20px;">
				<a href="#" onclick="javascript:saveOldBrand()" class="save_btn addshop-form-a"></a> 
				<a href="javascript:$('#addOldBrand').hide();" class="cancel_btn "></a> 
			</div>
		</div>
		<!-- 选择已有品牌BEGIN -->
		<div id="oldBrandWindow" class="easyui-window" title="选择品牌"  closed="true"  style="width:600px;height:616px;">
			<div style="padding: 5px;" class="datagrid-toolbar" id="product_sub_toolbar" >
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="confirmOldBrand()"> 确认选择</a>
			</div>
			<div class="zTreeDemoBackground left">
				<table id="brandTable" style="marge: 10px auto; width: 100%;"></table>
			</div>
		 </div>
		<!-- 选择已有品牌 END -->	
		
		
		
		<table id="brandList_dg" style="margin: 10px auto;"></table>
	</div>
	<script type="text/javascript">
		initBrandListDg();
	</script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/ToolTip.js"></script>
</body>
</html>