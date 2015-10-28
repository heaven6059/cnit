/**
 * 别人家的表单验证插件用着不顺手 遂自己写了个 创建日期：2015-04-30 作者：李明 使用说明：
 * ------------------------------------------------------------------------------------
 * 本插件依赖jquery与jquery.metadata插件 请在使用前导入以上插件
 * 用户可通过改变$.fn.walidator.defaults的值来改变提示方式与表单项css
 */
;
(function($) {
	/* 元素过滤配置 */
	var elementSupport = [":text", ":password", "textarea", "select", "input[type='hidden']", "input[type='tel']", "input[type='email']", "input[type='number']", "input[type='file']"].join(", ");
	/* 校验函数 */
	var walidate = function($element) {
		var result = true;
		var walitype = $element.metadata();
		if (!$.isEmptyObject(walitype)) {
			$.each(walitype, function(index, value) {
				var validator = $.fn.walidator.validator[index];
				if (validator) {
					$element.removeClass($.fn.walidator.defaults.style.nowalitated);
					$element.removeClass($.fn.walidator.defaults.style.walitated);
					if (!validator.call($element, value)) {
						$element.addClass($.fn.walidator.defaults.style.nowalitated);
						$element.focus();
						result = result && false;
						return false;
					}
				}
				$element.addClass($.fn.walidator.defaults.style.walitated);
				result = result && true;
			});
		} else {
			$element.addClass($.fn.walidator.defaults.style.walitated);
			result = result && true;
		}
		return result;
	};
	/* 插件定义 */
	$.fn.walidator = function() {
		var thisArgs = arguments;
		// 指定metadata插件获取数据的元素属性名字
		$.metadata.setType('attr', 'walitype');
		return this.each(function() {
			var $element = $(this);
			if ($element.is('form')) {
				if (!thisArgs[0]) {
					var walidataTime = $element.attr('WalidateTime');
					// 表单项改变时
					$.each($element.find('.walidator').filter(elementSupport), function(index, item) {
						var $item = $(item);
						walidate($item);
						if ('realtime' == walidataTime) {
							$item.on('keyup.walidator', function() {
								walidate($item);
							});
							$item.on('focusout.walidator', function() {
								walidate($item);
							});
						}
					});
				}
				if (thisArgs[0] && thisArgs[0] == 'isValidated') {
					var result = true;
					$.each($element.find('.walidator').filter(elementSupport), function(index, item) {
						result = result && walidate($(item));
					});
					$element.attr('isValidated', result);
				}
			}
		});
	};
	/* 插件默认设置 */
	$.fn.walidator.defaults = {style : {nowalitated : 'nowalitated', walitated : 'walitated'}, promptor : function(element, msg) {}};
	/* 正则匹配器 */
	$.fn.walidator.patterns = {
		integer : /^\d+$/,
		decimal_6_2 : /^(0|([1-9]\d{0,5}))(\.\d{1,2})?$/,
		chinese : /^[\u4e00-\u9fa5]?$/,
		date : /^((0?\d)|(1[012]))[\/-]([012]?\d|30|31)[\/-]\d{1,4}$/,
		email : /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
		usd : /^\$?((\d{1,3}(,\d{3})*)|\d+)(\.(\d{2})?)?$/,
		url : /^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
		number : /^[+-]?(\d+(\.\d*)?|\.\d+)([Ee]-?\d+)?$/, zip : /^\d{5}(-\d{4})?$/, phone : /^[2-9]\d{2}-\d{3}-\d{4}$/, guid : /^(\{?([0-9a-fA-F]){8}-(([0-9a-fA-F]){4}-){3}([0-9a-fA-F]){12}\}?)$/, time12 : /^((0?\d)|(1[012])):[0-5]\d?\s?[aApP]\.?[mM]\.?$/, time24 : /^(20|21|22|23|[01]\d|\d)(([:][0-5]\d){1,2})$/, nonHtml : /^[^<>]*$/};
	/* 提示信息 */
	$.fn.walidator.messages = {require : "#{field} is required.", match : "#{field} is in an invalid format.", integer : "#{field} must be a positive, whole number.", date : "#{field} must be formatted as a date. (mm/dd/yyyy)", email : "#{field} must be formatted as an email.", usd : "#{field} must be formatted as a US Dollar amount.", url : "#{field} must be formatted as a URL.", number : "#{field} must be formatted as a number.",
		zip : "#{field} must be formatted as a zipcode ##### or #####-####.", phone : "#{field} must be formatted as a phone number ###-###-####.", guid : "#{field} must be formatted as a guid like {3F2504E0-4F89-11D3-9A0C-0305E82C3301}.", time24 : "#{field} must be formatted as a 24 hour time: 23:00.", time12 : "#{field} must be formatted as a 12 hour time: 12:00 AM/PM", lessThan : "#{field} must be less than #{max}.", lessThanOrEqualTo : "#{field} must be less than or equal to #{max}.",
		greaterThan : "#{field} must be greater than #{min}.", greaterThanOrEqualTo : "#{field} must be greater than or equal to #{min}.", range : "#{field} must be between #{min} and #{max}.", tooLong : "#{field} cannot be longer than #{max} characters.", tooShort : "#{field} cannot be shorter than #{min} characters.", nonHtml : "#{field} cannot contain HTML characters.", alphabet : "#{field} contains disallowed characters.",
		minCharClass : "#{field} cannot have more than #{min} #{charClass} characters.", maxCharClass : "#{field} cannot have less than #{min} #{charClass} characters.", equal : "Values don't match.", distinct : "A value was repeated.", sum : "Values don't add to #{sum}.", sumMax : "The sum of the values must be less than #{max}.", sumMin : "The sum of the values must be greater than #{min}.", radioChecked : "The selected value is not valid.", checkboxChecked : "#{field} must be checked.",
		generic : "Invalid."};
	/* 校验器 */
	$.fn.walidator.validator = {require : function(val) {
		if (val) {
			return $(this).val() != null && !!$(this).val().length;
		}
		return true;
	}, match : function(rule) {
		var inputVal = this.val();
		if (typeof (rule) == "string") {
			rule = $.fn.walidator.patterns[rule];
		}
		return $.isFunction(rule) ? !inputVal.length || rule(inputVal) : !inputVal.length || rule.test(inputVal);
	}, range : function(min, max) {
		return validate(this, min.getTime && max.getTime ? orEmpty(function(obj) {
			var d = new Date(obj.value);
			return d >= new Date(min) && d <= new Date(max);
		}) : min.substring && max.substring && Big ? orEmpty(function(obj) {
			var n = new Big(obj.value);
			return (n.greaterThanOrEqualTo(new Big(min)) && n.lessThanOrEqualTo(new Big(max)));
		}) : orEmpty(function(obj) {
			var f = parseFloat(obj.value);
			return f >= min && f <= max;
		}),

		msg || format($.walidator.messages.range, {min : $.walidator.settings.argToString(min), max : $.walidator.settings.argToString(max)}));
	}, greaterThan : function(min, msg) {
		return validate(this, min.getTime ? orEmpty(function(obj) {
			return new Date(obj.value) > min;
		}) : min.substring && Big ? orEmpty(function(obj) {
			return new Big(obj.value).greaterThan(new Big(min));
		}) : orEmpty(function(obj) {
			return parseFloat(obj.value) > min;
		}), msg || format($.walidator.messages.greaterThan, {min : $.walidator.settings.argToString(min)}));
	}, greaterThanOrEqualTo : function(min, msg) {
		return validate(this, min.getTime ? orEmpty(function(obj) {
			return new Date(obj.value) >= min;
		}) : min.substring && Big ? orEmpty(function(obj) {
			return new Big(obj.value).greaterThanOrEqualTo(new Big(min));
		}) : orEmpty(function(obj) {
			return parseFloat(obj.value) >= min;
		}), msg || format($.walidator.messages.greaterThanOrEqualTo, {min : $.walidator.settings.argToString(min)}));
	}, lessThan : function(max, msg) {
		return validate(this, max.getTime ? orEmpty(function(obj) {
			return new Date(obj.value) < max;
		}) : max.substring && Big ? orEmpty(function(obj) {
			return new Big(obj.value).lessThan(new Big(max));
		}) : orEmpty(function(obj) {
			return parseFloat(obj.value) < max;
		}), msg || format($.walidator.messages.lessThan, {max : $.walidator.settings.argToString(max)}));
	}, lessThanOrEqualTo : function(max, msg) {
		return validate(this, max.getTime ? orEmpty(function(obj) {
			return new Date(obj.value) <= max;
		}) : max.substring && Big ?

		orEmpty(function(obj) {
			return new Big(obj.value).lessThanOrEqualTo(new Big(max));
		}) :

		orEmpty(function(obj) {
			return parseFloat(obj.value) <= max;
		}),

		msg || format($.walidator.messages.lessThanOrEqualTo, {max : $.walidator.settings.argToString(max)}));
	}, maxLength : function(max) {
		var inputVal = this.val();
		return inputVal.length <= max;
	}, minLength : function(min) {
		var inputVal = this.val();
		return inputVal.length >= min;
	}, alphabet : function(alpha) {
		var inputVal = this.val();
		for ( var idx = 0; idx < obj.value.length; ++idx) {
			if (alpha.indexOf(obj.value.charAt(idx)) == -1) {
				return false;
			}
		}
		return true;
	}, nonHtml : function() {
		var inputVal = this.val();
		return $.walidator.patterns.nonHtml.test(obj.value);
	}, equal : function(arg0, arg1) {
		$.walidator.registerReduction(this);

		var $reduction = (this.reduction || this).filter($.walidator.settings.elementSupport),

		transform = function(val) {
			return val;
		},

		msg = $.walidator.messages.equal;

		if ($reduction.length) {
			if ($.isFunction(arg0)) {
				transform = arg0;

				if (typeof (arg1) == "string") {
					msg = arg1;
				}
			}

			else if (typeof (arg0) == "string") {
				msg = arg0;
			}

			var map = $.map($reduction, function(obj) {
				return transform(obj.value);
			}), first = map[0], valid = true;
			for ( var i in map) {
				if (map[i] != first) {
					valid = false;
				}
			}

			if (!valid) {
				raiseAggregateError($reduction, msg);
				this.reduction = $([]);
			}
		}

		return this;
	}, sum : function(sum, msg) {
		$.walidator.registerReduction(this);
		var $reduction = (this.reduction || this).filter($.walidator.settings.elementSupport);

		if ($reduction.length && sum != numericSum($reduction)) {
			raiseAggregateError($reduction, msg || format($.walidator.messages.sum, {sum : sum}));
			this.reduction = $([]);
		}

		return this;
	}, sumMax : function(max, msg) {
		$.walidator.registerReduction(this);
		var $reduction = (this.reduction || this).filter($.walidator.settings.elementSupport);

		if ($reduction.length && max < numericSum($reduction)) {
			raiseAggregateError($reduction, msg || format($.walidator.messages.sumMax, {max : max}));

			// The set reduces to zero valid elements.
			this.reduction = $([]);
		}

		return this;
	}, sumMin : function(min, msg) {
		$.walidator.registerReduction(this);
		var $reduction = (this.reduction || this).filter($.walidator.settings.elementSupport);

		if ($reduction.length && min > numericSum($reduction)) {
			raiseAggregateError($reduction, msg || format($.walidator.messages.sumMin, {min : min}));
			this.reduction = $([]);
		}

		return this;
	}, radioChecked : function(val, msg) {
		$.walidator.registerReduction(this);
		var $reduction = (this.reduction || this).filter($.walidator.settings.elementSupport);

		if ($reduction.is(":radio") && $reduction.find(":checked").val() != val) {
			raiseAggregateError($reduction, msg || $.walidator.messages.radioChecked);
		}
	}, radioNotChecked : function(val, msg) {
		$.walidator.registerReduction(this);
		var $reduction = (this.reduction || this).filter($.walidator.settings.elementSupport);

		if ($reduction.is(":radio") && $reduction.filter(":checked").val() == val) {
			raiseAggregateError($reduction, msg || $.walidator.messages.radioChecked);
		}
	}, checkboxChecked : function(msg) {
		return validate(this, function(obj) {
			return !$(obj).is(":checkbox") || $(obj).is(":checked");
		}, msg || $.walidator.messages.checkboxChecked);
	}};
})(jQuery);