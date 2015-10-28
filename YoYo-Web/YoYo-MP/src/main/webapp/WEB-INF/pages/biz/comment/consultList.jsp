<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>评论咨询</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/member/consult.js"></script>
<link type="text/css" href="${path}/resources/styles/comment/comment.css" rel="stylesheet" />
</head>
<body>
	<div id="toolbar">
		&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton " iconCls="display-oepn" plain="true" id="displayAsk"><img src="../resources/images/disply-open.png">在前台显示</a>
		&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton " iconCls="display-close" plain="true" id="closeAsk"><img src="../resources/images/disply-close.png">关闭前台显示 </a>
		&nbsp;
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="delAsk"> 删除 </a>
		<input type="hidden" id="object_type" value="${objectType}" />
		<a class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_consult')">高级筛选</a>
	</div>
	
	<table id="consult_list_dataGrid" style="marge: 10px auto;">
	</table>
	<div id="commentDetail" class="easyui-dialog" style="width: 884px;height:500px; padding: 10px 20px" data-options="title:'评论咨询',closed:true,modal:true,cache:false"></div>
	
	
	<div id="advance_search_consult" class="easyui-dialog advance_search" title="高级筛选" style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="consult_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
                    <td align="right">咨询商品：</td>
                    <td>
                    	<input name="productName" id="productName" width="220px"/>
                    </td>
                </tr>
				<tr>
                    <td align="right">咨询人：</td>
                    <td>
                    	<input name="memberName" id="memberName" width="220px"/>
                    </td>
                </tr>
                <tr>
                    <td align="right">店铺名称：</td>
                    <td>
                   		<input name="storeName" id="storeName" width="220px"/>
                    </td>
                </tr>
                <tr>
                	<td align="right">开始日期：</td>
                    <td>
                   		<input id="startTime" type="text" class="easyui-datebox" required="required"></input><br>
                    </td>
                </tr>
                <tr>
                	<td align="right">结束日期：</td>
                    <td>
                   		<input id="endTime" type="text" class="easyui-datebox" required="required"></input>
                    </td>
                </tr>
                <tr>
                    <td align="right">是否显示：</td>
                    <td>
                    	<label><input type="radio" name="disPlay" value="" checked="checked"/>全部</label>&nbsp;
                    	<label><input type="radio" name="disPlay" value="1"/>是</label>&nbsp;
                    	<label><input type="radio" name="disPlay" value="0"/>否</label>&nbsp;
                    </td>
                </tr>
    		</table>
		</form>
	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="consultSearch();" style="width: 90px">立即筛选</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearConsultSearch();" style="width: 90px">清除条件</a>
	</div>
	
</body>

</html>

