<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品打印</title>
<style media="screen">
.btn-print { background:#369; border:2px solid #efefef; width:80px; height:30px; line-height:30px; text-align:center; cursor:pointer; display:block; color:#fff; font-size:14px; font-weight:700;}
table tr{line-height: 24px;}
</style>
<style media="print">
.btn-print {display:none;}
.copyright { text-align:center;}
</style>
</head>
<body>
	<div class="content">
		<div id="container">
			<div class="pager">
				<table width="100%" cellspacing="0" cellpadding="2" border="0"
					class="p12black">
					<tbody>
						<tr>
							<td width="3%" valign="middle" height="59"><strong><br>
							</strong></td>
							<td width="40%" valign="middle" class="p12black"><strong>订单信息<br>
							</strong></td>
							<td width="47%" valign="bottom" align="right" class="p9black">
							</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="2" border="0"
					class="p12white">
					<tbody>
						<tr>
							<td class="p12black"><strong>商品信息</strong>
							<hr size="1" noshade=""></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="2" border="0" bgcolor="#F7F7F7" class="p9black">
					<tbody>
						<tr>
							<td width="50%" align="center" height="26" class="p9black">部位<br></td>
							<td width="50%" align="center" height="26" class="p9black">小计</td>
						</tr>
						<c:forEach items="${order.damageOfferList}" var="offerList">
							<tr class="row0">
								<td width="325" align="center" height="20">${offerList.carDamageDetail.carPart.partName}</td>
								<td width="90" align="center" height="20">￥<fmt:formatNumber value="${offerList.offerPrice}" pattern="#,#00.00#"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="2" border="0"
					class="p12white">
					<tbody>
						<tr>
							<td class="p12black"><strong>订单信息</strong>
							<hr size="1" noshade=""></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="4" border="0"
					bgcolor="#F7F7F7" class="p9black">
					<tbody>
						<tr>
							<td width="18%" nowrap="" align="right" class="p9orange">订单号：</td>
							<td width="27%" nowrap="">${order.id}</td>
							<td width="16%" nowrap="" align="right" class="p9orange">状态：</td>
							<td width="39%" nowrap="">
								<c:if test="${order.payStatus eq 0|| order.payStatus eq 500}">
									未付款<c:if test="${order.status eq 'dead'}">[已撤单]</c:if>
								</c:if>										
								<c:if test="${order.payStatus eq '1' && order.status eq 'uninstall'}">已支付[待安装]</c:if>
								<c:if test="${order.payStatus eq '1' && order.status eq 'install'}">已支付[已安装]</c:if>
								<c:if test="${order.payStatus eq '1' && order.status eq 'finish'}">已支付[已完成]</c:if>												
								<c:if test="${order.payStatus eq '1' && order.status eq 'dead'}">已支付[已撤单]</c:if>
							</td>
						</tr>
						<tr>
							<td width="18%" valign="top" nowrap="" align="right" class="p9orange">下单日期：</td>
							<td width="27%" nowrap=""><fmt:formatDate value="${order.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
							<td width="16%" valign="top" nowrap="" align="right" class="p9orange">商品总额：</td>								
							<td width="39%" nowrap="">￥<fmt:formatNumber value="${order.carDamageOffer.totalprice}" pattern="#,#00.00#"/></td>
						</tr>
						<tr>
							<td valign="top" nowrap="" align="right" class="p9orange">支付方式：</td>
							<td nowrap="">${order.payment}</td>
							<td valign="top" nowrap="" align="right" class="p9orange">订单总金额：</td>
							<td nowrap="">￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></td>
						</tr>
						<tr>
						</tr>
						<tr class="allborder">						
							<td colspan="3" valign="top" nowrap="" align="right" class="p9orange">支付总金额：</td>
							<td nowrap="">￥<fmt:formatNumber value="${order.payed}" pattern="#,#00.00#"/></td>
						</tr>
					</tbody>
				</table>
				<br>
				<table width="100%" cellspacing="0" cellpadding="2" border="0"
					class="p12white">
					<tbody>
						<tr>
							<td class="p12black"><strong>联系人</strong>
							<hr size="1" noshade=""></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="4" border="0"
					bgcolor="#F7F7F7" class="p9black">
					<tbody>
						<tr>
							<td width="18%" valign="top" nowrap="" align="right"
								class="p9orange">姓名：</td>
							<td width="27%" nowrap="">${order.name}</td>
							<td width="16%" valign="top" nowrap="" align="right"
								class="p9orange">电话</td>
							<td width="39%" nowrap="">${order.phone }</td>
						</tr>
						<tr>
							<td valign="top" nowrap="" align="right" class="p9orange">附言：</td>
							<td nowrap="" colspan="3">${order.markText}</td>
						</tr>
					</tbody>
				</table>
				<br>
				<table width="100%" cellspacing="0" cellpadding="2" border="0"
					class="p12white">
					<tbody>
						<tr>
							<td class="p12black">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="0" border="0">
					<tbody>
						<tr>
							<td><hr size="1" noshade=""></td>
						</tr>
					</tbody>
				</table>
				<table width="100%" cellspacing="0" cellpadding="0" border="0"
					class="p9black">
					<tbody>
						<tr>
							<td align="center">&nbsp;</td>
						</tr>
					</tbody>
				</table>
				<table cellspacing="0" cellpadding="0" border="0" align="center" class="table-action">
					<tbody>
						<tr>
							<td><div onclick="window.print()" class="btn-print">立即打印</div></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>

