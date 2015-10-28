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
<title>卖家中心</title>
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<link href="${path}/resources/styles/sell.css" rel="stylesheet" type="text/css" media="screen, projection" />
</head>
<body>
	<div class="sell_ri fr">
	<div class="sell_ri_top">
	    <div class="sell_ri_top_le fl">
			<a target="_blank" href="${portalPath}/shop/index?companyId=${store.companyId}"><img alt="" src="${imgUrl}${store.company.image}"></a>
	    	<p><a target="_blank" href="${portalPath}/shop/index?companyId=${store.companyId}">${store.storeName}</a></p>
		</div>
	    <div class="sell_ri_top_ri fr">
           <div class="order_info">
		        <div class="order_info_le fl">
			        <ul>
				      	<li>
				      		<strong>订单：</strong>
				      		<div class="fl">
								<span class="color_1"><a href="${shopPath}/shopOrder/toOrderList?status=create&payStatus=0">待付款订单<font>（${tips.unPay}）</font></a></span>
								<span class="color_1"><a href="${shopPath}/shopOrder/toOrderList?status=unconfirm&payStatus=1">待确认订单<font>（${tips.unconfirm}）</font></a></span>
					      		<span class="color_1"><a href="${shopPath}/shopOrder/toOrderList?status=confirm&payStatus=1">待完成订单<font>（${tips.confirm}）</font></a></span>
					      		<span class="color_1"><a href="${shopPath}/complain/complainList">投诉的订单<font>（${tips.complain}）</font></a></span>
				      		</div>
				      	</li>

				      	<li>
				      		<strong>提醒：</strong>
				      		<div class="fl">
								<span class="color_1"><a href="${shopPath}/goods/loadPutawayList?marketableQ=0">待上架的宝贝<font>（${tips.putaway}）</font></a></span>
								<span class="color_1"><a href="${shopPath}/returnProductManager/toReturnProductList">待处理的售后<font>（${tips.aftersales}）</font></a></span>
								<span class="color_1"><a href="${shopPath}/orderRefundsManager/toRefundsList">待处理的退款<font>（${tips.refunds}）</font></a></span>
							</div>
						</li>

						<li class="none">
							<strong>商品：</strong>
							<div class="fl">
								<span class="color_1"><a href="${shopPath}/goods/loadGoodsList">出售中的商品<font>（${tips.selling}）</font></a></span>	
								<span class="color_1"><a href="${shopPath}/report/reportList">被举报的商品<font>（${tips.report}）</font></a></span>
				      		</div>
				    	</li>
			        </ul>
			    </div>
		    </div>
		</div>
		
	    <div class="cl_zhi"></div>
	</div>

		<!--订单-待评价-待付款开始-->
		<div id="all_orders" class="Plate member-main">
			<h4>
				<a id="nopayed" href="javascript:void(0);" class="current">店铺销售情况</a>				
			</h4>
			<div id="my_order" class="Plate_info">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" style="border-bottom: none" class="gridlist">
					<thead>
                        <tr class="firstTr">
                            <th style="width: 90px;">项目</th>
                            <th style="width: 225px;">订单总数</th>
                            <th style="width: 225px;">订单总金额</th>
                            <th style="width: 225px;">已付款订单总数</th>
                            <th style="width: 225px">已付款订单总金额</th>
                        </tr>                   
                        <tr>
                        	<td>日销量</td>
                        	<td>${orderSelles.dayCount}</td>
                        	<td>${orderSelles.daySumMoney}</td>
                        	<td>${orderSelles.dayPayCount}</td>
                        	<td>${orderSelles.dayPayMoney}</td>
                        </tr>
                        <tr>
                        	<td>月销量</td>
                        	<td>${orderSelles.monthCount}</td>
                        	<td>${orderSelles.monthSumMoney}</td>
                        	<td>${orderSelles.monthPayCount}</td>
                        	<td>${orderSelles.monthPayMoney}</td>
                        </tr>
                    </thead>
					<tbody>
						
					</tbody>
				</table>
			</div>
			
			<h4>
				<a id="nopayed" href="javascript:void(0);" class="current">店铺销量排行</a>				
			</h4>
			<div id="my_order" class="Plate_info">
				<table width="100%" cellspacing="0" cellpadding="0" border="0" style="border-bottom: none" class="gridlist">
					<thead>
                        <tr class="firstTr">
                            <th style="width: 90px">序号</th>
                            <th style="width: 125px">商品图片</th>
                            <th style="width: 425px;">商品名称</th>
                            <th style="width: 225px">商品价格</th>
                            <th style="width: 125px">销量</th>
                        </tr>
                        <c:forEach items="${topSales}" var="topSale" varStatus="status">                   
                        <tr>
                        	<td>${status.index+1}</td>
                        	<td>
                        		<a class="clearfix" href="${portalPath}/goodsManager/goodsIndex?goodsId=${topSale.goodsId}" target="_blank">
                        			<img width="50px" height="50px" src="${imgUrl}/${topSale.smallPic}" />
                        		</a> 
                        	</td>
                        	<td style="text-align: left;">
                        		<a class="clearfix" href="${portalPath}/goodsManager/goodsIndex?goodsId=${topSale.goodsId}" target="_blank">
                        			${topSale.name}
                        		</a>
                        	</td>
                        	<td>${topSale.price}</td>
                        	<td>${topSale.buyCount}</td>
                        </tr>
                        </c:forEach>
                    </thead>
					<tbody>

					</tbody>
				</table>
			</div>
		</div>		
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/membercenter/membercenter.js?v=${versionVal}"></script>
</body>
</html>

