<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
			request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOYO汽车商城-商家选择开店类型</title>
<link type="text/css" rel="stylesheet"
	href="${path}/resources/styles/shopEnter/shopRegister.css">
<style type="text/css">
	.flowShop{ width: 540px; margin: 50px auto 0; padding: 0 230px; text-align: center; font-family: "微软雅黑"; }
	.shop{ width: 184px; height: 176px; text-align: center; background-color: #fff; border: 2px dashed #e5e5e5; padding: 20px 16px; cursor: pointer; }
	.shop_img{ width: 78px; height: 63px; background: url(../resources/images/flowShop.png) no-repeat; margin-left: 53px; }
	.shop_odd{ background-position: 0 -84px !important; margin-left: 62px !important; }
	.shop h2{ font-size: 30px; margin: 4px 0 10px; }
	.shop p{ font-size: 14px; text-align: center; line-height: 30px; }
	.shopCurrent{ border: 2px solid #ff702a !important; background-color: #ffebe1 !important; }
	.shop_btn{ width: 200px; height: 38px; line-height: 38px; color: #fff; margin: 50px auto 0; background-color: #8dc420; border: 1px solid #76a518; text-align: center; font-size: 18px; cursor: pointer; }
</style>

<script type="text/javascript">
	$(function() {
		var flag = true;
		var type = "0"; //选择店铺的类型：4s店 1，快修店：0
		$("#first_div").click(function() {
			flag = true;
			type = "0";
		});

		$("#second_div").click(function() {
			flag = true;
			type = "1";
		});

		$(".shop").click(function(e) {
            $(this).addClass("shopCurrent").siblings(".shop").removeClass("shopCurrent");
        });
		
		//下一步 进行判断，是否选择店铺类型
		$("#next_btn")
				.click(
						function() {
							if (flag) {
								window.location.href = "${path}/shopEnter/shopRegisterSubmit?type="
										+ type;
								flag = false;
								type = "0";
							} else {
								parent.$.messager.alert('提示', '请选择店铺类型！','error');
							}
						});
	});
</script>
</head>
<body>
	<div class="shop_reg_main">
	<div class="shop_reg_center"><img src="${path}/resources/images/shop/shop_reg_data.png" />	</div>
		<div class="add_con">
			<div class="flowShop clearfix">
				<div class="shop shopCurrent fl" id="first_div">
					<div class="shop_img"></div>
					<h2>快修店</h2>
					<p>商家以快修店形式入驻开设店铺 快修店可以添加多个分店</p>
				</div>
				<div class="shop fr" id="second_div">
					<div class="shop_img shop_odd"></div>
					<h2>4S店</h2>
					<p>商家以4S形式入驻开设店铺</p>
				</div>
			</div>
			<div class="shop_btn" id="next_btn">下一步</div>
		</div>
		
	</div>
</body>
</html>