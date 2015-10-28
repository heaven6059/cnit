<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
    response.addHeader("Pragma", "no-cache");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.addHeader("Cache-Control", "pre-check=0, post-check=0");
    response.setDateHeader("Expires", 0);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<title>YOYO汽车商城</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/index.css?v=${versionVal}">
</head>
<body>
<!--content-->
<div class="con">
  <input type="hidden" id="newCateId" value="${cateId }"/>
  <div class="con_in"> 
    <!--侧边栏-->
    <div class="sidebar">
      <ul class="menu_ul" id="virtualCateList">
      </ul>
    </div>
    <!--侧边栏 end--> 
    
    <!--右侧浮动栏-->
    <div class="con_right">
      <div class="con_right_t">
        <div class="yoyo">
          <h3 class="fl">YOYO快讯</h3>
          <a class="fr" href="${path}/news/more">更多<span>&nbsp;&gt;</span></a> 
        </div>
        <div class="yoyo_con">
          <ul id="newsList">
          	<c:forEach items="${newsList}" var="news">
          		 <li> 
          		 	<c:choose>
          		 		<c:when test="${news.hasContent}">
          		 			<a href="${path}/news/detail?newsId=${news.newsId}">
          		 		</c:when>
          		 		<c:otherwise>
          		 			<a href="${news.url}">
          		 		</c:otherwise>
          		 	</c:choose>
	              		<h5 class="fl">
	              			<c:choose>
	              				<c:when test="${news.type}">
	              					[公告]
	              				</c:when>
	              				<c:otherwise>
	              					[特惠]
	              				</c:otherwise>
	              			</c:choose>
	              		</h5>
	              		<span class="fl" style="margin-left:5px;">${news.title}</span> 
	              	</a> 
	              </li>
          	</c:forEach>
          </ul>
        </div>
      </div>
      <div class="con_right_b">
        <div class="yoyo">
          <h3 class="fl">自助保养服务</h3>
        </div>
        <div class="serve">
          <div class="serve_t clearfix">
            <p>当前行驶里程</p>
            <div class="serve_ipt">
              <input class="fl" maxlength="7" id="curmileage" type="text" autocomplete="off" />
              <span class="fr">公里</span> </div>
          </div>
          <div class="serve_m clearfix">
            <p>新车上路时间</p>
            <select class="fl" id="year">
              <option value="0">年份</option>
            </select>
            <select class="fr" id="month">
              <option value="0">月份</option>
            </select>
          </div>
          <div class="serve_b">
            <c:choose>
            
              <c:when test="${sessionScope.accountType==110 || sessionScope.accountType==120}"><span>查看我需做哪些保养</span></c:when>
              <c:otherwise><a href="javascript:;" onclick="index.qryMaintian()">查看我需做哪些保养</a></c:otherwise>
            </c:choose>
          </div>
          <div class="serve_f">
            <ul>
              <li> <span class="zp fl"></span>
                <p class="fl">100%<br />
                  正 品</p>
              </li>
              <li> <span class="pf fl"></span>
                <p class="fl">先 行<br />
                  赔 付</p>
              </li>
              <li style="margin-right: 0; "> <span class="bz fl"></span>
                <p class="fl">技 术<br />
                  保 障</p>
              </li>
            </ul>
          </div>
        </div>
      </div>
    </div>
    <!--右侧浮动栏 end--> 
  </div>
