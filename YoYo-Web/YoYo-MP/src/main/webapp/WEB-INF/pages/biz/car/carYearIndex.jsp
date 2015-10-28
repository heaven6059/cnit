<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>车系年款</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css" href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/car/carYearIndex.js"></script>
    <%-- <script type="text/javascript" src="${path}/resources/scripts/biz/car/SimpleCanleder.js"></script> --%>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <%-- <link type="text/css" href="${path}/resources/styles/SimpleCanleder.css" /> --%>

    <div id="toolbar-car_year">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteCarYear()"> 删除 </a> 
            <a href="#" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_factory')">高级筛选</a>
    </div>
    <table id="table-car-year"></table>


    <!-- 新增汽车车系年款-->
    <div id="window-add-car-year" class="easyui-window" title="添加车系"
        closed="true" style="height: 550px; width: 800px;"
        data-options="minimizable:false,cache:false,modal:true">

        <form id="form-car-year-add" class="easyui-form">
            <input type="hidden" name="carYearId" id="carYearId">
            <table class="add_brand_table">
            	<tr>
					<td align="right"><span style="color: red;">*</span>汽车品牌：</td>
					<td><select id="factoryId" name="factoryId" style="width: 160px;"></select></td>
				</tr>
                <tr>
                    <td align="right">车系名称：</td>
                    <td><select id="carDeptId" style="width: 160px;" name="carDeptId">
                    </select>
                   </td>
                </tr>
                <!-- <tr>
                    <td align="right">年份值：</td>
                    <td>
                    	<input type='text' name="carYearValue"  style="width: 260px;"  class="tab_input"  /> 
                    	<input id="dd" name="carYearValue" type="text" style="width: 260px;" >
                    	<select id="s1" type="text" name="carYearValue" style="width: 260px;" ></select>
                    </td>
                </tr> -->
                
                <tr>
                    <td align="right">年份值：</td>
                    <td>
                    	<select id="s2" name="carYearValue" style="width: 160px;"></select>
                    </td>
                </tr>
                
               <!--  <tr>
                    <div id="SimpleCanleder_Year_Month">
                    	<div class="title">
                    		<ul>
                    			<li class="over"><div class="inner"> < </div></li>
                    			<li class="over middle"><div class="inner paddingTop"></div></li>
                    			<li class="over"><div class="inner"> > </div></li>
                    		</ul>
                    	</div>
                    	<div class="body">
                    		<ul>
                    			<li class="over"><div class="inner"></div></li>
                    			<li class="middle"><div class="inner"></div></li>
                    			<li class="over"><div class="inner"></div></li>
                    		</ul>
                    	</div>
                    </div>
                </tr> -->
                
                <!-- <tr>
                    <td align="right">年份值1：</td>
                    <td>
                    	<input class="test" value="" style="paddind-top: 100px; padding-left:100px;"/>  
                    </td>
                </tr> -->
            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-car-factory-btn" icon="icon-save" onclick="saveCarYear()">保存</a>
                <a class="easyui-linkbutton" id="update-car_factory-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-car-year');" style="margin-left: 15px;">取消</a>
        </div>
    </div>
   <!--  <script>  
		$(".test").simpleCanleder();  
	</script> -->

	<!-- 高级查询 -->
	<div id="advance_search_car_factory" class="easyui-dialog advance_search" title="高级筛选"
		style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
		data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
					<td align="right"><span>汽车品牌：</span></td>
					<td><select id="searchFactoryId" name="searchFactoryId" style="width: 160px;" class="search_class"></select></td>
				</tr>
				<tr>
					<td align="right"><span>车系名称: </span></td>
					<td><select id="searchCarDeptId" style="width: 160px;" class="search_class">
                    </select></td>
				</tr>
				<tr>
                    <!-- <td align="right">选择车型：</td>
                    <td><select id="search_car_type" style="width: 160px;" class="search_class">
                    </select></td> -->
                    <td align="right">年份值：</td>
                    <td>
                    	<!-- <input type='text' name="carYearValue"  style="width: 260px;"  class="tab_input"  />  -->
                    	<!-- <input id="dd" name="carYearValue" type="text" style="width: 260px;" > -->
                    	<select id="searchYear" type="text" name="carYearValue" style="width: 160px;" ></select>
                    </td>
                </tr>
			</table>
		</form>

	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok"
			onclick="advanceSearch()"
			style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
			class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="advance_clear()" style="width: 90px">清除条件</a>
	</div>

    
</body>
</html>
