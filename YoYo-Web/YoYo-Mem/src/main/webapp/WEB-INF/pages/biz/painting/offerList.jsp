<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商家报价</title>
<style type="text/css">

</style>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/painting/offerList.css" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/offerList.js"></script>
</head>
<body>
	<div class="sell_main ml10 fl">
		<!--nav-->
		<div class="sell_title">
			<div class="title_border">
				<span>报价列表</span>
			</div>
		</div>
		
		<form action="">
	        <div class="sell_con">
	            <!--近期订单-->
	            <div class="buy_list clearfix">
	                <table width="970" border="1" style="border: 1px solid #ddd;" class="sell_table">
	                	<thead>
	                        <tr class="firstTr">
	                        	<th style=" width: 80px;">选择</th>
	                            <th style=" width: 450px;">商家名称</th>
	                            <th style=" width: 180px;">报价总额</th>
	                            <th style=" width: 180px;">报价时间</th>
	                            <th>展开</th>
	                        </tr>
	                    </thead>
	                    <tbody>
		                    <c:forEach items="${offerList}" var="offer"  varStatus="status">
			                    <tr class="selectTr" style="border-bottom: 1px solid #ddd;">
			                    	<td>
			                    		<c:choose>
			                    			<c:when test="${status.first}">
			                    				<input type="radio" name="choose" checked = "true" value="${offer.companyId}"/>
					                    	</c:when>
					                    	<c:otherwise>
			                    				<input type="radio" name="choose" value="${offer.companyId}"/>
					                    	</c:otherwise>
			                    		</c:choose>
			                    	</td>
			                        <td> ${offer.company.storeName}</td>
			                        <td>${offer.totalprice}</td>
									<td class="sell_time">
					                	<fmt:formatDate value="${offer.createtime}" pattern="yyyy-MM-dd HH:mm:ss" var="newDate"/>
					                    <c:forTokens items="${newDate}" delims=" " var="value">
										 	<p>${value}</p>
										 </c:forTokens>
					                </td>
					                <td>
					                	<a class="link" herf="javascript:vodi(0)" onclick="toggle(this)">
					                		<c:choose>
					                    		<c:when test="${status.first}">
					                    			<span class="openClose"></span>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<span class="openClose2"></span>
					                    		</c:otherwise>
					                    	</c:choose>	
					                		
					                	</a>
					                </td>
			                    </tr>
			                    <tr>
			                    	<c:choose>
			                    		<c:when test="${status.first}">
			                    			<td colspan="5" >
			                    		</c:when>
			                    		<c:otherwise>
			                    			<td colspan="5" style="display:none" >
			                    		</c:otherwise>
			                    	</c:choose>	
						                    	<table>
						                    		<thead>
						                    			<tr class="innerTr">
						                    				<td>部位</td>
						                    				<td>报价</td>
						                    				<td>卖家留言</td>
						                    			</tr>
						                    		</thead>
						                    		<tbody>
					                   					<c:forEach items="${offer.offerDetailList}" var="detail">
							                    			<tr>
							                    				<td width="200px">${detail.carDamageDetail.carPart.partName}</td>
							                    				<td width="100px">${detail.offerPrice}</td>
							                    				<td width="300px" style="text-align: left;">${detail.remark}</td>
							                    			</tr>
						                    			</c:forEach>
						                    		</tbody>
						                    	</table>
				                    		</td>
				                 </tr>
		                    </c:forEach>
	                    </tbody>
	                </table>
	            </div>
	         </div>
	         <button class="btn" type="button" id="submit"">选择报价</button>
         </form>
	</div>
</body>
</html>