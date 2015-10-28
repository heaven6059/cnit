<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/biz/order/orderDetail.js?v=${versionVal}"></script>
<%-- <link type="text/css"	href="${path}/resources/styles/order/framework.css" rel="stylesheet" />
<link type="text/css"	href="${path}/resources/styles/order/style.css" rel="stylesheet" /> --%>
</head>
<body>
	<div class="easyui-layout"  style="min-height: 2000px;" >
		<div region="center" border="true" fit="true" style="min-height: 20px;">
			<div id="tab-cate-add" class="easyui-tabs">
				<div title="基本信息">
					<div id="order-detail-base-info" style="padding: 0px 20px;">
						<h3>基本信息</h3>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
						    <tr>
						      	<td style="vertical-align:top">
						        	<div></div>
							        <div class="tableform">
								        <table border="0" cellpadding="0" style="margin-top:10px" cellspacing="0" class="orderdetails_basic">
								  			<tr>
								    			<td style=" padding:0;" >
													<h5 align="center" style=" margin-top:10px;">商品价格</h5>
								    				<table class="multi-cols" border="0" cellpadding="0" cellspacing="0" >
								      					<tr><th >商品总额：</th><td>${detail.costItem}</td></tr>
								            			<tr><th >订单总额：</th><td><span class="price0">${detail.finalAmount}</span></td></tr>
								      					<tr><th >已支付金额：</th><td>${detail.costPayment}</td></tr>
								          			</table>
								    			</td>
								    			<td style=" padding:0" >
													<h5 align="center" style=" margin-top:10px">订单其他信息</h5>
								    				<table class="multi-cols" border="0" cellpadding="0" cellspacing="0" >
								      					<tr><th >商品重量：</th><td>${detail.weight}g</td></tr>
								      					<tr><th >支付方式：</th><td>${detail.payment}</td></tr>
								            			<tr><th >可得积分：</th><td>${detail.scoreG}</td></tr>
									      			</table>
								    			</td>
								    			<td style=" padding:0" >
								    				<h5 align="center" style=" margin-top:10px">购买人信息</h5>
								    				<table class="multi-cols" border="0" cellpadding="0" cellspacing="0" >
								            			<tr><th >用户名：</th><td>${detail.loginName}</td></tr>
								      					<tr><th >姓名：</th><td>${detail.memeberName}</td></tr>
								      					<tr><th >电话：</th><td>${detail.memeberPhone}</td></tr>
								      					<tr><th >地区：</th><td >${detail.area}</td></tr>
								      					<tr><th >Email：</th><td>${detail.email}</td></tr>
									      			</table>
								    			</td>
								    			<!-- <td style=" padding:0;" >
								            		<h5 align="center" style=" margin-top:9px;*margin-top:10px;_margin-top:9px;line-height:19px">收货人信息        <button class="btn" style="margin-top:-8px" id="order_receiver_copy" receiver_copy_status="Y" info="安徽/六安市/金安区,安徽六安市金安区tat435465435,AAAAAAA,13269500928,123456"><span><span>复制收货人信息</span></span></button>&nbsp;     <span id="dom_el_5c24a70"><img src="/app/desktop/statics/bundle/tips_help.gif" /></span>
									        			<div id="tip_Xtip">
									      					<div><i class="arr"></i><i class="arr2"></i></div>
									    				</div>
								    				</h5>
								        			<table class="multi-cols" border="0" cellpadding="0" cellspacing="0" >
								                    	<tr><th >发货日期：</th><td></td></tr>
								          				<tr><th >姓名：</th><td>AAAAAAA</td></tr>
								          				<tr><th >电话：</th><td></td></tr>
								          				<tr><th >手机：</th><td>13269500928</td></tr>
								          				<tr><th >地区：</th><td>安徽/六安市/金安区</td></tr>
								          				<tr><th >地址：</th><td style="white-space:normal; line-height:18px;">安徽六安市金安区tat435465435</td></tr>
								          				<tr><th >邮编：</th><td>123456</td></tr>
								                  	</table>
								          		</td> -->
								  			</tr>
										</table>
									</div>
								</td>
					      	</tr>
						    <tr>
						      	<td>
						      		<div class="tableform">
						      			<h3>商品信息</h3>
						      			<table cellpadding="0" class="gridlist" cellspacing="0" width="100%" border="0">
							  				<col style="width:19.5%"/>
											<col style="width:45%"/>
											<col style="width:10%"/>
											<col style="width:10%"/>
											<col style="width:10%"/>
											<col style="width:10%"/>
						      				<thead>
						      					<tr><th>货品编号</th><th>货品名称</th><th>已发货量</th><th>单价</th><th>合计金额</th><th>购买数量</th></tr>
						      				</thead>
						      				<tbody>
						      					<c:forEach items="${orderItem}" var="item">
									           		<tr>
											          <td width="19%">${item.productId}</td>
											          <td class="textleft" width="30%">${item.name}</td>
											          <td class="Colamount">${item.sendnum}</td>
											          <td width="12%" >${item.price}</td>
											          <td class="Colamount" width="15%">${item.amount}</td>
											          <td class="Colamount" width="15%">${item.nums}</td>
							        				</tr>
									            </c:forEach>
						      				</tbody>
						      			</table>
							        </div>
						      	</td>
						      </tr>
						      <tr>
						      	<td>
							    <div class="tableform">
								      <table width="100%" border="0" cellspacing="0" cellpadding="0">
								        <tbody>
								       		<tr><td style="text-align:left;"><strong>会员备注：</strong>${detail.remark}</td></tr>
								        	<tr><td style="text-align:left;"><div style="float:left"><strong>订单备注：</div></strong><div style="float:left">${detail.markText}</div></td></tr>
								         	<tr><td style="text-align:left;"><strong>订单附言：</strong>${detail.memo}</td></tr>
								        </tbody>
								      </table>
								  </div>
						      	</td>
						      </tr>
					    </table>
					</div>
				</div>
				<div title="商品">
					<div id="order-detail-good-info" style="padding: 0px 20px;">
						<h3>商品</h3>
						<div class="tableform">
			      			<table cellpadding="0" class="gridlist" cellspacing="0" width="100%" border="0">
				  				<col style="width:19.5%"/>
								<col style="width:45%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
								<col style="width:10%"/>
			      				<thead>
			      					<tr><th>货品编号</th><th>货品名称</th><th>已发货量</th><th>单价</th><th>合计金额</th><th>购买数量</th></tr>
			      				</thead>
			      				<tbody>
									<c:forEach items="${orderItem}" var="item">
						           		<tr>
								          <td width="19%">${item.productId}</td>
								          <td class="textleft" width="30%">${item.name}</td>
								          <td class="Colamount">${item.sendnum}</td>
								          <td width="12%" >${item.price}</td>
								          <td class="Colamount" width="15%">${item.amount}</td>
								          <td class="Colamount" width="15%">${item.nums}</td>
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
				
				<div title="订单备注">
					<div id="order-detail-remark-info" style="padding: 0px 20px;">
						<h3>订单备注</h3>
						<div class="tableform" id="Order_Form_Mark">
						  <form id="form-order-add" class="easyui-form">
							  <div class="division">
								  <div class="mark_area"></div>
								  <input type="hidden" name='orderId' id="order_id" value="${orderId}">
								  <table width="100%" border="0" cellpadding="0" cellspacing="0" id="b2c-order-mark-table">
								  <!--  <tr>
								   <th align='right'>标记：</th>
								   <td align='left'>
								      <label><input type='radio' name='markType' value='b1' id='b1'><img src='/app/b2c/statics/remark_icons/b1.gif' width='20' height='20'></label>
									   <label><input type='radio' name='markType' value='b2' id='b2'><img src='/app/b2c/statics/remark_icons/b2.gif' width='20' height='20'></label>
									  <label><input type='radio' name='markType' value='b3' id='b3'><img src='/app/b2c/statics/remark_icons/b3.gif' width='20' height='20'></label>
									  <label><input type='radio' name='markType' value='b4' id='b4'><img src='/app/b2c/statics/remark_icons/b4.gif' width='20' height='20'></label>
									  <label><input type='radio' name='markType' value='b5' id='b5'><img src='/app/b2c/statics/remark_icons/b5.gif' width='20' height='20'></label>
									  <label><input type='radio' name='markType' value='b0' id='b0'><img src='/app/b2c/statics/remark_icons/b0.gif' width='20' height='20'></label>
								   </td>
								   </tr> -->
								    <tr>
								      <th>订单备注：</th>
								      <td><textarea id="markText" name="markText" rows="6" style="width:80%"></textarea></td>
								    </tr>
								    </table>
								    <!-- <div class="table-action">
							        <button id="btn_do_submit" class="btn" type="button"><span><span>保存</span></span></button>    </div>   -->
							  </div>
						  </form>
						  
						  	<div  border="false" style="text-align: center; height: 10px; line-height: 10px; margin-bottom: 5px;">
								<a class="easyui-linkbutton" id="add-order-btn" icon="icon-save" onclick="saveOrderRemark()">保存</a> 
							</div>
						</div>
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