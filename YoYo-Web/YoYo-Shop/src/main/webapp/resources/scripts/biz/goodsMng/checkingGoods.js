/**
 * 审核中的商品
 */
$(function() {
	initRealCateTree('goodsCategory', false);
	$("#btnsearch").click(function (){
		var op={
			refresh:true
		};
		$("#page-num").val("0");
		initPageList(op);
	});
	// 创建分页元素
	$("#Pagination").yoyoPagination(total, {
		items_per_page:rows,
		callback:function (){
			var op={
				refresh:false
			};
			initPageList(op);
		}
	});
	
	/**全选*/
	$("#checkAll").click(function(){
	     if(this.checked){
	    	 $("input[name='goodsItem']").each(function(){this.checked=true;}); 
    	 }else{ 
    		 $("input[name='goodsItem']").each(function(){this.checked=false;}); 
    	 } 
	    	 
	});
	
	/**加载图片*/
	loadPicture();
	
});



/**
 * 分页操作
 * @param option
 */
function initPageList(option){
	var regData = biz.serializeObject($("#searchForm"));
	regData.page = $("#page-num").val();
	regData.rows = 10;
	regData.marketableQ=2;
	regData.isChecking=1; //审核不通过的
	$.ajax( {    
	    url:'../goods/checkingList',// 跳转到 action    
	    data:regData,    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {console.log(data);
	    	if(data.resultObject.retCode==0000){
	    		$(".gridlist tr:gt(1)").empty();
	    		var createtime = null;
	    		$.each(data.resultObject.content.rows,function (x,good){
	    			createtime = good.lastModify;
	    			var html=
					'<tbody>'+
						'<tr class="tr-th">'+
							'<td colspan="9"><span class="tcol1"><input type="checkbox" name="goodsItem" class="selector" value="'+good.goodsId+'">商品编码:<a href="">'+good.fbn+'</a></span></td>'+
						'</tr>';
	    				html+='<tr>'+						
							'<td class="product"><img data-img="'+good.smallPic+'" src="../resources/images/default_white.png" width="80px" height="80px" /></td>'+
							'<td class="product_name">'+
								'<label class="col1">'+good.name+'</label>'+
							'</td>'+
	    				'<td>'+formatCurrency(good.price,true)+'</td>'+
						'<td>'+formatCurrency(good.store,false)+'</td><td>';
							
							if(createtime!=null && createtime!=''){
								html += createtime.substring(0,4)+'-'+createtime.substring(4,6)+'-'+createtime.substring(6,8)+' ';
								html +=createtime.substring(8,10)+':'+createtime.substring(10,12)+':'+createtime.substring(12,14);
							}
							html+="</td>";
							if(good.marketable=='2'){
								html+='<td>审核中</td>';
							}else if(good.marketable=='3'){
								html+='<td>审核不通过</td>';
							}
							
							if(good.p22!=null){
								html+='<td>'+good.p22+'</td><td>';
							}else{
								html+='<td></td><td>';
							}
							
							if(good.p23!=null && good.p23!=''){
								html += good.p23.substring(0,4)+'-'+good.p23.substring(4,6)+'-'+good.p23.substring(6,8)+' ';
								html +=good.p23.substring(8,10)+':'+good.p23.substring(10,12)+':'+good.p23.substring(12,14);
							}
							html+='</td><td>';
							if(good.marketable=='3'){ //审核不通过的才能编辑
								html +=	'<a class="font-blue operate-btn" href="../goods/editIndex/'+good.identifier+'/'+good.goodsId+'">编辑商品</a>';
							}
							html+='</td></tr></tbody>';
					$(".gridlist").append(html);
	    		});
	    		if(option&&option.refresh){
	    			var opt = {
	    				items_per_page:data.resultObject.content.pageSize,
	    				callback:function (){
	    					var op={
	    						refresh:false
	    					};
	    					initPageList(op);
	    				}
    				};
	    			$("#Pagination").yoyoPagination(data.resultObject.content.total,opt);
	    		}
	    		/**加载图片*/
    			loadPicture();
	    	}
	     },  
	     error : function() {
	          alert("异常！");
	     }
	});
}



/**获得选择的商品**/
function vailidateOperate(){
	var rows = [];
	$("input[name=goodsItem]:checked").each(function(){ 
		rows.push('{"goodsId":'+$(this).val()+'}'); 
	 });
	
	return rows;
}



/**删除商品,商品下架
 * action:delete,down**/
function operationItem(action){
	var rows = vailidateOperate();
	if (rows.length < 1) {
		easyuiMsg('提示', '请选择要操作的数据项!');
		return false;
	} else {
		var info = "";
		if(action=='delete'){
			info = '确认删除商品？';
		}else if(action=='up'){
			info = '确认上架商品？';
		}
		$.messager.confirm('确认', info, function(r) {
			if (r) {
				var params = {};
				params.op = action;
				params.rows = JSON.stringify(rows);
				commonAjax(_path + '/goods/batchEdit', params, function(data) {
					var op={
							refresh:false
						};
					initPageList(op);
					$("#checkAll").attr('checked',false);
				}, function(data) {
					easyuiMsg('失败', "操作失败!");
				});
			}
		});
		
	}
}




