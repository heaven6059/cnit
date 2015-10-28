/**
 * 发布虚拟商品
 */
var pics = [];
var defaultPic = "";
var isDefault = true;
$(function() {
	findMemberLevel();
	onchangeType();
	if($("#status").val()=='1' ){
		var type=$("#type").val();
		if(type==1){
			$('#coupon_conditions').show();
			$('#coupon_solution').show();
		}
		$("#btn_coupon_next").hide();
		$("#c_coupon_prev").hide();
		$("#c_coupon_next").hide();
		$("#s_coupon_prev").hide();
		$("#s_coupon_next").html("关闭");
		$("#finish").show();
	}else{
		$('#coupon_conditions').hide();
		$('#coupon_solution').hide();
	}
	createTimeCombox($('#start_time_hh'),$('#start_time_hh_hide').val(),24);
	createTimeCombox($('#start_time_mm'),$('#start_time_mm_hide').val(),60);
	createTimeCombox($('#end_time_hh'),$('#end_time_hh_hide').val(),24);
	createTimeCombox($('#end_time_mm'),$('#end_time_mm_hide').val(),60);
	
	if($('#cpnsId').val()!=null && $('#cpnsId').val()!=''){ // 编辑
		$.ajax({
			 url:_path+"/coupons/getCatAndBrand",
			 data : {couponId:$('#cpnsId').val()} ,
			 type : "post" , 
			 datatype : "json" , 
			 success : function(data) {
				 if (data.head.retCode == '000000') {
					 	var str = '';  //选择的类目名称
					 	var catsAndBrand = '';  //选择的类目id+品牌id
						$.each(data.content,function(k,v){
							str+=v.catName+'>'+v.brandName+'、';
							catsAndBrand+=v.catId+"|"+v.brandId;
						});
						if(str!=''){
							$('#chooseCats').html(str.substring(0, str.length - 1));
							$('#resultId').val(catsAndBrand);
						}
						$("#catAndBrand").virtualCategoryDialog({ url : _path + '/cate/getChildTree' ,
							parentCatId:$('#parentCateId').val(),id:'catAndBrand',
							chooseCats:'chooseCats',result:"resultId",
							data:data.content
						});
					} else {
						$.messager.alert('错误', '获取数据失败', 'error');
					}
			 }
		});
	}else{
	
		$("#catAndBrand").virtualCategoryDialog({ url : _path + '/cate/getChildTree' ,
			parentCatId:$('#parentCateId').val(),id:'catAndBrand',
			chooseCats:'chooseCats',result:"resultId"
		});
	}
	
	
	/**优惠基本信息下一步*/
	$('#btn_coupon_next').click(function(){
		if (!$("#form_saveVirtualGoods").form('validate')) {
			easyuiMsg('提示', "请填写完整信息！");
			return false;
		}
		
		if(new Date($('#fromTimeStr').datebox('getValue')).format("yyyy-MM-dd")==new Date($('#toTimeStr').datebox('getValue')).format("yyyy-MM-dd")){
			if($("#start_time_hh").val()>$("#end_time_hh").val()){
				easyuiMsg('提示', "结束日期小于开始日期！");
				return false;
			}else if($("#start_time_hh").val()==$("#end_time_hh").val()){
				if($("#start_time_mm").val()>$("#end_time_mm").val()){
					easyuiMsg('提示', "结束日期小于开始日期！");
					return false;
				}
			}
		}
		
		if ($(".mlev input[name='memberLvIds']:checked").length == 0) {
			easyuiMsg('提示', "请选择会员级别！");
			return false;
		}
		if (parseInt($("#onlineQuantity").val())< parseInt($("#onlineLimit").val())) {
			easyuiMsg('提示', "发放数量不能小于领取数量");
			return false;
		}
		
		$('#coupon_conditions').show();
		$('#coupon_basic').hide();
		$('#coupon_solution').hide();
	});
	
	/**优惠条件下一步*/
	$('#c_coupon_next').click(function(){
		if ($(".can input[name='cTemplate']:checked").length == 0) {
			easyuiMsg('提示', "请选择优惠条件！");
			return false;
		}
		if($('input[name="cTemplate"]:checked').parent().parent().find('.tableform input').length>0){ //是否存在input
			if ($($(".can .tableform").find('input:visible')).val() == '') {
				easyuiMsg('提示', "请填写完整信息！");
				return false;
			}
		}
		$('#coupon_conditions').hide();
		$('#coupon_basic').hide();
		$('#coupon_solution').show();
		$("#finish").show();
	});
	
	/**优惠条件上一步*/
	$('#c_coupon_prev').click(function(){
		$('#coupon_conditions').hide();
		$('#coupon_basic').show();
		$('#coupon_solution').hide();
	});
	
	/**优惠方案上一步*/
	$('#s_coupon_prev').click(function(){
		$('#coupon_conditions').show();
		$('#coupon_basic').hide();
		$('#coupon_solution').hide();
		$("#finish").hide();
	});
	
	/**优惠条件选择*/
	$(".can  input[name='cTemplate']").click( function() { // 绑定点击事件
		$(this).parent().parent().parent().find('.tableform').hide();
		$(this).parent().parent().find('.tableform').show();
	});
	
	
	/**优惠方案选择*/
	$(".can_solution  input[name='sTemplate']").click( function() { // 绑定点击事件
		$('.solution_li').find('li').hide();
		$('.solution_li').find('li').eq($(this).attr('sort')).show();
	});
	
	// 初始化上传图片插件
	$("#goods_picture").zyUpload({ width : "600px" , // 宽度
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
		//var pic = json.retMsg;
		if (json.retCode == "000000") {
			pics.push(pic[0]);
			if(isDefault) {
				defaultPic = pic[1];
				isDefault = false;
			}
			//alert("pics="+pics);
			//createPic(pic, file.index);
		} else if (json.retCode == "000003") {
			parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
		}
	} , onDelete : function(file, files) { // 删除一个文件的回调方法
		 //$("#prev_" + file.index).remove(); // 删除选择货品图片
		pics.splice(file.index,1);
		//alert("pics1="+pics);
	} });
	
	
	/**保存*/
	$('#s_coupon_next').click(function(){
		var type=$("#type").val();
		if($("#status").val()=='1'){
			window.location.href=_path+"/coupons/couponsList?t="+type+"";
			return false;
		}
		if (type==1 && $(".can_solution input[name='sTemplate']:checked").length == 0) {
			easyuiMsg('提示', "请选择优惠方案！");
			return false;
		}
		if(type==1 &&  $('.solution_li').find('input:visible').length>0){ //是否存在input
			if ($($('.solution_li').find('input:visible')).val() == '') {
				easyuiMsg('提示', "请填写完整信息！");
				return false;
			}
		}
		var params = biz.serializeObject($("#form_saveVirtualGoods"));
		//var fromDate = new Date(params.fromTime+" "+$('#start_time_hh').val()+":"+$('#start_time_mm').val()+":00");
		//var toDate =  new Date(params.toTime+" "+$('#end_time_hh').val()+":"+$('#end_time_mm').val()+":00");
		//params.fromTime = fromDate;
		//params.toTime = toDate;
		params.fromTimeStr = params.fromTimeStr+" "+$('#start_time_hh').val()+":"+$('#start_time_mm').val()+":00";
		params.toTimeStr = params.toTimeStr+" "+$('#end_time_hh').val()+":"+$('#end_time_mm').val()+":00";
		
		var cTemplate = $("input[name='cTemplate']:checked").val();
		if(cTemplate == "promotion_conditions_order_subtotalallgoods") {
			params.conditions = $("#conditionsVal1").val();
		}else if(cTemplate == "promotion_conditions_order_itemsquanityallgoods") {
			params.conditions = $("#conditionsVal2").val();
		}else {
			params.conditions = "all";
		}
		var sTemplate = $("input[name='sTemplate']:checked").val();
		if(sTemplate == "promotion_solutions_addscore") {
			params.actionSolution = $("#addscore").val();
		}else if(sTemplate == "promotion_solutions_topercent") {
			params.actionSolution = $("#topercent").val();
		}else if(sTemplate == "promotion_solutions_bypercent") {
			params.actionSolution = $("#bypercent").val();
		}else if(sTemplate == "promotion_solutions_byfixed") {
			params.actionSolution = $("#byfixed").val();
		}else {
			params.actionSolution = $("#getcoupon").val();
		}
		
		params.cpnsPrefix = "B"+params.cpnsPrefix; //B券类型
		params.bigPic = defaultPic;
		var picIdStr = "";
		$.each(pics, function(i, n) {
			picIdStr += n + ",";
		});
		picIdStr=picIdStr.length>1?picIdStr.substring(0, picIdStr.length-1):picIdStr; //除开没有数据的情况
		params.picIds = picIdStr;
		var url  = _path + '/coupons/updateCoupons';  //编辑
		if(params.cpnsId=='' || params.cpnsId==null){ //新增
			url  = _path + '/coupons/saveCoupons';
		}
		console.log(params);
		$.ajax({ url : url , data : params , type : "post" , datatype : "json" , success : function(data) {
			if (data.head.retCode == '000000') {
				window.location.href=_path+"/coupons/couponsList?t="+type+"";
			}else if (data.head.retCode == '000004') {
				$.messager.alert('错误', '保存失败，号码已经存在', 'error');
			}else {
				$.messager.alert('错误', '保存失败', 'error');
			}
		} });
	});
	
	//初始化图片
	initPic();
	
	// 显示分类对话框
	$('#gEditor-GCat-category').click(function() {
		$("#catAndBrand").show();
	});
	$("#closeCatAndBrand").click(function(){
		$("#catAndBrand").hide();
	});
	
});

