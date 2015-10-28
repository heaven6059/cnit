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
<title>YOYO汽车商城-商家上传图片</title>
<link type="text/css" rel="stylesheet" href="${path}/resources/styles/shop/shopRegister.css">
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<script type="text/javascript">
//图片路径提交
function imgPathSubmit(){
	var reg_data = biz.serializeObject($("#img_form"));
	if($("#imageId1").val()=='' || $("#imageCid1").val()=='' ||$("#imageCodeid1").val()==''||$("#imageTaxid1").val()==''){
		parent.$.messager.alert('错误', '部分图片没有上传', 'error',function(){
			
		});
		return false ;
	}
	$.ajax({
		url : biz.rootPath() + "/shopEnter/saveShopImagePath",
		data : reg_data,
		type : "post",
		datatype : "json",
		success : function(data) {
			parent.$.messager.progress('close');
			if (data.retCode=='000000') {
				window.opener=null;
				window.open('','_self');
				window.close();
			} else {
				$.messager.alert('错误', '保存失败', 'error');
			}
			clear();
		}
	});
	
}


function clear(){
	$("#imageId1").val('');
	$("#imageCid1").val('');
	$("#imageCodeid1").val('');
	$("#imageTaxid1").val('');
}


</script>
</head>
<body>
	<div class="shop_reg_main" style="height:1100px;">
		<div style="margin-left:100px; ">
				<form action="post" id="img_form">
					<input type="hidden" name="imageId" id="imageId1"/>
					<input type="hidden" name="imageCid" id="imageCid1"/>
					<input type="hidden" name="imageCodeid" id="imageCodeid1"/>
					<input type="hidden" name="imageTaxid" id="imageTaxid1"/>
					<input type="hidden" name="companyId" id="companyId" value="${companyId}"/>
				</form>
				<div class="shop_reg_info_title"><span style="font-size:18px;font-weight:bold;">公司资料：</span><span style="margin-left:700px;">图片大小不能超过2M</span></div>
				<hr style="width:1000px; margin-left: 0px;"/>
				<div class="fig">
					<h4>身份证</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传身份证照:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageId" class="fileInput" onchange="previewImage(this,'previewImageId','imgimageId',448,248)" />
						
						 </div>
						 <input type="button" onclick="submitForm('imageId','imageId1','imgImageid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewImageId" class="fig_tj">
							<img id="imgImageid" src="${path}/resources/images/default.jpg">
						</div>
					</form>
				</div>
				
				<div class="fig">
					<h4>执照</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传执照:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageCid" class="fileInput" onchange="previewImage(this,'previewImageCid','imgimageCid',448,248)" />
						 </div>
						 <input type="button" onclick="submitForm('imageCid','imageCid1','imgimageCid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewImageCid" class="fig_tj">
							<img id="imgimageCid" src="${path}/resources/images/default.jpg">
						</div>
					</form>
				</div>
				
				
				<div class="fig">
					<h4>组织机构代码</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传组织机构代码:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageCodeid" class="fileInput" onchange="previewImage(this,'previewCodeid','imgimageCodeid',448,248)" />
						 </div>
						 <input type="button" onclick="submitForm('imageCodeid','imageCodeid1','imgimageCodeid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewCodeid" class="fig_tj">
							<img id="imgimageCodeid" src="${path}/resources/images/default.jpg">
						</div>
					</form>
				</div>
				
				
				<div class="fig">
					<h4>税务登记证</h4>
					<form method="post" enctype="multipart/form-data"><div style="height:31px;">
					<p>上传税务登记证:</p>
					<div class="fileInputContainer">
						 <input type="file" name="imageFile" id="imageTaxid" class="fileInput" onchange="previewImage(this,'previewTaxid','imgimageTaxid',448,248)" />
						 </div>
						 <input type="button" onclick="submitForm('imageTaxid','imageTaxid1','imgimageTaxid')"	class="upLoad"  value="上传" />
						 </div>
						<div id="previewTaxid" class="fig_tj">
							<img id="imgimageTaxid" src="${path}/resources/images/default.jpg">
						</div>
					</form>
				</div>
			
		<div class="clear">
		</div>
		
		<div style="margin-left:475px;"><a href="#" onclick="imgPathSubmit()" class="easyui-linkbutton" >保存</a></div>
	</div>
	</div>
</body>
</html>