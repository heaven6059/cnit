<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>编辑广告</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/scripts/biz/jquery.walidator.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${path}/resources/scripts/colorpicker/css/jquery.bigcolorpicker.css" /> 
<link type="text/css" rel="stylesheet"  href="${path}/resources/styles/site/adEdit.css?var=${time}" />

<script type="text/javascript" src="${path}/resources/scripts/jscolor/jscolor.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.walidator.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript"	src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript"	src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/colorpicker/js/jquery.bigcolorpicker.min.js"></script>
<c:if test="${not empty adJson}">
	<script type="text/javascript">
		var adJson = ${adJson};
	</script>
</c:if>
<script type="text/javascript" src="${path}/resources/scripts/biz/site/adEdit.js?var=${time}"></script>
</head>
<body>
	<div class="info">
		<form method="post" enctype="multipart/form-data">
			<input type="hidden" id="adId" name="adId" value="${adJson.adId}"/>
			<table id="edtiBody">
				<tbody>
					<tr>
						<td align="right"><span class="star">*</span>广告类型：</td>
						<td align="left">
							<input id="adType" name="adType" class="easyui-combobox" value="${adJson.adType}" data-options="panelHeight:'auto', disabled:true,valueField: 'label',textField: 'value',data: [{label: '0',value: '轮播'},{label: '1',value: '图片'},{label: '2',value: '文字'}]" />
						</td>
						<td align="right"><span class="star">*</span>广告名称：</td>
						<td align="left"><input type="text" id="name" name="name" disabled value="${adJson.name}"/></td>
					</tr>
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'intervalTime'}">
							<tr>
								<td align="right"><span class="star">*</span>轮播间隔：</td>
								<td align="left" colspan="3"><input id="intervalTime" name="intervalTime" />（毫秒）</td>
							</tr>
						</c:if>
					</c:forTokens>
					<tr>
						<td align="right">开始时间：</td>
						<td align="left">
							<input id="beginTime" name="beginTime" class="easyui-datetimebox"  data-options="required:true"  style="width:150px">
						</td>
						<td align="right">结束时间：</td>
						<td align="left">
							<input id="endTime" name="endTime" class="easyui-datetimebox"  data-options="required:true" style="width:150px">
						</td>
					</tr>
					<tr>
						<td align="right"><span class="star">*</span>启用状态：</td>
						<td align="left" colspan="3">
							<input id="enabled" name="enabled"  class="easyui-combobox" value="${adJson.enabled}" data-options="required:true, valueField:'enabled',editable:false,textField:'text', panelHeight:'auto',data:[{enabled: 'true',text: '开启'},{enabled: 'false',text: '关闭'}]"/>
						</td>
					</tr>
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'adDescription'}">
							<tr>
								<td align="right">广告说明：</td>
								<td align="left" colspan="3"><textarea class="fieldValue3" id="description" name="description" rows="3" >${adJson.description }</textarea></td>
							</tr>
						</c:if>
					</c:forTokens>
				</tbody>
				<tbody class="contentBody">
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'needPic'}">
							<tr class="contentTitle">
								<td align="right">
									<span title='cTitle'>图片</span>：
								</td>
								<td colspan="3">
									<div class="fileInputContainer1">
										<input type="file" name="imageFile" class="fileInput1" id="fileInput{contentNo}" onchange="previewAndUpload(this,'preImg{contentNo}','img{contentNo}',410,138,'fileInput{contentNo}','imgPath{contentNo}')" />
									</div>
									<c:forTokens items="${adJson.picSize}" delims="," var="size" varStatus="picStatus">
										<c:if test="${picStatus.first }"><c:set var="weight" value="${size}"/></c:if>
										<c:if test="${picStatus.index eq 1 }"><c:set var="height" value="${size}"/></c:if>
									</c:forTokens>
									<div class="imgSizeTips red">
										图片大小及宽高限制：宽=${weight}px,高=${height}px,大小不大于2m<br/>
										图片格式支持：.jpg,.png,.jpeg,.gif,.JPG,.PNG,.JPEG,.GIF
									</div>
									<div id="preImg{contentNo}">
										<img id="img{contentNo}" width="${weight}" height="${height}" class="uploadImg" />
									</div>
								</td>
							</tr>
							<c:forTokens items="${adJson.config}" delims="," var="conf" >
								<c:if test="${conf eq 'picUrl'}">
									<tr>
										<td align="right">
											<span class="star">*</span>图片地址：
										</td>
										<td align="left" colspan="3">
											 <input type="text" class="fieldValue3 readonly" readonly="readonly" name="imgPath" id="imgPath{contentNo}" >
										</td>
									</tr>
								</c:if>
							</c:forTokens>
							<c:forTokens items="${adJson.config}" delims="," var="conf" >
								<c:if test="${conf eq 'backgroundColor'}">
									<tr class="bgColor ">
										<td align="right">
											<span class="star">*</span>背景颜色：
										</td>
										<td align="left" colspan="3">
											 <input  class="selectColor" >
										</td>
									</tr>
								</c:if>
							</c:forTokens>
						</c:if>
					</c:forTokens>
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'adUrl'}">
							<tr>
								<td align="right">
									<span class="star">*</span>广告地址：
								</td>
								<td align="left" colspan="3">
									<input class="fieldValue3" name="destUrl" />
								</td>
							</tr>
						</c:if>
					</c:forTokens>
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'adTitle'}">
							<tr>
								<td align="right">广告标题：</td>
								<td align="left" colspan="3">
									<input class="fieldValue3" name="title" />
								</td>
							</tr>
						</c:if>
					</c:forTokens>
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'adContent'}">
							<tr>
								<td align="right">内容描述：</td>
								<td align="left" colspan="3">
									<input class="fieldValue3" name="desc" />
								</td>
							</tr>
						</c:if>
					</c:forTokens>
					<c:forTokens items="${adJson.config}" delims="," var="conf" >
						<c:if test="${conf eq 'orderNo'}">
							<tr class="orderNo">
								<td align="right">排序序号：</td>
								<td align="left" colspan="3">
									<input type="text" name="orderNo" />
								</td>
							</tr>
						</c:if>
					</c:forTokens>
					<tr>
						<td colspan="4" align="right"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="removeContent(this)">删除</a></td>
					</tr>
				</tbody>
				<tbody id="addContent">
					<tr>
					<td colspan="1">
						<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'"   onclick="javascript:addContent()">添加内容</a>
					</td>
					<td colspan="3">
					</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="confirm">
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'"  onclick="javascript:saveEdit()">保存</a>
			<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"  onclick="javascript:initAdValue()">取消</a>
		</div>			
	</div>
</body>
</html>