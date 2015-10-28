<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title>钣金喷漆</title>
	<!-- 是否显示图片工具栏 -->
    <meta http-equiv="imagetoolbar" content="no"/>
    <!-- 全屏显示 WebApp，隐藏 safari 导航栏以及工具栏 -->
    <meta name="apple-mobile-web-app-capable" content="yes"/>
	<link href="${path}/resources/styles/base.css" type="text/css" rel="stylesheet">
	<link href="${path}/resources/styles/webUploader/webuploader.css" type="text/css" rel="stylesheet">
	<link href="${path}/resources/styles/painting/painting.css" type="text/css" rel="stylesheet">
	<script src="${path}/resources/scripts/biz/common.js"></script>
	<script type="text/javascript"  src="${path}/resources/scripts/webUploader/webuploader.min.js"></script>
	<script src="${path}/resources/scripts/biz/painting/paintingUtil.js"></script>
	<script src="${path}/resources/scripts/biz/painting/painting.js"></script>
</head>
<body>
    <!-- 中间内容区 -->
    <div class="content clearfix">
		<div class="leftContent">
            <div class="top">钣金喷漆一一事故保修服务选项</div>
			<div class="leftContent_1f">
				<div class="note">
		        	<h2>使用小贴士：</h2>
		        	<p>1、请从左侧选择您的受损部位，点击添加到保修单，即可上传受损部位图片并生成受损清单。<br/></p>
		        	<p>2、我们会在尽快响应，并提供报价。<br/></p>
				</div>
				<!-- <div class="choose">
					<form >
                        <div>
    						<label>* </label>是否要走保险：
    						<input type="radio" value="1" name="insurance"/><span>我要走保险（返利10%）</span>
    						<input type="radio" checked value="0" name="insurance"/><span>不走保险</span>
                        </div>
                        <div id='selectInsurance' class="hide">
                            <div>
                                <label>* </label>定损情况说明：
                                <input id="inputPrice" class="input_moneny" type="number" min="0" step="0.1" value="请输入定损价格  "/>&nbsp元
                            </div>
                            <div>
                                <div class="up_pic"><a id="uploadPic" href="javascript:void(0)">上传定损单照片</a></div>
                                <div class="tip">
                                    <p><span>注；</span>1、图片格式仅限于jpg、png、gif;<br/>
                                    2、图片不大于2M;</p>
                                </div>
                            </div>
                        </div>
					</form>
				</div> -->
			</div>
			<div class="leftContent_2f">
				<div class="chooseList">
					<span class="headline">可选部位列表</span>
					<table id='carParts'>
						<c:if test="${supParts != null}" >
							<c:forEach items="${supParts}" var="supPart">
								<tr>
									<td class="left_td">${supPart.partName}</td>
									<td class="right_td">
										<div class="car_parts">
											<c:if test="${subParts != null}" >
												<c:forEach items="${subParts}" var="subPart">
													<c:if test="${subPart.parentId == supPart.id}">
														<div class="car_part"><span>${subPart.partName}</span><a class="add" href="javascript:void(0)">+ 添加</a><input type="hidden" partId = "${subPart.id}"/></div>
													</c:if>
												</c:forEach>
											</c:if>
										</div>
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</div>
				<div class="choosedKList">
					<span class="headline">您选择的受损部位<span>（每个部位最多上传3张图片）</span></span>
                    <form id="chooseForm" class="chooseForm">
                        <div class="cell clearfix" id="cell1">
                            <div class="left"><span class="num" isNull="true">1</span>、</div>
                            <div class="right">
                                <div class="innerBox">
                                    <div class="title">点击左侧受损部位添加</div>
                                    <div class="picture">
									    <!--用来存放item-->
									    <div id="fileList_cell1" node-type="data_list" class="uploader-list">
									    	<div title="uploaderDiv" class="file-item thumbnail">
									    		<a href="javascript:void(0)" onClick="uploadFile(this)">
										    		<div class="pickImg">
														<img src="${path}/resources/images/add_img.png">
													</div>
												</a>
											</div>
									    </div>
									    <div id="filePicker_cell1" node-type="data_picker">选择图片</div>
									</div>
                                </div>
                                <div class="innerBox hide">
                                    <div class="title2">请简单描述您的问题</div>
                                    <textarea  name="textarea" class="question"></textarea>
                                </div>
                            </div>
                            <input _partid="" type="hidden" class="_partId">
                        </div>
                    	<div id="submit" class="submit hide">
                    		确定，获得修复以上问题报价
                    	</div>
                    </form>
				</div>
			</div>
		</div>
		<div class="rightContent hide">
			<div class="topProductList gray_border">
				<div class="content_title"><span>热销排行榜</span></div>
				<div id="secondeTitle" class="second_title">
					<div class="selected"><a hrf="#">销售量</a></div>
					<div class="unselect"><a hrf="#">收藏量</a></div>
				</div>
				<div class="goods_list">
					<ul>
						<li>
							<span>1</span>
							<div class="curr">
								<div class="img"><a href="#"><img src="${path}/resources/images/painting/salTop.png"></a></div>
								<div class="name"><a title="" href="">北京极光汽车贴eeeeeeeeeeee</a></div>
								<div class="price"><strong>￥100</strong></div>
								<div class="evaluate"><a href="">1000条评论</a></div>
							</div>
						</li>
						<li>
							<span>2</span>
							<div class="curr">
								<div class="img"><a href="#"><img src="${path}/resources/images/painting/salTop.png"></a></div>
								<div class="name"><a title="" href="">北京极光汽车贴eeeeeeeeeeee</a></div>
								<div class="price"><strong>￥100</strong></div>
								<div class="evaluate"><a href="">1000条评论</a></div>
							</div>
						</li>
						<li>
							<span>3</span>
							<div class="curr">
								<div class="img"><a href="#"><img src="${path}/resources/images/painting/salTop.png"></a></div>
								<div class="name"><a title="" href="">北京极光汽车贴eeeeeeeeeeee</a></div>
								<div class="price"><strong>￥100</strong></div>
								<div class="evaluate"><a href="">1000条评论</a></div>
							</div>
						</li>
						<li>
							<span>4</span>
							<div class="curr">
								<div class="img"><a href="#"><img src="${path}/resources/images/painting/salTop.png"></a></div>
								<div class="name"><a title="" href="">北京极光汽车贴eeeeeeeeeeee</a></div>
								<div class="price"><strong>￥100</strong></div>
								<div class="evaluate"><a href="">1000条评论</a></div>
							</div>
						</li>
						<li>
							<span>5</span>
							<div class="curr">
								<div class="img"><a href="#"><img src="${path}/resources/images/painting/salTop.png"></a></div>
								<div class="name"><a title="" href="">北京极光汽车贴eeeeeeeeeeee</a></div>
								<div class="price"><strong>￥100</strong></div>
								<div class="evaluate"><a href="">1000条评论</a></div>
							</div>
						</li>
					</ul>
				</div>
				<div class="more">
					<a href="">查看更多商品 ></a>
				</div>
			</div>
			<div class="brand gray_border">
				<div class="content_title"><span>热销排行榜</span></div>
				<div>
					<ul>
						<li class="brand_list">
							<a href="javascript:void(0)">
								<img class="brandPic" src="${path}/resources/images/painting/li01.png">
									<div class="chinese">荣威</div>
								</img>
								<span class="arrow">></span>
							</a>
						</li>
						<li class="brand_list">
							<a href="javascript:void(0)">
								<img class="brandPic" src="${path}/resources/images/painting/li01.png">
									<div class="chinese">荣威</div>
								</img>
								<span class="arrow">></span>
							</a>
						</li>
						<li class="brand_list">
							<a href="javascript:void(0)">
								<img class="brandPic" src="${path}/resources/images/painting/li01.png">
									<div class="chinese">荣威</div>
								</img>
								<span class="arrow">></span>
							</a>
						</li>
						<li class="brand_list">
							<a href="javascript:void(0)">
								<img class="brandPic" src="${path}/resources/images/painting/li01.png">
									<div class="chinese">一汽大众</div>
								</img>
								<span class="arrow">></span>
							</a>
						</li>						
					</ul>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		 memberId = "${sessionScope.memberId}";
		 accountId = "${sessionScope.accountId}";
		 accountType = "${sessionScope.accountType}";
	</script>
</body>
</html>