function initPic(){
	var cpnsId = $("#cpnsId").val();
	commonAjax(_path + '/coupons/pic', { 'cpnsId' : cpnsId }, function(data) {
		createEditPic(data.content);
	}, null);
}

function createEditPic(data){
	var html = '';
	var picName =[];
	var pic = [];
	$.each(data,function(i,v){
		picName = v.picturePath.split('\/');
		html += '<div id="uploadEdit_'+i+'" class="upload_append_list">';
		html += '<div class="file_bar">';
		html += '	<div style="padding: 5px;">';
		html += '	  <p class="file_name">'+picName[picName.length-1]+'</p>';
		html += '	  <span class="file_del" data-index="Edit_'+i+'" title="删除"></span>';
		html += '	</div>';
		html += ' </div>';
		html += ' <a style="height: 100px; width: 100px;" href="#" class="imgBox">';
		html += '	<div class="uploadImg" style="width: 85px">';
		html += '		<img id="uploadEditImage_'+i+'" class="upload_image" src="'+ yoyo.imagesUrl +v.picturePath+'"';
		html += '			style="width: expression(this.width &gt;   85 ?   85px :   this.width)">';
		html += '	</div>';
		html += '</a>';
		html += '<p id="uploadEditProgress_'+i+'" class="file_progress"	style="display: none; width: 100%;"></p>';
		html += '<p id="uploadEditFailure_'+i+'" class="file_failure">上传失败，请重试</p>';
		html += '<p id="uploadEditSuccess_Edit_'+i+'" class="file_success" style="display: block;" pictureId="'+v.pictureId+'"></p>';
		html += '<p id="uploadEditDefault_'+i+'" class="file_default">默认图</p></div>';
		pic[0]=v.pictureId;
		pic[1]=v.picturePath;
		defaultPic = pic[1];
		pics.push(pic[0]);
//		createPic(pic,"Edit_"+i); //创建货品选择图片相册
	});
	$('.add_upload').before(html);
	// 绑定删除按钮
	funBindDelEvent();
	funBindHoverEvent();
}
//绑定显示操作栏事件
function funBindHoverEvent(){
	$(".upload_append_list").hover(
		function (e) {
			$(this).find(".file_bar").addClass("file_hover");
		},function (e) {
			$(this).find(".file_bar").removeClass("file_hover");
		}
	);
}
//绑定删除按钮
 function funBindDelEvent(){
	if($(".file_del").length>0){
		// 删除方法
		$(".file_del").click(function() {console.log('upload'+$(this).attr("data-index"));
			var pictureId = $("#uploadEditSuccess_" + $(this).attr("data-index")).attr('pictureId');
			$('#upload'+$(this).attr("data-index")).remove();  //删除商品相册中的图片
			pics.remove(pictureId);  
			return false;	
		});
	}
	
}
/**获得会员等级*/
function findMemberLevel() {
	var ids = null;
	if($("#memberIds").val()!='' && $("#memberIds").val()!=null){  //编辑时，进行默认选择
		ids = $("#memberIds").val().split(',');
	}
	$.getJSON(biz.rootPath() + '/memberLevel/findAll', function(json) {console.log(json);
		var html = "";
		$.each(json.content, function(i, n) {
			html += '<input type="checkbox" name="memberLvIds" value="'+n.memberLvId+'" id="level_'+i+'"';
			if(ids!=null && ids.indexOf(n.memberLvId+'')!=-1){
				html += 'checked';
			}
			html += '><label for="level_'+i+'">'+n.name+'</label>';
		});
		$('#mLev').append(html);
	});

}

