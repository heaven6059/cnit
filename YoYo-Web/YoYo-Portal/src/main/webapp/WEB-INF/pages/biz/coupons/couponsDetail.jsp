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
<title>${coupons.cpnsName}</title>

<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<link type="text/css" href="${path}/resources/styles/base.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/details.css?v=${versionVal}" rel="stylesheet" />
<script src="${path}/resources/scripts/biz/goods/flashBuyDetailPage_v7.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/index.css?v=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/goods.css?v=${versionVal}">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/parameter.css?v=${versionVal}">
<style type="text/css">
.zoomspan{display:none;}
.g_s_name {width: 80px;}
.g_s_data {width: 480px;}
.g_cs_t ul li {padding-right: 0px;}
.goodsIntro *{max-width: 100%;   /* 设置最大宽度 */}
.buyNow {cursor: pointer;font-size: 20px;height: 40px;margin: 0;width: 220px;line-height: 40px;background: #e32a2f none repeat scroll 0 0;color: #ffffff;border: none;}
.buyNow:hover {background: #c30000 none repeat scroll 0 0;}
</style>
</head>
<body>
<div class="gray"></div>
<div class="car_add">
    <a class="close" href="javascript:;">X</a>
</div>
<input type="hidden" id="cpnsId" value="${coupons.cpnsId }"/>
	<c:choose>
		<c:when test="${coupons.cpnsId!=null}">
	<!-- 商品信息部分 -->
	<div class="goods">
		<div class="goods_in">
	        <!--商品图片展示-->
	    	<div class="goods_l fl">
	        	<div class="goods_show">
	            	<div class="goods_pic" style="padding:0px;height:348px;position: relative;">
	                	<img src="${imagePath }${coupons.bigPic }" alt="" width="335" height="335" class="jqzoom">
	                	<div class="zoomspan" style="display: block; left: 100px; top: 200px;"></div>	                	
	                </div>
	                <div class="goods_min">
	                	<div class="spec-items">
		                	<ul style="position: absolute; width: 310px; height: 54px; top: 0px; left: 0px;">
		                    	<li style="margin-right: 3px;"><img src="${imagePath }${coupons.bigPic }" alt="" width="50" height="50"></li>
		                    </ul>
	                    </div>
	                    <a class="b_left"></a>
	                    <a class="b_right"></a>
	                </div>
	            </div>
	       	    <div class="goods_share">
	          		<ul>
	                	<li>编号：${coupons.cpnsPrefix}</li>
	                </ul>
	            </div>
	        </div>
	        <!--商品图片展示 end-->
	        
	        <!--参数信息-->
	        <div class="goods_m fl">
	        	<div class="g_name">
	            	<h2 style="width: 560px;overflow: hidden;line-height: 30px;">${coupons.cpnsName}</h2>
	            	<h3 class="orange" style="width: 560px;height: 20px;line-height:20px;font-size:16px;">${coupons.description}</h3>
	            </div>
	            <div class="g_j" style="padding:15px 0px;">
            		<span >购买价：￥</span>
            		<strong id="price" class="red"><fmt:formatNumber value="${coupons.cnnsPrice}" pattern="#0.00"/></strong>
            		<span >面值：￥</span><span id="yoyoPrice" class="red"><fmt:formatNumber value="${coupons.cnnsPar}" pattern="#0.00"/></span>
	            </div>
	        </div>
	        <div class="goods_m fl">
	        	<ul class=" clearfix">
                   	<li class="g_cs01" >
                       	<span class="fl"></span>
                           <div class="fl" style="padding:10px 10px 10px 10px">
                               <p style="width:400px;height:18px;overflow:hidden;">有效期：
                               <fmt:formatDate value="${coupons.fromTime}" pattern="yyyy-MM-dd HH:mm:ss"/> 至 <fmt:formatDate value="${coupons.toTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
                           </div>
                       </li>
                  </ul>
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
            	<div class="g_num_r fl" style="padding-left: 10px;">库存：<span>${coupons.onlineQuantity-coupons.onlineGenQuantity}</span></div>
            </div>
            <div class="g_shop clearfix">
				<input type="button" value="立即购买" id="activityBuyNow" class="buyNow">						                
            </div>
	        <!--参数信息 end-->
	        
	        <div class="clearfix"></div>
	    </div>
	    <div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
	
	<!-- 优惠套餐+产品描述 -->
	<div class="g_main">
    
    <!--产品描述-->
    
    <div class="g_f clearfix" >
    
        <div class="g_cp fl">
        	<div class="stor" style="background-color: white;">
                <h2 id="dpt" class="g_select">产品描述</h2>
            </div>
            <div id="dpt_select" class="g_cont clearfix g_open">
            	<div style="display:none;">
                    <div class="table clearfix">
                    	<ul class="goods_attribute" style="padding-top:20px;padding-bottom:20px;display:table;">
                    		<li></li>
	                	</ul>
                    </div>
                </div>
            </div>
        </div>
        
         <!-- 店铺start -->
		<div id="extInfo" class="extInfo fr" style="width: 208px;padding:0px;margin: 0px;border:0px;">
			<div style="width:168px;height:auto;border:1px solid #CBCBCB;padding:12px 15px;margin:5px;">
				<div class="brand-logo">
					<a target="_blank" href="${portalPath}/shop/index?companyId=${company.companyId}">
						<img title="${company.storeName}" width="166px;" height="60px;" src="${imagePath }${company.image}" />
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
	                        	<img src="${imagePath }${topsales.smallPic}" alt="" style="width:45px;height:45px;">
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

		<!-- 商品详情部分 -->
		</c:when>
		<c:otherwise><!-- 该商品不存在 -->
		<c:out value="${coupons.cpnsId}"></c:out>
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
<script src="${path}/resources/scripts/biz/goods/coupons.js?v=${versionVal}"></script>
</body>
</html>