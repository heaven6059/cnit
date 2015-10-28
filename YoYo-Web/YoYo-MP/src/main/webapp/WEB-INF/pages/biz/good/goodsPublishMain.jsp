<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>商品编辑</title>
<jsp:include page="${_path}/WEB-INF/pages/decorator/inc.jsp" />
<link type="text/css" href="${path}/resources/styles/good/shopRegister.css?v=${versionVal}" rel="stylesheet" />
<link type="text/css" rel="stylesheet" href="${path }/resources/styles/good/shopManager.css?v=${versionVal}">
<link type="text/css" rel="stylesheet" href="${path }/resources/styles/good/categoryDialog.css?v=${versionVal}">
<link type="text/css" href="${path}/resources/scripts/biz/good/goodSpecEditIndex.css?v=${versionVal}" rel="stylesheet" />
<!-- 引用控制层插件样式 -->
<link rel="stylesheet" href="${path}/resources/scripts/imageUpload/zyUpload.css" type="text/css">
<!-- 引用核心层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<!-- 引用控制层插件 -->
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<!-- 自动搜索插件 -->
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.trapown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.comdropdown.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/jquery.addaccessory.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/good/categoryDialog.js?v=${versionVal}"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/good/goodsPublishMain.js?v=${versionVal}"></script>

