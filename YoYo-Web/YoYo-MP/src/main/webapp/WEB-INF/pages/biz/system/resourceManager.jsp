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
<title>资源管理</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
    
    <script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
    <link type="text/css" href="${path}/resources/styles/select2/select2.min.css"  rel="stylesheet" />
    <script type="text/javascript" src="${path}/resources/scripts/biz/system/resourceManager.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <div id="toolbar-resource-manager">
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true"
            onclick="openSaveDialog()"> 添加</a> <a
            class="easyui-linkbutton" iconCls="icon-remove" plain="true"
            onclick="deleteResource()"> 删除 </a> 
    </div>
    <table id="table-resource-manager"></table>


    <!-- 新增资源-->
    <div id="window-add-resource-manager" class="easyui-window" title="添加资源"
        closed="true" style="height: 550px; width: 800px;"
        data-options="cache:false,modal:true"">

        <form id="form-resource-manager-add" class="easyui-form">
        <input type="hidden" name="id" id="id">
            <table class="add_brand_table">
                <tr>
                    <td align="right">资源名称：</td>
                    <td><input type='text' name="resourceName" id="resourceName" class="tab_input"  /></td>
                </tr>
                <tr>
                    <td align="right">资源类型：</td>
                    <td><select id="resourceType" style="width: 160px;" name="resourceType">
                    	<option value="menu">菜单</option>
                    	<option value="button">按钮</option>
                    </select></td>
                </tr>
                <tr>
                    <td align="right">是否叶节点：</td>
                    <td><select id="target" style="width: 160px;" name="target">
                    	<option value="0">否</option>
                    	<option value="1">是</option>
                    </select></td>
                </tr>
                <tr>
                    <td align="right">资源路径：</td>
                    <td><input type='text' name="url" id="url" class="tab_input"  /></td>
                </tr>
                <tr>
                    <td align="right">权限：</td>
                    <td><input type='text' name="permission" id="permission" class="tab_input"  /></td>
                </tr>
                <tr>
                    <td align="right">级别：</td>
                    <td><input type='text' name="menuOrder" id="menuOrder" class="tab_input"  /></td>
                </tr>
                <tr>
                    <td align="right">父节点：</td>
                    <td><input type='text' name="parentId" id="parentId" class="tab_input"  /></td>
                </tr>
            </table>
        </form>

        <div region="south" border="false"
            style="text-align: center; height: 30px; line-height: 30px; margin-bottom: 10px;">
            <a class="easyui-linkbutton" id="add-resource-btn" icon="icon-save" onclick="saveResource()">保存</a>
                <a class="easyui-linkbutton" id="close-resource-btn" icon="icon-cancel"
                onclick="javascript:closeDailog('window-add-resource-manager');" style="margin-left: 15px;">取消</a>
        </div>
    </div>



    
</body>
</html>
