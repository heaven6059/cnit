<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的车型库</title>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<script type="text/javascript" src="${path}/resources/scripts/common/yoyo.js?v=${versionVal}"></script>
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/membercar/person.css?v=${versionVal}" />
<style type="text/css">

</style>
<script type="text/javascript">
$(function (){
	$('#changeCar').bind('click', function(e) {
		var opts={
			layerObj:$("#myCar"),			
			success:choose_car.chooseFinish=function(){
				$("#carbrand_input").val($("#brand_choose_input").val());
				$("#carDept_input").val($("#dept_choose_input").val());
				$("#car_input").val($("#car_choose_input").val());
				$("#carYear_input").val($("#year_choose_input").val());
				$("#car_info").html($("#car_name_choose_input").val()+"&nbsp;"+$("#year_choose_input").val());
				$("#js_mygarimg").attr("src",yoyo.imagesUrl+$("#brand_log_choose_input").val());
				$("#carProductionMonth").html(buildMonth($("#carYear_input").val()));
				buildYear($("#carYear_input").val());
				$("#useMonth").html(buildMonth($("#carYear_input").val()));
				layer.closeAll();
			},end:choose_car.destory()
		};
		my_car_utils.showSelectCarLayer(opts);
		
	});
	buildYear($("#carYear_input").val());
	$("#carProductionMonth").html(buildMonth($("#carYear_input").val(),$("#carProductionMonth_input").val()));
	$("#useMonth").html(buildMonth($("#carYear_input").val(),$("#useMonth_input").val()));

	$("#useYear").change(function (){
		$("#useMonth").html(buildMonth($(this).val()));
	});

	$(".photo180").find("img").each(function (x,item){		
		if($(item).attr("src").indexOf("u5.png")==-1){
			$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
		}
	});
	
	$("#js_add_ad_myadd").click(function (){
		if($("#car_input").val().length<=0){			
			layer.alert("请选择车型！",{title:false,closeBtn:false,icon:0});
			return;
		}
		if($("#carYear_input").val().length<=0){
			layer.alert("请选择车型年款！",{title:false,closeBtn:false,icon:0});
			return;
		}
		if($("#myaiche_name_u").val().length<=0){			
			layer.alert("请输入您的爱车昵称！",{title:false,closeBtn:false,icon:0});
			return;
		}
		if((parseInt($("#useYear").val()))==(parseInt($("#carYear_input").val()))){						
			if((parseInt($("#carProductionMonth").val()))>(parseInt($("#useMonth").val()))){				
				layer.alert("新车上路月份不能小于车型生产月份！",{title:false,closeBtn:false,icon:0});
				return 
			}
		}
						
		if($("#memberCarId").val()){
			$("#form_1").attr("action","../membercar/updateMemberCar");
			$("#form_1").submit();
		}else{
			var rqeustdata=$("#form_1").serialize();
			commonAjax(yoyo.memUrl+"/membercar/hasAddCurrentCar",rqeustdata,function (data){
				if(data.content==0){
					commonAjax(yoyo.memUrl+"/membercar/saveMemberCar",rqeustdata,function (data){
						window.location.href=yoyo.memUrl+"/membercar/memberCarList";
					},function (data){
						if(data.retCode=="000009"){
							layer.msg('最多只能添加6辆车型，请前往我的车型列表修改后再添加',{time: 3000,end:function(){
								window.location.href=yoyo.memUrl+"/membercar/memberCarList";
							}});
							return;
						}else{							
							layer.msg('保存车型失败',{time: 3000,end:function(){
								window.location.href=yoyo.memUrl+"/membercar/memberCarList";
							}});
						}
						
					});
				}else{
					layer.msg('该车型已添加请修改',{time:2000});
				}
			},function(data){
				layer.msg('保存车型失败',{time: 3000,end:function(){
					window.location.href=yoyo.memUrl+"/membercar/memberCarList";
				}});
			});
		}
	});
});