</div>
<div class="con"> 
  <!--banner-->
  <div class="banner">
    <div class="banner_t">
      <ul>
        <li class="c01 current"> <a href="javascript:;"><img style="width: 760px; height: 470px;"></a> </li>
        <li class="c02"> <a href="javascript:;"><img style="width: 760px; height: 470px;"></a> </li>
        <li class="c03"> <a href="javascript:;"><img style="width: 760px; height: 470px;"></a> </li>
        <li class="c04"> <a href="javascript:;"><img style="width: 760px; height: 470px;"></a> </li>
      </ul>
      <ol>
        <li class="current"></li>
        <li></li>
        <li></li>
        <li></li>
      </ol>
    </div>
    <div class="banner_b"> <a class="left_btn" href="javascript:;"><img src="${path}/resources/images/index/left_btn.png" width="10" height="19"></a>
      <div class="b_ad">
        <ul class="b_ad_ul">
          <li class="b_ad_li">
            <ul class="banner_b_add clearfix">
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴清洗服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
            </ul>
          </li>
          <li class="b_ad_li">
            <ul class="banner_b_add clearfix">
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油</h5>
                  <p class="orange">优惠</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
            </ul>
          </li>
          <li class="b_ad_li">
            <ul class="banner_b_add clearfix">
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷2334油</h5>
                  <p class="orange">优惠</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
              <li class="br1"></li>
              <li> <a href="javascript:;">
                <div class="sib fl">
                  <h5>喷油嘴服务</h5>
                  <p class="orange">优惠价：￥128</p>
                </div>
                <span class="sib_span fr"></span> </a> </li>
            </ul>
          </li>
        </ul>
      </div>
      <a class="right_btn" href="javascript:;"><img src="${path}/resources/images/index/right_btn.png" width="10" height="19"></a> </div>
  </div>
  <!--banner end-->
  <div class="con_in">
    <div class="hotSell">
      <div class="hs_l fl">
        <h3>超值热销</h3>
      </div>
      <div class="hs_r fr">
        <ul class="hs_ul clearfix">
          <li class="fore4">
            <c:forEach items= "${resultHot.rows}"  var = "good">
              <div class="fore1"> <a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank"><img src="${imagePath }${good.pic220x220}" alt="${good.name}" title="${good.name}" ></a> </div>
            </c:forEach>
          </li>
        </ul>
        <div class="hs_page"> <a class="hs_prev" href="javascript:;"><</a> <a class="hs_next" href="javascript:;">></a> </div>
      </div>
    </div>
  </div>
</div>
</div>
<!--content end--> 

