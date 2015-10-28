<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="body" class="body">
	<div id="messagebox" class="msgbox ">加载完成...</div>
	<div id="container" class="container clearfix"
		style="height: 100%; width: 100%;">
		<div id="workground" class="workground">
			<div style="" class="content-head">
				<h2 class="head-title span-auto">列表</h2>
			</div>
			<div id="main" class="content-main"
				style="height: auto; width: auto;">
				<div class="finder-list finder-normal">
					<table width="100%" cellspacing="0" cellpadding="0" id="finder-list">
						<tbody>
							<tr>
								<th width="10%"><input type="checkbox" id="choose_all" /></th>
								<th width="90%"><h5 class="cell">分类名称</h5></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div style="" class="content-foot">
				<div class="finder-submit-btn ">
					<table cellspacing="0" cellpadding="0" class="table-action">
						<tbody>
							<tr valign="middle">
								<td>
									<button type="button" id="choose_category_btn" class="btn btn-primary dialogBtn">
										<span><span>确 定</span></span>
									</button>
									<button type="button" id="close_category_btn" class="btn btn-secondary close">
										<span><span>关 闭</span></span>
									</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="MODALPANEL"
	style="position: absolute; background: none repeat scroll 0% 0% rgb(51, 51, 51); width: 100%; height: 673px; top: 0px; left: 0px; z-index: 65500; opacity: 0.4; display: none;"></div>
<script type="text/javascript">
	$(function() {
		$.ajax({
			url : '../scoreBuyActivity/findAllMainCategory',
			type : 'post',
			cache : false,
			dataType : 'json',
			success : function(data) {
				if (data.retCode == 0000) {
					$.each(data.content, function(x, content) {
						$("#finder-list").append(intiCategorytHTML(content));
					});
				} else {
					alert("加载商品收藏信息失败");
				}
			},
			error : function() {
				alert("异常！");
			}
		});
		
		$("#choose_all").click(function (){
			var curObj=$(this);			
			$("#finder-list").find("input[name='items']").each(function (){
				if(curObj.is(":checked")){
					$(this).prop("checked",true);
				}else{
					$(this).prop("checked",false);
				}
			});
		});
		
		$("#choose_category_btn").click(function() {
			var categoryItem="";
			var categorysArray=new Array();
			$("#finder-list").find("input[name='items']").each(function (){
				if($(this).is(":checked")){
				categorysArray.push($(this).val());
				categoryItem+='<div class="row">'+
						  		'<div style="float: left;">'+$(this).attr("data")+'</div>'+
					     		'<div style="float: left;" class="opt">'+
							     	'<img data="'+$(this).val()+'" src="../resources/images/delecate.gif" app="desktop">'+							     	
					    		'</div>'+
							  '</div>';
				}
			});
			$("#categorys").val(categorysArray.join(","));
			$("#choose_cateogry_items").html(categoryItem);
			closeCategoryDialog();
		});
		
		$("#close_category_btn").click(function (){
			closeCategoryDialog();
		});
	});
	function intiCategorytHTML(content) {
		var categorys= new Array();
		categorys=$("#categorys").val().split(",");
		var html = '<tbody>'+
					'<tr class="row odd first ">'+
						'<td>';
						if(categorys.arryIndexOf(content.catId)>=0){
							html+='<input type="checkbox" value="'+content.catId+'" checked="checked" name="items" data='+content.catName+' class="sel ">';
						}else{
							html+='<input type="checkbox" value="'+content.catId+'" name="items" data='+content.catName+' class="sel ">';
						}
						html+='</td>'+
						'<td key="cat_name"><div class="cell">'+ content.catName + '</div></td>'+
					'</tr>'+
				'</tbody>';
		return html;
	}
	
	Array.prototype.arryIndexOf=function(val){
		for(var i=0;i<this.length;i++){
			if(val==this[i]){
				return i;
			}
		}
		return -1
	}
	
	Array.prototype.elremove = function(m){  
	    if(isNaN(m)||m>this.length){return false;}  
	    this.splice(m,1);  
	};
</script>

