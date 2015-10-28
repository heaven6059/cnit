<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺首页管理</title>

<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?t=${time}" rel="stylesheet" />
<style type="text/css">
.upload_btn {
    height: 31px;
    margin-bottom: 3px;
}

.shop_logo {
    border: 3px solid #dadada;
    border-radius: 5px;
    height: 120px;
    text-align: center;
    width: 120px;
}

.upLoad {
    background: #fff none repeat scroll 0 0;
    border: 1px solid #dadada;
    display: none;
    float: left;
    height: 31px;
    margin: 0 0 0 10px;
    width: 80px;
}

.fileInput {
    cursor: pointer;
    font-size: 300px;
    height: 31px;
    opacity: 0;
    overflow: hidden;
    position: absolute;
    right: 0;
    top: 0;
    width: 101px;
}


.fileInputContainer {
    background: rgba(0, 0, 0, 0) url("../resources/images/btn1.jpg") repeat scroll left center;
    float: left;
    height: 31px;
    margin-left: 5px;
    position: relative;
    width: 101px;
}
</style>
</head>
<body>
<div class="member-main member-main2">
<form action="../shopIndexManager/editShopIndexBanner" method="post" id="banner_form">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="bottom_right_talbel">
		<thead>
			<tr>
				<th width="20%">关键字名称</th>
				<th width="40%">关键字链接</th>
				<th width="20%">图片</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach var="rightPush" items="${shopIndexPush.rightPushs}" varStatus="status">
			<tr>
				<td><input type="text" validate='{"msg":"标题不能为空","type":"isNull"}' name="bottom-right-keyword" value="${rightPush.title}" class="x-input" style="width:160px"/></td>
				<td><input type="text" validate='{"msg":"请输入正确的链接地址","type":"isHref"}' name="bottom-right-href" value="${rightPush.href}" class="x-input" style="width:320px"/></td>
				<td>
					<form method="post" enctype="multipart/form-data">
						<div id="preBrandLogo${status.index}" class="shop_logo">
							<img id="imgBrandLogo${status.index}" class="lazyImg" src="${rightPush.img}" width="120" height="120" />
						</div>
						
						<div class="upload_btn">
							<div class="fileInputContainer">
								<input type="file" name="imageFile" id="imageFile${status.index}" class="fileInput" onchange="previewImage(this,'preBrandLogo${status.index}','imgBrandLogo${status.index}',100,100)" />
							</div>
							<input type="button" onclick="submitForm('imageFile${status.index}','keywordImg${status.index}','imgBrandLogo${status.index}')" class="upLoad" value="上传" />
						</div>
					</form>
					<input type="hidden" validate='{"msg":"请上传图片","type":"isNull"}' name="bottom-right-Img" value="${rightPush.img}" id="keywordImg${status.index}">
				</td>
				<td><a href="javascript:;" class="del_cur_row">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="title" style="text-align: center;margin-top: 5px;">
		<input name="id" type="hidden" value="${shopIndexPush.id}" />
		<button class="btn search1" id="bottom_right_keyword_add_rows" type="button"><span><span>添加</span></span></button>
		<button class="btn search1" id="bottom_right_keyword_save" type="button"><span><span>保存</span></span></button>
		<button class="btn search1" onclick="history.go(-1);" type="button"><span><span>返回</span></span></button>
	</div>
</form>
</div>
<script type="text/javascript">

$(function (){
	$("#bottom_right_talbel").on("click",".del_cur_row",function (){
		$(this).parent().parent().remove();
	});
	
	$("#bottom_right_keyword_save").click(function (){		
		validate_utils.validate();
		if(validate_utils.result){
			$("#banner_form").submit();
		}
	});
	
	$(".lazyImg").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	
	$("#bottom_right_keyword_add_rows").click(function (){		
		var html=[],index=$("#bottom_right_talbel").find("tr").length;
		if(index+1>8){
			layer.msg("最多只能添加7个！",{time:1500});
			return;
		}
		html.push('<tr>');				
		html.push('<td><input type="text" validate=\'{"msg":"标题不能为空","type":"isNull"}\' name="bottom-right-keyword" class="x-input" style="width:160px"/></td>');
		html.push('<td><input type="text" validate=\'{"msg":"请输入正确的链接地址","type":"isHref"}\' name="bottom-right-href" class="x-input" style="width:280px"/></td>');
		html.push('<td>');
		html.push('<form method="post" enctype="multipart/form-data">');
		html.push('<div id="preBrandLogo'+(index)+'" class="shop_logo">');
		html.push('<img id="preview'+(index)+'" src="../resources/images/pre_default.png" width="120" height="120">');
		html.push('</div>');
		html.push('<div class="upload_btn">');
		html.push('<div class="fileInputContainer">');
		html.push('<input type="file" name="imageFile" id="brandLogo'+(index)+'" class="fileInput" onchange="previewImage(this,\'preBrandLogo'+(index)+'\',\'preview'+(index)+'\',120,120)" />');
		html.push('</div>');
		html.push('<input type="button" onclick="submitForm(\'brandLogo'+(index)+'\',\'keywordImg'+(index)+'\',\'preview'+(index)+'\')" class="upLoad" value="上传" />');
		html.push('</div>');
		html.push('</form>');
		html.push('<input type="hidden" validate=\'{"msg":"请上传图片","type":"isNull"}\' name="bottom-right-Img" id="keywordImg'+(index)+'\">');
		html.push('</td>');
		html.push('<td><a href="javascript:;" class="del_cur_row">删除</a></td>');
		html.push('</tr>');
		$("#bottom_right_talbel").append(html.join(""));
	});
});
var validate_utils={
	result:true,
	validate:function (){
		var inputs=$("#bottom_right_talbel").find("input[name^='bottom-right']");
		if(inputs.length==0){
			layer.msg("请完善信息",{time:3000});
			validate_utils.result = false;
			return;
		}
		for(var i=0;i<inputs.length;i++){
			var item=inputs[i];
			if($(item).attr("validate")){
				var required=JSON.parse($(item).attr("validate"));
				if(required.type=="isNull"){
					if($(item).val().length==0){
						layer.msg(required.msg,{time:3000});
						validate_utils.result = false;						
						 break;
					}
				}else if(required.type=="isHref"){
					var Expression=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
					var objExp=new RegExp(Expression);
					if(!objExp.test($(item).val())){
						layer.msg(required.msg,{time:3000});
						validate_utils.result=false;
						break;
					}
				}
			}
			validate_utils.result=true;
		}
	}
};

</script>
</body>
</html>