/** 创建时间下拉框 
 * $this:下拉框对象
 * val:默认选择的值 
 * limit:最大值
 */
function createTimeCombox($this,val,limit) {
	var html = '';
	for ( var i = 0; i < limit; i++) {
		if (i < 10) {
			html += '<option value="0' + i + '">0' + i + '</option>';
		} else {
			html += '<option value="' + i + '">' + i + '</option>';
		}
	}
	$this.append(html);
	if(val != '' && val!=null){
		$this.val(val);
	}else{
		$this.val('00');
	}
}

/*
 * 生成图片 pic:图片上传成功，返回的id与地址 index：图片在商品相册中的索引
 */
function createPic(pic, index,isChoose) {
	var html = '';
	html = '<div style="margin:2px 15px;float:left" id="prev_' + index + '">  ';  
	html += ' <input type="checkbox" name="spec_picture" value="' + yoyo.imagesUrl + pic[ 1 ] + '" pic_id="' + pic[ 0 ] + '" path="' + pic[ 1 ] + '" >';
	html += '<img src="' + yoyo.imagesUrl + pic[ 1 ] + '" width="62" height="62">';
	html += '</div>';

	$('.division').append(html);
	$(".division  input[name='spec_picture']").unbind('click');
	$(".division  input[name='spec_picture']").bind("click", function(e) { // 绑定点击事件
		if ($(this).attr("checked") == 'checked') {
			$(this).removeAttr("checked");
		} else {
			$(this).attr('checked', true);
		}
	});
}

/**判断时间大小*/
function onSelect(date) { 
    var issd = this.id == 'fromTime';
    var sd = issd ? date.format("yyyy-MM-dd") : new Date($('#fromTime').datebox('getValue')).format("yyyy-MM-dd");
    var ed = issd ? new Date($('#toTime').datebox('getValue')).format("yyyy-MM-dd") : date.format("yyyy-MM-dd");
    if (ed < sd) {
        $.messager.alert('错误', '结束日期小于开始日期！', 'error');
        //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
        $('#toTime').datebox('setValue', '').datebox('showPanel');
    }
}

function onchangeType(){
	var type=$("#type").val();
	if(type==2){
		$("#coupon_type").hide();
		$("#next_one").hide();
		$("#next_two").hide();
		$("#coupon_par").show();
		$("#coupon_price").show();
		$("#finish").show();
		$("#s_coupon_prev").hide();
	}else{
		$("#coupon_type").show();
		$("#next_one").show();
		$("#next_two").show();
		$("#coupon_par").hide();
		$("#coupon_price").hide();
		$("#finish").hide();
		$("#s_coupon_prev").show();
	}
}