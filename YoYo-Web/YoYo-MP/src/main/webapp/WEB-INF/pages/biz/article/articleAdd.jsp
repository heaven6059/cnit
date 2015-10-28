<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>添加文章</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<%-- <script type="text/javascript" src="${path}/resources/scripts/biz/article/article.js"></script> --%>
<link type="text/css"
	href="${path}/resources/styles/article/articleAdd.css?v=${versionVal}"
	rel="stylesheet" />
<link type="text/css"
	href="${path}/resources/scripts/cleditor/jquery.cleditor.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="${path}/resources/scripts/cleditor/jquery.cleditor.min.js"></script>
<script type="text/javascript"
	src="${path}/resources/scripts/cleditor/jquery.cleditor.xhtml.min.js"></script>
<script type="text/javascript"
	src="${path}/resources/scripts/biz/article/articleAdd.js?v=${versionVal}"></script>
</head>
<body>
	<div class="content-main" id="main" style="height: 722px; width: 1338px;">
		<form method="post" name="aEditor" id="form-article-add">
			<input type="hidden" name="id" value="${article.id}" id="id">
			<input type="hidden" name="hideNodeId" value="${article.nodeId}"id="hideNodeId">
			<div id="aEditor-Body">
				<div class="spage-main-box">
					<h3>基本信息</h3>
					<div id="x-g-basic" class="goods-detail">
						<div class="tableform">
							<div class="division">
								<table border="0" cellpadding="0" cellspacing="0">
									<tbody>
										<tr>
											<th><em><font color="red">*</font></em>文章标题：</th>
											<td><input autocomplete="off" class="x-input " type="text" id="article_title" name="title" required="true" vtype="required" value="${article.title }"></td>
										</tr>
										<tr>
											<th><em><font color="red">*</font></em>所属文章类目：</th>
											<td><select id="combox-article-node" name="nodeId" style="width: 300px; padding: 3px 5px; height: 28px;"></select>
											</td>
										</tr>
										<tr>
											<th>作者：</th>
											<td><input autocomplete="off" class="x-input " type="text" name="author" style="width: 60px" maxlength="30" id="article_author" vtype="text" value="${article.author}"></td>
										</tr>
										<tr>
											<th>发布：</th>
											<td>
												<c:choose>
													<c:when test="${article!=null && article.ifpub==0}">
														<input type="radio" value="1" name="ifpub">是					 	
				 										<input type="radio" value="0" name="ifpub" checked="checked">否
				 									</c:when>
													<c:otherwise>
														<input type="radio" value="1" name="ifpub" checked="checked">是					 	
				 										<input type="radio" value="0" name="ifpub">否
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
				<div class="spage-main-box">
					<h3>文章内容</h3>
					<textarea id="cle-article-content" name="content">${article.content}</textarea>
				</div>
			</div>
			<div style="text-align: center; margin-top: 20px;">
				<a class="easyui-linkbutton" icon="icon-save" onclick="saveOrUpdateArticle()">保存</a>
			</div>
		</form>
	</div>
</body>
</html>
