<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@page import="com.cnit.yoyo.util.Configuration" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	application.setAttribute("imagesUrl", Configuration.getInstance().getConfigValue("images.url"));
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品发布</title>
<link type="text/css"	href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}"	rel="stylesheet" />
<link type="text/css" rel="stylesheet"	href="${path }/resources/styles/shopEnter/shopRegister.css?v=${versionVal}">
<link type="text/css" rel="stylesheet"	href="${path }/resources/styles/categoryDialog.css">
<script type="text/javascript">
	var _imagesUrl = '${imagesUrl}';
</script>
<!-- 引用控制层插件样式 -->
	<link rel="stylesheet" href="${path}/resources/styles/zyUpload.css"	type="text/css">
	<!-- 引用核心层插件 -->
	<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
	<!-- 引用控制层插件 -->
	<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
	<!-- 自动搜索插件 -->
	<script type="text/javascript"	src="${path}/resources/scripts/biz/categoryDialog.js?v=${versionVal}"></script>

	<script type="text/javascript"	src="${path}/resources/scripts/select2/select2.min.js"></script>
	<link type="text/css"	href="${path}/resources/styles/select2/select2.min.css"	rel="stylesheet" />
<%--	<link type="text/css" href="${path}/resources/scripts/cleditor/jquery.cleditor.css"	rel="stylesheet" />
 	<script type="text/javascript"	src="${path}/resources/scripts/cleditor/jquery.cleditor.min.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/cleditor/jquery.cleditor.xhtml.min.js"></script> --%>
	<link href="${path}/resources/scripts/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
	<script type="text/javascript" charset="utf-8" src="${path}/resources/scripts/umeditor/umeditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${path}/resources/scripts/umeditor/umeditor.min.js"></script>
	<script type="text/javascript" src="${path}/resources/scripts/umeditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/jquery.comdropdown.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/jquery.addaccessory.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/biz/goodsMng/goodsPublishMain.js?v=${versionVal}"></script>
	<script type="text/javascript"	src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
	
	<link type="text/css" href="${path}/resources/styles/goodsMng/goodSpecEditIndex.css?v=${versionVal}" rel="stylesheet" />
	<link rel="stylesheet" href="${path}/resources/styles/jquery-ui.css"	type="text/css" />

	<script type="text/javascript"	src="${path}/resources/scripts/jquery/jquery-ui.js"></script>
	<style type="text/css">
		  .ui-autocomplete {
		    max-height: 200px;
		    overflow-y: auto;
		    /* 防止水平滚动条 */
		    overflow-x: hidden;
		  }
		  /* IE 6 不支持 max-height
		   * 我们使用 height 代替，但是这会强制菜单总是显示为那个高度
		   */
		  * html .ui-autocomplete {
		    height: 200px;
		  }
		  
		    
		  
	</style>
	
