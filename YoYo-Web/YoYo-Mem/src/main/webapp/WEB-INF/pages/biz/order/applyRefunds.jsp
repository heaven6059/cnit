<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
    String path = request.getContextPath();
    request.setAttribute("path", path);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>退款申请</title>
<link rel="stylesheet" type="text/css" href="/yoyomem/resources/styles/framework.css?v=${versionVal}" />
<link rel="stylesheet" type="text/css" href="${path}/resources/styles/member.css?v=${versionVal}" />
<link rel="stylesheet" href="${path}/resources/styles/zyUpload.css"	type="text/css">
<script src="${path}/resources/scripts/imageUpload/zyFile.js"></script>
<script src="${path}/resources/scripts/imageUpload/zyUpload.js"></script>
<script type="text/javascript">
$(function (){
	var _path = "${path}";
	var pics = ''; 	// 存放图片地址字符串
	var pic_size=[];
	//初始化上传图片插件
	$("#goods_picture").zyUpload({ 
		width : "934px" , // 宽度
		height : "200px" , // 高度
		itemWidth : "100px" , // 文件项的宽度
		itemHeight : "100px" , // 文件项的高度
		url : _path + "/image/uploadImg" , // 上传文件的路径
		multiple : false , // 是否可以多个文件上传
		path:_path,
		dragDrop : false , // 是否可以拖动上传文件
		del : true , // 是否可以删除文件
		finishDel : false , // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSuccess : function(file, data) { // 文件上传成功的回调方法
			var json = $.parseJSON(data);
			var pic = json.retMsg.split(";");
			pics =pics + pic[1] + ',' ;
			$('#memberImagePath').attr('value',pics);
			if (json.retCode == "000000") {
				pic_size.push(file.index);				
			} else if (json.retCode == "000003") {
				parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
			}
		}, onDelete : function(file, files) {// 删除一个文件的回调方法
			$.each(pic_size,function (x,index){
				if(index==file.index){
					pic_size.splice(x, 1);		
				}
			});
		}
	});
});

function submitForm(){
	if($("#memberName").val().length<=0){
		alert("请输入联系人姓名");
		return;
	}
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if(!reg.test($("#memberPhone").val())) {
      alert("请输入正确的手机号");
      return;
	};
	if($("#x-return-content").val().length<=0){
		$(".text-count").removeClass("hide");
		return;
	}
	 $.ajax({
			url:yoyo.memUrl+"/reship/applyRefunds",
			data:$("#reshipForm").serialize(),
			type:"post",
			dataType:"json",
			async: false,
			success:function(returnData){
				var resultCode = returnData.retCode;
		   		if (resultCode == yoyo.successCode) {
		   			layer.alert("退款申请提交成功！",{title:false,closeBtn:false,icon:1,end:function (){
		   					window.location.href = yoyo.memUrl+"/reship/refundsList";	
		   				}
		   			});
		   		}else{
		   			layer.alert("退款申请提交失败！",{title:false,closeBtn:false,icon:0});	
		   		}
			},	
		   	error:function(){
		   		layer.alert("退款申请提交失败！",{title:false,closeBtn:false,icon:0});
		   	}
	 });
}
</script>
</head>
<body>
	<div class="member-main">
	    <h1 class="title title2">申请退款</h1>
	    <form id="reshipForm" name="return_save" method="post">
	        <div class="FormWrap">
				<div class="division">
	                <h4 class="fontnormal">退款金额：<font color="red">￥${order.finalAmount}</font></h4>
	                <input type=hidden name="storeId" value="${order.storeId}" />
	                <input type=hidden name="companyId" value="${order.companyId}" />
	                <input type="hidden" name="amount" id="amount" value="${order.finalAmount}"/>
					<input type="hidden" name="safeguardRequire" id="safeguardRequire" value="5"/>
					<input type="hidden" name="safeguardType" id="safeguardType" value="2"/>
					<input type="hidden" name="memberImagePath" id="memberImagePath"/>
					<input type="hidden" name="orderId" value="${order.orderId}"/>
					<input type="hidden" name="status" value="1" />
					<input type="hidden" name="isSafeguard" value="1" />
	            </div>
	            <div class="division">
	            	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="liststyle data width1" style="border: none">
	            		<tr>
							<th><em><font color="red">*</font></em>联系人姓名：</th>
							<td>
								<input autocomplete="off" class="x-input inputstyle" style="width: 315px" type="text" name="memberName" value="${order.member.name}" id="memberName"  maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th><em><font color="red">*</font></em>联系人手机：</th>
							<td>
								<input autocomplete="off" class="x-input inputstyle" style="width: 315px" type="text" name="memberPhone" value="${order.member.mobile}" id="memberPhone"  maxlength="11"/>
							</td>
						</tr>
						<tr>
							<th>联系人地址：</th>
							<td>
								<input autocomplete="off" class="x-input inputstyle" style="width: 315px" type="text" name="refundAddress"  />
							</td>
						</tr>
	            	</table>
	            </div>
				<div class="division">
	                <h4 class="fontnormal"><em><font color="red">*</font></em>退款说明：</h4>
	                <textarea style="width:98%" rows="5" cols="80" class="x-inputs x-input" name="content" id="x-return-content" type="textarea"></textarea>
		            <div class="text-count hide">
						<span class="msg-error">麻烦填写10-300个字</span>
					</div>
					<div class="clearfix"></div>
	            </div>
				<div class="division">
					<div class="row-item first-row-item">
						<div class="ctitle">
							<label>上传凭证：</label>
						</div>
						<div class="info" >
							<div class="goods-image-div" id="goods_picture"></div>
							<div style="clear: both;"></div>
						</div>
						<div >
							<div class="info">
								<div class="J_ImgHelpMsg help-msg">每张图片不超过2M，支持GIF，JPG，JPEG，PNG，BMP格式</div>
							</div>
						</div>
						<div class="clearfix"></div>
					</div>
	            </div>
				<div class="textcenter p10 division">
					<button type="button" onclick="submitForm()" class="btn order-btn"><span><span>提交申请</span></span></button>
				</div>
	    </div>
	</form>
	</div>
</body>
</html>