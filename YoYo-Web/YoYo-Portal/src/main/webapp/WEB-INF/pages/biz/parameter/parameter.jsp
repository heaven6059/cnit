<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>车型对比</title>
<!--css文件-->
<script type="text/javascript" src="${path}/resources/scripts/jquery/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/cookie/jquery.cookie.js"></script>
<link type="text/css" href="${path}/resources/styles/base.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/common.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" href="${path}/resources/styles/parameter.css?v=${versionVal}" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/parameter/parameter.js?v=${versionVal}"></script>
<style type="text/css">
.choice-pop {
    margin-left: -1px;
    padding-bottom: 2px;
    position: fixed;
    top: 0;
    z-index: 300;
}
.fixed-name-pop, .fixed-ul-pop, .choice-pop {
    background: rgba(0, 0, 0, 0) url("http://www.autohome.com.cn/images/shadow_bg.png") repeat scroll 0 0;
}
</style>
</head>
<body>
<!--面包屑导航-->
<div class="root_nav">
	<div class="root_navin">
    	<span><a href="javascript:;">首页</a></span>&nbsp;>&nbsp;
    	<span><a href="javascript:;">车型参数配置对比</a></span>
    </div>
</div>
<div class="row" id="content">
    <div id="navScrollLeft" class="followcon fl" style="position: fixed; top: 622px; display: block; left: 0px; ">
        <ul class="folul">
        	<c:forEach items="${dtos}" var="dto" varStatus="status">
            <li <c:if test="${status.first}">class="top"</c:if>>
                <a <c:if test="${status.first}">class="active"</c:if> href="javascript:void(0);") data-target="target${dto.catalogId}">${dto.catalogName}</a>
            </li>
            </c:forEach>
        </ul>
    </div> 
    <div class="column fr">
    	<div class="title">
        	<div class="title-content">
            
            	<!--车型信息-->
                <div>
                    <table class="tablechoice">
                        <tr>
                            <td class="title">
                                <div>
                                    <p class="title-name red">车型图片</p>
                                    <p>配置状况</p>
                                    <p class="font-grey999">●&nbsp;标配</p>
                                    <p class="font-grey999">○&nbsp;选配</p>
                                    <p class="font-grey999">&mdash;&nbsp;无</p>
                                </div>
                                <div class="title-option">
                                    <p>
                                        <label><input type="checkbox" class="checkbox" id="highlight" name="chkSame">高亮显示差异参数</label>
                                    </p>
                                    <p>
                                        <label><input type="checkbox" class="checkbox" id="need_hide" name="chkBest">隐藏相同项</label>
                                    </p>
                                </div>
                            </td>
                            <c:forEach var="car" items="${cars}" varStatus="status">
                            <td class="text js-text-select js-items-select " data-index="0">
                                <ul class="text-ul">
                                    <li class="text-ul-pic">
                                        <a href="${portalPath}/goodsManager/goodsIndex?goodsId=${car.goodsId}" target="_blank">
                                            <img height="90" width="120" src="${imagePath}/${car.midPic}" title="${car.carName}"/>                                            
                                        </a>
                                    </li>
                                    <li class="text-ul-btncont js-li-btncont text-right">
                                        <a class="js-btn-left btn btn-small" href="javascript:;" <c:if test="${status.index eq 0}">style="display:none"</c:if>>&lt;&lt;</a>
                                        <a class="js-btn-delete btn btn-small" href="javascript:;" data-goods="${car.goodsId}">删除</a>
                                        <a class="js-btn-right btn btn-small" href="javascript:;" <c:if test="${status.index eq 3}">style="display:none"</c:if>>&gt;&gt;</a>
                                    </li>
                                </ul>
                            </td>
                            </c:forEach>
                            <c:if test="${fn:length(cars)<4}">
                            <c:forEach begin="1" end="${4-fn:length(cars)}" varStatus="status">
                            <td class="text js-text-select js-items-select <c:if test="${status.last}">border-r-no</c:if>" data-index="3">
                                <ul class="text-ul">
                                    <li class="text-ul-pic"> 
                                        <img height="90" width="120" src="../resources/images/index/duibinopic.gif" title="" alt=""/>
                                    </li>
                                    <li class="text-ul-btncont js-li-btncont text-left">                                    	
                                        <a class="js-btn-left btn btn-small btn-disabled" href="javascript:;" <c:if test="${status.first}">style="display:none"</c:if>>&lt;&lt;</a>
                                        <a class="js-btn-right btn btn-small btn-disabled" href="javascript:;" <c:if test="${status.last}">style="display:none"</c:if>>&gt;&gt;</a>
                                    </li>                                    
                                </ul>
                            </td>
                            </c:forEach>
                            </c:if>
                        </tr>
                    </table>
                </div>
                
            	<!--车型图片-->
            	<div id="divHeader" class="fn-hide" style="display: block;">
					<div class="choice-fixed">
		                <table id="tbSpecInfo" class="tableinfo">
		                    <tr>
		                        <th class="title redtitle">
		                           	 车型信息
		                        </th>
		                        <c:forEach var="car" items="${cars}">
		                        <td class="text name">
		                            <a href="${portalPath}/goodsManager/goodsIndex?goodsId=${car.goodsId}" target="_blank">${car.carName}</a>
		                        </td>
		                        </c:forEach>
								<c:if test="${fn:length(cars)<4}">
								<c:forEach begin="1" end="${4-fn:length(cars)}" varStatus="status">                  
		                       	 	<td class="text name">&nbsp;
		                        	</td>
		                        </c:forEach>
		                        </c:if>
		                    </tr>
		                    <tr>
		                        <th>市场价</th>
		                        <c:forEach var="car" items="${cars}">
		                        <td class="text name">
		                            <a href="javascript:;">￥${car.mktPrice}</a>
		                        </td>
		                        </c:forEach>
								<c:if test="${fn:length(cars)<4}">
								<c:forEach begin="1" end="${4-fn:length(cars)}" varStatus="status">                  
		                       	 	<td class="text name">&nbsp;</td>
		                        </c:forEach>
		                        </c:if>
		                    </tr>
		                    <tr>
		                        <th>销售价</th>
		                        <c:forEach var="car" items="${cars}">
		                        <td class="text name">
		                            <a href="javascript:;">￥${car.price}</a>
		                        </td>
		                        </c:forEach>
								<c:if test="${fn:length(cars)<4}">
								<c:forEach begin="1" end="${4-fn:length(cars)}" varStatus="status">                  
		                       	 	<td class="text name">&nbsp;</td>
		                        </c:forEach>
		                        </c:if>
		                    </tr>
		                    <tr>
		                        <th>
		                          	  口碑综合评分
		                        </th>
		                        <td>
		                            <div id="divKoubei22496" class="scorecont">
		                                -
		                            </div>
		                        </td>
		                        <td>
		                            <div id="divKoubei19738" class="scorecont">
		                                -
		                            </div>
		                        </td>
		                        <td>
		                            <div id="divKoubei19750" class="scorecont">
		                                -
		                            </div>
		                        </td>
		                        <td>&nbsp;
		                        </td>
		                    </tr>
		                </table>
	                </div>
                </div>
			<c:forEach items="${dtos}" var="dto">
                <div class="js-title genre-title" id="target${dto.catalogId}" name="target${dto.catalogId}" data-title="基本参数">
                    <i class="icon10 icon10-pack"></i>
                    <h3 id="floor1">${dto.catalogName}</h3>
                </div>
                
                <table class="js-titems tableinfo">
                	<c:forEach items="${dto.carDatas}" var="carData">
                    <tr>
                        <th class="title">
                            <a href="javascript:;" target="_blank">${carData.displayName}</a>
                        </th>
                        <c:forEach items="${carData.compares}" varStatus="status" var="compare">
                        <td class="text">
                        	${compare.carDataValue}  
                        </td>
                        </c:forEach>
						
						<c:if test="${fn:length(carData.compares)<4}">
						<c:forEach begin="1" end="${4-fn:length(carData.compares)}" varStatus="status" step="1">
						<td class="text">
							<c:if test="${fn:length(carData.compares)+(status.index)<=fn:length(cars)}">-</c:if>
						</td>
						</c:forEach>
						</c:if>
                    </tr>
                    </c:forEach>
                </table>
			</c:forEach>
        </div>
    </div>
    </div>
</div>
<div class="clearfix"></div>
<script type="text/javascript">
var cars_count=${fn:length(cars)};
</script>
<form action="${path}/carCompare/carCompare" id="sub">
	<input type="hidden" id="car_id" name="ids"/>
</form>
</body>
</html>
