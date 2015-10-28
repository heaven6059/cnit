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
<form id="reply_comment">
	<script type="text/javascript" src="${path}/resources/scripts/biz/member/commentDetail.js?t=${time} "></script>
	<table class="consult-table">
		   	<tr>
		   		<td class="txt_center" width="20%" rowspan="5">
		   			<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${content.goodsId}" target="_blank"><img width="80" height="80" src="${content.picturePath}" /></a>
		   		</td>
		  	 	<td>商品名称：${content.productName}</td>  
		   	</tr>
		   	<tr>
		   		<td>评论人：${content.loginName}</td>
		   	<tr>
		   		<td>联系方式：${content.mobile}</td>
		   	</tr>
		   	<tr>
		   		<td>发表时间：<fmt:formatDate value="${content.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		   	</tr>
		   	<tr>
		   		<td>
		   			<a href="javascript:void(0);" class="comment-display" reloadMain="true" data-commentId="${content.commentId}" data-display="<c:if test="${content.display==1}">0</c:if><c:if test="${content.display==0}">1</c:if>">
		   				<c:if test="${content.display==1}">[关闭显示]</c:if>
						<c:if test="${content.display==0}">[显示到商品]</c:if>
		   			</a>
		   		</td>
		   	</tr>
		   	<tr>
		   		<td colspan="2" class="line"></td>
		   	</tr>
		   	<tr>
			   <td class="txt_center">评论标题:</td>
			   <td>${content.title}</td>
		   	</tr>
		   	<tr>
			   <td class="txt_center">评论内容:</td>
			   <td>${content.comment}</td>
		   	</tr>
			<tr>
				<td>&nbsp;</td>
				<td>
					<c:forEach var="reply" items="${content.replyComment}">
					<div>
						<h4 style="font-weight: normal; padding-left: 0">
							<span>
							<a href="javascript:void(0);" class="comment-display" data-commentId="${reply.commentId}" data-display="<c:if test="${reply.display==1}">0</c:if><c:if test="${reply.display==0}">1</c:if>">
								<c:if test="${reply.display==1}">[关闭显示]</c:if>
								<c:if test="${reply.display==0}">[显示到商品]</c:if>
							</a>
							[<a href="javascript:void(0);" class="comment-del" data-commentId="${reply.commentId}">删除</a>]
							</span>
						</h4>
						<span>
							 回复内容：${reply.comment}&nbsp;(<fmt:formatDate value="${reply.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />)
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
		   			<input type="hidden" name="toName" value="${content.loginName}" />
		   			<input type="hidden" name="toId" value="${content.memberId}" />
					<input type="hidden" name="forCommentId" value="${content.commentId}" />
					<input type="hidden" name="productId" value="${content.productId}" />
					<input type="hidden" name="orderId" value="${content.orderId}" />
					<input type="hidden" name="orderItemId" value="${content.orderItemId}" />
		   			<button type="button" onclick="saveComment();">提交</button>
		   			<button type="button" onclick="closeComment();">取消</button>
		   		</td>
		   	</tr>
	</table>
</form>
<script type="text/javascript">
$(function (){
	$(".consult-table").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
})
</script>
</body>
</html>

