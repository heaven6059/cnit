;
(function($) {
	var MicroEvent = function() {
	};
	MicroEvent.prototype = {
		on : function(event, fct) {
			this._events = this._events || {};
			this._events[event] = this._events[event] || [];
			this._events[event].push(fct);
		},
		off : function(event, fct) {
			var n = arguments.length;
			if (n === 0)
				return delete this._events;
			if (n === 1)
				return delete this._events[event];

			this._events = this._events || {};
			if (event in this._events === false)
				return;
			this._events[event].splice(this._events[event].indexOf(fct), 1);
		},
		trigger : function(event /* , args... */) {
			this._events = this._events || {};
			if (event in this._events === false)
				return;
			for (var i = 0; i < this._events[event].length; i++) {
				this._events[event][i].apply(this, Array.prototype.slice.call(
						arguments, 1));
			}
		}
	};

	MicroEvent.mixin = function(destObject) {
		var props = [ 'on', 'off', 'trigger' ];
		for (var i = 0; i < props.length; i++) {
			destObject.prototype[props[i]] = MicroEvent.prototype[props[i]];
		}
	};

	var Trapown = function($input, settings) {
		var self = this, input = $input[0];
		input.trapown = self;
		Trapown.count = Trapown.count + 1;
		$.extend(self, {
			settings : settings,
			$input : $input,
			trapownDropdownId : 'trapown-dropdown-' + Trapown.count,
			hasMoreData : true,
			data : []
		});
		$(input).attr('trapown-dropdown-id', self.trapownDropdownId);
		self.setup();
		self.createTropdown();
	};
	Trapown.count = 0;
	Trapown.defaults = {
		trapownWrapperClass : 'trapown-wrapper',
		trapownControlClass : 'trapown-input',
		trapownDropdownClass : 'trapown-dropdown',
		trapownDropdownContentClass : 'trapown-dropdown-content',
		trapownDropdownItemClass : 'trapown-item',
		trapownDropdownPagenationClass : 'trapown-pagenation',
		valueTag : 'value-tag',
		needCacheData : true,
		pageIndex : 1,
		pageSize : 20,
		afterSelectItem:$.noop
	};
	Trapown.prototype = {
		setup : function() {
			var self = this;
			var settings = self.settings;
			var $input = self.$input;
			var inputMode = self.settings.mode;
			var $inputWidth = $input.outerWidth();
			var $inputHeight = $input.outerHeight();

			var $trapownWrapper = $('<div>').addClass(
					settings.trapownWrapperClass).css('width', $inputWidth)
					.addClass(inputMode).attr('id', self.trapownDropdownId);
			var $trapownControl = $('<div>').addClass(
					settings.trapownControlClass).css({
				'min-height' : $inputHeight
			}).appendTo($trapownWrapper);
			var $trapownControlInput = $('<input type="text" />').appendTo(
					$trapownControl);
			var $trapownDropdown = $('<div>').addClass(
					settings.trapownDropdownClass).css('width', $inputWidth)
					.addClass(inputMode).hide().appendTo($trapownWrapper);
			var $trapownDropdownContent = $('<div>').addClass(
					settings.trapownDropdownContentClass).appendTo(
					$trapownDropdown);
			var $trapownDropdownPagenation = $('<div>').addClass(
					settings.trapownDropdownPagenationClass).appendTo(
					$trapownDropdown);
			var $trapownDropdownPagenationPrev = $('<div>').addClass('prev')
					.text('上一页').appendTo($trapownDropdownPagenation);
			var $trapownDropdownPagenationNext = $('<div>').addClass('next')
					.text('下一页').appendTo($trapownDropdownPagenation);
			var $trapownDropdownItem = $('<div>').addClass(
					settings.trapownDropdownItemClass);
			var $selete_item = $('<div>').addClass(settings.valueTag).append('<span></span>');

			self.$trapownWrapper = $trapownWrapper;
			self.$trapownControl = $trapownControl;
			self.$trapownControlInput = $trapownControlInput;
			self.$trapownDropdown = $trapownDropdown;
			self.$trapownDropdownContent = $trapownDropdownContent;
			self.$trapownDropdownPagenation = $trapownDropdownPagenation;
			self.$trapownDropdownItem = $trapownDropdownItem;
			self.$selete_item = $selete_item;

			// 绑定分页按钮触发事件
			$trapownDropdownPagenationPrev.on('click', function() {
				return self.onPrevPage.apply(self, arguments);
			});
			$trapownDropdownPagenationNext.on('click', function() {
				return self.onNextPage.apply(self, arguments);
			});
			// 绑定下拉项目选择事件
			$trapownDropdownItem.on('mouseenter mouseout', function() {
				return self.onItemHover.apply(self, arguments);
			});
			$trapownDropdownItem.on('mousedown', function() {
				return self.onItemSelect.apply(self, arguments);
			});

			$trapownControl.on({
				click : function() {
					return self.onClick.apply(self, arguments);
				}
			});

			$trapownControlInput.on({
				focus : function() {
					self.ignoreBlur = false;
					return self.onFocus.apply(self, arguments);
				},
				click : function() {
					return self.onFocus.apply(self, arguments);
				},
				blur : function() {
					return self.onBlur.apply(self, arguments);
				},
				keydown : function() {
					return self.onKeydown.apply(self, arguments);
				}
			});

			self.createTropdown();
		},
		setInitParams : function(params) {
			var self = this;
			self.settings.searchParam.initParams = params;
			self.reset();
		},
		createTropdown : function() {
			var self = this;
			var $input = self.$input;
			$input.css('display', 'none');
			var $trapownWrapper = self.$trapownWrapper;
			$trapownWrapper.insertAfter($input);
		},
		onClick : function(e) {
			var self = this;
			if (!self.isFocused) {
				self.focus();
				e.preventDefault();
			}
		},
		onSearchChange : function(value) {
			var self = this;
			var fn = self.settings.load;
			if (!fn)
				return;
			if (self.loadedSearches.hasOwnProperty(value))
				return;
			self.loadedSearches[value] = true;
			self.load(function(callback) {
				fn.apply(self, [ value, callback ]);
			});
		},
		onFocus : function(e) {
			var self = this;
			var wasFocused = self.isFocused;
			if (!wasFocused)
				self.trigger('focus');
			if (self.$trapownDropdown.css('display') != 'block')
				self.showInput();
			if (!self.initailed)
				self.refreshOptions(!!self.settings.openOnFocus);
			self.isFocused = true;
		},
		onBlur : function(e) {
			var self = this;
			self.isFocused = false;
			self.hideInput();
		},
		onKeydown : function(e) {
			var self = this;
			e.stopPropagation();
			if (e.keyCode == 13) {
				self.search();
			}
		},
		onItemHover : function(e) {
			e.type == 'mouseenter' ? $(e.currentTarget).addClass('hover') : $(
					e.currentTarget).removeClass('hover');
		},
		onItemSelect : function(e) {
			e.preventDefault();
			var self = this;
			var $target = $(e.currentTarget);
			var $input = self.$input;
			var $trapownControlInput = self.$trapownControlInput;
			var $trapownControl = self.$trapownControl;
			if (!self.settings.multiple) {
				var $existItem = $target.siblings().filter('.selected:eq(0)');
				if ($existItem) {
					self.removeOption($existItem.attr('value'));
					$existItem.removeClass('selected');
				}
			}
			if ($.inArray('selected', $target.attr('class').split(' ')) == -1) {
				$target.addClass('selected');
				var $selete_item = self.$selete_item.clone(true);
				$selete_item.find('span:eq(0)').text($target.text());
				$selete_item.attr('data-value', $target.attr('value'));
				$selete_item.attr('data-text', $target.text());
				$selete_item.insertBefore($trapownControlInput);
				$('<option>', {'value' : $target.attr('value')}).text($target.text()).appendTo($input);
				if (self.settings.closeOnSelect) {
					self.hideInput();
				}
				self.settings.afterSelectItem.apply(self,$target);
			} else {
				$target.removeClass('selected');
				$input.find('option[value=' + $target.attr('value') + ']')
						.remove();
				$trapownControl.find(
						'div[data-value=' + $target.attr('value') + ']')
						.remove();
			}
		},
		onPrevPage : function(e) {
			var self = this;
			if (self.pageIndex > 1) {
				self.load(-1);
			}
		},
		onNextPage : function(e) {
			var self = this;
			self.load(1);
		},
		focus : function() {
			var self = this;
			if (self.isDisabled)
				return;

			self.ignoreFocus = true;
			self.$trapownControlInput[0].focus();
			window.setTimeout(function() {
				self.ignoreFocus = false;
				self.onFocus();
			}, 0);
		},
		hideInput : function() {
			var self = this;
			self.$trapownDropdown.css({
				display : 'none',
				visibility : 'visible'
			});
			self.$trapownControl.removeClass('focusin').addClass('focusout');
			self.isInputHidden = true;
		},
		showInput : function() {
			var self = this;
			self.$trapownDropdown.css({
				display : "block",
				visibility : 'visible'
			});
			self.$trapownControl.removeClass('focusout').addClass('focusin');
			self.isInputHidden = false;
		},
		refreshOptions : function(triggerDropdown) {
			var self = this;
			self.initailed = true;
			var paramValue = self.$trapownControlInput.val();
			var paramName = self.settings.searchParam.paramName;
			var param = {};
			if (paramValue) {
				param[paramName] = paramValue;
			}
			// 重置参数
			self.hasMoreData = true;
			param[self.settings.searchParam.pageIndexParam] = self.pageIndex;
			param[self.settings.searchParam.pageSizeParam] = self.pageSize;
			self.load($.extend(param,self.settings.searchParam.initParams), self.updateOptions);
		},
		search : function() {
			var self = this;
			var paramValue = self.$trapownControlInput.val();
			var paramName = self.settings.searchParam.paramName;
			var param = {};
			if (paramValue) {
				param[paramName] = paramValue;
			}
			// 重置参数
			self.pageIndex = 1;
			self.hasMoreData = true;
			param[self.settings.searchParam.pageIndexParam] = self.settings.pageIndex;
			param[self.settings.searchParam.pageSizeParam] = self.settings.pageSize;
			self.clearOption();
			self.load(param, self.updateOptions);
		},
		load : function(param, callback) {
			var self = this;
			if (self.hasMoreData) {
				$.ajax({
					url : self.settings.searchParam.url,
					data : param,
					type : self.settings.searchParam.type || 'POST'
				}).done(function(result) {
					var datapath = self.settings.searchParam.datapath;
					var data = result;
					if(datapath){
						var _path = datapath.split('.');
						for(var str in _path){
							data = data[_path[str]];
						}
					}
					callback.apply(self, self.settings.searchParam.datapath?result[self.settings.searchParam.datapath]:result);
				});
			} else {
				console.log('no more data!');
			}
		},
		loadDefaultItem : function(items) {
			var self = this;
			self.hasMoreData = true;
			self.load(items, self.setDefaultItem);
		},
		setDefaultItem : function(data) {

		},
		addOption : function(index, data) {
			var self = this;
			var $trapownDropdownContent = self.$trapownDropdownContent;
			var $item = self.$trapownDropdownItem.clone(true);
			$item.attr({
				'item_id' : self.trapownDropdownId + '_' + index,
				'value' : data[self.settings.searchParam.idField]
			}).text(data[self.settings.searchParam.textField]).data('trapown.data',data);
			$trapownDropdownContent.append($item);
		},
		removeOption : function(value) {
			var self = this;
			var $input = self.$input;
			var $trapownControl = self.$trapownControl;
			$input.find('option[value=' + value + ']').remove();
			$trapownControl.find('div[data-value=' + value + ']').remove();
		},
		clearOption : function() {
			var self = this;
			self.$trapownDropdownContent.find(
					'.' + self.settings.trapownDropdownItemClass).remove();
		},
		updateOptions : function() {
			var self = this;
			var pageIndex = self.pageIndex || 1;
			var pageSize = self.pageSize || 20;
			var indexStart = (pageIndex - 1) * pageSize + 1;
			$.each(arguments, function(index, item) {
				self.addOption(indexStart + index, item);
				self.cacheData(item);
			});
			if (arguments.length > 0) {
				self.pageIndex += 1;
			}
			if (arguments.length < pageSize) {
				self.hasMoreData = false;
			} else {
				self.hasMoreData = true;
			}
		},
		cacheData : function(item) {
			var self = this;
			if (self.needCacheData) {
				self.data.push(item);
			}
		},
		reset:function(){
			var self = this;
			self.data = [];
			self.initailed = false;
			self.settings.pageIndex = 1;
			self.hasMoreData = true;
			var $trapown = $('#'+self.trapownDropdownId);
			$trapown.find('.value-tag').remove();
			$trapown.find('.trapown-item').remove();
			self.$input.find('option').remove();
		},
		destroy:function(){
			var self = this;
			self.$input.remove();
			self.$trapownWrapper.remove();
			Trapown.count -=1;
		}
	};
	MicroEvent.mixin(Trapown);
	$.fn.trapown = function(settings_user) {
		var defaults = $.fn.trapown.defaults;
		var settings = {};
		var method = '';
		var params= {};
		if (typeof arguments[0] == 'object') {
			if(!$.isEmptyObject(arguments[0])){
				settings = $.extend({}, defaults, arguments[0]);
			}else{
				return;
			}
		} else if (typeof arguments[0] == 'string') {
			method = arguments[0];
			params= arguments[1];
			if(!Trapown.prototype[method]){
				return;
			}
		} else {
			console.log('unkown argument type,please check again !');
			return;
		}


		return this.each(function() {
			var instance = this.trapown;
			if (!instance) {
				instance = new Trapown($(this), settings);
			}
			if(method){
				Trapown.prototype[method].apply(instance,[params]);
			}
		});
	};
	$.fn.trapown.defaults = Trapown.defaults;
})(jQuery);