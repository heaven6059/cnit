<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>店铺首页管理</title>

<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<link type="text/css" href="${path}/resources/styles/tradeMng/tradeManager.css?t=${time}" rel="stylesheet" />
</head>
<body>
<div class="member-main member-main2">
<form action="../shopIndexManager/editShopIndexClassify" method="post" id="classify_form">
	<table width="100%" cellspacing="0" cellpadding="0" border="0" class="gridlist" id="bottom_left_talbel">
		<thead>
			<tr>
				<th width="35%">关键字名称</th>
				<th width="55%">关键字链接</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="leftPush" items="${shopIndexPush.leftPushs}">
			<tr>
				<td><input type="text" validate='{"msg":"关键字不能为空","type":"isNull"}'  name="bottom-left-keyword" value="${leftPush.title}" class="x-input" style="width:180px"/></td>
				<td><input type="text" validate='{"msg":"请输入真确的链接地址","type":"isHref"}'  name="bottom-left-href" value="${leftPush.href}" class="x-input" style="width:340px"/></td>
				<td><a href="javascript:;" class="del_cur_row">删除</a></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="title" style="text-align: center;margin-top: 5px;">
		<input name="id" type="hidden" value="${shopIndexPush.id}" />
		<button class="btn search1" id="bottom_left_keyword_add_rows" type="button"><span><span>添加</span></span></button>
		<button class="btn search1" id="bottom_left_keyword_save" type="button"><span><span>保存</span></span></button>
		<button class="btn search1" onclick="history.go(-1);" type="button"><span><span>返回</span></span></button>
	</div>
</form>
</div>
<script type="text/javascript">
	$(function (){
		$("#bottom_left_keyword_add_rows").click(function (){
			var html=[];
			html.push('<tr>');				
			html.push('<td><input type="text" validate=\'{"msg":"关键字不能为空","type":"isNull"}\' name="bottom-left-keyword" class="x-input" style="width:180px"/></td>');
			html.push('<td><input type="text" validate=\'{"msg":"请输入真确的链接地址","type":"isHref"}\' name="bottom-left-href" class="x-input" style="width:340px"/></td>');
			html.push('<td><a href="javascript:;" class="del_cur_row">删除</a></td>');
			html.push('</tr>');
			$("#bottom_left_talbel").append(html.join(""));
		});
		
		$("#bottom_left_talbel").on("click",".del_cur_row",function (){
			$(this).parent().parent().remove();
		});
		$("#bottom_left_keyword_save").click(function (){		
			validate_utils.validate();
			if(validate_utils.result){
				$("#classify_form").submit();
			}
		});
	});
	
	var validate_utils={
			result:true,
			validate:function (){
				var inputs=$("#bottom_left_talbel").find("input[name^='bottom-left']");
				if(inputs.length==0){
					layer.msg("请完善信息",{time:3000});
					validate_utils.result = false;
					return;
				}
				for(var i=0;i<inputs.length;i++){
					var item=inputs[i];
					if($(item).attr("validate")){
						var required=JSON.parse($(item).attr("validate"));
						if(required.type=="isNull"){
							if($(item).val().length==0){
								layer.msg(required.msg,{time:3000});
								validate_utils.result = false;						
								 break;
							}
						}else if(required.type=="isHref"){
							var Expression=/http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
							var objExp=new RegExp(Expression);
							if(!objExp.test($(item).val())){
								layer.msg(required.msg,{time:3000});
								validate_utils.result=false;
								break;
							}
						}
					}
					validate_utils.result=true;
				}
			}
		};
</script>
</body>
</html>



