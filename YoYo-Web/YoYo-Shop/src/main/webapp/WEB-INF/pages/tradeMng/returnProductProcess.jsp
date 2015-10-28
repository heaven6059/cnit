<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理退换货</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/popup/magnific-popup.css" />
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?r=${versionVal}" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/popup/jquery.magnific-popup.min.js"></script>
<script type="text/javascript">
var interval = 1000;
var countDown;
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
	countDown = window.setInterval(function(){
		var d='<fmt:formatDate value="${returnProduct.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/>';
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
</script>
</head>
<body>
	<div id="member-main" class="member-main tk member-main2">
		<div class="title">退款退货管理</div>	
		<c:if test="${returnProduct.status==1}">
		<ul id="timecount" class="je_ac_limtime">
			<li><strong>距离处理申请还有：</strong></li>
			<li><a class="day">00</a>天</li>
			<li><a class="hour">00</a>小时</li>
			<li><a class="minute">00</a>分</li>
			<li><a class="second">00</a>秒</li>
		</ul>
		</c:if>	
		<div id="order-delivery">
			<div>
				<div class="note clearfix">
					<h3></h3>
					<div class="clearfix">
						<div class="span-auto colborder">
							订单编号：<span>${returnProduct.orderId}</span>
						</div>
						<div class="span-auto colborder">
							申请状态： <span>${returnProduct.refundText}</span>
						</div>
						<div class="span-auto last">
							申请时间：<span><fmt:formatDate value="${returnProduct.addTime}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
						</div>
					</div>
				</div>
			</div>
			<c:if test="${returnProduct.safeguardRequire==5}">
			<h4 class="lineheight-30">
				退款金额：<span class="color1 font14px">￥${returnProduct.amount}</span>
			</h4>
			</c:if>
			
			<h4 class="lineheight-30">
				售后要求：<span class="color1 font14px">${returnProduct.safeguardRequireText}</span>
			</h4>
			
			<h4 class="lineheight-30">
				申请原因：<span class="color1 font14px">${returnProduct.afterSalesReasonText}</span>
			</h4>
			
			<h4 class="lineheight-30">
				申请人：<span class="color1 font14px">${returnProduct.memberName}</span>
			</h4>
			<h4 class="lineheight-30">
				联系电话：<span class="color1 font14px">${returnProduct.memberPhone}</span>
			</h4>
			<c:if test="${returnProduct.isSafeguard==2}">
			<h4 class="lineheight-30">
				联系地址：<span class="color1 font14px">${returnProduct.refundAddress}</span>
			</h4>
			</c:if>
			<c:if test="${!fn:startsWith(returnProduct.orderId,'333')}">
				<table width="100%" cellspacing="0" cellpadding="3"
					class="gridlist border-all">
					<colgroup>
						<col class="span-4">
						<col class="span-auto">
						<col class="span-4">
					</colgroup>
					<thead>
						<tr>
							<th>商品图片</th>
							<th>商品名称</th>
							<th>退货数量</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="reshipItem" items="${returnProduct.orderItems}">
						<tr>
							<td class="textcenter"><a href="${portalPath}/goodsManager/goodsIndex?goodsId=${reshipItem.goodsId}" target="_blank"><img width="50" height="50" src="${imgUrl}${reshipItem.picturePath}" /></a></td>
							<td class="textcenter"><a href="${portalPath}/goodsManager/goodsIndex?goodsId=${reshipItem.goodsId}" target="_blank">${reshipItem.name}</a></td>
							<td class="textcenter">${reshipItem.nums}</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:if>
			<h4>详细说明：</h4>
			<div class="division division22">${returnProduct.content}</div>
			
			<c:if test="${!empty returnProduct.memberImagePath}">
			<h4>买家举证:</h4>
			<div class="division">
				<c:forEach var="image" items="${fn:split(returnProduct.memberImagePath,',')}">
				<div class="pics J_Pics" style="float: left;margin-left: 2px;">
					<a style="width:193px;height:125px;" title="" href="${imgUrl}${image}">
						<img style="width: 80px; height: 80px;" class="J_IMGDD" src="${imgUrl}${image}">											
					</a>
				</div>
				</c:forEach>
				<div class="clearfix"></div>
			</div>
			</c:if>
			
			<c:if test="${returnProduct.status eq 1}">
			<div style="width: 180px; margin: 10px auto">
				<button type="button" onclick="do_agree();" class="btn">
					<span><span>同意</span></span>
				</button>
				<button type="button" onclick="do_send();" class="btn">
					<span><span>拒绝</span></span>
				</button>
			</div>
			</c:if>
			<!-- 提交举证 -->
			<h4 class="lineheight-30">售后申请记录：</h4>
			<c:forEach var="log" items="${returnProduct.logs}">
			<div class="division">
			${log.opName}(${log.roleText})： 
			${log.logText}<br> 日期：<fmt:formatDate value="${log.alttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
			</div>
			</c:forEach>
		</div>
		
		<form action="../returnProductManager/processReturnProduct" id="order-delivery-agree-process" style="display: none;" method="post"
			encoding="multipart/form-data" enctype="multipart/form-data">
			<div class="FormWrap">
				<div class="division">
					<h4 class="fontnormal">卖家留言：</h4>
					<textarea style="width: 98%" rows="5" cols="80" class="x-inputs x-input" name="sellerComment" id="agree-content" type="textarea"></textarea>
				</div>
			</div>
			<input type="hidden" value="2" name="status">
			<input type="hidden" value="${returnProduct.returnId}" name="returnId"> 
			<input type="hidden" value="${returnProduct.orderId}" name="orderId">
			<button type="button" class="btn" onclick="agreeReturnProduct();">
				<span>确定</span>
			</button>
			<button type="button" onclick="to_back('order-delivery-agree-process');" class="btn">
				<span>返回</span>
			</button>
		</form>

		<form action="../returnProductManager/processReturnProduct" id="order-delivery-refuse-process" style="display: none;" method="post" 
			encoding="multipart/form-data" enctype="multipart/form-data">
			<div class="FormWrap">
				<div class="division">
					<h4 class="fontnormal">拒绝原因：</h4>
					<textarea style="width: 98%" rows="5" cols="80" class="x-inputs x-input" name="sellerReason" id="return-content" type="textarea"></textarea>
				</div>
			</div>
			<input type="hidden" value="3" name="status">
			<input type="hidden" value="${returnProduct.returnId}" name="returnId"> 
			<input type="hidden" value="${returnProduct.orderId}" name="orderId">
			<button type="button" class="btn" onclick="disAgreeReturnProduct();">
				<span>拒绝申请</span>
			</button>
			<button type="button" onclick="to_back('order-delivery-refuse-process')" class="btn">
				<span>返回</span>
			</button>
		</form>
		<input id="isSafeguard" value="${returnProduct.isSafeguard}" type="hidden" />
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/tradeMng/shopReturnProductMng.js?r=${versionVal}"></script>
</body>
</html>

