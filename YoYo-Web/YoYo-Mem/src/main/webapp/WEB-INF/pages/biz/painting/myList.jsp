<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
	request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的钣金</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/painting/myList.css?v=${time}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css" />
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />

<script type="text/javascript" src="${path}/resources/scripts/biz/common.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/paintingUtil.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/myList.js?v=${time}"></script>

</head>
<body>
	<div class="sell_main ml10 fl">
		<!--nav-->
		<div class="sell_title">
			<div class="title_border">
				<span>我的钣金</span>
			</div>
		</div>
		
		<div class="Plate">
			<h4>
				受损单状态：
				<a class="current" href="../mypainting/myDamageList">全部</a>  
				<a href="../mypainting/myDamageList?passStatus=0">审核中</a>
				<a href="../mypainting/myDamageList?passStatus=1">报价中</a>
				<a href="../mypainting/myDamageList?passStatus=2">审核失败</a>
				<a href="../mypainting/myDamageList?passStatus=3">已成交</a>
			</h4>
		</div>
        <div class="sell_con">
            <!--近期订单-->
            <div class="buy_list clearfix">
                <table width="970" border="1" style="border: 1px solid #ddd;" class="sell_table">
                	<thead>
                        <tr class="firstTr">
                            <th colspan="2" style=" width: 580px;">受损单信息</th>
                            <th style=" width: 118px;">下单时间</th>
                            <th style=" width: 118px;">
                                	状态
                            </th>
                            <th>详情</th>
                        </tr>
                    </thead>
                    <c:forEach items="${damageList.rows}" var="damage" varStatus="vstatus" >
                    	<c:forEach items="${damage.detailList}" var="detail" varStatus="status">
	                    	<c:choose>
	                   			<c:when test="${vstatus.index % 2 ==0 }">
	                   				 <tr>
	                   			</c:when>
	                   			<c:otherwise>
	                   				 <tr style="background-color:#F5F5F5">
	                   			</c:otherwise>
	                    	</c:choose>
		                        <td>
			                		<c:forTokens items="${detail.pic}" delims=";" var="picPath">
										<div class="sell_img fl">
										    <a href="javascript:void(0)"><img src="${imgUrl}${picPath}" style="width:68px; height:68px"></a>
										</div>
									</c:forTokens>
									<div class="sell_name fl">
										<p>
											${detail.carPart.partName}
										</p>
				                	</div>
		                        </td>
		                        <c:if test="${status.first}">
		                            <td class="pading_left" style="text-align:left" rowspan="${fn:length(damage.detailList)}">
		                            	受损单编号：<a class="dd_color" href="javascript:void(0)" onClick="detail(this)">${damage.id}</a>
		                            	<br/>
		                            	<c:if test="${damage.source ne 'web' }">
		                            		<span class="source">来自${damage.source}客户端</span>
		                            	</c:if>
		                            </td>
									<td class="sell_time" rowspan="${fn:length(damage.detailList)}">
					                	<fmt:formatDate value="${damage.createtime}" pattern="yyyy-MM-dd HH:mm:ss" var="newDate"/>
					                    <c:forTokens items="${newDate}" delims=" " var="value">
										 	<p>${value}</p>
										 </c:forTokens>
					                </td>
			                        
		                        	<c:set var="passStatus" value="${damage.passStatus}"/>
		                        	<c:choose>
										<c:when test="${passStatus eq '0'}"><td rowspan="${fn:length(damage.detailList)}">审核中</td></c:when>
										<c:when test="${passStatus eq '1'}"><td rowspan="${fn:length(damage.detailList)}">报价中</td></c:when>
										<c:when test="${passStatus eq '2'}"><td class="red" rowspan="${fn:length(damage.detailList)}">审核未通过</td></c:when>
										<c:otherwise><td rowspan="${fn:length(damage.detailList)}">已成交</td></c:otherwise>
									</c:choose>
			                        <td rowspan="${fn:length(damage.detailList)}">
			                            <div class="buy_opera">
			                            	<c:choose>
			                            		<c:when test="${passStatus ==2}">
			                            			<span class="offerPrice">${damage.reason}</span>
			                            		</c:when>
		                            			<c:when test="${passStatus <2 && damage.offerCount <= 0 }">
			                            			<span class="offerPrice">暂未有商家报价</span>
			                            		</c:when>
		                            			<c:when test="${passStatus <2 && damage.offerCount > 0 }">
			                            			<span class="offerPrice">已有${damage.offerCount}家商家报价<br/><a href="../mypainting/offers?id=${damage.id}">点击查看报价</a></span>
			                            		</c:when>
		                            			<c:when test="${passStatus == 3}">
				                            		<span class="offerPrice"><a href="../mypainting/orderDetail?damageId=${damage.id}">订单详情</a></span>
			                            		</c:when>
			                            	</c:choose>
			                            </div>
			                        </td>
			                    </tr>
		                    </c:if>
	                    </c:forEach>
                    </c:forEach>
                </table>
            </div>
         </div>
         <div id="pagination" class="yoyoPagination"></div> 
	</div>
	<script type="text/javascript">
	 	totalRecords ="${damageList.total}";
		memberId = "${sessionScope.memberId}";
	</script>
</body>
</html>