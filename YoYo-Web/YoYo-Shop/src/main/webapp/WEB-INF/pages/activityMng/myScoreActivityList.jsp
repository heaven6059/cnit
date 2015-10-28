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
<title>我参加的活动</title>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?v=${versionVal}" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script type="text/javascript"  src="${path}/resources/scripts/biz/activityMng/myScoreActivityList.js?v=${versionVal}"></script>
</head>
<body>
    <div class="member-main fl">
        <!--nav-->
    	<div class="title title2">申请列表</div>
    	<div class="Plate">
			<h4>
			    <a class="active cur_active"  href="javascript:void(0);" onclick="openCheckingGoods(this);">限时抢购</a>
			    <a class="active"  href="javascript:void(0);" onclick="openLocationGoods(this);">团购活动</a>
				<a class="active"  href="javascript:void(0);" onclick="openPutawayGoods(this);">秒杀活动</a> 
				<a class="active"  href="javascript:void(0);" onclick="openWarningGoods(this);">捆绑活动</a>
				<a class="active" href="javascript:void(0);" onclick="openDealingGoods(this)">积分换购活动</a>
			</h4>
		</div>
        <form >
        <div class="sell_con">
            <div class="buy_list clearfix">
            	<table  class="gridlist" width="100%" cellspacing="0" cellpadding="0" border="0" id="tableId">
                	<thead>
                        <tr class="firstTr">
                            <th style=" width: 150px;">活动名称</th>
                        	<th style=" width: 150px;"> 商品名称</th>
                            <th style=" width: 150px;">活动价格</th>
                            <th style=" width: 150px;">申请状态</th>
                            <th style=" width: 150px;">活动状态</th>
                            <th style=" width: 150px;">备注</th>
                            <th style="width: 120px;">操作</th>
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

