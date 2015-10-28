$(function (){
	maintian.init();
});

var maintian={
	maintian_items:[],
	maintian_category_id:[],
	my_car_cook:"my_car",
	maintian_accessory:[],
	choose_company:null,
	is_login:"IS_LOGIN",
	init:function (){
		var mycar=this.readCookie(this.my_car_cook);
		if(mycar){
			mycar=mycar.split("|");
			$(".models1 h3 b").html(mycar[3]+" "+mycar[4]);
		}
		
		$(".addmainten").click(function (){
			var len =$(this).closest(".delist").find("input:checkbox:checked").length;
			if(len==0){
				layer.alert("请选择保养项目！",{title:false,closeBtn:false,icon:0});
				return;
			}
			var maintainItem={
				index:$(this).closest(".delist").index(),
				maintainText:$(this).attr("data-orgs"),
				maintainItems:[]
			};
			$(this).closest(".delist").find("input[type='checkbox']").each(function (x,item){
				if($(item).is(":checked")){
					if((maintian.maintian_category_id.join("").indexOf($(item).val()))==-1){
						maintian.maintian_category_id.push($(item).val());
					}
					if((maintian.maintian_accessory.join("").indexOf($(item).attr("data-key")))==-1){
						maintian.maintian_accessory.push($(item).attr("data-key"));
					}
					maintainItem.maintainItems.push($(item));
				}
			});
			maintian.addMaintainItem($(this).closest(".delist").index(),maintainItem);
			maintian.loadMaintainCompany();
		});
		var companyId=null;
		$(".nextstep").click(function (){
			companyId = $('#company_info input[name="company"]:checked ').val();
			var appDate=$("#appDate").val();
			if(appDate.length==0){
				layer.alert("请选择预计消费时间！",{title:false,closeBtn:false,icon:0});
				return;
			}
			if(companyId==null){
				layer.alert("请选择需预约店铺！",{title:false,closeBtn:false,icon:0});
				return;
			}
			
			window.location.href=yoyo.portalUrl+"/carMaintain/step3?maintainId="+$("#maintainId").val()+"&companyId="+companyId+"&ids="+maintian.maintian_accessory.join(",")+"&appDate="+appDate+"&accessoryCategoryIds="+maintian.maintian_category_id.join(",");
		});
	},
	addMaintainItem:function (index,newItem){
		var notJoin=true;
		$.each(maintian.maintian_items,function (x,item){
			if(item.index==index){
				maintian.maintian_items.splice(x, 1,newItem);
				notJoin=false;
				return notJoin;
			}
		});
		if(notJoin){
			maintian.maintian_items.push(newItem);
		}		
		maintian.buildMaintainList();
	},
	loadMaintainCompany:function(){
		if($("#maintainId").val()){
			$.post("../carMaintain/findMaintainCompany",{maintainId:$("#maintainId").val(),ids:maintian.maintian_accessory.join(",")},function (result){
				var html=[];
				if(result&&null!=result.content){
					$.each(result.content,function (x,item){
						html.push('<tr>');
						html.push('<td width="5%" style="text-align:center"><input type="radio" name="company" value="'+item.companyId+'" /></td>');
						html.push('<td width="30%">'+item.companyName+'</td>');
						html.push('<td width="45%">'+item.companyArea+"&nbsp;&nbsp;"+item.companyAddr+'</td>');
						html.push('<td width="20%">'+item.companyTel+'</td>');
						html.push('</tr>');
					});
				}
				$("#company_info tr:gt(0)").remove();
				$("#company_info").append(html.join(""));
			});
		}
	},
	buildMaintainList:function(){
		var maintainLit=[];
		$(".sList").find("tr:gt(0)").remove();
		$.each(maintian.maintian_items,function (x,items){
			$.each(items.maintainItems,function (x,item){				
				maintainLit.push('<tr>');
				if(x==0){
				maintainLit.push('<td rowspan="'+items.maintainItems.length+'">'+items.maintainText+'</td>');
				}
				maintainLit.push('<td class="project">'+item.attr("data-orgs")+'</td>');
				maintainLit.push('<td class="center"><a class="hidefocus" onclick="removeMaintain('+items.index+','+item.attr("data-key")+');" href="javascript:;">删除</a></td>');
				maintainLit.push('</tr>');				
			});
		});
		$(".sList").append(maintainLit.join(""));
	},
	readCookie:function (cookieName){
		var cookieV=$.cookie(cookieName);// 存储 cookie
		return cookieV;
	},
	writeCookie:function(cookieName,cookieValue){
		$.cookie(cookieName,cookieValue,{expires: 7, path: "/"}); // 存储 cookie
	},	
};

function removeMaintain(index,id){
	$.each(maintian.maintian_items,function (x,items){
		if(items&&items.index==index){
			$.each(items.maintainItems,function (y,item){
				if(item.attr("data-key")==id){
					items.maintainItems.splice(y, 1);									
					if(items.maintainItems.length==0){
						maintian.maintian_items.splice(x,1);
					}
					maintian.buildMaintainList();					
				}
			});
		}
	});
	
	$.each(maintian.maintian_accessory,function (x,items){
		if(items==id){
			maintian.maintian_accessory.splice(x,1);
			maintian.loadMaintainCompany();
		}
	});
	
	$(".tableSetp2").find("input[type='checkbox']").each(function (x,item){
		if($(item).attr("data-key")==id){			
			$(item).prop("checked",false);
		}		
	});	
}