/**
 * 发布商品js
 */
// SKU信息组合
var arrayInfor = new Array();// 存放每组选中的CheckBox值的对象
var arrayTile = new Array();// 规格标题组数
var picArr = new Array(); 	// 存放规格的图片地址
var productArr = [ ]; 		// 所有规格的组合
var attrArr = [ ]; 			// 属性值id集合
var isChooseSpec = false; 	// 是否选择规格
var editSpec = false;  //编辑时，上次发布的商品是否选择规格false:开启，true：未开启
var CATAGORY_TYPE = 0;      //分类的类型：999表示整车
var selectRelateGoodsIds = new Array();   //分页选择的相关商品id
var selectRelateGoodsObj = {};   //分页选择的相关商品对象
var GOODS_ID = null;
var CAT_ID = null;   
var ACC_ID = null;  //配件id
var catObj = {};   //待审核的分类id对象
var num = 0; // 车型的排数（每4个一排）
var um = null;
$(function() {
	
	//1.判断店铺是否需要进行商品审核
	judgeGoodsCheck();
	
	// 分级分类获得规格与属性集合
	$("#category_dialog").categoryDialog({ url : _path + '/cate/getChildTree' ,parentCatId:$('#parentCateId').val(), onSuccess : function(rootCategoryId,categoryType, label) {
			$('#labelCategory').text(label);
			CATAGORY_TYPE=categoryType;
			$("#rootCategoryId").val(CATAGORY_TYPE);
			console.log(categoryType+" categoryType");
			loadBaseData(false,categoryType);
			CAT_ID = $('#cat_id').val();
			if(CATAGORY_TYPE==yoyo.car){
				showSearch();//通过搜索选择车型
			}
			showSaveBtn();
		}

	});
	// 汽车厂商改变
	$("#factoryId").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#carDeptId").empty();
	//	$("#carDeptId").val('--请选择--').trigger("change");
		$("#carDeptId").select2({ placeholder : "--请选择--" });
		$("#carYearValue").empty();
		$("#carTypeData").empty();
		$("#carYearValue").select2({ placeholder : "--请选择--" });
		$("#carTypeData").select2({ placeholder : "--请选择--" });
		if($("#factoryId").val()!="-1"){
			getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + $("#factoryId").val(), 'carDeptId', true,null);// 获取车系下拉列表数据
		}
	});
	
	var appointTypeSet=[{"text":"--请选择--","id": -1,selected:true},{"text":"工作日","id":1},{"text":"周末","id":2},{"text":"节假日","id":3}];
	$('#appointType').select2({data:appointTypeSet});
	
	$("#appointType").on("select2:select", function(e) {
		var appointType = $(this).select2("val");
		if(appointType == 3) {
			$('.appoint-value-table').show();
		}else {
			$('.appoint-value-table').hide();
		}
	});

	// 汽车车系改变
	$("#carDeptId").on("select2:select", function(e) {
		createCar($("#carDeptId").val());
		// 清空后面下拉框的数
		$("#carYearValue").empty();
		$("#carYearValue").select2({ placeholder : "--请选择--" });
		$("#carTypeData").empty();
		$("#carTypeData").select2({ placeholder : "--请选择--" });
		if($("#carDeptId").val()!="-1"){
			getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + $("#carDeptId").val(), 'carYearValue', true,null);// 获取年款下拉列表数据
		}
	});

	// 汽车年款改变
	$("#carYearValue").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#carTypeData").empty();
		$("#carTypeData").select2({ placeholder : "--请选择--" });
		if($("#carYearValue").val()!=-1){
			getCarData(biz.rootPath() + '/car/carList?carYearId=' + $("#carYearValue").val(), 'carTypeData', true,null);// 获取车型下拉列表数据
		}
	});
	
	// 汽车车型改变
	$("#carTypeData").on("select2:select", function(e) {
		// 车型自动设置商品名称
		$('#id_gname').val(e.params.data.text);
		$('#carId').val(e.params.data.id);
		loadSpec(false,e.params.data.id); // 加载规格
	});
	

	// 显示分类对话框
	$('#gEditor-GCat-category').click(function() {
		$("#category_dialog").show();
	});

	$('.dialog_close').click(function() {
		$('#products_pic_dialog').hide();
		$("#category_dialog").hide();
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
		if (json.retCode == "000000") {
			createPic(pic, file.index);
		} else if (json.retCode == "000003") {
			parent.$.messager.alert('提示', '上传失败！' + json.retMsg, 'error');
		}
	} , onDelete : function(file, files) { // 删除一个文件的回调方法
		 $("#prev_" + file.index).remove(); // 删除选择货品图片

	} });

	// 在线html编辑器
	//$('#cle-good-detail-info').cleditor();
	//实例化编辑器
	//实例化编辑器
    um = UM.getEditor('cle-good-detail-info');
	
	// 添加配件
	$('#button-good-edit-acc-add').on('click', function() {
		$('#toolbar-good-edit-acc-info-group').addaccessory('kreatePanel', { renderAim : '#good-edit-acc-info-from-group' , toolbar : '#toolbar-good-edit-acc-info-group' });
	});

	//初始化编辑
	initEdit();
	
	var smallNum = 0;
	// 点击banner下广告切换
	$('.right_btn').click(function(e) {
		smallNum++;
		if (smallNum == num) {
			smallNum = 0;
		}
		var numZhi = smallNum * -880;
		numZhi=(numZhi==0)?10:numZhi;
		$('.b_ad_ul').stop().animate({
			'left' : '' + numZhi + 'px'
		}, 300);
	});

	$('.left_btn').click(function(e) {
		smallNum--;
		if (smallNum == -1) {
			smallNum = 0;
		}
		var numZhi = smallNum * -880;
		numZhi=(numZhi==0)?10:numZhi;
		$('.b_ad_ul').stop().animate({
			'left' : '' + numZhi + 'px'
		}, 300);
	});
});


function deleteRelateGoods(obj,goodsId){
	$(obj).parent().remove();
}

// 加载规格
function loadSpec(isEdit,carId) {
	var arg_data = {};
	console.log("cat_id=" + $('#cat_id').val() + " brandId=" + $("#brand_id").val());
	arg_data.catId = $('#cat_id').val();
	arg_data.carId = carId;
	$.ajax({ url : _path + '/cate/getDetail' , type : "post" , data : arg_data , datatype : "json" , success : function(result) {
		var data = $.parseJSON(result.content); // 获得扩展属性与规格
		$('.attrTable').empty(); // 清空属性与规格
		clearSpec();
		if (data.attrs!=null && data.attrs.length > 0) {
			if(isEdit){
				setAttrValue();
			}else{  //加载属性
				setAttr(data.attrs);
			}
		}
		if(isEdit){ //编辑
			getSpecByCatId(CAT_ID,GOODS_ID,carId);
		}else{
			getSpecByCatId(CAT_ID,null,carId);
		}
	} });
}

// 清空规格
function clearSpec() {
	$('.specs').empty();
	$('.spec-values-list').empty();
	$('.spec-value-table table').empty();
	$('.product-table-body').empty();
}

function showTip(tip, type) {
    var $tip = $('#tip');
    $tip.attr('class', 'alert alert-' + type).text(tip).css('margin-left', - $tip.outerWidth() / 2).fadeIn(500).delay(2000).fadeOut(500);
}

