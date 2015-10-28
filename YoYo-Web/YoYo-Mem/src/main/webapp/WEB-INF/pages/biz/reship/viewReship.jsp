<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page import="java.net.URLDecoder" %>
<%
   String path =  request.getContextPath();
   request.setAttribute("path", path);
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>返修退换货</title>
<%@include file="/resources/include/head.jsp" %>

<!-- 引用核心层插件 -->
<script type="text/javascript" >
var _path = "${path}"; 
</script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/popup/magnific-popup.css" />
<script type="text/javascript" src="${path}/resources/scripts/popup/jquery.magnific-popup.min.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css" />
<script type="text/javascript">
$(function (){
    $('.J_Pics').magnificPopup({
        delegate: 'a',
        type: 'image',
        closeOnContentClick: false,
        closeBtnInside: false,
        mainClass: 'mfp-with-zoom mfp-img-mobile',
        image: {
          verticalFit: true,            
        },
        gallery: {
          enabled: true
        },
        zoom: {
          enabled: true,
          duration: 300, // don't foget to change the duration also in CSS
          opener: function(element) {
            return element.find('img');
          }
        }
  });
});
var interval = 1000;
var countDown;
$(function (){
	countDown = window.setInterval(function(){
		var d='<fmt:formatDate value="${afterSales.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>';
		d = d.replace(/-/g,"/");
		var date = new Date(d);	
		date.setDate(date.getDate()+7);
		if(!compareDate(date,new Date())){//超时
			showCountDown(date);
		}else{
			window.clearInterval(countDown);
		}
	}, interval);
});

function showCountDown(endDate){
	var now = new Date(); 
	var leftTime=endDate.getTime()-now.getTime(); 
	var leftsecond = parseInt(leftTime/1000); 
	
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
	if(day1==0&&hour==0&&minute==0&&second==0){
		$("#form_1").submit();
	}
	$(".day").html(pad(day1))+"天"+$(".hour").html(pad(hour,2))+"小时"+$(".minute").html(pad(minute,2))+"分"+$(".second").html(pad(second,2))+"秒";
}
function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}
function appealAfterSales(){
	$("#form_1").attr("action","../reship/appealAfterSales");
	$("#form_1").submit();
}
</script>
</head>

<body>
	<form id="form_1" action="../reship/autoProcessAfterSales" method="post">
		<input type="hidden" name="returnId" value="${afterSales.returnId}"/>
		<input type="hidden" name="orderId" value="${afterSales.orderId}"/>
	</form>
	
	<div class="member-main tk2">
		<div class="title title2">退款退货管理</div>
		<c:if test="${afterSales.status==1}">
		<ul id="timecount" class="je_ac_limtime">
			<li><strong>距离同意申请还有：</strong></li>
			<li><a class="day">00</a>天</li>
			<li><a class="hour">00</a>小时</li>
			<li><a class="minute">00</a>分</li>
			<li><a class="second">00</a>秒</li>
		</ul>
		</c:if>
		<div class="note clearfix">
			<h3></h3>
			<div class="clearfix">
				<div class="span-auto colborder pl10">				
					订单编号：<span>${afterSales.orderId}</span>
				</div>
				<div class="span-auto colborder">
					申请状态： <span>${afterSales.refundText}</span>
				</div>

				<div class="span-auto last">
					申请时间：<span><fmt:formatDate value="${afterSales.addTime}" pattern="yyyy-MM-dd hh:mm:ss"/></span>
				</div>
			</div>
		</div>
		<c:if test="${afterSales.isSafeguard==2&&afterSales.applyNums>0}">
		<h4 class="lineheight-30">
			申请数量：<span class="color1 font14px">${afterSales.applyNums}</span>
		</h4>
		</c:if>
		<c:if test="${afterSales.safeguardRequire==5}">
		<h4 class="lineheight-30">
			退款金额：<span class="color1 font14px">￥<fmt:formatNumber value="${afterSales.amount}" /></span>
		</h4>
		
		</c:if>
		<h4 class="lineheight-30">
			售后要求：<span class="color1 font14px">${afterSales.safeguardRequireText}</span>
		</h4>
		<h4 class="lineheight-30">
			申请原因：<span class="color1 font14px">${afterSales.afterSalesReasonText}</span>
		</h4>
		
		<h4 class="lineheight-30">
			申请人：<span class="color1 font14px">${afterSales.memberName}</span>
		</h4>
		<h4 class="lineheight-30">
			联系电话：<span class="color1 font14px">${afterSales.memberPhone}</span>
		</h4>
		<h4 class="lineheight-30">
			联系地址：<span class="color1 font14px">${afterSales.refundAddress}</span>
		</h4>
		
		<h4 class="lineheight-30">申请售后的商品：</h4>
		<table width="100%" cellspacing="0" cellpadding="3"
			class="gridlist border-all gridlist_member">
			<colgroup>
				<col class="span-4">
				<col class="span-auto">
				<col class="span-4">
			</colgroup>
			<thead>
				<tr>
					<th>图片</th>
					<th>商品名称</th>
					<th>退货数量</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${afterSales.orderItems}" var="orderItem">
				<tr>
					<td class="textcenter"><img height="50px" width="50px" src="${imgUrl}/${orderItem.picturePath}"></td>
					<td class="textcenter"><a href="${portalPath}/goodsManager/goodsIndex?goodsId=${orderItem.goodsId}" target="_blank">${orderItem.name}</a></td>
					<td class="textcenter">${orderItem.nums}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		<h4>详细说明:</h4>
		<div class="division">${afterSales.content}</div>
		
		<c:if test="${!empty afterSales.sellerComment}">
		<h4>退款留言：</h4>
		<div class="division division22">
			${afterSales.sellerComment}
		</div>
		</c:if>
			
		<c:if test="${!empty afterSales.sellerReason}">
		<h4>拒绝原因：</h4>
		<div class="division division22">
			${afterSales.sellerReason}
		</div>
		</c:if>
		<c:if test="${!empty afterSales.memberImagePath}">
		<h4>买家举证:</h4>
		<div class="division">
			<c:forEach var="image" items="${fn:split(afterSales.memberImagePath,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>
		</c:if>
		
		<c:if test="${!empty afterSales.interevenImage}">
		<h4>卖家举证:</h4>
		<div class="division">
			<div>
				${afterSales.interevenComment}
			</div>
			<c:forEach var="image" items="${fn:split(afterSales.interevenImage,',')}">
			<div class="pics J_Pics" style="float: left;margin-left: 2px;">
				<a style="width:193px;height:125px;" title="" href="${imgUrl}/${image}">
					<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}/${image}">											
				</a>
			</div>
			</c:forEach>
			<div class="clearfix"></div>
		</div>									
		</c:if>
		<c:if test="${afterSales.status eq 3}">
		<div style="width: 180px; margin: 10px auto">
			<button type="button" onclick="appealAfterSales();" class="btn">
				<span><span>平台申诉</span></span>
			</button>
		</div>
		</c:if>		
		<h4 class="lineheight-30">售后申请记录：</h4>
		<c:forEach items="${afterSales.logs}" var="log">
		<div class="division">
			${log.roleText} ：${log.logText}<br> 日期：<fmt:formatDate value="${log.alttime}" pattern="yyyy-MM-dd HH:mm:ss" />
		</div>
		</c:forEach>
	</div>
</body>
</html>