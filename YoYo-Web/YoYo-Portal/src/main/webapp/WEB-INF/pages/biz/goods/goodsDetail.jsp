<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${goods.name}</title>
<script type="text/javascript">
var loginstatus="${sessionScope.loginStatus}";
var interval = 1000;
var countDown;
$(function (){
	countDown = window.setInterval(function(){
		var startTime='<fmt:formatDate value="${scoreActivity.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>';
		var endTime='<fmt:formatDate value="${scoreActivity.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>';
		startTime = startTime.replace(/-/g,"/");
		endTime=endTime.replace(/-/g,"/");
		startTime = new Date(startTime);	
		endTime=new Date(endTime);
		if(compareDate(startTime,endTime)){//超时
			showCountDown(endTime);
		}else{
			window.clearInterval(countDown);
		}
	}, interval);
});

function showCountDown(endDate){
	var now = new Date(); 
	var leftTime=endDate.getTime()-now.getTime(); 
	var leftsecond = parseInt(leftTime/1000); 
	
	var day1=Math.floor(leftsecond/(60*60*24)); 
	var hour=Math.floor((leftsecond-day1*24*60*60)/3600); 
	var minute=Math.floor((leftsecond-day1*24*60*60-hour*3600)/60); 
	var second=Math.floor(leftsecond-day1*24*60*60-hour*3600-minute*60);
	if(day1==0&&hour==0&&minute==0&&second==0){
		$("#form_1").submit();
	}
	$(".day").html(pad(day1));	
	$(".hour").html(pad(hour,2));
	$(".minute").html(pad(minute,2));
	$(".second").html(pad(second,2));
}
function pad(num, n) {
    var len = num.toString().length;
    while(len < n) {
        num = "0" + num;
        len++;
    }
    return num;
}
</script>
<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<link type="text/css" href="${path}/resources/styles/base.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/details.css?v=${versionVal}" rel="stylesheet" />
<script src="${path}/resources/scripts/biz/goods/goodsDetail.js?v=${versionVal}"></script>
<script src="${path}/resources/scripts/biz/goods/flashBuyDetailPage_v7.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/index.css?v=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/goods.css?v=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/parameter.css?v=${versionVal}">
<style type="text/css">
.myschedule-main{width: 660px;margin: 0px auto;overflow: hidden;position: relative;padding: 10px 0px;}
.myschedule-main .myschedule-title{margin: 0 auto;text-align: center;font-weight: 700;font-size: 14px;padding-bottom: 10px}
.myschedule-tb {border: 1px solid #6eb2c1;border-radius: 3px;table-layout: fixed;width: 100%;}
.myschedule-tb th {background-color: #dcf0f5;border-bottom: 1px solid #bddfe8;font-weight: normal;height: 40px;width:60px; padding: 5px 0;text-align: center;}
.myschedule-tb th.first, .myschedule-tb th.last {width: 100px;}
.myschedule-tb td.first {vertical-align: middle;}
.myschedule-tb td.bg {background-color: #e9f8fc;}
.myschedule-tb td {border: 1px dashed #bddfe8;height: 30px;text-align: center;vertical-align: top;width:60px}
.disable-choose{background-color: #f6f6f6}
.icon {background: rgba(0, 0, 0, 0) url("http://www.yihu.com/css/../images/icons.png") no-repeat scroll 0 0;display: inline-block;overflow: hidden;vertical-align: text-top;}
.icon-prev-gray {background-position: -200px -280px;cursor: pointer;height: 24px;width: 24px;}
.icon-prev-gray.disabled, .icon-next-gray.disabled {cursor: default;opacity: 0.5;}
.icon-next-gray {background-position: -230px -280px;cursor: pointer;height: 24px;width: 24px;}
.zoomspan{display:none;}
.g_s_name {width: 80px;}
.g_s_data {width: 480px;}
.g_cs_t ul li {padding-right: 0px;}
.goodsIntro *{max-width: 100%;   /* 设置最大宽度 */}
.buyNow {background: #fff none repeat scroll 0 0;border: 2px solid #e32a2f;color: #e32a2f;height: 40px;width: 218px;font-size: 20px;cursor: pointer;}
.buyNow:hover {background: #e32a2f none repeat scroll 0 0;color: #fff;cursor: pointer;}
#addCart {cursor: pointer;font-size: 20px;height: 40px;margin: 0;width: 220px;line-height: 40px;background: #e32a2f none repeat scroll 0 0;color: #ffffff;border: none;}
#addCart:hover {background: #c30000 none repeat scroll 0 0;}
</style>
</head>
<body>
	<c:if test="${fn:length(dateList)>0}">
	<div class="myschedule-main" style="display: none;">
		<div class="myschedule-title">请选择到店消费时间</div>
		<table class="myschedule-tb">
			<tbody>
				<tr>
					<th class="first">
						<input type="hidden" value="2" id="tableDoctorYYGHStr" name="tableDoctorYYGHStr">
						<i class="icon icon-prev-gray"></i>
					</th>
					<c:forEach var="dateList" items="${dateList[0].goodsAppointmentDTOs}" varStatus="status">
					<th <c:if test="${status.index>6}">style="display: none"</c:if>>
					<fmt:formatDate value="${dateList.appointmentDate}" pattern="MM-dd"/><br>
					<fmt:formatDate value="${dateList.appointmentDate}" pattern="E"/>
					</th>
					</c:forEach>
					<th class="last"><i class="icon icon-next-gray"></i></th>
				</tr>
				<c:forEach var="dateList" items="${dateList}" varStatus="dateStatus">
				<tr>
					<td class="bg first">${dateList.appointmentTime}</td>
					<c:forEach var="appointmentDTOs" items="${dateList.goodsAppointmentDTOs}" varStatus="status">
					<c:if test="${appointmentDTOs.enabled}">
					<td <c:if test="${status.index%2==1}">class="bg"</c:if>
						<c:if test="${status.index>6}">style="display: none"</c:if>>
						<div style="height: 30px;line-height: 30px;">
						<c:if test="${dateStatus.index==0&&appointmentDTOs.timeNum1-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==1&&appointmentDTOs.timeNum2-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==2&&appointmentDTOs.timeNum3-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==3&&appointmentDTOs.timeNum4-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==4&&appointmentDTOs.timeNum5-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==5&&appointmentDTOs.timeNum6-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==6&&appointmentDTOs.timeNum7-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						<c:if test="${dateStatus.index==7&&appointmentDTOs.timeNum8-appointmentDTOs.appointmentNum>0}"><a href="javascript:void(0)" data-app="<fmt:formatDate value="${appointmentDTOs.appointmentDate}" pattern="yyyy-MM-dd"/>|${dateList.appointmentTime},${dateStatus.index+1}">预约</a></c:if>
						</div>
					</td>
					</c:if>
					<c:if test="${!appointmentDTOs.enabled}">
					<td class="disable-choose" <c:if test="${status.index>6}">style="display: none"</c:if>></td>
					</c:if>
					</c:forEach>			
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	</c:if>
<div class="gray"></div>
<div class="car_add">
    <a class="close" href="javascript:;">X</a>
</div>
	<c:choose>
		<c:when test="${goods.goodsId!=null}">
	<!-- 商品信息部分 -->
	<div class="goods">
		<div class="goods_in">
			<input type="hidden" id="appointment" value="" />
			<input type="hidden" id="goods_id" value="${goods.goodsId}" />
			<input type="hidden" id="car_id" value="${goods.carId}" />
			<input type="hidden" id="product_id" value="${productId}" />
			<input type="hidden" id="marketable" value="${goods.marketable}" />
			<input type="hidden" id="brand_id" value="${goods.brandId}" />
	        <!--商品图片展示-->
	    	<div class="goods_l fl">
	        	<div class="goods_show">
	            	<div class="goods_pic" style="padding:0px;height:348px;position: relative;">
	                	<img src="${goods.midPic}" alt="${goods.midPic}" width="335" height="335" class="jqzoom">
	                	<div class="zoomspan" style="display: block; left: 100px; top: 200px;"></div>	                	
	                </div>
	                <div class="goods_min">
	                	<div class="spec-items">
		                	<ul style="position: absolute; width: 310px; height: 54px; top: 0px; left: 0px;">
		                    	<li style="margin-right: 3px;"><img src="${goods.midPic}" alt="${goods.midPic}" width="50" height="50"></li>
		                    </ul>
	                    </div>
	                    <a class="b_left"></a>
	                    <a class="b_right"></a>
	                </div>
	            </div>
	       	    <div class="goods_share">
	          		<ul>
	                	<li>商品编号：${goods.goodsId}</li>
	                    <li class="g_li02"><a href="javascript:;" onclick="addWishList()"><i></i>关注商品</a></li>
	                    <%-- ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ 还没有做 ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ --%>
	                    <!-- <li class="g_li03"><a href="javascript:;"><i></i>分享</a></li> -->
	                    <%-- ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ 还没有做 ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑ --%>
	                    <c:if test="${goods.carId>0}">
	                    	<li class="g_li04"><a href="javascript:addCompare(${goods.goodsId},'<c:out value="${goods.name}" />','<c:out value="${goods.smallPic}"/>','${goods.price}',<c:out value="${carInfo.carId}"/>,<c:out value="${carInfo.deptId}"/>,<c:out value="${carInfo.brandId}"/>);"><i></i>加入对比</a></li>
	                    </c:if>
	                </ul>
	            </div>
	        </div>
	        <!--商品图片展示 end-->
	        
	        <!--参数信息-->
	        <div class="goods_m fl">
	        	<div class="g_name">
	            	<h2 style="width: 560px;height: 60px;overflow: hidden;line-height: 30px;">${goods.name}</h2>
	            	<h3 class="orange" style="width: 560px;height: 20px;line-height:20px;font-size:16px;" title="${goods.brief}">${goods.brief}</h3>
	            </div>
	            <div class="g_j" style="padding:15px 0px;">
	            	<c:choose>
	            	<c:when test="${goods.carId>0}">
	            		<span id="priceName">预付订金：￥</span>
	            		<strong id="price" class="red"><fmt:formatNumber value="${goods.cost}" pattern="#0.00"/></strong>
	            		<span>优优价：￥</span><span id="yoyoPrice"><fmt:formatNumber value="${goods.price}" pattern="#0.00"/></span>
	            		<span>市场价：￥</span><span id="mtkPrice" style="text-decoration: line-through;"><fmt:formatNumber value="${goods.mktPrice}" pattern="#0.00"/></span>
	            		<c:if test="${goods.mktPrice-goods.price>0}">
	            		<span>节省：￥</span><span id="economyPrice"><fmt:formatNumber value="${goods.mktPrice-goods.price}" pattern="#0.00"/></span>
	            		</c:if>
	            	</c:when>
	            	<c:when test="${!empty scoreActivity}">
	            		<span>换购价：</span><strong id="activityPrice" class="red">￥<fmt:formatNumber value="${scoreActivity.lastPrice}" pattern="#0.00"/></strong>
	            		<span>原价：￥</span><span id="price" class="red"><fmt:formatNumber value="${goods.cost}" pattern="#0.00"/></span>
	            		<span>市场价：￥</span><span id="mtkPrice"><fmt:formatNumber value="${goods.mktPrice}" pattern="#0.00"/></span>	            		
	            		<p>换购积分：<span class="red">${scoreActivity.score}</span></p>	            		
	            		<p>	
	            			<input type="hidden" id="personLimit" value="${scoreActivity.personlimit}">	            			
	            			<input type="hidden" id="orderType" value="SCORE_BUY"/>
	            			<span>剩余：</span>
	            			<span class="day">00</span><span>天</span>
				        	<span class="hour">00</span><span>小时</span>
				        	<span class="minute">00</span><span>分</span>
				        	<span class="second">00</span><span>秒</span>				        	
				      	</p>
	            	</c:when>
	            	<c:otherwise>
	            		<span id="priceName">优优价：￥</span>	            		
	            		<strong id="price" class="red"><fmt:formatNumber value="${goods.price}" pattern="#0.00"/></strong>
	            		<span>市场价：￥</span><span id="mtkPrice"><fmt:formatNumber value="${goods.mktPrice}" pattern="#0.00"/></span>
	            		<c:if test="${goods.mktPrice-goods.price>0}">
	            		<span>节省：￥</span><span id="economyPrice"><fmt:formatNumber value="${goods.mktPrice-goods.price}" pattern="#0.00"/></span>
	            		</c:if>
	            	</c:otherwise>
	            	</c:choose>
	            </div>
	            
	            <c:choose>
	            	<c:when test="${goods.marketable=='1'}">
	            		<div class="g_cs" style="height: auto;">
			        		<c:if test="${goods.carId!='0' }">
				            	<div class="g_cs_t" >
				                	<ul class=" clearfix">
				                    	<li class="g_cs01" >
				                        	<span class="fl"></span>
				                            <div class="fl" >
				                                <p style="width:100px;height:18px;overflow:hidden;">厂商：<i>--</i></p>
				                                <p style="width:100px;height:18px;overflow:hidden;">品牌：<i><c:choose><c:when test="${goods.brandName!=null&&goods.brandName!=''}">${goods.brandName}</c:when><c:otherwise>--</c:otherwise></c:choose></i></p>
				                            </div>
				                        </li>
				                    	<li class="g_cs02" >
				                        	<span class="fl"></span>
				                            <div class="fl">
				                                <p style="width:140px;height:18px;overflow:hidden;">质保：<i>--</i></p>
				                                <p style="width:140px;height:18px;overflow:hidden;">保养间隔：<i>--</i></p>
				                            </div>
				                        </li>
				                    	<li class="g_cs03" >
				                        	<span class="fl"></span>
				                            <div class="fl">
				                                <p style="width:145px;height:18px;overflow:hidden;">油耗：<i>--</i></p>
				                                <p style="width:145px;height:18px;overflow:hidden;">燃油及标号：<i>--</i></p>
				                            </div>
				                        </li>
				                    </ul>
			                
				                </div>
			                </c:if>
			                <c:if test="${goods.carId>0}">
			                <div style="margin-top:10px;height:auto;">
								<div class="g_s1 clearfix">
									<div title="车款" class="g_s_name fl">选择车款：</div>
									<div class="g_s_data fl">
										<ul>
											<c:forEach var="otherCarInfo" items="${otherCarInfos}">
											<li <c:if test="${otherCarInfo.goodsId==goods.goodsId}">class="g_cur"</c:if>>										
												<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${otherCarInfo.goodsId}">${otherCarInfo.name}</a><i></i>
											</li>
											</c:forEach>
										</ul>
									</div>
								</div>
							</div>
							</c:if>
			            	<div class="g_cs_b" style="margin-top:10px;height:auto;">
							</div>
			            </div>
			            <div class="g_num">
			            	<div class="g_num_l fl">数  量：</div>
			            	<div class="g_num_m fl">
			                	<div class="g_num_in" style="margin-left: 0px;">
			                    	<button class="g_left"></button>
			                        <div class="ipt" style="left:26px;">
			                        	<input type="text" id="quantity" value="1" back="1">
			                        </div>
			                        <button class="g_right"></button>
			                    </div>
			                </div>
			            	<div class="g_num_r fl" style="padding-left: 10px;">库存：<span></span></div>
			            </div>
			            <c:if test="${goods.carId!='0'}">
			            <div class="g_shop clearfix">
		                	<span class="shop_xzdj fl">
			                	<a class="xzdj" href="${path}/goodsManager/enquiry?type=1&goodsId=${goods.goodsId}" target="_blank">询最低价</a>
			                </span>
			                <span class="shop_yysj fl">
			                	<a class="yysj" href="${path}/goodsManager/enquiry?type=2&goodsId=${goods.goodsId}" target="_blank">预约试驾</a>
			                </span>
		                </div>
		                </c:if>
			            <div class="g_shop clearfix">
			            	<c:choose>
			            		<c:when test="${!empty scoreActivity}">
									 <input type="button" value="立即购买" id="activityBuyNow" class="buyNow">						                
			            		</c:when>
			            		<c:otherwise>
				                	<input type="button" value="立即购买" id="buyNow" class="buyNow" />
				                	<input type="submit" value="加入购物车" id="addCart">
			            		</c:otherwise>
			            	</c:choose>				            	
			            </div>
	            	</c:when>
	            	<c:otherwise>
	            		<div>
	            			<table style="width:100%;height:250px;">
								<tr>
									<td style="font-size: 25px;font-weight: bold;vertical-align: middle;padding-left: 50px;color: red;">该商品已下架了</td>
								</tr>
							</table>
	            		</div>
	            	</c:otherwise>
	            </c:choose>
	            
	        	
	        </div>
	        <!--参数信息 end-->
	        
	        <!--看了又看-->
	        <c:if test="${fn:length(realtedGoods)>0}">
	        <div class="goods_r fr">
	        	<div class="g_look">
	            	<b></b>
	            	<p>相关商品</p>
	                <b></b>
	            </div>
	        	<div class="g_pic">
	            	<ul>
	            		<c:forEach var="realtedGood" items="${realtedGoods}" begin="0" end="3">
						<li style="text-align: center;">
							<div style="height: 120px; overflow: hidden;">
								<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${realtedGood.goodsId}" target="_blank">
									<img width="80px" height="80px" src="${imagePath}/${realtedGood.smallPic}" />
								</a>
								<br>
								<a href="${portalPath}/goodsManager/goodsIndex?goodsId=${realtedGood.goodsId}">${realtedGood.name}</a>
								<br>
							</div>
						</li>
						</c:forEach>
					</ul>
	            </div>
	        </div>
	        </c:if>
	        <!--看了又看 end-->
	        <div class="clearfix"></div>
	    </div>
	    <div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
	
	<!-- 优惠套餐+产品描述 -->
	<div class="g_main">
	<!--优惠套装-->
	<c:if test="${fn:length(accessoryGoods)>0&&fn:length(accessoryGoods[0].products)>0}">
    <div class="g_f">
        <div class="stor" style="padding-right:0px;">
            <h2>优惠套装</h2>
        </div>
        <div class="g_sale">
        	<div class="g_nav" style="overflow: hidden;">
        	 	<a class="b_left"></a>
                <a class="b_right"></a>
                <div class="g_nav_items">
	            	<ul style="position: absolute; width: ${fn:length(accessoryGoods)*108}px; height: 40px; top: 0px; left: 0px;">
	            		<c:forEach var="accessoryGood" items="${accessoryGoods}" varStatus="status">
	                	<li>优惠套装${status.index+1}</li>
	                	</c:forEach>
	                </ul>
                </div>
            </div>
            <div class="g_show clearfix">                
                <c:forEach items="${accessoryGoods}" var="accessoryGood" varStatus="status">
	                <div class="g_show_l fl"<c:if test="${status.index!=0}">style="display:none;"</c:if>>
						<div class="master">
							<s></s>
							<div class="p-img">
								<img width="100" height="100" src="${imagePath}${goods.smallPic}">
							</div>
							<div class="p-name">
							<a>${goods.name}</a>
							</div>
						</div>

						<div class="suits" data-goods="${product.goodsId}">
							<ul class="lh" style="width: ${fn:length(accessoryGood.products)*150}px">
                				<c:forEach items="${accessoryGood.products}" var="product" varStatus="productStatus">
								<li class="fore1">
								    <c:if test="${!productStatus.last}"><s></s></c:if>
									<div class="p-img">
										<a target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${product.goodsId}">
											<img width="100" height="100" src="${imagePath}${product.picturePath}" />
										</a>
									</div>
									<div class="p-name">
										<a title="${product.name}" target="_blank" href="${portalPath}/goodsManager/goodsIndex?goodsId=${product.goodsId}">${product.name}</a>
									</div>
								</li>
	                			</c:forEach>
							</ul>
                 		</div>

						<div class="infos">
							<s></s>
							<div class="p-name">
								<a>${accessoryGood.accGroupName}</a>
							</div>
							<div class="p-suit">
								套&nbsp;装&nbsp;价：<strong class="J_res_sp">￥${accessoryGood.discountPrice}</strong>
							</div>
							<div class="p-price">
								YOYO价：
								<del class="J_res_jp">￥ ${accessoryGood.sumPrice}</del>
							</div>
							<div class="p-saving">
								立即节省：<span class="J_res_mp">￥${accessoryGood.sumPrice-accessoryGood.discountPrice}</span>
							</div>
							<div class="btns">
								<a href="javascript:void(0);" class="btn-buy">购买套装</a>
							</div>
							<input type="hidden" id="accessorySumPrice" name="accessorySumPrice" value="${accessoryGood.sumPrice}" />
		                	<input type="hidden" id="discountPrice" name="discountPrice" value="${accessoryGood.discountPrice}" />
		                	<input type="hidden" id="accessoryId" name="accessoryId" value="${accessoryGood.id}" />
		                	<input type="hidden" id="discType" name="discType" value="${accessoryGood.discType}" />
		                	<input type="hidden" id="credit" name="credit" value="${accessoryGood.credit}" />
						</div>
	                </div>
                </c:forEach>
            </div>
        </div>
    </div>
    </c:if>
	<!--优惠套装 end-->
    
    <!--产品描述-->
    
    <div class="g_f clearfix" >
    
        <div class="g_cp fl">
        	<div class="stor" style="background-color: white;">
                <h2 id="dpt" class="g_select">产品描述</h2>
                <c:if test="${goods.carId>0}">
                <h2 id="par" class="" >规格参数</h2>
                </c:if>
                <h2 class="" id="dis">评论(0)</h2>
                <h2 class="" id="cst">商品咨询(0)</h2>
            </div>
            <div id="dpt_select" class="g_cont clearfix g_open">
            	<div style="display:none;">
                    <div class="table clearfix">
                    	<ul class="goods_attribute" style="padding-top:20px;padding-bottom:20px;display:table;">
	                	</ul>
                    </div>
                </div>
            
            	<div style="display:none;">
                    <div class="table clearfix">
                        <dl class="dl01">
                            <dt class="g_dt">1.6L排量</dt>
                        </dl>
                        <dl class="dl02">
                            <dt>厂商指导价</dt>
                        </dl>
                        <dl class="dl03">
                            <dt style="color: #8dc420;">优优价</dt>
                        </dl>
                        <dl class="dl04">
                            <dt>经销商报价</dt>
                        </dl>
                        <dl class="dl05">
                            <dt>操作</dt>
                        </dl>
                    </div>
                </div>
                <div class="goodsIntro">
                	<c:if test="${goods.intro!=null && goods.intro!=''}">
                		<div class="table clearfix" style="padding: 20px;width:auto;margin-top:20px;">
		                	${goods.intro}
		                </div>
                	</c:if>
                </div>
            </div>
            <!--参数配置-->
            <div id="par_select" class="g_cont clearfix">            	
            	<c:if test="${goods.carId>0}">
                <div class="row clearfix fl" id="content" style=" width: 495px; ">
                    <div id="navScrollLeft" class="followcon fl" style=" width: 127px; display: none;">
                        <ul class="folul">
                        </ul>
                    </div> 
                    <div class="column fr" style=" width: 365px; ">
                        <div class="title">
                            <div class="title-content" style=" padding-top: 0; border-top: 0; ">
                          	</div>
                        </div>
                    </div>
                </div>
                </c:if>
            </div>
            
            <!--评论-->
            <div id="dis_select" class="g_cont">
            	<div class="g_t">
                	<div class="pl">
                    	<h2>商品评价</h2>
                    </div>
                    <div class="pf clearfix">
                    	<div class="pf_1 fl">
                        	<strong class="red">0<span>%</span></strong>
                            <br>
                            <span>好评度</span>
                        </div>
                        <div class="pf_2 fl" style="width:182px;">
                        	<div class="pf_ul1 fl">
                            	<ul>
                                	<li>好评<i>(0%)</i></li>
                                	<li>中评<i>(0%)</i></li>
                                	<li>差评<i>(0%)</i></li>
                                </ul>
                            </div>
                        	<div class="pf_ul2 fl" style="margin-left:10px;">
                            	<ul>
                                	<li><i class="i01" style="width:0px;"></i></li>
                                	<li style="margin-top: 5px;"><i class="i02" style="width:0px;"></i></li>
                                	<li style="margin-top: 5px;"><i class="i03" style="width:0px;"></i></li>
                                </ul>
                            </div>
                        </div>
                        <div class="pf_3 fl" style="overflow:hidden;display:none;">
                        	<p>买家印象</p>
                            <ul>
                            </ul>
                        </div>
                        <div class="pf_4 fr" style="display:none;">
                        	<p>你可对已购商品进行评价</p>
                            <button>发表拿优豆</button>
                            <p class="red">前五名双倍优豆<span>(规则)</span></p>
                        </div>
                    </div>
                </div>
                <div class="com-table-header" style="height:auto;">            
                	<span class="item column1" style="padding:0px;border:0px;width:610px;">评价心得</span>            
                	<span class="item column2" style="padding:0px;border:0px;width:140px;">顾客满意度</span>            
                	<!-- <span class="item column3">购买信息</span> -->            
                	<span class="item column5" style="padding:0px;border:0px;width:158px;">评论者</span>        
                </div>
                <div class="g_b">
                	<ul>
                    	<li class="clearfix" style="display:none;" >
                        	<div class="g_b_l fl">
                            	<p>因厂家更改产品包装、产地或者更换随机附件等没有任何提前通知，且每位咨询者购买情况、提问时间等不同，为此以下回复仅对提问者3天内有效，其他网友仅供参考！若由此给您带来不便请多多谅解，谢谢！</p>
                            	
                            	
                            	<div class="p-photos-viewer" style="display:none;">            
                            		<div class="p-photos-wrap">                
                            			<i></i>                
                            			<img class="J-big-img" src="" alt="" style="transform: rotate(0deg);" width="500px" height="500px;" onload="autosize(this,500,500)">                
                            			<div class="cursor-left J-photo-prev"></div>                
                            			<div class="cursor-small J-hide-big-show"></div>                
                            			<div class="cursor-right J-photo-next"></div>            
                            		</div>        
                            	</div>
                            	
                            	
                            	
                            	<div class="tp_div clearfix">
                            		<span class="J-thumb-prev i-prev-btn i-prev-disable" style="float:left;position:relative;top:30px;" onclick="prevCommentPic(this)"></span>
                            		<table style="float:left;">
                            			<tr>
                            			</tr>
                            		</table>
                            		<span class="J-thumb-next i-next-btn i-next-disable" style="float:left;position:relative;top:30px;" onclick="nextCommentPic(this)"></span><!-- i-next-disable -->
                                    <span style="display:none;">共0张图 <a onclick="showHideBig(this)"> 查看晒单&gt;</a></span><!-- href="javascript:showHideBig(this)"  -->
                                    <em style="overflow: hidden;"></em> 
                                    <!-- <span class="star sa4" style="left: 600px;"></span> -->
                                </div>
                                
                                <div class="tp_f" style="height: auto;">
                                	<div class="tp_f_t">
                                    	<a href="javascript:;" onclick="toReply(this)">回复(0)</a>
                                    	<a href="javascript:;">赞(0)</a>
                                    	
                                    	<div class="reply-textarea J-reply-con" style="display: none;">                        
                                    		<div class="reply-arrow"><b class="layer1"></b><b class="layer2"></b></div>                        
                                    		<div class="inner">                            
                                    			<textarea class="reply-input" name="" id="" placeholder=""></textarea>                            
                                    			<div class="btnbox">
                                    				<button type="button" data-nick="ftereffect" data-guid="" data-replyid="" class="reply-submit J-submit-reply J-submit-reply-lz" onclick="submitReply(this)">提交回复</button>
                                    			</div>                        
                                    		</div>                    
                                    	</div>
                                    </div>
                                    
                                    <div class="comment-replylist" style="margin-top:25px;">  
		                               	<div class="view-all-reply" style="margin: 10px;"><a href="javascript:;" class="view-link">查看全部回复&gt;&gt;</a></div>                
		                            </div>
                                </div>
                            </div>
                            <div class="g_b_r fr">
                            	<ul>
                                	<li></li>
                                    <li></li><br/>
                                    <li></li>
                                </ul>
                            </div>
                        </li>
                    	
                    </ul>
                    
                    
                    <div class="com-table-footer">            
                    	<div class="ui-page-wrap clearfix">                
                    		<div class="ui-page">
                    			
                    		</div>            
                    	</div>        
                    </div>
                </div>
            </div>
            
            <!--商品咨询-->
            <div id="cst_select" class="g_cont">
            	<div class="ts">
                	<p><strong>温馨提示:</strong>因厂家更改产品包装、产地或者更换随机附件等没有任何提前通知，且每位咨询者购买情况、提问时间等不同，为此以下回复仅对提问者3天内有效，其他网友仅供参考！若由此给您带来不便请多多谅解，谢谢！</p>	
                </div>
            	<div class="zx">
                	<div class="item" style="display:none;">
                    	<div class="g_user">
                        	<span>网　　友:</span>
                        	<span></span>
                        	<span class="ml56"></span>
                        </div>
                        <dl>
                        	<dt>咨询内容：</dt>
                            <dd class="ml56"><a href="javascript:;"></a></dd>
                        </dl>
                        <dl>
                        	<dt class="it_color">YOYO回复：</dt>
                            <dd class="ml56">
                                <a class="it_color" href="javascript:;"></a>
                                <span class="ml400 fr"></span>
                            </dd>
                        </dl>
                    </div>
                	
                </div>
                <div class="it_f clearfix">
                	<div class="fl">
                    	<dl>
                        	<dt>购买之前，如有问题，请</dt>
                            <dd><a class="dd_color" href="javascript:toConsult(${goods.goodsId})">[发表咨询]</a></dd>
                        </dl>
                    </div>
                    <div class="fr">
                    	<dl>
                        	<dt>共0条</dt>
                            <dd><a class="dd_color" href="javascript:toConsult(${goods.goodsId})" >浏览所有咨询信息&gt;&gt;</a></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        
         <!-- 店铺start -->
		<div id="extInfo" class="extInfo fr" style="width: 208px;padding:0px;margin: 0px;border:0px;">
		
			<c:if test="${!empty hasReport&&(empty sessionScope.accountType||sessionScope.accountType==0||sessionScope.accountType==100)}">
			<div style="width:168px;height:auto;border:1px solid #CBCBCB;padding:12px 15px;margin:5px;">
					<div class="brand-logo">
						<a href="javascript:;" class="btn-gray btn-shop-access J-enter-shop" onclick="openReport();">举报该商品</a>	
					</div>
            </div>
			</c:if>
		
			<div style="width:168px;height:auto;border:1px solid #CBCBCB;padding:12px 15px;margin:5px;">
				<div class="brand-logo">
					<a target="_blank" href="${portalPath}/shop/index?companyId=${company.companyId}">
						<img title="${company.storeName}" width="166px;" height="60px;" src="${imagePath}/${company.image}" />
					</a>
				</div>
				<div class="seller-infor">
					<a title="${company.storeName}" target="_blank" href="${portalPath}/shop/index?companyId=${company.companyId}" class="name">${company.storeName}</a>				
				</div>
				
				<div class="seller-pop-box">
					<div class="J-pop-score">
						<div class="score-empty"></div>
					</div>
				</div>
				<div class="pop-shop-enter">
					<a class="btn-gray btn-shop-access J-enter-shop" href="${portalPath}/shop/index?companyId=${company.companyId}" target="_blank">进入店铺</a>
					<c:if test="${empty sessionScope.accountType||sessionScope.accountType==0||sessionScope.accountType==100}">
					<a class="btn-gray btn-shop-follower J-follow-shop" href="javascript:;" onclick="focuseShop(${company.companyId});" title="点击关注">关注店铺</a>
					</c:if>
				</div>
            </div>
            
            <!--热销排行榜-->
	        <c:if test="${fn:length(topSales)>0}">
	        <div class="charts" style="margin:5px;">
	        	<p>热销排行榜</p>
	            <ul class="clearfix">
	            	<c:forEach items="${topSales}" var="topsales">
	            	<li>
	                	<a class="clearfix" href="${portalPath}/goodsManager/goodsIndex?goodsId=${topsales.goodsId}" target="_blank">
	                        <div style=" margin-top: 4px;margin-right:10px;" class="fl">
	                        	<img src="${imagePath}/${topsales.smallPic}" alt="" style="width:45px;height:45px;">
	                        </div>
	                        <div class="fl" style="width:108px;overflow:hidden;">
	                            <h4 style="white-space: nowrap;">${topsales.name}</h4>
	                            <h3 class="red">￥${topsales.price}</h3>
	                            <h5>已出售${topsales.buyCount}件</h5>
	                        </div>
	                    </a>
	                </li>
	            	</c:forEach>
	            </ul>
	        </div>
	        </c:if>
		</div>
		<!--店 铺END -->
    </div>
    <!--产品描述end-->
</div>
	<!-- 优惠券 -->
	<div class="main" style="display:none;">
    <!--4楼-->
    <div class="f_4" style="display:none;">
        <div class="stor">
            <h2 class="fl">优惠券</h2>
            <a class="fr" href="javascript:;">更多优惠&gt;&gt;</a>
        </div>
        <div class="f_4_in">
            <ul class=" clearfix">
                
            </ul>
        </div>
    </div>
</div>

		<!-- 商品详情部分 -->
		</c:when>
		<c:otherwise><!-- 该商品不存在 -->
		<c:out value="${goods.goodsId}"></c:out>
			<div id="p-box" style="width:1200px; margin: 0 auto;">
				<div class="w">
					<div id="product-intro" class="m-item-grid clearfix">
						<input type="hidden" id="brand_id" value="${brandId}" />
						<!-- 左侧 -->
						<div id="preview" clstag="shangpin|keycount|product|1|mainpicarea">
							<!-- 大图 -->
							<div id="spec-n1" class="jqzoom" onclick="" clstag="shangpin|keycount|product|1|mainpic">
								<img data-img="1" width="350" height="350" src="${src}" jqimg="">
							</div>
							<!-- 小图 -->
							<div id="spec-list" clstag="shangpin|keycount|product|1|lunbotu">
								<div class="spec-items" style="position: absolute; width: 310px; height: 54px; overflow: hidden;">
									<ul class="lh"
										style="position: absolute; width: 372px; height: 54px; top: 0px; left: 0px;">
										<li><img alt="" src="${src}" data-url="" data-img="1" width="50" height="50" class="img-hover"></li>
									</ul>
								</div>
							</div>
						</div>
						<!-- 右侧 -->
						<div class="m-item-inner" clstag="shangpin|keycount|product|1|zhushujuqu" style="width: 790px;">
							<table style="width:100%;height:449px;">
								<tr>
									<td style="font-size: 15px;font-weight: bold;vertical-align: middle;padding-left: 50px;">该商品不存在，非常抱歉！</td>
								</tr>
							</table>
						</div>
						
					</div>
				</div>
			</div>
			<!-- 商品信息部分 -->
		</c:otherwise>
	</c:choose>

	<div id="report" style="display: none; margin: 75px 30px;">
		<form id="reportForm">
		<input type="hidden" name="source" value="buyer" />
		<input type="hidden" name="productId" value="${goods.goodsId}" />
		<input type="hidden" name="storeName" value="${company.storeName}" />
		<input type="hidden" name="storeId" value="${goods.storeId}" />
		<input type="hidden" name="companyId" value="${company.companyId}" />
		<table width="100%" align="center">
			<tr>
				<td width="20%" style=" text-align:right; vertical-align:top; padding-top:8px; padding-right:10px;">联系电话:</td>
				<td ><input name="mobile" id="mobile" maxlength="11" class="x-input" value="" style="width: 200px;height: 20px;border:1px solid #AEAEAE;border-radius:2px;margin-bottom:15px;"/></td>
			</tr>
			<tr>
				<td style=" text-align:right; vertical-align:top; padding-top:8px; padding-right:10px;">举报原因:</td>
				<td>
					<select name="reason" style="width: 212px;height: 34px;border:1px solid #AEAEAE;border-radius:2px; padding:6px 5px; margin-bottom:15px;" class="x-input-select ">
						<option value="false">虚假信息 </option>
						<option value="unconformity">图片不符</option>
					</select>
				</td>
			</tr>
			<tr>
				<td style=" text-align:right; vertical-align:top; padding-top:8px; padding-right:10px; ">留言：</td>
				<td>
                <textarea id="comment" name="memo" value=""  style="border:1px solid #AEAEAE;height: 70px; padding:5px;max-width: 402px;width: 402px; border-radius:2px;" required="true"></textarea>
                </td>
			</tr>
			<tr>
			<td style="text-align: center;" colspan="2">
				<button type="button" onclick="subReport();" style="background: none repeat scroll 0 0 #377bee;
border: medium none;border-radius: 2px;color: #ffffff;cursor: pointer;display: inline-block;font-size: 14px;height: 32px;line-height: 32px;
margin: 0 5px;overflow: visible;padding: 0 20px;text-decoration: none;vertical-align: middle;margin-top:15px;">提交</button>
			</td>
			</tr>
		</table>
		</form>
	</div>
	<div class="pop-compare" data-load="true" id="pop-compare" style="display: none; bottom: 0px;">
		<div class="pop-wrap">
			<p class="pop-compare-tips"></p>
			<div class="pop-inner">
				<div class="diff-hd">
					<ul class="tab-btns clearfix">
						<li class="ui-switchable-item current"><a href="javascript:;">对比栏</a></li>
					</ul>
					<div class="operate">
						<a class="hide-me" href="javascript:hideCompare();">隐藏</a>
					</div>
				</div>
				<div class="diff-bd tab-cons">
					<div class="tab-con ui-switchable-panel ui-switchable-panel-selected" style="display: block;">
						<div class="diff-items clearfix" id="diff-items">
							
						</div>
						<div class="diff-operate">
							<a class="btn-compare-b compare-active" href="javascript:toCompare();" id="goto-contrast">对比</a>
							<a class="del-items" href="javascript:clearAllCompare();">清空对比栏</a>
						</div>
					</div>					
				</div>
			</div>
		</div>
	</div>
</body>
</html>