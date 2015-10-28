<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>汽车受损单列表</title>
<style type="text/css">
	.sellList_dl{ width: 950px; height: auto; padding: 0 10px; }
	.sellList_dl dt,.sellList_dl dd{ float: left; height: 34px; line-height: 34px; }
	.sellList_dl dt{ margin-left: 20px; }
	.sellList_dl dd input{ width: 108px; height: 32px; line-height: 32px; border: 1px solid #ddd; padding-left: 10px; }
	.sell_search{ width: 100px; height: 34px; line-height: 34px; background-color: #0093e7; color: #fff; font-size: 18px; font-family: "微软雅黑"; display: inline-block; text-align: center; }
	a.sell_search:hover{ color: #fff; background-color: #0283cd; }
	
	.sell_con{ width: 970px; height: auto; margin-top: 20px; }
	.sell_table{ color: #333; }
	.sell_table .firstTr{ background-color: #edf2f8; }
	.sell_table .firstTr th{ text-align: center; }
	.sell_table tr td{ text-align: center; }
	.sell_table .sell_img a{ display: inline-block; width: 68px; height: 68px; border: 1px solid #ddd; margin: 11px 0 0 10px; }
	.sell_table .sell_name{ width: auto; margin: 11px 0 0 10px; text-align: left; }
	.sell_table p{ color: #333; line-height: 20px; }
	.sell_table .sell_name span{ font-size: 12px; color: #aaa; line-height: 16px; display: inline-block;  }
	.sell_table .sell_name b{ font-style: normal; display: inline-block; width: 17px; height: 16px; background-color: #ccc; overflow: hidden; position: relative; top: 4px; }
	.sell_table .buy_opera a{ line-height: 20px; }
	
	.select_c{ width: 77px; height: 18px; border: 1px solid #abadb3; }
	.padding_left{ padding-left:10px;}
	.buy_opera{
	  width: 150px;
	  height: 109px;
	  text-align: center;
	  vertical-align: middle;
	  display: table-cell;
	}
	.buy_opera a{
	  display: block;
	  color: #0081cc;
	 margin-left: 0;
	}
</style>
<link type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" rel="stylesheet" />
<script type="text/javascript" src="${path}/resources/scripts/biz/common.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/pagination/jquery.pagination.js?v=${versionVal}"></script>
</head>
<body>

    <!--个人中心-->
    <div class="sell_main ml10 fl">
        <!--nav-->
    	<div class="sell_title">
        	<div class="title_border">
                <span>受损单列表</span>
            </div>
        </div>
        <c:choose>
        	<c:when test="${noPermission eq true}">
        		<span style="font-size: 14px;">对不起，您的经营范围未有<b>钣金喷漆</b></span>
        	</c:when>
        	<c:otherwise>
				<div class="buy_list clearfix">
			        <table width="970" border="1" style="border: 1px solid #ddd;" class="sell_table">
			        	<thead>
			                <tr class="firstTr">
			                    <th style=" width: 450px;">受损单信息</th>
			                    <th style=" width: 129px;">买家</th>
			                    <th style=" width: 118px;">下单时间</th>
			                    <th style=" width: 118px;">是否报价</th>
			                    <th>操作</th>
			                </tr>
			            </thead>
			            <tbody>
			            	<c:forEach items="${damageList.rows}" var="damage">
					            <tr>
					                <th colspan="6" class="padding_left">受损单编号：<a class="dd_color" href="javascript:void(0)" onClick="detail(this)">${damage.id}</a>
					                	<c:if test="${damage.source ne 'web'}">
					                		<span>(${damage.source} 客户端)</span>
					                	</c:if>
					                </th>
					            </tr>
					            <tr>
					                <td>
						                <c:forEach items="${damage.detailList}" var="detail" varStatus="status">
						                	<c:forTokens items="${detail.pic}" delims=";" var="picPath" begin="0" end="0">
							                    <div class="sell_img fl">
							                        <a href="javascript:void(0)"><img src="${imgUrl}${picPath}" style="width:68px; height:68px"></a>
							                    </div>
						                    </c:forTokens>
						                </c:forEach>
					                    <div class="sell_name fl">
					                    	<p>
						                    	<c:forEach items="${damage.detailList}" var="detail1" varStatus="status">
														${detail1.carPart.partName}
														<c:if test="${!status.last}">，</c:if>
												</c:forEach>
											</p>
						                </div>
					                </td>
					                <td>
					                    <p>${damage.pamAccount.loginName}</p>
					                </td>
					                <td class="sell_time">
					                	<fmt:formatDate value="${damage.createtime}" pattern="yyyy-MM-dd HH:mm:ss" var="newDate"/>
					                    <c:forTokens items="${newDate}" delims=" " var="value">
										 	<p>${value}</p>
										 </c:forTokens>
					                </td>
					                <c:choose>
										<c:when test="${damage.offered}">
											<td class="red">已报价</td>
							                <td>
							                    <div class="buy_opera">
							                   		<a href="../paintingManager/detail?id=${damage.id}&edit=0"><span name="aText">查看详情</span></a>
							                    </div>
							                </td>
										</c:when>	
										<c:when test="${!damage.offered && damage.passStatus == '1'}">
											<td>未报价</td>
							                <td>
							                    <div class="buy_opera">
							                    	<a href="../paintingManager/detail?id=${damage.id}&edit=1"><span name="aText">我要报价</span></a>
							                    </div>
							                </td>
										</c:when>	
										<c:when test="${!damage.offered && damage.passStatus == '3'}">
											<td>未报价</td>
							                <td>
							                    <div class="buy_opera">
							                   		<span name="aText">已关闭</span>
							                    </div>
							                </td>
										</c:when>
									</c:choose>
					            </tr>
				            </c:forEach>
			        	</tbody>
			   		</table>
				</div>
        		<div id="pagination" class="yoyoPagination"></div> 
        	</c:otherwise>
        </c:choose>
	</div>
	<script src="${path}/resources/scripts/biz/paintingMng/detailList.js?v=${versionVal}"></script>
	<script type="text/javascript">
	 totalRecords ="${damageList.total}";
	 accountId = ${sessionScope.accountId};
	 companyId = ${sessionScope.companyId};
	</script>
</body>
</html>