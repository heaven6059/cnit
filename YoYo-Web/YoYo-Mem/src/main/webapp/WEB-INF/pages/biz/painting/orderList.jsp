<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>钣金订单列表</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/buyYoYo.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/sellYoYo.css?v=${versionVal}" />
<link type="text/css" href="${path}/resources/scripts/alert/jquery.alerts.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/formValidate/formitem.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/member.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/painting/orderList.css?v=${versionVal}" />
<link rel="stylesheet" href="http://misc.360buyimg.com/user/myjd-2015/widget/??common/common.css" type="text/css">
<link rel="stylesheet" href="http://misc.360buyimg.com/user/myjd-2015/css/myjd.commentImg.css" type="text/css">
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/dataUtil.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form.valid-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/painting/orderList.js?v=${versionVal}"></script>

</head>
	<body>
	    <div class="sell_main ml10 fl">
	        <form >
		    	<div class="sell_title">
		        	<div class="title_border">
		                <span>钣金订单</span>
		            </div>
		        </div>
<!-- 		        <dl class="sellList_dl clearfix">
		        	<dt>订单号：</dt>
		            <dd>
		                <input type="text"  id="search_order_id"  />
		            </dd>
		        	<dt>商品名称：</dt>
		            <dd>
		                <input type="text"  id="search_name"/>
		            </dd>
		            <dt>
		            	<a class="sell_search" href="javascript:;" onclick = "submitForm()" style="margin:0 10px 0 20px;">搜索</a>
		            	<a class="sell_search" href="javascript:;" onclick = "resetQuery()">清空</a>
		            </dt>
		        </dl>
 -->		        
		        <div class="sell_con" style="margin-top: 0;">
		            <!--近期订单-->
		            <div class="buy_list clearfix" style="margin-top: 0;">
		            	<table  class="gridlist" style="border-left: none;width: 100%;" id="tableId">
		                	<thead>
		                        <tr class="firstTr">
		                            <th style=" width: 343px;" colspan="2">订单信息</th>
		                            <th style=" width: 107px;">卖家</th>
		                            <th style=" width: 129px;">订单金额</th>
		                            <th style=" width: 118px;">
										<select class="select_c" id="search_createtime" >
										    <option value="0">全部时间</option>
											<option value="3">三个月内</option>
											<option value="6">半年内</option>
											<option value="12">今年内</option>
											<option value="13">1年以前</option>
										</select>                            
		                            </th>
		                            <th style=" width: 118px;">
		                                <select class="select_c" id="search_order_status">
										    <option value = "allStateId" >全部状态</option>
											<option value = "payStatusId">待付款</option>
											<option value = "unconfirmId" >待确认</option>
											<option value = "uninstallId" >待安装</option>
											<option value = "installId" >已安装</option>
											<option value = "finishId" >已完成</option>
											<option value = "refunds">退款中</option>
											<option value = "deadId" >已取消</option>
		                                </select>
		                            </th>
		                            <th>操作</th>
		                        </tr>
		                    </thead>
		                </table>
		            </div>
		        </div>
	        </form>
	       	<div class="page clearfix">
				<div id="Pagination" class="yoyoPagination"></div>
			</div>
			<!-- 评论 -->
	    </div>
	</body>
</html>