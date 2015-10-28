/**
 * 商品分类选择弹出框 xiaox 2015.04.07
 */

;
(function($, window, document, undefined) {
	$.fn.categoryDialog = function(options, param) {
		var otherArgs = Array.prototype.slice.call(arguments, 1);
		if (typeof options == 'string') {
			var fn = this[ 0 ][ options ];
			if ($.isFunction(fn)) {
				return fn.apply(this, otherArgs);
			} else {
				throw ("categoryDialog - No such method: " + options);
			}
		}
		;

		return this.each(function() {
			var para = {}; // 保留参数
			var self = this; // 保存组件对象
			var categoryArr = new Array(); // 存放选择的分类名称
			var categoryIds = new Array(); //存放选择的分类id的集合
			var categoryType =0; //存放分类的类型，如：999表示整车 888配件

			var defaults = { width : "700px" , // 宽度
			height : "480px" , // 高度
			url : "/cate/cateTree" , // 分类数据的url
			onSuccess : function(rootCategoryId,categoryType,text) {} , // 加载成功,text:叶子节点的值
			};

			para = $.extend(defaults, options);

			this.init = function() {
				if(para.parentCatId!=null && para.parentCatId!=''){
					getCategoryInfo({ parentCatId : para.parentCatId });
				}else{
					getCategoryInfo();
				}
				createHtml(); // 创建组件html
				submintCate(); // 绑定提交事件
			};

			/**
			 * 获得分类信息
			 */
			var getCategoryInfo = function(arg_data) {
				$.ajax({ url : para.url , type : "post" , data : arg_data , datatype : "json" , success : function(data) {
					if (data.length > 0) {
						var initHtml = '<ul class="cat_select">';
						   if (data.length >= 10) { // 大于10条就显示查询框
							initHtml += '<li class="filter_item"><input autofocus="" id="' + data[ 0 ].catId + '-filter' + '" class="ipt_search ui-widget cat_filter" placeholder="输入关键字"';
							initHtml += 'style="color: rgb(0, 0, 0);"></li>';
						} 
						$.each(data, function(i, n) {
							if (n.childCount > 0) { // 存在下级分类
								initHtml += '<li class="categoryIsParent categoryclass"  cat_id="' + n.catId + '" identifier="'+n.identifier+'">';
							} else {
								initHtml += '<li class="categoryIsLeaf categoryclass" cat_id="' + n.catId + '"  identifier="'+n.identifier+'">';
							}
							initHtml += n.catName + '<span class="icon"></span></li>';
						});
						initHtml += '</ul>';
						$('.goods_category_body').append(initHtml);
						// 绑定选择事件
						addEvent();
						// 修改父级的宽度,默认为418px
						$('.goods_category_body').css('width', 418 + 209 * ($('.goods_category_body').find('ul').length - 2));
						$('.category_filter').animate({'scrollLeft':209 * ($('.goods_category_body').find('ul').length - 2)},800);
						
					}
				} });
			};

			/**
			 * 获得品牌信息
			 */
			var getBrandInfo = function(arg_data) {
				$.ajax({ url : _path + '/brandTypeShip/findBrandCateShip' , type : "post" , data : arg_data , datatype : "json" , success : function(result) {
					var data = result.content;
					if (data.length > 0) {
						var initHtml = '<ul class="cat_select">';
						if (data.length >= 10) { // 大于10条就显示查询框
							initHtml += '<li class="filter_item"><input autofocus="" id="' + data[ 0 ].brandId + '-filter' + '" class="ipt_search ui-widget brand_filter" placeholder="输入关键字"';
							initHtml += 'style="color: rgb(0, 0, 0);"></li>';
						}
						$.each(data, function(i, n) {
							initHtml += '<li class="brandIsLeaf" brand_id="' + n.brandId + '">';
							initHtml += n.brandName + '</li>';
						});
						initHtml += '</ul>';
						$('.goods_category_body').append(initHtml);
						// 绑定品牌选择事件
						addBrandEvent();
						// 修改父级的宽度,默认为418px
						$('.goods_category_body').css('width', 418 + 209 * ($('.goods_category_body').find('ul').length - 2));
						$('.category_filter').animate({'scrollLeft':209 * ($('.goods_category_body').find('ul').length - 2)},800);
					} else {
						$('.isLeafNode').val('true');
					}
				} });
			};
			
			

			/**
			 * 功能：创建弹出框所使用的html 参数: 无 返回: 无
			 */
			var createHtml = function() {

				var html = '';

				// 创建html
				html += '<div id="dialog_136" class="dialog dialog_div"	style="visibility: visible; zoom: 1; opacity: 1; z-index: 65534; border: none; width: 622px; position: absolute; top: 51px; ">';
				html += '<div class="dialog-box" style="display: block">';
				html += '<div class="dialog-head clearfix">';
				html += '<div class="dialog-title flt"';
				html += 'style="background: -moz-linear-gradient(center top, white, #E0E3EC) repeat scroll 0 0 #F0F0F0;">分类选择</div>';
				html += '<div class="dialog_close" >✖</div>';
				html += '</div>';
				html += '<div class="dialog-content-head" style="font-size: 0; height: 0;"></div>';
				html += '<div  class="dialog-content-body"	style="height: 400px; width: 600px;">';
				html += '<div class="category_wrap">';
				html += '<div class="category_filter">';
				html += '<div class="goods_category_body" style="width: 418px;">'; // 修改width
				// 动态获取数据进行填充

				html += '</div></div>';
				html += '<div class="cat_catpath">';
				html += '<div class="cat_catpath_info">';
				html += '	<strong>您当前选择的类目是：</strong><span class="cat_info"></span>';
				html += '	<input name="catId" id="cat_id" type="hidden">';
				html += '	<input name="brandId" id="brand_id" type="hidden" >';
				html += '</div></div></div>';

				// 底部确定按钮
				html += '<div class="dialog-content-foot">';
				html += '<div class="table-action">';
				html += '	<button class="btn btn-primary confirm_btn" type="button">';
				html += '		<span><span>确定</span></span>';
				html += '	</button>';
				html += '	<input type="hidden" class="isLeafNode" value="false"><input type="hidden" class="leafVal">';
				html += '</div>	</div>';
				html += '</div></div>';

				$(self).append(html).css({ "width" : para.width , "height" : para.height });

			};

			/**
			 * 提交
			 */
			var submintCate = function() {

				$('.confirm_btn').bind("click", function(e) {
					// 先进行判断是否是叶子节点
					if ($('.isLeafNode').val() == 'false') {
						$.messager.show({ title : '提示' , msg : '请选择子级分类' });
						return false;
					}
					//$('.label').text($('.leafVal').val());
					$('#category_dialog').hide();
					para.onSuccess(categoryIds[0],categoryType,$('.leafVal').val()); // 回调函数
				});
			};

			/**
			 * 功能：绑定事件 参数: 无 返回: 无
			 */
			var addEvent = function() {
				// 如果分类li存在
				if ($(".categoryIsParent").length > 0) {
					$(".categoryIsParent").unbind("click");
					// 绑定选择事件
					$(".categoryIsParent").bind("click", function(e) {
						var $this = $(this);

						$this.siblings().removeClass('cur'); // 去掉其他所有的选择状态
						$this.addClass('cur');
						getCategoryInfo({ parentCatId : $this.attr('cat_id') });
						$this.parent().nextAll().remove(); // 删除之后的分类列表
						setCatInfo($this);
						$('.isLeafNode').val('false');
					});
				}

				/* 分类叶子节点点击事件 */
				if ($(".categoryIsLeaf").length > 0) {
					$(".categoryIsLeaf").unbind("click");
					// 绑定选择事件
					$(".categoryIsLeaf").bind("click", function(e) {
						var $this = $(this);
						$this.siblings().removeClass('cur'); // 去掉其他所有的选择状态
						$this.addClass('cur');
						getBrandInfo({ categoryId : $this.attr('cat_id') }); // 获取品牌信息
						$this.parent().nextAll().remove(); // 删除之前的分类列表
						setCatInfo($this);
						$('#cat_id').val($this.attr('cat_id'));
						$('.isLeafNode').val('false');
						categoryType=$this.attr('identifier');
					});
				}
				
				
				//分类搜索过滤
				if($('.cat_filter')){
					$(".cat_filter").unbind("keyup");
					// 绑定选择事件
					$(".cat_filter").bind("keyup", function(e) {
						var $this = $(this);
						//$(".brandIsLeaf");
						$.grep($(".categoryclass"),function(n,i){
							 var matcher = new RegExp(  $.ui.autocomplete.escapeRegex( $this.val() ), "i" );
							 if(matcher.test( $(n).text() )){
								 $(n).show();
							 }else{
								 $(n).hide();
							 }
							return matcher.test( $(n).text() );
							
						});
					});
				}

			};

			/**
			 * 功能：绑定品牌事件 参数: 无 返回: 无
			 */
			var addBrandEvent = function() {

				/* 品牌叶子节点点击事件 */
				if ($(".brandIsLeaf").length > 0) {
					$(".brandIsLeaf").unbind("click");
					// 绑定选择事件
					$(".brandIsLeaf").bind("click", function(e) {
						var $this = $(this);
						$this.siblings().removeClass('cur'); // 去掉其他所有的选择状态
						$this.addClass('cur');
						setCatInfo($this);
						$('#brand_id').val($this.attr('brand_id'));
						$('.isLeafNode').val('true');
					});
				}
				
				//品牌搜索过滤
				if($('.brand_filter')){
					$(".brand_filter").unbind("keyup");
					// 绑定选择事件
					$(".brand_filter").bind("keyup", function(e) {
						var $this = $(this);
						//$(".brandIsLeaf");
						$.grep($(".brandIsLeaf"),function(n,i){
							 var matcher = new RegExp(  $.ui.autocomplete.escapeRegex( $this.val() ), "i" );
							 if(matcher.test( $(n).text() )){
								 $(n).show();
							 }else{
								 $(n).hide();
							 }
							return matcher.test( $(n).text() );
							
						});
					});
				}

			};

			/**
			 * 设置选择的分类项，展示给客户看
			 */
			var setCatInfo = function($this) {
				var cateInfo = '';
				// 获得选择的分类值，展示
				var index = $('.goods_category_body ul').index($this.parent()); // 第几个ul
				categoryArr.splice(index, categoryArr.length - index); // 删除点击ul后的所有之前选择的项
				categoryArr[ index ] = $this.text(); // 获得当前点击的li的值存放到ul对应的数组中
				categoryIds[index] = $this.attr('cat_id');
				$.each(categoryArr, function(key, val) {
					cateInfo += val + '>';
				});
				$('.cat_info').html(cateInfo.substring(0, cateInfo.length - 1));
				$('.leafVal').val($this.text()); // 存放每次选择的值
			};

			// 初始化上传控制层插件
			this.init();
		});
	};
})(jQuery, window, document);