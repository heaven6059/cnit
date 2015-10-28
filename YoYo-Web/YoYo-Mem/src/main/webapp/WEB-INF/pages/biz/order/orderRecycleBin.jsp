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
<title>回收站</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css" />
<style type="text/css">
	.Plate>h5 a.current {
	  background: #0083ce;
	  color: #FFFFFF;
	  border: 0;
	  height: 45px;
	  line-height: 45px;
	  font-size: 18px;
	  font-weight: normal;
      margin-right: 5px;
	}
	
	.Plate>h5 a {
	  background: #f1f1f1;
	  display: inline-block;
	  padding: 0 30px;
	  font-weight: normal;
	  color: #333333;
	  border-left: 1px solid #ecebf0;
	  border-right: 1px solid #ecebf0;
	  height: 45px;
	  line-height: 45px;
	  font-size: 18px;
	}
	
	.Plate>h5 {
	  border-bottom: 2px solid #0083ce;
	}
	
	#conditionFrom>span{
		margin-left:10px
	}
</style>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/orderRecycleBin.js?v=${versionVal}"></script>

</head>
<body>
	<div class="member-main" id="J_DataTable">
		<div class="orderlist-warp">
			<div class="Plate">
				<h5>
					<a class="current" href="javascript:void(0)" onclick="loadDataList({refresh:true})">普通订单</a>  
					<a href="javascript:void(0)" onclick="queryPaintingOrder()">钣金订单</a>
				</h5>
			</div>
			<div class="lineh b4" style="border-bottom: 0;" id = "query_id">
			<form id="conditionFrom">
				<span><span> 订&nbsp;单&nbsp;号：</span><input  class="x-input " type="text" name="orderId" value="" maxlength="20" id="search_order_id" style="width: 160px"></span>
				<span id="name" ><span> 商品名称:</span><input  class="x-input " type="text" name="search_name" id="search_name"  style="width: 160px"> </span>
				<span><span>下单时间：</span>
				<select  id="search_createtime" name="createtime" class=" x-input-select inputstyle">
					    <option value="0">全部时间</option>
						<option value="3">三个月内</option>
						<option value="6">半年内</option>
						<option value="12">今年内</option>
						<option value="13">1年以前</option>
				</select></span>
				<button type="button" class="btn btn-secondary" id="btnsearch" onclick = "search()">
						<span><span>搜索</span></span>
				</button>
				<button type="button" class="btn btn-secondary" id="btnreset" onclick = "resetQuery()">
						<span><span>清空</span></span>
				</button>				
				</span>
			 </form>
			</div>
			<table class="gridlist table-goods-list gridlist_member " style="border-bottom: none" width="100%" border="0" cellspacing="0" cellpadding="0"  id = "tableId">
				<thead>
					<tr>
						<th colspan="2" width="60%">订单信息</th>
						<th width="15%">订单金额</th>
						<th width="15%">状态</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
			</table>
			<div class="page clearfix">
				<div class="member-del">
					<input type="checkbox" id="del-all-box">&nbsp;&nbsp;全选&nbsp;&nbsp;
					<a class="trigger-btn long-toolbtn" data-action="batchPDelOrder">批量删除</a>
					<a class="trigger-btn long-toolbtn" data-action="batchRestoreOrder">批量还原</a>
				</div>
				<div id="Pagination" class="yoyoPagination" ></div>
			</div>	
		</div>
	</div>
</body>
</html>

