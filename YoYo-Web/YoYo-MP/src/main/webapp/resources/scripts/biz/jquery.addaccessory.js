/**
 * 添加套装
 */
;
(function($) {
	var datakey = 'data-accessory';

	$.fn.addaccessory = function() {
		var method = arguments[0];
		if (Accessory.prototype[method]) {
			settings = $.extend({}, $.fn.addaccessory.defaults, arguments[1]);
		} else {
			$.error("Method" + method + "does not exist on jQuery.addaccessory");
			return this;
		}
		return this.each(function() {
			// 只取集合中的第1个元素
			var $element = $(this);
			// 获取保存的实例化对象
			var instance = new Accessory(settings, $element);
			// 防止与静态方法重合，只运行原型上的方法 返回原型方法结果，否则返回undefined
			return Accessory.prototype[method] ? Accessory.prototype[method].apply(instance, arguments) : undefined;
		});
	};

	// 插件的defaults
	$.fn.addaccessory.defaults = {};
	// 构造函数
	var Accessory = function(_settings, _element) {
		this.forms = [];
		this.options = _settings;
		this.element = _element;
	};

	Accessory.prototype = {
		constructor : Accessory,
		// 添加panel入口
		kreatePanel : function() {
			var acctype = $(this.options.toolbar).find('input[name="accType"]:checked').val();
			if (acctype) {
				if (acctype == '1') {
					this.kreacteNormalPanel();
				} else {
					this.kreacteGroupPanel();
				}
			}
		},
		// 选择几件商品作为套装
		kreacteNormalPanel : function(data) {
			var $form = this.commonKreate(data);
			var that = this;
			var accessoryGoods = [];
			$form.find('tr:last td').append('<div class="object-select clearfix"  onclick="selectAccessoryGoods();"><div class="label">请选择套装商品</div><div class="handle">&nbsp;</div></div>');
			if (data != null && (data.accessoryGoods != null && data.accessoryGoods != '')) {
				accessoryGoods = data.accessoryGoods.split(",");

				var param = {};
				param.productId = accessoryGoods;
				if (that.options.companyId) {
					param.companyId = that.options.companyId;
				}
				commonAjax(_path + '/product/findProductByIds', param, function(data) {
					var html = '<ul class="accessory-product">';
					$.each(data.content, function(i, option) {
						html += '<li style="list-style: none;" accessory_id="' + option.productId + '"><a style="  margin-right: 10px;" onclick="deleteProduct()">删除</a><span style="  margin-right: 10px;">' + option.name + '</span></li>';
					});
					html += '</ul>';
					$form.find('tr:last td').append(html);
					that.forms[that.forms.length] = {
						id : 'acc-group-' + that.forms.length + 1
					};
					$form.attr('id', 'acc-group-' + that.forms.length).attr('accType', 'normal');
					that.element.data(datakey, that);
					that.render($form);
				}, null);

			} else {
				var html = '<ul class="accessory-product"></ul>';
				$form.find('tr:last td').append(html);
				this.forms[this.forms.length] = {
					id : 'acc-group-' + this.forms.length + 1
				};
				$form.attr('id', 'acc-group-' + this.forms.length).attr('accType', 'normal');
				this.element.data(datakey, this);
				this.render($form);
			}

		},
		// 选择一组商品搜索结果作为套装
		kreacteGroupPanel : function(data) {
			var $form = this.commonKreate(data);
			var catIds = null; // 分类id集合
			if (data) {
				var $filter_1 = $('<div><table width="100%">' + '<tr><th>按价格区间筛选</th><td><input type="text" name="pricefrom" value=' + data.priceform + '> - <input type="text" name="priceto" value=' + data.priceto + '> 元</td></tr>' + '<tr><th>按商品关键词筛选</th> <td><input type="text" name="searchKeywords" value=' + data.searchKeywords + '></td></tr>' + '</table></div>');
				var $filter_2 = $('<div><table style="width:auto;"><tr>' + '<td><label for="f_cat">商品分类 </label><select style="width:300px" id="f_cat" name="cat_id[]"></select></td></tr><tr>' + '<td><label for="f_brand">品牌 </label><select style="width:300px" id="f_brand" name="brand_id[]" multiple="multiple"></select></td>' + '</tr></table></div>');
				$form.find('tr:last td').append($filter_1).append($filter_2);
				var brandIds = (data.brandIds != '') ? data.brandIds.split(",") : null;
				catIds = (data.catIds != '') ? data.catIds.split(",") : null;
				this.getBrandData(_path + '/brandTypeShip/findShip', $form.find('#f_brand'), brandIds);
			} else {
				var $filter_1 = $('<div><table width="100%">' + '<tr><th>按价格区间筛选</th><td><input type="text" name="pricefrom" /> - <input type="text" name="priceto" /> 元</td></tr>' + '<tr><th>按商品关键词筛选</th> <td><input type="text" name="searchKeywords"/></td></tr>' + '</table></div>');
				var $filter_2 = $('<div><table style="width:auto;"><tr>' + '<td><label for="f_cat">商品分类 </label><select style="width:300px" id="f_cat" name="cat_id[]" ></select></td></tr><tr>' + '<td><label for="f_brand">品牌 </label><select style="width:300px" id="f_brand" name="brand_id[]" multiple="multiple"></select></td>' + '</tr></table></div>');
				$form.find('tr:last td').append($filter_1).append($filter_2);
				this.getBrandData(_path + '/brandTypeShip/findShip', $form.find('#f_brand'), null);
			}
			$form.find('#f_cat').trapown({
				url : _path + '/comboBox/cateTreeCombox',
				multiple : true,
				idField : 'catId',
				textField : 'catName',
				parentField : 'parentCatId',
				initParentId : 0,
				selected : catIds
			});
			this.forms[this.forms.length] = {
				id : 'acc-group-' + this.forms.length + 1
			};
			$form.attr('id', 'acc-group-' + this.forms.length).attr('accType', 'group');
			this.element.data(datakey, this);
			this.render($form);
		},
		// 获得汽车品牌数据
		getBrandData : function(url, obj, brandId) {
			$.getJSON(url, function(json) {
				var data = "[";

				$.each(json.content, function(i, n) {
					if (brandId != null && brandId.indexOf(n.brandId) != -1) { // 选择指定值
						data += '{ text: "' + n.brandName + '", id:' + n.brandId + ',selected:true },';
					} else {
						data += '{ text: "' + n.brandName + '", id:' + n.brandId + '},';
					}

				});
				data = data.length > 1 ? data.substring(0, data.length - 1) : data; // 除开没有数据的情况
				data += "]";
				obj.select2({
					data : eval(data)
				});
			});
		},
		commonKreate : function(data) {
			if (data) {
				var html = '<form class="acc-group-form"><table width="100%">' + '<tr><th>套装组名称：</th><td><input type="text" name="accGroupName" value=' + data.accGroupName + '><a onclick="this.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode.parentNode.parentNode.parentNode)" class="easyui-linkbutton l-btn l-btn-small" group="" id=""><span class="l-btn-left"><span class="l-btn-text">删除此组套装</span></span></a></td></tr>'
						+ '<tr><th>最小购买量：</th><td><input type="text" name="minBuy" value=' + data.minBuy + '></td></tr>' + '<tr><th>最大购买量：</th><td><input type="text" name="maxBuy" value=' + data.maxBuy + '></td></tr>';
				if (data.discType == 'discount') {
					html += '<tr><th>套装优惠：</th><td><input type="radio" name="discType" value="discount" checked="checked" onclick="this.parentNode.parentNode.nextSibling.firstChild.innerHTML=\'<th>优惠折扣：</th>\';">优惠某个折扣 <input type="radio" name="discType" value="minus" onclick="this.parentNode.parentNode.nextSibling.firstChild.innerHTML=\'<th>优惠金额：</th>\';">优惠一定金额</td></tr>';
				} else {
					html += '<tr><th>套装优惠：</th><td><input type="radio" name="discType" value="discount" onclick="this.parentNode.parentNode.nextSibling.firstChild.innerHTML=\'<th>优惠折扣：</th>\';">优惠某个折扣 <input type="radio" name="discType" value="minus" checked="checked" onclick="this.parentNode.parentNode.nextSibling.firstChild.innerHTML=\'<th>优惠金额：</th>\';">优惠一定金额</td></tr>';
				}

				html += '<tr><th>优惠折扣：</th><td><input type="text" name="credit" value=' + data.credit + '>(无优惠可不填；优惠9折就输入0.9，优惠100元就输入100)</td></tr>' + '<tr><th>添加套装商品：</th><td></td></tr></table></form>';
				return $(html);
			} else {
				return $('<form class="acc-group-form"><table width="100%">' + '<tr><th>套装组名称：</th><td><input type="text" name="accGroupName"><a onclick="this.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.removeChild(this.parentNode.parentNode.parentNode.parentNode.parentNode)" class="easyui-linkbutton l-btn l-btn-small" group="" id=""><span class="l-btn-left"><span class="l-btn-text">删除此组套装</span></span></a></td></tr>'
						+ '<tr><th>最小购买量：</th><td><input type="text" name="minBuy"></td></tr>' + '<tr><th>最大购买量：</th><td><input type="text" name="maxBuy"></td></tr>' + '<tr><th>套装优惠：</th><td><input type="radio" name="discType" value="discount" checked="checked" onclick="this.parentNode.parentNode.nextSibling.firstChild.innerHTML=\'<th>优惠折扣：</th>\';">优惠某个折扣 <input type="radio" name="discType" value="minus" onclick="this.parentNode.parentNode.nextSibling.firstChild.innerHTML=\'<th>优惠金额：</th>\';">优惠一定金额</td></tr>' + '<tr><th>优惠折扣：</th><td><input type="text" name="credit">(无优惠可不填；优惠9折就输入0.9，优惠100元就输入100)</td></tr>' + '<tr><th>添加套装商品：</th><td></td></tr></table></form>');
			}
		},
		render : function($html) {
			$(this.options.renderAim).append($html);
		},
		destory : function() {
			$this.removeData(datakey);
		},
		getAccessory : function() {
			var $this = this;
			commonAjax(_path + '/goods/getAccessoryGoods', {
				'goodsId' : this.options.goodsId
			}, function(data) {
				$.each(data.content, function(index, data) {
					if (data.actype == 'normal') {
						$this.kreacteNormalPanel(data);
					} else {
						$this.kreacteGroupPanel(data);
					}
				});

			}, null);
		}
	};
})(jQuery);