function buildYear(year){	
	var date=new Date(),option=[];
	for(var i=year;i<=date.getFullYear();i++){
		if($("#useYear_input").val()==i){
			option.push("<option value="+i+" selected='selected'>"+i+"年</option>");	
		}else{
			option.push("<option value="+i+">"+i+"年</option>");
		}
	}
	$("#useYear").html(option);
}
function buildMonth(year,sel){
	var date=new Date(),option=["<option value='0'>月份</option>"],curMonth=12;
	if(date.getFullYear()==year){
		curMonth=date.getMonth()+1;
	}
	if(year&&year>0){
		for(var i=1;i<=curMonth;i++){
			if(i==sel){
				option.push("<option value="+i+" selected='selected'>"+i+"月</option>");
			}else{
				option.push("<option value="+i+">"+i+"月</option>");
			}
		}
	}
	return option.join("");
}

</script>
</head>
<body>
<form action="" method="post" id="form_1">
<div class="member-main member-main2">
	<div class="left_b">
		<div class="managecar">
			<div class="mtitle">我的车型库</div>
			<div class="editcar clearfix">
				<div class="editleft">
					<div class="photo180">
						<img width="180" height="180" id="js_mygarimg" src="<c:if test="${empty memberCar.carLogo}">../resources/images/sign/u5.png</c:if><c:if test="${!empty memberCar.carLogo}">${memberCar.carLogo}</c:if>">
					</div>
				</div>
				<input type="hidden" name="carBrandId" id="carbrand_input" value="${memberCar.carBrandId}"/>
				<input type="hidden" name="carDeptId" id="carDept_input" value="${memberCar.carDeptId}"/>
				<input type="hidden" name="carId" id="car_input" value="${memberCar.carId}"/>
				<input type="hidden" name="carYear" id="carYear_input" value="${memberCar.carYear}"/>
				<input type="hidden" name="" id="carProductionMonth_input" value="${memberCar.carProductionMonth}"/> 
				<input type="hidden" name="id" id="memberCarId" value="${memberCar.id}" />
				<input type="hidden" name="isDefault" id="isDefault" value="${memberCar.isDefault}" />
				<input type="hidden" name="" id="useMonth_input" value="${memberCar.useMonth}" />
				<input type="hidden" name="" id="useYear_input" value="${memberCar.useYear}" />
				
				<div class="editright">
					<dl class="clearfix emptysom" style="position: relative;">
						<dt>
							<span>*</span>我的车型：
						</dt>
						<dd>
							<div class="select" id="js-myam_childbox">
								<label id="car_info">${memberCar.carName}&nbsp;${memberCar.carYear}</label>
								<a class="selectBut hidefocus" href="javascript:;" id="changeCar">选择车型</a>
							</div>
							<div id="chexingcansh" class="errors" style="display: none">请选择我的车型</div>
						</dd>
					</dl>
					<dl class="clearfix emptything" style="z-index: 3; position: relative;">
						<dt>生产月份：</dt>
						<dd>
							<div class="mylist">
								<select class="x-input inputstyle" name="carProductionMonth" id="carProductionMonth" style="width: 106px">
									<option value="">生产月份</option>									
								</select>
							</div>
						</dd>
					</dl>
					<dl class="clearfix emptything">
						<dt>
							<span>*</span>爱车昵称：
						</dt>
						<dd>
							<input type="text" class="x-input inputstyle" name="carNickName" id="myaiche_name_u" maxlength="30" value="${memberCar.carNickName}" />
							<div class="errors" id="nichengnded" style="display: none">请填写您的爱车昵称</div>
						</dd>
					</dl>
					<dl class="clearfix emptything" style="z-index: -1;">
						<dt>新车上路时间：</dt>
						<dd>
							<div class="mylist">
								<ol>
									<li>
										<div class="sel">
										
											<select class="x-input inputstyle" name="useYear" id="useYear" style="width: 149px">
												<option value="">购买年份</option>
											</select>

											<select class="x-input inputstyle" name="useMonth" id="useMonth" style="width: 80px">
												<option value="">月份</option>
											</select>
										</div>
									</li>
								</ol>
								<div class="question">
									<a href="javascript:void(0)" class="hidefocus"></a>									
								</div>
							</div>
							<div class="c"></div>
						</dd>
					</dl>
					<div class="savemycar">
						<a id="js_add_ad_myadd" href="javascript:void(0)" class="hidefocus">保存</a>
					</div>
				</div>
				<!--editright-->
				<div class="c"></div>
			</div>
			<!--editcar-->
		</div>
		<!--managecar-->
	</div>
</div>
</form>
</body>
</html>

