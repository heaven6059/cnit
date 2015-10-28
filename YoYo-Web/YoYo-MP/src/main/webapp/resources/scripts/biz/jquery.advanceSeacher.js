/**
 * 
 */
// 创建一个闭包
;
(function($, window, document, undefined) {
	// 插件的定义
	$.fn.advanceSeacher = function(options) {
		var opts = $.extend({}, $.fn.advanceSeacher.defaults, options);
		return this.each(function() {
			init(this, opts);
		});
	};

	function init(obj, options) {
		if (options.aimDataGrid) {
			var rootEle = genElement(options);
			renderElement(options.renderTo ? options.renderTo : options.aimDataGrid, rootEle, options.aimDataGrid);
			bindEvent(options.renderTo ? options.renderTo : options.aimDataGrid, obj.id, options.aimDataGrid);
		}
	}

	function genElement(options) {
		var _aimDataGrid = options.aimDataGrid;
		if ($('#' + _aimDataGrid)) {
			var $aimdg = $('#' + _aimDataGrid);
			var rootEle = $('<table />', {
				class : 'table-form'
			});
			$.each($aimdg.datagrid('options').columns[0], function(index, column) {
				if (!column.hidden && $.inArray(column.field, options.exceptField) == -1) {
					var fieldGroup = '';
					var _label = $('<label />').text(column.title);
					if (column.options && column.options.querytype) {
						var querytype = column.options.querytype;
						if (querytype == 'range') {// 范围查询
							fieldGroup = '<input type="text" name="min" class="' + column.options.clazz + '" datatype="' + column.options.datatype + '" style="width:87px;">到<input type="text" name="max" class="' + column.options.clazz + '" datatype="' + column.options.datatype + '" style="width:87px;">'
						}
						if (querytype == 'equal') {// 选择
							var items = [];
							items.push('<select>')
							items.push('<option value="">请选择</option>');
							$.each(column.options.items, function(index, item) {
								items.push('<option value="' + item.value + '">' + item.text + '</option>');
							});
							items.push('</select>')
							fieldGroup = items.join('');
						}
						if (querytype == 'like' || querytype == 'likeL' || querytype == 'likeR') {// 选择
							fieldGroup = '<input type="text" style="width:200px;">'
						}
						rootEle.append($('<tr class="' + querytype + '" name="' + column.field + 'Q">').append($('<td>', {
							class : 'table-form-label'
						}).css('text-align', 'right').append(_label)).append($('<td >').css('text-align', 'left').append($(fieldGroup))));
					} else {
						fieldGroup = '<input type="text" style="width:200px;">'
						rootEle.append($('<tr class="like" name="' + column.field + 'Q">').append($('<td>', {
							class : 'table-form-label'
						}).css('text-align', 'right').append(_label)).append($('<td >').css('text-align', 'left').append($(fieldGroup))));
					}
				}
			});
			var advsForm = $('<form />', {
				id : $aimdg.datagrid('options').id + '_asf'
			}).append(rootEle);
			var doSearchButton = $('<a />', {
				class : 'easyui-linkbutton',
				plain : true,
				id : $aimdg.datagrid('options').id + '_dosb',
				iconCls : 'icon-search'
			}).text('查询');
			var doClearForm = $('<a />', {
				class : 'easyui-linkbutton',
				plain : true,
				id : $aimdg.datagrid('options').id + '_docf',
				iconCls : 'icon-clear'
			}).text('清除条件');
			var advsLayout = $('<div />', {
				class : 'easyui-layout',
				fit : true
			}).append($('<div />', {
				region : 'center',
				fit : true
			}).append(advsForm)).append($('<div />', {
				region : 'south',
				border : true,
				style : 'text-align: center; height: 30px; line-height: 30px;'
			}).append(doSearchButton).append(doClearForm));
			return advsLayout;
		}
	}

	function bindEvent(_parent, _buttonId, _aimGrid) {
		$('#' + _buttonId).on('click', function() {
			var width = $('#' + _parent).parent().width();
			$('#' + _aimGrid + '_asw').window('open').window('resize', {
				top : 0,
				left : width - $('#' + _aimGrid + '_asw').window('options').width
			});
		})
		$('#' + _aimGrid + '_dosb').on('click', function() {
			var params = {}// biz.serializeObject($('#' + _aimGrid + '_asf'));
			var likes = [];
			var ranges = [];
			var equals = [];
			$.each($('#' + _aimGrid + '_asf table tr'), function(index, element) {
				$element = $(element);
				var clazz = $(element).attr('class').split(' ');
				// 模糊查询
				if ($.inArray('like', clazz) != -1) {
					var $input = $($element.find('input:eq(0)'));
					if ($input.val()) {
						likes.push({
							paramName : $element.attr('name'),
							paramValue : $input.val(),
							wildType : 'both'
						})
					}
				}
				if ($.inArray('likeL', clazz) != -1) {
					var $input = $($element.find('input:eq(0)'));
					if ($input.val()) {
						likes.push({
							paramName : $element.attr('name'),
							paramValue : $input.val(),
							wildType : 'left'
						})
					}
				}
				if ($.inArray('likeR', clazz) != -1) {
					var $input = $($element.find('input:eq(0)'));
					if ($input.val()) {
						likes.push({
							paramName : $element.attr('name'),
							paramValue : $input.val(),
							wildType : 'right'
						})
					}
				}
				// 范围查询
				if ($.inArray('range', clazz) != -1) {
					var inputs = $element.find('input');

					var formatter = {
						datatime : function(val) {
							if (val) {
								return val.replace(' ', '').replace('-', '').replace(':', '');
							} else {
								return val;
							}
						}
					}
					var $min = $(inputs[0]);
					var $max = $(inputs[1]);
					
					if(inputs.length==6){
						$min = $(inputs[2]);
						$max = $(inputs[5]);
					}
					var min = $min.attr('datatype') ? (formatter[$min.attr('datatype')] ? formatter[$min.attr('datatype')].apply(this, [$min.val()]) : $min.val()) : $min.val();
					var max = $max.attr('datatype') ? (formatter[$max.attr('datatype')] ? formatter[$max.attr('datatype')].apply(this, [$max.val()]) : $max.val()) : $max.val();
					if(inputs.length==6){
						min=min.replace(' ', '').replace(/-/gm,'').replace(/:/gm, '');
						max=max.replace(' ', '').replace(/-/gm,'').replace(/:/gm, '');
					}
					if (min || max) {
						ranges.push({
							paramName : $element.attr('name'),
							paramMinValue : min,
							paramMaxValue : max
						})
					}
				}
				// 精确查找
				if ($.inArray('equal', clazz) != -1) {
					var $input = $element.find('select:eq(0)') || $element.find('input:eq(0)');
					if ($input.val()) {
						equals.push({
							paramName : $element.attr('name'),
							paramValue : $input.val()
						});
					}
				}
			});
			params.likes = likes;
			params.ranges = ranges;
			params.equals = equals;
			$('#' + _aimGrid).datagrid('reload', {
				params : JSON.stringify(params)
			});
		})
		$('#' + _aimGrid + '_docf').on('click', function() {
			$('#' + _aimGrid + '_asf').form('clear');
			$('#' + _aimGrid).datagrid('reload', {
				params : {}
			});
		})
	}

	function renderElement(_parent, _rootEle, _aimDataGrid) {
		var advsWindow = $('<div />', {
			id : _aimDataGrid + '_asw'
		});
		$('#' + _parent).append(advsWindow);
		$('#' + _aimDataGrid + '_asw').window({
			title : '高级筛选',
			width : 400,
			height : 500,
			closed : true,
			inline : true,
			cache : false,
			resizable : true,
			minimizable : false,
			maximizable : false,
			collapsible: true,
			content : _rootEle,
			zIndex : 9010
		});
	}

	// 定义暴露format函数
	$.fn.advanceSeacher.format = function(txt) {
		return '<strong>' + txt + '</strong>';
	};
	// 插件的defaults
	$.fn.advanceSeacher.defaults = {
		foreground : 'red',
		background : 'yellow',
		exceptField : [],
		fieldSubfix : 'Q'
	};
	// 闭包结束
})(jQuery, window, document);