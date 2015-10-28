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
<title>预约试驾</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/biz/aftersales/refundslist.js?v=${versionVal}"></script>
</head>
<body>
	<div id="toolbar">
	</div>
	<div id="toolbar-refunds">
		&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:refundsAccount();" class="easyui-linkbutton" style="width: 100px;" >批量退款到账户</a>
	</div>
	<table id="refunds_list_dataGrid" style="marge: 10px auto;">
	</table>
	
	<div id="window-view-after-sales" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',closed:true" style="width:840px;height:740px;padding:10px">
    </div>
	
	<div id="advance_search_scope" class="easyui-dialog advance_search" title="高级筛选" style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="drive_search_form" class="easyui-form" method="post" data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tbody>
					 <tr>
		                <td>询问车型：</td>
		                <td>
							<select id="consultBrand" style="width: 220px">
								<option value="-1">--请选择--</option>
							</select>
							<br>
							<select id="consultDept" style="width: 220px">
								<option value="-1">--请选择--</option>
							</select>
							<br>
							<select id="consultCar" name="carId" style="width: 220px">
								<option value="-1">--请选择--</option>
							</select>
						</td>
		            </tr>
		            <tr>
		                <td>询问人姓名：</td>
		                <td>
							<input type="text" name="userName"  id="userName"/>
						</td>
		            </tr>
		            <tr>
		                <td>询问人性别：</td>
		                <td>
							<label><input type="radio" name="userSex"  id="man" value="1"/>男</label>&nbsp;
							<label><input type="radio" name="userSex"  id="women" value="0"/>女</label>
						</td>
		            </tr>
		            <tr>
		                <td>询问人手机号：</td>
		                <td>
							<input type="text" name="phone"  id="phone"/>
						</td>
		            </tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="advanceQuery('drive_list_dataGrid','drive_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearCondition()" style="width: 90px">清除条件</a>
    </div>
</body>

</html>

