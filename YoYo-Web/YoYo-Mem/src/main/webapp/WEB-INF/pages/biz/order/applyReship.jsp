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
<title>售后申请列表</title>

<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/applyReship.js?v=${versionVal}"></script>
<style type="text/css">
.item-form::after {clear: both;content: ".";display: block;height: 0;visibility: hidden;}
.p-goods {width: 420px;}
.cell {float: left;padding: 15px 0 10px;}
.p-img {background: #fff none repeat scroll 0 0;border: 1px solid #eee;float: left;height: 50px;margin-right: 10px;overflow: hidden;padding: 0;text-align: center;width: 50px;}
.p-ops {padding-left: 20px;}
</style>
</head>
<body>
	<div class="member-main" id="J_DataTable">
		<div class="orderlist-warp">
			<div class="lineh b4" style="border-bottom: 0;" id = "query_id">
				<form >
					<dl class="sellList_dl clearfix">
			        	<dt>订单号：</dt>
			            <dd>
			                <input type="text" id="search_order_id" value="${orderId}" class="form_item_input">
			            </dd>
			        	<dt>商品名称：</dt>
			            <dd>
			                <input type="text" id="search_name" class="form_item_input">
			            </dd>
			            <dt>
			            	<a onclick="searchReship()" href="javascript:;" class="sell_search">搜索</a>
			            	<a onclick="resetQuery()" href="javascript:;" class="sell_search">清空</a>
			            </dt>
			        </dl>
				 </form>
			</div>
			<table class="gridlist table-goods-list gridlist_member " style="border-bottom: none" 
			   width="100%" border="0" cellspacing="0" cellpadding="0"  id = "tableId">
				<tbody>
					<tr>
						<th >订单号</th>
						<th>订单商品</th>
						<th>下单日期</th>
					</tr>
				</tbody>
			</table>
			<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div> 
		</div>
	</div>	
</body>
</html>

