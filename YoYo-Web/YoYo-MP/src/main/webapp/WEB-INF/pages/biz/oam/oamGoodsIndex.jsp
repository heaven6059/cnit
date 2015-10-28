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
    <script type="text/javascript" src="${path}/resources/scripts/biz/oam/oamGoodsIndex.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js?v=${versionVal}"></script>
    <script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
    <script type="text/javascript" src="${path}/resources/scripts/easyui/plugins/datagrid-detailview.js"></script>
    <div id="toolbar-car_dept">
        <a href="#" class="easyui-linkbutton" style="width: 80px;" id="checkBtn">审核</a>
        <!-- <a href="#"
            style="margin-left: 50px;" class="easyui-linkbutton"
            iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_dept')">高级筛选</a> -->
    </div>
    <table id="table-oam-goods"></table>

	<div id="checkWindow" class="easyui-dialog" title="审核商品" closed="true" style="width:500px; height: 200px; padding: 2px;">
		<div style="padding:5px;text-align:center;">
			 <div style="text-align:left;">审核备注:&nbsp;&nbsp;<textarea rows="5" cols="40" id="cause"></textarea><br/><br/></div>
			 
			<a href="#" class="easyui-linkbutton" icon="icon-ok" id="checkok">审核通过</a>&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="#" class="easyui-linkbutton" icon="icon-cancel" id="checkcancel">审核不通过</a>
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
    
    
    
</body>
</html>
