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
<title>汽车数据项</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
</head>
<body>
    <script type="text/javascript" src="${path}/resources/scripts/biz/car/carDataIndex.js?v=${versionVal}"></script>
    <div id="car_data_toolbar">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openCarDataDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteCarData()"> 删除 </a> <a href="#"
            style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_data')">高级筛选</a>
           <!--  <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="test()"> 测试</a> -->
    </div>
    <table id="car_data_datagrid"></table>


   <!-- 新增数据项-->
    <div id="car_data_add" class="easyui-window" title="添加数据项"
        closed="true" style="height: 550px; width: 620px;"
        data-options="cache:false,top:50,minimizable:false,iconCls:'icon-save',modal:true">

        <form id="car_data_add_form" class="easyui-form">
            <input type="hidden" name="dataId" id="dataId">
            <table class="add_brand_table">
                <tr>
                    <td align="right"><span style="color: red;">*</span>数据项类别：</td>
                    <td><select id="add_catalogId"  name="catalogId" style="width:120px;">
                            </select></td>
                </tr>
                <tr>
                    <td align="right">数据项名称：</td>
                    <td><input type='text' name="displayName"
                        class="tab_input easyui-validatebox" data-options="required:true" validType="special"/></td>
                </tr>
                <tr>
                    <td align="right">数据类型：</td>
                    <td><select name="dataType" id="dataType" class="easyui-combobox easyui-validatebox" data-options="required:true, editable:false" >
                        <option value="STR">文本</option>
                        <option value="INT">整型</option>
                        <option value="BOL">布尔型</option>
                        <option value="DEC">数值型</option>
                        <option value="LIST">列表</option>
                    </select></td>
                </tr>
              
                <tr>
                    <td align="right">排序：</td>
                    <td><input type='text' name="orderId"    class="tab_input"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/> </td>
                </tr>
                
                <!--  <tr>
                    <td align="right">列表名称：</td>
                    <td><input type='text' name="listName"    class="tab_input"  /> </td>
                </tr> -->
                <tr class="listTr" style="display:none;">
                    <td align="right">列表数据项：</td>
                    <td>
                        <input type='text' name="listValue" id="listValue"  class="tab_input"  /> 
                   </td>
                </tr>
                <tr class="listTr" style="display:none;"><td><td>数据项之间以"|"分隔</td></tr>
               
            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-car-factory-btn" icon="icon-save" onclick="saveCarData()">保存</a>
                <a class="easyui-linkbutton" id="update-car_factory-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('car_data_add');" style="margin-left: 15px;">取消</a>
        </div>
    </div>
    
    
    
   
    
      <!-- 选择数据项类别对话框 -->
    <div id="car_data_cate_window" class="easyui-window" title="数据项类别 "
        closed="true" style="height: 500px; width: 660px;"
        data-options="cache:false,modal:true,shadow:false,left:'400px'">
        <table id="car_data_cate_table"></table>
        <div style=" margin-left: 200px;  margin-top: 60px;">
            <a onclick="javascript:getCatalogId()" class="easyui-linkbutton" style="margin-right:50px;">确定</a>
            <a onclick="javascript:closeCateDialog()" class="easyui-linkbutton" >取消</a>
        </div>
     </div>
    
     
     
     <!-- 高级查询 -->
     
      <div id="advance_search_car_data" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		     <form id="car_data_search_form" class="easyui-form" method="post"
			    data-options="novalidate:true">
			    <table style="border-collapse: separate; border-spacing: 5px;">
			          <tr>
                        <td align="right"><span>数据项名称: </span></td>
                        <td><input  name="displayName"
                            class="easyui-textbox search_class" /></td>
                    </tr>
			        <tr>
			            <td align="right"><span>数据项类别: </span></td>
			            <td> <select id="query_catalogId"  name="catalogId" style="width:120px;">
                            </select>
                        </td>
			        </tr>
			      
			        <tr>
			            <td align="right"><span>数据类型: </span></td>
			            <td><select name="dataType"  class="easyui-combobox" >
                        <option value="STR">文本</option>
                        <option value="INT">整型</option>
                        <option value="BOL">布尔型</option>
                        <option value="DEC">数值型</option>
                        <option value="LIST">列表</option>
                    </select></td>
			        </tr>
			    
			    </table>
			</form>
	 </div>
	 
	 <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"  iconCls="icon-ok"   onclick="carDataAdvanceSearch()"
            style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
            class="easyui-linkbutton" iconCls="icon-cancel"
            onclick="carDataAdvanceClear()" style="width: 90px">清除条件</a>
    </div>
</body>
</html>
