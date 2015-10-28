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

<title>用户管理</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css" href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/system/urlManager.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <div id="toolbar-user-manager">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteUser()"> 删除 </a> 
    </div>
    <table id="table-user-manager"></table>


    <!-- 新增用户-->
    <div id="window-add-user-manager" class="easyui-window" title="添加用户"
        closed="true" style="height: 550px; width: 800px;"
        data-options="cache:false,modal:true">

        <form id="form-user-manager-add" class="easyui-form">
        <input type="hidden" name="id" id="id">
            <table class="add_brand_table">
                <tr>
                    <td align="right">名称：</td>
                    <td><input type='text' name="name" class="tab_input"  /></td>
                </tr>
                <tr>
                    <td align="right">路径：</td>
                    <td><input type='text' name="url" class="tab_input"  /> </td>
                </tr>
                <tr>
                    <td align="right">角色：</td>
                    <td><select id="roleId" style="width: 160px;" name="roles">
                    </select>
                   </td>
                </tr>
                <tr>
                    <td align="right">权限：</td>
                    <td><input type='text' name="permissions" class="tab_input"  /> </td>
                </tr>
            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-user-btn" icon="icon-save" onclick="saveUser()">保存</a>
                <a class="easyui-linkbutton" id="close-user-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-user-manager');" style="margin-left: 15px;">取消</a>
        </div>
    </div>



    
</body>
</html>