</head>
<body>
	<div class="clearfix">
		<div style="float: left; margin: 20px 10px;">
			<ul class="good-edit-tab-nav">
				<li><a href="#good-edit-base-info"><span>基本信息</span></a></li>
				<li><a href="#good-edit-base-info"><span>扩展信息</span></a></li>
				<li><a href="#good-edit-acc-info"><span>优惠套装</span></a></li>
				<li><a href="#good-edit-det-info"><span>详细介绍</span></a></li>
				<li><a href="#good-edit-ass-info"><span>相关商品</span></a></li>
				<!-- <li><a href="#good-edit-tag-info"><span>商品标签</span></a></li> -->
			</ul>
		</div>
		<div style="float: left; margin: 20px 10px;">
			<form method="post" id='form_saveGoods' class="section">
				<input type="hidden" value="${goodsId}" name="goodsId" id="goodsId"> <input type="hidden" id="productId">
				<div id="gEditor-Body">
					<div>
						<a name="good-edit-base-info"
							style="text-decoration: none; cursor: default; font-size: medium; font-weight: bold;">基本信息</a>
					</div>
					<div class="FormWrap" style="background: none">
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="liststyle data width1" style="border: none">
							<tr>
								<th><em><font color="red">*</font></em>商品分类：</th>
								<td>
									<div class="object-select clearfix" id="gEditor-GCat-category">
										<div class="label" id="labelCategory">请选择</div>
										<div class="handle">&nbsp;</div>
										<input type="hidden" name="rootCategory" id="rootCategoryId">
									</div>
								</td>
							</tr>
							<tr style="display: none;" id="car_category" class="category_type">
								<th>汽车车型：</th>
								<td><select id="factoryId" name="factoryId" style="width: 100px;"></select> <select id="carDeptId"
										name="carDeptId" style="width: 100px;"></select> <select id="carYearValue" name="carYearValue"
										style="width: 100px;"></select> <select id="carTypeData" name="carId" style="width: 120px;"></select></td>
							</tr>
							<tr style="display: none;" id="acc_category" class="category_type">
								<th>汽车套装类型：</th>
								<td><select id="carAccType" style="width: 100px;"></select> <select id="accId" name="accId"
										style="width: 100px;"></select> </select></td>
							</tr>
							<tr>
								<th><em><font color="red">*</font></em>商品名称：</th>
								<td><input autocomplete="off" type="text" class="easyui-validatebox x-input" data-options="required:true"
									id="id_gname" name="name" maxlength="100" style="width: 60%" /></td>
							</tr>
							<tr>
								<th></th>
								<td></td>
							</tr>
							<tr>
								<th>商品编号：</th>
								<td><input autocomplete="off" class="x-input inputstyle" type="text" name="fbn" /></td>
							</tr>
							<tr>
								<th>商品关键词：</th>
								<td><input type="text" name="keywords" value="" class="inputstyle" maxlength="100" /> <span
									class="notice-inline ">用于筛选商品，多个关键词用半角竖线"|"分开</span></td>
							</tr>
							<tr>
								<th>商品简介：</th>
								<td><textarea type="textarea" class="x-input" name="brief" style="resize: none;" cols="50" rows="5"
										maxth="255"></textarea>
									<div class="notice-inline">简短的商品介绍,请不要超过70个字</div></td>
							</tr>
							<tr>
								<th>商品相册：</th>
								<td>
									<div class="goods-image-div" id="goods_picture"></div>
									<div style="clear: both;"></div>
								</td>
							</tr>
							<tr style="display:none;">
								<th>立即上架：</th>
								<td><input type="radio" name="marketable" value="0" checked />否 <input type="radio" name="marketable"
									value="1" style="margin-left: 15px;" />是</td>
							</tr>
							<tr  style="visibility: hidden">
								<th><font color="red">*</font>积分：</th>
								<td><input type="text" name="score" value="" width="70" class="easyui-numberbox easyui-validatebox"
									data-options="required:true,min:0.5,max:30,precision:2" maxlength="10" />% （请填写与商品销售价的比例，运营商设置范围为0.5%~30%）</td>
							</tr>
						</table>
						<div id="gEditor-ginfo">
							<table class="liststyle2 width1" style="border: none">
								<tr>
									<th style="text-align: right;">商品属性：</th>
									<td>
										<!-- 根据分类获取对应的属性列表 -->
										<table border="0" style="border-collapse: separate; border-spacing: 10px;" class="attrTable">
										</table>
									</td>
								</tr>
							</table>
						</div>
						<div id="spec_items">
							<div id="gEditor-sepc-panel" style="display: none;">
								<table class="liststyle2 width1" style="border: none; width: 50%;">
									<tr>
										<th style="text-align: right;">规格开关：</th>
										<td>
											<button class="btn closespec" onclick="specclose()" type="button">
												<span><span>关闭</span></span>
											</button>
											<button class="btn openspec" onclick="openclose()" type="button" style="display: none;">
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
											<thead class="product-table-head">
												<tr>
													<th class="textcenter" width="25">上架</th>
													<th class="textcenter" width="170">规格值</th>
													<th class="textcenter" width="158">图片</th>
													<th class="textcenter" width="100">货号</th>
													<th class="textcenter" width="70"><em><font color="red">*</font></em>库存</th>
													<th class="textcenter" width="125"><em><font color="red">*</font></em><span id="price1">销售价(元)</span></th>
													<th class="textcenter" width="70">成本价(元)</th>
													<th class="textcenter" width="70"><em><font color="red">*</font></em><span id="price2">市场价(元)</span></th>
													<th class="textcenter" width="60">货位</th>
													<th class="textcenter" width="25">操作</th>
												</tr>
											</thead>
											<tbody class="product-table-body"></tbody>
										</table>
									</div>
								</div>
							</div>
							<!-- 没有规格的默认值 -->
							<table border="0" cellpadding="0" cellspacing="0" width="100%" id="nospec_body" class="liststyle data"
								style="border: none">
								<tbody>
									<tr>
										<th width="98"><em><font color="red">*</font></em><span id="price3">销售价：</span></th>
										<td><input class="easyui-numberbox  x-input inputstyle" data-options="precision:2" name="price"  data-options="required:true" style="width: 100px;" maxlength="25" type="text" id="sales_price" />元 <!-- <button type="button"
											onclick="goodsEditor.mprice.bind(goodsEditor)(this)">
											<span><img
												src="${path}/resources/images/shop/edit_price.png" /></span>
										</button> --tag_foreign_nospec--></td>
									</tr>
									<tr>
										<th><em><font color="red">*</font></em><span id="price4">市场价：</span></th>
										<td><input class="easyui-numberbox  x-input" data-options="precision:2" id="id_goods_mktprice"  data-options="required:true" name="mktPrice" maxlength="30" style="width: 100px" type="text" />元</td>
									</tr>
									<tr>
										<th>货号：</th>
										<td><input class="x-input " type="text" name="bn" maxlength="25" /></td>
									</tr>
									<!-- <tr>
									<th>重量：</th>
									<td><input autocomplete="off" class="x-input " type="text" name="weight"
										style="width: 60px" maxlength="25" />克(g)</td>
								</tr> -->
									<tr>
										<th><em><font color="red">*</font></em>库存：</th>
										<td><input type="text" class="easyui-numberbox x-input" name="store" style="width: 100px" maxlength="25"
											id="sales_store" /></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div>
					<div id="good-edit-acc-info" style="width: 960px;">
						<div>
							<a name="good-edit-ass-info"
								style="text-decoration: none; cursor: default; font-size: medium; font-weight: bold;">套装</a>
						</div>
						<div id="good-edit-acc-info-group" class="good-edit-form border-radius-5px">
							<div id="toolbar-good-edit-acc-info-group">
								<label>选择套装：</label> <input type="radio" name="accType" value="1" id="accType_1" checked="checked" /><label
									for="accType_1">选择几件商品作为套装</label> <input type="radio" name="accType" value="2" id="accType_2" /><label
									for="accType_2">选择一组商品搜索结果作为套装</label> <a id="button-good-edit-acc-add" class="easyui-linkbutton"
									href="javascript:void(0)">添加套装</a>
							</div>
							<div id="good-edit-acc-info-from-group"></div>
						</div>
					</div>
					<div id="good-edit-det-info" style="padding: 20px 0px;">
						<div>
							<a name="good-edit-det-info"
								style="text-decoration: none; cursor: default; font-size: medium; font-weight: bold;">详细信息</a>
						</div>
						<table style="width: 960px;">
							<tr>
								<td><textarea id="cle-good-detail-info" name="intro"></textarea></td>
							</tr>
						</table>
					</div>
					<div id="good-edit-ass-info">
						<div>
							<a name="good-edit-ass-info"
								style="text-decoration: none; cursor: default; font-size: medium; font-weight: bold;">相关商品</a>
						</div>
						<div class="object-select clearfix" onclick="selectRelateGoods();">
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
				<button class="btn btn-primary" type="button" id="saveGoodsBtn">
					<span><span>保存</span></span>
				</button>
			</div>
		</div>
	</div>
	<!-- 分类弹出框 -->
	<div id="category_dialog" style='display: none;'></div>
	<!-- 商品相册图片 -->
	<div id="products_pic_dialog" class="dialog dialog_div"
		style='display: none; visibility: visible; zoom: 1; opacity: 1; z-index: 65534; border: none; width: 722px; position: absolute;  left: 460px;'>
		<input type="hidden" id="product_spec_id">
		<!-- 存放当前点击的规格值id -->
		<div class="dialog-box" style="display: block">
			<div class="dialog-head clearfix">
				<div class="dialog-title flt"
					style="background: -moz-linear-gradient(center top, white, #E0E3EC) repeat scroll 0 0 #F0F0F0;">关联商品相册图片</div>
				<div class="dialog_close">✖</div>
			</div>
			<div class="dialog-content-head" style="font-size: 0; height: 0;"></div>
			<div container="true" class="dialog-content-body" style="height: 400px; width: 700px;">
				<div id="sel-albums-images">
					<div class="division" style="height: 300px; overflow: auto;"></div>
					<div class="mainFoot" style="width: 80px; height: 40px; margin: 0 auto;">
						<div class="table-action">
							<button class="btn" type="button" style="padding: 0px 10px;" onclick="saveSpecPic()">
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
	<div id="categoryWindow" class="easyui-window" title="选择相关商品" closed="true" style="width: 600px; height: 616px;">
		<div style="padding: 5px;" class="datagrid-toolbar" id="sub_toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="confirmRelateGoods()"> 确认选择</a>
		</div>
		<div class="zTreeDemoBackground left">
			<table id="goodsTable" style="marge: 10px auto; width: 100%;"></table>
		</div>
	</div>
	<!-- 选择相关商品 END -->
	<!-- 选择套装货品BEGIN -->
	<div id="accessoryWindow" class="easyui-window" title="选择套装货品" closed="true" style="width: 600px; height: 616px;">
		<div style="padding: 5px;" class="datagrid-toolbar" id="product_sub_toolbar">
			<a href="javascript:void(0)" class="easyui-linkbutton" iconcls="icon-add" onclick="confirmAccessoryGoods()"> 确认选择</a>
		</div>
		<div class="zTreeDemoBackground left">
			<table id="productTable" style="marge: 10px auto; width: 100%;"></table>
		</div>
	</div>
	<!-- 选择套装货品 END -->
	
	
</body>
</html>
