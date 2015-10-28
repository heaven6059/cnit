<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  --%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${goods.name}</title>
<script type="text/javascript">var _path="${path}";</script>
<script src="${path}/resources/scripts/common/yoyo.js"></script>
<%-- <link type="text/css" href="${path}/resources/styles/goods/jd/base.css" rel="stylesheet" /> --%>
<link type="text/css" href="${path}/resources/styles/base.css" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/goods/jd/details.css" rel="stylesheet" />
<script src="${path}/resources/scripts/biz/goods/goodsDetail.js"></script>
<script src="${path}/resources/scripts/biz/goods/flashBuyDetailPage_v7.js"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/index.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/goods.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/goods/parameter.css">
<script type="text/javascript" src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css" href="${path}/resources/styles/select2/select2.min.css" rel="stylesheet" />

<style type="text/css">
/* .disabledLi a{
	cursor: not-allowed;
	border: 1px dashed #CCC !important;
	color: #999;
}
.goods_attribute li{
	float: left;
	width: 240px;
	line-height: 25px;
	padding-left: 42px;
	overflow: hidden;
	text-overflow:ellipsis;
	white-space:nowrap; 
}
.g_s_name {
	overflow: hidden;
	white-space:nowrap;
}
.g_show_l{
	overflow: hidden;
}
a img {
	border: 1px solid gray;
}
.ui-page {
	padding-top: 15px;
	padding-bottom: 5px;
	float: right;
}
.g_b td {
	border-top: 1px solid #DDD;
	overflow: hidden;
}
.tp_div td {
	width: 82px;
	height: 82px;
	border: 1px solid #DDD;
	overflow: hidden;
	text-align: center;
}
.p-photos-viewer {
	width: 500px;
	height: 500px;
}
.tp_div td img {
	cursor: url(${path}/resources/images/goods/big.cur),auto;
}
.zoomdiv {
	display: none;
	position: absolute;
	z-index: 6;
	background: #fff;
	border: 1px solid #ededed;
	text-align: center;
	overflow: hidden;
} */
.zoomspan{
	display:none;
}
.g_s_name {
	width: 80px;
}
.g_s_data {
	width: 480px;
}
.g_j p {
	font-size: 16px;
}
.g_cs_t ul li {
	padding-right: 0px;
}
</style>


<script>
	$(function(){
		
		
		
		
		/* //产品描述评论切换
		$(".stor h2").click(function(e){
            var currentId = $(this).attr("id");
			$(this).addClass("g_select").siblings("h2").removeClass("g_select");
			$(".g_cont").removeClass("g_open");
			$("#"+currentId +"_select").addClass("g_open");
            if("par"==$(this).attr("id"))
                isCangshu = true;
            else
                isCangshu = false;
        });
		
		//左侧参数菜单
		$("#navScrollLeft ul li a").click(function(t){
			clickFlag = true;
			var scrollTop = $(this).attr("scroll-top");//scrollTop值
			$(window).scrollTop(scrollTop);
			$("#navScrollLeft ul li a").removeClass("active");
			$(this).addClass("active");
			//console.log($(this).html()+"-"+scrollTop);
			
		});
		
		var leftMenuTop = [0,503,908,1582,1682,1902,2086,2491,2981,3200,3540,4060,4457,4748,5196,5380];//左侧菜单scrollTop值
		var currentSTop =0;
		var c_index = 0;//当前定位在左侧第几个菜单 0第一个
		var next_index = 1;
		var clickFlag = false; //是否是点击
		$(window).scroll(function(){
			//console.log(currentSTop+" - "+c_index+"- "+next_index );	
			currentSTop = $(window).scrollTop();
			if(currentSTop>leftMenuTop[next_index] ){
				if(next_index+1 <= leftMenuTop.length){
					c_index++;
					next_index++;
					if(!clickFlag){
						$("#navScrollLeft ul li a").removeClass("active");
						$("#navScrollLeft ul li a").eq(c_index).addClass("active");
					}
					
				}
				
			}else if(currentSTop < leftMenuTop[c_index] && currentSTop >=  leftMenuTop[0]) {
					c_index--;
					next_index--;
					if(!clickFlag){
						$("#navScrollLeft ul li a").removeClass("active");
						$("#navScrollLeft ul li a").eq(c_index).addClass("active");
					}
			}
			
			clickFlag = false;
			
			
		});

        var isCangshu = false; //是否是参数页
        var currentSTop = 0; //滚动条top值
        $(window).scroll(function(){
           if(isCangshu){
                currentSTop = $(window).scrollTop();
                if(currentSTop <480){
                    $("#navScrollLeft").css({"position":"absolute","top":0});
                }else{
                    $("#navScrollLeft").css({"position":"fixed","top":480});
                }
                      
                //console.log(currentSTop);
           }
        }); */
		
	})
