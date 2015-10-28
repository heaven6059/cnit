/**
 * 出售中的商品
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
	$.ajax( {    
	    url:'../goods/goodsList',// 跳转到 action    
	    data:regData,    
	    type:'post',    
	    cache:false,    
	    dataType:'json',    
	    success:function(data) {
	    	if(data.retCode==0000){
	    		$(".gridlist tr:gt(1)").empty();
	    		var createtime = null;
	    		$.each(data.content.rows,function (x,good){
	    			createtime = good.upTime;
	    			var html=
					'<tbody>'+
						'<tr class="tr-th">'+
							'<td colspan="6"><span class="tcol1"><input type="checkbox" name="goodsItem" class="selector" value="'+good.goodsId+'">商品编码:<a href="">'+good.fbn+'</a></span></td>'+
						'</tr>';
	    				html+='<tr>'+						
							'<td class="product"><a href="#" onclick="openGoodsLink(' + good.goodsId+');" ><img data-img="'+good.smallPic+'" src="../resources/images/default_white.png" width="80px" height="80px" /></a></td>'+
							'<td class="product_name"><a href="#" onclick="openGoodsLink(' + good.goodsId+');" >'+
								'<label class="col1" style="cursor:pointer;">'+good.name+'</label></a>'+
							'</td>'+
							'<td>'+formatCurrency(good.price,true)+'</td>'+
							'<td>'+formatCurrency(good.store,false)+'</td>'+
							/*'<td>'+formatCurrency(good.storeFreeze,false)+'</td>'+*/
							'<td>'+good.buyCount+'</td>'+
							'<td>';
							if(createtime!=null && createtime!=''){
								html += createtime.substring(0,4)+'-'+createtime.substring(4,6)+'-'+createtime.substring(6,8)+' ';
								html +=createtime.substring(8,10)+':'+createtime.substring(10,12)+':'+createtime.substring(12,14);
							}
							html +='</td>';
									/*'<td></td>';*/
							html+='</tr></tbody>';
					$(".gridlist").append(html);
	    		});
	    		if(option&&option.refresh){
	    			var opt = {
	    				items_per_page:data.content.pageSize,
	    				callback:function (){
	    					var op={
	    						refresh:false
	    					};
	    					initPageList(op);
	    				}
    				};
	    			$("#Pagination").yoyoPagination(data.content.total,opt);
	    			
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
		}else if(action=='down'){
			info = '确认下架商品？';
		}else if(action=='alert'){
			info = '确认设置预警值？';
			if(null == $("#alertValue").val() || '' == $("#alertValue").val()) {
				$.messager.alert('错误', '请填写预警值！', 'error');
				return false;
			}
		}
		$.messager.confirm('确认', info, function(r) {
			if (r) {
				var params = {};
				params.op = action;
				params.rows = JSON.stringify(rows);
				params.alertValue = $("#alertValue").val();
				commonAjax(_path + '/goods/batchEdit', params, function(data) {
					var op={
							refresh:false
						};
					initPageList(op);
					$("#checkAll").attr('checked',false);
				}, function(data) {
					alert("retcode="+data.head.retCode);
					if(data.head.retCode == "000013") {
						easyuiMsg('失败', "设置的预警值比所选择的商品的最小库存值大!");
					}else {
						easyuiMsg('失败', "操作失败!");
					}
					
				});
			}
		});
		
	}
}





