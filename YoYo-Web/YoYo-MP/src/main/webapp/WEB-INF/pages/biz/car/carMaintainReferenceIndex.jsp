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
<title>保养周期对照</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/styles/maintain/maintain.css" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/car/carMaintainReferenceIndex.js"></script>
<style type="text/css">
	.datagrid-row-selected{
	  	background: none repeat scroll 0 0 #799fbe;
   	 	color: #00438a;
    	cursor: default;
    	color:#fff;font-weight:bold;
	}
</style>
</head>
<body>
	<div id="toolbar_car_maintain">
		<a class="easyui-linkbutton" iconCls="icon-edit" onclick="editMaintain();">编辑保养周期</a>
		<a class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_maintain')">高级筛选</a>
	</div>
	<table id="table-car-maintain">
	</table>
	
	 <div id="window-edit-car-maintain" class="easyui-dialog" title="Basic Dialog" data-options="iconCls:'icon-save',closed:true" style="width:840px;height:740px;padding:10px">
    </div>
	
	<div id="window-view-car-maintain" class="easyui-dialog" style="width: 884px;height:500px; padding: 10px 20px" data-options="fit:true,closed:true,cache:false">
	</div>
	

    <!-- 高级查询 -->
	<div id="advance_search_car_maintain" class="easyui-dialog advance_search" title="高级筛选"
		style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
		data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="maintain_search_form" class="easyui-form" method="post" data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;">
				<tr>
                    <td align="right">选择品牌：</td>
                    <td>
                    	<select id="car_brand_qry" name="carBrand" style="width: 160px;">
						</select>
                    </td>
                </tr>
                <tr>
                    <td align="right">选择厂商：</td>
                    <td>
                    	<select id="car_factory_qry" name="carFactory" style="width: 160px;">
					</select>
                    </td>
                </tr>
                <tr>
                    <td align="right">选择车系：</td>
                    <td>
                    	<select id="car_dept_qry" name="carDept" style="width: 160px;">
						</select>
                    </td>
                </tr>
                 <tr>
                    <td align="right">选择车型：</td>
                    <td>
                    	<select id="car_type_qry" name="carId" style="width: 160px;">
						</select>
						<input type="hidden" name="invert">
                    </td>
                </tr>
			</table>
		</form>

	</div>
	<div id="search_btn">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok" onclick="searchMaintain()" style="width: 90px">立即筛选</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="clearMaintain()" style="width: 90px">清除条件</a>
	</div>
	
	
	<!-- 关联保养类别 -->
	<div id="window-maintain-category" class="easyui-window" title="关联保养类别"
        closed="true" style="height: 450px; width: 600px;padding:10px;"
        data-options="modal:true,cache:false">
        <input type="hidden" id="selectId">
        <table  style="border-collapse: separate; border-spacing: 5px;">
            <tr > 
                  <td align="right">保养类别：</td>
                  <td>
                  <select id="maintain_select_parent" style="width:160px;" >
                   </select>
                    <select id="maintain_select_child" style="width:160px;" >
                    </select>
                  </td>
              </tr>
              <tr id="good_category_tr" >
                  <td align="right">选择的分类：</td>
                  <td> 
                  	<select id="maintain_category_ids" name="goodCategory" style="width:360px;" multiple="multiple">
                    </select>
                   </td>
              </tr>
        </table>
        <div style="text-align: center;margin-top:10px;">
         <a class="easyui-linkbutton" id="update-car-maintain-btn" icon="icon-save" onclick="updateMaintainCategory()" >保存</a>
         <a class="easyui-linkbutton" id="update-car_maintain-btn" icon="icon-cancel" onclick="javascript:closeCategory();" style="margin-left: 15px;">取消</a>
        </div>
    </div>
</body>
</html>