<!--main-->
<div class="main"> 
  
  <!--1F-->
  <div class="f_1">
    <div class="stor">
      <h2 class="fl"><span>1F</span>新车优惠</h2>
      <a class="fr" href="${path}/search?cId=${cateId}" target="_blank">更多<span>></span></a> </div>
    <div class="car_show">
      <ul class="clearfix">
        <c:forEach items= "${result.rows}"  var = "good">
          <li>
            <div class="in_pic"><a class="clearfix" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}"  target="_blank"><img src="${imagePath }${good.pic180x180 }" title="${good.name}" ></a></div>
            <div class="pro_price"> <a class="clearfix" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">
              <h4 title="${good.name}">
                <c:choose>
                  <c:when test="${fn:length(good.name) >34}">
                    <c:out value="${fn:substring(good.name, 0, 34)}..." />
                  </c:when>
                  <c:otherwise>
                    <c:out value="${good.name}" />
                  </c:otherwise>
                </c:choose>
              </h4>
              <h3 class="red">￥${good.price }<span>元起</span></h3>
              </a> </div>
          </li>
        </c:forEach>
      </ul>
    </div>
  </div>
  
  <!--2F-->
  <div class="f_2">
    <div class="stor">
      <h2 class="fl"><span>2F</span>保养服务</h2>
      <a class="fr" href="${path}/search?cId=${cateId3}" target="_blank">更多<span>></span></a> </div>
    <div class="f_2_in">
      <div class="f_2_l fl">
        <ul class="f_2_ult clearfix">
          <li> <a class="clearfix" href="javascript:;">
            <h4>夏日保养</h4>
            <h3>5S级保养体验<br />
              <span class="orange">4折</span>套餐价格</h3>
            <h5>Summer Maintenance</h5>
            <img src="${path}/resources/images/index/yo_03.jpg" width="130" height="174"> </a> </li>
        </ul>
        <ul class="f_2_ulb clearfix">
          <li> <a class="clearfix" href="javascript:;">
            <h4>夏日保养</h4>
            <h3>YOYO<span class="orange">全方位</span>照顾</h3>
            <h5>Summer Maintenance</h5>
            <img src="${path}/resources/images/index/yo_05.jpg" alt=""> </a> </li>
        </ul>
      </div>
      <div class="f_2_m fl">
        <ul>
          <c:forEach items= "${mainResult.rows}"  begin="0"  end="1" var = "good">
            <li> <a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank"><img src="${imagePath }${good.pic300x300 }" alt="${good.name}"  title="${good.name}" ></a> 
            <a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">
              <h4>
                <c:choose>
                  <c:when test="${fn:length(good.name) >40}">
                    <c:out value="${fn:substring(good.name, 0, 40)}..." />
                  </c:when>
                  <c:otherwise>
                    <c:out value="${good.name}" />
                  </c:otherwise>
                </c:choose>
              </h4>
              <h5>
                <c:choose>
                  <c:when test="${fn:length(good.brief) >10}">
                    <c:out value="${fn:substring(good.brief, 0, 10)}..." />
                  </c:when>
                  <c:otherwise>
                    <c:out value="${good.brief}" />
                  </c:otherwise>
                </c:choose>
              </h5>
              </a>
              <h3 class="red">￥${good.price }<s>￥${good.mktPrice }</s></h3>
              <div class="shop"><a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">购买</a> 已有<i>${good.buyCount }</i>人购买</div>
            </li>
          </c:forEach>
        </ul>
      </div>
      <div class="f_2_r fr">
        <ul class="clearfix">
          <c:forEach items= "${mainResult.rows}"  begin="2"  end="5" var = "good">
            <li> <a class="fl" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank"><img src="${imagePath }${good.pic110x110 }" title="${good.name}"></a>
              <div class="fr f_2_r_r"> <a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">
                <h4 title="${good.name}">
                  <c:choose>
                    <c:when test="${fn:length(good.name) >8}">
                      <c:out value="${fn:substring(good.name, 0, 8)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${good.name}" />
                    </c:otherwise>
                  </c:choose>
                </h4>
                <h5>
                  <c:choose>
                    <c:when test="${fn:length(good.brief) >10}">
                      <c:out value="${fn:substring(good.brief, 0, 10)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${good.brief}" />
                    </c:otherwise>
                  </c:choose>
                </h5>
                </a>
                <h3 class="red">￥${good.price }<s>￥${good.mktPrice }</s></h3>
                <div class="shop"><a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">购买</a> 已有<i>${good.buyCount }</i>人购买</div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  
  <!--3F-->
  <div class="f_3">
    <div class="stor">
      <h2 class="fl"><span>3F</span>配件更换</h2>
      <a class="fr" href="${path}/search?cId=${cateId2}" target="_blank">更多<span>></span></a> </div>
    <div class="f_3_in">
      <div class="fl"><img src="${path}/resources/images/index/yo_06.jpg" width="180" height="380"></div>
      <div class="fl" >
        <ul class="clearfix">
          <c:forEach items= "${accessResult.rows}"  begin="0"  end="2" var = "good">
            <li> <a class="fl" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank"><img src="${imagePath }${good.pic110x110 }"   title="${good.name}"></a>
              <div class="state fr"> <a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">
                <h4 title="${good.name}">
                  <c:choose>
                    <c:when test="${fn:length(good.name) >10}">
                      <c:out value="${fn:substring(good.name, 0, 10)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${good.name}" />
                    </c:otherwise>
                  </c:choose>
                </h4>
                <h5>
                  <c:choose>
                    <c:when test="${fn:length(good.brief) >10}">
                      <c:out value="${fn:substring(good.brief, 0, 10)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${good.brief}" />
                    </c:otherwise>
                  </c:choose>
                </h5>
                </a>
                <h3 class="red">￥${good.price }<s>￥${good.mktPrice }</s></h3>
                <div class="shop"><a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">购买</a> 已有<i>${good.buyCount }</i>人购买</div>
              </div>
              </a> </li>
          </c:forEach>
        </ul>
        <ul class="clearfix">
          <c:forEach items= "${accessResult.rows}"  begin="3"  end="5" var = "good">
            <li> <a class="fl" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank"><img src="${imagePath }${good.pic110x110 }"   title="${good.name}" ></a>
              <div class="state fr"> <a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">
                <h4 title="${good.name}">
                  <c:choose>
                    <c:when test="${fn:length(good.name) >10}">
                      <c:out value="${fn:substring(good.name, 0, 10)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${good.name}" />
                    </c:otherwise>
                  </c:choose>
                </h4>
                <h5>
                  <c:choose>
                    <c:when test="${fn:length(good.brief) >10}">
                      <c:out value="${fn:substring(good.brief, 0, 10)}..." />
                    </c:when>
                    <c:otherwise>
                      <c:out value="${good.brief}" />
                    </c:otherwise>
                  </c:choose>
                </h5>
                </a>
                <h3 class="red">￥${good.price }<s>￥${good.mktPrice }</s></h3>
                <div class="shop"><a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" target="_blank">购买</a> 已有<i>${good.buyCount }</i>人购买</div>
              </div>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <!--4楼-->
  <div class="f_4">
    <div class="stor">
      <h2 class="fl"><span>4F</span>优惠券</h2>
      <a class="fr" href="${path}/searchCoupons" target="_blank">更多<span>></span></a> </div>
    <div class="f_4_in">
      <ul class=" clearfix">
       	<c:forEach items= "${resultCoupons.rows}"   var = "good"  varStatus="abc">
       		<c:if test="${abc.count%3==0}">
       			<li style=" margin-right: 0;">
       		</c:if>
       		<c:if test="${abc.count%3 !=0}">
        		<li>
        	</c:if>
        			<a class="fl" href="javascript:;"><img src="${imagePath }${good.bigPic }" width="220" height="220"></a>
        			<div class="fr f_4_c">
        			<h4 style="font-size:16px;color: #6c6c6c;">${good.cpnsName }</h4>
        			<h4 style="font-size:12px;color: #6c6c6c;">适用店铺:
						<c:if test="${!empty good.storeName}"><a href="${path}/shop/index?companyId=${good.companyId}" title="${good.storeName }" target="_blank">${fn:substring(good.storeName, 0, 5)}...</a></c:if>
						<c:if test="${empty good.storeName}">无</c:if>
					</h4>
        			<h5 style="font-size:14px;color: #6c6c6c;">发放数量：${good.onlineQuantity}</h5>
	                <h3 class="red" style="font-size:14px;">已领数量：${good.onlineGenQuantity} </h3>
	                <div class="shop" style="padding-top:30px;"><a href="javascript:index.focuseCoupon(${good.cpnsId});" >领取</a></div></div>
        		</li>
        </c:forEach>
      </ul>
    </div>
  </div>
   <!--5楼-->
  <div class="f_4">
    <div class="stor">
      <h2 class="fl"><span>5F</span>代金券</h2>
      <a class="fr" href="${path}/searchCoupons" target="_blank">更多<span>></span></a> </div>
    <div class="f_4_in">
      <ul class=" clearfix">
       	<c:forEach items= "${resultCoupons1.rows}"   var = "good"  varStatus="abc">
       		<c:if test="${abc.count%3==0}">
       			<li style=" margin-right: 0;">
       		</c:if>
       		<c:if test="${abc.count%3 !=0}">
        		<li>
        	</c:if>
        			<a class="fl" href="${path}/coupons/detail?cpnsId=${good.cpnsId}" target="_blank"><img src="${imagePath }${good.bigPic }" width="220" height="220"></a>
        			<div class="fr f_4_c">
        			<h4 style="font-size:16px;color: #6c6c6c;">${good.cpnsName }</h4>
        			<h4 style="font-size:12px;color: #6c6c6c;">适用店铺:
						<c:if test="${!empty good.storeName}"><a href="${path}/shop/index?companyId=${good.companyId}" title="${good.storeName }" target="_blank">${fn:substring(good.storeName, 0, 5)}...</a></c:if>
						<c:if test="${empty good.storeName}">无</c:if>
					</h4>
        			<h5 style="font-size:14px;color: #6c6c6c;">购买价：${good.cnnsPrice}</h5>
	                <h3 class="red" style="font-size:14px;">面值：${good.cnnsPar} </h3>
	                <div class="shop" style="padding-top:30px;"><a href="${path}/coupons/detail?cpnsId=${good.cpnsId}" target="_blank">购买</a></div></div>
        		</li>
        </c:forEach>
      </ul>
    </div>
  </div>
</div>
<!--main end--> 

<!--右侧便捷功能-->
<div class="mui">
  <ul>
    <li> <a href="javascript:;" uid="8759328409230" class="oWindow"> <span class="s01"></span>
      <p>在线咨询</p>
      </a> </li>
    <li> <a href="javascript:;"> <span class="s02"></span>
      <p>在线咨询</p>
      </a> </li>
    <li> <a href="javascript:;"> <span class="s03"></span>
      <p>在线咨询</p>
      </a> </li>
    <li class="last"> <a class="clearfix" href="javascript:;"> <span class="s04 last fl"></span>
      <p class="fr">返回<br />  顶部</p> </a> </li>
  </ul>
</div>
<!--右侧便捷功能end-->
</div>
<!--content--> 
<script type="text/javascript" src="${path}/resources/scripts/biz/index.js?v=${versionVal}"></script> 
<script type="text/javascript">
	accountId = "${sessionScope.accountId}";
	accountType = "${sessionScope.accountType}";
	var basePath = '${path}';
	$(function() {
		 $("a.oWindow").click(function(){
	         var uid=$(this).attr("uid");
	         window.open(yoyo.portalUrl+"/resources/chat/chat.jsp");
	     });
   });
</script>
</body>
</html>