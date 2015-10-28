/**
 * 
 */
$(function() {
	// 初始化插件
	$("#goods_picture").zyUpload({ width : "100%" , // 宽度
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
			var goodsPictureCache = $("#goods_picture").data('goodsPictureCache');
			if(!goodsPictureCache){
				goodsPictureCache=new Array();
			}
			goodsPictureCache[file.index]=JSON.parse(data).retMsg;
			 $("#goods_picture").data('goodsPictureCache',goodsPictureCache);
		},
		onDelete: function(file, files){
			var goodsPictureCache = $("#goods_picture").data('goodsPictureCache');
			if(goodsPictureCache){
				goodsPictureCache[file.index]='';
				$("#goods_picture").data('goodsPictureCache',goodsPictureCache);
			}
		}
	 });
	// 初始化分类下拉
	initRealCateTree('select-good-edit-cat', false);
	// 初始化标签下拉
	$('#id_tags').comdropdown({ url : _path + '/label/brandApplyLabels' , cache : true , idField : 'id' , textField : 'name' , multiple : 'multiple' , queryParams : { rows : 20 , campanyId : 1 } });
	// 初始化品牌下拉
	initBrandComboxGrid('select-good-edit-brand', false);
	// 在线html编辑器
	$('#cle-good-detail-info').cleditor();
	// 获取商品详细信息
	commonAjax(_path + '/goods/goodsDetail', { goodsId : $('#GOOD_EDIT_GOOD_ID').val() }, function(data) {
		$('#form-good-edit-base-info').form('load', data.content);
		$('#form-good-edit-ext-info').extribute({ catId : data.content.catId , goodsId : data.content.goodsId , cssClass : 'form-table' });
		$('#select-good-edit-cat').combotree('setValue',data.content.catId); 
		$('#cle-good-detail-info').val(data.content.intro);
		$('#cle-good-detail-info').cleditor()[0].updateFrame();
		var $specWindow = $('#spec-window');
		$specWindow.attr("goodsId", data.content.goodsId);
		$specWindow.attr("catId", data.content.catId);
		// 初始化配件信息
		$('#toolbar-good-edit-acc-info-group').addaccessory('getAccessory', { renderAim : '#good-edit-acc-info-from-group' , toolbar : '#toolbar-good-edit-acc-info-group',goodsId: data.content.goodsId});
		$('#form-good-edit-base-info').walidator();
	}, null);
	
	// 绑定左侧导航卡
	$('.good-edit-tab-nav li').on('click',function(){
		var $this = $(this);
		$this.siblings().removeClass('selected');
		$this.addClass('selected');
		// 该导航卡对应的tabid
		var $tab = $('#'+$this.attr('for'));
		$tab.siblings().addClass('hide');
		$tab.removeClass('hide');
	});
	$('.good-edit-tab-nav li[for="good-edit-base-info"]').click();
	// 绑定添加配件按钮
	$('#button-good-edit-acc-add').on('click', function() {
		$('#toolbar-good-edit-acc-info-group').addaccessory('kreatePanel', { renderAim : '#good-edit-acc-info-from-group' , toolbar : '#toolbar-good-edit-acc-info-group' });
	});
	// 初始化关联商品下拉
	$('#select-good-edit-ass').comdropdown({ url : _path + '/goods/goodsList' , cache : true , idField : 'goodsId' , textField : 'name' , multiple : 'multiple' });
	// 绑定关联商品下拉选择事件
	$('#select-good-edit-ass').on(
			"select2:select",
			function(e) {
				var ass_good = $('#good-edit-ass-info-ass-good');
				ass_good.find('li').remove()
				var selected = $('#select-good-edit-ass').find('option');
				$.each(selected, function(index, option) {
					ass_good.append($('<li id="' + $(option).val() + '"><span>' + $(option).text() + '</span><input name="asstype_' + $(option).val()
							+ '" type="radio" value="both"/>双向关联<input name="asstype_' + $(option).val()
							+ '" type="radio" value="left" checked="checked"/>单向关联<a onclick="this.parentNode.parentNode.removeChild(this.parentNode)">删除</a></li>'));
				});
			});
	// 关闭窗口
	$('#button-good-edit-close').on('click', function() {
		$('#window-goods-edit').window('close');
	});
	// 开启规格
	$('#spec-no-spec-switch').on('click', function() {
		if ($(this).attr('specable') == 'specable') {
			$(this).attr('specable', 'unspecable');
			$(this).text("开启");
			// 清除缓存在窗口上的值
			$('#spec-window').removeData('ProductData');
		} else {
			$(this).attr('specable', 'specable');
			$(this).text("关闭");
			var $specWindow = $('#spec-window');
			$specWindow.window('open').window('refresh', _path + '/goods/specEditIndex');
		}
	});

	// 保存信息
	$('#button-good-edit-submit').on('click', function() {
		var param = {};
		// 基本信息
		param.info = biz.serializeObject($('#form-good-edit-base-info'));
		var goodsId=$("#GOOD_EDIT_GOOD_ID").val();
		param.info.goodsId=goodsId;
		// 扩展属性
		var attrJson=new Array();
		$.each(biz.serializeObject($('#form-good-edit-ext-info')),function(index,obj){
			attrJson.push({'goodsId':goodsId,'catId':29,'attrId':index,'attrValueId':0,'attrValue':obj});
		});
		param.attrJson=attrJson;
		// 标签
		var tags =new Array();
		$.each($('#id_tags').find("option"), function(index, option) {
			tags.push({ 'id' : $(option).attr('value') , 'name' : $(option).text() });
		});
		param.tags=tags;
		// 关联商品
		var relateGoodsJson=new Array();
		$.each($('#good-edit-ass-info-ass-good').find('li'), function(index, liele) {
			var $liele = $(this);
			var asstype=$liele.find('input[name="asstype_' + $liele.attr('id') + '"]:checked').val();
			relateGoodsJson.push({'goods1':goodsId,'goods2':$liele.attr('id'),'manual':asstype});
		})
		param.relateGoodsJson=relateGoodsJson;
		// 配件
		var accessoryJson = new Array();
		$('.acc-group-form').each(function(index) {
			var $this = $(this);
			var formObject = {};
			formObject.goodsId =goodsId;
			formObject.accGroupName = $this.find('input[name="accGroupName"]').eq(0).val();
			formObject.minBuy = $this.find('input[name="minBuy"]').eq(0).val();
			formObject.maxBuy = $this.find('input[name="maxBuy"]').eq(0).val();
			formObject.discType = $this.find('input[name="discType"]:checked').eq(0).val();
			formObject.credit = $this.find('input[name="credit"]').eq(0).val();
			var accType = $this.attr('acctype');
			formObject.acType = accType;
			if (accType == 'normal') {
				var accGoods ="";
				$this.find('td:last select:eq(0) option').each(function(index) {
					if(accGoods){
						accGoods+=","+ $(this).val();
					}else{
						accGoods= $(this).val();
					}
				});
				formObject.accessoryGoods=accGoods;
			} else {
				formObject.pricefrom = $this.find('input[name="pricefrom"]').eq(0).val();
				formObject.priceto = $this.find('input[name="priceto"]').eq(0).val();
				formObject.searchname = $this.find('input[name="searchname"]').eq(0).val();
				// 分类
				var combox = $this.find('#f_cat:eq(0)');
				var catText = combox.combotree('getText').split(',');
				var catids = combox.combotree('getValues');
				var cats = [ ];
				for (var i = 0; i < catids.length; i++) {
					cats[ i ] = { 'catId' : catids[ i ] , 'catName' : catText[ i ] };
				}
				formObject[ 'cats' ] = cats;
				// 品牌
				var brands = [ ];
				$this.find('#f_brand:eq(0) option').each(function(index) {
					brands[ brands.length ] = { 'brandId' : $(this).val() , 'brandName' : $(this).text() };
				});
				formObject[ 'brands' ] = brands;
				// 标签
				var tags = [ ];
				$this.find('#f_tag:eq(0) option').each(function(index) {
					tags[ tags.length ] = { 'id' : $(this).val() , 'tag' : $(this).text() };
				});
				formObject[ 'tags' ] = tags;
			}
			accessoryJson.push(formObject);
		})
		if(accessoryJson && accessoryJson.length>0){
			param.accessoryJson=accessoryJson;
		}
		// 货品信息
		param.products = $('#spec-window').data('ProductData');
		// 详细介绍
		param.info.intro = $('#cle-good-detail-info').val();
		param.info.specDesc = $('#spec-window').data('specDesc');
		param.info.attrDesc='';
		console.log(JSON.stringify(param));
		commonAjax(_path + '/goods/saveGoodInfo', { 'param' : JSON.stringify(param) }, function(data) {
			self.close();
		}, function(data) {
			self.close();
		})
	});
});
