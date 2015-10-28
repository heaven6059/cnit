<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>申请活动</title>
<link type="text/css"	href="${path}/resources/styles/shopMng/shopManager.css?v=${versionVal}"	rel="stylesheet" />
<script type="text/javascript"	src="${path}/resources/scripts/select2/select2.min.js"></script>
<link type="text/css"	href="${path}/resources/styles/select2/select2.min.css"	rel="stylesheet" />
<script type="text/javascript"	src="${path}/resources/scripts/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript"	src="${path}/resources/scripts/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"	src="${path}/resources/scripts/biz/activityMng/addScoreActivity.js?v=${versionVal}"></script>
<link type="text/css" rel="stylesheet" href="${path }/resources/styles/shopEnter/shopRegister.css?r=${versionVal}">
<script type="text/javascript" src="${path}/resources/scripts/biz/previewImg.js"></script>
<script type="text/javascript" src="${path}/resources/scripts/biz/ajaxfileupload.js"></script>
<script type="text/javascript"	src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<link rel="stylesheet" href="${path}/resources/styles/jquery-ui.css"	type="text/css" />
<script type="text/javascript"	src="${path}/resources/scripts/jquery/jquery-ui.js"></script>
</head>
<body>
	<div class="shop_manager_right ">
		<div id="window_goods_select" class="easyui-dialog" title="选择商品" data-options="closed:true,cache:false,buttons:'#search_car_type_btn'" style="width:800px;height:800px;padding:10px">
			<table id="goods_table">
			</table>
			<div style="text-align: center; margin-top: 20px;">
				<button class="btn btn-primary" type="button" onclick="selectGoods()">
					<span><span>确定</span></span>
				</button>
				<button class="btn btn-primary" type="button" onclick="closeGoods()">
					<span><span>关闭</span></span>
				</button>
			</div>
	    </div>
			
		<form method="post" id='form_saveActivity' class="section easyui-form">
			<input type="hidden" name="aid" id="actId" value="${actId}">
			<input type="hidden" name="gid" id="goodsId" >
			<input type="hidden" name="catId" id="catId" >
			<input type="hidden" name="limitNum" id="limitNum" value="${limitNum}">
			<input type="hidden" name="type" id="type" value="${type}">
			<div id="gEditor-Body">
				<div class="title_fb ">活动信息</div>
				<div class="FormWrap" style="line-height:3;background: none">
					<label for="" class="col-sm-1 control-label">活动名称：<span  style="color:red;">${name}</span></label></br>
					<label for="" class="col-sm-1 control-label">开始时间：<span  style="color:red;">${startTime}</span></label></br>
					<label for="" class="col-sm-1 control-label">结束时间：<span  style="color:red;">${endTime}</span></label>
				</div>
			</div>
			
			<div class="FormWrap" style="line-height:3;background: none">
			<div class="title_fb ">申请信息</div>
				<div>
					<div>
						<label>参加活动的商品：</label> 
						<div class="object-select clearfix" id="gEditor-GCat-category">
							<div class="label" id="labelCategory">请选择</div>
							<div class="handle">&nbsp;</div>
						</div>
					</div>
					<c:if test="${type==1 }">
					<div>
						<label>积分：</label><input type="text" class="easyui-validatebox" validType="integ" required="required" style="width:120px;" name="score"  id="score"  >
					</div>
					</c:if>
					<div>
						<label>价格：</label><input type="text" class="easyui-validatebox" validType="money" style="width:120px;" name="lastPrice"  id="lastPrice"  >
						<c:if test="${type==1 }">不填代表全积分换购</c:if>
						<span style="color:red;"><label>原始价格：</label><input type="text" readonly="true" id="price" name="price" /></span>
					</div>
					<c:if test="${type==1 }">
					<div>
						<label>是否开启会员等级积分：</label>
						<input type="radio" name="ismemlv" value="0" id="isMemLv1" onclick="hideLev()" checked="checked" /><label>否</label>
						<input type="radio" name="ismemlv" value="1" id="isMemLv2" onclick="showLev()" /><label>是</label>
					</div>
					</c:if>
					<div id="lev" style="display: none;">
						<label>会员等级积分：</label>
						<div id="mLev"></div>
					</div>
					<div>
						<label>参加活动商品的数量：</label><input type="text" class="easyui-validatebox" validType="integ" style="width:120px;" name="nums"  id="nums"  >不填代表不做限制 
						<span><label>商品库存：</label><input type="text" readonly="true" id="remainnums" name="remainnums" /></span>
					</div>
					<div>
						<label>每人限购数量：</label><input type="text" class="easyui-validatebox" validType="integ" style="width:120px;" name="personlimit"  id="personlimit"  >不填代表不做限制
					</div>
					<div>
						<label>活动广告图：</label>
						<div>
							<div style="height:31px;">
								<div class="fileInputContainer"><input type="file" name="imageFile" id="imageId" class="fileInput" onchange="previewImage(this,'previewImageId','imgimageId',358,248)" /></div>
								<input type="button" onclick="submitForm('imageId','imageId1','imgImageid')"	class="upLoad"  value="上传" />
							</div>
							<div id="previewImageId" class="shop_logo">
								<img id="imgImageid"  width="360" height="250" >
							</div>
					 	</div>
                        <input type="hidden" id="imageId1" name="imageCodeid" >
					</div>
				</div>
			</div>
			<div style="text-align: center; margin-top: 20px;">
				<button class="btn btn-primary" type="button" id="saleUpSubmit"  onclick="saveActivity()">
					<span><span>确定</span></span>
				</button>
				<button class="btn btn-primary" type="button" id="saleUpSubmit"  onclick="cancle()">
					<span><span>取消</span></span>
				</button>
			</div>
		</form>

	</div>
	
	
</body>
</html>

