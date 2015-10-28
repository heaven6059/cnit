<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>活动申请详情</title>
<link type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/tradeMng/orderdetail.css?v=${versionVal}" rel="stylesheet" />
</head>
<body>
	<div class="member-main member-main2">
		<div style="height: auto">
			<div class="title">活动申请信息</div>
			<table width="100%" cellspacing="0" cellpadding="0" border="0" style="margin: 10px 0 20px 0" class="order-info">
				<tbody>
					<tr><td width="30%" valign="top">
							<ul>
								<li>申请状态：
									<span class="siteparttitle-blue">
										<c:if test="${detail.status eq '1'}">待审核</c:if>
										<c:if test="${detail.status eq '2'}">审核通过</c:if>
										<c:if test="${detail.status eq '3'}">审核未通过</c:if>
									</span>
								</li>
								<li>申请时间：<fmt:formatDate value="${detail.lastMidifity}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
								<li>参与商品：${detail.goodsName }</li>
								<li>商品分类：${detail.catName }</li>
								<li>参加活动商品数量：${detail.nums }</li>
								<li>每人限购：${detail.personlimit }</li>
								<li>参与库存：${detail.nums }</li>
								<li>剩余库存：${detail.remainnums }</li>
								<li>参与价格：${detail.price }</li>
								<li>最终活动价格：${detail.lastPrice }</li>
								<li>店铺名称：${detail.storeName }</li>
							</ul>
						</td>
					</tr>
				</tbody>
			</table>
			<div style="border: none" class="order-track">
				<div id="orderinfo">
					<div class="mc">
						<dl class="fore">
							<dt>活动信息</dt>
							<dd>
								<ul>
									<li>活动名称：${detail.actName}</li>
									<li>活动状态：<c:if test="${detail.actOpen eq '0'}">开启</c:if><c:if test="${detail.actOpen eq '2'}">关闭</c:if></li>
									<li>开始时间：<fmt:formatDate value="${detail.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
									<li>结束时间：<fmt:formatDate value="${detail.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
									<li>参与活动最低库存限制：${detail.limitNum}</li>
								</ul>
							</dd>
						</dl>
						<dl>
							<dt>备注</dt>
							<dd>
								<ul>
									<li>备注内容：${detail.remark}</li>
								</ul>
							</dd>
						</dl>
					</div>
				</div>
			</div>
			<div style="text-align: center; margin-top: 20px;">
				<button class="btn btn-primary" type="button" id="saleUpSubmit"  onclick="preActivityList()">
					<span><span>返回</span></span>
				</button>
			</div>
		</div>
	</div>
<script type="text/javascript">
	function preActivityList(){
		window.location.href = _path + '/activityApply/myActivityList';
	}
</script>
</body>
</html>