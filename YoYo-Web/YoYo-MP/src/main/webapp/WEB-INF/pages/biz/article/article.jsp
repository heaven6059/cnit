<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	<title>文章列表</title>
	<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
	<script type="text/javascript" src="${path}/resources/scripts/biz/article/article.js?v=${versionVal}"></script>    
</head>
<body>
    <div id="toolbar-violation">
        <!-- <a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="openSaveDialog('add')"> 添加</a> -->
        <a class="easyui-linkbutton" iconCls="icon-add" plain="true" href="${path}/article/addArticle"> 添加</a>
        <a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="deleteArticle()"> 删除 </a>
        <a href="javascript:;" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openSeachWindow()">高级筛选</a>
    </div>
    <table id="table-article"></table>
	
	<!-- <div id="window-add-violation" class="easyui-dialog"  style="width:400px;height:200px;max-width:800px;padding:10px" data-options="
            iconCls:'icon-save',closed:true,
            onResize:function(){
                $(this).dialog('center');
            }">        
    </div> -->
	
	

	
	
	<div id="advance_search_article" class="easyui-dialog advance_search" title="高级筛选"
        style="width: 380px; height: 380px; padding: 10px 20px; background: #F5F5F5;"
        data-options="closed:true,modal:false,cache:false,buttons:'#search_btn'">
		<form id="article_search_form" class="easyui-form" method="post"
			data-options="novalidate:true">
			<table style="border-collapse: separate; border-spacing: 5px;width:100%;">
				<tbody>
		            <tr>
	                    <td align="right"><span style="color: red;">*</span>所属栏目：</td>
	                    <td>
	                    	<select id="search-combox-article-nodes" style="width: 124px;" name="nodeId" data-options="required:true"></select>
	                    </td>
	                </tr>
					<tr>
						<td align="right"><label>文章标题：</label></td>
						<td><input type="text" name="title" style="width: 120px;" class="easyui-text" data-options="precision:0,min:0" /></td>
					</tr>
			</table>
		</form>
	</div>
    <div id="search_btn">
        <a href="javascript:void(0)" class="easyui-linkbutton c6"
            iconCls="icon-ok" onclick="advanceQuery('table-article','article_search_form')" style="width: 90px">立即筛选</a>
        <a href="javascript:void(0)" class="easyui-linkbutton"
            iconCls="icon-cancel" onclick="clearCondition('article_search_form')" style="width: 90px">清除条件</a>
    </div>
	<div id="MODALPANEL" style="position: absolute; width: 100%; height: 100%; top: 0px; left: 0px; z-index: 6000; opacity: 0.4; background: rgb(51, 51, 51);display:none;"></div>
</body>
</html>