</head>
<body>

	<div class="shop_manager_right ">
		<div class="title ">发布商品</div>
		<input type="hidden" value="${parentCatId}" id="parentCateId">
		<input type="hidden" value="${catType }" id="catType">
	
			<div id="gEditor-Body">
				<div class="title_fb ">1. 商品基本信息</div>
				<div class="FormWrap" style="background: none">
				<table width="100%" border="0" cellspacing="0" cellpadding="0"	class="liststyle data width1" style="border: none" id="category_table">
					<tr>
						<th><em><font color="red">*</font></em>商品分类：</th>
						<td>
							<div class="object-select clearfix" id="gEditor-GCat-category">
								<div class="label" id="labelCategory">请选择</div>
								<div class="handle">&nbsp;</div>
							</div>
						</td>
					</tr>
					
					<tr style="display: none;" id="car_category" class="category_type">
						<th>汽车车型：</th>
						<td><select id="factoryId" name="factoryId" style="width: 110px;"></select>
							 <select id="carDeptId" 	name="carDeptId" style="width: 110px;"></select> 
							 <select id="carYearValue" name="carYearValue" style="width: 110px;"></select>
							<select id="carTypeData" name="carTypeData" style="width: 120px;"></select></td>
					</tr>
					<tr style="display: none;" id="car_search">
						<th>车型：</th>
						<td><div>
							<input id="autocomplete" placeholder="请输入车型名称、快速搜索" style="width: 60%">
						</div>
						</td>
					</tr>
					<tr style="display: none;" id="acc_category" class="category_type">
						<th>汽车配件类型：</th>
						<td><select id="carAccType"  style="width: 100px;"></select>
							<select id="accId" name="accId" style="width: 100px;"></select></td>
					</tr>
					

				</table>
				</div>
				
				<c:if test="${catType!='999'}">
					<div class="FormWrap" style="background: none;line-height: 3;">
						<div id="gEditor-appointment-panel">
							<table style="border: none; width: 50%;">
								<tr>
									<!-- <th style="text-align: right;">预约时间开关：</th> -->
									<td>
									预约开关：
										<button class="btn closeappoint" onclick="appointclose()"
											type="button">
											<span><span>关闭</span></span>
										</button>
										<button class="btn openappoint" onclick="appointopen()"
											type="button" style="display: none;">
											<span><span>开启</span></span>
										</button>
									</td>
								</tr>
							</table>
							
							
							<div class="appoint-panel" id="appoint-window">
								<div class="clearfixs">
									<div class="appoint-value">
										<div class="appoint-toolbar">预约区间：
											<select id="appointType" name="appointType" style="width: 150px;">
												<!-- <option value="1" selected="selected">工作日</option>
												<option value="2">周末</option>
												<option value="3">节假日</option> -->
											</select>							
										</div>
										<div class="appoint-value-table" style="display: none;">
											<table>
												<tr>
													<th>开始时间：</th>
													<td>
														<input size="10" class="easyui-datebox cal" style="width: 150px; height: 30px;" required="required" maxlength="30" data-options="editable:false,onSelect:onSelect"
																type="text" name="fromTime" id="fromTime">
													</td>
												</tr>
												<tr>
													<th>结束时间：</th>
													<td>
														<input size="10" id="toTime" 
														class="easyui-datebox cal" maxlength="30" style="width: 150px; height: 30px;" type="text" data-options="editable:false,onSelect:onSelect"
														required="true" name="toTime">
													</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
								<div class="product-table">
									<table width="100%">
										<thead class="product-table-head" >
											<tr>
												<th class="textcenter" width="25">时间段</th>
												<th class="textcenter" width="170">数量</th>
											</tr>
										</thead>
										<tbody class="appoint-table-body">
											<tr>
												<td align="center">9:00-10:00</td>
												<td align="center"><input name="timenum1" validType="number" maxlength="5" class="easyui-validatebox" id="timenum1"/></td>
											</tr>
											<tr>
												<td align="center">10:00-11:00</td>
												<td align="center"><input name="timenum2" validType="number" maxlength="5" class="easyui-validatebox" id="timenum2"/></td>
											</tr>
											<tr>
												<td align="center">11:00-12:00</td>
												<td align="center"><input name="timenum3" validType="number" maxlength="5" class="easyui-validatebox" id="timenum3"/></td>
											</tr>
											<tr>
												<td align="center">12:00-13:00</td>
												<td align="center"><input name="timenum4" validType="number" maxlength="5" class="easyui-validatebox" id="timenum4"/></td>
											</tr>
											<tr>
												<td align="center">13:00-14:00</td>
												<td align="center"><input name="timenum5" validType="number" maxlength="5" class="easyui-validatebox" id="timenum5"/></td>
											</tr>
											<tr>
												<td align="center">14:00-15:00</td>
												<td align="center"><input name="timenum6" validType="number" maxlength="5" class="easyui-validatebox" id="timenum6"/></td>
											</tr>
											<tr>
												<td align="center">15:00-16:00</td>
												<td align="center"><input name="timenum7" validType="number" maxlength="5" class="easyui-validatebox" id="timenum7"/></td>
											</tr>
											<tr>
												<td align="center">16:00-17:00</td>
												<td align="center"><input name="timenum8" validType="number" maxlength="5" class="easyui-validatebox" id="timenum8"/></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
		
						</div>
					</div>
				</c:if>
				
				<div style="height:92px;display:none;" id="car_banner">
					<div class="banner_b">
				        <a class="left_btn" href="javascript:;"><img src="/yoyoportal/resources/images/index/left_btn.png" width="10" height="19"></a>
				        <div class="b_ad">
				            <ul class="b_ad_ul" id="carListBar">
				              
				            </ul>
				        </div>
			        	<a class="right_btn" href="javascript:;"><img src="/yoyoportal/resources/images/index/right_btn.png" width="10" height="19"></a>
			    </div>
				</div>
				
			<form method="post" id='form_saveGoods' class="section easyui-form">
				<input type="hidden" value="${goodsId }" name="goodsId" id="goodsId">
				<input type="hidden"  id="productId" >
				<input type="hidden" name="rootCategory" id="rootCategoryId">
				<input type="hidden" name="carId" id="carId">
				<input type="hidden" name="accId" id="accIdInput"> <!-- 配件id -->
				<div class="FormWrap " style="background: none">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="liststyle data width1" style="border: none">

						<tr>
							<th><em><font color="red">*</font></em>商品名称：</th>
							<td><input autocomplete="off"  type="text" class="easyui-validatebox x-input"  data-options="required:true"
								id="id_gname" name="name" maxlength="100" style="width: 60%" /></td>
						</tr>
						<tr>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>商家商品编号：</th>
							<td><input autocomplete="off" class="easyui-validatebox x-input inputstyle"
								type="text" validType="number" name="fbn" /></td>
						</tr>
						<tr>
							<th>商品关键词：</th>
							<td><input type="text" name="keywords" value=""
								class="inputstyle" maxlength="100" /> <span
								class="notice-inline ">用于筛选商品，多个关键词用半角竖线"|"分开</span></td>
						</tr>

						<tr>
							<th>商品简介：</th>
							<td><textarea type="textarea" class="x-input" name="brief"
									style="resize: none;" cols="50" rows="5" maxth="255"></textarea>
								<div class="notice-inline">简短的商品介绍,请不要超过70个字</div></td>
						</tr>
						<tr>
							<th>商品相册：</th>
							<td>
								<div class="goods-image-div" id="goods_picture"></div>
								<div style="clear: both;"></div>
							</td>
						</tr>
						<!-- <tr>
							<th>立即上架：</th>
							<td><input type="radio" name="marketable" value="0" checked/>否 <input
								type="radio" name="marketable" value="1"
								style="margin-left: 15px;" />是</td>
						</tr> -->

						<!-- <tr>
							<th><font color="red">*</font>积分：</th>
							<td><input type="text" name="score" value="" width="70"  class="easyui-numberbox easyui-validatebox"  data-options="required:true,min:0.5,max:30,precision:2"
								maxlength="10" />% （请填写与商品销售价的比例，运营商设置范围为0.5%~30%）</td>
						</tr> -->

					</table>

					<div id="gEditor-ginfo">
						<table class="liststyle2 width1" style="border: none">
							<tr>
								<th style="text-align: right;">商品属性：</th>
								<td>
									<!-- 根据分类获取对应的属性列表 -->
									<table border="0" style="border-collapse: separate; border-spacing: 10px;"	class="attrTable">

									</table>
								</td>
							</tr>
						</table>
					</div>


					<div id="spec_items">

						<div id="gEditor-sepc-panel" style="display: none;">
							<table class="liststyle2 width1"
								style="border: none; width: 50%;">
								<tr>
									<th style="text-align: right;">规格开关：</th>
									<td>
										<button class="btn closespec" onclick="specclose()"
											type="button">
											<span><span>关闭</span></span>
										</button>
										<button class="btn openspec" onclick="openclose()"
											type="button" style="display: none;">
											<span><span>开启</span></span>
										</button>
									</td>
								</tr>
							</table>
							
							
							<div class="spec-panel" id="spec-window">
								<div class="clearfixs">
									<div class="specs"></div>
									<div class="spec-value">
										<div class="spec-toolbar">规格值</div>
										<div class="spec-values-list"></div>
										<div class="spec-value-table">
											<table width="100%"></table>
										</div>
									</div>
								</div>
								<div class="spec-action">
									<button type="button" id="spec-action-gen-prod" class="btn btn-primary">生成所有货品</button>
								</div>
								<div class="product-table">
									<table width="100%">
										<thead class="product-table-head" >
											<tr>
												<th class="textcenter" width="25">上架</th>
													<th class="textcenter" width="170">规格值</th>
													<th class="textcenter" width="158">图片</th>
													<!-- <th class="textcenter" width="100">货号</th> -->
													<th class="textcenter" width="70"><em><font
															color="red">*</font></em>库存</th>
													<c:if test="${catType=='999'}">
														<th class="textcenter" width="125"><em><font color="red">*</font></em>订金价(元)</th>
														<!-- <th class="textcenter" width="70">成本价(元)</th> -->
														<th class="textcenter" width="70"><font	color="red">*</font>yoyo价(元)</th>
														<th class="textcenter" width="70"><font	color="red">*</font>市场价(元)</th>
													</c:if>
													<c:if test="${catType!='999'}">
														<th class="textcenter" width="125"><em><font color="red">*</font></em><span class="priceName">销售价</span>(元)</th>
														<!-- <th class="textcenter" width="70">成本价(元)</th> -->
														<th class="textcenter" width="70"><font	color="red">*</font>市场价(元)</th>
														<th class="textcenter" width="70"><font	color="red">*</font>区间价(元)</th>
													</c:if>
													
													<!-- <th class="textcenter" width="60">货位</th> -->
													<th class="textcenter" width="25">操作</th>
											</tr>
										</thead>
										<tbody class="product-table-body"></tbody>
									</table>
								</div>
							</div>

						</div>
						<!-- 没有规格的默认值 -->
						<table border="0" cellpadding="0" cellspacing="0" width="100%"
							id="nospec_body" class="liststyle data" style="border: none">
							<tbody>
								<tr>
								<c:if test="${catType==999}">
									<th width="98"><em><font color="red">*</font></em>订金价：</th>
									<td><input   class="easyui-numberbox  x-input inputstyle"  data-options="precision:2,min:0,max:999999999"
										name="cost" style="width: 150px;" maxlength="25" type="text" id="deposit_price" />元
									</td>
								</c:if>
								
								<c:if test="${catType!=999}">
									<th width="98"><em><font color="red">*</font></em>销售价：</th>
									<td><input   class="easyui-numberbox  x-input inputstyle"  data-options="precision:2,min:0,max:999999999"
										name="price" style="width: 150px;" maxlength="25" type="text" id="sales_price" />元
										<!-- <button type="button"
											onclick="goodsEditor.mprice.bind(goodsEditor)(this)">
											<span><img
												src="${path}/resources/images/shop/edit_price.png" /></span>
										</button> --tag_foreign_nospec--></td>
								</c:if>
									
								</tr>

								<tr>
									<c:if test="${catType==999}">
										<th><em><font color="red">*</font></em>yoyo价：</th>
										<td><input   class="easyui-numberbox  x-input inputstyle"  data-options="precision:2,min:0,max:999999999"
											name="price" style="width: 150px;" maxlength="25" type="text" id="sales_price" />元
										</td>
									</c:if>
								</tr>
								<%-- <c:if test="${catType!=999}"> --%>
								<tr>
									<th><em><font color="red">*</font></em>市场价：</th>
									<td><input  class="easyui-numberbox  x-input"  data-options="precision:2,min:0,max:999999999" id="id_goods_mktprice"
										name="mktPrice" maxlength="30" style="width: 150px" type="text" />元</td>
								</tr>
								<%-- </c:if> --%>
								<tr>
									<th>货号：</th>
									<td><input  class="x-input " type="text" style="width: 150px"
										name="bn" maxlength="25" /></td>
								</tr>
								<!-- <tr>
									<th>重量：</th>
									<td><input autocomplete="off" class="x-input " type="text" name="weight"
										style="width: 60px" maxlength="25" />克(g)</td>
								</tr> -->
								<tr>
									<th><em><font color="red">*</font></em>库存：</th>
									<td><input   type="text" class="easyui-numberbox x-input"   data-options="precision:0,min:0,max:9999999"
										name="store" style="width: 150px" maxlength="25" id="sales_store" /></td>
								</tr>
								<%-- <c:if test="${catType!='999'}">
									<tr>
										<th>价格区间开始时间：</th>
										<td>
											<input size="10" class="easyui-datebox cal" style="width: 150px; height: 30px;" required="true" maxlength="30" data-options="editable:false,onSelect:onSelectPriceTime"
													type="text" name="priceStartTime" id="priceStartTime">
										</td>
									</tr>
									<tr>
										<th>价格区间结束时间：</th>
										<td>
											<input size="10" id="priceEndTime" 
											class="easyui-datebox cal" maxlength="30" style="width: 150px; height: 30px;" type="text" data-options="editable:false,onSelect:onSelectPriceTime"
											required="true" name="priceEndTime">
										</td>
									</tr>
								</c:if> --%>
								<c:if test="${catType!='999'}">
									<tr>
										<th><em><font color="red">*</font></em>价格区间中的价格：</th>
										<td>
											<input   class="easyui-numberbox  x-input inputstyle"  data-options="precision:2,min:0,max:999999999"
										name="timePrice" style="width: 150px;" maxlength="25" type="text" id="timePrice" />元
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
						<c:if test="${catType!='999'}">
							<table border="0" cellpadding="0" cellspacing="0" width="100%"
							class="liststyle data" style="border: none">
								<tbody>
									<tr>
										<th><em><font color="red">*</font></em>价格区间开始时间：</th>
										<td>
											<input size="10" class="easyui-datebox cal" style="width: 150px; height: 30px;" required="true" maxlength="30" data-options="editable:false,onSelect:onSelectPriceTime"
													type="text" name="priceStartTime" id="priceStartTime">
										</td>
									</tr>
									<tr>
										<th><em><font color="red">*</font></em>价格区间结束时间：</th>
										<td>
											<input size="10" id="priceEndTime" 
											class="easyui-datebox cal" maxlength="30" style="width: 150px; height: 30px;" type="text" data-options="editable:false,onSelect:onSelectPriceTime"
											required="true" name="priceEndTime">
										</td>
									</tr>
								</tbody>
							</table>
						</c:if>
					</div>

				</div>
			</div>
			<div id="errormsg"></div>
			<div>
				<div id="good-edit-acc-info" style="width: 960px;">
					<div class="title_fb ">2.优惠套装</div>
					<div id="good-edit-acc-info-group"
						class="good-edit-form border-radius-5px">
						<div id="toolbar-good-edit-acc-info-group">
							<label>选择套装：</label> <input type="radio" name="accType" value="1"
								id="accType_1" checked="checked" /><label for="accType_1">选择几件商品作为套装</label>
							<input type="radio" name="accType" value="2" id="accType_2" /><label
								for="accType_2">选择一组商品搜索结果作为套装</label> <a
								id="button-good-edit-acc-add" class="easyui-linkbutton"
								href="javascript:void(0)">添加套装</a>
						</div>
						<div id="good-edit-acc-info-from-group"></div>
					</div>
				</div>

				<div id="good-edit-det-info" style="padding: 20px 0px;">
					<div class="title_fb ">3.产品描述</div>
					<table style="width: 960px;">
						<tr>
							<td>
								<!-- <textarea id="cle-good-detail-info" name="intro" ></textarea> -->
								
								<!--style给定宽度可以影响编辑器的最终宽度-->
