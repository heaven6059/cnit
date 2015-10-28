<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<script type="text/javascript">
	var _path = "${path}";
</script>
<script type="text/javascript" src="${path}/resources/scripts/biz/maintain/setp2.js?v=${versionVal}"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/maintain/maintain_common220150527.css?v=${versionVal}">
<link rel="stylesheet" type="text/css" 	href="${path}/resources/styles/maintain/maintain_selfcar20150228.css?v=${versionVal}">
</head>
<body>
	<div id="content">
		<div class="titleBox">
			<h2>
				<b>自助保养服务</b>[ 选择保养项目和商品 ]
			</h2>
			<ol class="setp2">
				<li>查询项目</li>
				<li class="cur">确认项目</li>
				<li>选购商品</li>
				<li>加入购物车</li>
			</ol>
		</div>
		<div class="c"></div>
		<div class="models1">
			<h3>
				<b></b>
			</h3>
		</div>
		<div class="modelsSelect" id="div_AllAutoModelParam"></div>
		<input type="hidden" value="55202|2011" id="txt_MyMSubIdYear">
		<div id="projectpanel" class="listBox">
			<div id="partsListLeft" class="partsList">
				<h4>保养项目查询结果：</h4>
				<div class="info">
					（根据原厂保养标准以及爱车使用情况，我们针对你的爱车保养给出如下建议） <span>当前行驶里程：1公里；新车上路时间：2011年12月</span>
				</div>
				<div class="tableSetp2">
					<h6>
						<b>1</b>需要&nbsp;更换&nbsp;的保养项目
					</h6>
					<div style="display: none" class="layer">
						<a style="background: none; cursor: pointer" class="forks hidefocus" onclick="iKnow()" href="javascript:"></a>
						<a class="closebut hidefocus" style="background: none; cursor: pointer" onclick="iKnow()" href="javascript:"></a>
					</div>
					<div id="MType1_0">
						<table cellspacing="0" cellpadding="1" border="0"
							class="ProjectTable">
							<tbody>
								<tr height="40">
									<th width="40">选取</th>
									<th width="410" style="width: 100px;">保养项目</th>
								</tr>
							</tbody>
						</table>
					</div>
					<c:forEach var="carMaintain" items="${carMaintain}" varStatus="status">
					<div class="delist  ">
						<dl>
							<dt>
								<b> (${status.index+1}).${carMaintain.catName} </b>
							</dt>
							<dd>
								<a class="addmainten hidefocus" data-orgs="${carMaintain.catName}" href="javascript:;">添加保养&gt;&gt;</a>
							</dd>
						</dl>
						<div id="MType1_1">
							<table cellspacing="0" cellpadding="1" border="0" class="ProjectTable" style="text-align: center;">
								<tbody>
									<c:forEach var="officialMaintain" items="${carMaintain.maintainAccessorys}">
										<tr>
											<td style="width: 40px; text-align: center;">
											<input type="checkbox" data-orgs="${officialMaintain.catalogName}" data-key="${officialMaintain.id}" value="${officialMaintain.catalogId}" class="cheproject" />
											<td align="left" class="textl" style="width: 410px;" colspan="2">${officialMaintain.catalogName}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="c"></div>
						<div style="display: none" class="morepack">
							<a class="morepacka hidefocus" href="javascript:;"></a>
						</div>
					</div>
					</c:forEach>
				</div>

				<div style="z-index: 5;display:block;" name="" id="dd_tablestep2mt" class="tableSetp2 mT">
                       <h6>
                           <b>2</b>请选择预约门店
                       </h6>
                </div>

				<input type="hidden" value="0" id="amsid" name="amsid"> 
				<input type="hidden" value="2011" id="year" name="year"> 
				<input type="hidden" value="1" id="curmileage" name="curmileage">
				<input type="hidden" value="201112" id="firsttime" name="firsttime">
			</div>

			<!--projectleft-->
			<div id="rightproject" class="projectright"
				style="float: none; position: absolute; right: 0px; top: 0px;">
				<div id="ProjectListbox">
					<div class="moreList" id="bytable">
						<h5>爱车要保养的项目清单：</h5>
						<div class="info">（从左侧选择要保养的项目加入到项目清单中）</div>
						<input type="hidden" value="7,9,8,10,12" id="SelectedProjectId" name="SelectedProjectId">
						<table cellspacing="0" cellpadding="1" border="0" class="sList">
							<tbody>
								<tr>
									<th width="144">保养类型</th>
									<th width="150">保养项目</th>
									<th width="50">操作</th>
								</tr>								
							</tbody>
						</table>
						<div class="listbut">
							<form method="get" id="form1" action="http://www.yangche51.com/baoyang/step3.html">
								<a style="*line-height: 14px; background: none; color: #246ba7;" class="backtop hidefocus" href="http://www.yangche51.com/baoyang/">返回上一步</a> 
								<a style="*line-height: 14px;" class="nextstep hidefocus" href="###"><span>下一步，选购保养商品</span></a>
								<div class="c"></div>
							</form>
						</div>
					</div>
					<div class="c"></div>
				</div>
				<div id="Advert_100572" style="margin-bottom: 10px;"></div>
				<div id="Advert_100573" style="margin-bottom: 10px;"></div>
				<div style="margin-top: 5px;" id="Advert_100467"></div>
			</div>
			<input type="hidden" value="0" id="amsid" name="amsid" /> 
			<input type="hidden" value="2011" id="year" name="year" /> 
			<input type="hidden" value="1" id="curmileage" name="curmileage" /> 
			<input type="hidden" value="201112" id="firsttime" name="firsttime" />
			<input type="hidden" value="" id="txt_MProjectIds" name="txt_MProjectIds" />
			<!--projectright-->
			<div class="c"></div>
		</div>
	</div>
</body>
</html>