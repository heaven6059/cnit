<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<title>车型数据项列表</title>
	<link rel="stylesheet" type="text/css" href="${path}/resources/styles/good/parameter.css?v=${versionVal}">
</head>
<body>
	
	<script type="text/javascript" src="${path}/resources/scripts/biz/car/carConfiginfoIndex.js?v=${versionVal}"></script>
	<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
	
    
	<%-- <div id="car_configinfo_toolba">
		<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="window.parent.addTabs('/carData/index','车型数据项')"> 添加数据项</a> 
		<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" iconCls="icon-search" onclick="openAdvaceQuery('advance_search_car_data')">高级筛选</a>
		<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" onclick="openCarDataDialog()">保存</a>
		<a href="#" style="margin-left: 50px;" class="easyui-linkbutton" onclick="editCarconfig()">编辑配置项的值</a>
		<input type="hidden" name="carId" id="carId" value="${carId}">
	</div> --%>
	<!-- <table id="car_configinfo_datagrid" ></table> -->
	<%-- <form id="car_configinfo_form" class="easyui-form">
		<input type="hidden" id="carId" name="carId" value="${carId}" />
	    <table style="height: 550px; width: 620px;" >
	      	<thead align="left">
	       		<tr>
	         		<th>数据项名称</th>
	         		<th>数据类型</th>
	         		<th>数据项类别</th>
	         		<th>数据项值</th>
	       		</tr>
	     	</thead>
	     	<tbody>
	     		<c:forEach items="${carData}" var="item" varStatus="num">
					<tr>
						<td>
							<input type="hidden" id="dataId" name="dataId" value="${item.dataId}" />
							${item.displayName}
							<input type="hidden" id="displayName" name="displayName" value="${item.displayName}" />
						</td>
						<td>
							<input type="hidden" id="dataType" name="dataType" value="${item.dataType}" />
							<c:choose>
								<c:when test="${item.dataType == 'INT'}">
									整型
								</c:when>
								<c:when test="${item.dataType == 'BOL'}">
									布尔型
								</c:when>
								<c:when test="${item.dataType == 'DEC'}">
									数值型
								</c:when>
								<c:when test="${item.dataType == 'STR'}">
									文本
								</c:when>
								<c:when test="${item.dataType == 'LIST'}">
									列表
								</c:when>
								<c:otherwise>
									数据错误
								</c:otherwise>
							</c:choose>
						</td>
						<td>${item.catalogName}</td>
						<td>
							<c:choose>
								<c:when test="${item.dataType == 'BOL'}">
									<input type="checkbox" name="configValue" id="configValue${num.index}" value="1" />
								</c:when>
								<c:when test="${item.dataType == 'LIST'}">
									<c:choose>
										<c:when test="${item.listValue != null || item.listValue != ''}">
											<c:set var="string2" value="${fn:split(item.listValue, '|')}" />
											<input name="configValue"  type="hidden" />
											<select id="listvalue${num.index}" name="listvalue" style="width: 172px;">
												<option value="-1">--请选择--
												<c:forEach items="${string2}" var="name">
													<c:choose>
														<c:when test="${name == item.dataValue}">
															<option value="${name}" selected="selected">${name}
														</c:when>
														<c:otherwise>
															<option value="${name}">${name}
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</select>									
										</c:when>
										<c:otherwise>
											<input name="configValue"  type="text" value="${item.dataValue}" />
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${item.dataType == 'INT'}">
									<input name="configValue" validType="integ" class="tab_input easyui-validatebox" style="width: 172px;" type="text" value="${item.dataValue}" />
								</c:when>
								<c:when test="${item.dataType == 'DEC'}">
									<input name="configValue" validType="mone" class="tab_input easyui-validatebox" style="width: 172px;" type="text" value="${item.dataValue}" />
								</c:when>
								<c:otherwise>
									<input name="configValue" validType="loginName" class="tab_input easyui-validatebox" style="width: 172px;" type="text" value="${item.dataValue}" />
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</c:forEach>
	     	</tbody>
	    </table>
	    
	    <!-- <input type="submit" value="保存"  name="save"/> -->
	</form> --%>
	<%--<c:choose>
		 <c:when test="${isconfig == 1}">
			<a class="easyui-linkbutton" id="update-car-config-btn" icon="icon-save" onclick="updateCarData()">更新</a>
		</c:when>
		<c:otherwise> --%>
			
		<%-- </c:otherwise>
	</c:choose> --%>
	
	<form id="car_configinfo_form" class="easyui-form">
	<input type="hidden" id="carId" name="carId" value="${carId}" />
	
	<!--参数配置-->
            <div id="par_select" class="g_cont clearfix"  ">
                <div class="row clearfix fl" id="content" style=" width: 495px;float: left;margin-left: 10px; margin-top: 10px;">
                    <div class="column fr" style=" width: 365px; ">
                        <div class="title">
                            <div class="title-content" style=" padding-top: 0; padding-left: 1px; border-top: 0; ">
                            <c:set var="numk" value="0"></c:set>
                                <c:forEach items="${carData}" var="item" >
                                	<div class="js-title genre-title" data-title="${item.catalogName}">
	                                    <i class="icon10 icon10-pack"></i>
	                                    <h3 id="floor1">${item.catalogName}</h3>
	                                </div>
	                                <c:forEach items="${item.carDataDTOs}" var="itemdto" varStatus="num">
	                                
	                                <%-- <c:out value="${numk}"></c:out> --%>
	                                	<table class="js-titems tableinfo">
		                                    <tbody>
			                                    <tr>
			                                        <th class="title">
			                                            <a href="javascript:;" target="_blank">${itemdto.displayName}</a>
			                                            <input type="hidden" id="dataId" name="dataId" value="${itemdto.dataId}" />
														<input type="hidden" id="displayName" name="displayName" value="${itemdto.displayName}" />
														<input type="hidden" id="dataType" name="dataType" value="${itemdto.dataType}" />
			                                        </th>
			                                        <td class="text" style="width: 170px;" >
		                                           		 <c:choose>
															<c:when test="${itemdto.dataType == 'BOL'}">
																<c:choose>
																	<c:when test="${itemdto.dataValue == '1'}">
																			<input type="checkbox" name="configValue" id="configValue${numk}" style="width: 180px;" checked="checked" value="1" />								
																	</c:when>
																	<c:otherwise>
																		<input type="checkbox" name="configValue" id="configValue${numk}" style="width: 180px;" value="0" />
																	</c:otherwise>
																</c:choose>
															</c:when>
															<c:when test="${itemdto.dataType == 'LIST'}">
																<c:choose>
																	<c:when test="${itemdto.listValue != null && itemdto.listValue != ''}">
																		<c:set var="string2" value="${fn:split(itemdto.listValue, '|')}" />
																		<input name="configValue"  type="hidden" />
																		<select id="listvalue${numk}" name="listvalue" style="width: 182px;">
																			<option value="-1">--请选择--
																			<c:forEach items="${string2}" var="name">
																				<c:choose>
																					<c:when test="${name == itemdto.dataValue}">
																						<option value="${name}" selected="selected">${name}
																					</c:when>
																					<c:otherwise>
																						<option value="${name}">${name}
																					</c:otherwise>
																				</c:choose>
																			</c:forEach>
																		</select>									
																	</c:when>
																	<c:otherwise>
																		<input name="configValue"  type="text" value="${itemdto.dataValue}" />
																	</c:otherwise>
																</c:choose>
															</c:when>
															<c:when test="${itemdto.dataType == 'INT'}">
																<input name="configValue" validType="integ" class="tab_input easyui-validatebox" style="width: 180px;" type="text" value="${itemdto.dataValue}" />
															</c:when>
															<c:when test="${itemdto.dataType == 'DEC'}">
																<input name="configValue" validType="mone" class="tab_input easyui-validatebox" style="width: 180px;" type="text" value="${itemdto.dataValue}" />
															</c:when>
															<c:otherwise>
																<input name="configValue"  class="tab_input easyui-validatebox" style="width: 180px;" type="text" value="${itemdto.dataValue}" />
															</c:otherwise>
														</c:choose>
			                                        </td>
			                                    </tr>
		                                	</tbody>
		                                </table>
		                                <c:set var="numk" value="${numk+1}"></c:set>
	                                </c:forEach>
                                </c:forEach>
                                
                          </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <a class="easyui-linkbutton" style="height: 40px; width: 70px; margin-left: 140px; margin-top: 30px;" id="add-car-config-btn" icon="icon-save" onclick="saveCarData1()">保存</a>
</body>
</html>
