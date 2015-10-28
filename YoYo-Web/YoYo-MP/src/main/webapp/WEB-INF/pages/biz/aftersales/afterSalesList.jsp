<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
		request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">   
<meta http-equiv="cache-control" content="no-cache">   
<meta http-equiv="expires" content="0">   
<title>预约试驾</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/biz/aftersales/aftersales.js?v=${versionVal}"></script>
</head>
<body>
	<div id="toolbar-car_maintain">		
		<a class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_aftersales')">高级筛选</a>
	</div>

	<table id="aftersales_list_dataGrid" style="marge: 10px auto;">
	</table>
	
	<div id="window-view-after-sales" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',closed:true" style="width:840px;height:740px;padding:10px">
    </div>
    
    
    <div id="advance_search_aftersales" class="easyui-dialog advance_search" title="高级筛选"
		style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
		data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="adftersales_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
                    <td align="right">订单号：</td>
                    <td>
                    	<input name="orderId" id="order_id" width="220px"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">申请人：</td>
                    <td>
                   		<input name="memberName" id="member_name" width="220px"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">售后要求：</td>
                    <td>
                    	<select id="afterSalesRequire" name="afterSalesRequire" style="width: 220px;">
                    		<option value="-1" selected="selected">请选择</option>
                    		<c:forEach var="afterSalesRequire" items="${afterSalesRequires}">
                    		<option value="${afterSalesRequire.key}">${afterSalesRequire.value}</option>
                    		</c:forEach>
						</select>
                    </td>
                </tr>
                
                <tr>
                    <td align="right">申请原因：</td>
                    <td>
                    	<select id="afterSalesType" name="afterSalesType" style="width: 220px;">
                    		<option value="-1" selected="selected">请选择</option>
                    		<c:forEach var="type" items="${afterSalesType}">
                    		<option value="${type.key}">${type.value}</option>
                    		</c:forEach>
						</select>
                    </td>
                </tr>
			</table>
		</form>

	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="afterSalesAdvanceSearch();" style="width: 90px">立即筛选</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearAfterSalesAdvanceSearch();" style="width: 90px">清除条件</a>
	</div>
    
</body>
</html>

