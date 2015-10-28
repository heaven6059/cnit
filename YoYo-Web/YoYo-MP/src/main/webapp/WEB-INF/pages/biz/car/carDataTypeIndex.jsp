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
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>汽车数据项类别</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    <script type="text/javascript" src="${path}/resources/scripts/biz/car/carDataTypeIndex.js?v=${versionVal}"></script>
    <div id="car_data_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openAndCateDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteCatalog()"> 删除 </a> <a href="#"
            style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_data')">高级筛选</a>
    </div>
     <table id="car_data_cate_table"></table>


     <!-- 添加数据项类别 -->
      <div id="car_cate_add_window" class="easyui-window" title="添加数据项类别  "
        closed="true" style="height: 260px; width: 380px;"
        data-options="cache:false,modal:true,shadow:false,left:'480px',minimizable:false,iconCls:'icon-save'">
        <form id="add_cate_form" class="easyui-form" method="post"   >
          <input type="hidden" name="catalogId" id="catalogId"/>
          <table  style="border-collapse: separate; border-spacing: 15px;">
            <tr > 
                  <td align="right">数据项类别名称：</td>
                  <td><input type="text" name="catalogName" id="addCatalogName" class="easyui-textbox easyui-validatebox" data-options="required:true" validType="special"></td>
            </tr>
             <tr > 
                  <td align="right">排序：</td>
                  <td><input type="text" name="orderId"  class="easyui-numberbox " ></td>
            </tr>
          </table>
          </form>
        <div style="  margin-left: 100px;  margin-top: 100px;"><a onclick="javascript:saveCate()" class="easyui-linkbutton" style="margin-right:50px;margint-left:80px;">保存</a>
            <a  onclick="javascript:closeAndCatalog()" class="easyui-linkbutton" >取消</a>
        </div>
     </div>
     
     
     
     <!-- 高级查询 -->
     
      <div id="advance_search_car_data" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		     <form id="car_data_type_search_form" class="easyui-form" method="post"   data-options="novalidate:true">
			    <table style="border-collapse: separate; border-spacing: 5px;">
			          <tr>
                        <td align="right"><span>类别名称: </span></td>
                        <td><input  name="catalogName"  id="catalogName" class="easyui-textbox search_class" /></td>
                    </tr>
			    </table>
			</form>
	 </div>
	 
	 <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"  iconCls="icon-ok"   onclick="carDataTypeAdvanceSearch()"
            style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
            class="easyui-linkbutton" iconCls="icon-cancel"
            onclick="carDataTypeAdvanceClear()" style="width: 90px">清除条件</a>
    </div>
</body>
</html>
