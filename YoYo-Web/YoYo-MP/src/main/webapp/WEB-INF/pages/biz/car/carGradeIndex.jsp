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
<title>车系年款</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css" href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/car/carGradeIndex.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <div id="toolbar-car-grade" style="display:none;">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openAddGradeDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteGrade()"> 删除 </a>
    </div>
    <table id="car-grade-table"></table>


 <!-- 添加汽车级别-->
      <div id="window-add-grade" class="easyui-window" title="汽车级别 "
        closed="true" style="height: 260px; width: 380px;"
        data-options="cache:false,minimizable:false,modal:true,shadow:false,left:'480px'">
        <form id="add_grade_form" class="easyui-form" method="post"   >
          <input type="hidden" name="gradeid" id="gradeid"/>
          <table  style="border-collapse: separate; border-spacing: 15px;">
            <tr > 
                  <td align="right"><span style="color: red;">*</span>级别名称：</td>
                  <td><input type="text" name="gradeName" id="gradeName" validType="loginName" maxlength="6" class="easyui-textbox easyui-validatebox" data-options="required:true"></td>
            </tr>
          </table>
          </form>
        <div style="  margin-left: 100px;  margin-top: 100px;">
        <a onclick="javascript:saveGrade()" class="easyui-linkbutton" style="margin-right:50px;margint-left:80px;">确定</a>
            <a  onclick="javascript:closeDailog('window-add-grade');" class="easyui-linkbutton" >取消</a>
        </div>
     </div>


    
</body>
</html>
