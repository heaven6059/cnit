/**
 * This jQuery plugin displays pagination links inside the selected elements.
 *
 * @author Gabriel Birke (birke *at* d-scribe *dot* de)
 * @version 1.1
 * @param {int} maxentries 数据总数
 * @param {Object} opts Several options (see README for documentation)
 * @return {Object} jQuery Object
 */

//maxentries	总条目数	必选参数，整数
//items_per_page	每页显示的条目数	可选参数，默认是10
//num_display_entries	连续分页主体部分显示的分页条目数	可选参数，默认是10
//current_page	当前选中的页面	可选参数，默认是0，表示第1页
//num_edge_entries	两侧显示的首尾分页的条目数	可选参数，默认是0
//link_to	分页的链接	字符串，可选参数，默认是"#"
//prev_text	“前一页”分页按钮上显示的文字	字符串参数，可选，默认是"Prev"
//next_text	“下一页”分页按钮上显示的文字	字符串参数，可选，默认是"Next"
//ellipse_text	省略的页数用什么文字表示	可选字符串参数，默认是"…"
//prev_show_always	是否显示“前一页”分页按钮	布尔型，可选参数，默认为true，即显示“前一页”按钮
//next_show_always	是否显示“下一页”分页按钮	布尔型，可选参数，默认为true，即显示“下一页”按钮
//callback	回调函数	默认无执行效果
jQuery.fn.yoyoPagination = function(maxentries, opts){
	opts = jQuery.extend({
		items_per_page:10,//每页显示数据条数
		num_display_entries:5,
		num_edge_entries: 2,
		current_page:0,
		link_to:"javascript:void(0);",
		prev_text:"上一页",
		next_text:"下一页",
		page_num_id:"page-num",
		page_num_name:"page-num",
		ellipse_text:"...",
		prev_show_always:true,
		next_show_always:true,		
		callback:function(){return false;}
	},opts||{});
	
	return this.each(function() {
		/**
		 * Calculate the maximum number of pages
		 */
		function numPages() {
			return Math.ceil(maxentries/opts.items_per_page);
		}
		
		/**
		 * Calculate start and end point of pagination links depending on 
		 * current_page and num_display_entries.
		 * @return {Array}
		 */
		function getInterval()  {
			var ne_half = Math.ceil(opts.num_display_entries/2);
			var np = numPages();
			var upper_limit = np-opts.num_display_entries;
			var start = current_page>ne_half?Math.max(Math.min(current_page-ne_half, upper_limit), 0):0;
			var end = current_page>ne_half?Math.min(current_page+ne_half, np):Math.min(opts.num_display_entries, np);
			return [start,end];
		}
		
		/**
		 * This is the event handling function for the pagination links. 
		 * @param {int} page_num The new page number
		 */
		function pageSelected(page_num, evt){			
			current_page = page_num;
			drawLinks();
			panel.append("<input type='hidden' name='"+opts.page_num_name+"' id='"+opts.page_num_id+"' value='"+(page_num+1)+"' />");			
			var continuePropagation = opts.callback();
			if (!continuePropagation) {
				if (evt.stopPropagation) {
					evt.stopPropagation();
				}
				else {
					evt.cancelBubble = true;
				}
			}
			return continuePropagation;
		}
		
		/**
		 * This function inserts the pagination links into the container element
		 */
		function drawLinks() {
			panel.empty();
			var interval = getInterval();
			var np = numPages();
			// This helper function returns a handler function that calls pageSelected with the right page_num
			var getClickHandler = function(page_num) {
				return function(evt){ return pageSelected(page_num,evt); }
			}
			// Helper function for generating a single link (or a span tag if it'S the current page)
			var appendItem = function(page_num, appendopts){				
				page_num = page_num<0?0:(page_num<np?page_num:np-1); // Normalize page id to sane value
				appendopts = jQuery.extend({text:page_num+1, classes:""}, appendopts||{});
				var link;
				if(page_num == current_page){					
					 link= $("<span class='current'>"+(appendopts.text)+"</span>");
				}else{
					link = $("<a page-num='"+(appendopts.text)+"'>"+(appendopts.text)+"</a>").bind("click", getClickHandler(page_num)).attr('href', opts.link_to.replace(/__id__/,page_num));
				}
				if(appendopts.classes){link.addClass(appendopts.classes);}
				panel.append(link);
			}
			// Generate "Previous"-Link
			if(opts.prev_text && (current_page > 0 || opts.prev_show_always)){
				appendItem(current_page-1,{text:opts.prev_text, classes:"prev"});
			}
			// Generate starting points
			if (interval[0] > 0 && opts.num_edge_entries > 0)
			{
				var end = Math.min(opts.num_edge_entries, interval[0]);
				for(var i=0; i<end; i++) {
					appendItem(i);
				}
				if(opts.num_edge_entries < interval[0] && opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
				}
			}
			// Generate interval links
			for(var i=interval[0]; i<interval[1]; i++) {
				appendItem(i);
			}
			// Generate ending points
			if (interval[1] < np && opts.num_edge_entries > 0)
			{
				if(np-opts.num_edge_entries > interval[1]&& opts.ellipse_text)
				{
					jQuery("<span>"+opts.ellipse_text+"</span>").appendTo(panel);
				}
				var begin = Math.max(np-opts.num_edge_entries, interval[1]);
				for(var i=begin; i<np; i++) {
					appendItem(i);
				}
				
			}
			// Generate "Next"-Link
			if(opts.next_text && (current_page < np-1 || opts.next_show_always)){
				appendItem(current_page+1,{text:opts.next_text, classes:"next"});
			}
		}
		
		// Extract current_page from options
		var current_page = opts.current_page;
		// Create a sane value for maxentries and items_per_page
		maxentries = (!maxentries || maxentries < 0)?1:maxentries;
		opts.items_per_page = (!opts.items_per_page || opts.items_per_page < 0)?1:opts.items_per_page;
		// Store DOM element for easy access from all inner functions
		var panel = jQuery(this);
		// Attach control functions to the DOM element 
		this.selectPage = function(page_num){ pageSelected(page_num);}
		this.prevPage = function(){ 
			if (current_page > 0) {
				pageSelected(current_page - 1);
				return true;
			}
			else {
				return false;
			}
		}
		this.nextPage = function(){ 
			if(current_page < numPages()-1) {
				pageSelected(current_page+1);
				return true;
			}
			else {
				return false;
			}
		}
		// When all initialisation is done, draw the links
		drawLinks();
	});
}


