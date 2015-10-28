<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    Long time = System.currentTimeMillis();
    request.setAttribute("time", time);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${path}/resources/styles/news/more.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
<script src="${path}/resources/scripts/biz/news/more.js?v=${time}"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更多</title>
</head>
<body>
	<div class="w main clearfix">
	    <div class="content fl" id="left">
	        <div class="m">
	        	<div class="mt"><h2>特惠新闻</h2>
	        		<div class="form">
	        			<input type="text" class="text" id="textPreference">
	        			<input type="button" onclick="queryNews(0)" class="btn-search" value="搜索">
	        		</div>
	        		<div class="extra"></div>
	        	</div>
	        	<div id="news_div" class="mc"><h5><span>发表时间</span>标题</h5>
	            	<ul id="preferenceUl">
					</ul>
	        	</div>
	        </div>
	        <div id="preference" class="yoyoPagination"></div> 
	    </div>
		<div class="content fr" id="right">
			<div class="m">
				<div class="mt"><h2>公告新闻</h2>
					<div class="form">
						<input type="text" id="textNotice" class="text">
						<input type="button" onclick="queryNews(1)" class="btn-search" value="搜索">
					</div>
					<div class="extra"></div>
				</div>
				<div class="mc" id="active_div"><h5><span>发表时间</span>标题</h5>
					<ul id="noticeUl">
					</ul>
				</div>
			</div>
			<div id="notice" class="yoyoPagination"></div> 
		</div>
	</div>
</body>
</html>