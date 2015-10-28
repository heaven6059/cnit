<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>汽车厂商</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/car/carFactoryScope.js?t=${time}"></script>
<style type="text/css">

</style>    
</head>
<body>
    <div id="toolbar-car_factory">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openSaveDialog('add')"> 添加</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteScope()"> 删除 </a>
        <a href="#" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openSeachWindow()">高级筛选</a>
    </div>
    <table id="table-car-factory-scope"></table>
	
	<div id="window-add-scope" class="easyui-dialog"  style="width:400px;height:200px;max-width:800px;padding:10px" data-options="
            iconCls:'icon-save',closed:true,
            onResize:function(){
                $(this).dialog('center');
            }">        
    </div>
	
	<div id="advance_search_scope" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="scope_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tbody>
		            <tr>
		                <td>品牌区域名称：</td>
		                <td>
							<input type="text" name="carType"  id="factoryName" maxlength="20"/>
						</td>
		            </tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('table-car-factory-scope','scope_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="clearCondition('scope_search_form')" style="width: 90px">清除条件</a>
    </div>
	
</body>
</html>