// 提交商品信息
function submitGoods(params) {
	var accessoryInfo = getAccessoryInfo();
	if(accessoryInfo != null) {
		var minBuyInt = 0;
		var maxBuyInt = 0;
		for(var i=0;i<accessoryInfo.length;i++) {
			if(accessoryInfo[i].accGroupName == null || accessoryInfo[i].accGroupName == '') {
				$.messager.alert('错误', '套装组名称不能为空！', 'error');
				return;
			}
			if(accessoryInfo[i].minBuy == null || accessoryInfo[i].minBuy == '') {
				$.messager.alert('错误', '最小购买量不能为空！', 'error');
				return;
			}
			if(accessoryInfo[i].maxBuy == null || accessoryInfo[i].maxBuy == '') {
				$.messager.alert('错误', '最大购买量不能为空！', 'error');
				return;
			}
			
			if(null != accessoryInfo[i].minBuy) {
				minBuyInt = parseInt(accessoryInfo[i].minBuy);
			}
			if(null != accessoryInfo[i].maxBuy) {
				maxBuyInt = parseInt(accessoryInfo[i].maxBuy);
			}
			
			if(minBuyInt>maxBuyInt) {
				$.messager.alert('错误', '最大购买量小于最小购买量！', 'error');
				return;
			}
			if(accessoryInfo[i].credit == null || accessoryInfo[i].credit == '') {
				$.messager.alert('错误', '优惠折扣不能为空！', 'error');
				return;
			}
			if(accessoryInfo[i].accessoryGoods == null || accessoryInfo[i].accessoryGoods == '') {
				$.messager.alert('错误', '请添加套装商品！', 'error');
				return;
			}
		}
	}
	/*if('error' == accessoryInfo[0]) {
		return;
	}*/
	params.accessoryJson = JSON.stringify(accessoryInfo);
	
	if(CATAGORY_TYPE != yoyo.car) {
		var appointType = $('#appointType').val();
		if(appointType > 0) {
			if(appointType == 3) {
				params.fromTime = new Date($('#fromTime').datebox('getValue'));
				params.toTime = new Date($('#toTime').datebox('getValue'));
			}else {
				params.fromTime = new Date();
				params.toTime = new Date();
			}
			params.appointType = appointType;
			if($('#timenum1').val() == null || $('#timenum1').val() == '') {
				params.timenum1 = 0;
			}else {
				params.timenum1 = $('#timenum1').val();
			}
			if($('#timenum2').val() == null || $('#timenum2').val() == '') {
				params.timenum2 = 0;
			}else {
				params.timenum2 = $('#timenum2').val();
			}
			if($('#timenum3').val() == null || $('#timenum3').val() == '') {
				params.timenum3 = 0;
			}else {
				params.timenum3 = $('#timenum3').val();
			}
			if($('#timenum4').val() == null || $('#timenum4').val() == '') {
				params.timenum4 = 0;
			}else {
				params.timenum4 = $('#timenum4').val();
			}
			if($('#timenum5').val() == null || $('#timenum5').val() == '') {
				params.timenum5 = 0;
			}else {
				params.timenum5 = $('#timenum5').val();
			}
			if($('#timenum6').val() == null || $('#timenum6').val() == '') {
				params.timenum6 = 0;
			}else {
				params.timenum6 = $('#timenum6').val();
			}
			if($('#timenum7').val() == null || $('#timenum7').val() == '') {
				params.timenum7 = 0;
			}else {
				params.timenum7 = $('#timenum7').val();
			}
			if($('#timenum8').val() == null || $('#timenum8').val() == '') {
				params.timenum8 = 0;
			}else {
				params.timenum8 = $('#timenum8').val();
			}
		}
		
		//params.priceStartTime = new Date($('#priceStartTime').datebox('getValue') + " 00:00:00");
		//params.priceEndTime = new Date($('#priceEndTime').datebox('getValue') + " 00:00:00");
		params.priceStartTime = params.priceStartTime+ " 00:00:00";
		params.priceEndTime = params.priceEndTime+ " 00:00:00";
	}
	

	
	params.catId = $('#cat_id').val();
	params.brandId = $("#brand_id").val();

	params.productId = $("#productId").val();
	params.defaultPic = $($(".division input[name='spec_picture']").eq(0)).attr('path');
	params.defaultPicId = $($(".division input[name='spec_picture']").eq(0)).attr('pic_id');

	// 关联商品
	var relateGoodsJson=new Array();
	$.each($('#good-edit-relate').find('li'), function(index, liele) {
		var $liele = $(this);
		var asstype=$liele.find('input[name="asstype_' + $liele.attr('id') + '"]:checked').val();
		relateGoodsJson.push({'goods1':$("#goodsId").val(),'goods2':$liele.attr('id'),'manual':asstype});
	});
	params.relateGoodsJson=JSON.stringify(relateGoodsJson);
	
	// 获得扩展属性的json字符串
	var attrJson = "[";
	$.each(attrArr, function(i, v) {
		attrJson += '{\"value\":\"' + params[ "attr_" + v ] + '\",\"id\":' + v + '},';
	});
	if (attrArr.length > 0) {
		attrJson = attrJson.substring(0, attrJson.length - 1);
	}
	attrJson += "]";
	params.attrJson = attrJson;
	//console.log($('#cle-good-detail-info').val()+":ddddd");
	//params.intro = $('#cle-good-detail-info').val();
	params.intro =$.trim(um.getPlainTxt());
	if (params.intro == '' || params.intro == null) {
		easyuiMsg('提示', "请填写详细信息！");
		return false;
	}
	console.log(params);
	var url = '';
	if($("#goodsId").val()==null ||$("#goodsId").val()=='' ){ //新增
		url = _path + '/goodsPublish/saveGoods';
	}else{													  //更新
		url = _path + '/goodsPublish/updateGoods';
	}
	yoyo.ajaxRequest(url,params,"","json",function(data){
		if (data.head.retCode == '000000') {
			//$.messager.alert('提示', '保存成功', 'info', function() {
				//window.location.reload();
				/*if(params.marketable=='-1'){ //不上架，暂存本地，跳转到仓库
					window.location.href=_path+'/goods/loadLocationList?marketableQ=-1';
				}else if(params.marketable=='1'){
					window.location.href=_path+'/goods/loadGoodsList';
				}else { //审核中
					window.location.href = yoyo.shopUrl+'/goods/loadCheckingList?marketableQ=2&isChecking=1';  
				}*/
				if(CATAGORY_TYPE==yoyo.car){
					$('.b_ad_li').find("li.liVisited").addClass("li_save_car");
					clearCarData();
				}else{
					if(params.marketable=='-1'){ //不上架，暂存本地，跳转到仓库
						window.location.href=_path+'/goods/loadLocationList?marketableQ=-1';
					}else if(params.marketable=='1'){
						window.location.href=_path+'/goods/loadGoodsList';
					}else { //审核中
						window.location.href = yoyo.shopUrl+'/goods/loadCheckingList?marketableQ=2&isChecking=1';  
					}
				}
				if(params.marketable=='1') {
					layer.msg('上架成功',{time: 3000});
					//easyuiMsg('提示', "上架成功");
				}else if(params.marketable=='-1') {
					//$("#errormsg").html("您的信息上架成功，请重试!").show(300).delay(3000).hide(300); 
					layer.msg('暂存成功',{time: 3000});
					//easyuiMsg('提示', "暂存成功");
				}
				
			//});
		} else if (data.head.retCode == '000004') {
			easyuiMsg('错误', "该商品编号已经存在！");
		} else if (data.head.retCode == '000008') {
			easyuiMsg('错误', "货品编号已经存在！");
		} else if (data.head.retCode == '000010') {
			$.messager.alert('提示', '店铺商品已达到店铺的最大发布数量!', 'info', function() {
				window.location.href=_path+'/goods/loadLocationList?marketableQ=-1';
			});
		} else if (data.head.retCode == '000011') {
			easyuiMsg('错误', "店铺违规，限制发布商品或开启规格选择未违规的分店进行发布商品！");
		}else {
			//$.messager.alert('错误', '保存失败', 'error');
			if(params.marketable=='1') {
				//easyuiMsg('错误', "上架失败");
				layer.msg('上架失败',{time: 3000});
			}else if(params.marketable=='-1') {
				//easyuiMsg('错误', "暂存失败");
				layer.msg('暂存失败',{time: 3000});
			}
		}
	});
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

// 设置扩展属性
function setAttr(data) {
	var html = '';
	attrArr = [ ];
	$.each(data, function(i, v) {
		attrArr.push(v.attrId);
		html += ' <tr><td align="right">' + v.attrName + '：</td><td>';
		if (v.attrInputType == 'select') { // 属性值是下拉框
			html += '<select autocomplete="off" class="easyui-combobox"';
			html += 'name="attr_' + v.attrId + '"  >';
			$.each(v.attrValues.split('|'), function(index, value) {
				html += '<option value="' + value + '">' + value + '</option>';
			});
			html += '</select>';

		} else { // 文本
			html += '<input  class="x-input"  ';
			html += 'name="attr_' + v.attrId + '" maxlength="30"  type="text" />';
		}
		html += '</td></tr>';
	});
	$('.attrTable').append(html);
	$.parser.parse('.attrTable');
}


// 关闭规格div
function specclose() {
	$.messager.confirm('确认', '关闭后货品数据将不能恢复，确定关闭规格？', function(r) {
		if (r) {
			clearSpec();
			$('.spec-panel').hide();
			$("#nospec_body").show();
			$('.openspec').show();
			$('.closespec').hide();
			// 把所有图片关联到没有规格的这个货品上
			picArr = [ ];
			var pic = "";
			// 获取选择的图片的地址
			$(".division input[name='spec_picture']").each(function() {
				pic += $(this).attr('pic_id') + "|";
			});
			picArr[ 0 ] = pic.substring(0, pic.length - 1);
			isChooseSpec = false;
			
		}
	});

}

// 打开规格div
function openclose() {
	clearSpec();
	getSpecByCatId(CAT_ID,null,null);
	picArr = [ ];
	$('.spec-panel').show();
	$("#nospec_body").hide();
	$('.openspec').hide();
	$('.closespec').show();
	isChooseSpec = true;
}


function appointclose() {
	$.messager.confirm('确认', '关闭后预约时间数据将不能恢复，确定关闭预约时间？', function(r) {
		if (r) {
			//$('.product-table-body').empty();
			$("#appointType").val('-1').trigger("change");
			$("#timenum1").val("");
			$("#timenum2").val("");
			$("#timenum3").val("");
			$("#timenum4").val("");
			$("#timenum5").val("");
			$("#timenum6").val("");
			$("#timenum7").val("");
			$("#timenum8").val("");
			$('#toTime').datebox('setValue', '');
			$('#fromTime').datebox('setValue', '');
			$('.appoint-value-table').hide();
			$('.appoint-panel').hide();
			$('.openappoint').show();
			$('.closeappoint').hide();
		}
	});

}

function appointopen() {
	//$('.product-table-body').empty();
	$("#appointType").val('-1').trigger("change");
	$("#timenum1").val("");
	$("#timenum2").val("");
	$("#timenum3").val("");
	$("#timenum4").val("");
	$("#timenum5").val("");
	$("#timenum6").val("");
	$("#timenum7").val("");
	$("#timenum8").val("");
	$('#toTime').datebox('setValue', '');
	$('#fromTime').datebox('setValue', '');
	$('.appoint-value-table').hide();
	$('.appoint-panel').show();
	$('.openappoint').hide();
	$('.closeappoint').show();
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

/**判断价格区间时间大小*/
function onSelectPriceTime(date) { 
    var issd = this.id == 'priceStartTime';
    var sd = issd ? date.format("yyyy-MM-dd") : new Date($('#priceStartTime').datebox('getValue')).format("yyyy-MM-dd");
    var ed = issd ? new Date($('#priceEndTime').datebox('getValue')).format("yyyy-MM-dd") : date.format("yyyy-MM-dd");
    if (ed < sd) {
        $.messager.alert('错误', '结束日期小于开始日期！', 'error');
        //只要选择了日期，不管是开始或者结束都对比一下，如果结束小于开始，则清空结束日期的值并弹出日历选择框
        $('#priceEndTime').datebox('setValue', '').datebox('showPanel');
    }
}



// 打开关联商品相册
function showPic(obj,e) {
	// 判断是否有选择的图片，有的话，需要选中
	$(".division input[name='spec_picture']").removeAttr("checked");
	$('#product_spec_id').val($(obj).attr('cur_spec_id'));
	var picId = '';
	var $this = '';
	var tempStr='';
	$(".division input[name='spec_picture']").each(function() {
		picId = $(this).attr('pic_id');
		$this = $(this);
		tempStr = picArr[$('#product_spec_id').val()]+'';
		
		if (picArr[ $('#product_spec_id').val() ] != '') {console.log((tempStr.indexOf('|', 0)));
			if(tempStr.indexOf('|', 0)==-1){console.log("v="+tempStr+" pic="+picId+' '+(tempStr==picId));
				if(tempStr==picId){
					$this.prop('checked', true); 
				 }
			}else{
			 $.each(picArr[$('#product_spec_id').val()].split("|"),function(i,v){
				 console.log("v2="+v+" pic="+picId+' '+(v==picId));
				 if(v==picId){
					$this.prop('checked', true); 
				 }
			 });
			}
		}
		
	});
	console.log(getY(e));
	$('#products_pic_dialog').css("position","absolute"); 
	$('#products_pic_dialog').css('top',getY(e)-140);
	$('#products_pic_dialog').show();
	//$("products_pic_dialog").css('top', $(document).scrollTop() + ($(window).height()-650) * 0.5);//设置div的Y坐标
	
	
}



// 保存规格选择的商品图片
function saveSpecPic() {
	$('#products_pic_dialog').hide();
	if ($(".division input[name='spec_picture']:checked").length == 0) {
		return;
	}
	var defaultPic = '';
	var isDefault = false;
	picArr[ $('#product_spec_id').val() ] = '';
	$('#span_' + $('#product_spec_id').val()).children().remove(); // 清空图片
	var pic = "";
	// 获取选择的图片的地址
	$(".division input[name='spec_picture']:checked").each(function() {
		pic += $(this).attr('pic_id') + "|";
		if(!isDefault){
			defaultPic=$(this).attr('path');
			isDefault = true;
		}
		// 展示所选择的图片
		$('#span_' + $('#product_spec_id').val()).append('<img src="' + $(this).attr('value') + '" class="img_'+$(this).attr('pic_id')+'" width=28 height=28>');
	});
	if (pic != "") {
		picArr[ $('#product_spec_id').val() ] = pic.substring(0, pic.length - 1);
		$("#pic_"+$('#product_spec_id').val()).val(pic.substring(0, pic.length - 1));
		$("#pic_default_"+$('#product_spec_id').val()).val(defaultPic);  //货品默认图片
		
	}
}

/**
 * 删除选择的商品规格值
 * 
 * @param specId
 *            规格id
 * @param specValueId
 *            规格值id
 */
function deleteSpecValue(t) {
	var id = $(t).parent().parent().parent().attr('id');// 当前行的id
	var specValueId = id.substring(10);
	$('#' + id).remove();
	$('#tp_' + specValueId).removeAttr("checked");
	createSKU();
}

// 表格行上移
function up(t) {
	var i = $(t).parent().parent().parent().index();// 当前行的id
	if (i >= 1) {// 不是表头，也不是第一行，则可以上移
		var tem0 = $(t).parent().parent().parent().html();
		var tem1 = $(t).parent().parent().parent().prev().html();
		$(t).parent().parent().parent().prev().html(tem0);
		$(t).parent().parent().parent().html(tem1);
	}
}
// 表格行下移
function down(t) {
	var l = $("#typeDetailView tr").length;// 总行数
	var i = $(t).parent().parent().parent().index();// 当前行的id
	if (i < l - 1) {// 不是最后一行，则可以下移
		var tem0 = $(t).parent().parent().parent().html();
		var tem1 = $(t).parent().parent().parent().next().html();
		$(t).parent().parent().parent().next().html(tem0);
		$(t).parent().parent().parent().html(tem1);
	}
}



// 获取配件信息
function getAccessoryInfo() {
	
	// 配件
	var accessoryJson = [];
	$('.acc-group-form').each(function(index) {
		var $this = $(this);
		var formObject = {};
		formObject.accGroupName = $this.find('input[name="accGroupName"]').eq(0).val();
		formObject.minBuy = $this.find('input[name="minBuy"]').eq(0).val();
		formObject.maxBuy = $this.find('input[name="maxBuy"]').eq(0).val();
		/*if(null == formObject.accGroupName || '' == formObject.accGroupName) {
			$.messager.alert('错误', '套装组名称不能为空！', 'error');
			accessoryJson.push('error');
			return accessoryJson;
		}
		if(null == formObject.minBuy || '' == formObject.minBuy) {
			$.messager.alert('错误', '最小购买量不能为空！', 'error');
			accessoryJson.push('error');
			return accessoryJson;
		}
		if(null == formObject.maxBuy || '' == formObject.maxBuy) {
			$.messager.alert('错误', '最大购买量不能为空！', 'error');
			accessoryJson.push('error');
			return accessoryJson;
		}
		var minBuyInt = 0;
		var maxBuyInt = 0;
		if(null != formObject.minBuy) {
			minBuyInt = parseInt(formObject.minBuy);
		}
		if(null != formObject.maxBuy) {
			maxBuyInt = parseInt(formObject.maxBuy);
		}
		
		if(minBuyInt>maxBuyInt) {
			$.messager.alert('错误', '最大购买量小于最小购买量！', 'error');
			accessoryJson.push('error');
			return accessoryJson;
		}*/
		
		formObject.discType = $this.find('input[name="discType"]:checked').eq(0).val();
		formObject.credit = $this.find('input[name="credit"]').eq(0).val();
		/*if(null == formObject.credit || '' == formObject.credit) {
			$.messager.alert('错误', '优惠折扣不能为空！', 'error');
			accessoryJson.push('error');
			return accessoryJson;
		}*/
		var accType = $this.attr('acctype');
		formObject.actype = accType;
		if (accType == 'normal') {
			var accGoods ="";
			$.each($(this).find('tr:last td').find('ul li'), function(index, liele) {
				if(accGoods){
					accGoods+=","+  $(this).attr('accessory_id');
				}else{
					accGoods=  $(this).attr('accessory_id');
				}
				
			});
			formObject.accessoryGoods=accGoods;
		} else {
			formObject.pricefrom = $this.find('input[name="pricefrom"]').eq(0).val();
			formObject.priceto = $this.find('input[name="priceto"]').eq(0).val();
			formObject.searchKeywords = $this.find('input[name="searchKeywords"]').eq(0).val();
			// 分类
			var combox = $this.find('#f_cat:eq(0)');
			var catids = combox.combotree('getValues');
			if(catids!=null && catids.length>0){
				formObject.catIds= combox.combotree('getValues').join(",");
			}
			
			// 品牌
			if(null != $this.find('#f_brand').val() && $this.find('#f_brand').val()!='' && $this.find('#f_brand').val().length>0){
				formObject.brandIds= $this.find('#f_brand').val().join(",");
			}
			
		}
		accessoryJson.push(formObject);
	});
	return accessoryJson;
}

// 获得汽车厂商数据
function getCarFactoryData(url, obj, isSelect,factoryId) {
	$("#factoryId").empty();
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if(n.factoryId==factoryId){
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + ' ,selected:true},';
			}else{
				data += '{ text: "' + n.factoryName + '", id:' + n.factoryId + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({ data : eval(data) });
	});
}

// 获得汽车车系数据
function getCarDeptData(url, obj, isSelect,deptId) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if(n.carDeptId==deptId){ //选择指定值
				data += '{ text: "' + n.carDeptName + '", id:' + n.carDeptId + ',selected:true},';
			}else{
				data += '{ text: "' + n.carDeptName + '", id:' + n.carDeptId + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({ data : eval(data) });
	});
}

