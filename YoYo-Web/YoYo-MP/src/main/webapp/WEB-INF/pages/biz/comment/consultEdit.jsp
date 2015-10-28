<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
    String path = request.getContextPath();
	request.setAttribute("path", path);
	Long time=System.currentTimeMillis();
	request.setAttribute("time", time);
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">   
<meta http-equiv="cache-control" content="no-cache">   
<meta http-equiv="expires" content="0">   
<title>咨询列表</title>
</head>
<body>
<form id="reply_ask">
	<script type="text/javascript" src="${path}/resources/scripts/biz/member/commentDetail.js"></script>
	<table class="consult-table">
		   	<tr>
		   		<td class="txt_center" width="20%" rowspan="6">
		   			<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${content.product.goodsId}" target="_blank"><img width="80px" height="80px" src="${imgUrl}/${content.product.picturePath}" /></a>
		   		</td>
		  	 	<td>商品名称：${content.product.name}</td>  
		   	</tr>
		   	<tr>
		   		<td>发表人：${content.author}</td>
		   	<tr>
		   		<td>发表时间：<fmt:formatDate value="${content.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		   	</tr>
		   	<tr>
		   		<td>卖家：${content.storeName}</td>
		   	</tr>
		   	<tr>
		   		<td>内容：${content.comment}</td>
		   	</tr>
		   	<tr>
		   		<td colspan="2" class="line"></td>
		   	</tr>
		   	<tr>
		   		<td class="txt_center"></td>
		   		<td>
		   			<a href="javascript:void(0);" class="comment-display" reloadMain="true" data-commentId="${content.commentId}" data-display="<c:if test="${content.display==1}">0</c:if><c:if test="${content.display==0}">1</c:if>">
		   				<c:if test="${content.display==1}">[关闭显示]</c:if>
						<c:if test="${content.display==0}">[显示到商品]</c:if>
		   			</a>
		   			&nbsp;
		   			<a href="javascript:void(0);" class="comment-del" close="true" data-commentId="${content.commentId}">[删除]</a>
		   		</td>
		   	</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<c:forEach var="reply" items="${content.replyComment}">
					<div>
						<h4 style="font-weight: normal; padding-left: 0">
							${reply.author}<span style="margin: 0 3px; color: #333" class="assis">于[<fmt:formatDate value="${reply.time}" pattern="yyyy-MM-dd HH:mm:ss" />]回复：</span>
							<span>
							<a href="javascript:void(0);" class="comment-display" data-commentId="${reply.commentId}" data-display="<c:if test="${reply.display==1}">0</c:if><c:if test="${reply.display==0}">1</c:if>">
								<c:if test="${reply.display==1}">[关闭显示]</c:if>
								<c:if test="${reply.display==0}">[显示到商品]</c:if>
							</a>
							[<a href="javascript:void(0);" class="comment-del" data-commentId="${reply.commentId}">删除</a>]
							</span>
						</h4>
						<span>
							 内容：${reply.comment}
						</span>
					</div>
					</c:forEach>					
				</td>
			</tr>
			<tr>
		   		<td class="txt_center">回复:	</td>
		   		<td>
		   		   	
		   			<div class="ap-ct-textinput">
		   				<textarea contenteditable="true" name="comment" id="reply_text"></textarea>
		   			</div>
		   		</td>
		   	</tr>
		   	<tr>
		   		<td colspan="2" class="txt_center">
					<input type="hidden" name="forCommentId" value="${content.commentId}" />
					<input type="hidden" name="typeId" value="0" />
					<input type="hidden" name="productId" value="${content.product.productId}" />
					<input type="hidden" name="objectType" value="ask" />
					<input type="hidden" name="toId" value="${content.authorId}" />
					<input type="hidden" name="pIndex" value="1" />
					<input type="hidden" name="commentsType" value="2" />
		   			<button type="button" onclick="saveCommentAsk();">提交</button>
		   			<button type="button" onclick="closeCommentAsk();">取消</button>
		   		</td>
		   	</tr>
	</table>
</form>
</body>

</html>

