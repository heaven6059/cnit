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
<title>汽车车系</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/car/carDeptIndex.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <div id="toolbar-car_dept">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteCarDept()"> 删除 </a> <a href="#"
            style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_dept')">高级筛选</a>
    </div>
    <table id="table-car-dept"></table>


    <!-- 新增汽车车系-->
    <div id="window-add-car-dept" class="easyui-window" title="车系"
        closed="true" style="height: 550px; width: 800px;"
        data-options="minimizable:false,cache:false,modal:true">

        <form id="form-car-dept-add" class="easyui-form">
            <input type="hidden" name="carDeptId" id="carDeptId">
            <table class="add_brand_table">
                <tr>
                    <td style="width:30%; text-align:right" >汽车品牌名称：</td>
                    <td><select id="factoryId" style="width: 260px;" name="factoryId">
                    </select></td>
                </tr>
                <tr>
                    <td style="width:30%; text-align:right" >汽车级别：</td>
                    <td>
                    	<!-- <a   id="carGradeId"  class="easyui-linkbutton"   onclick="javascript:openGrade()">请选择</a>
                       	<span id="gradeName_span"></span> -->
                       	<select id="carGradeId" style="width: 260px;" name="carGradeId"></select>
                    </td>
                </tr>
                <tr>
                    <td style="width:30%; text-align:right" ><span style="color: red;">*</span>车系名称：</td>
                    <td><input type='text' name="carDeptName" validType="loginName" maxlength="30" style="width: 260px;" class="tab_input easyui-validatebox"  data-options="required:true" />
                   </td>
                </tr>
                <!-- <tr>
                    <td align="right">关键字：</td>
                    <td><input type='text' name="keyword"  style="width: 260px;"  class="tab_input"  /> </td>
                </tr> -->
            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-car-factory-btn" icon="icon-save" onclick="saveCarDept()">保存</a>
                <a class="easyui-linkbutton" id="update-car_factory-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-car-dept');" style="margin-left: 15px;">取消</a>
        </div>
    </div>

    <!-- 高级查询 -->
    <div id="advance_search_car_dept" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
        <form id="car_dept_search_form" class="easyui-form" method="post"
            data-options="novalidate:true">
            <table style="border-collapse: separate; border-spacing: 5px;">
                
                <tr>
                    <td align="right">汽车品牌名称：</td>
                    <td><select id="searchFactoryId" name="factoryid" style="width: 160px;" >
                    </select></td>
                </tr>
                <tr>
                    <td align="right">汽车级别：</td>
                    <td><select id="gradeSelectId" name="gradeId" style="width: 160px;" >
                    </select></td>
                </tr>
                <tr>
                    <td align="right">车系名称：</td>
                    <td><input type='text' name="carDeptName"  class="easyui-textbox search_class" />
                   </td>
                </tr>
                <!-- <tr>
                    <td align="right">关键字：</td>
                    <td><input type='text' name="keyword"    class="easyui-textbox search_class"  /> </td>
                </tr> -->
                
            </table>
        </form>
    </div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok"
            onclick="CarDeptSearch()"
            style="width: 90px">立即筛选</a> <a href="javascript:void(0)"
            class="easyui-linkbutton" iconCls="icon-cancel"
            onclick="advance_clear()" style="width: 90px">清除条件</a>
    </div>
    
    
    
    <div id="toolbar-car-brand-grade" style="display:none;">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openAddGradeDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteGrade()"> 删除 </a>
    </div>
    <!-- 选择汽车级别 -->
    <div id="window-car-grade" class="easyui-window" title="汽车级别 "
        closed="true" style="height: 700px; width: 660px;"
        data-options="cache:false,modal:true,shadow:false,left:'400px'">
        <table id="factory-grade-table"></table>
        <div style=" margin-left: 200px;  margin-top: 100px;">
            <a onclick="javascript:getGrade()" class="easyui-linkbutton" style="margin-right:50px;">确定</a>
            <a onclick="javascript:closeGradeDialog()" class="easyui-linkbutton" >取消</a>
        </div>
     </div>
     <!-- 添加汽车级别-->
      <div id="window-add-grade" class="easyui-window" title="汽车级别 "
        closed="true" style="height: 260px; width: 380px;"
        data-options="cache:false,modal:true,shadow:false,left:'480px'">
        <form id="add_grade_form" class="easyui-form" method="post"   >
          <input type="hidden" name="gradeId" id="gradeId"/>
          <table  style="border-collapse: separate; border-spacing: 15px;">
            <tr > 
                  <td align="right">级别名称：</td>
                  <td><input type="text" name="gradeName" id="gradeName" class="easyui-textbox easyui-validatebox" data-options="required:true"></td>
            </tr>
          </table>
          </form>
        <div style="  margin-left: 100px;  margin-top: 100px;">
        <a onclick="javascript:saveGrade()" class="easyui-linkbutton" style="margin-right:50px;margint-left:80px;">确定</a>
            <a  onclick="javascript:closeAddGrade()" class="easyui-linkbutton" >取消</a>
        </div>
     </div>
</body>
</html>