</script>



</head>
<body>

<!--增加车型模态窗口-->
<div class="gray"></div>
<div class="car_add">
    <a class="close" href="javascript:;">X</a>
</div>
<!--增加车型模态窗口-->


	<c:choose>
		<c:when test="${goods.goodsId!=null}">
	<!-- 商品信息部分 -->
	<div class="goods">
		<div class="goods_in">
			<input type="hidden" id="goods_id" value="${goods.goodsId}" />
			<input type="hidden" id="car_id" value="${goods.carId}" />
			<input type="hidden" id="product_id" value="${productId}" />
			<input type="hidden" id="marketable" value="${goods.marketable}" />
	        <!--商品图片展示-->
	    	<div class="goods_l fl">
	        	<div class="goods_show">
	            	<div class="goods_pic" style="padding:0px;height:348px;position: relative;">
	                	<img src="${goods.midPic}" alt="${goods.midPic}" width="335" height="335" class="jqzoom"> <!-- onError="imgErr(this)" -->
	                	<div class="zoomspan" style="display: block; left: 200px; top: 200px;"></div>
	                </div>
	                <div class="goods_min">
	                	<ul>
	                    	<li style="margin-right: 3px;"><img src="${goods.midPic}" alt="${goods.midPic}" width="50" height="50"></li>
	                    </ul>
	                    <b class="b_left"></b>
	                    <b class="b_right"></b>
	                </div>
	            </div>
	       	    <div class="goods_share">
	          		<ul>
	                	<li>商品编号：${goods.goodsId}</li>
	                    <li class="g_li02"><a href="javascript:;" onclick="addWishList()"><i></i>关注商品</a></li>
	                    <li class="g_li03"><a href="javascript:;"><i></i>分享</a></li>
	                    <c:if test="${goods.accId>0}">
	                    	<li class="g_li04"><a href="javascript:addCompare(${goods.goodsId},'<c:out value="${goods.name}" />','<c:out value="${goods.smallPic}"/>','${goods.price}');"><i></i>加入对比</a></li>
	                    </c:if>
	                    <c:if test="${goods.carId>0}">
	                    	<li class="g_li04"><a href="${path}/carCompare/carCompare?ids=${goods.carId}"><i></i>加入对比</a></li>
	                    </c:if>
	                   
	                </ul>
	            </div>
	        </div>
	        <!--商品图片展示 end-->
	        
	        <!--参数信息-->
	        <div class="goods_m fl">
	        	<div class="g_name" style="height:80px;">
	            	<h2 style="width: 560px;height: 60px;overflow: hidden;line-height: 30px;">${goods.name}</h2>
	            	<h3 class="orange" style="width: 560px;height: 20px;overflow: hidden;line-height:20px;font-size:16px;" title="${goods.brief}">${goods.brief}</h3>
	            </div>
	            <div class="g_j" style="height: 60px;line-height: 60px;padding-left:0px;">
	            	<span id="priceName" style="font-size: 16px;color:red;">预付定金：</span><strong class="red">￥${goods.price}</strong>
	            	<c:if test="${goods.carId!=null && goods.carId!='' && goods.carId>0}">
	            		<p id="yoyoPrice">优优价：￥${goods.mktPrice}</p>
	            	</c:if>
	            	<c:choose>
	            		<c:when test="${goods.buyCount!=null&&goods.buyCount!=''}">
	            			<p>总销量：${goods.buyCount}</p>
	            		</c:when>
	            		<c:otherwise>
	            			<p>总销量：0</p>
	            		</c:otherwise>
	            	</c:choose>
	                <!-- <p>原价：<del>￥100万</del></p> -->
	            </div>
	            
	            <c:choose>
	            	<c:when test="${goods.marketable=='1'}">
	            		<div class="g_cs" style="height: auto;">
			        		<c:if test="${goods.carId!='0' }">
				            	<div class="g_cs_t" >
				                	<!-- <ul>
				                    	<li class="g_cs01">
				                        	<span class="fl"></span>
				                            <div class="g_con fr">
				                                <p>厂商：<i>--</i></p>
				                                <p>级别：<i>--</i></p>
				                            </div>
				                        </li>
				                    	<li class="g_cs02">
				                        	<span class="fl"></span>
				                            <div class="g_con fr">
				                                <p>质保：<i>--</i></p>
				                                <p>保养间隔：<i>--</i></p>
				                            </div>
				                        </li>
				                    	<li class="g_cs03">
				                        	<span class="fl"></span>
				                            <div class="g_con fr">
				                                <p>油耗：<i>--</i></p>
				                                <p>燃油及标号：<i>--</i></p>
				                            </div>
				                        </li>
				                    </ul> -->
				                    
				                	<ul class=" clearfix">
				                    	<li class="g_cs01" ><!-- 135 -->
				                        	<span class="fl"></span>
				                            <!-- <div class="g_con fl"> -->
				                            <div class="fl" >
				                                <p style="width:100px;height:18px;overflow:hidden;">厂商：<i>--</i></p>
				                                <p style="width:100px;height:18px;overflow:hidden;">品牌：<i><c:choose><c:when test="${goods.brandName!=null&&goods.brandName!=''}">${goods.brandName}</c:when><c:otherwise>--</c:otherwise></c:choose></i></p>
				                            </div>
				                        </li>
				                    	<li class="g_cs02" ><!-- 175 -->
				                        	<span class="fl"></span>
				                            <!-- <div class="g_con fl"> -->
				                            <div class="fl">
				                                <p style="width:140px;height:18px;overflow:hidden;">质保：<i>--</i></p>
				                                <p style="width:140px;height:18px;overflow:hidden;">保养间隔：<i>--</i></p>
				                            </div>
				                        </li>
				                    	<li class="g_cs03" ><!-- 195 -->
				                        	<span class="fl"></span>
				                            <!-- <div class="g_con fl"> -->
				                            <div class="fl">
				                                <p style="width:145px;height:18px;overflow:hidden;">油耗：<i>--</i></p>
				                                <p style="width:145px;height:18px;overflow:hidden;">燃油及标号：<i>--</i></p>
				                            </div>
				                        </li>
				                    </ul>
			                
				                </div>
			                </c:if>
			            	<div class="g_cs_b" style="margin-top:10px;height:auto;min-height: 160px;">
			                	<!-- <div class="g_s1 clearfix">
			                    	<div class="g_s_name fl">选择年款：</div>
			                        <div class="g_s_data fl">
			                        	<ul>
			                            	<li class="g_cur"><a href="javascript:;">2015款</a><i></i></li>
			                            	<li><a href="javascript:;">2014款</a><i></i></li>
			                            	<li><a href="javascript:;">2013款</a><i></i></li>
			                            	<li><a href="javascript:;">2012款</a><i></i></li>
			                            </ul>
			                        </div>
			                    </div>
			                	<div class="g_s1 clearfix">
			                    	<div class="g_s_name fl">选择车款：</div>
			                        <div class="g_s_data fl">
			                        	<ul>
			                            	<li class="g_cur"><a href="javascript:;">xDrive35i 中国限量版</a><i></i></li>
			                            </ul>
			                        </div>
			                    </div>
			                	<div class="g_s1 clearfix">
			                    	<div class="g_s_name fl">意向颜色：</div>
			                        <div class="g_s_data fl">
			                        	<ul>
			                            	<li class="g_cur"><a href="javascript:;"><b class="x"></b>雪山白</a><i></i></li>
			                            	<li><a href="javascript:;"><b class="c"></b>长滩蓝</a><i></i></li>
			                            	<li><a href="javascript:;"><b class="t"></b>碳黑色</a><i></i></li>
			                            	<li><a href="javascript:;"><b class="k"></b>矿石白</a><i></i></li>
			                            	<li><a href="javascript:;"><b class="y"></b>银石色</a><i></i></li>
			                            	<li><a href="javascript:;"><b class="m"></b>墨尔本红</a><i></i></li>
			                            	<li><a href="javascript:;"><b class="b"></b>宝石青</a><i></i></li>
			                            </ul>
			                        </div>
			                    </div> -->
			                </div>
			            </div>
			            <div class="g_num">
			            	<div class="g_num_l fl" style = "width:80px;">数  量：</div>
			            	<div class="g_num_m fl">
			                	<div class="g_num_in" style="margin-left: 0px;">
			                    	<button class="g_left"></button>
			                        <div class="ipt" style="left:26px;">
			                        	<input type="text" value="1" back="1">
			                        </div>
			                        <button class="g_right"></button>
			                    </div>
			                </div>
			            	<div class="g_num_r fl" style="padding-left: 10px;">库存<span></span></div>
			            </div>
			            <div class="g_shop clearfix">
			            	<div class="g_buy fl" style="width:110px;">
			                    <button style="width:110px;">立即购买</button>
			                </div>
			                <div class="shop_cart fl" id="addCart" style="width:110px;">
			                	<!-- <a class="shop_cartA" href="javascript:;" style="background-image: none;display: block;width: 110px;height: 40px;line-height: 40px;color: #fff;background-color: #f8a42d;font-size: 14px;text-align: center;">加入购物车</a> -->
			                	<button style="width: 110px;height: 40px;border: 0;line-height: 40px;color: #fff;background-color: #ee4509;font-size: 14px;cursor: pointer;font-family: 微软雅黑;">加入购物车</button>
			                	
			                	
			                	
			                </div>
			                <c:if test="${goods.carId!='0'}">
			                	<div class="shop_xzdj fl" style="margin-left: 40px; ">
				                	<a class="xzdj" href="${path}/goodsManager/enquiry?type=1&goodsId=${goods.goodsId}" target="_blank">询最低价</a>
				                </div>
				                <div class="shop_yysj fr" style="margin-left: 10px; ">
				                	<a class="yysj" href="${path}/goodsManager/enquiry?type=2&goodsId=${goods.goodsId}" target="_blank">预约试驾</a>
				                </div>
			                </c:if>
			            </div>
	            	</c:when>
	            	<c:otherwise>
	            		<div>
	            			<table style="width:100%;height:250px;">
								<tr>
									<td style="font-size: 25px;font-weight: bold;vertical-align: middle;padding-left: 50px;color: red;">该商品已下架</td>
								</tr>
							</table>
	            		</div>
	            	</c:otherwise>
	            </c:choose>
	            
	        	
	        </div>
	        <!--参数信息 end-->
	        
	        <!--看了又看-->
	        <div class="goods_r fr" style="display:none;">
	        	<div class="g_look">
	            	<b></b>
	            	<p>看了又看</p>
	                <b></b>
	            </div>
	        	<div class="g_pic">
	            	<ul>
	                	<li style="text-align: center;">
	                    	<!-- <a href="javascript:;"><img src="../base/images/goods_1.jpg" alt=""></a>
	                    	<a href="javascript:;"><img src="../base/images/goods_2.jpg" alt=""></a>
	                    	<a href="javascript:;"><img src="../base/images/goods_3.jpg" alt=""></a> -->
	                    </li>
	                </ul>
	            </div>
	            <div class="g_ref" style="display: none;"><a href="javascript:;" onclick="findRelatedGoods(2)"><i></i>换一批试试</a></div>
	        </div>
	        <!--看了又看 end-->
	    </div>
	</div>
	
	
	<!-- 优惠套餐+产品描述 -->
	
	<div class="g_main">
	<!--优惠套装-->
    <div class="g_f" style="display:none;">
        <div class="stor" style="padding-right:0px;">
            <h2>优惠套装</h2>
        </div>
        <div class="g_sale" style="height: 228px;">
        	<div class="g_nav" style="overflow: hidden;">
        		<b class="accessoryPrev" onclick="findAccessory(1)" style="background-image: url(${path}/resources/images/goods/select.jpg);background-position: 57px 0px;width:15px;height:25px;margin-left:5px;float:left;display:none;"></b>
            	<ul style="margin-top: 2px;float:left;margin-left:10px;">
                	<li class="g_nav_cur" style="width: 77px;overflow: hidden;height: 19px;text-align: center;">优惠套装1</li>
                	<!-- <li>优惠套装2</li>
                	<li>优惠套装3</li> -->
                </ul>
                <b class="accessoryNext" onclick="findAccessory(2)" style="background-image: url(${path}/resources/images/goods/select.jpg);background-position: 42px 0px;width:15px;height:25px;margin-left:15px;float:left;display:none;"></b>
            </div>
            <div class="g_show clearfix">
                <b class="accessoryGoodsPrev" onclick="findAccessoryGoods(1)" style="background-image: url(${path}/resources/images/goods/select.jpg);background-position: 57px 0px;width:15px;height:25px;margin-left:5px;margin-top: 60px;float:left;display:none;"></b>
                <div class="g_show_l fl">
                	
                    <div class="g_show_tp">
                        <a href="javascript:;">
                            <!-- <div class="g_tup" style="height: 152px;"><img src="" alt="" width="140px" height="140px"></div> -->
                            <img src="${goods.midPic }" alt="" width="140px" height="140px">
                        </a>
                        <br/>
                        <a style="width:140px; height: 18px;overflow: hidden;text-align:center;display:block;" href="javascript:;">${goods.name}</a>
                        <span class="g_add" style="top:70px;"></span>
                    </div>
                    <!-- <div class="g_show_tp">
                        <a href="javascript:;">
                            <div class="g_tup"><img src="../base/images/goods_show_02.jpg" alt=""></div>
                        </a>
                        <p>亮牌蓝喜力Nuldnk亮牌蓝喜力Nuldnk</p>
                    </div> -->
                </div>
                <b class="accessoryGoodsNext" onclick="findAccessoryGoods(2)" style="background-image: url(${path}/resources/images/goods/select.jpg);background-position: 42px 0px;width:15px;height:25px;margin-left:-65px;margin-top: 60px;float:left;display:none;"></b>
                <div class="g_show_r fr">
                	<h3 style="margin-top: 10px;"></h3>
                	<input type="hidden" id="accessorySumPrice" value="0" />
                	<input type="hidden" id="accessoryDiscType" value="0" />
                	<input type="hidden" id="accessoryCredit" value="0" />
                    <div class="g_price">
                    	<p>套餐价：<i class="red">￥0.00</i></p>
                    	<p>YOYO价：<del>￥0.00</del></p>
                    	<p>立即节省：<i>￥0.00</i></p>
                    </div>
                   	<c:if test="${goods.marketable=='1'}">
                   		<div class="g_buy_m">
	                        <button class="fl">立即购买</button>
	                        <!-- <a class="fr" href="javascript:;"></a> -->
	                    </div>
                   	</c:if>
                    <span></span>
                </div>
            </div>
        </div>
    </div>
	<!--优惠套装 end-->
    
    <!--产品描述-->