// 获得汽车年款数据-ssd
function getCarYearData(url, obj, isSelect,yearId) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if(n.id==yearId){ //选择指定值
				data += '{ text: "' + n.carYearValue + '", id:' + n.id + ',selected:true},';
			}else{
				data += '{ text: "' + n.carYearValue + '", id:' + n.id + '},';
			}
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({ data : eval(data) });
	});
}

// 获得车型数据-ssd
function getCarData(url, obj, isSelect,carId) {
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if(n.carId==carId){ //选择指定值
				data += '{ text: "' + n.carName + '", id:' + n.carId + ',selected:true },';
			}else{
				data += '{ text: "' + n.carName + '", id:' + n.carId + '},';	
			}
			
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({ data : eval(data) });
		showCar(json.content);
	});
}

//获得汽车配件类型
function getCarAccessoryType(url, obj, isSelect,accTypeId) {
	$("#"+obj).empty();
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if(n.catalogId==accTypeId){ //选择指定值
				data += '{ text: "' + n.catalogName + '", id:' + n.catalogId + ',selected:true },';
			}else{
				data += '{ text: "' + n.catalogName + '", id:' + n.catalogId + '},';	
			}
			
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({ data : eval(data) });
	});
}


//获得汽车配件数据项
function getCarAccessoryData(url, obj, isSelect,accId) {
	$("#"+obj).empty();
	$.getJSON(url, function(json) {
		var data = "[";
		if (isSelect) {
			data += '{ text: "--请选择--", id: "-1" ,selected:true  },';
		}
		$.each(json.content, function(i, n) {
			if(n.accId==accId){ //选择指定值
				data += '{ text: "' + n.accName + '", id:' + n.accId + ',selected:true },';
			}else{
				data += '{ text: "' + n.accName + '", id:' + n.accId + '},';	
			}
			
		});
		data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
		data += "]";

		$("#" + obj).select2({ data : eval(data) });
	});
}

// 规格复选框全选
function checkAll() {
	if (this.checked) {
		$("input[spec_id='checkname']").each(function() {
			this.checked = true;
		});
	} else {
		$("input[name='checkname']").each(function() {
			this.checked = false;
		});
	}
}

/**加载分类相关的信息：如规格
 * isEdit:是否为编辑操作
 * categoryType：分类的类别：999整车，888配件*/
function loadBaseData(isEdit,categoryType){
	console.log("categoryType="+categoryType);
	$('.category_type').hide();
	switch(parseInt(categoryType)){
	case yoyo.car:    //整车
		publishCar(isEdit);
		break;
	case yoyo.accessory:   //配件
		publishAccessory(isEdit);
		break;
//	case yoyo.maintain:    //保养
//		break;
//	case yoyo.boutique:    //精品
//		break;
	default:
			
	}
	//loadSpec(isEdit); // 加载规格
}

/**发布整车*/
function publishCar(isEdit){
	$('#car_category').show();
	$('#car_search').show();
	if(!isEdit){
		getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + $("#brand_id").val(), 'factoryId', true,null);// 获取厂商信息下拉列表数据
		clearCarData();  //清空数据
		$('#carListBar').empty();// 清空车型列表
		$('#autocomplete').val('');
	}
	$("#carDeptId").empty();
	$("#carYearValue").empty();
	$("#carTypeData").empty();
	$("#carDeptId").select2({ placeholder : "--请选择--" });
	$("#carYearValue").select2({ placeholder : "--请选择--" });
	$("#carTypeData").select2({ placeholder : "--请选择--" });

}

/**发布配件*/
function publishAccessory(isEdit){
	$('#acc_category').show();
	if(!isEdit){
		getCarAccessoryType(biz.rootPath() + '/accessory/catalogList?catId='+$('#cat_id').val(),'carAccType',true,null);  //获取汽车配件类型
		$("#accId").select2({ placeholder : "--请选择--" });
		$("#carAccType").select2({ placeholder : "--请选择--" });
	}

	// 汽车配件类型改变
	$("#carAccType").on("select2:select", function(e) {
		// 清空后面下拉框的数
		$("#accId").empty();
		$("#accId").val('--请选择--').trigger("change");
		if( $("#carAccType").val()!='-1'){
			getCarAccessoryData(biz.rootPath() + '/accessory/catalogDataList?catalogId=' + $("#carAccType").val()+"&brandId="+$("#brand_id").val(), 'accId', true,null);
		}
	});
	
	//显示配件适用的车型
	$("#accId").on("select2:select", function(e) {
		console.log($(this).val()+" 999");
		genGoodsDetailContent($(this).val());
		$("#id_gname").val($(this).find("option:selected").text());
	});
	
	loadSpec(false,0); // 加载规格
}


