/**
 * 添加配件
 */
;
(function($) {
	var datakey = 'data-Extribute';

	$.fn.extribute = function() {
		var options = arguments[ 0 ];
		if (options && $.type(options) === 'object') {
			settings = $.extend({}, $.fn.extribute.defaults, options);
		} else {
			$.error("参数不合法！");
			return this;
		}
		return this.each(function() {
			// 只取集合中的第1个元素
			var $element = $(this);
			// 获取保存的实例化对象
			var instance = $element.data(datakey);
			// 若未保存实例化对象，则先保存实例化对象
			if (!instance) {
				$element.data(datakey, instance = new Extribute(settings, $element));
			}
			// 防止与静态方法重合，只运行原型上的方法 返回原型方法结果，否则返回undefined
			return Extribute.prototype.kreateFormble.apply(instance, arguments);
		});
	};
	// 插件的defaults
	$.fn.extribute.defaults = {};
	// 构造函数
	var Extribute = function(_settings, _element) {
		this.options = _settings;
		this.element = _element;
	}

	Extribute.prototype = {
		constructor : Extribute ,
		// 添加panel入口
		kreateFormble : function() {
			this._getElementData();
		} ,
		_getElementData : function() {
			if (this.options && this.options.catId) {
				var that = this;
				commonAjax(_path + '/goods/attributeValue', { goodsId : this.options.goodsId ,
					catId : this.options.catId }, function(data) {
					that.render(that._genDomElement(data.content));
				}, function(data) {
					console.log(data.head.retMsg);
				});
			}
		} ,
		_genDomElement : function(data) {
			var rootDom = $('<table></table>').addClass(this.options.cssClass);
			var trDom = $('<tr><th></th><td></td></tr>');
			$.each(data, function(index, ele) {
				var trDomCopy = trDom.clone();
				trDomCopy.find('th').text(ele.attrName + '：');
				if (ele.attrInputType == 'select') {
					var options = ele.attrValues ? ele.attrValues.split(',') : [ ];
					$.each(options, function(index, value) {
						if (value == ele.attrValue) {
							options[ index ] = '<option selected=true value="' + value + '">' + value + '</option>';
						} else {
							options[ index ] = '<option  value="' + value + '">' + value + '</option>';
						}
					});
					trDomCopy.find('td').append(
							$('<select name="' + ele.attrId + '" value="' + ele.attrValue + '">' + options.join('')
									+ '</select>'));
				} else {
					trDomCopy.find('td').append(
							$('<input />', { name : ele.attrId , type : 'text' , value : ele.attrValue }));

				}
				rootDom.append(trDomCopy);
			});
			return rootDom;
		} , render : function($html) {
			this.element.append($html);
		} , destory : function() {
			$this.removeData(datakey);
		} };
})(jQuery);