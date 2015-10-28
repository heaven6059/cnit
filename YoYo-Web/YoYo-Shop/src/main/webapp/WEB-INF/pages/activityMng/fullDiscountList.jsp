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
<title>满折管理</title>
<style type="text/css">

</style>
<link type="text/css" href="${path}/resources/styles/formValidate/formitem.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" rel="stylesheet" />

<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/dataUtil.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/formValidate/jquery.form.valid-1.0.1.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/activityMng/fullDiscountList.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>

</head>
<body>
    <div class="member-main fl">
        <!--nav-->
    	<div class="title title2">满折管理</div>
        <form >
        <dl class="sellList_dl clearfix">
            <dt>
            	<a class="sell_search" href="javascript:;" onclick = "addBtn()">添加活动</a>
            </dt>
        </dl>
        
        <div class="sell_con">
            <div class="buy_list clearfix">
            	<table  class="gridlist" width="100%" cellspacing="0" cellpadding="0" border="0" id="tableId">
                	<thead>
                        <tr class="firstTr">
                            <th style=" width: 150px;">满折促销名称</th>
                            <th style=" width: 150px;">状态</th>
                            <th style=" width: 150px;">规则</th>
                            <th style=" width: 150px;">适用会员</th>
                            <th style=" width: 150px;">有效期</th>
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

