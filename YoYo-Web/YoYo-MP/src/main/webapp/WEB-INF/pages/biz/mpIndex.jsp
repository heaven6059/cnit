<%@ page language="java"   contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.cnit.yoyo.util.Configuration" %> 
<!DOCTYPE html>
<%
   String path = request.getContextPath();
   request.setAttribute("path", path);
   application.setAttribute("imgUrl", Configuration.getInstance().getConfigValue("images.url"));
   application.setAttribute("portalPath", Configuration.getInstance().getConfigValue("portal.url"));
   application.setAttribute("versionVal", Configuration.getInstance().getConfigValue("version"));
   response.addHeader("Pragma", "no-cache");
   response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
   response.addHeader("Cache-Control", "pre-check=0, post-check=0");
   response.setDateHeader("Expires", 0);
%>
<html>
<head>
<title>YOYO汽车电商</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?t=${versionVal }" />
<link type="text/css" href="${path}/resources/styles/style.css" rel="stylesheet">
<link type="text/css" href="${path}/resources/styles/framework.css" rel="stylesheet">
</head>
<body>
	<div class="dashbd-bd">
	 <div class="dashbd-head clearfix">
		<div class="dashbd-headl span-auto">业务概览</div>
		<div class="flt sort">
			<div class="span-auto">
				<img stye="cursor:pointer;float:left" app="desktop" class="move up" src="/bbc3/app/desktop/statics/bundle/icon_asc.gif" style="opacity: 0.2; visibility: visible; display: none;">						</div>
			<div class="flt">
				<img stye="cursor:pointer;float:left" app="desktop" class="move down" src="/bbc3/app/desktop/statics/bundle/icon_desc.gif" style="opacity: 1; visibility: visible; display: none;">						</div>
		</div>
	</div>
	<div class="dashbd-list">
			<div class="clearfix dashbd-row">
   			<h4>订单：</h4>
				<table cellspacing="0" cellpadding="0">
					<tbody>
						<tr>
                        	<td <c:if test="${ShopSellsInfo.dayCount<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">日订单总数<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.dayCount}" default="0"></c:out>)</span></a></td>
                        	<td <c:if test="${ShopSellsInfo.daySumMoney<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">日订单总金额<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.daySumMoney}" default="0"></c:out>)</span></a></td>
                        	<td <c:if test="${ShopSellsInfo.dayPayCount<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">日已付款订单总数<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.dayPayCount}" default="0"></c:out>)</span></a></td>
                        	<td <c:if test="${ShopSellsInfo.dayPayMoney<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">日已付款订单总金额<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.dayPayMoney}" default="0"></c:out>)</span></a></td>
                        </tr>
                        <tr>
                        	<td <c:if test="${ShopSellsInfo.monthCount<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">月订单总数<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.monthCount}" default="0"></c:out>)</span></a></td>
                        	<td <c:if test="${ShopSellsInfo.monthSumMoney<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">月订单总金额<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.monthSumMoney}" default="0"></c:out>)</span></a></td>
                        	<td <c:if test="${ShopSellsInfo.monthPayCount<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">月已付款订单总数<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.monthPayCount}" default="0"></c:out>)</span></a></td>
                        	<td <c:if test="${ShopSellsInfo.monthPayMoney<=0}">class="figure-zero"</c:if>><a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">月已付款订单总金额<span class="dashbd-figure">(<c:out value="${ShopSellsInfo.monthPayMoney}" default="0"></c:out>)</span></a></td>
                        </tr>
					</tbody>
				</table>
			</div>
			<div class="clearfix dashbd-row">
			    <h4>会员：</h4>
			    <table cellspacing="0" cellpadding="0">
				    <tbody>
					    <tr>
					        <td class="figure-zero">
					            <a  href="javascript:void(0)" onclick="window.parent.addTabs('/member/members','会员列表')">今日新增<span class="dashbd-figure">(${AccountInfo.todayAccount})</span></a>
					        </td>
					        <td class="">
					            <a href="javascript:void(0)" onclick="window.parent.addTabs('/member/members','会员列表')">当月新增<span class="dashbd-figure">(${AccountInfo.monthAccount})</span></a>
					        </td>
					        <td class="">
					            <a href="javascript:void(0)" onclick="window.parent.addTabs('/member/members','会员列表')">会员总数<span class="dashbd-figure">(${AccountInfo.totalAccount})</span></a>
					        </td>
					    </tr>
				    </tbody>
			    </table>
			</div>
			<div class="clearfix dashbd-row">
			
			    <h4>商品：</h4>
			    <table cellspacing="0" cellpadding="0">
			    <tbody>
			    <tr>
			        <td <c:if test="${GoodsStatistics.totalCount<=0}">class="figure-zero"</c:if>>
			            <a href="javascript:void(0)" onclick="window.parent.addTabs('/goods/index','商品列表')">商品总数<span class="dashbd-figure">(${GoodsStatistics.totalCount})</span></a>
			        </td>
			        <td <c:if test="${GoodsStatistics.sellsCount<=0}">class="figure-zero"</c:if>>
			            <a href="javascript:void(0)" onclick="window.parent.addTabs('/goods/index','商品列表')">销售商品<span class="dashbd-figure">(${GoodsStatistics.sellsCount})</span></a>
			        </td>
			        <td <c:if test="${GoodsStatistics.putawayCount<=0}">class="figure-zero"</c:if>>
			            <a href="javascript:void(0)" onclick="window.parent.addTabs('/goods/index','商品列表')">下架商品<span class="dashbd-figure">(${GoodsStatistics.putawayCount})</span></a>
			        </td>
			        <td <c:if test="${GoodsStatistics.checkCount<=0}">class="figure-zero"</c:if>>
			            <a href="javascript:void(0)" onclick="window.parent.addTabs('/goods/index','商品列表')">审核商品<span class="dashbd-figure">(${GoodsStatistics.checkCount})</span></a>
			        </td>
			        <td <c:if test="${GoodsStatistics.backCount<=0}">class="figure-zero"</c:if>>
			            <a href="javascript:void(0)" onclick="window.parent.addTabs('/goods/index','商品列表')">审核打回<span class="dashbd-figure">(${GoodsStatistics.backCount})</span></a>
			        </td>
			    </tr></tbody></table>
			
			</div>
		</div>
	</div>
	
	
	<div class="dashbd-bd">
		 <div class="dashbd-head clearfix">
			<div class="dashbd-headl span-auto">待处理事项</div>
			<div class="flt sort">
				<div class="span-auto">
					<img stye="cursor:pointer;float:left" app="desktop" class="move up" src="" style="opacity: 0.2; visibility: visible; display: none;">						</div>
				<div class="flt">
					<img stye="cursor:pointer;float:left" app="desktop" class="move down" src="" style="opacity: 0.2; visibility: visible; display: none;">						</div>
			</div>
		</div>
		<div class="dashbd-list">
			<div class="clearfix dashbd-row">

			<h4>订单：</h4>
			<table cellspacing="0" cellpadding="0">
				<tbody>
					<tr>
						<td class="">
							<a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">待付款订单<span class="dashbd-figure">(${OrderTips.unPay})</span></a>
						</td>
						<td class="">
							<a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">待确认订单</a><span class="dashbd-figure">(${OrderTips.unconfirm})
						</span></td>
						<td class="">
							<a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">待完成订单<span class="dashbd-figure">(${OrderTips.confirm})</span></a>
						</td>
						<td class="">
							<a href="javascript:void(0);" onclick="window.parent.addTabs('/order/index','订单列表')">投诉的订单<span class="dashbd-figure">(${OrderTips.complain})</span></a>
						</td>
					</tr>
				</tbody>
			</table>
			</div>
	</div>
 	</div>
</body>
</html>