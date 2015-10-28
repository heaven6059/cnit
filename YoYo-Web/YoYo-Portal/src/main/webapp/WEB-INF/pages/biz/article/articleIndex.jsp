<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="imagetoolbar" content="no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">

<script type="text/javascript">
	var _path = "${path}";
</script>
<link href="${path}/resources/styles/article/articleIndex.css?r=${versionVal}" type="text/css" rel="stylesheet">
<title>${title}</title>
</head>
<body>
	<div class="add_con clearfix">
		<div class="subside-box">
			<div class="subside-in">
				<h3 class="h3-title">帮助中心</h3>
				<c:forEach items="${list}" var="article_1">
					<dl class="subside-mod">
						<dt class="title">
							${article_1.nodeName} <b class="icon02"></b>
						</dt>
						<c:forEach items="${article_1.children}" var="article_2">
							<dd class="subside-cnt">
								<ul class="subside-list">
									<c:choose>
										<c:when test="${article_2.nodeId == selectedId}">
											<c:set var="currentRootNode" value="${article_1}" />
											<c:set var="currentNode" value="${article_2}" />
											<input type="hidden" value="${article_2.nodeName}">
											<input id="selectedId" type="hidden" value="${article_2.nodeId}">
											<li class="list-item current" data-id="28" data-name="${article_2.nodeName}" data-parent-id="21" data-parent-name="${article_1.nodeName}"><a href="javascript:void(0)">${article_2.nodeName}</a></li>
										</c:when>
										<c:otherwise>
											<li class="list-item " data-id="28" data-name="${article_2.nodeName}" data-parent-id="21" data-parent-name="${article_1.nodeName}"><a href="${path}/article/index?nodeId=${article_2.nodeId}">${article_2.nodeName}</a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</dd>
						</c:forEach>
					</dl>
				</c:forEach>
			</div>
		</div>

		<!-- 内容区 -->
		<div class="content">
			<div class="breadcrumb">
				<span id="sLevel1">${currentRootNode.nodeName}</span>&nbsp;&gt; <span id="sLevel2">${currentNode.nodeName}</span>
			</div>
			<div class="tabs-01" id="all-ques-tab">
				<ul class="tab">
					<li id="ques_list_all" class="list-item current"><strong class="dk-line"> <a id="articleAll" href="javascript:void(0)">全部</a>
					</strong></li>
					<c:forEach items="${currentNode.children}" var="article_3">
						<li class="list-item"><strong class="dk-line">${article_3.nodeName}</strong> <input type="hidden" value="${article_3.nodeId}"></li>
					</c:forEach>
				</ul>
				<div class="tabcons">
					<div class="tabcon">
						<h4 class="help-tit-l2">问题知识列表</h4>
						<ul class="help_list">
							<c:forEach items="${currentNode.children}" var="article_3">
								<c:forEach items="${article_3.articleIndexList}" var="articleIndex">
									<li><a href="javascript:viewContent(${articleIndex.articleId})"> <b>·</b> ${articleIndex.title}
									</a></li>
								</c:forEach>
							</c:forEach>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/biz/article/articleIndex.js?ver=ab"></script>
</body>
</html>




<!-- 	    	<dl class="subside-mod on"> -->
<!-- 	            <dt class="title"> -->
<!-- 	            	购物指南 <b class="icon02"></b> -->
<!-- 	            </dt> -->
<!-- 	            <dd class="subside-cnt"> -->
<!-- 	                <ul class="subside-list"> -->
<!-- 		                <li class="list-item" data-id="28" data-name="交易条款" data-parent-id="21" data-parent-name="购物指南"> -->
<!-- 		                    <a href="http://help.jd.com/user/issue/list-28.html">交易条款</a> -->
<!-- 		                </li> -->
<!-- 	                </ul> -->
<!-- 	            </dd> -->
<!-- 	        </dl> -->
<!-- 			<dl class="subside-mod"> -->
<!-- 	            <dt class="title"> -->
<!-- 	               	 账户及会员 <b class="icon02"></b> -->
<!-- 	            </dt> -->
<!-- 	            <dd class="subside-cnt"> -->
<!-- 	                <ul class="subside-list"> -->
<!-- 	                    <li class="list-item current" data-id="151" data-name="会员介绍" data-parent-id="26" data-parent-name="账户及会员"> -->
<!-- 	                        <a href="http://help.jd.com/user/issue/list-151.html">会员介绍</a> -->
<!-- 	                    </li> -->
<!-- 					</ul> -->
<!-- 	            </dd> -->
<!-- 	        </dl> -->