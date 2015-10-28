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
<title>最低价咨询</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<script type="text/javascript" src="${path}/resources/scripts/biz/driveOrReplace/lowPriceConsult.js?t=${time} "></script>
</head>
<body>
	<div id="toolbar">
		<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteLowPriceConsult()"> 删除 </a>
		<a href="#" style="margin-left: 5px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openSeachWindow()">高级筛选</a>
	</div>
	
	<table id="low_price_list_dataGrid" style="marge: 10px auto;">
	</table>
	<div id="advance_search_scope" class="easyui-dialog advance_search" title="高级筛选" style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;" data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="low_price_search_form" class="easyui-form" method="post" data-options="novalidate:true">
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
		            <tr>
		                <td>是否置换：</td>
		                <td>
							<label><input type="radio" name="isReplace"  id="replace" value="1"/>是</label>&nbsp;
							<label><input type="radio" name="isReplace"  id="notreplace" value="0"/>否</label>
						</td>
		            </tr>
		            <tr>
		                <td>置换车型：</td>
		                <td>
							<select id="replaceBrand" name="replaceBrandId" style="width: 220px">
								<option value="-1">--请选择--</option>
							</select>
							<br>
							<select id="replaceDept" name="replaceDepId" style="width: 220px">
								<option value="-1">--请选择--</option>
							</select>
							<br>
							<select id="replaceCar" name="replaceCarId" style="width: 220px">
								<option value="-1">--请选择--</option>
							</select>
						</td>
		            </tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="advanceQuery('low_price_list_dataGrid','low_price_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearCondition()" style="width: 90px">清除条件</a>
    </div>
</body>

</html>

