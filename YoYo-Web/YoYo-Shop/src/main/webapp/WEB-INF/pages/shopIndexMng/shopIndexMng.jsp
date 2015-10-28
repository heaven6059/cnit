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
<link type="text/css" rel="stylesheet" href="${path }/resources/styles/shopEnter/shopRegister.css?r=${versionVal}">
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?t=${time}" rel="stylesheet" />

<style type="text/css">
.upload_btn {
    height: 31px;
    margin-bottom: 3px;
}

.shop_logo {
    border: 3px solid #dadada;
    border-radius: 5px;
    height: 90px;
    text-align: center;
    width: 200px;
	background:none;
}
.shop_logo img{
	padding-top: 15px;
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

/*.fileInput {
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
}*/
</style>
</head>
<body>
	<div class="member-main member-main2">
		<div class="title">店铺首页管理</div>
		<div class="title">
		<a href="${portalPath}/shop/index?companyId=${memberListDo.companyId}" target="_blank">进入店铺</a>
		</div>
		<div class="title">焦点图模块</div>
		<form action="../shopIndexManager/saveFocusAd" method="post" id="focusForm">
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="focus_img_talbel">
			<thead>
				<tr>
					<th width="20%">焦点图描述</th>
					<th width="40%">焦点图链接</th>
					<th width="20%">焦点图</th>
					<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody  style="">
			<c:forEach var="shopFocusAd" items="${shopFocusAd}" varStatus="status">
				<tr>	
					<td ><input type="text" name="focuskeyword" value="${shopFocusAd.focusTitle}" class="x-input" style="width:160px"/></td>
					<td ><input type="text" name="focushref" value="${shopFocusAd.focusUrl }" class="x-input" style="width:280px"/></td>
					<td style="position:relative">
                         <div style="position:absolute; right:-80px; top:55px;  z-index:999999999999999">
                                <a href="javascript:;" onclick="deleteFocusAd('../shopIndexManager/deleteFocusAd?id=${shopFocusAd.id}');">删除</a>
                                <c:if test="${shopFocusAd.focusEnabled==1}">
                                <a href="javascript:;" onclick="enableMsg('../shopIndexManager/eanbleFocusAd?id=${shopFocusAd.id}&focusEnabled=0');">禁用</a>
                                </c:if>
                                <c:if test="${shopFocusAd.focusEnabled==0}">
                                <a href="javascript:;" onclick="enableMsg('../shopIndexManager/eanbleFocusAd?id=${shopFocusAd.id}&focusEnabled=1');">发布</a>
                                </c:if>
                        </div>
                        
                        <form method="post" enctype="multipart/form-data" >
                            <div id="preFocusAd${status.index}" class="shop_logo">
                                <img id="preview${status.index}" src="${shopFocusAd.focusImg}" width="180" height="60">
                            </div>
                            <div class="upload_btn">
                            <div class="fileInputContainer">
                                
                                <input type="file" name="imageFile" id="focusImg${status.index}" class="fileInput" onchange="previewImage(this,'preFocusAd${status.index}','preview${status.index}',180,60)"/>
                            </div>
                                <input type="button" onclick="submitForm('focusImg${status.index}','focusImgVal${status.index}','preview${status.index}')" class="upLoad" value="上传" />
                            </div>
                        </form>
						<input type="hidden" name="focusImg" value="${shopFocusAd.focusImg}" id="focusImgVal${status.index}">
						<input type="hidden" name="focusId" value="${shopFocusAd.id}" />
					</td>
					<td s>&nbsp;
                         
					</td>	

				</tr>
			</c:forEach>
			</tbody>
		</table>
        <div style="clear:both"></div>
		<div class="title" style="text-align: center;margin-top: 5px;">
			<button class="btn search1" id="focus_img_add_rows" type="button"><span><span>添加</span></span></button>
			<button class="btn search1" id="focus_img_save" type="button" onclick="subFocusAd();"><span><span>保存</span></span></button>
		</div>
		</form>
		
		<div class="title">内容模块</div>
		<div class="lineh b4">
			<button id="btnsearch" onclick="window.location.href='../shopIndexManager/toEditShopIndexPush'" class="btn search1" type="button">
				<span><span>添加模块</span></span>
			</button>
		</div>
		
		<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="shop_order_list">
				<tbody>
					<tr>
						<th width="30%">模块标题</th>
						<th width="30%">修改时间</th>
						<th width="30%">是否发布</th>
						<th width="10%">操作</th>
					</tr>
				</tbody>
				<c:forEach var="shopIndexPush" items="${shopIndexPush}">
				<tr>
					<td>${shopIndexPush.title}</td>
					<td><fmt:formatDate value="${shopIndexPush.lastModify}" pattern="yyy-MM-dd hh:mm:ss"/></td>
					<td>
						<c:if test="${shopIndexPush.enabled==1}">发布</c:if>
						<c:if test="${shopIndexPush.enabled==0}">未发布</c:if>
					</td>
					<td>
						<c:if test="${shopIndexPush.enabled==0}">
							<a href="../shopIndexManager/toEditShopIndexPush?type=Edit&id=${shopIndexPush.id}">编辑</a>
							<a href="javascript:;" onclick="deleteFocusAd('../shopIndexManager/deleteShopIndexPush?id=${shopIndexPush.id}')">删除</a>
							<a href="javascript:;" onclick="enableMsg('../shopIndexManager/updateShopIndexPush?type=Edit&enabled=1&id=${shopIndexPush.id}');">发布</a>
						</c:if>
						<c:if test="${shopIndexPush.enabled==1}">
							<a href="javascript:;" onclick="enableMsg('../shopIndexManager/updateShopIndexPush?type=Edit&enabled=0&id=${shopIndexPush.id}');">停用</a>
						</c:if>
					</td>
				</tr>
				</c:forEach>
		</table>
	</div>
<script type="text/javascript">
$("#focus_img_add_rows").click(function (){		
	var html=[],index=$("#focus_img_talbel").find("tr").length;
	if(index+1>8){
		layer.msg("最多只能添加7个！",{time:1500});
		return;
	}
	html.push('<tr>');				
	html.push('<td><input type="text" name="focuskeyword" class="x-input" style="width:160px"/></td>');
	html.push('<td><input type="text" name="focushref" class="x-input" style="width:280px"/></td>');
	html.push('<td>');
	html.push('<form method="post" enctype="multipart/form-data">');
	html.push('<div id="preFocusAd'+(index)+'" class="shop_logo">');
	html.push('<img id="preview'+(index)+'" src="../resources/images/pre_default.png" width="180" height="60">');
	html.push('</div>');
	html.push('<div class="upload_btn">');
	html.push('<div class="fileInputContainer">');
	html.push('<input type="file" name="imageFile" id="focusImg'+(index)+'" class="fileInput" onchange="previewImage(this,\'preFocusAd'+(index)+'\',\'preview'+(index)+'\',180,60)" />');
	html.push('</div>');
	html.push('<input type="button" onclick="submitForm(\'focusImg'+(index)+'\',\'focusImgVal'+(index)+'\',\'preview'+(index)+'\')" class="upLoad" value="上传" />');
	html.push('</div>');
	html.push('</form>');
	html.push('<input type="hidden" name="focusImg" id="focusImgVal'+(index)+'">');
	html.push('<input type="hidden" name="focusId"/>');
	html.push('</td>');
	html.push('<td><a href="javascript:;" class="del_cur_row">删除</a></td>');
	html.push('</tr>');
	$("#focus_img_talbel").append(html.join(""));
});

$("#focus_img_talbel").on("click",".del_cur_row",function (){
	$(this).parent().parent().remove();
});
$("#focus_img_talbel").find("img").each(function (x,img){
	$(img).attr("src",yoyo.imagesUrl+$(img).attr("src"));
});

function enableMsg(href){
	layer_utils.confirm("是否确认修改发布状态?",function (){
		window.location.href=href;
	});	
}

function deleteFocusAd(href){
	layer_utils.confirm("删除后将无法恢复是否继续?",function (){
		window.location.href=href;
	});
}

function subFocusAd(){
	var pass=true;
	$("#focus_img_talbel").find("input[name^='focus']").each(function (x,input){
		if($(input).attr("name")=='focuskeyword'&&$(input).val().length==0){			
			layer.alert("焦点图描述不能为空请完善！",{title:false,closeBtn:false,icon:0});
			pass=false;
			return pass;
		}
		if($(input).attr("name")=='focushref'){
			var Expression=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
			var objExp=new RegExp(Expression);
			if(!objExp.test($(input).val())){
				console.log($(input).val());
				layer.alert("请完善正确的焦点图链接！",{title:false,closeBtn:false,icon:0});
				pass=false;
				return pass;
			}
		}
		
		if($(input).attr("name")=='focusImg'&&$(input).val().length==0){
			layer.alert("请上传焦点图！",{title:false,closeBtn:false,icon:0});
			pass=false;
			return pass;
		}
	});
	if(pass){
		$("#focusForm").submit();
	}
}
</script>
</body>
</html>


