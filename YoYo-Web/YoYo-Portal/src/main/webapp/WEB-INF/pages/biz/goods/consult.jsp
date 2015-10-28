<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品咨询</title>
<script type="text/javascript">var _path="${path}";</script>
<link type="text/css" href="${path}/resources/styles/goods/jd/consult.css" rel="stylesheet" />
<script src="${path}/resources/scripts/biz/goods/consult.js?v=${versionVal}"></script>
<script src="${path}/resources/scripts/pagination/jquery.pagination.js"></script>
</head>
<body>
<div id="main_container" class="root61" style="width: 1200px !important; margin:auto; height: auto;display: table;">
  <div class="left">
		<div class="m" id="pinfo">
			<div class="mt">
				<h2>商品信息</h2>
				<input type="hidden" id="goods_id" value="${goodsId}" />
				<input type="hidden" id="product_id" value="${productId}" />
			</div>
			<div class="mc">
				<div class="p-img">
					<a href="" target="_blank"> 
						<img alt="" src="" width="130px" height="130px"> 
					</a>
				</div>
				<div class="p-name">
					商品名称：<a href="" target="_blank"></a>
				</div>
				<div class="p-price">优优价：
					<strong class="">￥0.00</strong>
				</div>
				<div class="p-cost-price" style="display: none;">预定金：
					<strong class="">￥0.00</strong>
				</div>
			</div>
		</div>
		<div id="miaozhen8151" class="AD_L"></div>
	</div>
	<div class="right" style="width:979px;">
		<div class="View_Mode">
			<span class="float_Right">
				<a href="${path}/goodsManager/goodsIndex?goodsId=${goodsId}" class="link_1">返回商品页&gt;&gt;</a>
			</span>
			<div class="viewindex   viewindex_curr  ">
				<a href="javascript:;">全部购买咨询</a>
			</div>
		</div>
		
		<div class="Refer_List">
			<div class="refer" style="display: none;">
				<div class="r_info">网友：<span></span>&nbsp;&nbsp;&nbsp;&nbsp;<em id="level" name=""></em>&nbsp;&nbsp;&nbsp;&nbsp;<span></span>
				</div>
				<dl class="ask">
					<dt>咨询内容：</dt>
					<dd>
						<span>你好，戴尔N4110  能使用吗？</span>
					</dd>
				</dl>
				<dl class="answer">
					 
					<dt>掌柜回复：</dt>
								<dd>
						您好！可以。感谢您对京东的支持！祝您购物愉快！
					</dd> 
				</dl>
			</div>
		</div>
		<div id="Pagination" class="yoyoPagination" >
		</div>
		<div class="Review_Form" style="width:977px;">
			<h5>发表咨询：</h5>
			<div class="Re_Explain">声明：您可在购买前对产品包装、颜色、运输、库存等方面进行咨询，我们有专人进行回复！因厂家随时会更改一些产品的包装、颜色、产地等参数，所以该回复仅在当时对提问者有效，其他网友仅供参考！咨询回复的工作时间为：周一至周五，9:00至18:00，请耐心等待工作人员回复。</div>
			<ul>
				<li id="pointType">
					<span style="display: inline;">咨询类型：</span>
					<input type="radio" value="2" name="pointType" checked="checked">
					商品咨询  
				</li>
				<li>
					<span>咨询内容：</span>
					<textarea id="consultationContent" name="consultationContent" class="area1" style="font-size: 14px;border:1px solid rgb(169, 169, 169);"></textarea>
				</li>
				<li id="column_refer_result">
					<img title="点击更新验证码" id="js-mail_vcode_img" style="height: 40px; width: 60px;" onclick="javascript:refresh(this);" src="${path}/verifyImage?width=60&height=30" alt="code" />
					<input type="text" class="code_ipt" name="code" id="id_imgValidation"/>
				</li>
				<li class="buttons">
					<a href="javascript:;" id="getRefer">
						<img id="saveConsultation" src="${path}/resources/images/goods/button_08.jpg">
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>
</body>
</html>