<script type="text/plain" id="cle-good-detail-info" name="intro" style="width:1000px;height:240px;">
</script>
							</td>
						</tr>
					</table>
				</div>
				<div id="good-edit-ass-info">
					<div class="title_fb " >4.相关商品</div>
					
					<div class="object-select clearfix"  onclick="selectRelateGoods();">
						<div class="label">请选择相关商品</div>
						<div class="handle">&nbsp;</div>
					</div>
					<ul id="good-edit-relate">
					</ul>
				</div>
				<!-- <div id="good-edit-seo-info">
					<div class="title_fb ">5.SEO设置</div>
					<table class="liststyle tdright">
						<tr>
							<th>Meta Keyword：</th>
							<td style="text-align: left;"><input type="text"
								name="title" /></td>
						</tr>
						<tr>
							<th>Meta Description：</th>
							<td style="text-align: left;"><input type="text"
								name="metaKeywords" /></td>
						</tr>

					</table>
				</div> -->
			</div>
		</form>
		<div style="text-align: center; margin-top: 20px;">
			<button class="btn btn-primary" type="button" id="checkSubmit" style="display:none;" onclick="saveGoods('2')">
				<span><span>提交审核</span></span>
			</button>
			
			<button class="btn btn-primary" type="button" id="saleUpSubmit" style="display:none;" onclick="saveGoods('1')">
				<span><span>立即上架</span></span>
			</button>
			
			<button class="btn btn-primary" type="button" id="saveStore" onclick="saveGoods('-1')">
				<span><span>暂存仓库</span></span>
			</button>
		</div>



		<!-- 分类弹出框 -->
		<div id="category_dialog" style='display: none;'></div>

		<!-- 商品相册图片 -->
		<div id="products_pic_dialog" class="dialog dialog_div"
			style='display: none; visibility: visible; zoom: 1; opacity: 1; z-index: 65534; border: none; width: 722px; position: absolute; top: 1026px; left: 460px;'>
			<input type="hidden" id="product_spec_id">
			<!-- 存放当前点击的规格值id -->
			<div class="dialog-box" style="display: block">
				<div class="dialog-head clearfix">
					<div class="dialog-title flt"
						style="background: -moz-linear-gradient(center top, white, #E0E3EC) repeat scroll 0 0 #F0F0F0;">关联商品相册图片</div>
					<div class="dialog_close">✖</div>
				</div>
				<div class="dialog-content-head" style="font-size: 0; height: 0;"></div>
				<div container="true" class="dialog-content-body"
					style="height: 400px; width: 700px;">
					<div id="sel-albums-images">
						<div class="division" style="height: 300px; overflow: auto;">

						</div>
						<div class="mainFoot"
							style="width: 80px; height: 40px; margin: 0 auto;">
							<div class="table-action">
								<button class="btn" type="button" style="padding: 0px 10px;"
									onclick="saveSpecPic()">
									<span>保存</span>
								</button>
							</div>
						</div>

					</div>
				</div>
				<div class="dialog-content-foot" style="font-size: 0; height: 0;"></div>
				<div class="btn-resize"></div>
			</div>

		</div>

		<!-- 选择相关商品BEGIN -->
		<div id="categoryWindow" class="easyui-window" title="选择相关商品"  closed="true"  style="width:600px;height:616px;">
			<div style="padding: 5px;" class="datagrid-toolbar" id="sub_toolbar" >
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="confirmRelateGoods()"> 确认选择</a>
			</div>
			<div class="zTreeDemoBackground left">
				<table id="goodsTable" style="marge: 10px auto; width: 100%;"></table>
			</div>
		 </div>
		<!-- 选择相关商品 END -->	
		
		<!-- 选择配件货品BEGIN -->
		<div id="accessoryWindow" class="easyui-window" title="选择套装货品"  closed="true"  style="width:600px;height:616px;">
			<div style="padding: 5px;" class="datagrid-toolbar" id="product_sub_toolbar" >
				<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="confirmAccessoryGoods()"> 确认选择</a>
			</div>
			<div class="zTreeDemoBackground left">
				<table id="productTable" style="marge: 10px auto; width: 100%;"></table>
			</div>
		 </div>
		<!-- 选择配件货品 END -->	

	</div>
	
	
</body>
</html>