//显示配件适用的车型
function genGoodsDetailContent (accId) {
	if($('#car_search')){
		$('#car_search').remove();
	}
	var html='<tr id="car_search"><th>适用车型：</th><td>';
	$.ajax({  
        url : _path + '/accessory/getAccessoryDetail?',  
        async : false, // 注意此处需要同步，因为返回完数据后，下面才能让结果的第一条selected  
        type : "POST",  
        dataType : "json",
        data: {'accId' : accId},
        success : function(data) {  
        	if (data.head.retCode == '000000') {
        		if(data.content.accessory.cars.length>0){
        			html+="<table  style='width:100%;height:100%;' class='product-table accessory_table'>";
            		html+='<thead><tr><th width="80px" style="text-align: center;">厂商</th><th width="80px" style="text-align: center;">车系</th><th width="30px" style="text-align: center;">年款</th><th width="100px" style="text-align: center;">车型</th></tr></thead><tbody> ';
            		$.each(data.content.accessory.cars, function(index, item) {
            			if(index>4){
            				html+='<tr><td></td><td></td><td></td><td style="text-align: right;"><a href="#" onclick="getMore('+accId+')">更多>></a></td></tr>';
            				return false;
            			}else{
            				html+='<tr><td>' + item.factoryName + '</td><td>' + item.deptName + '</td><td>' + item.yearValue + '</td><td>' + item.carName + '</td></tr>';
            			}
        			});
        			html+='</tbody></table>';
        		}else{
        			html+="适用所有车型";
        		}
        		html+='</td></tr>';
        		$('#category_table').append(html);
    		}
        }  
    }); 
	 
};

function getMore(data){console.log(data);
	window.open(_path+"/accessory/getCars?accId="+data);
}

///////////////////////////////商品编辑////////////////////////////////////////////
/**初始化编辑*/
function initEdit(){
	console.log('goodsId='+ $('#goodsId').val());
	var goodsId = $('#goodsId').val();
	GOODS_ID = goodsId;
	var categoryTypeEdit=0;  //分类类型：999整车
	// 获取商品详细信息
	if($('#goodsId').val()!=null && $('#goodsId').val()!=''){
		$('#gEditor-GCat-category').off('click'); //不能修改分类
		commonAjax(_path + '/goods/goodsDetail', { goodsId : goodsId }, function(data) {
			var dataObj = data.content;
			$('#cat_id').val(dataObj.catId);
			$('#brand_id').val(dataObj.brandId);
			CAT_ID=dataObj.catId;   //分类id
			ACC_ID = dataObj.accId;
			showSaveBtn();
			if(dataObj.p21=='1'){ //未开启规格
				editSpec = true;
			}
			/**设置分类：如果品牌字段有值则显示品牌，否则显示分类*/
			if(dataObj.brandId!=null && dataObj.brandId!='' && dataObj.brandId!=0){
				commonAjax(_path + '/brand/brandList', { brandId : dataObj.brandId,brandType:-1 }, function(data) {
					$('#labelCategory').text(data.content.rows[0].brandName);
				},null);
			}
			//不是所有分类都关联品牌
			commonAjax(_path + '/cate/findCateById', { catId : dataObj.catId }, function(data) {
				if(dataObj.brandId==null || dataObj.brandId==''||dataObj.brandId==0){
					$('#labelCategory').text(JSON.parse(data.content).cate.catName);
				}
				categoryTypeEdit=JSON.parse(data.content).cate.identifier;
				$("#rootCategoryId").val(categoryTypeEdit);
				/**根据车型id，查找车型，厂商*/
				if(categoryTypeEdit==yoyo.car){
					showSearch();//通过搜索选择车型
					$('#carId').val(dataObj.carId);
					commonAjax(_path + '/car/carInfo', { carId : dataObj.carId }, function(carData) {
						//$("#factoryId").val(carData.content.factoryId).trigger("change");
						getCarFactoryData(biz.rootPath() + '/carFactory/findSelect?brandId=' + $("#brand_id").val(), 'factoryId', true,carData.content.factoryId);// 获取厂商信息下拉列表数据
						getCarDeptData(biz.rootPath() + '/carDept/findSelect?factoryId=' + carData.content.factoryId, 'carDeptId', true,carData.content.deptId);// 获取车系下拉列表数据
						getCarYearData(biz.rootPath() + '/carYear/findSelect?deptId=' + carData.content.deptId, 'carYearValue', true,carData.content.carYearId);// 获取年款下拉列表数据
						getCarData(biz.rootPath() + '/car/carList?carYearId=' + carData.content.carYearId, 'carTypeData', true,dataObj.carId);// 获取车型下拉列表数据
					},null);
				}else if(categoryTypeEdit==yoyo.accessory){  //根据配件id找配件类型
					$('#accIdInput').val(dataObj.accId);
					commonAjax(_path + '/accessory/findAccessoryTypeInfo', { accId : dataObj.accId}, function(accData) {
						//$("#carAccType").val(accData.content.catalogId).trigger("change");
						getCarAccessoryType(biz.rootPath() + '/accessory/catalogList?catId='+$('#cat_id').val(),'carAccType',true,accData.content.catalogId);  //获取汽车配件类型
						getCarAccessoryData(biz.rootPath() + '/accessory/catalogDataList?catalogId=' + accData.content.catalogId+"&brandId="+$("#brand_id").val(), 'accId', true,ACC_ID);// 获取车型下拉列表数据
						genGoodsDetailContent(dataObj.accId);
					},null);
				}
				loadBaseData(true,categoryTypeEdit);
				loadSpec(true,dataObj.carId); // 加载规格
				
			},null);
			
			$('#form_saveGoods').form('load', dataObj);
			/*var o = $("#cle-good-detail-info").cleditor()[0];//关键是这里获取第一个对象，否则upateFrame无法调用  
			$('#cle-good-detail-info').val(dataObj.intro);
			o.updateFrame();*/  
			um.setContent(dataObj.intro);
			
			
			
			getProductByGoodsId(goodsId);
			setPic(goodsId);
			
			//初始化配件信息
			$('#toolbar-good-edit-acc-info-group').addaccessory('getAccessory', { renderAim : '#good-edit-acc-info-from-group' , toolbar : '#toolbar-good-edit-acc-info-group',goodsId: data.content.goodsId});
		}, null);
		
		//获得相关商品
		setRelateGoods(goodsId);
	}
}



/**填充属性*/
function setAttrValue(){
	commonAjax(_path + '/goods/findAllAttrValue',{ goodsId : $('#goodsId').val() ,catId : $('#cat_id').val() }, function(data) {
		var html = '';
		attrArr = [ ];
		$.each(data.content, function(i, v) {
			attrArr.push(v.attrId);
			html += ' <tr><td>' + v.attrName + '：</td><td>';
			if (v.attrInputType == 'select') { // 属性值是下拉框
				html += '<select autocomplete="off" class="easyui-combobox"';
				html += 'name="attr_' + v.attrId + '"  >';
				$.each(v.attrValues.split('|'), function(index, value) {
					if (value == v.attrValue) {
						html += '<option selected=true value="' + value + '">' + value + '</option>';
					}else{
						html += '<option value="' + value + '">' + value + '</option>';
					}
				});
				html += '</select>';

			} else { // 文本
				html += '<input  class="x-input"  ';
				html += 'name="attr_' + v.attrId + '" maxlength="30"  type="text" value="'+v.attrValue+'"/>';
			}
			html += '</td></tr>';
		});
		$('.attrTable').append(html);
		$.parser.parse('.attrTable');

	},null);
}


/**获得货品**/
function getProductByGoodsId(goodsId) {
	commonAjax(_path + '/product/productSpec', { 'goodsId' : goodsId }, function(data) {
		console.log("product=:");
		console.log(data);
		if(data.content.length==0 || data.content[0].specDesc==''){ //没有选择规格或者没有规格
			$('.spec-panel').hide();
			$("#nospec_body").show();
			$('.openspec').show();
			$('.closespec').hide();
			
			/*
			$('.specification').hide();
			$('.openspec').show();
			$('.closespec').hide();
			$("#nospec_body").show();*/
			$("#productId").val(data.content[0].productId);
			isChooseSpec=false;
		}else{
			genExistProduct(data.content);
			isChooseSpec = true;
		}
	}, null);
}



/**填充图片相册*/
function setPic(goodsId){
	commonAjax(_path + '/goods/productPic', { 'goodsId' : goodsId }, function(data) {
		console.log("picture===");
		console.log(data);
		createEditPic(data.content);
	}, null);
}

