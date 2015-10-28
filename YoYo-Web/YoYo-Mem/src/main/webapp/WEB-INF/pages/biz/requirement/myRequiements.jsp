<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    request.setAttribute("time", System.currentTimeMillis());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的需求</title>
<link type="text/css" href="${path}/resources/styles/member.css?v=${time}" rel="stylesheet" />
<style type="text/css">
	.comment-item {
	  border: 1px solid #ddd;
	  margin-top:5px;
	}
	.reply-item{
	  padding: 10px;
	}
	.reply-infor-two{
   		padding-left: 20px;
	}
	.reply-infor-two .user-name{
		color: #005aa0;
	}
	.reply-infor-two .time,.reply-infor-one .time{
    	margin-left: 30px;
	}
	.reply-textarea .reply-input {
	  background-color: #fff;
	  border: 1px solid #ddd;
	  display: block;
	  height: 80px;
	  line-height: 20px;
	  padding: 3px 5px;
	  width: 99%;
	}
	.showContent{
		cursor: pointer;    
		height: 25px;    
		line-height: 25px;    
	    margin: -30px 10px;    
	    background-color: #f5f5f5;    
	    border: 1px solid #ddd;    
	    display: inline-block;    
	    padding: 0 14px;
	    float: right;
	}
	.words{
		word-wrap: break-word;
  		word-break: normal;
	}
</style>
</head>
<body>
	<div class="member-main member-main2">
		<div class="orderlist-warp">
			<div class="title">我的需求</div>
			<div class="clr"></div>
			<div　class="mc ui-switchable-panel comments-table ui-switchable-panel-selected"　id="comment-0" style="display: block;">
				<div class="com-table-header">
					<span class="item column1" style="width:948px;">咨询内容</span> 
				</div>
				<div class="com-table-main">
				<!-- 评论内容 -->
				</div>
				<div class="com-table-footer">
					<div class="page clearfix">
						<div id="Pagination" class="yoyoPagination"></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/biz/requirement/myRequiements.js?v=${time}"></script>
</body>
</html>

