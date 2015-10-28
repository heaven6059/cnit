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
<title>角色管理</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css" href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
    <link type="text/css" href="${path}/resources/styles/css/zTreeStyle.css"  rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/system/roleManager.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/zTree_v3/jquery.ztree.core-3.5.min.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/zTree_v3/jquery.ztree.excheck-3.5.min.js"></script>
    <div id="toolbar-role-manager">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteRole()"> 删除 </a> 
    </div>
    <table id="table-role-manager"></table>


    <!-- 新增角色-->
    <div id="window-add-role-manager" class="easyui-window" title="添加角色"
        closed="true" style="height: 550px; width: 800px;"
        data-options="cache:false,modal:true">

        <form id="form-role-manager-add" class="easyui-form">
        <input type="hidden" name="roleId" id="roleId">
            <table class="add_brand_table">
                <tr>
                    <td align="right">角色名称：</td>
                    <td><input type='text' name="roleName" id="roleName" class="tab_input"  /></td>
                </tr>
                <tr>
                    <td align="right">角色描述：</td>
                    <td><input type='text' name="description" id="description" class="tab_input"  /> </td>
                </tr>
               <!--  <tr>
                    <td align="right">选择资源：</td>
                    <td><select id="resourceId" class="easyui-combotree" style="width: 260px;" name="resourceId">
                    </select>
                   </td>
                </tr> -->
                <tr>
                    <td align="right">选择资源：</td>
                    <td><ul id="treeDemo" class="ztree"></ul>
                   </td>
                </tr>
            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-role-btn" icon="icon-save" onclick="saveRole()">保存</a>
                <a class="easyui-linkbutton" id="close-role-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-role-manager');" style="margin-left: 15px;">取消</a>
        </div>
    </div>



    
</body>
</html>
