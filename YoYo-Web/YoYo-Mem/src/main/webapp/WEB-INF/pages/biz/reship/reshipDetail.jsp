<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>  
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>售后服务信息详情</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/complain.css" />
<%@include file="/resources/include/head.jsp" %>

<link rel="stylesheet" href="${path}/resources/styles/reship/reship.css"	type="text/css">
<link rel="stylesheet" href="${path}/resources/scripts/imageUpload/zyUpload.css"	type="text/css">
<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script src="${path}/resources/scripts/biz/common.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/addReship.js"></script>
</head>
<body>
	<div class="shop_manager_right ">
		<div class="title ">售后服务信息详情</div>
		<form  id='formId' class="section">
			<input type="hidden" name="goodsId" value="${goodsId}"> 
			<input type="hidden" name="storeId" value="${storeId}"> 
			<input type="hidden" name="goodsType" value="${goodsType}"> 
			<input type="hidden" name="productBn" value="${bn}"> 
		    <input type="hidden" id = "imagePath" name="memberImagePath" >
			<div id="gEditor-Body">
				<div class="FormWrap" style="background: none">

					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="liststyle data width1" style="border: none">

						<tr>
							<th><em><font color="red">*</font></em>订单号：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="orderId" value="${reshipDTO.orderId}" readonly = "readonly"/></td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>商品名称：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="productName" value="${reshipDTO.productName}" readonly = "readonly"/></td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>卖家：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="storeName" value="${reshipDTO.storeName}" readonly = "readonly"/></td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>退货数量：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="number"  value = "${reshipDTO.number}" readonly = "readonly"/></td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>联系人姓名：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="memberName"  value = "${reshipDTO.memberName }" readonly = "readonly"/></td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>联系人手机：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="memberPhone" value = "${reshipDTO.memberPhone } "readonly = "readonly"/></td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>联系人地址：</th>
							<td><input autocomplete="off" class="x-input inputstyle"
								type="text" name="refundAddress"  value = "${reshipDTO.refundAddress } " readonly = "readonly"/></td>
						</tr> 

						<tr>
							<th><em><font color="red">*</font></em>问题描述：</th>
							<td><textarea type="textarea" class="x-input" name="content"
									style="resize: none;" cols="50" rows="5" maxth="255" readonly = "readonly">${reshipDTO.content }</textarea>
								<div class="notice-inline">描述购买的商品、服务的质量问题</div></td>
						</tr>
					<c:forEach var="image" items="${fn:split(reshipDTO.memberImagePath,',')}">
						<tr>
						    <th>买家上传凭证 ：</th> 
							<td>
							<div >
								<div>
									<img style="width: 80px; height: 80px;" class="J_IMGDD"
										src="http://10.255.8.17:8082/imageUrl${image}">											
								</div>
							</div>
						   </td>
						<tr>
					</c:forEach>	
						<tr>
							<th>卖家留言举证：</th>
							<td><textarea type="textarea" class="x-input" name="interevenComment" readonly="readonly"
									style="resize: none;" cols="50" rows="5" maxth="255">${reshipDTO.interevenComment }</textarea>
								<div class="notice-inline"></div></td>
						</tr>						
						<tr>
							<th>卖家上传凭证：</th>
							<td><textarea type="textarea" class="x-input" name="interevenImage" readonly="readonly"
									style="resize: none;" cols="50" rows="5" maxth="255">${reshipDTO.interevenImage }</textarea>
								<div class="notice-inline"></div></td>
						</tr>						
				
					<!-- 	<tr>
							<th>上传凭证相册 ：</th> 
							<td>
								<div class="goods-image-div" id="goods_picture"></div>
								<div style="clear: both;"></div>
							</td>
						</tr> -->

					</table>
					</div>
		<!-- <div style="text-align: center; margin-top: 20px;">
			<button class="btn btn-primary" type="button" id="save_button">
				<span><span>保存</span></span>
			</button>
		</div> -->
		</form>
	</div> 

			

</body>
</html>