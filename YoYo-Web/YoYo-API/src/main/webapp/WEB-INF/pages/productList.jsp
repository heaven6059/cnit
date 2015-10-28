<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/index.css">
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/search.css">
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</head>
<body>
<!--main-->
<div class="main">
	<div class="search_main clearfix">
    	<p class="ind">全部结果> <span id="searchResult"></span> </p>
    	<div class="search_left fl">
    		<!--左边查看所有类目-->
        	<div class="search_left_t">
            	<p>查看所有类目</p>
            	<div class="sea_sid">
                    <ul id="searchCategory">
                       
                    </ul>
                    <span class="sear_all"><a href="javascript:;">显示全部分类</a></span>
                </div>
            </div>
        	<!--左边查看所有类目 end-->
        	
        	<!--左边推荐-->
            <div class="search_left_b">
           	  <div class="sear_t">
               	<h2>推广商品</h2>
                  <ul>
                  <c:forEach items= "${resultHot.rows}"  var = "good">
                   	  <li>
                          <a target="_blank" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}"><img src="${imagePath }${good.pic180x180}" alt=""></a>
                              <h3>
                              <a target="_blank" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}">
                              <c:choose>  
							    <c:when test="${fn:length(good.name) >27}">  
							        <c:out value="${fn:substring(good.name, 0, 27)}..." />  
							    </c:when>  
							   <c:otherwise>  
							      <c:out value="${good.name}" />
							    </c:otherwise>  
								</c:choose>
                              </a></h3>
                              <h4><a class="red" target="_blank" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}">￥${good.price }</a></h4>
                          </a>
                      </li>
               	  </c:forEach> 
                  </ul>
                </div>
                <div class="sear_b"><a href="javascript:;"><img src="${path}/resources/images/index/search_22.jpg" alt=""></a></div>
          </div>
        	<!--左边推荐 end-->
          
        </div>
        <div class="search_right fr">
        
          <!--右边搜索条件1 店铺
       	  <div class="store clearfix">
            	<div class="store_l fl"><a href="javascript:;"><img src="${path}/resources/images/index/sear_logo.jpg" alt=""></a></div>
                <div class="store_m fl">
                    <h3 class="lh150"><a href="javascript:;">九牧官方旗舰店</a></h3>
                    <h5 class="lh150"><a href="javascript:;">九牧—中国卫浴第一品牌</a></h5>
                </div>
                <dl class="clearfix fr">
                    <dt>猎豹汽车旗舰店</dt>
                    <dd class="sell_ddimg">
                        <span class="star" style="width: 97.10%">
                            <s></s>
                        </span>
                    </dd>
                    <dd>9.71分</dd>
                    <dd class="search_btn"><a href="javascript:;">加关注</a></dd>
                </dl>
          </div>	-->
          
          <!--右边搜索条件2 相关搜索-->
           <!--    <div class="sear_related">
             <ul class="sear_ul">
                    <li>相关搜索：</li>
                    <li><a href="javascript:;">车载充电器</a></li>
                    <li><a href="javascript:;">车载充电器</a></li>
                    <li><a href="javascript:;">车载充电器</a></li>
                    <li><a href="javascript:;">车载充电器</a></li>
                    <li><a href="javascript:;">车载充电器</a></li>
                    <li><a href="javascript:;">车载充电器</a></li>
                </ul>
          </div>-->
          
          <!--右边搜索条件3 筛选搜索-->
          <div class="m">
                <div class="mt">
                    <h1><span id="itemKey"></span>&nbsp;&nbsp;<strong>商品筛选</strong></h1>
                    <div class="total">
                        (共<span id="res_count" class="orange">${result.total }</span>个商品)
                    </div>
                </div>
                <div class="mc clearfix">
                <c:if test="${null!=brandIds}">
                	<div class="brand clearfix" style="margin-bottom: 4px;">
                        <div class="mc_l fl">品牌：</div>
                        <div class="mc_r clearfix fr">
                        	<div class="v_tbs">
                                <div class="tabcon clearfix">
                                	<ul>
                                	<c:forEach items= "${brandIds}"  var = "brand">
                                		<c:if test="${brand.brandId!=0}">
                                			<li><a href="javascript:productList.searchByBId(${brand.brandId });">${brand.brandName }</a></li>
                                		</c:if>
                                	</c:forEach>
                                	</ul>
                                </div>
                            </div> 
                        </div>
                    </div> 
                    </c:if>
                   <c:forEach items= "${cateResultObject}"  var = "searchFilter">
                	<div class="brand_attrs clearfix">
                        <div class="mc_l fl">${searchFilter.attrName }：</div>
                        <div class="mc_r clearfix fr">
                            <div class="v_tbs">
                                <div class="tabcon clearfix">
                                	<ul>
                                		<c:forEach items= '${searchFilter.attrValueList}'  var = "searchValue">
                                    		<li><a href="javascript:productList.searchFilter(${searchFilter.attrId },'${searchValue }');">${searchValue }</a></li>
                                    	</c:forEach>
                                    </ul>
                                </div>
                            </div> 
                           <!--  <div class="v-option clearfix">
                                <span class="o-multiple fr"><b></b>多选</span>
                            </div>   -->                
                        </div>
                    </div>
                	</c:forEach>
                	
                </div>    
                <!-- <div class="mb" id="advanced">
                    <div class="attr-extra"><div>更多选项（价格，机身材质）<b></b></div></div>
                </div> -->
          </div>
          
          <!--右边搜索条件4 排序搜索-->
          <div id="filter" >
            <div class="fore1 clearfix">
                    <dl class="order clearfix">
                    	<dt>排序：</dt>
                        <dd <c:if test="${orderFile==null}"> class="curr" </c:if> >
                            <a href="javascript:productList.sort('');">综合排序</a><b></b>
                        </dd>
                        <dd <c:if test="${orderFile=='buyCount'}"> class="curr" </c:if>>
                            <a href="javascript:productList.sort('buyCount');">销量</a><b></b>
                        </dd>
                        <dd <c:if test="${orderFile=='prices'}"> class="curr" </c:if>>
                            <a href="javascript:productList.sort('prices');">价格</a><b></b>
                        </dd>
                        <dd <c:if test="${orderFile=='commentsCount'}"> class='curr' </c:if> >
                            <a href="javascript:productList.sort('commentsCount');">评论数</a><b></b>
                        </dd>
                        <dd <c:if test="${orderFile=='lastModify'}"> class="curr" </c:if> >
                            <a href="javascript:productList.sort('lastModify');">新品</a><b></b>
                        </dd>
                    </dl>
                    <dl class="activity">
                        <dd>
                        </dd>
                    </dl>
                    <div class="pagin pagin-m">
	                   	<c:if test="${result.pages>1}">
                       		<span class="text" style=" margin-top: 0;"><i id="current">${result.currPageSize}</i>/${result.pages}</span>
                       		<c:if test="${result.currPageSize>1}">
                      			<a href="javascript:productList.prePage();" class="next" title="使用方向键右键也可翻到上一页哦！">上一页<b></b></a>
                      		</c:if>
                      		<c:if test="${result.currPageSize==1}">
                      			<span class="prev-disabled">上一页<b></b></span>
                      		</c:if>
                      		<c:if test="${result.currPageSize!=result.pages}">
                      			<a href="javascript:productList.nextPage();" class="next" title="使用方向键右键也可翻到下一页哦！">下一页<b></b></a>
                      		</c:if>
                      		<c:if test="${result.currPageSize==result.pages}">
                      			<span class="prev-disabled">上一页<b></b></span>
                      		</c:if>
                      	</c:if>
                    </div>
                    <span class="clr"></span>
                </div>            
            <div class="fore2 clearfix">
                <div class="type3 fl">
                    <div class="bd">
                        <a href="javascript:;"><b id="showStore"></b>仅显示有货</a>
                        <!-- <a href="javascript:;"><b></b>京东配送</a> -->
                        <!-- a href="javascript:;"><b></b>货到付款</a> -->
                    </div>
                </div>
                <dl class="stock-search fl">
                    <dt><input class="text-stock-search" type="text" id="sq" value="${sq }" placeholder="搜索"></dt>
                    <dd><input class="btn-stock-search" type="button" id="confirm"  value="确定"></dd>
                </dl>                                
            </div>
          </div>  
          
          
          <!--商品列表展示-->
          <div class="products clearfix">
          		<ul class="clearfix">
          		 <c:forEach items= "${result.rows}"  var = "good">
					<li>
						<div class="pro_list">
							<div class="pro_pic">
								<a href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}"><img src="${imagePath }${good.pic220x220}" alt="" ></a><!-- <div class="pro_bg"></div> -->
							</div>
							<div class="p_name">
								<a target="_blank" href="${path}/goodsManager/goodsIndex?goodsId=${good.goodsId}" title=" ${good.name}">
							     	${good.p22}
								</a>
							</div>
							<div class="p_price"><h3 class="red">￥${good.price }</h3></div>
							<div class="extra"><span class="star"><span class="star-white"><span id="star-1221370" class="star-yellow h5">&nbsp;</span></span></span> <a id="comment-1221370" target="_blank" href="javascript:;">已有${good.commentsCount}人评价</a></div>
							<div class="stocklist"><span class="st33">
								<c:if test="${good.store>0}">
									有货 
								</c:if>
								<c:if test="${good.store<=0}">
									无货 
								</c:if>
							</span></div>
							<div class="btns"><a class="btn-buy" href="javascript:productList.addCart(${good.goodsId});">加入购物车</a> 
							<a class="btn-coll" href="javascript:productList.addWishList(${good.goodsId});" >关注</a>
							<!-- <a class="btn-compare" href="${path}/carCompare/carCompare?ids=${goods.carId}"><span></span>对比</a> --></div>
						</div>
					</li>
	 			</c:forEach>	
                </ul>
          </div>   
          
          
          <!--商品列表翻页-->
          <div class="page">
         
            <div id="pagin-btm" class="pagin fr">
            	<c:if test="${result.pages>1}">
					<div id="Pagination" class="pagination"></div>
				</c:if>
				<input type="hidden" id="total" name="total" value="${result.total}"/>
				<input type="hidden" id="rows" name="rows" value="${result.pageSize}"/>
				<input type="hidden" id="pageIndex" value="${result.currPageSize}"/>
				<input type="hidden" value="${queryKey }" id="queryKey" />
				<input type="hidden" id="orderFile" value="${orderFile}"/>
				<input type="hidden" id="cateId" value="${cateId}"/>
				<input type="hidden" id="brandId" value="${brandId}"/>
				<input type="hidden" id="cId" value="${cId}"/>
				<input type="hidden" id="store" value="${store}"/>
	  		</div>
	  		
          </div>          
        </div>
    </div>
    
    <!--优惠券-->
    <div class="f_4 clearfix">
        <div class="stor clearfix">
            <h2 class="fl"><span>4F</span>优惠券</h2>
            <a class="fr" href="javascript:;">更多<span>></span></a>
        </div>
        <div class="f_4_in">
            <ul class=" clearfix">
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li style=" margin-right: 0;"><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
                <li style=" margin-right: 0;"><a href="javascript:;"><img src="${path}/resources/images/index/yo_08.jpg" width="382" height="160"></a></li>
            </ul>
        </div>
    </div>
</div>
<!--main end-->
<script type="text/javascript" src="${path}/resources/scripts/biz/productList.js"></script>
</body>
</html>