<%--    <div class="g_f clearfix">
        <div class="g_cp fl" style="width: 100%;">
        	<div class="stor">
                <h2 class="g_select">产品描述</h2>
                <h2>评论(41)</h2>
                <h2>商品咨询(20)</h2>
            </div>
            <div class="g_con clearfix">
                <div class="table clearfix">
                	<ul class="goods_attribute" style="padding-top:20px;padding-bottom:20px;">
                		<li>商品名称：${goods.name}</li>
                		<li>商品编号：${goods.goodsId}</li>
                		<c:choose>
                			<c:when test="${goods.brandName!=null&&goods.brandName!='' }"><li>商品品牌：${goods.brandName}</li></c:when>
                		</c:choose>
                		<li style="margin-bottom: 20px;">&nbsp;</li>
                	</ul>
                	
                    <!-- <dl class="g_one">
                        <dt>1.6L排量</dt>
                        <dd>2014款 1.6L SL百万纪念版 MT</dd>
                        <dd>2014款 1.6L SL百万纪念版 AT</dd>
                        <dd>2014款 1.6L MT</dd>
                        <dd>2014款 1.6L AT</dd>
                    </dl>
                    <dl>
                        <dt>厂商指导价</dt>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                    </dl>
                    <dl>
                        <dt>优优价</dt>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                    </dl>
                    <dl>
                        <dt>经销商报价</dt>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                        <dd>11.55万</dd>
                    </dl>
                    <dl>
                        <dt>操作</dt>
                        <dd>
                            <a href="javascript:;">询最低价</a>
                            <a href="javascript:;">预约试驾</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">询最低价</a>
                            <a href="javascript:;">预约试驾</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">询最低价</a>
                            <a href="javascript:;">预约试驾</a>
                        </dd>
                        <dd>
                            <a href="javascript:;">询最低价</a>
                            <a href="javascript:;">预约试驾</a>
                        </dd>
                    </dl> -->
                    
                    
                </div>
                <c:choose>
                	<c:when test="${goods.intro!=null && goods.intro!=''}">
                		<div class="table clearfix" style="padding: 20px;">
		                	${goods.intro}
		                </div>
                	</c:when>
                </c:choose>
            </div>
        </div>
        <!-- <div class="charts fr">
        	xnjndmm,
        </div> -->
    </div>--%>
    
    
    <div class="g_f clearfix" >
    
        <div class="g_cp fl">
        	<div class="stor" style="background-color: white;">
                <h2 id="dpt" class="g_select">产品描述</h2>
                <h2 id="par" class="" style="display:none;">规格参数</h2>
                <h2 class="" id="dis">评论(0)</h2>
                <h2 class="" id="cst">商品咨询(0)</h2>
            </div>
            <div id="dpt_select" class="g_cont clearfix g_open">
            	<div style="display:none;">
                    <div class="table clearfix">
                    	<ul class="goods_attribute" style="padding-top:20px;padding-bottom:20px;display:table;">
	                		<%-- <li>商品名称：${goods.name}</li>
	                		<li>商品编号：${goods.goodsId}</li>
	                		<c:choose>
	                			<c:when test="${goods.brandName!=null&&goods.brandName!='' }"><li>商品品牌：${goods.brandName}</li></c:when>
	                		</c:choose>
	                		<li style="margin-bottom: 20px;">&nbsp;</li> --%>
	                	</ul>
                    </div>
                </div>
            
            	<div style="display:none;">
                    <div class="table clearfix">
                        <dl class="dl01">
                            <dt class="g_dt">1.6L排量</dt>
                            <!-- <dd>2014款 1.6L SL百万纪念版 MT</dd>
                            <dd>2014款 1.6L SL百万纪念版 AT</dd>
                            <dd>2014款 1.6L MT</dd>
                            <dd>2014款 1.6L AT</dd> -->
                        </dl>
                        <dl class="dl02">
                            <dt>厂商指导价</dt>
                            <!-- <dd class="g_dd">11.55万</dd>
                            <dd class="g_dd">11.55万</dd>
                            <dd class="g_dd">11.55万</dd>
                            <dd class="g_dd">11.55万</dd> -->
                        </dl>
                        <dl class="dl03">
                            <dt style="color: #8dc420;">优优价</dt>
                            <!-- <dd>11.55万</dd>
                            <dd>11.55万</dd>
                            <dd>11.55万</dd>
                            <dd>11.55万</dd> -->
                        </dl>
                        <dl class="dl04">
                            <dt>经销商报价</dt>
                            <!-- <dd>11.55万</dd>
                            <dd>11.55万</dd>
                            <dd>11.55万</dd>
                            <dd>11.55万</dd> -->
                        </dl>
                        <dl class="dl05">
                            <dt>操作</dt>
                            <!-- <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd>
                            <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd>
                            <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd>
                            <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd> -->
                        </dl>
                    </div>
                    <!-- <div class="table bt0 clearfix">
                        <dl class="dl01">
                            <dt class="g_dt">1.6L排量</dt>
                            <dd>2014款 1.6L SL百万纪念版 MT</dd>
                        </dl>
                        <dl class="dl02">
                            <dt>&nbsp;</dt>
                            <dd class="g_dd">11.55万</dd>
                        </dl>
                        <dl class="dl03">
                            <dt>&nbsp;</dt>
                            <dd>11.55万</dd>
                        </dl>
                        <dl class="dl04">
                            <dt>&nbsp;</dt>
                            <dd>11.55万</dd>
                        </dl>
                        <dl class="dl05">
                            <dt>&nbsp;</dt>
                            <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd>
                        </dl>
                    </div>
                    <div class="table bt0 clearfix">
                        <dl class="dl01">
                            <dt class="g_dt">1.6L排量</dt>
                            <dd>2014款 1.6L SL百万纪念版 MT</dd>
                            <dd>2014款 1.6L SL百万纪念版 MT</dd>
                        </dl>
                        <dl class="dl02">
                            <dt>&nbsp;</dt>
                            <dd class="g_dd">11.55万</dd>
                            <dd class="g_dd">11.55万</dd>
                        </dl>
                        <dl class="dl03">
                            <dt>&nbsp;</dt>
                            <dd>11.55万</dd>
                            <dd>11.55万</dd>
                        </dl>
                        <dl class="dl04">
                            <dt>&nbsp;</dt>
                            <dd>11.55万</dd>
                            <dd>11.55万</dd>
                        </dl>
                        <dl class="dl05">
                            <dt>&nbsp;</dt>
                            <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd>
                            <dd class="br0">
                                <a class="g_dd_a" href="javascript:;">询最低价</a>
                                <a class="g_dd_aa" href="javascript:;">预约试驾</a>
                            </dd>
                        </dl>
                    </div> -->
                </div>
                <div>
                	<c:if test="${goods.intro!=null && goods.intro!=''}">
                		<div class="table clearfix" style="padding: 20px;width:auto;margin-top:20px;">
		                	${goods.intro}
		                </div>
                	</c:if>
                	<!-- <img src="../base/images/goods_list.jpg" alt=""> -->
                </div>
            </div>
            
            <!--参数配置-->
            <div id="par_select" class="g_cont clearfix">
                <div class="row clearfix fl" id="content" style=" width: 495px; ">
                    <div id="navScrollLeft" class="followcon fl" style=" width: 127px; display: none;">
                        <ul class="folul">
                            <!-- <li class="top">
                                <a class="" href="javascript:;" scroll-top="44">基本参数</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="530" class="active">车身</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="930">发动机</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="1624">变速箱</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="1721">底盘转向</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="2000">车轮制动</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="2216">安全装备</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="2642">操控配置</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="3158">外部配置</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="3395">内部配置</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="3771">座椅配置</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="4313">多媒体配置</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="4745">灯光配置</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="5082">玻璃/后视镜</a>
                            </li>
                            <li>
                                <a href="javascript:;" scroll-top="5263">空调/冰箱</a>
                            </li>
                            <li class="bottom">
                                <a href="javascript:;" scroll-top="5479">高科技配置</a>
                            </li> -->
                        </ul>
                    </div> 
                    <div class="column fr" style=" width: 365px; ">
                        <div class="title">
                            <div class="title-content" style=" padding-top: 0; border-top: 0; ">
                          	</div>
                        </div>
                    </div>
                </div>
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
                        <div class="pf_2 fl">
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
                            	<!-- <li>外观漂亮<b>(343)</b></li>
                            	<li>外观漂亮<b>(343)</b></li>
                            	<li>外观漂亮<b>(343)</b></li>
                            	<li>外观漂亮<b>(343)</b></li>
                            	<li>外观漂亮<b>(343)</b></li>
                            	<li>外观漂亮<b>(343)</b></li> -->
                            </ul>
                        </div>
                        <div class="pf_4 fr" style="display:none;">
                        	<p>你可对已购商品进行评价</p>
                            <button>发表拿优豆</button>
                            <p class="red">前五名双倍优豆<span>(规则)</span></p>
                        </div>
                    </div>
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
                                    <!-- <li><a href="javascript:;">来自优优iPhone客户端</a></li> -->
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
                            <dd><a class="dd_color" href="javascript:toConsult(${goods.goodsId})" target="_blank">浏览所有咨询信息&gt;&gt;</a></dd>
                        </dl>
                    </div>
                </div>
            </div>
        </div>
        
         <!-- 店铺start -->
		<div id="extInfo" class="extInfo fr" style="width: 208px;padding:0px;margin: 0px;border:0px;">
			<div style="width:168px;height:auto;border:1px solid #CBCBCB;padding:12px 15px;margin:5px;">
				<div class="brand-logo">
					<a target="_blank" href="${portalPath}/shop/index?companyId=${company.companyId}">
						<img title="${company.storeName}" width="166px;" height="60px;" src="${company.image}" />
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
				
				<c:if test="${!empty hasReport&&(empty sessionScope.accountType||sessionScope.accountType==0||sessionScope.accountType==100)}">
				<dl class="jd-service">
					<dt id="suport-icons">商品举报：</dt>
					<dd>
						<a href="javascript:;" class="btn-gray btn-shop-access J-enter-shop" onclick="openReport();">举报该商品</a>					
					</dd>
				</dl>
	            </c:if>
            </div>
            <!--热销排行榜-->
	        <!-- <div class="charts fr"> -->
	        <div class="charts" style="margin:5px;">
	        	<p>热销排行榜</p>
	            <ul class="clearfix">
	            	<li style="display:none;">
	                	<a class="clearfix" href="javascript:;" target="_blank">
	                        <div style=" margin-top: 4px;margin-right:10px;" class="fl"><img src="" alt="" style="width:45px;height:45px;"></div>
	                        <div class="fl" style="width:108px;overflow:hidden;">
	                            <h4 style="white-space: nowrap;"></h4>
	                            <h3 class="red">￥0.00</h3>
	                            <h5>已出售0件</h5>
	                        </div>
	                    </a>
	                </li>
	            	
	            </ul>
	        </div>
		</div>
		<!--店 铺END -->
		
		
        <!--热销排行榜-->
        <!-- <div class="charts fr">
        	<p>热销排行榜</p>
            <ul class="clearfix">
            	<li style="display:none;">
                	<a class="clearfix" href="javascript:;" target="_blank">
                        <div style=" margin-top: 4px;margin-right:10px;" class="fl"><img src="" alt="" style="width:45px;height:45px;"></div>
                        <div class="fl" style="width:108px;overflow:hidden;">
                            <h4 style="white-space: nowrap;"></h4>
                            <h3 class="red">￥0.00</h3>
                            <h5>已出售0件</h5>
                        </div>
                    </a>
                </li>
            	
            </ul>
        </div> -->
    
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
						<!-- <input type="hidden" id="goods_id" value="" />
						<input type="hidden" id="car_id" value="" /> -->
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
										<!-- <li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>
										<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>
										<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>
										<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li>
										<li><img alt="" src="" data-url="" data-img="1" width="50" height="50"></li> -->
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
	<div id="report" style="display: none; margin: 30px 30px;">
		<form id="reportForm">
		<input type="hidden" name="source" value="buyer" />
		<input type="hidden" name="productId" value="${goods.goodsId}" />
		<input type="hidden" name="storeName" value="${company.storeName}" />
		<input type="hidden" name="storeId" value="${company.companyId}" />
		<table width="100%" align="center" border="1">
			<tr>
				<td width="20%">联系电话:</td>
				<td><input name="mobile" id="mobile" value="" style="width: 200px"/></td>
			</tr>
			<tr>
				<td>举报原因:</td>
				<td>
					<select name="reason" style="width: 200px">
						<option value="false">虚假信息 </option>
						<option value="unconformity">图片不符</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input value="" name="memo" style="width: 200px"/></td>
			</tr>
			<tr>
				<td style="text-align: center;" colspan="2"><button type="button" onclick="subReport()">提交</button></td>
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