function createEditPic(data){
	var html = '';
	var picName =[];
	var pic = [];
	$.each(data,function(i,v){
		picName = v.picturePath.split('\/');console.log(picName);
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
		createPic(pic,"Edit_"+i); //创建货品选择图片相册
	});
	$('.add_upload').before(html);
	// 绑定删除按钮
	funBindDelEvent();
	funBindHoverEvent();
}
// 绑定显示操作栏事件
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
			var isChooseCount = $(".img_"+pictureId).length;
			console.log("isChooseCount="+isChooseCount);
			if(isChooseCount>0){
				easyuiMsg('提示', "该图片已经关联规格，请取消关联关系，再删除！");
			}else{
				$('#upload'+$(this).attr("data-index")).remove();  //删除商品相册中的图片
				$('#prev_'+$(this).attr("data-index")).remove();        //删除货品相册中的图片
			}
			
			return false;	
		});
	}
	
}
 
 
 ///////////////////////生成货品的修改，不使用数组//////////////////////////////////////////

 
 //获取规格数据
 function getSpecByCatId(catId,goodsId,carId) {
		commonAjax(_path + '/spec/specAndValuesByCatId', { 'catId' : catId,'carId':carId }, function(data) {
			
			if(goodsId!=null){ //编辑状态
				renderEditSpecs(data.content);
				commonAjax(_path + '/product/productSpec', { 'goodsId' : goodsId }, function(data) {
					genExistProduct(data.content);
				}, null);
			}else{
				renderSpecs(data.content);
			}
		},null);
	}

 
 /**编辑状态下，初始化规格列表*/
	function renderEditSpecs(data) {
		var root = $('<ul class="specs-ul"></ul>');
		if(!editSpec && data.length>0){  //判断是否有规格,并且如果有规格，上次发布商品时是否选择了规格
			isChooseSpec = true;
			$("#nospec_body").hide();
			$('.spec-panel').show();
			$('.openspec').hide();
			$('.closespec').show();
			$('#gEditor-sepc-panel').show();
		}else{
			isChooseSpec = false;
			$("#nospec_body").show();
			$('.openspec').show();
			$('.closespec').hide();
			$('#gEditor-sepc-panel').show();
		}
		$.each(data, function(index, spec) {
			var ele = $('<li style="cursor:pointer;"><a id="spec_' + spec.specId + '">' + spec.specName + '</a><span>[ 0 ]</span></li>');
			renderSpecValues(spec.specId, spec.values,spec.specName);
			root.append(ele);
		});
		$('.specs').append(root);
		bindingEvent();
		$('.specs ul li:eq(0) a').click();  //默认显示第一个规格值
	};
 
 
 /**初始化规格列表*/
	function renderSpecs(data) {
		var root = $('<ul class="specs-ul"></ul>');
		if(data.length>0){  //判断是否有规格
			isChooseSpec = true;
			$("#nospec_body").hide();
			$('.spec-panel').show();
			$('.openspec').hide();
			$('.closespec').show();
			$('#gEditor-sepc-panel').show();
		}else{
			isChooseSpec = false;
			$("#nospec_body").show();
			$('#gEditor-sepc-panel').hide();
		}
		$.each(data, function(index, spec) {
			var ele = $('<li style="cursor:pointer;"><a id="spec_' + spec.specId + '">' + spec.specName + '</a><span>[ 0 ]</span></li>');
			renderSpecValues(spec.specId, spec.values,spec.specName);
			root.append(ele);
		});
		$('.specs').empty();
		$('.specs').append(root);
		bindingEvent();
		$('.specs ul li:eq(0) a').click();  //默认显示第一个规格值
	};

	/**初始化规格值列表 */
	function renderSpecValues(specId, data,specName) {
		var root = $('<ul class="specs-value-ul spec_' + specId + ' hidden"></ul>');
		$.each(data, function(index, specValue) {
			specValue.disabled = false;
			if(specName=='分店' && specValue.limitGoods=='1'){
				root.append($('<li />').append($('<span/>').attr(specValue).attr('spec_id', 'spec_' + specId).attr('id', 'spec_value_' + specId + '_' + specValue.specValueId)).append(
						'<span style="background:red;color:white;" >' + specValue.specValueName + '(违规)</span>'));
			}else{
				root.append($('<li />').append($('<input type="checkbox" style="cursor:pointer;"/>').attr(specValue).attr('spec_id', 'spec_' + specId).attr('id', 'spec_value_' + specId + '_' + specValue.specValueId)).append(
						'<label for="'+'spec_value_' + (specId + '_' + specValue.specValueId)+'" style="cursor:pointer;">' + specValue.specValueName + '</label>'));
			}
			
		});
		$('.spec-values-list').append(root);
		
	}
	

	function renderValueTable(specId) {
		$('.spec-value-table').append();
	}

	// 绑定事件
	function bindingEvent() {
		
	/*	$('.specs-ul').find('a').on('click', function() {
			$(this).parent().siblings().removeClass("c");
			$(this).parent().addClass('c');
			var specValues = $('.' + $(this).attr('id'));
			specValues.siblings().addClass("hidden");
			$('.spec-value-table table').find('tbody').addClass('hidden');
			specValues.removeClass("hidden");
		});*/
		
		$('.specs-ul').find('li').on('click', function() {
			$(this).siblings().removeClass("c");
			$(this).addClass('c');
			var specValues = $('.' + $(this).find('a').attr('id'));
			specValues.siblings().addClass("hidden");
			$('.spec-value-table table').find('tbody').addClass('hidden');
			specValues.removeClass("hidden");
		});
		
		$('.specs-value-ul').find('input:checkbox').on(
				'click',
				function() {
					var $this = $(this);
					var specId = $this.attr('spec_id');
					var $spec = $('#' + specId);
					var specValueId = $this.attr('id');
					if ($this.prop("checked")) {
						var $th = $('<tr><th>系统规格</th><th>规格值</th><th>操作</th></tr>');
						var $tb = $('<tr><td>' + $spec.text() + '</td><td>' + $this.attr('specvaluename')
								+ '</td><td><i class="top" onclick="up(this)"></i><i class="bottom" onclick="down(this)"></i><i class="delete" onclick="deleteSpecValue(this)"></i></td></tr>');
						var $tbody = $('<tbody></tbody>').addClass(specId).addClass(specValueId).append($th).append($tb);
						$('.spec-value-table table').append($tbody);
					} else {
						$('.spec-value-table table').find('.' + specValueId).remove();
					}
					createSKU();
					// 更新规格列表的选择数量
					var checkedNum = $this.parent().parent().find('input:checked').length;
					$spec.siblings('span').text('[ ' + checkedNum + ' ]');
				});
		$('#spec-action-gen-prod').off('click').on('click', function() {
			specActionGenProd();
		});
		
	}


	// 将货品缓存到页面规格的ProductData字段
	function cacheProductData() {
		$('#spec-window').removeData('ProductData');
		var productData = [ ];
		var staticMktprice = 0;
		var staticTimeprice = 0;
		var staticStore = 0;
		var depositPrice = 0;
		var staticPrice = Number.MAX_VALUE;
		var temp = 0;
		$('.product-table-body tr').each(function(index) {
			var $this = $(this);
			$('.product-table-body tr').eq(0).find('span[name="product_spec"]');
			var productDataLen = productData.length;
			productData[ productDataLen ] = {};
			productData[ productDataLen ].specInfo = $this.find('.product_spec').map(function() {
				return $(this).html();
			}).get().join(',');
			productData[ productDataLen ].specDesc = $this.find('.product_spec_desc').text();
			productData[ productDataLen ].productId = $this.find('input[name="productId"]').eq(0).val();
			temp = $this.find('input[name="_pic"]').eq(0).val();
			productData[ productDataLen ].picIds = temp;
			
			temp = $this.find('input[name="_product_pic_default"]').eq(0).val();
			if(temp=='' || temp==null){
				temp=$($(".division input[name='spec_picture']")[0]).attr('path');
			}
			productData[ productDataLen ].picturePath = temp;  //货品默认图片
			
			productData[ productDataLen ].bn = $this.find('input[name="bn"]').eq(0).val();
			temp = $this.find('input[name="_store"]').eq(0).val();
			productData[ productDataLen ].store = temp;
			if(temp){
				staticStore = staticStore+Number.parseFloat(temp); 
			}
			/*获取货品定金价*/
			temp = $this.find('input[name="_cost"]').eq(0).val();
			productData[ productDataLen ].cost = temp;
			if(temp&&temp<depositPrice){
				depositPrice = temp;
			}
			/*获取货品yoyo价*/
			temp = $this.find('input[name="_price"]').eq(0).val();
			productData[ productDataLen ].price = temp;
			if(temp&&temp<staticPrice){
				staticPrice = temp;
			}
			/*获取货品市场价*/
			temp = $this.find('input[name="_mktprice"]').eq(0).val();
			productData[ productDataLen ].mktprice = temp;
			if(temp&&temp>staticMktprice){
				staticMktprice = temp;
			}
			/*获取货品市场价*/
			temp = $this.find('input[name="_timeprice"]').eq(0).val();
			productData[ productDataLen ].timeprice = temp;
			if(temp&&temp>staticTimeprice){
				staticTimeprice = temp;
			}
			productData[ productDataLen ].storePlace = $this.find('input[name="_storePlace"]').eq(0).val();
			productData[ productDataLen ].marketable = $this.find('input[name="_marketable"]').eq(0).prop('checked')?'1':'0';
		});
		
		$('#deposit_price').val(depositPrice);
		$('#sales_price').val(staticPrice);
		$('#sales_store').val(staticStore);
		$('#id_goods_mktprice').val(staticMktprice);
		$('#timePrice').val(staticTimeprice);
		$('#spec-window').data('ProductData', productData);
	}

	//将货品规格汇总值商品主表的spec_desc字段用于商品详情页规格显示
	function cacheSpecStatistic(){
		var specs = $('.specs-ul').find('a');
		var specArray = [];
		$.each(specs,function(index,spec){
			var specStr = [$(spec).attr('id').split('_')[1],$(spec).text()].join('|');
			var specValue = [];
			$(".spec-values-list  input[spec_id=" + $(spec).attr('id') + "]:checked").each(function() {
				specValue.push($(this).attr('specvalueid')+'|'+$(this).attr('specvaluename'));
			});
			specValue = '{'+specValue.join(';')+'}';
			specArray.push(specStr+':'+specValue);
		});
		$('#spec-window').data('specDesc', specArray.join(','));
	}


	function test(value) {
	   //alert("test="+value);
	   //alert("id="+this.id);
	   var temp = this.id.split("_");
	   var first = temp[0];
	   var index = temp[1];
	   
	   var issd = first == 'cost';
	   var sdval = 0;
	   var edval = 0;
	    var sd = issd ? value : $('#cost_'+index).val();
	    if(sd != null){
	    	sdval = parseFloat(sd);
	    }
	    
	    var ed = issd ? $('#price_'+index).val() : value;
	    if(ed != null){
	    	edval = parseFloat(ed);
	    }
	    
	    if(edval > 0) {
	    	if (edval < sdval) {
		        $.messager.alert('错误', 'yoyo价小于订金价！', 'error');
		        //$('#mktprice_'+index).numberbox('setValue', 0);
		        $('#price_'+index).next().children().val('');
		    }
	    }
	    
	}
	
	// 依据选定的规格和规格值生成货品
	function specActionGenProd() {
		//createSKU();
		//var html = '';
		var productNewTable = [];
		var productTable = []; //货品id集合
		if($('.product-table-body tr').length>0){
			$.each($('.product-table-body tr'),function(k,v){
				productTable.push($(v).attr("id"));
			});
		}
		// 获得选择的规格的值
		//var start1 = new Date().getTime();
		var productArr = doExchange(arrayInfor); // 货品规格
		//alert("productArr length="+productArr.length);
		//var end1 = new Date().getTime();//结束时间
		//alert("doexchange time : "+(end1 - start1)+"ms");
		//var start = new Date().getTime();
		if (productArr !== undefined && productArr.length > 0) {
			$.each(productArr, function(i, item) {
				var td_array = item.split(",");
				var productEleId = ['product'];
				$.each(td_array, function(index, val) { // 遍历多个规格
					//html += '<span class="product_spec">' + val + '</span>';
					productEleId.push(val.split(':')[0].split('|')[0]);
					productEleId.push(val.split(':')[1].split('|')[0]);
				});
				productEleId = productEleId.join("_");
				productNewTable.push(productEleId);
				if($('#'+productEleId).length==0){
					var html = '';
					html = '<tr id="'+productEleId+'" status="new">';
					html += '<td><input type="checkbox" name="_marketable" class="pro-marketable-check" checked="checked"></td>';
					html += '<td>';
					$.each(kreateSpecInfo(item), function(i, obj) { // 遍历多个规格
						html += '<span class="product_spec">' + obj + '</span>';
					});
					html +='<div class="hide product_spec_desc">'+item+'</div>';
					html += '</td>';
				//	html += '<td><div name="pic"><div></td>';
					html += '<td>';
					html += '<span class="sel-albums-images lnk" onclick="showPic(this,event);" cur_spec_id="' + productEleId + '"><input type="button"  name="picture" value="绑定图片"></span>';
					html += '<span style="width:auto;" class="sel-imgs-area" id="span_' + productEleId + '"></span><input type="hidden" name="_pic" id="pic_'+productEleId+'">';
					html +=' <input name="_product_pic_default" type="hidden" id="pic_default_'+productEleId+'"></td>';
				
					
					//html += '<td><input type="hidden" name="productId"><input type="hidden" name="bn"  size="12"></td>';
					html += '<td><input type="hidden" name="productId"><input type="hidden" name="bn"  size="12"><input type="text" name="_store" size="10" class="easyui-numberbox easyui-validatebox "  data-options="required:true,min:0,max:9999999"></td>';
					if(CATAGORY_TYPE == yoyo.car) {
						html += '<td><input type="text" name="_cost" id="cost_' + i + '" size="8" class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,onChange:test"></td>';
						html += '<td><input type="text"	 name="_price" id="price_' + i + '" size="8" class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,onChange:test"></td>';
						html += '<td><input type="text"	 name="_mktprice" id="mktprice_' + i + '" size="8" class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,onChange:test"></td>';
					}else {
						html += '<td><input type="text"	 name="_price" id="price_' + i + '" size="8" class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,onChange:test"></td>';
						html += '<td><input type="text"	 name="_mktprice" id="mktprice_' + i + '" size="8" class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,onChange:test"></td>';
						html += '<td><input type="text"	 name="_timeprice" id="timeprice_' + i + '" size="8" class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,onChange:test"></td>';
					}
					
					
					
					//html += '<td><input type="hidden" name="_storePlace" size="4"></td>';
					html += '<td><input type="hidden" name="_storePlace" size="4"><span class="operater"  style="cursor: pointer;border:none;background:none" >删除</span></td>';
					html += '</tr>';
					$('.product-table-body').append(html);
					//$.parser.parse('.product-table-body');
				}
			});
			$.parser.parse('.product-table-body');

			//var end = new Date().getTime();
			//alert("total time : "+(end - start)+"ms");
			//去掉删除的货品
			if(productTable.length>0){
				$.each(productTable,function(k,v){
					if(productNewTable.indexOf(v)==-1){
						$('#'+v).remove();
					}
				});
			}
			
			// 绑定删除货品按钮事件
			$('.product-table-body .operater').on('click', function() {
				var $this = $(this).parent().parent();
				productArr.splice($this.index(), 1);// 当前行的id
				$this.remove();
			});
		} else {
			$.messager.show({ title : '提示' , msg : '请选择全规格' });
		}
		
	}

	function kreateSpecInfo(specDesc){
		var specInfo = [];
		$.each(specDesc.split(','),function(index,spec){
			specInfo.push([spec.split(':')[0].split('|')[1],spec.split(':')[1].split('|')[1]].join(':'));
		});
		return specInfo;
	}

	//编辑时，生成已有的货品
	function genExistProduct(data){
		var specObj = new Object();
		$.each(data, function(i, item) {
			var td_array = item.specDesc.split(",");
			var productEleId = ['product'];
			var spec = '';
			var pic = "";
			$.each(td_array, function(index, val) { // 遍历多个规格
				spec += '<span class="product_spec">' + val + '</span>';
				var valsplit = val.split(':');
				$.each(valsplit,function(index,temp){
					productEleId.push(temp.split('|')[0]);
				});
				if(!specObj[valsplit[0]]){
					var valObject = new Object();
					valObject[valsplit[1]]=valsplit[1];
					specObj[valsplit[0]]=valObject;
				}else{
					var valObject = specObj[valsplit[0]];
					valObject[valsplit[1]]=valsplit[1];
					specObj[valsplit[0]]=valObject;
				}
			});
			productEleId = productEleId.join("_");
			var html = '<tr id="'+productEleId+'" status="exist">';
			if(item.marketable=='0'){
				html += '<td><input type="checkbox" name="_marketable" class="pro-marketable-check"></td>';
			}else{
				html += '<td><input type="checkbox" name="_marketable" class="pro-marketable-check" checked="checked"></td>';
			}
			html += '<td>';
			$.each(kreateSpecInfo(item.specDesc), function(i, obj) { // 遍历多个规格
				html += '<span class="product_spec">' + obj + '</span>';
			});
			html +='<div class="hide product_spec_desc">'+item.specDesc+'</div>';
			html += '</td>';
			html += '<td>';
			html += '<span class="sel-albums-images lnk" onclick="showPic(this,event);" cur_spec_id="' + productEleId + '">图片</span>';
			html += '<span style="width:auto;" class="sel-imgs-area" id="span_' + productEleId + '">';
			$.each(item.pictures,function(k,kval){  //遍历货品图片
				html +='<img src="'+ yoyo.imagesUrl+kval.picturePath+'" class="img_'+kval.pictureId+'"  width=28 height=28/>';
				pic += kval.pictureId + "|";
				
			});
			html+="</span>";
			if (pic != "") {
				html += '<input type="hidden" name="_pic" id="pic_'+productEleId+'" value="'+pic.substring(0, pic.length - 1)+'">';
			}else{
				html += '<input type="hidden" name="_pic" id="pic_'+productEleId+'" >';
			}
			html +=' <input name="_product_pic_default" type="hidden" value="'+item.picturePath+'"></td>';
			html += '</td>';
			//html += '<td><div name="pic"><div></td>';
			
			html += '<td><input type="hidden" name="productId" value='+item.productId+'><input type="text" name="bn"  size="12" value='+item.bn+'></td>';
			html += '<td><input type="text" name="_store" size="10" value='+item.store+' class="easyui-numberbox easyui-validatebox "  data-options="required:true,precision:0,min:0,max:9999999"></td>';
			html += '<td><input type="text"	 name="_price" size="8" value='+item.price+' class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,min:0,max:999999999,onChange:test"></td>';
			html += '<td><input type="text" name="_cost" size="8" value='+item.cost+'></td>';
			html += '<td><input type="text"	 name="_mktprice" size="8" value='+item.mktprice+' class="easyui-numberbox easyui-validatebox " validType="money" data-options="required:true,precision:2,min:0,max:999999999,onChange:test"></td>';
			html += '<td><input type="text" name="_storePlace" size="4" value='+item.storePlace+'></td>';
			html += '<td><img class="operater"  style="cursor: pointer;" alt="删除" src=""></td>';
			html += '</tr>';
			$('.product-table-body').append(html);
			$.parser.parse('.product-table-body');
			// 绑定删除货品按钮事件
			$('.product-table-body .operater').on('click', function() {
				var $this = $(this).parent().parent();
				productArr.splice($this.index(), 1);// 当前行的id
				$this.remove();
			});
			
		});
		//勾选规格值 更新规格数字
		$.each(specObj,function(index,obj){
			var specid = index.split('|')[0];//specId
			var spec = $('#spec_'+specid);
			var checkedNum = 0;//规格数字
			$.each(obj,function(index2){
				var specvalueid=index2.split('|')[0];
				var specValue = $('#spec_value_'+specid+'_'+specvalueid);
				specValue.prop('checked','checked');
				var $th = $('<tr><th>系统规格</th><th>规格值</th><th>操作</th></tr>');
				var $tb = $('<tr><td>' + spec.text() + '</td><td>' + specValue.attr('specvaluename')
						+ '</td><td><i class="top" onclick="up(this)"></i><i class="bottom" onclick="down(this)"></i><i class="delete" onclick="deleteSpecValue(this)"></i></td></tr>');
				var $tbody = $('<tbody></tbody>').addClass('spec_'+specid).addClass('spec_value_'+specid+'_'+specvalueid).append($th).append($tb);
				$('.spec-value-table table').append($tbody);
				checkedNum += 1;
			});
			spec.siblings('span').text('[ ' + checkedNum + ' ]');
			$('.specs ul li:eq(0) a').click();
		});
		//缓存至父页面
		cacheProductData();
	}

	// 组合数组: 组合算法 获得所有组合
	function doExchange(doubleArrays) {
		var len = doubleArrays.length;
		if (len >= 2) {
			var arr1 = doubleArrays[ 0 ];
			var arr2 = doubleArrays[ 1 ];
			var len1 = doubleArrays[ 0 ].length;
			var len2 = doubleArrays[ 1 ].length;
			var newlen = len1 * len2;
			var temp = new Array(newlen);
			var index = 0;
			for (var i = 0; i < len1; i++) {
				for (var j = 0; j < len2; j++) {
					temp[ index ] = arr1[ i ] + "," + arr2[ j ];
					index++;
				}
			}
			var newArray = new Array(len - 1);
			newArray[ 0 ] = temp;
			if (len > 2) {
				var _count = 1;
				for (var i = 2; i < len; i++) {
					newArray[ _count ] = doubleArrays[ i ];
					_count++;
				}
			}
			return doExchange(newArray);
		} else {
			return doubleArrays[ 0 ];
		}
		
	}

	
	function createSKU() {
		var SKUObj = $(".specs-ul").find('a');
		//arrayTile = new Array();// 规格标题组数
		arrayInfor = new Array();// 存放每组选中的CheckBox值的对象
		$.each(SKUObj, function(i, item) {
			var specId = $(item).attr('id');
			//arrayTile.push($(item).text());
			// 选中的CHeckBox取值
			var order = new Array();
			$(".spec-values-list  input[spec_id=" + specId + "]:checked").each(function() {
				var $this = $(this);
				var specEleId = $this.attr('spec_id'); 
				var specItem = $this.attr('specid')+'|'+$('#'+specEleId).text()+':'+$this.attr('specvalueid')+'|'+$(this).attr('specvaluename');
				order.push(specItem);
			});
			arrayInfor.push(order);
		});
		SKUObj = null;
	}
	
	
	
	
	/**填充相关商品*/
	function setRelateGoods(goodsId){
		commonAjax(_path + '/goodsRelate/relateGoodsList', { goodsId : goodsId }, function(data) {
			var html ='';
			var ass_good = $('#good-edit-relate');
			ass_good.find('li').remove();
			var selected = data.content;
			if(selected!=null){
				$.each(selected, function(index, option) {
					html +='<li id="' + option.goodsId + '"><a style="  margin-right: 10px;" onclick="deleteRelateGoods(this,' + option.goodsId + ')">删除</a><span style="  margin-right: 10px;">' + option.goodsName + '</span><input name="asstype_' + option.goodsId+ '"';
					if(option.manual=='both'){
						html+=' type="radio" value="both" checked="checked"  />';
						html+='双向关联<input name="asstype_' + option.goodsId
							+ '" type="radio" value="left"  style="  margin-left: 10px;"/>单向关联</li>';
					}else{
						html+= ' type="radio" value="both"  />双向关联<input name="asstype_' + option.goodsId
						+ '" type="radio" value="left" checked="checked" style="  margin-left: 10px;"/>单向关联</li>';
					}
					
				});
				ass_good.append(html);
			}
		},null);
	}
	
	
	/**弹出相关商品的对话框*/
	 function selectRelateGoods(){
		 var selectGoods = new Array();   //已经选择的商品
		 $.each($('#good-edit-relate').find('li'), function(index, liele) {
				var $liele = $(this);
				selectGoods.push($liele.attr('id'));
				selectRelateGoodsIds.push($liele.attr('id'));
				selectRelateGoodsObj[$liele.attr('id')]=$(this).find('span:first').html();
			});
			$('#goodsTable').datagrid({
				url : _path + '/goods/goodsList',
				columns : [ [ {
					field:'ck',
					checkbox:"true"
				},{
					field : 'name',
					align : 'center',
					title : '商品名称'			
				}, {
					field : 'store',
					align : 'center',
					title : '库存'
				},  {
					field : 'price',
					align : 'center',
					title : '价格'
				}/*,{
					field : 'intro',
					align : 'center',
					title : '商品简介'					
				} */] ],
				pagination : true,
				pagePosition : 'bottom',
				rownumbers : true,
				fitColumns : true,
				pageSize : 20,
				height:540,
				pageList : [ 20, 100, 150, 500 ],
				singleSelect : false,
				checkOnSelect:false,
				remoteSort : false,
				nowrap:false,
				multiSort : true,
				loadFilter : function(data) {
					if (data.rows) {
						return data;
					} else {
						return data.content;
					}
				},
				onLoadSuccess:function(data){
					console.log(selectGoods.length);
					if(selectGoods.length>0){
						$.each(data.rows,function(i,v){
							if(selectGoods.indexOf(v.goodsId+'')!=-1){  //已经被选择了
								$('#goodsTable').datagrid('checkRow',i);
							}
						});
					}
				},
				onCheck:function(rowIndex,rowData){
					if(selectRelateGoodsIds.indexOf(rowData.goodsId)==-1){ //判断是否存在，存在则不需要插入
						selectRelateGoodsIds.push(rowData.goodsId);
						selectRelateGoodsObj[rowData.goodsId]=rowData.name;
					}
				},
				onUncheck:function(rowIndex,rowData){
					selectRelateGoodsIds.remove(rowData.goodsId);
				},onCheckAll:function(rows){   //点击全选按钮
					$.each(rows,function(i,rowData){
						if (selectRelateGoodsIds.indexOf(rowData.goodsId) == -1) { // 判断是否存在，存在则不需要插入
							selectRelateGoodsIds.push(rowData.goodsId);
							selectRelateGoodsObj[rowData.goodsId]=rowData.name;
						}
					
					});
				} ,onUncheckAll:function(rows){
					$.each(rows,function(i,rowData){
						selectRelateGoodsIds.remove(rowData.goodsId);
					});
				}
			});
			$("#categoryWindow").window({
				modal:false,
				resizable:false,
				draggable:false,
				collapsible:false,
				closed:true,
				top : $('#good-edit-ass-info').position().top-600,
		    	maximized:false,
		    	minimizable:false,
		    	maximizable:false,
		    	top:1050,
				onClose:function(){
					selectRelateGoodsIds=[];
					selectRelateGoodsObj = {}; 
				}
			});
			
			$('#categoryWindow').window('open');
			$("#categoryWindow").window("move",{top:$(document).scrollTop() + ($(window).height()-650) * 0.5});
			
		}
		
		
	/**确定选择的相关商品*/
	function confirmRelateGoods(){
		if(selectRelateGoodsIds.length==0){
			$.messager.show({ title : '提示' , msg : '请选择相关商品!' });
		return false;
	}
	var ass_good = $('#good-edit-relate');
	ass_good.empty();
	$.each(selectRelateGoodsIds,function(i,option){console.log( selectRelateGoodsObj[option]);
		ass_good.append($('<li id="' +option + '" ><a style="  margin-right: 10px;" onclick="deleteRelateGoods(this,' + option + ')">删除</a><span style="  margin-right: 10px;">' + selectRelateGoodsObj[option] + '</span><input name="asstype_' + option
				+ '" type="radio" value="both"  />双向关联<input name="asstype_' + option
				+ '" type="radio" value="left" checked="checked" style="  margin-left: 10px;"/>单向关联</li>'));
	});
	selectRelateGoodsIds=[];
	selectRelateGoodsObj = {};  
	$('#categoryWindow').window('close');
	}
	
	var thisAccessoryObj = {};  //当前点击的配件货品按钮对象
	
	/**弹出配件货品的对话框*/
	 function selectAccessoryGoods(){
		 var selectGoods = new Array();   //已经选择的商品
		 thisAccessoryObj = $(event.currentTarget).parent().find('ul');
		 $.each($(event.currentTarget).parent().find('ul li'), function(index, liele) {
				var $liele = $(this);
				selectGoods.push($liele.attr('accessory_id'));
				selectRelateGoodsIds.push($liele.attr('accessory_id'));
				selectRelateGoodsObj[$liele.attr('accessory_id')]=$(this).find('span:first').html();
			});
		$('#productTable').datagrid({
			url : _path + '/product/productList',
			columns : [ [ {
				field:'ck',
				checkbox:"true"
			},{
				field : 'name',
				align : 'center',
				title : '货品名称'			
			}, {
				field : 'productId',
				align : 'center',
				title : '货品ID'
			},  {
				field : 'price',
				align : 'center',
				title : '价格'
			},{
				field : 'specInfo',
				align : 'center',
				title : '货品描述'					
			} ] ],
			pagination : true,
			pagePosition : 'bottom',
			rownumbers : true,
			fitColumns : false,
			pageSize : 20,
			height:540,
			pageList : [ 20, 100, 150, 500 ],
			singleSelect : false,
			checkOnSelect:false,
			remoteSort : false,
			multiSort : true,
			nowrap:false,
			loadFilter : function(data) {
				if (data.rows) {
					return data;
				} else {
					return data.content;
				}
			},
			onLoadSuccess:function(data){
				if(selectGoods.length>0){
					$.each(data.rows,function(i,v){
						if(selectGoods.indexOf(v.productId+'')!=-1){  //已经被选择了
							$('#productTable').datagrid('checkRow',i);
						}
					});
				}
			},
			onCheck:function(rowIndex,rowData){
				if(selectRelateGoodsIds.indexOf(rowData.productId)==-1){ //判断是否存在，存在则不需要插入
					selectRelateGoodsIds.push(rowData.productId);
					selectRelateGoodsObj[rowData.productId]=rowData.name;
				}
			},
			onUncheck:function(rowIndex,rowData){
				selectRelateGoodsIds.remove(rowData.productId);
			},
			onCheckAll:function(rows){   //点击全选按钮
				$.each(rows,function(i,rowData){
					if (selectRelateGoodsIds.indexOf(rowData.productId) == -1) { // 判断是否存在，存在则不需要插入
						selectRelateGoodsIds.push(rowData.productId);
						selectRelateGoodsObj[rowData.productId]=rowData.name;
					}
				
				});
			} ,onUncheckAll:function(rows){
				$.each(rows,function(i,rowData){
					selectRelateGoodsIds.remove(rowData.productId);
				});
			},
			onBeforeClose:function(){
				selectRelateGoodsIds=[];
				selectRelateGoodsObj = {}; 
			}
		});
		$("#accessoryWindow").window({
			modal:false,
			resizable:false,
			draggable:false,
			collapsible:false,
			closed:true,
			top : $(event.target).position().top-300,
	    	maximized:false,
	    	minimizable:false,
	    	maximizable:false,
	    	onClose:function(){
				selectRelateGoodsIds=[];
				selectRelateGoodsObj = {}; 
			}
		});
		
		$('#accessoryWindow').window('open');
		$("#accessoryWindow").window("move",{top:$(document).scrollTop() + ($(window).height()-650) * 0.5});
		
	}
	
	
	/**确定选择的配件货品*/
	function confirmAccessoryGoods(){
		if(selectRelateGoodsIds.length==0){
			$.messager.show({ title : '提示' , msg : '请选择套装货品!' });
			return false;
		}
		var ass_good = thisAccessoryObj;
		ass_good.children().remove();
		$.each(selectRelateGoodsIds,function(i,option){console.log( selectRelateGoodsObj[option]);
			ass_good.append($('<li accessory_id="' +option + '" ><a style="  margin-right: 10px;" onclick="deleteProduct()">删除</a><span style="  margin-right: 10px;">' + selectRelateGoodsObj[option] + '</span></li>'));
		});
		selectRelateGoodsIds=[];
		selectRelateGoodsObj = {};  
		$('#accessoryWindow').window('close');
	}
	
	
	/**jquery.addaccessory.js中使用 删除选定的配件货品*/
	function deleteProduct(){
		$(event.currentTarget).parent().remove();
	}
	
	
	//获取鼠标点击的坐标
	function GetPostion(e) {
	  var x = getX(e);
	  var y = getY(e);
	}
	function getX(e) {
	  e = e || window.event;
	   
	return e.pageX || e.clientX + document.body.scroolLeft;
	}
	
	function getY(e) {
	  e = e|| window.event;
	 return e.pageY || e.clientY + document.boyd.scrollTop;
	}
	
	//判断分类叶子节点是否需要进行商品审核
	function judgeGoodsCheck(){
		$.ajax({ url :  _path+'/shopManager/shopGoodCheck',  type : "post" , datatype : "json" , success : function(data) {
			if (data.head.retCode == '000000') {
				if(data.content.isCheck=='1'){ //店铺商品需要审核
					$('#saleUpSubmit').hide();
					$('#checkSubmit').show();
					catObj.check=true; //是否需要审核
					if(data.content.catIds!=null && data.content.catIds.trim()!=''){
						catObj.catIds = data.content.catIds.split(",");
					}else{
						catObj.catIds=null;
					}
				}else{
					catObj.check=false;
					$('#saleUpSubmit').show();
					$('#checkSubmit').hide();
				}
			} else {
				$.messager.alert('错误', '保存失败', 'error');
			}
		} });
	}

	
	/**保存商品*/
	function saveGoods(marketable) {
		$('#accIdInput').val($('#accId').val());
		var params = biz.serializeObject($("#form_saveGoods"));
		params.marketable=marketable;
		if ($('#cat_id').val() == '') {
			easyuiMsg('提示', "请选择分类！");
			return false;
		}
		if (CATAGORY_TYPE == yoyo.car && ( $("#carId").val() == -1 || $("#carId").val() == null || $("#carId").val() == '')) { // TODO
			// 车型不能为空
			easyuiMsg('提示', "请选择车型！");
			return false;
		}
		
		if(CATAGORY_TYPE == yoyo.accessory && ($('#accIdInput').val() == -1 || $('#accIdInput').val() == null || $('#accIdInput').val() == '')) {
			// 汽车配件类型不能为空
			easyuiMsg('提示', "请选择汽车配件类型！");
			return false;
		}
		if ($(".division input[name='spec_picture']").length == 0) {
			easyuiMsg('提示', "请上传图片相册！");
			return false;
		}
		if (params.brief.length > 70) {
			easyuiMsg('提示', "商品简介字数超出70个字，请控制在70字以内！");
			return false;
		}
		if (!isChooseSpec) { // 没有开启规格
			var despositPrice = 0;
			var salsePrice = 0;
			var mktPrice = 0;
			
			if(CATAGORY_TYPE == yoyo.car) {
				if ($("#deposit_price").val() == '') {
					easyuiMsg('提示', "请填写订金价格！");
					return false;
				}
				
				if ($("#sales_price").val() == '') {
					easyuiMsg('提示', "请填写yoyo价格！");
					return false;
				}
				despositPrice = parseInt($('#deposit_price').val());
				salsePrice = parseInt($('#sales_price').val());
				if (despositPrice > salsePrice) {
					easyuiMsg('提示', "yoyo价小于订金价！");
					return false;
				}
			}else {
				if ($("#sales_price").val() == '') {
					easyuiMsg('提示', "请填写销售价格！");
					return false;
				}
				
				if ($("#id_goods_mktprice").val() == '') {
					easyuiMsg('提示', "请填写市场价格！");
					return false;
				}
				
				if ($("#timePrice").val() == '') {
					easyuiMsg('提示', "请填写区间价格！");
					return false;
				}
			}
			
			
			/*if(null != $('#sales_price').val()) {
				salsePrice = parseInt($('#sales_price').val());
			}
			
			if(null != $('#id_goods_mktprice').val()) {
				mktPrice = $('#id_goods_mktprice').val();
			}
			if (salsePrice > mktPrice) {
				if(CATAGORY_TYPE==yoyo.car){
					easyuiMsg('提示', "yoyo价小于订金价！");
				}else {
					easyuiMsg('提示', "市场价小于销售价！");
				}
				
				return false;
			}*/
			
			if ($("#sales_store").val() == '') {
				easyuiMsg('提示', "请填写库存！");
				return false;
			}
			// 商品相册
			var pic = "";
			// 获取选择的图片的地址
			$(".division input[name='spec_picture']").each(function() {
				pic += $(this).attr('pic_id') + "|";
			});
			if (pic != '') {
				picArr[ 0 ] = pic.substring(0, pic.length - 1);
				params.picIds = picArr.join(",");
			}
			params.isOpenSpec='1';
			
		} else {
			cacheProductData();
			cacheSpecStatistic();
			
			var depositPrice = 0;
			var salsePrice = 0;
			var mktPrice = 0;
			if(null != $('#deposit_price').val()) {
				depositPrice = parseInt($('#deposit_price').val());
			}
			
			if(null != $('#sales_price').val()) {
				salsePrice = parseInt($('#sales_price').val());
			}
			
			if(null != $('#id_goods_mktprice').val()) {
				mktPrice = $('#id_goods_mktprice').val();
			}
			if (depositPrice > salsePrice) {
				easyuiMsg('提示', "yoyo价小于订金价！");
				return false;
			}
			params.cost = $('#deposit_price').val();
			params.price = $('#sales_price').val();
			params.store = $('#sales_store').val();
			params.mktPrice = $('#id_goods_mktprice').val();
			params.timePrice = $('#timePrice').val();
			
			var productData = $('#spec-window').data('ProductData');
			if (productData.length ==0) {
				easyuiMsg('提示', "请生成货品！");
				return false;
			}
			// 货品信息
			params.productJson = JSON.stringify($('#spec-window').data('ProductData'));
			params.goodsSpecJson = $('#spec-window').data('specDesc');
			params.isOpenSpec='0';
		}
		/*if ($("#cle-good-detail-info").val() == '') {
			easyuiMsg('提示', "请填写详细信息！");
			return false;
		}*/
		
		if (!$("#form_saveGoods").form('validate')) {
			easyuiMsg('提示', "请填完整信息！");
			return false;
		}
		submitGoods(params);
	}
	//显示保存的按钮		
    function showSaveBtn(){
    	if(catObj.check && catObj.catIds!=null){ //所选分类需要审核
			if(catObj.catIds.indexOf(CAT_ID)!=-1){
				$('#saleUpSubmit').hide();
				$('#checkSubmit').show();
			}else{
				$('#saleUpSubmit').show();
				$('#checkSubmit').hide();
			}
		}
    }
    
    
    
    /**通过搜索选择车型*/
    function showSearch(){
    	$.ajax({
            url: _path +"/car/getCarByBrandId?brandId="+$("#brand_id").val(),
            dataType: "json",
            success: function (msg) {
                if (msg == null) {
                }
                else if (msg != null && msg.head.retCode == '000000' && msg.content!=null) {
                	//console.log(JSON.stringify(msg.content));
                	var target='[';
                	$.each(msg.content,function(k,v){
                		target+='{"carId":'+v.carId+',"carName":"'+v.carName+'"},';
                	});
                	target = target.length>1 ? target.substring(0, target.length-1):target;
                	target+=']';
                    $("#autocomplete").autocomplete({
                    	source: function( request, response ) {
	                        var matcher = new RegExp(  $.ui.autocomplete.escapeRegex( request.term ), "i" );
	                        response( $.grep( JSON.parse(target), function( item ){
	                            return matcher.test( item.carName );
	                        }) );
	                    } ,
	                    select: function( event, ui ) {
	                          this.value = ui.item.carName;
	                          $('#id_gname').val(this.value);
	                          $('#carId').val(ui.item.carId);
	                          loadSpec(false,ui.item.carId); // 加载规格
	                          return false;
	                    },
	                    minLength: 0,
	                    autoFocus:true
                       
                    }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {  
                        return $( "<li></li>" )  
                        .data( "item.autocomplete", item )  
                        .append( "<a >" + item.carName+ "</a>" )  
                        .appendTo( ul );  
                };  
                }
               
            }
        });
    }
    
    
     /*************批量发布*******************/
    /**点击车系，动态生成所有车型*/
    function createCar(deptId){
    	$.ajax({ url :  _path+'/car/findCar',  type : "post" ,data: { deptId : deptId }, datatype : "json" , success : function(data) {
    		console.log(data);
    		showCar(data);
		} });
    	
    }
    
    //创建车型列表
    function showCar(data){
    	$('#carListBar').empty();// 清空
		var html = '';
		var carPo = {};
    	if(data!=null && data.length>0){
    		num = Math.ceil(data.length/4);  //一排展示4个车型
    		for(var i = 0; i < num ; i++ ){
    			html+='<li class="b_ad_li">'
    					+ '<ul class="banner_b_add clearfix">';
				for(var j=0 ; j < data.length-4*i ; j++ ){
					carPo = data[j+4*i];
    				html+=' <li class="car_li" car_id="'+carPo.carId+'">'
    					+' <a href="javascript:;">'
    					+' <div class="sib fl">'
    					+'     <h5>'+carPo.carName+'</h5>'
    					//+'    <p class="orange">指导价：￥'+carPo.price+'万元</p>'
    					+'  </div>'
    					//+'  <span class="sib_span fr"></span>'
    					+'  </a>'
    					+' </li>'
    					+' <li class="br1">'
    					+' </li>';
				}
				html +='</ul></li>';
    		}
    		$('#carListBar').append(html);
    		
    		//添加点击事件
    		$('.car_li').bind("click",function(e){
    			clearCarData();  //清空数据
    			// 车型自动设置商品名称
    			$('#id_gname').val($(this).find('h5').html());
    			$('#carId').val($(this).attr('car_id'));
    			$('.b_ad_li li').removeClass("liVisited");
    			$(this).addClass("liVisited");
    			loadSpec(false,$(this).attr('car_id')); // 加载规格
    			
    		});
    		
    		$('#car_banner').show();
    	}
    }
    
   /**清空上一个车型的数据*/ 
  function clearCarData(){
	  $('#form_saveGoods input[type="text"]:visible').val('');
	  $('#form_saveGoods textarea:visible').val('');
	 //清空选中规格
	  $.each($('.specs-value-ul').find('input[type="checkbox"]:checked'),function(i,v){
		  $(this).click();
	  });
	  $('.product-table-body').empty();  //清空货品
	  $('.add_upload').prevAll().remove(); //清空图片相册中的图片
	  /*var o = $("#cle-good-detail-info").cleditor()[0];//关键是这里获取第一个对象，否则upateFrame无法调用  
	  $('#cle-good-detail-info').val(''); //清空详细说明
	  o.updateFrame(); */ 
	  um.setContent("");
	  $('#good-edit-acc-info-from-group').empty(); //清空套餐
	  $('#good-edit-relate').empty();  //清空相关商品
	  $('.division').empty();  //清空货品选择的图片
  }
  
  
