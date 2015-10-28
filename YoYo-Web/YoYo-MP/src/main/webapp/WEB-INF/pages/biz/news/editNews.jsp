<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Long time = System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/styles/article/articleAdd.css?v=${versionVal}" rel="stylesheet" />
<script type="text/javascript">
	var hasContent = ${news.hasContent};
</script>
<script type="text/javascript" src="${path}/resources/scripts/cleditor/jquery.cleditor.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cleditor/jquery.cleditor.xhtml.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/news/editNews.js?v=${time}"></script>
</head>
<body>
	<div class="content-main" id="main" style="height: 722px; width: 1338px;">
		<form method="post" id="newsForm">
			<input type="hidden" name="newsId" value="${news.newsId}" id="id">
			<div id="aEditor-Body">
				<div class="spage-main-box">
					<h3>基本信息</h3>
					<div id="x-g-basic" class="goods-detail">
						<div class="tableform">
							<div class="division">
								<table border="0" cellpadding="0" cellspacing="0">
									<tbody>
										<tr>
											<th><em><font color="red">*&nbsp</font></em>标题：</th>
											<td><input autocomplete="off" class="easyui-validatebox" type="text" id="title" name="title" value="${news.title }" style="width:250px"></td>
										</tr>
										<tr>
											<th><em><font color="red">*&nbsp</font></em>类型：</th>
											<td><input autocomplete="off" class="easyui-validatebox" type="text" id="type" name="type" value="${news.type }" style="width:120px"></td>
										</tr>
										<tr>
											<th>作者：</th>
											<td><input autocomplete="off" class="easyui-validatebox"  type="text" name=""author"" style="width:250px" id="author" value="${news.author}"></td>
										</tr>
										<tr>
											<th><em style="display:none" id="showUrl"><font color="red">*&nbsp</font></em>链接地址：</th>
											<td><input autocomplete="off" class="x-input " type="text" name="url" id="url" value="${news.url}" style="width:250px">
										</tr>
										<tr>
											<th><em><font color="red">*&nbsp</font></em>发布：</th>
											<td>
												<c:choose>
													<c:when test="${news.ifpub eq 'false' }">
														<input type="radio" value="1" name="ifpub" >是	
														<input type="radio" value="0" name="ifpub" checked="checked">否
													</c:when>
													<c:otherwise>
														<input type="radio" value="1" name="ifpub" checked="checked">是	
														<input type="radio" value="0" name="ifpub" >否
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
										<tr>
											<th><em><font color="red">*&nbsp</font></em>是否有内容：</th>
											<td>
											<c:choose>
													<c:when test="${news.hasContent eq 'false'}">
														<input type="radio" value="1" name="hasContent">是					 	
		 												<input type="radio" value="0" name="hasContent" checked="checked">否
													</c:when>
													<c:otherwise>
														<input type="radio" value="1" name="hasContent" checked="checked">是					 	
		 												<input type="radio" value="0" name="hasContent" >否
													</c:otherwise>
												</c:choose>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
				<div class="spage-main-box" id="richText" >
					<h3>文章内容</h3>
					<textarea id="cle-news-content" name="content">${news.newsContent.content}</textarea>
				</div>
			</div>
			<div style="text-align: center; margin-top: 20px;">
				<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdate()">保存</a>
			</div>
		</form>
	</div>
</body>
</html>