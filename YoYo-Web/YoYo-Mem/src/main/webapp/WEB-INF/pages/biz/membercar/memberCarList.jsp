<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的车型</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>

<link rel="stylesheet" type="text/css" href="${path}/resources/styles/membercar/person.css?v=${versionVal}" />

<script type="text/javascript">
$(function (){
	$(".mycarlist").bind("mouseenter",function (){
		$(this).find(".changebut").show();
		$(this).find(".closebut").show();
		$(this).addClass("overfoverever");
	}).bind("mouseleave",function (){
		$(this).find(".changebut").hide();
		$(this).find(".closebut").hide();
		$(this).removeClass("overfoverever");
	});
	$(".photo180").find("img").each(function (x,item){		
		if($(item).attr("src").indexOf("u5.png")==-1){
			$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
		}
	});
});
function delCar(id,isDefault){
	layer_utils.confirm("是否删除该车型?",function(){
		window.location.href="../membercar/deleteMemberCar?id="+id+"&isDefault="+isDefault;
	});
}
</script>
</head>
<body>
<div class="member-main member-main2">
	<div class="left_b">
		<div class="managecar">
			<div class="mtitle">
				<a href="../membercar/editMemberCar" class="pluscar hidefocus" style="display: block" >添加车型</a>我的车型库
			</div>
			
		  <c:forEach items="${memberCarDTOs.rows}" var = "memberCarDTO">
			<div class="mycarlist clearfix">
				<div class="changebut" style="display: none;">
					<a href="../membercar/editMemberCar?opType=Edit&id=${memberCarDTO.id}" class="hidefocus">修改</a>
				</div>
				<div class="closebut" style="display: none;">
					<a title="删除" href="javascript:void(0);" onclick="delCar(${memberCarDTO.id},${memberCarDTO.isDefault})" class="hidefocus" ></a>
				</div>
				<div class="myleft" >
					<dl class="clearfix">
						<dt class="copaiheight">
							<div class="photo180">
								<img src="<c:if test="${empty memberCarDTO.carLogo}">../resources/images/sign/u5.png</c:if><c:if test="${!empty memberCarDTO.carLogo}">${memberCarDTO.carLogo}</c:if>" width="180" height="180">
							</div>
							<div class="tipInfo" id="55201" name="2011">
							<c:if test="${memberCarDTO.isDefault==1}">
								<span id="aa"><i>默认车型</i></span>
							</c:if>
							<c:if test="${memberCarDTO.isDefault==0}">
								<a href="../membercar/setDefaulCar?id=${memberCarDTO.id}&isDefault=1">设为默认车型</a>
							</c:if>
							</div>
						</dt>
						<dd>
							<b></b><span id="getID">${memberCarDTO.carName }</span> 
						</dd>
					</dl>
				</div>
				<div class="myright">
					<dl>
						<dt>生产年月：</dt>
						<dd>${memberCarDTO.carYear}年${memberCarDTO.carProductionMonth}月</dd>
					</dl>
				</div>
				<div class="c"></div>
			</div>
		 </c:forEach>	
		</div>
	</div>
</div>
</body>
</html>

