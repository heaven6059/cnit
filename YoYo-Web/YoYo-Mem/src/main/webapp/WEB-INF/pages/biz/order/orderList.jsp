<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    String orderId=request.getParameter("orderId");
    request.setAttribute("orderId", orderId);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的订单</title>
<style type="text/css">

</style>
<link type="text/css" href="${path}/resources/styles/formValidate/formitem.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" rel="stylesheet" />

<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/dataUtil.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form.valid-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/order/orderList.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>

</head>
<body>
    <div class="member-main fl">
        <!--nav-->
    	<div class="title title2">我的订单</div>
        <form >
        <dl class="sellList_dl clearfix">
        	<dt>订单号：</dt>
            <dd>
                <input type="text"  id="search_order_id" maxlength="18" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
            </dd>
        	<dt>商品名称：</dt>
            <dd>
                <input type="text"  id="search_name" maxlength="20"/>
            </dd>
            <dt>
            	<a class="sell_search" href="javascript:;" onclick = "submitForm()">搜索</a>
            	<a class="sell_search" href="javascript:;" onclick = "resetQuery()">清空</a>
            </dt>
        </dl>
        
        <div class="sell_con">
            <!--近期订单-->
            <div class="buy_list clearfix">
            	<table  class="gridlist" width="100%" cellspacing="0" cellpadding="0" border="0" id="tableId">
                	<thead>
                        <tr class="firstTr">
                            <th style=" width: 450px;" colspan="2">订单信息</th>
                            <th style=" width: 150px;">订单金额</th>
                            <th style=" width: 125px;">
								<select class="select_c" id="search_createtime" >
								    <option value="0">全部时间</option>
									<option value="3">三个月内</option>
									<option value="6">半年内</option>
									<option value="12">今年内</option>
									<option value="13">1年以前</option>
								</select>                            
                            </th>
                            <th style=" width: 125px;">
                                <select class="select_c" id="search_order_status">
									<option value="all">全部状态</option>
									<option value="unpay" <c:if test="${orderQry.status eq 'create'}">selected="selected"</c:if>>待付款</option>
									<option value="unconfirm" <c:if test="${orderQry.status eq 'unconfirm'}">selected="selected"</c:if>>待确认</option>
									<option value="confirm" <c:if test="${orderQry.status eq 'confirm'}">selected="selected"</c:if>>已确认</option>
									<option value="refunds" <c:if test="${orderQry.status eq 'refunds'}">selected="selected"</c:if>>退款中</option>
									<option value="finish" <c:if test="${orderQry.status eq'finish'}">selected="selected"</c:if>>已完成</option>
									<option value="dead" <c:if test="${orderQry.status eq 'dead'}">selected="selected"</c:if>>已撤单</option>
								</select>
                            </th>
                            <th style="width: 120px">操作</th>
                        </tr>
                    </thead>
                </table>
            </div>
        </div>
        
        </form>
       	<div class="page clearfix">
			<div id="Pagination" class="yoyoPagination"></div>
		</div>
    </div>
</body>
</html>

