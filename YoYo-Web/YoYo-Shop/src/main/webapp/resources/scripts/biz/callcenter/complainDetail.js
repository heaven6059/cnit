var pics = ''; 	// 存放图片地址字符串
$(document).ready(function(){
	debugger
	if(orderId.startsWith('333')){
		$('#J_BabyDetail').hide();
	}
	$(".member-main").find("img").each(function (x,item){
		$(item).attr("src",yoyo.imagesUrl+$(item).attr("src"));
	});
	
	$(".J_Pics a").each(function (x,item){
		$(item).attr("href",yoyo.imagesUrl+$(item).attr("href"));
	});
	$('.J_Pics').magnificPopup({
          delegate: 'a',
          type: 'image',
          closeOnContentClick: false,
          closeBtnInside: false,
          mainClass: 'mfp-with-zoom mfp-img-mobile',
          image: {
            verticalFit: true,            
          },
          gallery: {
            enabled: true
          },
          zoom: {
            enabled: true,
            duration: 300, // don't foget to change the duration also in CSS
            opener: function(element) {
              return element.find('img');
            }
          }
    });
	
	
	// 初始化上传图片插件
	$("#goods_picture").zyUpload({ width : "630px" , // 宽度
		height : "200px" , // 高度
		itemWidth : "100px" , // 文件项的宽度
		itemHeight : "100px" , // 文件项的高度
		url : _path + "/image/uploadImg" , // 上传文件的路径
		path:_path,
		multiple : false , // 是否可以多个文件上传
		dragDrop : false , // 是否可以拖动上传文件
		del : true , // 是否可以删除文件
		finishDel : false , // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSuccess : function(file, data) { // 文件上传成功的回调方法
			var json = $.parseJSON(data);
			var pic = json.retMsg.split(";");
			pics =pics + pic[1] + ',' ;
			$('#imagePath').attr('value',pics);
			if (json.retCode == "000000") {
			} else if (json.retCode == "000003"){
				parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
			}
		}
	});
	
	
	//保存
	$("#save_button").click(function() {
		var params = biz.serializeObject($("#formId"));
		if(($(".msg-textarea").val().length<10)||($(".msg-textarea").val().length>300)){
			$(".text-count").removeClass("hide");
			return;
		}
		submitForm(params);
	});
	
	var limitNum = 300;
	var pattern = '还可以输入' + limitNum + '字符';
	$('.J_TextCount').html(pattern);
	$('.msg-textarea').keyup(function() {
		var remain = $(this).val().length;
		if (remain > 300) {
			pattern = "字数超过限制！";
		} else {
			var result = limitNum - remain;
			pattern = '还可以输入' + result + '字符';
		}
		$('.J_TextCount').html(pattern);
	});
	
});


function submitForm(params){
	var url=yoyo.shopUrl+"/complain/saveComplainComment";
	data = params;
	complainId = params.complainId;
	 yoyo.ajaxRequest(url,data,true,"json",function(data){		   	
		   var resultCode = data.retCode;
		   if (resultCode == yoyo.successCode) {
		   		window.location.href = yoyo.shopUrl+"/complain/complainDetail?complainId="+complainId; 
		   } else {
		   		layer.alert("投诉失败！",{title:false,closeBtn:false,icon:0});
		   }
	 });
}



