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
    <script type="text/javascript" src="${path}/resources/scripts/biz/system/shopManager/shopUser.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <div id="toolbar-user-manager">
        <!-- <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 绑定角色</a>  --><a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteUser()"> 删除 </a> 
    </div>
    <table id="table-user-manager"></table>


    <!-- 新增用户-->
    <div id="window-add-user-manager" class="easyui-window" title="绑定角色"
        closed="true" style="height: 550px; width: 800px;"
        data-options="cache:false,modal:true">

        <form id="form-user-manager-add" class="easyui-form">
        <input type="hidden" name="accountId" id="accountId">
            <table class="add_brand_table">
                <!-- <tr>
                    <td align="right">选择用户：</td>
                    <td><select id="loginName" style="width: 160px;" name="loginName">
                    </select></td>
                </tr> 
                <tr>
                    <td align="right">选择主店：</td>
                    <td><select id="companyId"  style="width: 260px;" name="companyId">
                    </select>
                   </td>
                </tr>
                 <tr>
                    <td align="right">选择分店：</td>
                    <td><select id="storeId"  style="width: 260px;" name="storeId">
                    </select>
                   </td>
                </tr>-->
                <tr>
                    <td align="right">选择角色：</td>
                    <td><select id="roleId" style="width: 160px;" name="roleId">
                    </select>
                   </td>
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
