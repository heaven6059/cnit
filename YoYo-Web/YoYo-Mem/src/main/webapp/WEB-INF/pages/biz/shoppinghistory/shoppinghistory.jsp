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
<title>我最近购买的商品</title>
<style type="text/css">

</style>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
</head>
<body>
    <div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">我最近购买的商品</div>
			<div class="clr"></div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist">
				<tbody>
					<tr>
						<th width="60"></th>
						<th>商品名称</th>
						<th width="200px">购买时间</th>
						<th width="100px">商品单价</th>
						<th width="100px">操作</th>
					</tr>
					<c:forEach var="history" items="${history}">
					<tr class="itemsList">
						<td class="horizontal-m" style="white-space:normal">
					        <div class="goodpic">
					            <a href="${portalPath}/goodsManager/goodsIndex?goodsId=${history.goodsId}" style="" target="_blank">
					    			<img alt="${history.productName}" src="${history.picturePath}" width="50" height="50" />
					  			</a>
					       	</div>
					  	</td>
					  	<td align="center">
					  	<div class="goods-main">
					  		<div class="goodinfo" style="text-align: left;">
					  				<a title="${history.productName}" href="${portalPath}/goodsManager/goodsIndex?goodsId=${history.goodsId}" target="_blank" class="font-blue">${history.productName}</a>
					   			</div>
					  		</div>
					  	</td>
					  	<td align="center" class="price-button">
					  		<fmt:formatDate value="${history.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
						</td>					    
					   	<td align="center" class="price-button">
					        	￥<fmt:formatNumber value="${history.price}"/>
						</td>					    
					  	<td align="left" class="member-fav" style="vertical-align:middle">
					    	<a rel="nofollow" title="加入购物车" class="btn-bj-hover operate-btn" href="${portalPath}/goodsManager/goodsIndex?goodsId=${history.goodsId}" style="" target="_blank">再次购买</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	$(function (){
		$(".gridlist").find("img").each(function (x,item){
			$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"))
		});
	});

	function addToCart(productId){
		$.ajax({
		    url:yoyo.portalUrl+'/cart/addCart',
		    data:{productId:productId,quantity:1},
		    type:'post',    
		    cache:false,    
		    dataType:'json',    
		    success:function(data){
		    	if(data.retCode==0000){
		    		if(data.content.result){
		    			layer_utils.confirm("加入购物车成功 是否前往购物车结算?",function (){
		    				window.location.href=yoyo.portalUrl+"/cart/index";
		    			});
		    		}else{
		    			layer.msg(data.content.msg,{icon:0,shade: [0.3, '#393D49'],time:1500});
		    		}
		    	}else{
		    		layer_utils.alert("加入购物车失败");
		    	}
		    }
		});
	}
	</script>
</body>
</html>

