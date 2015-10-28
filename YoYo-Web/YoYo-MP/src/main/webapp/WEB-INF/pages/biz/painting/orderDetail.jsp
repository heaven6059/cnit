<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
	<title>订单详情</title>
	<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
</head>
<body>
	<div class="easyui-layout"  style="min-height: 2000px;" >
		<div region="center" border="true" fit="true" style="min-height: 20px;">
			<div id="tab-cate-add" class="easyui-tabs">
				<div title="基本信息">
					<table  class="shop-detail" border="1" cellspacing="1" >
					   <tr>
							<th align="center" class="shop-detail-th" colspan="2">订单信息</th>
							<th align="center" class="shop-detail-th" colspan="2">买家信息</th>
							<th align="center" class="shop-detail-th" colspan="2">卖家信息</th>
						</tr>
						<tr><td colspan="8"><hr></td></tr>
						<tr>
							<td  width="8%" class="shop-detail-label">订单号：</td>
							<td  width="15%">${order.id }</td>
							<td  width="8%" class="shop-detail-label">买家账号：</td>
							<td  width="15%">${order.pamAccount.loginName }</td>
							<td  width="14%" class="shop-detail-label">店铺名称：</td>
							<td  width="15%">${order.store.storeName}</td>
						</tr>
						<tr>
							<td class="shop-detail-label">订单金额：</td>
							<td>${ order.payed }</td>
							<td class="shop-detail-label">收货人：</td>
							<td>${ order.name }</td>
							<td class="shop-detail-label">店铺地址：</td>
							<td>${ order.store.area }&nbsp${ order.store.addr }</td>
					    </tr>
						<tr>
							<td class="shop-detail-label">订单状态：</td>
							<td>
								<c:choose>
								    <c:when test="${order.status == 'active'}">
								    	活动订单
								    </c:when>
								    <c:when test="${order.status=='dead'}">
								    	已取消
								    </c:when>
								    <c:when test="${order.status=='finish'}">
								    	已完成
								    </c:when>
								    <c:when test="${order.status=='unconfirm'}">
								    	待确认
								    </c:when>
								    <c:when test="${order.status=='create'}">
								    	提交订单
								    </c:when>
								    <c:when test="${order.status=='uninstall'}">
								    	未安装
								    </c:when>
								    <c:when test="${order.status=='install'}">
								    	安装中
								    </c:when>
								</c:choose>
							</td>
							<td class="shop-detail-label">收货号码：</td>
							<td>
								${order.phone}
							</td>
							<td class="shop-detail-label">联系电话：</td>
							<td>${order.store.tel}</td>
						</tr>
						<tr>	
							<td class="shop-detail-label">支付状态：</td>
							<td>
								<c:choose>
								    <c:when test="${order.payStatus=='0'}">
								    	未支付
								    </c:when>
								    <c:when test="${order.payStatus=='1'}">
								    	已支付
								    </c:when>
								    <c:when test="${order.payStatus=='2'}">
								    	已付款至担保方
								    </c:when>
								    <c:when test="${order.payStatus=='3'}">
								    	部分付款
								    </c:when>
								    <c:when test="${order.payStatus=='4'}">
								    	部分退款
								    </c:when>
								    <c:when test="${order.payStatus=='5'}">
								    	全额退款
								    </c:when>
								</c:choose>
							</td>
						</tr>
					</table>
				</div>
				<div title="受损部位">
					<div id="order-detail-good-info" style="padding: 0px 20px;margin-top: 20px;">
						<div class="tableform">
			      			<table cellpadding="20" class="gridlist"  width="100%" border="0" cellspacing="20">
								<col style="width:15%"/>
								<col style="width:15%"/>
								<col style="width:35%"/>
								<col style="width:35%"/>
			      				<thead>
			      					<tr><th align="center">部位</th><th align="center">单价</th><th align="center">买家留言</th><th align="center">卖家留言</th></tr>
			      				</thead>
			      				<tbody>
									<c:forEach items="${order.damageOfferList}" var="damageOfferList">
						           		<tr>
								          <td align="center">${damageOfferList.carDamageDetail.carPart.partName}</td>
								          <td align="center">${damageOfferList.offerPrice}</td>
								          <td align="center">${damageOfferList.remark}</td>
								          <td align="center">${damageOfferList.carDamageDetail.remark}</td>
				        				</tr>
						            </c:forEach>
			      				</tbody>
			      			</table>
				        </div>
					</div>
				</div>
				<div title="收退款记录">
					<div id="order-detail-refund-info" style="padding: 0px 20px;">
						<h3>收退款记录</h3>
					    <table width="100%" border="0" cellspacing="0" cellpadding="0">
					  		<tr>
					    		<td style="vertical-align:top">
						    		<div class="tableform">
						        		<h4>收款单据列表</h4>
						        		<div class="division">
						          			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist">
						          				<col class="Coldate"></col>
						          				<col class="Colamount"></col>      
						      					<col></col> 
						      					<col></col> 
						            			<thead>
						              				<tr>
						                				<th>单据日期</th>
						                				<th>支付金额</th>
						                				<th>支付方式</th>
						                				<th>状态</th>
						              				</tr>
						            			</thead>
						            			<tbody>
						                        	<tr>
										              <td>${payment.tBegin}</td>
										              <td class="Colamount">${payment.money}</td>
										              <td>${payment.payAppId}</td>
										              <td>
										              	<c:choose>
															<c:when test="${payment.status == 'succ'}">
																支付成功
															</c:when>
															<c:when test="${payment.status == 'failed'}">
																支付失败
															</c:when>
															<c:when test="${payment.status == 'cancel'}">
																未支付
															</c:when>
															<c:when test="${payment.status == 'error'}">
																处理异常
															</c:when>
															<c:when test="${payment.status == 'invalid'}">
																非法参数
															</c:when>
															<c:when test="${payment.status == 'progress'}">
																已付款至担保方
															</c:when>
															<c:when test="${payment.status == 'timeout'}">
																超时
															</c:when>
															<c:when test="${payment.status == 'ready'}">
																准备中
															</c:when>
															<c:otherwise>
																数据错误
															</c:otherwise>
														</c:choose>
										              </td>
										            </tr>
						                        </tbody>
					          				</table>
						        		</div>
						      		</div>
					      		</td>
					    		<td  style="vertical-align:top" >
						    		<div class="tableform">
						        		<h4>退款单据列表</h4>
						        		<div class="division">
						          			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist">
						           				<col class="Coldate"></col>
						          				<col class="Colamount"></col>     
									            <thead>
									              <tr>
									                <th>单据日期</th>
									                <th>退款金额</th>
									                <th>退款方式</th>
									                <th>状态</th>
									              </tr>
									            </thead>
									            <tbody>
									            	<tr>
										              <td>${refund.tBegin}</td>
										              <td class="Colamount">${refund.money}</td>
										              <td>${refund.refundAppId}</td>
										              <td>
										              	<c:choose>
															<c:when test="${refund.status == 'succ'}">
																退款成功
															</c:when>
															<c:when test="${parefundyment.status == 'failed'}">
																退款失败
															</c:when>
															<c:when test="${refund.status == 'cancel'}">
																未支付
															</c:when>
															<c:when test="${refund.status == 'error'}">
																处理异常
															</c:when>
															<c:when test="${refund.status == 'invalid'}">
																非法参数
															</c:when>
															<c:when test="${refund.status == 'progress'}">
																已付款至担保方
															</c:when>
															<c:when test="${refund.status == 'timeout'}">
																超时
															</c:when>
															<c:when test="${refund.status == 'ready'}">
																准备中
															</c:when>
															<c:otherwise>
																数据错误
															</c:otherwise>
														</c:choose>
										              </td>
										            </tr>
						                        </tbody>
						          			</table>
					        			</div>
						      		</div>
					     		 </td>
				  			</tr>
						</table>
					</div>
				</div>
				<div title="优惠方案">
					<div id="order-detail-coupon-info" style="padding: 0px 20px;">
						<h3>优惠方案</h3>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist">
						    <col class="Coldetails"></col>
						    <col class="Colamount"></col>
						      <thead>
						        <tr>
						          <th>优惠方案</th>
						          <th>优惠金额</th>
						        </tr>
						      </thead>
						      <tbody>
						      	<c:forEach items="${pmt}" var="item">
					           		<tr>
							          <td width="19%">${item.pmtDescribe}</td>
							          <td class="textleft" width="30%">${item.pmtAmount}</td>
			        				</tr>
					            </c:forEach>
				              </tbody>
					    </table>
					</div>
				</div>
				<div title="订单日记">
					<div id="order-detail-log-info" style="padding: 0px 20px;">
						<h3>订单日记</h3>
						<div class="tableform">
						    <div class="division">
						    <div  class="table-grid">
						      <table width="100%" border="0" cellspacing="0" cellpadding="0" class="gridlist">
						        <thead>
							        <tr>
							          <th>序号</th>
							          <th>时间</th>
							          <th>操作人</th>
							          <th>行为</th>
							          <th>结果</th>
							          <th>备注</th>
							        </tr>
							      </thead>
							      <tbody>
							      	<c:forEach items="${log}" var="item" varStatus="num">
										<tr>
											<td>${num.index+1}</td>
											<td>${item.alttime}</td>
											<td>${item.opName}</td>
											<td>${item.behavior}</td>
											<td>${item.result}</td>
											<td>
												<div style="position: relative;">${item.addon}</div>
											</td>
										</tr>
									</c:forEach>
					              </tbody>
						      </table>
						    </div>
						   </div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>